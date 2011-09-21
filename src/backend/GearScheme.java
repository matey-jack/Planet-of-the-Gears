package backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Master class of the backend which contains all the data about a gear scheme:
 * which gears there are and how they are connected (that's implicit in the 
 * structure), what size the gears are and which are the transmission paths.
 * 
 * All this data can be used to calculate the gear speeds for each transmission path.
 *
 */
public class GearScheme
{
	private static GearScheme the_scheme;
	public static GearScheme the_scheme()
	{
		if (the_scheme==null)
		{
			the_scheme = new GearScheme();
			the_scheme.add_planet_element();
			the_scheme.add_planet_element();
			the_scheme.add_path();
			the_scheme.add_path();
		}
		return the_scheme;
	}

	GearScheme()
	{
		planet_elements = new ArrayList<PlanetElement>();
		paths = new ArrayList<TransmissionPath>();
	}
	
	// state
	public List<PlanetElement> planet_elements;
	public List<PlanetElement> deleted_planet_elements;
	public List<TransmissionPath> paths;
	public List<TransmissionPath> deleted_paths;

	public List<Shaft> all_shafts()
	{
		List<Shaft> result = new ArrayList<Shaft>();
		result.add(new Shaft(ElementType.CARRIER, 0));
		for (int i = 0; i < planet_elements.size(); i++ )
		{
			PlanetElement pi = planet_elements.get(i);
			if (pi.has_ring)
				result.add(new Shaft(ElementType.RING, i));
			if (pi.has_sun)
				result.add(new Shaft(ElementType.SUN, i));
		}
		return result;
	}

	public static PlanetElement get_element(int i)
	{
		return the_scheme().planet_elements.get(i);
	}

	public static Shaft get_shaft(int i)
	{
		return the_scheme().all_shafts().get(i);
	}

	public static TransmissionPath get_path(int i)
	{
		return the_scheme().paths.get(i);
	}
	
	public void add_planet_element()
	{
		planet_elements.add(new PlanetElement(0.5));		
	}
	
	public void add_path()
	{
		paths.add(new TransmissionPath());
	}
}
