package students;

import java.util.HashMap;

import students.items.*;
/**
 * The world environment
 * waterLevel determines the status of the field. If waterLevel is > 20, there will be a great chance of having *flood* event.
 * If waterLevel is < -20, there will be a great chance of having *drought* event. These events would make the crops die.
 * Normally the waterLevel will be decreased by 1 for each turn. Its value would only increase when there's *raining* event going on.
 */
public class Field {
	/**
	 * The probability of weed would spawn at a given location.
	 */
	private final double WEED_SPAWN_CHANCE = 0.2;
	/**
	 * Maximum water level for the farm
	 */
	private final int maxWaterLevelFarm = 25;
	/**
	 * Minimum water level for the farm
	 */
	private final int minWaterLevelFarm = -25;
	/**
	 * Maximum water level for crops to grow
	 */
	private final int maxWaterLevelForCrops = 10;
	/**
	 * Minimum water level for crops to grow
	 */
	private final int minWaterLevelForCrops = -10;
	/**
	 * Water level in soil
	 */
	private int waterLevel;
	/**
	 * A list of items in the field.
	 */
	private Item[][] myItemList;
	/**
	 * Initialize the environment
	 * @param height y-coordinate of the field.
	 * @param width x-coordinate of the field.
	 */
	public Field(int height, int width)
	{
		myItemList = new Item[height][width];
		for(int i =0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				myItemList[i][j] = new Soil();
			}
		}
		waterLevel = 0;
	}
	/**
	 * Run the world
	 */
	public void tick() {
		// {(1,2,3),(1,2,3)}
		
		for(int height =0; height < myItemList.length; height++) {
			for(int width = 0; width < myItemList[height].length; width++) {
				Item currentItem = myItemList[height][width];
				currentItem.tick();

				// if it's Soil, turn into Weed
				if(currentItem.toString().equals(".") && Math.random() <= WEED_SPAWN_CHANCE) {
					plant(height, width, new Weed());

				}
				// if crops die, turn into UntilledSoil
				if(currentItem.died() ){
					plant(height, width, new UntilledSoil());
				} 
				if (waterLevel > maxWaterLevelForCrops || waterLevel < minWaterLevelForCrops) {
					plant(height, width, new WeatherAffectedFood());
					
				}
				
			}
		}

		losingHumidity();
	}
	/**
	 * Increase/Decrease water level
	 * @param water level that is going to be increased/decreased
	 */
	public void updateWaterLevel(int water) {
		waterLevel += water;
	}
	
	/**
	 * Set the water level in the soil back to the normal state.
	 */
	public void setWaterLevelToNormal() {
		this.waterLevel = 1;
	}
	/**
	 * Losing 1 water level each day
	 */
	public void losingHumidity() {
		waterLevel -= 1;
	}
	/**
	 * Return water level in the soil
	 * @return waterLevel
	 */
	public int getWaterLevel() {
		return waterLevel;
	}
	/**
	 * Print the world
	 */
	@Override
	public String toString() {
	    int width = myItemList[0].length;
	    int height = myItemList.length;
	    String environment = " ";

	    // Add x-coordinates to top row
	    for (int x = 0; x < width; x++) {
	        environment += String.format("%1$10s", x + 1);
	    }

	    // Add y-coordinates and elements to remaining rows
	    for (int y = 0; y < height; y++) {
	        environment += "\n" + String.format("%1$-10s", y + 1);
	        for (int x = 0; x < width; x++) {
	            String symbol = myItemList[y][x].toString();
	            environment += String.format("%1$-10s", symbol);
	        }
	    }

	    return environment;
	}



	/**
	 * Check if the field is destroyed due to the water level in the soil.
	 * @return true if you no longer can plant stuff in this field. false otherwise.
	 */
	public boolean isFieldDestroyed() {
		boolean result = false;
		if(waterLevel < minWaterLevelFarm || waterLevel > maxWaterLevelFarm) {
			result = true;
		}
		return result;
	}
	/**
	 * Turn Item at the location into Soil
	 * @param height y-coordinate of the crop
	 * @param width x-coordinate of the crop
	 */
	public void till(int height, int width) {
		myItemList[height][width] = new Soil();
	}
	/**
	 * Harvest the crop at this location
	 * @param height y-coordinate of the crop
	 * @param width x-coordinate of the crop
	 * @return the monetary value of the crop. 0 if the crop is not ready
	 */
	public int harvest(int height, int width) {
		int value = myItemList[height][width].getValue();
		myItemList[height][width] = new Soil();
		return value;
	}
	/**
	 * Return a copy of the item at the given location
	 * @param height y-coordinate of the crop
	 * @param width x-coordinate of the crop
	 * @return a copy of the item.
	 */
	public Item get(int height, int width) {
		return myItemList[height][width].clone();
	}
	
	/**
	 * Plant crops in this area
	 * @param height y-coordinate of the crop
	 * @param width x-coordinate of the crop
	 * @param item crop to be planted at this location
	 */
	public void plant(int height, int width, Item item) {
		if(myItemList[height][width].toString().equals(".")) { // only plant on soil
			myItemList[height][width] = item;	
		} else {
			System.out.println("Please till or harvest this area first!");
		}
	}
	/**
	 * Get the total monetary value of the farm
	 * @return totalMonetaryValue
	 */
	public int getValue() {
		int totalMonetaryValue = 0;
		for(int height = 0; height < myItemList.length; height++) {
			for(int width = 0; width < myItemList[height].length; width++) {
				totalMonetaryValue += myItemList[height][width].getValue();
			}
		}
		return totalMonetaryValue;
	}
	
	/**
	 * Print out the quantities and the overall of all items in the world 
	 * @return summary. String that summarizes all the details of the field.
	 */
	public String getSummary() {
		String summary ="";
		int totalCost =0;
		// HashMap - name of the class is the key, the number of the occurrences of the class is the value
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// initialize my map
		map.put("Apples", 0);
		map.put("Grain", 0);
		map.put("Soil", 0);
		map.put("Untilled Soil", 0);
		map.put("Weed", 0);
		
		
		// loop through the entire array to count different items.
		for(int height = 0; height < myItemList.length; height++) {
			for(int width = 0; width < myItemList[height].length; width++) {
				Item currentItem = myItemList[height][width];
				// update the map
				if(map.containsKey(currentItem.name())){
					map.put(currentItem.name(), map.get(currentItem.name()) +1);
				}
				totalCost += currentItem.getValue();
			}
		}
		
		// print out the quantities
		for(String i : map.keySet()) {
			summary += i + String.format("%" +  (25-i.length()) + "s", map.get(i)) + "\n";
		}
		summary += "For a total of " + String.format("%" + (25- "For a total of ".length()) + "s", totalCost) + "\n";
		summary += "Total apples created: " + String.format("%" + (25- "Total apples created: ".length()) + "s", Apples.getGenerationCount()) + "\n";
		summary += "Total grain created: " + String.format("%" + (25- "Total grain created: ".length()) + "s", Grain.getGenerationCount()) + "\n";
		summary += "Total peach created: " + String.format("%" + (25- "Total peach created: ".length()) + "s", Peach.getGenerationCount()) + "\n";
		return summary;
	}
}
