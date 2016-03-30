package com.mumfrey.worldeditcui;

import com.mumfrey.worldeditcui.exceptions.InitialisationException;

/**
 * Simple interface to trace what needs to be initialised at mod loading.
 * Uses a unique exception to know when to halt initialisation and stop mod loading.
 * 
 * @author yetanotherx
 */
public interface InitialisationFactory
{
	
	public void initialise() throws InitialisationException;
}
