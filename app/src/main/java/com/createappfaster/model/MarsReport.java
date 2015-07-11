package com.createappfaster.model;

/**
 * Created by arpit on 09/07/15.
 */
public class MarsReport {
    private String terrestrialDate;
    private String marsDate;
    private String minTemp;
    private String maxTemp;
    private String windSpeed;
    private String pressure;
    private String weatherStatus;

    public String getTerrestrialDate() {
        return terrestrialDate;
    }

    public String getMarsDate() {
        return marsDate;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getPressure() {
        return pressure;
    }

    public String getWeatherStatus() {
        return weatherStatus;
    }

    public void setTerrestrialDate(String terrestrialDate) {
        this.terrestrialDate = terrestrialDate;
    }

    public void setMarsDate(String marsDate) {
        this.marsDate = marsDate;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setWeatherStatus(String weatherStatus) {
        this.weatherStatus = weatherStatus;
    }
}

