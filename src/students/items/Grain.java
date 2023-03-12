package students.items;

/**
 * Grain class
 * 
 *
 */
public class Grain extends Food{
	
	private static int count =0;
	final static int COST = 1;
	public Grain () {
		super(2,6,2);
		count+=1;
		
	}

	/**
	 * Get the number of grain objects that have been created in this world.
	 * @return count
	 */
	public static int getGenerationCount() {
		return count;
	}
	@Override
	public int getCost() {
		if(age > maturationAge) {
			return COST;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		if(age < maturationAge) {
			return "g";
		} else {
			return "G";
		}

	}

	@Override
	public Item clone() {
		Grain newGrain = new Grain();
		count -=1;
		newGrain.age = age;
		return newGrain;
	}

	@Override
	public String name() {
		return "Grain";
	}
	
	
}
