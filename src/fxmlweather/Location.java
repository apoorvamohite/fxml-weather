package fxmlweather;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kiran
 */
public class Location {
    
    private String mName;
    private int mTemperature;
    private String mHumidity;
    private String mPrecipitation;
    private String mWind;
    private String mCondition;
    private String mTime;
    private String[] forecastCondition;
    private String[] forecastTemperature;
    
    public Location(String name, int temp, String hum, String prec, String wind, String cond, String time){
        mName = name;
        mTemperature = temp;
        mHumidity = hum;
        mPrecipitation = prec;
        mWind = wind;
        mCondition = cond;
        mTime = time;
    }
    
    public String getName(){
        return mName;
    }
    
    public int getTemperature(){
        return mTemperature;
    }
    public String getHumidity(){
        return mHumidity;
    }
    public String getPrecipitation(){
        return mPrecipitation;
    }
    public String getWind(){
        return mWind;
    }
    public String getCondition(){
        return mCondition;
    }
    public String getTime(){
        return mTime;
    }
    public void setForecastTemperature(String d1, String d2, String d3, String d4, String d5, String d6, String d7){
        forecastTemperature = new String[]{d1, d2, d3, d4, d5, d6, d7};
    }
    public void setForecastCondition(String d1, String d2, String d3, String d4, String d5, String d6, String d7){
        forecastCondition = new String[]{d1, d2, d3, d4, d5, d6, d7};
    }
    public String getForecastTemperature(int index){
        return forecastTemperature[index];
    }
    public String getForecastCondition(int index){
        return forecastCondition[index];
    }
}
