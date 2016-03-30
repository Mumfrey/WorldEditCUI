package com.mumfrey.worldeditcui.render.region;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.points.PointCube;
import com.mumfrey.worldeditcui.render.shapes.Render3DBox;
import com.mumfrey.worldeditcui.render.shapes.Render3DGrid;
import com.mumfrey.worldeditcui.util.BoundingBox;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Main controller for a cuboid-type region
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class CuboidRegion extends BaseRegion
{
	protected PointCube firstPoint;
	protected PointCube secondPoint;
	
	private Render3DGrid grid;
	private Render3DBox box;
	
	public CuboidRegion(WorldEditCUI controller)
	{
		super(controller);
	}
	
	@Override
	public void render(Vector3 cameraPos)
	{
		if (this.firstPoint != null && this.secondPoint != null)
		{
			this.grid.render(cameraPos);
			this.box.render(cameraPos);
			this.firstPoint.render(cameraPos);
			this.secondPoint.render(cameraPos);
		}
		else if (this.firstPoint != null)
		{
			this.firstPoint.render(cameraPos);
		}
		else if (this.secondPoint != null)
		{
			this.secondPoint.render(cameraPos);
		}
	}
	
	@Override
	public void setCuboidPoint(int id, double x, double y, double z)
	{
		if (id == 0)
		{
			this.firstPoint = new PointCube(x, y, z);
			this.firstPoint.setColour(LineColour.CUBOIDPOINT1);
		}
		else if (id == 1)
		{
			this.secondPoint = new PointCube(x, y, z);
			this.secondPoint.setColour(LineColour.CUBOIDPOINT2);
		}
		
		if (this.firstPoint != null && this.secondPoint != null)
		{
			BoundingBox bounds = new BoundingBox(this.firstPoint, this.secondPoint);
			this.grid = new Render3DGrid(LineColour.CUBOIDGRID, bounds);
			this.box = new Render3DBox(LineColour.CUBOIDBOX, bounds);
		}
	}
	
	@Override
	public RegionType getType()
	{
		return RegionType.CUBOID;
	}
}
