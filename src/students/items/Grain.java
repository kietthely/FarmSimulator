package students.items;

/**
 * Grain class
 * 
 *
 */
public class Grain extends Food{
	
	private static int count =0;
	final int cost = 1;
	public Grain () {
		super(2,6,2);
		count+=1;
		
	}

	/**
	 * Get the number of grain objects that have been created in this world.
	 * @return count
	 */
	public int getGenerationCount() {
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
}
