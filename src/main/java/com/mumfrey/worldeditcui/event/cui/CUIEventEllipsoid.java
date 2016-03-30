package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventType;

/**
 * Called when ellipsoid event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventEllipsoid extends CUIEvent
{
	
	public CUIEventEllipsoid(WorldEditCUI controller, String[] args)
	{
		super(controller, args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.ELLIPSOID;
	}
	
	@Override
	public String raise()
	{
		
		int id = this.getInt(0);
		
		if (id == 0)
		{
			int x = this.getInt(1);
			int y = this.getInt(2);
			int z = this.getInt(3);
			this.controller.getSelection().setEllipsoidCenter(x, y, z);
		}
		else if (id == 1)
		{
			double x = this.getDouble(1);
			double y = this.getDouble(2);
			double z = this.getDouble(3);
			this.controller.getSelection().setEllipsoidRadii(x, y, z);
		}
		
		this.controller.getDebugger().debug("Setting centre/radius");
		
		return null;
	}
}
