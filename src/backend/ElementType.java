package backend;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public enum ElementType
{
	RING, SUN, CARRIER, PLANET;
	// not all of those are used in all contexts, 
	// see the assertions at the places of use.
	public String toString()
	{
		switch (this)
		{
		case RING: return "ring";
		case SUN: return "sun";
		case CARRIER: return "carrier";
		case PLANET: return "planet";
		default: throw new NotImplementedException();
		}
	}
}
