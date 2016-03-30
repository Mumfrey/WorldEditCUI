package com.mumfrey.worldeditcui.render.shapes;

import java.util.List;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.LineInfo;
import com.mumfrey.worldeditcui.render.points.PointRectangle;
import com.mumfrey.worldeditcui.util.Vector2;
import com.mumfrey.worldeditcui.util.Vector3;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import static com.mumfrey.liteloader.gl.GL.*;

/**
 * Draws the grid for a polygon region
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class Render2DGrid
{
	protected LineColour colour;
	protected List<PointRectangle> points;
	protected int min;
	protected int max;
	
	public Render2DGrid(LineColour colour, List<PointRectangle> points, int min, int max)
	{
		this.colour = colour;
		this.points = points;
		this.min = min;
		this.max = max;
	}
	
	public void render(Vector3 cameraPos)
	{
		double off = 0.03;
		for (double height = this.min; height <= this.max + 1; height++)
		{
			this.drawPoly(cameraPos, height + off);
		}
	}
	
	protected void drawPoly(Vector3 cameraPos, double height)
	{
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();
			
			worldRenderer.startDrawing(GL_LINE_LOOP);
			tempColour.prepareColour();
			for (PointRectangle point : this.points)
			{
				if (point != null)
				{
					Vector2 pos = point.getPoint();
					double x = pos.getX() - cameraPos.getX();
					double z = pos.getY() - cameraPos.getZ();
					worldRenderer.addVertex(x + 0.5, height - cameraPos.getY(), z + 0.5);
				}
			}
			tessellator.draw();
		}
	}
}
