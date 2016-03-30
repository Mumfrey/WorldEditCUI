package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventType;

/**
 * Called when resize event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventBounds extends CUIEvent
{
	
	public CUIEventBounds(WorldEditCUI controller, String[] args)
	{
		super(controller, args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.MINMAX;
	}
	
	@Override
	public String raise()
	{
		int min = this.getInt(0);
		int max = this.getInt(1);
		this.controller.getSelection().setMinMax(min, max);
		
		this.controller.getDebugger().debug("Expanding/contracting selection.");
		
		return null;
	}
}
