package com.example.hw6;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TrafficCamera {

    private String cameraLocation;
    private String cameraImage;
    private String dot;

    public TrafficCamera(String cameraLocation) {
        this.cameraLocation = cameraLocation;
    }

    public String getCameraLocation() {
        return cameraLocation;
    }

    public String getCameraImage() {
        return cameraImage;
    }

    public String getDot() {
        return dot;
    }

    public static class Builder {
        TrafficCamera camera;

        public Builder() {
            camera = new TrafficCamera("");
        }

        public Builder cameraLocation(String cameraLocation) {
            camera.cameraLocation = cameraLocation;
            return this;
        }

        public Builder cameraImage(String cameraImage) {
            camera.cameraImage = cameraImage;
            return this;
        }

        public TrafficCamera build() {
            return camera;
        }
    }

}


