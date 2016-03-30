package com.mumfrey.worldeditcui;

import com.mumfrey.worldeditcui.config.CUIConfiguration;
import com.mumfrey.worldeditcui.debug.CUIDebug;
import com.mumfrey.worldeditcui.event.CUIEventDispatcher;
import com.mumfrey.worldeditcui.exceptions.InitialisationException;
import com.mumfrey.worldeditcui.render.CUISelectionProvider;
import com.mumfrey.worldeditcui.render.region.BaseRegion;
import com.mumfrey.worldeditcui.render.region.CuboidRegion;

/**
 * Main controller class. Uses a pseudo-JavaBeans paradigm. The only real
 * logic here is listener registration.
 * 
 * TODO: Preview mode
 * TODO: Command transactions
 * TODO: Add ability to flash selection
 *  
 * @author yetanotherx
 */
public class WorldEditCUI
{
	public static final int PROTOCOL_VERSION = 3;
	
	private BaseRegion selection;
	private CUIDebug debugger;
	private CUIConfiguration configuration;
	private CUIEventDispatcher dispatcher;
	private CUISelectionProvider selectionProvider;
	
	public void initialise()
	{
		this.selection = new CuboidRegion(this);
		this.configuration = CUIConfiguration.create();
		this.debugger = new CUIDebug(this);
		this.dispatcher = new CUIEventDispatcher(this);
		this.selectionProvider = new CUISelectionProvider(this);
		
		try
		{
			this.selection.initialise();
			this.configuration.initialise();
			this.debugger.initialise();
			this.dispatcher.initialise();
			this.selectionProvider.initialise();
		}
		catch (InitialisationException e)
		{
			e.printStackTrace();
			return;
		}
	}

	public CUIEventDispatcher getDispatcher()
	{
		return this.dispatcher;
	}
	
	public CUISelectionProvider getSelectionProvider()
	{
		return this.selectionProvider;
	}

	public CUIConfiguration getConfiguration()
	{
		return this.configuration;
	}
	
	public CUIDebug getDebugger()
	{
		return this.debugger;
	}
	
	public BaseRegion getSelection()
	{
		return this.selection;
	}
	
	public void setSelection(BaseRegion selection)
	{
		this.selection = selection;
	}
}
