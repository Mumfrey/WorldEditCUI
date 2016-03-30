package com.mumfrey.worldeditcui.util;

/**
 * A version of Vector2 that can be modified.
 * 
 * @author yetanotherx
 */
public class Vector2m extends Vector2
{
	
	public Vector2m()
	{
	}
	
	public Vector2m(Double x, Double y)
	{
		super(x, y);
	}
	
	public Vector2m(float x, float y)
	{
		super(x, y);
	}
	
	public Vector2m(Vector2 original)
	{
		super(original);
	}
	
	/**
	 * Sets the X coordinate
	 *
	 * @param x The x coordinate
	 */
	public void setX(float x)
	{
		this.x = x;
	}
	
	/**
	 * Sets the Y coordinate
	 *
	 * @param y The Y coordinate
	 */
	public void setY(float y)
	{
		this.z = y;
	}
	
	@Override
	public Vector2 add(Vector2 that)
	{
		this.x += that.x;
		this.z += that.z;
		return this;
	}
	
	@Override
	public Vector2 subtract(Vector2 that)
	{
		this.x -= that.x;
		this.z -= that.z;
		return this;
	}
	
	@Override
	public Vector2 scale(double scale)
	{
		this.x *= scale;
		this.z *= scale;
		return this;
	}
	
	public Vector2 cross(Vector2 that)
	{
		double tmp = this.z;
		this.z = -this.x;
		this.x = tmp;
		return this;
	}
	
	/**
	 * Rounds the X and Y values of this Vector2 up to 
	 * the nearest integer value. 
	 * 
	 * @return 
	 */
	@Override
	public Vector2 ceil()
	{
		this.x = (float)Math.ceil(this.x);
		this.z = (float)Math.ceil(this.z);
		return this;
	}
	
	/**
	 * Rounds the X and Y values of this Vector2 down to 
	 * the nearest integer value. 
	 * 
	 * @return 
	 */
	@Override
	public Vector2 floor()
	{
		this.x = (float)Math.floor(this.x);
		this.z = (float)Math.floor(this.z);
		return this;
	}
	
	/**
	 * Rounds the X and Y values of this Vector2 to 
	 * the nearest integer value. 
	 * 
	 * @return 
	 */
	@Override
	public Vector2 round()
	{
		this.x = Math.round(this.x);
		this.z = Math.round(this.z);
		return this;
	}
	
	/**
	 * Sets the X and Y values of this Vector2 to their
	 * absolute value.
	 * 
	 * @return 
	 */
	@Override
	public Vector2 abs()
	{
		this.x = Math.abs(this.x);
		this.z = Math.abs(this.z);
		return this;
	}
	
	@Override
	public Vector2 normalize()
	{
		double length = this.length();
		this.x *= 1 / length;
		this.z *= 1 / length;
		return this;
	}
}
