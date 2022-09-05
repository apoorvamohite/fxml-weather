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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author kiran
 */
public class WunderWeather {
    
    
    public static Location fetchWeatherData(String currentStringUrl){
        // Step 1. Create URL from String
        URL currentUrl = createUrl(currentStringUrl);
        
        // Step 2. HTTP connection and get JSON response
        String jsonResponse = getJsonResponse(currentUrl);
        
        // Step 3. Parse JSON and get back an object of type Location
        Location currentLocation = parseJsonResponse(jsonResponse);
        return currentLocation;
    }
    
    private static URL createUrl(String urlString){
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            
        }
        return url;
    }
    
    
    private static String getJsonResponse(URL url){
        
        StringBuilder output = new StringBuilder();
        
        try{
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while(line!=null){
                output.append(line);
                line= br.readLine();
            }
        }
        catch(IOException e){
        
        }
        return output.toString();
    }
    
    private static Location parseJsonResponse(String response){
        Location locationToBeReturned=null;
        try{
            JSONObject root = new JSONObject(response);
            JSONObject currentObservation = root.getJSONObject("current_observation");
            JSONObject observationLocation = currentObservation.getJSONObject("display_location");
            String currentLocation = observationLocation.getString("full");
            int currentTemp = currentObservation.getInt("temp_c");
            String currentHumidity = currentObservation.getString("relative_humidity");
            String currentPrecip = currentObservation.getString("precip_today_metric");
            String currentWind = currentObservation.getString("wind_string");
            String currentCondition = currentObservation.getString("weather");
            String currentTime = currentObservation.getString("local_time_rfc822");
            
            JSONArray currentForecast = root.getJSONObject("forecast").getJSONObject("simpleforecast").getJSONArray("forecastday");
            JSONObject d1 = currentForecast.getJSONObject(1);
            JSONObject d2 = currentForecast.getJSONObject(2);
            JSONObject d3 = currentForecast.getJSONObject(3);
            JSONObject d4 = currentForecast.getJSONObject(4);
            JSONObject d5 = currentForecast.getJSONObject(5);
            JSONObject d6 = currentForecast.getJSONObject(6);
            JSONObject d7 = currentForecast.getJSONObject(7);
            
            locationToBeReturned = new Location(currentLocation,
                                                currentTemp,
                                                currentHumidity, 
                                                currentPrecip,
                                                currentWind,
                                                currentCondition,
                                                currentTime);
            
            locationToBeReturned.setForecastTemperature(d1.getJSONObject("high").getString("celsius"),
                                                        d2.getJSONObject("high").getString("celsius"),
                                                        d3.getJSONObject("high").getString("celsius"),
                                                        d4.getJSONObject("high").getString("celsius"),
                                                        d5.getJSONObject("high").getString("celsius"),
                                                        d6.getJSONObject("high").getString("celsius"),
                                                        d7.getJSONObject("high").getString("celsius"));
            
            locationToBeReturned.setForecastCondition(d1.getString("conditions"),
                                                      d2.getString("conditions"),
                                                      d3.getString("conditions"),
                                                      d4.getString("conditions"),
                                                      d5.getString("conditions"),
                                                      d6.getString("conditions"),
                                                      d7.getString("conditions"));
        }
        catch(JSONException e){
        
        }
        return locationToBeReturned;
    }
    
}
