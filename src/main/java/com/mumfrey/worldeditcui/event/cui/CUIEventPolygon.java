package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventType;

/**
 * Called when polygon event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventPolygon extends CUIEvent
{
	
	public CUIEventPolygon(WorldEditCUI controller, String[] args)
	{
		super(controller, args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.POLYGON;
	}
	
	@Override
	public String raise()
	{
		final int[] vertexIds = new int[this.args.length];
		for (int i = 0; i < this.args.length; ++i)
		{
			vertexIds[i] = this.getInt(i);
		}
		
		this.controller.getSelection().addPolygon(vertexIds);
		//this.controller.getDebugger().debug("Setting point #" + id);
		
		return null;
	}
}
