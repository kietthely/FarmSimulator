package students.items;

/**
 * Grain class
 * 
 *
 */
public class Grain extends Food{
	/**
	 * The number of Grain objects has been created
	 */
	private static int count =0;
	/**
	 * Grain
	 * maturationAge = 2, deathAge = 6, value = 2
	 */
	public Grain () {
		super(2,6,2);
		count+=1;
		
	}

	/**
	 * Get the number of grain objects that have been created in this world.
	 * @return count. The number of Apples objects has been created
	 */
	public static int getGenerationCount() {
		return count;
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
