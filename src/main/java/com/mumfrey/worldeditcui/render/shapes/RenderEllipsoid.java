package com.mumfrey.worldeditcui.render.shapes;

import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.LineInfo;
import com.mumfrey.worldeditcui.render.points.PointCube;
import com.mumfrey.worldeditcui.util.Vector3;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import static com.mumfrey.liteloader.gl.GL.*;

/**
 * Draws an ellipsoid shape around a centre point.
 * 
 * @author yetanotherx
 */
public class RenderEllipsoid
{
	
	protected LineColour colour;
	protected PointCube centre;
	protected Vector3 radii;
	protected final static double twoPi = Math.PI * 2;
	protected double centreX;
	protected double centreY;
	protected double centreZ;
	
	public RenderEllipsoid(LineColour colour, PointCube centre, Vector3 radii)
	{
		this.colour = colour;
		this.centre = centre;
		this.radii = radii;
		this.centreX = centre.getPoint().getX() + 0.5;
		this.centreY = centre.getPoint().getY() + 0.5;
		this.centreZ = centre.getPoint().getZ() + 0.5;
	}
	
	public void render(Vector3 cameraPos)
	{
		glPushMatrix();
		glTranslated(this.centreX - cameraPos.getX(), this.centreY - cameraPos.getY(), this.centreZ - cameraPos.getZ());
		
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();
			this.drawXZPlane(tempColour);
			this.drawYZPlane(tempColour);
			this.drawXYPlane(tempColour);
		}
		
		glPopMatrix();
	}
	
	protected void drawXZPlane(LineInfo colour)
	{
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();

		int yRad = (int)Math.floor(this.radii.getY());
		for (int yBlock = -yRad; yBlock < yRad; yBlock++)
		{
			worldRenderer.begin(GL_LINE_LOOP, DefaultVertexFormats.POSITION);
			colour.prepareColour();
			
			for (int i = 0; i <= 40; i++)
			{
				double tempTheta = i * twoPi / 40;
				double tempX = this.radii.getX() * Math.cos(tempTheta) * Math.cos(Math.asin(yBlock / this.radii.getY()));
				double tempZ = this.radii.getZ() * Math.sin(tempTheta) * Math.cos(Math.asin(yBlock / this.radii.getY()));
				
				worldRenderer.pos(tempX, yBlock, tempZ).endVertex();
			}
			tessellator.draw();
		}
		
		worldRenderer.begin(GL_LINE_LOOP, DefaultVertexFormats.POSITION);
		colour.prepareColour();
		
		for (int i = 0; i <= 40; i++)
		{
			double tempTheta = i * twoPi / 40;
			double tempX = this.radii.getX() * Math.cos(tempTheta);
			double tempZ = this.radii.getZ() * Math.sin(tempTheta);
			
			worldRenderer.pos(tempX, 0.0, tempZ).endVertex();
		}
		tessellator.draw();
	}
	
	protected void drawYZPlane(LineInfo colour)
	{
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();

		int xRad = (int)Math.floor(this.radii.getX());
		for (int xBlock = -xRad; xBlock < xRad; xBlock++)
		{
			worldRenderer.begin(GL_LINE_LOOP, DefaultVertexFormats.POSITION);
			colour.prepareColour();
			
			for (int i = 0; i <= 40; i++)
			{
				double tempTheta = i * twoPi / 40;
				double tempY = this.radii.getY() * Math.cos(tempTheta) * Math.sin(Math.acos(xBlock / this.radii.getX()));
				double tempZ = this.radii.getZ() * Math.sin(tempTheta) * Math.sin(Math.acos(xBlock / this.radii.getX()));
				
				worldRenderer.pos(xBlock, tempY, tempZ).endVertex();
			}
			tessellator.draw();
		}
		
		worldRenderer.begin(GL_LINE_LOOP, DefaultVertexFormats.POSITION);
		colour.prepareColour();
		
		for (int i = 0; i <= 40; i++)
		{
			double tempTheta = i * twoPi / 40;
			double tempY = this.radii.getY() * Math.cos(tempTheta);
			double tempZ = this.radii.getZ() * Math.sin(tempTheta);
			
			worldRenderer.pos(0.0, tempY, tempZ).endVertex();
		}
		tessellator.draw();
	}
	
	protected void drawXYPlane(LineInfo colour)
	{
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();

		int zRad = (int)Math.floor(this.radii.getZ());
		for (int zBlock = -zRad; zBlock < zRad; zBlock++)
		{
			worldRenderer.begin(GL_LINE_LOOP, DefaultVertexFormats.POSITION);
			colour.prepareColour();
			
			for (int i = 0; i <= 40; i++)
			{
				double tempTheta = i * twoPi / 40;
				double tempX = this.radii.getX() * Math.sin(tempTheta) * Math.sin(Math.acos(zBlock / this.radii.getZ()));
				double tempY = this.radii.getY() * Math.cos(tempTheta) * Math.sin(Math.acos(zBlock / this.radii.getZ()));
				
				worldRenderer.pos(tempX, tempY, zBlock).endVertex();
			}
			tessellator.draw();
		}
		
		worldRenderer.begin(GL_LINE_LOOP, DefaultVertexFormats.POSITION);
		colour.prepareColour();
		
		for (int i = 0; i <= 40; i++)
		{
			double tempTheta = i * twoPi / 40;
			double tempX = this.radii.getX() * Math.cos(tempTheta);
			double tempY = this.radii.getY() * Math.sin(tempTheta);
			
			worldRenderer.pos(tempX, tempY, 0.0).endVertex();
		}
		tessellator.draw();
	}
}
