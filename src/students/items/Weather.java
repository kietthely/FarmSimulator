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
	/**
	 * The probability of raining in the world.
	 */
	final double RAINING_CHANCE = 0.4;
	/**
	 * The probability of having flood in the world.
	 */
	final double FLOOD_CHANCE = 0.01;
	/**
	 * The probability of having drought in the world.
	 */
	final double DROUGHT_CHANCE =  0.04;
	/**
	 * The water level after the drought happened
	 */
	final int DROUGHT_WATER_LEVEL = -10;
	/**
	 * The water level after the flood happened
	 */
	final int FLOOD_WATER_LEVEL = 10;
	/**
	 * The water level after raining
	 */
	final int RAIN_WATER_LEVEL = 2;
	/**
	 * The current weather status
	 */
	String weatherStatus;
	/**
	 * The disaster that happened in the world
	 */
	String disaster;
	/**
	 * Weather events handler
	 */
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
			weatherStatus = "Oh no!, we have a flood on our farm";
			disaster = "flood";
		} else if (event <= DROUGHT_CHANCE) {
			waterLevel = DROUGHT_WATER_LEVEL;
			weatherStatus = "Noo!, there's a drought happening on our farm";
			disaster = "drought";
		} else if (event <= RAINING_CHANCE) {
			waterLevel = RAIN_WATER_LEVEL;
			weatherStatus = "Yay! It's raining today.";
			disaster = "heavy rain";
		} else {
			disaster = "inappropriate water level in soil";
			weatherStatus = "It's a normal day today";
		}
		return waterLevel;
	}
	/**
	 * Get the details on the disaster that is occurring
	 * @return disaster. String to display the natural disaster.
	 */
	public String occurredDisaster() {
		return disaster;
	}
	/**
	 * Get the weather status today.
	 * @return weatherStatus. String to display the weather
	 */
	public String weatherStatus() {
		return weatherStatus;
	}
}
