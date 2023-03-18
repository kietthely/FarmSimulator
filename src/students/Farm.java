package students;

import java.util.Scanner;

import students.items.Apples;
import students.items.Grain;


/**
 * Farm simulator
 */
public class Farm {
	private int balance, x, y;
	protected Field world;
	// hashmap shop to get a list of Food items.
	public Farm(int fieldHeight, int fieldWidth, int startingFunds)
	{
		world = new Field(fieldHeight, fieldWidth);
		balance = startingFunds;
		x = fieldHeight;
		y = fieldWidth;
	}
	
	public void run()
	{	
		String action ="";
		int dx = 0;
		int dy = 0;

		// print the field and the options
		Scanner input = new Scanner(System.in);
		while (!action.equals("q")) {

			System.out.println("Balance: "+getBalance());
			System.out.println(world.toString());
			System.out.println(availableActions());
			String[] selection = input.nextLine().split(" ");
			if(selection.length == 3) {
				action = selection[0];
				dx = Integer.parseInt(selection[1]) -1;
				dy = Integer.parseInt(selection[2]) -1;
			} else {
				action = selection[0];
			}
			
			if(dx >= x || dy >= y) {
				System.out.println("Invalid coordinates. Please get a bigger farm.");
				continue;
			}
			switch(action) {
			case "t": // till
				world.till(x, y);
				break;
			case "h": // harvest
				world.get(dx, dy);
				break;
			case "p": // plant
				System.out.println("Enter: \n- 'a' to buy an apple for $ \n- 'g' to buy grain for ");
				String food = input.nextLine().trim();
				switch(food) {
				case "a":
					if(isSufficient(2)) {
						money(2, false);
						world.plant(dx, dy, new Apples());
					} else {
						System.out.println("Insufficient balance");
					}
					break;
				case "g":
					if(isSufficient(2)) {
						money(1, false);
						world.plant(dx, dy, new Grain());
					} else {
						System.out.println("Insufficient balance");
					}
					break;
				default:
					System.out.println("Invalid item to plant");
				}
				break;
			case "s":
				// summary
				// print balance
				world.getSummary();
				break;
			case "w":
				break;

			case "q":
				break;
			default:
					System.out.println("Wrong input");
			}
			world.tick();
		}

		input.close();

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
