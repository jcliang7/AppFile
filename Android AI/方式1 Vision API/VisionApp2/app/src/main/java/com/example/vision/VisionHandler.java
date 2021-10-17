package com.example.vision;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.AnnotateImageResponse;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.api.services.vision.v1.model.LocalizedObjectAnnotation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisionHandler {

    private static final String TAG = "OBJECT_Detection";
    private Context mContext;
    private Vision mVision;
    private String message;
    private File mFilePath;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
        }
    };

    private Runnable mLabelDetectionRunnable = new Runnable() {
        @Override
        public void run() {

            Bundle bundle = new Bundle();
            Message msg = new Message();
            try {
                InputStream is = new FileInputStream(mFilePath);
                byte[] buffer = new byte[2048];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while (is.read(buffer, 0, buffer.length) > 0) {
                    baos.write(buffer);
                }

                byte[] imageBytes = baos.toByteArray();

                Image inputImage = new Image();
                inputImage.encodeContent(imageBytes);

                Feature desiredFeature = new Feature();
                desiredFeature.setType("OBJECT_LOCALIZATION");

                AnnotateImageRequest request = new AnnotateImageRequest();
                request.setImage(inputImage);

                ArrayList<Feature> list1 = new ArrayList<>();
                list1.add(desiredFeature);

                request.setFeatures(list1);

                BatchAnnotateImagesRequest batchRequest =
                        new BatchAnnotateImagesRequest();

                batchRequest.setRequests(Arrays.asList(request));

                BatchAnnotateImagesResponse batchResponse
                        = mVision.images().annotate(batchRequest).execute();

                //https://cloud.google.com/vision/docs/labels
                List<AnnotateImageResponse> responses = batchResponse.getResponses();

                String likelihoods = "";
                for (AnnotateImageResponse res : responses) {
                    List<LocalizedObjectAnnotation> list = res.getLocalizedObjectAnnotations();

                    // For full list of available annotations, see http://g.co/cloud/vision/docs
                    for (LocalizedObjectAnnotation anno : list) {
                        float score = anno.getScore();
                        String description = anno.getName();
                        bundle.putFloat("Score", score);
                        bundle.putString("Description", description);
                        likelihoods += "\n" + (int) (score * 100) + "% chance is " + description;
//                        break;
                    }
                }
                // Concatenate
                message = "This result is:" + likelihoods;
            } catch (IOException e) {
                e.printStackTrace();
                message = "";
            } finally {
                Log.d(TAG, "message = " + message);
                bundle.putString("Message", message);
                msg = new Message();
                msg.setData(bundle);
                mHandler.sendMessage(msg);
            }
        }
    };

    public VisionHandler(Context ctx) {
        mContext = ctx;
        initVision();
    }

    private void initVision() {
        Vision.Builder visionBuilder = new Vision.Builder(
                new NetHttpTransport(),
                new AndroidJsonFactory(),
                null);

        visionBuilder.setVisionRequestInitializer(new VisionRequestInitializer(MyVisionApiKey.API_KEY1_VISION));
        mVision = visionBuilder.build();
    }

    public void setFilePath(File filePath) {
        mFilePath = filePath;
    }

    public void parse() {
        AsyncTask.execute(mLabelDetectionRunnable);
    }
}
