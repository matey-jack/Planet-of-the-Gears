package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JLabel;

public class JLinkLabel extends JLabel
{

	public JLinkLabel(String text)
	{
		super(text);
		// setFont(getFont().deriveFont(Font.ITALIC));
		// TODO underline has to be drawn manually
		setForeground(Color.blue);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	// TODO: handle onClick event
}
