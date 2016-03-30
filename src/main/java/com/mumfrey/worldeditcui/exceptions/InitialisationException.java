package com.mumfrey.worldeditcui.exceptions;

/**
 * Special exception that only gets called during initialisation
 * Throwing this halts the loading of the mod
 * 
 * @author yetanotherx
 * 
 */
public class InitialisationException extends Exception
{
	
	private static final long serialVersionUID = 1L;
	
	public InitialisationException(String string)
	{
		super(string);
	}
	
	public InitialisationException()
	{
	}
}
