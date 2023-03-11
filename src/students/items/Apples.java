package students.items;
/**
 * Apple class
 * 
 */
public class Apples extends Food {
	private static int count;
	final int cost =2;
	public Apples () {
		super(3,5,3);
		count +=1;
	}
	
	/**
	 * Get the number of apple objects that have been created in this world.
	 * @return count
	 */
	public int getGenerationCount() {
		return count;
	}
	@Override
	public String toString() {
		if(age < maturationAge) {
			return "a";
		} else {
			return "A";
		}

	}
}
