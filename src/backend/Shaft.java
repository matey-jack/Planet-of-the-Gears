package backend;

public class Shaft
{
	// Identifies a gear/shaft of the gear schema. 
	public final ElementType type;
	public final int index;
	public Shaft(ElementType t, int i)
	{
		type = t;
		index = i;
	}
	public PlanetElement get_element()
	{
		return GearScheme.get_element(index);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if ( ! (obj instanceof Shaft) )
			return false;
		Shaft other = (Shaft) obj;
		return (other.type == type) 
			&& (other.index == index || type == ElementType.CARRIER);
	}
	
	@Override
	public String toString()
	{
		if (type == ElementType.CARRIER) 
			return type.toString(); 
		else
			return type.toString() + "_" + Integer.toString(index+1);
	}
	
	public double ratio_to(Shaft base)
	// The static (that is, fixed carrier) gear ratio between 'this' and 'base'.
	{
		if (type == ElementType.CARRIER)
			return 0;
		return base.measure() / measure() * base.p_measure() / p_measure();
	}
	public double measure()
	{
		return get_element().measure(type);
	}
	public double p_measure()
	{
		return get_element().get_P();
	}
}
