package com.mumfrey.worldeditcui.render.shapes;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.LineInfo;
import com.mumfrey.worldeditcui.util.BoundingBox;
import com.mumfrey.worldeditcui.util.Vector3;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import static com.mumfrey.liteloader.gl.GL.*;

/**
 * Draws the grid for a region between
 * two corners in a cuboid region.
 * 
 * @author yetanotherx
 */
public class Render3DGrid
{
	
	protected LineColour colour;
	protected Vector3 first;
	protected Vector3 second;
	
	public Render3DGrid(LineColour colour, BoundingBox region)
	{
		this(colour, region.getMin(), region.getMax());
	}
	
	public Render3DGrid(LineColour colour, Vector3 first, Vector3 second)
	{
		this.colour = colour;
		this.first = first;
		this.second = second;
	}
	
	public void render(Vector3 cameraPos)
	{
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		double x1 = this.first.getX() - cameraPos.getX();
		double y1 = this.first.getY() - cameraPos.getY();
		double z1 = this.first.getZ() - cameraPos.getZ();
		double x2 = this.second.getX() - cameraPos.getX();
		double y2 = this.second.getY() - cameraPos.getY();
		double z2 = this.second.getZ() - cameraPos.getZ();
		
		double cullAt = 128.0F;
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();
			
			worldRenderer.startDrawing(GL_LINES);
			tempColour.prepareColour();
			
			double offsetSize = 1.0;
			
			for (double yoff = 0; yoff + y1 <= y2; yoff += offsetSize)
			{
				double y = y1 + yoff;
				worldRenderer.addVertex(x1, y, z2);
				worldRenderer.addVertex(x2, y, z2);
				worldRenderer.addVertex(x1, y, z1);
				worldRenderer.addVertex(x2, y, z1);
				worldRenderer.addVertex(x1, y, z1);
				worldRenderer.addVertex(x1, y, z2);
				worldRenderer.addVertex(x2, y, z1);
				worldRenderer.addVertex(x2, y, z2);
			}
			
			for (double xoff = 0; xoff + x1 <= x2; xoff += offsetSize)
			{
				double x = x1 + xoff;
//				boolean major = xoff % 10 == 0;
				if (x < -cullAt) continue;
				if (x > cullAt) break;
				worldRenderer.addVertex(x, y1, z1);
				worldRenderer.addVertex(x, y2, z1);
				worldRenderer.addVertex(x, y1, z2);
				worldRenderer.addVertex(x, y2, z2);
				worldRenderer.addVertex(x, y2, z1);
				worldRenderer.addVertex(x, y2, z2);
				worldRenderer.addVertex(x, y1, z1);
				worldRenderer.addVertex(x, y1, z2);
			}
			
			for (double zoff = 0; zoff + z1 <= z2; zoff += offsetSize)
			{
				double z = z1 + zoff;
//				boolean major = zoff % 10 == 0;
				if (z < -cullAt) continue;
				if (z > cullAt) break;
				worldRenderer.addVertex(x1, y1, z);
				worldRenderer.addVertex(x2, y1, z);
				worldRenderer.addVertex(x1, y2, z);
				worldRenderer.addVertex(x2, y2, z);
				worldRenderer.addVertex(x2, y1, z);
				worldRenderer.addVertex(x2, y2, z);
				worldRenderer.addVertex(x1, y1, z);
				worldRenderer.addVertex(x1, y2, z);
			}
			
			tessellator.draw();
		}
	}
}
