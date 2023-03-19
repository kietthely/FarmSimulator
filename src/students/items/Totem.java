package students.items;
/**
 * Base class for all types of Totem, this will be responsible for increment/decrement the water level of the Field, which would be 
 * beneficial for protecting the crops in the Field.
 * All types of Totem cannot die
 */
public abstract class Totem extends Item {
	public Totem() {
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, -1);
	}
	@Override
	public String toString() {
		return "+";
	}
}
