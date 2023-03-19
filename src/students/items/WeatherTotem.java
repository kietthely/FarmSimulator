package students.items;
/**
 * Weather Totem. It's responsible for setting the water level of the field back to normal state.
 *
 */
public class WeatherTotem extends Totem {
	public WeatherTotem() {
		super();
	}
	@Override
	public Item clone() {
		WeatherTotem newWeatherTotem = new WeatherTotem();
		newWeatherTotem.age = age;
		return newWeatherTotem;
	}
	
	@Override
	public String name() {
		return "Weather Totem";
	}

}
