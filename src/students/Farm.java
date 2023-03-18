package students;

import java.util.HashMap;
import java.util.Scanner;

import students.items.Apples;
import students.items.Grain;
import students.items.Item;


/**
 * Farm simulator
 */
public class Farm {
	private int balance, x, y;
	protected Field world;
	private HashMap<String, Integer> shop;
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
		x = fieldHeight;
		y = fieldWidth;
		shop = new HashMap<String, Integer>();
		shop.put("a", 2);
		shop.put("g", 1);
	}

	public void run()
	{	
		String action ="";
		int dx = 0;
		int dy = 0;
		Scanner input = new Scanner(System.in);
		
		while (!action.equals("q")) {
			displayField();
			String[] selection = input.nextLine().split(" ");

			if(selection.length == 3) {
				action = selection[0];
				try {
					dx = Integer.parseInt(selection[1]) -1;
					dy = Integer.parseInt(selection[2]) -1;
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
			if(dx >= x || dy >= y) {
				System.out.println("Invalid coordinates. Please get a bigger farm.");
				continue;
			}
			
			switch(action) {
			case "t":
				world.till(dx, dy);
				break;
			case "h": 
				int value = world.harvest(dx,dy);
				money(value, true);
				break;
			case "p":
				System.out.println("Enter: \n- 'a' to buy an apple for $ \n- 'g' to buy grain for ");
				String food = input.nextLine().trim();
				plantAction(food, dx, dy);
				break;
			case "s":
				System.out.println(world.getSummary());
				continue;
			case "w":
				break;
			case "q":
				break;
			default:
				System.out.println("Invalid inputs!");
			}
			world.tick();
		}

		input.close();
	}

	/**
	 * Handle plant() function
	 * @param food to plant
	 * @param xAxis x-coordinate
	 * @param yAxis y-coordinate
	 */
	public void plantAction(String food, int xAxis, int yAxis) {
		switch(food) {
		case "a":
			if(isSufficient(2)) {
				money(2, false);
				world.plant(xAxis, yAxis, new Apples());
			} else {
				System.out.println("Insufficient balance");
			}
			break;
		case "g":
			if(isSufficient(2)) {
				money(1, false);
				world.plant(xAxis, yAxis, new Grain());
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
		System.out.println("Balance: "+getBalance());
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
				+ "\n s: field summary"
				+ "\n w: wait"
				+ "\n q: quit";
		return options;
	}
}
