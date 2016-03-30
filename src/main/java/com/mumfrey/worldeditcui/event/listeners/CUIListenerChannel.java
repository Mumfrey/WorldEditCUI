package com.mumfrey.worldeditcui.event.listeners;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.event.CUIEventArgs;

/**
 * Listener class for incoming plugin channel messages
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CUIListenerChannel
{
	private WorldEditCUI controller;
	
	public CUIListenerChannel(WorldEditCUI controller)
	{
		this.controller = controller;
	}
	
	public void onMessage(String message)
	{
		String[] split = message.split("[|]");
		String type = split[0];
		String args = message.substring(type.length() + 1);
		
		this.controller.getDebugger().debug("Received CUI event from server: " + message);
		
		try
		{
			CUIEventArgs eventArgs = new CUIEventArgs(this.controller, type, args.split("[|]"));
			this.controller.getDispatcher().raiseEvent(eventArgs);
		}
		catch (Exception ex)
		{
		}
	}
}
