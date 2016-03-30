package com.mumfrey.worldeditcui.event;

import com.google.common.base.Joiner;
import com.mumfrey.worldeditcui.WorldEditCUI;

/**
 * Base event for CUI events, handles parameter validation and running the logic
 * 
 * @author yetanotherx
 * 
 */
public abstract class CUIEvent
{
	
	protected WorldEditCUI controller;
	protected String[] args;
	
	public CUIEvent(WorldEditCUI controller, String[] args)
	{
		this.controller = controller;
		this.args = args;
	}
	
	public abstract String raise();
	
	public abstract CUIEventType getEventType();
	
	public String getEventName()
	{
		return this.getEventType().getName();
	}
	
	/**
	 * Checks if the parameters match the required length.
	 * @return 
	 */
	public boolean isValid()
	{
		int max = this.getEventType().getMaxParameters();
		int min = this.getEventType().getMinParameters();
		
		if (max == min)
		{
			if (this.args.length != max)
			{
				return false;
			}
		}
		else
		{
			if (this.args.length > max || this.args.length < min)
			{
				return false;
			}
		}
		
		return true;
		
	}
	
	public final void prepare()
	{
		if (this.controller == null || this.args == null)
		{
			throw new IllegalStateException("Controller and parameters must both be set.");
		}
		
		if (!this.isValid())
		{
			String message = String.format("Invalid number of parameters. %s event requires %s parameters but received %s [%s]", this.getEventName(), this.getRequiredParameterString(), this.args.length, Joiner.on(", ").join(this.args));
			throw new IllegalArgumentException(message);
		}
	}
	
	private String getRequiredParameterString()
	{
		if (this.getEventType().getMaxParameters() == this.getEventType().getMinParameters())
		{
			return String.valueOf(this.getEventType().getMaxParameters());
		}
		
		return String.format("between %d and %d", this.getEventType().getMinParameters(), this.getEventType().getMaxParameters());
	}

	public int getInt(int index)
	{
		return (int)Float.parseFloat(this.args[index]);
	}
	
	public double getDouble(int index)
	{
		return Double.parseDouble(this.args[index]);
	}
	
	public String getString(int index)
	{
		return this.args[index];
	}
}
