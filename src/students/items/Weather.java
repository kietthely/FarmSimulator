package students.items;


/**
 * Weather class is responsible for generating different types of weather-related events i.e raining, flood, drought etc. 
 * For each player turn, tick() will be called to decides which type of weather will be in this turn.  
 * There will be 3 events at the moment. They're *raining*, *flood*, *drought*.
 * Raining event will only increase the water level of the field. However, if the water level reaches a certain point, all crops will die.
 * Flood event will 
 */
public class Weather {
	final double RAINING_CHANCE = 0.4;
	final double FLOOD_CHANCE = 0.01;
	final double DROUGHT_CHANCE =  0.04;
	public Weather() {
		
	}
	public static void tick() {
		
	}
	public void eventHandler() {
		
	}
}
