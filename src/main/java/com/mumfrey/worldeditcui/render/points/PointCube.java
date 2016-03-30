package com.mumfrey.worldeditcui.render.points;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.shapes.Render3DBox;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Stores data about a cube surrounding a
 * block in the world. Used to store info
 * about the selector blocks. Keeps track
 * of colour, x/y/z values, and rendering.
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class PointCube
{
	private static final double OFF = 0.03f;
	
	private static final Vector3 MIN_VEC = new Vector3(PointCube.OFF, PointCube.OFF, PointCube.OFF);
	private static final Vector3 MAX_VEC = new Vector3(PointCube.OFF + 1, PointCube.OFF + 1, PointCube.OFF + 1);

	protected Vector3 point;
	protected LineColour colour = LineColour.CUBOIDPOINT1;
	
	private Render3DBox box;
	
	public PointCube(double x, double y, double z)
	{
		this(new Vector3(x, y, z));
	}
	
	public PointCube(Vector3 point)
	{
		this.setPoint(point);
	}
	
	public void render(Vector3 cameraPos)
	{
		this.box.render(cameraPos);
	}
	
	public Vector3 getPoint()
	{
		return this.point;
	}
	
	public void setPoint(Vector3 point)
	{
		this.point = point;
		this.update();
	}

	public LineColour getColour()
	{
		return this.colour;
	}
	
	public void setColour(LineColour colour)
	{
		this.colour = colour;
		this.update();
	}

	private void update()
	{
		this.box = new Render3DBox(this.colour, this.point.subtract(MIN_VEC), this.point.add(MAX_VEC));
	}
}
