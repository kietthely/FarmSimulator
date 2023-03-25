package students.items;
/**
 * Apple class
 * 
 */
public class Apples extends Food {
	/**
	 * The number of Apples objects has been created
	 */
	private static int count;
	/**
	 * Apple
	 * maturationAge = 3, deathAge = 5, value = 3
	 */
	public Apples () {
		super(3,5,3);
		count +=1;
	}
	
	/**
	 * Get the number of apple objects that have been created in this world.
	 * @return count.The number of generation of Apples
	 */
	public static int getGenerationCount() {
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
	/**
	 * Deep copy of the class
	 * The creation of this class is not accounted in the total number of this objects. 
	 */
	@Override
	public Item clone() {
		Apples newApple = new Apples();
		count -= 1;
		newApple.age = age;
		return newApple;
	}

	@Override
	public String name() {
		return "Apples";
	}


	
	
	
}
