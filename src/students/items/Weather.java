package students.items;


/**
 * Weather class is responsible for generating different types of weather-related events i.e raining, flood, drought etc. 
 * For each player turn, tick() will be called to decides which type of weather will be in this turn.  
 * There will be 3 events at the moment. They're *raining*, *flood*, *drought*.
 * All events will only increase/decrease the water level in the soil of the field. However, if the water level reaches a certain point, all crops will die.
 * Flood event will increase the water level in the soil by 5
 * Raining event will increase it by 2
 * Drought will decrease it by 5 
 */
public class Weather {
	final double RAINING_CHANCE = 0.4;
	final double FLOOD_CHANCE = 0.01;
	final double DROUGHT_CHANCE =  0.04;
	final int DROUGHT_WATER_LEVEL = -5;
	final int FLOOD_WATER_LEVEL = 5;
	final int RAIN_WATER_LEVEL = 2;
	String weatherStatus;
	public Weather() {
		weatherStatus = "It's a normal day today";
	}

	/**
	 * Handles all types of event. It might be more complicated if we take wind speed, humidity and density of cloud cover as input.
	 * @return the water level in the soil
	 */
	public int event() {
		double event = Math.random();
		int waterLevel = 0; 
		if (event <= FLOOD_CHANCE) {
			waterLevel = FLOOD_WATER_LEVEL;
			weatherStatus = "Unfortunely, we have a flood on our farm";
		} else if (event <= DROUGHT_CHANCE) {
			waterLevel = DROUGHT_WATER_LEVEL;
			weatherStatus = "The soil moisture in our farm is decreasing dramatically due to the drought.";
			
		} else if (event <= RAINING_CHANCE) {
			waterLevel = RAIN_WATER_LEVEL;
			weatherStatus = "Yay! It's raining today.";
		} else {
			weatherStatus = "It's a normal day today";
		}
		return waterLevel;
	}
	public String displayWeatherStatus() {
		return weatherStatus;
	}
}
