package com.mumfrey.worldeditcui.debug;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.worldeditcui.InitialisationFactory;
import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.exceptions.InitialisationException;
import com.mumfrey.worldeditcui.util.ConsoleLogFormatter;

/**
 * Debugging helper class
 * 
 * @author yetanotherx
 * 
 */
public class CUIDebug implements InitialisationFactory
{
	
	protected WorldEditCUI controller;
	protected File debugFile;
	protected boolean debugMode = false;
	protected final static Logger logger = Logger.getLogger("WorldEditCUI");
	
	public CUIDebug(WorldEditCUI controller)
	{
		this.controller = controller;
	}
	
	@Override
	public void initialise() throws InitialisationException
	{
		
		ConsoleLogFormatter formatter = new ConsoleLogFormatter();
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(formatter);
		
		logger.setUseParentHandlers(false);
		logger.addHandler(handler);
		
		try
		{
			this.debugFile = new File(LiteLoader.getCommonConfigFolder(), "worldeditcui.debug.log");
			this.debugMode = this.controller.getConfiguration().isDebugMode();
			
			if (this.debugMode)
			{
				FileHandler newHandler = new FileHandler(this.debugFile.getAbsolutePath());
				newHandler.setFormatter(formatter);
				logger.addHandler(newHandler);
			}
			
		}
		catch (IOException e)
		{
			e.printStackTrace(System.err);
			throw new InitialisationException();
		}
		
	}
	
	/**
	 * Shows a message if debug mode is true
	 * @param message 
	 */
	public void debug(String message)
	{
		if (this.debugMode)
		{
			logger.info("WorldEditCUI Debug - " + message);
		}
	}
	
	public void info(String message)
	{
		logger.info(message);
	}
	
	public void info(String message, Throwable e)
	{
		logger.log(Level.INFO, message, e);
	}
}
