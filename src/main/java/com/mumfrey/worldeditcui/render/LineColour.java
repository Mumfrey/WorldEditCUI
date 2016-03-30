package com.mumfrey.worldeditcui.render;

import com.mumfrey.worldeditcui.config.Colour;

import net.minecraft.client.resources.I18n;
import static com.mumfrey.liteloader.gl.GL.*;

/**
 * Stores colour data for each type of line.
 * 
 * Each line has a normal line, and a hidden line.
 * The normal line has an alpha value of 0.8f, and
 * the hidden line has an alpha value of 0.2f. They
 * both have a thickness of 3.0f.
 * 
 * @author yetanotherx
 * @author lahwran
 */
public enum LineColour
{
	CUBOIDBOX      ("colour.cuboidedge",     new Colour("#CC3333CC")),
	CUBOIDGRID     ("colour.cuboidgrid",     new Colour("#CC4C4CCC")),
	CUBOIDPOINT1   ("colour.cuboidpoint1",   new Colour("#33CC33CC")),
	CUBOIDPOINT2   ("colour.cuboidpoint2",   new Colour("#3333CCCC")),
	POLYGRID       ("colour.polygrid",       new Colour("#CC3333CC")),
	POLYBOX        ("colour.polyedge",       new Colour("#CC4C4CCC")),
	POLYPOINT      ("colour.polypoint",      new Colour("#33CCCCCC")),
	ELLIPSOIDGRID  ("colour.ellipsoidgrid",  new Colour("#CC4C4CCC")),
	ELLIPSOIDCENTRE("colour.ellipsoidpoint", new Colour("#CCCC33CC")),
	CYLINDERGRID   ("colour.cylindergrid",   new Colour("#CC3333CC")),
	CYLINDERBOX    ("colour.cylinderedge",   new Colour("#CC4C4CCC")),
	CYLINDERCENTRE ("colour.cylinderpoint",  new Colour("#CC33CCCC"));
	
	private String displayName;
	
	private Colour defaultColour;
	private Colour colour;
	
	private LineInfo normal;
	private LineInfo hidden;
	
	private LineColour(String displayName, Colour colour)
	{
		this.displayName = displayName;
		this.colour = colour;
		this.defaultColour = new Colour().copyFrom(colour);
	}
	
	public String getDisplayName()
	{
		return I18n.format(this.displayName);
	}
	
	public Colour getColour()
	{
		return this.colour;
	}
	
	public LineInfo getHidden()
	{
		return this.hidden;
	}
	
	public LineInfo getNormal()
	{
		return this.normal;
	}
	
	public LineInfo[] getColours()
	{
		return new LineInfo[] { this.hidden, this.normal };
	}
	
	public void setColour(Colour colour)
	{
		this.colour = colour;
		this.updateColour();
	}
	
	public void updateColour()
	{
		this.normal = new LineInfo(3.0f, this.colour.red(), this.colour.green(), this.colour.blue(), this.colour.alpha(), GL_LESS);
		this.hidden = new LineInfo(3.0f, this.colour.red() * 0.75F, this.colour.green() * 0.75F, this.colour.blue() * 0.75F, this.colour.alpha() * 0.25F, GL_GEQUAL);
	}
	
	public void setDefaultColour()
	{
		this.colour.copyFrom(this.defaultColour);
		this.updateColour();
	}
	
	public void setColourIntRGBA(int argb)
	{
		int rgba = ((argb << 8) & 0xFFFFFF00) | (((argb & 0xFF000000) >> 24) & 0xFF);
		this.colour.setHex(Integer.toHexString(rgba));
		this.updateColour();
	}
	
	public int getColourIntARGB()
	{
		return this.colour.getIntARGB();
	}
}
