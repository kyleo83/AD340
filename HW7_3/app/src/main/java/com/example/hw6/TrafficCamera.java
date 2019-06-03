package com.example.hw6;

public class TrafficCamera {

    private double cameraLatitude;
    private double cameraLongitude;
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

    public double getCameraLatitude() { return cameraLatitude; }

    public double getCameraLongitude() { return cameraLongitude; }

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

        public Builder cameraLatitude(double cameraLatitude) {
            camera.cameraLatitude = cameraLatitude;
            return this;
        }

        public Builder cameraLongitude(double cameraLongitude) {
            camera.cameraLongitude = cameraLongitude;
            return this;
        }

        public TrafficCamera build() {
            return camera;
        }
    }

}


