package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.event.CUIEventType;

/**
 * Called when poly point event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventPoint2D extends CUIEventPoint3D
{
	
	public CUIEventPoint2D(WorldEditCUI controller, String[] args)
	{
		super(controller, args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.POINT2D;
	}
	
	@Override
	public String raise()
	{
		
		int id = this.getInt(0);
		int x = this.getInt(1);
		int z = this.getInt(2);
		@SuppressWarnings("unused")
		int regionSize = this.getInt(3);
		this.controller.getSelection().setPolygonPoint(id, x, z);
		
		this.controller.getDebugger().debug("Setting point2d #" + id);
		
		return null;
	}
}
