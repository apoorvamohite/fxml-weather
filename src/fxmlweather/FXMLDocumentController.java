/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlweather;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author kiran
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label currentCondition;

    @FXML
    private ImageView currentImage;

    @FXML
    private Label currentTemp;

    @FXML
    private Label currentLocation;

    @FXML
    private Label currentDate;

    @FXML
    private Label currentHumidity;

    @FXML
    private Label currentPrecip;

    @FXML
    private Label currentWind;

    @FXML
    private ImageView d1Img;

    @FXML
    private ImageView d2Img;

    @FXML
    private ImageView d3Img;

    @FXML
    private ImageView d4Img;

    @FXML
    private ImageView d5Img;

    @FXML
    private ImageView d6Img;

    @FXML
    private ImageView d7Img;

    @FXML
    private ImageView loc;

    @FXML
    private Label d1Temp;

    @FXML
    private Label d2Temp;

    @FXML
    private Label d3Temp;

    @FXML
    private Label d4Temp;

    @FXML
    private Label d5Temp;

    @FXML
    private Label d6Temp;

    @FXML
    private Label d7Temp;

    @FXML
    private Label d1Day;

    @FXML
    private Label d2Day;

    @FXML
    private Label d3Day;

    @FXML
    private Label d4Day;

    @FXML
    private Label d5Day;

    @FXML
    private Label d6Day;

    @FXML
    private Label d7Day;

    @FXML
    private ComboBox combo;

    public Location bangalore = null;
    public Location nyc = null;
    public Location bom = null;
    public Location lon = null;
    public Location syd = null;
    public Location city = null;

    private String[] days = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};

    private static final String BGM_STRING_URL
            = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/India/Bangalore.json";
    private static final String BOM_STRING_URL
            = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/India/Mumbai.json";
    private static final String NYC_STRING_URL
            = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/NY/New_York.json";
    private static final String LON_STRING_URL
            = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/UK/London.json";
    private static final String SYD_STRING_URL
            = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/AU/Sydney.json";

    @FXML
    private void handleButtonBlr(ActionEvent event) {
        bangalore = WunderWeather.fetchWeatherData(BGM_STRING_URL);
        displayData(bangalore);
    }

    @FXML
    private void handleButtonNyc(ActionEvent event) {
        nyc = WunderWeather.fetchWeatherData(NYC_STRING_URL);
        displayData(nyc);
    }

    @FXML
    private void handleButtonBom(ActionEvent event) {
        bom = WunderWeather.fetchWeatherData(BOM_STRING_URL);
        displayData(bom);
    }

    @FXML
    private void handleButtonLon(ActionEvent event) {
        lon = WunderWeather.fetchWeatherData(LON_STRING_URL);
        displayData(lon);
    }

    @FXML
    private void handleButtonSyd(ActionEvent event) {
        syd = WunderWeather.fetchWeatherData(SYD_STRING_URL);
        displayData(syd);
    }

    @FXML
    private void handleButtonCity(ActionEvent event) {
        String cityLoc = (String) combo.getValue();
        city = WunderWeather.fetchWeatherData(generateURL(cityLoc));
        displayData(city);
    }

    private void setLocation() {
        loc.setImage(new Image(getClass().getClassLoader().getResourceAsStream("resources/location.png")));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setLocation();
        combo.getItems().add("Belgaum");
        combo.getItems().add("Chennai");
        combo.getItems().add("Kolkata");
        combo.getItems().add("New Delhi");
        combo.getItems().add("Pune");
        combo.getItems().add("Amsterdam, Netherlands");
        combo.getItems().add("Auckland, NZ");
        combo.getItems().add("Beijing, China");
        combo.getItems().add("Berlin, Germany");
        combo.getItems().add("Dubai, UAE");
        combo.getItems().add("Hong Kong, China");
        combo.getItems().add("Honolulu, Hawaii");
        combo.getItems().add("Johannesburg, SA");
        combo.getItems().add("Magadan, Russia");
        combo.getItems().add("Ottawa, Canada");
        combo.getItems().add("Paris, France");
        combo.getItems().add("San Francisco, CA");
        combo.getItems().add("San Jose, CA");
        combo.getItems().add("San Jose, Nicaragua");
        combo.getItems().add("Seoul, South Korea");
        combo.getItems().add("Tokyo, Japan");
        combo.getItems().add("Yakutsk, Russia");
    }

    private void displayData(Location location) {

        int currentDay = getProperDay(location.getTime().substring(0, 3));

        currentLocation.setText(location.getName());
        currentDate.setText(location.getTime().substring(0, 22));
        currentCondition.setText(location.getCondition());
        currentTemp.setText("" + location.getTemperature() + "° C");
        currentHumidity.setText(location.getHumidity());
        currentPrecip.setText(location.getPrecipitation() + " mm");
        String wind = location.getWind().substring(0, location.getWind().indexOf("MPH") + 3);
        if (!location.getWind().contains("MPH")) {
            currentWind.setText(location.getWind());
        } else {
            currentWind.setText(wind);
        }
        int time = Integer.parseInt(location.getTime().substring(17, 19));
        currentImage.setImage(getProperImage(location.getCondition(), time));

        d1Temp.setText(location.getForecastTemperature(0) + "° C");
        d1Day.setText(days[(currentDay + 1) % 7]);
        d1Img.setImage(getProperImage(location.getForecastCondition(0), 9));

        d2Temp.setText(location.getForecastTemperature(1) + "° C");
        d2Day.setText(days[(currentDay + 2) % 7]);
        d2Img.setImage(getProperImage(location.getForecastCondition(1), 9));

        d3Temp.setText(location.getForecastTemperature(2) + "° C");
        d3Day.setText(days[(currentDay + 3) % 7]);
        d3Img.setImage(getProperImage(location.getForecastCondition(2), 9));

        d4Temp.setText(location.getForecastTemperature(3) + "° C");
        d4Day.setText(days[(currentDay + 4) % 7]);
        d4Img.setImage(getProperImage(location.getForecastCondition(3), 9));

        d5Temp.setText(location.getForecastTemperature(4) + "° C");
        d5Day.setText(days[(currentDay + 5) % 7]);
        d5Img.setImage(getProperImage(location.getForecastCondition(4), 9));

        d6Temp.setText(location.getForecastTemperature(5) + "° C");
        d6Day.setText(days[(currentDay + 6) % 7]);
        d6Img.setImage(getProperImage(location.getForecastCondition(5), 9));

        d7Temp.setText(location.getForecastTemperature(6) + "° C");
        d7Day.setText(days[(currentDay + 7) % 7]);
        d7Img.setImage(getProperImage(location.getForecastCondition(6), 9));
    }

    private Image getProperImage(String condition, int time) {
        Image image = null;
        if (condition.contains("Clear")) {
            if (time <= 5 || time >= 19) {
                image = new Image(getClass().getClassLoader().getResourceAsStream("resources/moon.png"));
            } else {
                image = new Image(getClass().getClassLoader().getResourceAsStream("resources/sun.png"));
            }
        } else if (condition.contains("Funnel Cloud")) {
            image = new Image(getClass().getClassLoader().getResourceAsStream("resources/tornado.png"));
        } else if (condition.contains("Mostly") || condition.contains("Overcast")) {
            image = new Image(getClass().getClassLoader().getResourceAsStream("resources/clouds.png"));
        } else if (condition.contains("Partly Cloudy") || condition.contains("Scattered Clouds")) {
            if (time <= 5 || time >= 19) {
                image = new Image(getClass().getClassLoader().getResourceAsStream("resources/partly_cloudy_nite.png"));
            } else {
                image = new Image(getClass().getClassLoader().getResourceAsStream("resources/partly_cloudy.png"));
            }
        } else if (condition.contains("Drizzle") || condition.contains("Light Rain")) {
            image = new Image(getClass().getClassLoader().getResourceAsStream("resources/drizzle.png"));
        } else if (condition.contains("Thunderstorms and") || condition.contains("Thunderstorms with")) {
            image = new Image(getClass().getClassLoader().getResourceAsStream("resources/thunder_rain.png"));
        } else if (condition.contains("Thunderstorm")) {
            image = new Image(getClass().getClassLoader().getResourceAsStream("resources/storm.png"));
        } else if (condition.contains("Rain")) {
            image = new Image(getClass().getClassLoader().getResourceAsStream("resources/rain.png"));
        } else if (condition.contains("Hail")) {
            image = new Image(getClass().getClassLoader().getResourceAsStream("resources/hail.png"));
        } else if (condition.contains("Snow") || condition.contains("Ice")) {
            image = new Image(getClass().getClassLoader().getResourceAsStream("resources/snow.png"));
        } else if (condition.contains("Mist") || condition.contains("Fog") || condition.contains("Spray")) {
            image = new Image(getClass().getClassLoader().getResourceAsStream("resources/mist.png"));
        } else if (condition.contains("Haze")) {
            image = new Image(getClass().getClassLoader().getResourceAsStream("resources/haze.png"));
        } else if (condition.contains("Sand") || condition.contains("Dust") || condition.contains("Ash") || condition.contains("Smoke") || condition.contains("Squall")) {
            image = new Image(getClass().getClassLoader().getResourceAsStream("resources/wind.png"));
        }
        return image;
    }

    private int getProperDay(String currentDay) {
        switch (currentDay) {
            case "Mon":
                return 0;
            case "Tue":
                return 1;
            case "Wed":
                return 2;
            case "Thu":
                return 3;
            case "Fri":
                return 4;
            case "Sat":
                return 5;
            case "Sun":
                return 6;
        }
        return 1;
    }

    private String generateURL(String place) {
        String newUrl = null;
        switch (place) {
            case "Belgaum":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/India/Belgaum.json";
                break;
            case "San Francisco, CA":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/CA/San_Francisco.json";
                break;
            case "Dubai, UAE":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/UAE/Dubai.json";
                break;
            case "New Delhi":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/India/New_Delhi.json";
                break;
            case "Tokyo, Japan":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/Japan/Tokyo.json";
                break;
            case "San Jose, CA":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/CA/San_Jose.json";
                break;
            case "San Jose, Nicaragua":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/NI/SanJose.json";
                break;
            case "Auckland, NZ":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/NZ/Auckland.json";
                break;
            case "Beijing, China":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/China/Peking.json";
                break;
            case "Johannesburg, SA":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/ZA/Johannesburg.json";
                break;
            case "Kolkata":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/India/Kolkata.json";
                break;
            case "Honolulu, Hawaii":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/HI/Honolulu.json";
                break;
            case "Pune":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/India/Pune.json";
                break;
            case "Hong Kong, China":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/HK/HongKong.json";
                break;
            case "Magadan, Russia":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/RU/Magadan.json";
                break;
            case "Paris, France":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/FR/Paris.json";
                break;
            case "Yakutsk, Russia":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/RU/Yakutsk.json";
                break;
            case "Berlin, Germany":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/Germany/Berlin.json";
                break;
            case "Amsterdam, Netherlands":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/NL/Amsterdam.json";
                break;
            case "Seoul, South Korea":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/SouthKorea/Seoul.json";
                break;
            case "Ottawa, Canada":
                newUrl = "http://api.wunderground.com/api/3bb35abc66336ead/conditions/forecast10day/q/Canada/Ottawa.json";
                break;
        }
        return newUrl;
    }
}
