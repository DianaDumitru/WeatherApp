package main;

import java.io.IOException;
import java.util.Scanner;

import org.bitpipeline.lib.owm.OwmClient;
import org.bitpipeline.lib.owm.WeatherData;
import org.bitpipeline.lib.owm.WeatherData.WeatherCondition;
import org.bitpipeline.lib.owm.WeatherStatusResponse;
import org.json.JSONException;

import client.WeatherRecord;
import database.DatabaseHandler;
import database.DatabaseOperations;

public class Main {

	public static void main(String[] args) throws IOException, JSONException {
		
		DatabaseHandler.getInstance();
		
		
		String city = null;
		System.out.print("Enter the city: ");
		Scanner sc = new Scanner(System.in);
		city = sc.nextLine();
		
		
		OwmClient owm = new OwmClient ();
		int id = 1;
		WeatherStatusResponse currentWeather = owm.currentWeatherAtCity(city);
		if (currentWeather.hasWeatherStatus ()) {
			
		    WeatherData weather = currentWeather.getWeatherStatus().get(0);
		    if (weather.getPrecipitation () == Integer.MIN_VALUE) {
		    	WeatherRecord wrecord = new WeatherRecord();
		    	WeatherCondition weatherCondition = weather.getWeatherConditions ().get (0);
		        String description = weatherCondition.getDescription ();
		        
		        wrecord.setCity(city);
		    	wrecord.setHumidity(weather.getHumidity());
		    	wrecord.setPressure(weather.getPressure());
		        wrecord.setDescription(description);
		        wrecord.setTemperature(weather.getTemp());
		        
		        DatabaseOperations.getInstance().insertWeatherRecord(wrecord);
		       
		    } 
		}
		/*WeatherRecord wr = new WeatherRecord("Bucharest", 17, 749.38F, 20, "few clouds");
		DatabaseOperations.getInstance().insertWeatherRecord(wr);*/
		
		sc.close();
	}

}
