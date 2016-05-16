package client;

import java.sql.Timestamp;

public class WeatherRecord {
	private String city;
	private float temperature;
	private float pressure;
	private float humidity;
	private String description;
	private Timestamp timestamp;

	
	public WeatherRecord(){
		
	}
	
	public WeatherRecord(String city, float temperature, float pressure, float humidity, String description) {
		super();
		this.city = city;
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "WeatherRecord [city=" + city + ", temperature=" + temperature + ", pressure=" + pressure + ", humidity="
				+ humidity + ", description=" + description + "]";
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getPressure() {
		return pressure;
	}
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
		
	

}
