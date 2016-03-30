package com.mumfrey.worldeditcui.render.shapes;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.LineInfo;
import com.mumfrey.worldeditcui.util.Vector3;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import static com.mumfrey.liteloader.gl.GL.*;

/**
 * Draws a polygon
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class Render3DPolygon
{
	
	protected LineColour colour;
	protected Vector3[] vertices;
	
	public Render3DPolygon(LineColour colour, Vector3... vertices)
	{
		this.colour = colour;
		this.vertices = vertices;
	}
	
	public void render(Vector3 cameraPos)
	{
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();
			
			worldRenderer.begin(GL_LINE_LOOP, DefaultVertexFormats.POSITION);
			tempColour.prepareColour();
			for (Vector3 vertex : this.vertices)
			{
				worldRenderer.pos(vertex.getX() - cameraPos.getX(), vertex.getY() - cameraPos.getY(), vertex.getZ() - cameraPos.getZ()).endVertex();
			}
			tessellator.draw();
		}
	}
}
