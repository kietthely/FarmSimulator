package students;

import java.util.HashMap;

import students.items.*;
/**
 * The world environment
 * waterLevel determines the status of the field. If waterLevel is > 10, there will be a great chance of having *flood* event.
 * If waterLevel is < -10, there will be a great chance of having *drought* event.
 * Normally the waterLevel will be decreased by 1 for each turn. Its value would only increase when there's *raining* event going on.
 */
public class Field {
	private final double WEED_SPAWN_CHANCE = 0.2;
	private int waterLevel;
	private Item[][] myItemList;
	/**
	 * Initialize the environment
	 * @param height
	 * @param width
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
	 * The world is running
	 */
	public void tick() {
		// {(1,2,3),(1,2,3)}
		for(int height =0; height < myItemList.length; height++) {
			for(int width = 0; width < myItemList[height].length; width++) {
				Item currentItem = myItemList[height][width];
				currentItem.tick();
				// TODO waterLevel logic
				losingHumidity();

				// if it's Soil, turn into Weed
				if(currentItem.toString().equals(".") && Math.random() <= WEED_SPAWN_CHANCE) {
					plant(height, width, new Weed());

				}
				// if crops die, turn into UntilledSoil
				if(currentItem.died()){
					plant(height, width, new UntilledSoil());
				}
			}
		}
	}
	/**
	 * Increase water level
	 * @param water
	 */
	public void increaseWaterLevelBy(int water) {
		waterLevel += water;
	}
	
	public void losingHumidity() {
		waterLevel -= 1;
	}
	/**
	 * Print the world
	 */
	@Override
	public String toString() {
		String environment =" ";
		
		for(int width = 0; width < myItemList[0].length; width++ ) {
			environment += String.format("%1$6s", width+1);
			
		}

		
		for(int height = 0; height < myItemList.length; height++) {
			environment += "\n" + (height+1);
			for(int width = 0; width <myItemList[height].length; width++) {
				String symbol = myItemList[height][width].toString();


				environment += String.format("%1$6s", symbol);

				
			}
		}
		return environment;
	}

	/**
	 * Turn Item at the location into Soil
	 * @param height
	 * @param width
	 */
	public void till(int height, int width) {
		myItemList[height][width] = new Soil();
		
	}
	public int harvest(int height, int width) {
		int value = myItemList[height][width].getValue();
		myItemList[height][width] = new Soil();
		return value;
	}
	/**
	 * Return a copy of the item at the given location
	 * @param height
	 * @param width
	 * @return
	 */
	public Item get(int height, int width) {
		return myItemList[height][width].clone();
	}
	
	/**
	 * Plant stuff in this location
	 * @param height
	 * @param width
	 * @param item
	 */
	public void plant(int height, int width, Item item) {
		if(!myItemList[height][width].toString().equals("/")) {
			myItemList[height][width] = item;	
		} else {
			System.out.println("Cannot be planted into untilled soil");
		}
	}
	/**
	 * 
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
	 * @return
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
