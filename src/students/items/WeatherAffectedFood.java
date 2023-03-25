package students.items;

/**
 * WeatherAffectedFood, this Food will automatically appear when there's a flood/drought on our farm.
 * 
 */
public class WeatherAffectedFood extends Item {
	/**
	 * It nearly has infinite age, however, it still can crash on theory since it eventually
	 * will jump back to negative value and then keep incrementing to nearly max positive value
	 * i.e the tick() method needs to be invoked 2147483647 times to get crashed.
	 */
	public WeatherAffectedFood() {
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, -1);
	}

	
	
	@Override
	public Item clone() {
		WeatherAffectedFood newFood = new WeatherAffectedFood();
		newFood.age = age;
		return newFood;
	}



	@Override
	public String toString() {
		return "!";
	}



	@Override
	public String name() {
		return "Weather-Affected Crop";
	}
	
	
}
