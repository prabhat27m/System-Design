package LLD.DesignPatterns.Observer;


import java.util.ArrayList;
import java.util.List;

interface WeatherObserver{
     void update(float temperature);
}

class PhoneDisplay implements WeatherObserver {
    @Override
    public void update(float temperature) {
        System.out.println("Phone Display: Temperature is " + temperature + "°C");
    }
}

class WebsiteDisplay implements WeatherObserver {
    @Override
    public void update(float temperature) {
        System.out.println("Website Display: Temperature updated to " + temperature + "°C");
    }
}

public class WeatherStation {
    private float temperature;
    private List<WeatherObserver> observers = new ArrayList<WeatherObserver>();

    public void addObserver(WeatherObserver observer){
        observers.add(observer);
    }

    public void removeObserver(WeatherObserver observer){
        observers.remove(observer);
    }

    public void setTemperature(float temperature){
        this.temperature = temperature;

        notifyObserver();
    }

    public void notifyObserver(){
        for( WeatherObserver observer : observers){
            observer.update(temperature);
        }
    }

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        // Create observers
        PhoneDisplay phoneDisplay = new PhoneDisplay();
        WebsiteDisplay websiteDisplay = new WebsiteDisplay();

        // Register observers
        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(websiteDisplay);

        // Update temperature - all observers will be notified
        weatherStation.setTemperature(25.5f);

        // Remove an observer
        weatherStation.removeObserver(websiteDisplay);

        // Update again - only phone display will be notified
        weatherStation.setTemperature(26.5f);
    }

}
