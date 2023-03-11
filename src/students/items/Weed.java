package students.items;
/**
 * Weed class
 */
public class Weed extends Item
{	
	/**
	 * It nearly has infinite age, however, it still can crash on theory since it eventually
	 * will jump back to negative value and then keep incrementing to nearly max positive value
	 */
	public Weed() {
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, -1);
	}
	

	@Override
	public String toString() {
		return "#";
	}
}
