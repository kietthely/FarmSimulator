package students.items;
/**
 * Soil class
 */
public class Soil extends Item{
	/**
	 * Soil nearly has infinite age, however, it still can crash on theory since it eventually
	 * will jump back to negative value and then keep incrementing to nearly max positive value
	 * i.e the tick() method needs to be invoked 2147483647 times to get crashed.
	 */
	public Soil() {
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
	}
	
	@Override
	public Item clone() {
		Soil newSoil = new Soil();
		newSoil.age = age;
		return newSoil;
	}

	@Override
	public String toString() {
		return ".";
	}

	@Override
	public String name() {
		return "Soil";
	}
	
}
