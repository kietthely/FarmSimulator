package students.items;

public class Peach extends Food{
	private static int count;
	public Peach () {
		super(10,25, 12);
		count +=1;
	}
	
	/**
	 * Get the number of apple objects that have been created in this world.
	 * @return count
	 */
	public static int getGenerationCount() {
		return count;
	}
	@Override
	public String toString() {
		if(age < maturationAge) {
			return "p";
		} else {
			return "P";
		}

	}
	/**
	 * Deep copy of the class
	 * The creation of this class is not accounted in the total number of this objects. 
	 */
	@Override
	public Item clone() {
		Peach newPeach = new Peach();
		count -= 1;
		newPeach.age = age;
		return newPeach;
	}

	@Override
	public String name() {
		return "Peach";
	}
}
