package backend;

import java.util.List;

public class TransmissionPath
{
	public Shaft input;
	public Shaft output;
	public Shaft ground;
	/*
	// design alternative
	public EnumMap<ShaftType, Shaft> connections;
	Shaft input() { return connections.get(ShaftType.INPUT); }
	(...)
	// connections[FREE] is not used
	shaft_type get_shaft_type(Shaft s)
	{
		for (Map.Entry<> e : connections)
			if (e.getValue().equals(s)
				return e.getKey();
	}
	void set_shaft_type(Shaft s, ShaftType t)
	{ connections.put(t, s); }
	 */
	
	public void invariant()
	{
		assert ! input.equals(ground);
		assert ! ground.equals(output);
		assert ! output.equals(input); 
		// if two shafts had the same assignment, then we couldn't use DnD to separate them!
	}
	
	public TransmissionPath()
	{
		input = GearScheme.get_shaft(0);
		output = GearScheme.get_shaft(1);
		ground = GearScheme.get_shaft(2);
	}

	public List<Double> shaft_speeds;
	// This list is parallel to GearScheme.all_shafts
	
	public Double shaft_speed(Shaft x)
	// Calculates the speed for any output or free-wheeling shaft.
	// Automagically yields 1.0 for input and 0.0 for ground.
	{
		if (ground.type == ElementType.CARRIER)
			return x.ratio_to(input);
		else
			return (x.ratio_to(ground) - 1) / (input.ratio_to(ground) - 1);
	}
	
	public void update_speeds()
	{
		shaft_speeds.clear();
		for (Shaft s : GearScheme.the_scheme().all_shafts())
			shaft_speeds.add(shaft_speed(s));
	}
	
	public ShaftType get_shaft_type(Shaft x)
	{
		if (x.equals(input)) return ShaftType.INPUT;
		else if (x.equals(output)) return ShaftType.OUTPUT;
		else if (x.equals(ground)) return ShaftType.GROUND;
		else return ShaftType.FREE;
	}

	private void set_shaft_type(Shaft x, ShaftType t)
	// private to protect invariant
	{
		switch (t)
		{
		case INPUT: input = x; break;
		case OUTPUT: output = x; break;
		case GROUND: ground = x; break;
		case FREE: // nothing to do 
		}
	}
	
	public void swap_shafts(int a, int b)
	{
		Shaft sa = GearScheme.get_shaft(a);
		Shaft sb = GearScheme.get_shaft(b);
		ShaftType ta = get_shaft_type(sa);
		ShaftType tb = get_shaft_type(sb);
		set_shaft_type(sa, tb);
		set_shaft_type(sb, ta);
	}
}
