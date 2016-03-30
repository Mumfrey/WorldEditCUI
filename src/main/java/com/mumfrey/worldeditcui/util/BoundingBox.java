package com.mumfrey.worldeditcui.util;

import com.mumfrey.worldeditcui.render.points.PointCube;


public class BoundingBox
{
	private static final double OFF = 0.02;

	private static final Vector3 MIN_VEC = new Vector3(BoundingBox.OFF, BoundingBox.OFF, BoundingBox.OFF);
	private static final Vector3 MAX_VEC = new Vector3(BoundingBox.OFF + 1, BoundingBox.OFF + 1, BoundingBox.OFF + 1);

	private final Vector3 min, max;
	
	public BoundingBox(PointCube p1, PointCube p2)
	{
		this(p1.getPoint(), p2.getPoint());
	}
	
	public BoundingBox(Vector3 p1, Vector3 p2)
	{
		this.min = new Vector3(Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()), Math.min(p1.getZ(), p2.getZ())).subtract(BoundingBox.MIN_VEC);
		this.max = new Vector3(Math.max(p1.getX(), p2.getX()), Math.max(p1.getY(), p2.getY()), Math.max(p1.getZ(), p2.getZ())).add(BoundingBox.MAX_VEC);
	}

	public Vector3 getMin()
	{
		return this.min;
	}

	public Vector3 getMax()
	{
		return this.max;
	}
}
