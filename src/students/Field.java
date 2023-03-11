package students;

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
				myItemList[height][width].tick();
				// if it's Soil
				if(myItemList[height][width].toString().equals(".") && Math.random() < WEED_SPAWN_CHANCE) {
						myItemList[height][width] = new Weed();
					
				}
				if(myItemList[height][width].died()){
					myItemList[height][width] = new UntilledSoil();
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
		
		for(int i = 1; i <myItemList[0].length +1; i++) {
			environment += i;
		}
		
		for(int height = 0; height < myItemList[0].length; height++) {
			environment += "\n";
			for(int width = 0; width <myItemList[width].length; width++) {
				environment += (height+1) + myItemList[height][width].toString();
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
		for(int height = 0; height < myItemList[0].length; height++) {
			for(int width = 0; width < myItemList.length; width++) {
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
		//TODO: Finish getSummary()
		summary += "Apples: " + String.format("%" + (25-"Apples: ".length()) +"s", "");
		return summary;
	}
}
