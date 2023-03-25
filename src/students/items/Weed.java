package students.items;
/**
 * Weed class
 */
public class Weed extends Item
{	
	/**
	 * Weed nearly has infinite age, however, it still can crash on theory since it eventually
	 * will jump back to negative value and then keep incrementing to nearly max positive value
	 * i.e the tick() method needs to be invoked 2147483647 times to get crashed.
	 */
	public Weed() {
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, -1);
	}
	
	
	@Override
	public Item clone() {
		Weed newWeed = new Weed();
		newWeed.age = age;
		return newWeed;
	}


	@Override
	public String toString() {
		return "#";
	}


	@Override
	public String name() {
		return "Weed";
	}
	
}
