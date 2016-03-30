package com.mumfrey.worldeditcui.event.listeners;

import static com.mumfrey.liteloader.gl.GL.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Listener for WorldRenderEvent
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CUIListenerWorldRender
{
	private WorldEditCUI controller;
	
	private Minecraft minecraft;
	
	public CUIListenerWorldRender(WorldEditCUI controller, Minecraft minecraft)
	{
		this.controller = controller;
		this.minecraft = minecraft;
	}
	
	public void onRender(float partialTicks)
	{
		try
		{
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
			
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glEnableBlend();
			glEnableAlphaTest();
			glAlphaFunc(GL_GREATER, 0.0F);
			glDisableTexture2D();
			glEnableDepthTest();
			glDepthMask(false);
			glPushMatrix();
			glDisableFog();
			
			try
			{
				Vector3 cameraPos = new Vector3(this.minecraft.getRenderViewEntity(), partialTicks); 
				glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
				if (this.controller.getSelection() != null)
				{
					this.controller.getSelection().render(cameraPos);
				}
			}
			catch (Exception e)
			{
			}
			
			glDepthFunc(GL_LEQUAL);
			glPopMatrix();
			
			glDepthMask(true);
			glEnableTexture2D();
			glDisableBlend();
			glAlphaFunc(GL_GREATER, 0.1F);
		}
		catch (Exception ex) {}
	}
}
