package backend;

import java.awt.Color;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public enum ShaftType
{
	INPUT, OUTPUT, GROUND, FREE;
	public static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);

	public Color get_color()
	{
		switch (this)
		{
		case INPUT : return Color.orange;
		case OUTPUT: return Color.green;
		case GROUND: return Color.lightGray;
		case FREE  : return TRANSPARENT_COLOR;
		default: throw new NotImplementedException(); // to get rid of compiler warning :(
		}
	}
}
