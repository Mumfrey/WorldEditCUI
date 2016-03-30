package com.mumfrey.worldeditcui.render.region;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.points.PointCube;
import com.mumfrey.worldeditcui.render.shapes.RenderCylinderBox;
import com.mumfrey.worldeditcui.render.shapes.RenderCylinderCircles;
import com.mumfrey.worldeditcui.render.shapes.RenderCylinderGrid;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Main controller for a cylinder-type region
 * 
 * @author yetanotherx
 */
public class CylinderRegion extends BaseRegion
{
	
	protected PointCube centre;
	protected double radX = 0;
	protected double radZ = 0;
	protected int minY = 0;
	protected int maxY = 0;
	
	private RenderCylinderCircles circles;
	private RenderCylinderGrid grid;
	private RenderCylinderBox box;
	
	public CylinderRegion(WorldEditCUI controller)
	{
		super(controller);
	}
	
	@Override
	public void render(Vector3 cameraPos)
	{
		if (this.centre != null)
		{
			this.centre.render(cameraPos);
			this.circles.render(cameraPos);
			this.grid.render(cameraPos);
			this.box.render(cameraPos);
		}
	}

	@Override
	public void setCylinderCenter(int x, int y, int z)
	{
		this.centre = new PointCube(x, y, z);
		this.centre.setColour(LineColour.CYLINDERCENTRE);
		this.update();
	}
	
	@Override
	public void setCylinderRadius(double x, double z)
	{
		this.radX = x;
		this.radZ = z;
		this.update();
	}
	
	@Override
	public void setMinMax(int min, int max)
	{
		this.minY = min;
		this.maxY = max;
		this.update();
	}
	
	private void update()
	{
		int tMin = this.minY;
		int tMax = this.maxY;
		
		if (this.minY == 0 || this.maxY == 0)
		{
			tMin = (int)this.centre.getPoint().getY();
			tMax = (int)this.centre.getPoint().getY();
		}
		
		this.circles = new RenderCylinderCircles(LineColour.CYLINDERGRID, this.centre, this.radX, this.radZ, tMin, tMax);
		this.grid = new RenderCylinderGrid(LineColour.CYLINDERGRID, this.centre, this.radX, this.radZ, tMin, tMax);
		this.box = new RenderCylinderBox(LineColour.CYLINDERBOX, this.centre, this.radX, this.radZ, tMin, tMax);
	}
	
	@Override
	public RegionType getType()
	{
		return RegionType.CYLINDER;
	}
}
