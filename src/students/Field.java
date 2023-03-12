package students;

import java.util.HashMap;

import students.items.*;
/**
 * The world environment
 *
 */
public class Field {
	private final double WEED_SPAWN_CHANCE = 0.2;

	private Item[][] myItemList;
	/**
	 * Initialize the environment
	 * @param height
	 * @param width
	 */
	public Field(int height, int width)
	{
		myItemList = new Item[height][width];
		// 3x2
		// 
		for(int i =0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				myItemList[i][j] = new Soil();
			}
		}
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
				// if it's Soil
				if(currentItem.toString().equals(".") && Math.random() < WEED_SPAWN_CHANCE) {
					currentItem = new Weed();
					
				}
				if(currentItem.died()){
					currentItem = new UntilledSoil();
				}
			}
		}
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
		myItemList[height][width] = item;
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
		// HashMap - name of the class is the key, the number of the occurrence of the class is the value
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
				// retrieve the cost of the food item
				if(currentItem instanceof Food) {
					totalCost += ((Food) currentItem).getCost();
				}
			}
		}
		
		// print out the quantities
		for(String i : map.keySet()) {
			summary += i + String.format("%" +  (25-i.length()) + "s", map.get(i)) + "\n";
		}
		summary += "For a total of " + String.format("%" + (25- "For a total of ".length()) + "s", totalCost) + "\n";
		summary += "Total apples created: " + String.format("%" + (25- "Total apples created: ".length()) + "s", Apples.getGenerationCount()) + "\n";
		summary += "Total grain created: " + String.format("%" + (25- "Total grain created: ".length()) + "s", Grain.getGenerationCount()) + "\n";
		return summary;
	}
}
