package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventType;
import com.mumfrey.worldeditcui.render.region.BaseRegion;

/**
 * Called when selection event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventSelection extends CUIEvent
{
	public CUIEventSelection(WorldEditCUI controller, String[] args)
	{
		super(controller, args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.SELECTION;
	}
	
	@Override
	public String raise()
	{
		String key = this.getString(0);
		BaseRegion selection = this.controller.getSelectionProvider().createSelection(key);
		
		if (selection != null)
		{
			selection.initialise();
		}
		
		this.controller.setSelection(selection);
		this.controller.getDebugger().debug("Received selection event, initalizing new region instance.");
		
		return null;
	}
}
