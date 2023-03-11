package students.items;

/**
 * Food class
 * 
 */
public abstract class Food extends Item {
	public Food(int maturationAge, int deathAge, int monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
	}
	
	/**
	 * Default constructor
	 */
	public Food() {
		
	}
	@Override
	public String toString() {
		return "Food";
	}
	public abstract int getCost();
	
}
