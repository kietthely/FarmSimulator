package students;

import java.util.Scanner;
/**
 * Farm simulator
 */
public class Farm {
	private int balance;
	protected Field world;
	
	public Farm(int fieldWidth, int fieldHeight, int startingFunds)
	{
		world = new Field(fieldWidth, fieldHeight);
		balance = startingFunds;
	}
	
	public void run()
	{
		String action;
		int x = 0;
		int y = 0;
		while(true) {
			Scanner input = new Scanner(System.in);
			String[] selection = input.nextLine().split(" ");
			if(selection.length == 3) {
				action = selection[0];
				x = Integer.parseInt(selection[1]);
				y = Integer.parseInt(selection[2]);
			} else {
				action = selection[0];
			}
			switch(action) {
			case "t":
				world.till(x, y);;
				
				break;
			case "h":
				world.get(x, y);
				
				break;
			case "p":
				
				break;
			case "s":
				
				break;
			case "w":
				
				break;
			case "q":
				
				break;
			}
			
			world.tick();
		}
	}
	public int getBalance() {
		return balance;
	}
	
}
