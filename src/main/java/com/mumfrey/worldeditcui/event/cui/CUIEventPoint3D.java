package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventType;

/**
 * Called when point event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventPoint3D extends CUIEvent
{
	public CUIEventPoint3D(WorldEditCUI controller, String[] args)
	{
		super(controller, args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.POINT;
	}
	
	@Override
	public String raise()
	{
		int id = this.getInt(0);
		double x = this.getDouble(1);
		double y = this.getDouble(2);
		double z = this.getDouble(3);
		
		this.controller.getSelection().setCuboidPoint(id, x, y, z);
		this.controller.getDebugger().debug("Setting point #" + id);
		
		return null;
	}
}
