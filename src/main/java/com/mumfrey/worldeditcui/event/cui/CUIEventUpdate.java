package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventType;

/**
 * Called when update event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventUpdate extends CUIEvent
{
	
	public CUIEventUpdate(WorldEditCUI controller, String[] args)
	{
		super(controller, args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.UPDATE;
	}
	
	@Override
	public String raise()
	{
		return null;
	}
}
