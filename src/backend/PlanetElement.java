package backend;

public class PlanetElement
{
	/**
	 * a planet element is the triple of ring gear, planet gear, and sun gear
	 * which are in mesh with each other. ring and sun are optional (altho the
	 * element is useless if it hasn't at least ring or sun). 
	 * this class stores the static measure (a number proportional to gear 
	 * diameter, circumference, and tooth count) of those gears.
	 * due to normalization and the relation R=S+2×P, 
	 * we only need to store one of the three values. 
	 * 
	 * for convenience of calculating the direction of rotation, 
	 * the ring's measure is always transmitted as a negative number. 
	 * 
	 */
	private double P = 0.5; // default constructor, a random valid value
	void invariant() 
	{
		assert 0 < P;
		assert P < 1;
	}
	PlanetElement(double _P ) { P = _P; invariant(); }
	
	double get_P() { return P; } ;
	double get_S() { return (1-P); } ;
	double get_R() { return -(1+P); } ;
	void set_P(double _P) { P = _P; invariant(); }
	void set_S(double S) { P = (1-S); invariant(); }
	void set_R(double R) { P = (-R-1); invariant(); }

	// for purposes of display:
	public boolean has_ring = true;
	public boolean has_sun = true;
	
	public double measure(ElementType type)
	{
		switch (type)
		{
		case RING   : return get_R();
		case SUN    : return get_S();
		case PLANET : return get_P();
		default	    : throw new IllegalArgumentException();
		}
	}
	public void set_measure(ElementType t, String val)
	{
		val = val.substring(val.lastIndexOf(".")+1);
		switch (t)
		{
		case RING   : set_R(Double.parseDouble("-1." + val)); break;
		case SUN    : set_S(Double.parseDouble("0." + val)); break;
		case PLANET : set_P(Double.parseDouble("0." + val)); break;
		default	    : throw new IllegalArgumentException();
		}
	}

}
