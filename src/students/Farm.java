package students;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import students.items.*;



/**
 * Farm simulator
 * A player will have lots of items in their backpack.
 */
public class Farm {
	/**
	 * Player's bank
	 */
	private int balance;
	/**
	 * Farm's width
	 */
	private int x;
	/**
	 * Farm's height
	 */
	private int y;
	/**
	 * Farm's environment object
	 */
	protected Field world;
	/**
	 * Food store
	 */
	private HashMap<String, Integer> shop;
	/**
	 * Player inventory
	 */
	private ArrayList<Item> inventory;
	/**
	 * Weather
	 */
	private Weather weather;
	// hashmap shop to get a list of Food items.
	/**
	 * Initialize shop and the world
	 * @param fieldHeight
	 * @param fieldWidth
	 * @param startingFunds
	 */
	public Farm(int fieldHeight, int fieldWidth, int startingFunds)
	{
		world = new Field(fieldHeight, fieldWidth);
		balance = startingFunds;
		y = fieldHeight;
		x = fieldWidth;
		shop = new HashMap<String, Integer>();
		shop.put("a", 2);
		shop.put("g", 1);
		shop.put("p", 7);
		shop.put("w", 2);
		inventory = new ArrayList<Item>();
		weather = new Weather();
	}
	/**
	 * Run the farm simulator
	 */
	public void run()
	{	

		System.out.println(String.format("%s", "*".repeat(30)) + "\nEnjoy the game! :) \n" + String.format("%s", "*".repeat(30)));
		String action ="";
		int height = 0;
		int width = 0;
		Scanner input = new Scanner(System.in);
		while (!action.equals("q")) { 
			System.out.println("\n" + String.format("%s", "*".repeat(70)));
			System.out.println(String.format("%s", "*".repeat(70)));
			
			receiver(weather.event()); // receive events from the Weather
			if (world.isFieldDestroyed()) {
				 
				System.out.println("\n GAME OVER! \n"
						+ " You lost because the farm was destroyed due to " + weather.occurredDisaster()+". \n");
				System.out.println("\n" + String.format("%s", "*".repeat(70)));
				System.out.println(String.format("%s", "*".repeat(70)));
				break;
			}
			System.out.println(weather.weatherStatus());
			
			displayField();
			
			
			String[] selection = input.nextLine().split(" ");

			if(selection.length == 3) {
				action = selection[0];
				try {
					height = Integer.parseInt(selection[1]) -1;
					width = Integer.parseInt(selection[2]) -1;
				} catch (NumberFormatException ex) {
					System.out.println("Your location inputs are in wrong format ");
					continue;
				}
				} else if(selection.length == 1) {
				action = selection[0];
			} else {
				System.out.println("Invalid inputs!");
				continue;
			}
			if(width >= x || height >= y) {
				System.out.println("Invalid coordinates. Please get a bigger farm.");
				continue;
			}
			
			switch(action) {
			case "t": // till
				world.till(height, width);
				break;
			case "h": // harvest
				int value = world.harvest(height,width);
				money(value, true);
				break;
			case "p": // plant
				listOfFood();
				String food = input.nextLine().trim();
				plantAction(food, height, width);
				break;
			case "b": // buy items - i.e WeatherTotem
				listOfTotems();
				String totem = input.nextLine().trim();
				buyTotem(totem);
				break;
			case "u": // use items
				listOfUseActions();
				try {
					int itemPosition = Integer.parseInt(input.nextLine());
					useItem(itemPosition);
				} catch (NumberFormatException ex) {
					// Lose 1 turn. You looking for a non-existent item for a whole day.
					System.out.println("Non-existent item!");
					continue;
				}

				break;
			case "s": // summarize
				System.out.println(world.getSummary());
				continue;
			case "w": // wait
				break;
			case "q": // quit
				break;

			default:
				System.out.println("Invalid action!");
			}
			world.tick();
		}

		input.close();
	}
	/**
	 * Receive the event and update the water level in the soil.
	 * @param moistureLevel in the soil
	 */
	public void receiver(int moistureLevel) {
		world.updateWaterLevel(moistureLevel);
	}
	/**
	 * Use an item depending on the position of the item in the inventory
	 * @param position of the item
	 */
	public void useItem(int position) {


		try {
			if (inventory.get(position -1) instanceof Totem) {
				inventory.remove(position -1);
				world.setWaterLevelToNormal();
			}
		} catch (IndexOutOfBoundsException e){
			System.out.println("You don't have this item!");
		}
		

	} 
	/**
	 * List all of use items actions
	 */
	public void listOfUseActions() {
		for(int i = 0; i< inventory.size(); i++) {
			System.out.println("Enter: \n- '" + (i+1) + "' to use a(an) " + inventory.get(i).name());
			
		}
		
	}
	/**
	 * Buy a totem
	 * @param totem item
	 */
	public void buyTotem(String totem) {
		switch(totem) {
		case "w":
			inventory.add(new WeatherTotem());
			break;
		default:
			System.out.println("We don't have this item in the shop!");
		}
	}
	
	/**
	 * Display a list of food
	 */
	public void listOfFood() {
		System.out.println("Enter: \n- 'a' to buy an apple for $" + shop.get("a") + 
				"\n- 'g' to buy grain for $" + shop.get("g") + 
				"\n- 'p' to buy a peach for $" + shop.get("p"));
	}
	/**
	 * Display a list of totems
	 */
	public void listOfTotems () {
		System.out.println("Enter: \n- 'w' to buy a weather totem for $" + shop.get("w"));

	}

	/**
	 * Handle plant() function
	 * @param food to plant
	 * @param height y-coordinate
	 * @param width x-coordinate
	 */
	public void plantAction(String food, int height, int width) {
		switch(food) {
		case "a":
			if(isSufficient(2)) {
				money(2, false);
				world.plant(height, width, new Apples());
			} else {
				System.out.println("Insufficient balance");
			}
			break;
		case "g":
			if(isSufficient(1)) {
				money(1, false);
				world.plant(height, width, new Grain());
			} else {
				System.out.println("Insufficient balance");
			}
			break;
		case "p":
			if(isSufficient(7)) {
				money(7, false);
				world.plant(height, width, new Peach());
			} else {
				System.out.println("Insufficient balance");
			}
			break;
		default:
			System.out.println("Invalid item to plant");
		}
		
	}
	/**
	 * Display the field
	 */
	public void displayField() {
		System.out.println("Balance: "+getBalance() + String.format("%20s","Water Level: " + world.getWaterLevel()));
		String backpack = "Your Backpack: ";
		String items = "";
		boolean hasItem = false;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) != null) {
				hasItem = true;
				items += "|  " + inventory.get(i).name() + "  |";
			}
		}
		if(hasItem)
			System.out.println(backpack + items);
		else
			System.out.println(backpack + "No items");
		System.out.println(world.toString());
		System.out.println(availableActions());
	}
	/**
	 * Update your balance
	 * @param income: cost/income when we buy or sell an item
	 * @param isSelling: true if we're selling, false otherwise.
	 */
	public void money(int income, boolean isSelling ) {
		if(isSelling) {
			balance += income;
		} else {
			balance -= income;
		}
	}
	/**
	 * Check if the user can buy something
	 * @param cost to buy
	 * @return true if sufficient, false otherwise.
	 */
	public boolean isSufficient(int cost) {
		if (balance - cost >= 0) {
			return true;
		} else {
			return false;
		}
	}
	public int getBalance() {
		return balance;
	}
	/**
	 * Display all the available options
	 * @return a string that represents all the options that user can do
	 */
	public String availableActions() {
		String options;
		options = "Enter your next action: "
				+ "\n t x y: till"
				+ "\n h x y: harvest"
				+ "\n p x y: plant"
				+ "\n b: buy a totem";
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) instanceof Totem) {
				options += "\n u: use a totem";
				break;
			}
		}
		
		options += "\n s: field summary"
				+ "\n w: wait"
				+ "\n q: quit";
		return options;
	}
}
