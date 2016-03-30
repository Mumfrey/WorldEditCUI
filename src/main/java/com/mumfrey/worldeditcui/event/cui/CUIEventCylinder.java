package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventType;

/**
 * Called when cylinder event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventCylinder extends CUIEvent
{
	
	public CUIEventCylinder(WorldEditCUI controller, String[] args)
	{
		super(controller, args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.CYLINDER;
	}
	
	@Override
	public String raise()
	{
		
		int x = this.getInt(0);
		int y = this.getInt(1);
		int z = this.getInt(2);
		double radX = this.getDouble(3);
		double radZ = this.getDouble(4);
		
		this.controller.getSelection().setCylinderCenter(x, y, z);
		this.controller.getSelection().setCylinderRadius(radX, radZ);
		
		this.controller.getDebugger().debug("Setting centre/radius");
		
		return null;
	}
}
