package com.talhamustafa.i180573_180417;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;

import androidx.core.content.ContextCompat;

import java.lang.ref.WeakReference;

public class ScreenshotDetectionDelegate {
    private WeakReference<Activity> activityWeakReference;
    private ScreenshotDetectionListener listener;

    public ScreenshotDetectionDelegate(Activity activityWeakReference, ScreenshotDetectionListener listener) {
        this.activityWeakReference = new WeakReference<>(activityWeakReference);
        this.listener = listener;
    }

    public void startScreenshotDetection() {
        activityWeakReference.get()
                .getContentResolver()
                .registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, contentObserver);
    }

    public void stopScreenshotDetection() {
        activityWeakReference.get().getContentResolver().unregisterContentObserver(contentObserver);
    }

    private ContentObserver contentObserver = new ContentObserver(new Handler()) {
        @Override
        public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            if (isReadExternalStoragePermissionGranted()) {

                onScreenCaptured("");

            } else {
                onScreenCapturedWithDeniedPermission();
            }
        }
    };

    private void onScreenCaptured(String path) {
        if (listener != null) {
            listener.onScreenCaptured(path);
        }
    }

    private void onScreenCapturedWithDeniedPermission() {
        if (listener != null) {
            listener.onScreenCapturedWithDeniedPermission();
        }
    }


    private boolean isReadExternalStoragePermissionGranted() {
        return ContextCompat.checkSelfPermission(activityWeakReference.get(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public interface ScreenshotDetectionListener {
        void onScreenCaptured(String path);

        void onScreenCapturedWithDeniedPermission();
    }
}