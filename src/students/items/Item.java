package students.items;

/**
 * Base class for all items in the game
 * 
 * 
 */
public abstract class Item {
	
	protected int age, deathAge, maturationAge, monetaryValue;

	/**
	 * Initialize all the item's attributes
	 * @param maturationAge
	 * @param deathAge
	 * @param monetaryValue
	 */
	protected Item( int maturationAge, int deathAge, int monetaryValue ) {
		this.maturationAge = maturationAge;
		this.deathAge = deathAge;
		this.monetaryValue = monetaryValue;
		this.age = 0;
		
	}
	/**
	 * Empty constructor
	 */
	protected Item() {
		
	}
	public void tick() {
		age +=1;
	}
	
	public void setAge(int inputAge) {
		age = inputAge;
	}
	
	public boolean died() {
		if(age > deathAge) {		
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Return monetary value if it's ready to harvest otherwise returns 0
	 * @return monetaryValue
	 */
	public int getValue() {
		if (age >= maturationAge) {
			return monetaryValue;
		} else {
			return 0;
		}
	}
	
	/**
	 * See if two objects have exactly the same information
	 * @param otherItem
	 * @return true if have the same information, false otherwise.
	 */
	public boolean equals(Item otherItem) {
		if(age == otherItem.age && deathAge == otherItem.deathAge && maturationAge == otherItem.maturationAge && monetaryValue == otherItem.monetaryValue) {
			return true;
		} else {
			return false;
		}

	}

	public abstract Item clone();
	
	/**
	 * Return the symbol of the Item
	 */
	public abstract String toString();
	/**
	 * 
	 * @return the name of the Item
	 */
	public abstract String name();
}
