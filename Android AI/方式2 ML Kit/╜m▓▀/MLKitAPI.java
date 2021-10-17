package com.example.mlkit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import com.google.mlkit.vision.objects.DetectedObject;
import com.google.mlkit.vision.objects.ObjectDetection;
import com.google.mlkit.vision.objects.ObjectDetector;
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions;
import com.google.mlkit.vision.objects.defaults.PredefinedCategory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MLKitAPI {

    private static final String TAG = "MLKitApp";
    private ObjectDetector mObjectDetector;
    private Context mCtx;
    private static MLKitAPI mMLKitAPI;
    private ImageLabeler mLabeler;

    private MLKitAPI(Context ctx) {
        mCtx = ctx;
        init();
    }

    public static MLKitAPI getInstance(Context ctx) {
        if (mMLKitAPI == null) {
            return mMLKitAPI = new MLKitAPI(ctx);
        } else
            return mMLKitAPI;
    }

    private void init() {
        ObjectDetectorOptions options =
                new ObjectDetectorOptions.Builder()
                        .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
                        // 啟用多物件偵測
                        .enableMultipleObjects()
                        // 當啟用時，則分類為 fashion goods, food, home goods, places, and plants
                        .enableClassification()
                        .build();

        mObjectDetector = ObjectDetection.getClient(options);
        mLabeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS);
    }

    private InputImage inputImage(Bitmap bitmap) {
        InputImage image = InputImage.fromBitmap(bitmap, 90);
        return image;
    }

    private InputImage inputImage(String path) {
        Uri uri = Uri.fromFile(new File(path));
        try {
            return InputImage.fromFilePath(mCtx, uri);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void processImage(InputImage inputImage) {
        mObjectDetector.process(inputImage)
                .addOnSuccessListener(
                        new OnSuccessListener<List<DetectedObject>>() {
                            @Override
                            public void onSuccess(List<DetectedObject> detectedObjects) {
                                // 當照片無法辨識的時候，detectedObjects的size==0，若是一個物體，則size==1，依此類推
                                Log.d(TAG, "detectedObjects.size=" + detectedObjects.size());
                                // The list of detected objects contains one item if multiple
                                // object detection wasn't enabled.
                                List<Map<String, Object>> resultList = new ArrayList<>();
                                for (DetectedObject detectedObject : detectedObjects) {
                                    Rect boundingBox = detectedObject.getBoundingBox();
                                    Integer trackingId = detectedObject.getTrackingId();
                                    List<DetectedObject.Label> labels = detectedObject.getLabels();
                                    Log.d(TAG, "labels.size()=" + labels.size());
                                    if (labels.size() != 0) { // 注意，getLabels()的Size可能為0
                                        for (DetectedObject.Label label : labels) {
                                            Map<String, Object> map = new HashMap<>();
                                            String text = label.getText();
                                            if (PredefinedCategory.FOOD.equals(text)) {

                                            }
                                            int index = label.getIndex();
                                            if (PredefinedCategory.FOOD_INDEX == index) {

                                            }
                                            float confidence = label.getConfidence();
                                            map.put("action", "Classification");
                                            map.put("text", text);
                                            map.put("index", index);
                                            map.put("confidence", confidence);
                                            Log.d(TAG, "--------------------------");
                                            Log.d(TAG, "action=Classification");
                                            Log.d(TAG, "text=" + text);
                                            Log.d(TAG, "index=" + index);
                                            Log.d(TAG, "confidence=" + confidence);
                                            Log.d(TAG, "--------------------------");
                                            resultList.add(map);
                                        }
                                    } else {
                                        Map<String, Object> map = new HashMap<>();
                                        map.put("action", "Classification");
                                        map.put("text", "N/A");
                                        map.put("index", -1);
                                        map.put("confidence", 0f);
                                        resultList.add(map);
                                    }
                                }

                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                // ...
                                Log.d(TAG, "Exception=" + e);
                            }
                        });

        mLabeler.process(inputImage)
                .addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
                    @Override
                    public void onSuccess(List<ImageLabel> labels) {
                        // Task completed successfully
                        List<Map<String, Object>> resultList = new ArrayList<>();
                        if (labels.size() != 0) {
                            for (ImageLabel label : labels) {
                                Map<String, Object> map = new HashMap<>();
                                String text = label.getText();
                                float confidence = label.getConfidence();
                                int index = label.getIndex();
                                map.put("action", "ImageLabeling");
                                map.put("text", text);
                                map.put("index", index);
                                map.put("confidence", confidence);
                                Log.d(TAG, "--------------------------");
                                Log.d(TAG, "action=ImageLabeling");
                                Log.d(TAG, "text=" + text);
                                Log.d(TAG, "index=" + index);
                                Log.d(TAG, "confidence=" + confidence);
                                Log.d(TAG, "--------------------------");
                                resultList.add(map);
                            }
                        }else{
                            Map<String, Object> map = new HashMap<>();
                            map.put("action", "ImageLabeling");
                            map.put("text", "N/A");
                            map.put("index", -1);
                            map.put("confidence", 0f);
                            resultList.add(map);
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task failed with an exception
                        // ...
                    }
                });
    }

    public void parseImage(final Bitmap bitmap) {
        processImage(inputImage(bitmap));
    }

    public void parseImage(final String path) {
        processImage(inputImage(path));
    }
}
