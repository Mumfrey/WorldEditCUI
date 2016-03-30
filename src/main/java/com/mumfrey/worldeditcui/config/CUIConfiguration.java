package com.mumfrey.worldeditcui.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.worldeditcui.InitialisationFactory;
import com.mumfrey.worldeditcui.render.LineColour;

/**
 * Stores and reads WorldEditCUI settings
 * 
 * @author yetanotherx
 * 
 */
public class CUIConfiguration implements InitialisationFactory
{
	private static final String CONFIG_FILE_NAME = "worldeditcui.config.json";

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private boolean debugMode = false;
	private boolean ignoreUpdates = false;
	private boolean promiscuous = false;
	private boolean alwaysOnTop = false;
	
	private Colour cuboidGridColor        = new Colour("#CC3333CC");
	private Colour cuboidEdgeColor        = new Colour("#CC4C4CCC");
	private Colour cuboidFirstPointColor  = new Colour("#33CC33CC");
	private Colour cuboidSecondPointColor = new Colour("#3333CCCC");
	private Colour polyGridColor          = new Colour("#CC3333CC");
	private Colour polyEdgeColor          = new Colour("#CC4C4CCC");
	private Colour polyPointColor         = new Colour("#33CCCCCC");
	private Colour ellipsoidGridColor     = new Colour("#CC4C4CCC");
	private Colour ellipsoidPointColor    = new Colour("#CCCC33CC");
	private Colour cylinderGridColor      = new Colour("#CC3333CC");
	private Colour cylinderEdgeColor      = new Colour("#CC4C4CCC");
	private Colour cylinderPointColor     = new Colour("#CC33CCCC");
	
	/**
	 * Copies the default config file to the proper directory if it does not
	 * exist. It then reads the file and sets each variable to the proper value.
	 */
	@Override
	public void initialise()
	{
		this.cuboidGridColor        = Colour.setDefault(this.cuboidGridColor,        "#CC3333CC");
		this.cuboidEdgeColor        = Colour.setDefault(this.cuboidEdgeColor,        "#CC4C4CCC");
		this.cuboidFirstPointColor  = Colour.setDefault(this.cuboidFirstPointColor,  "#33CC33CC");
		this.cuboidSecondPointColor = Colour.setDefault(this.cuboidSecondPointColor, "#3333CCCC");
		this.polyGridColor          = Colour.setDefault(this.polyGridColor,          "#CC3333CC");
		this.polyEdgeColor          = Colour.setDefault(this.polyEdgeColor,          "#CC4C4CCC");
		this.polyPointColor         = Colour.setDefault(this.polyPointColor,         "#33CCCCCC");
		this.ellipsoidGridColor     = Colour.setDefault(this.ellipsoidGridColor,     "#CC4C4CCC");
		this.ellipsoidPointColor    = Colour.setDefault(this.ellipsoidPointColor,    "#CCCC33CC");
		this.cylinderGridColor      = Colour.setDefault(this.cylinderGridColor,      "#CC3333CC");
		this.cylinderEdgeColor      = Colour.setDefault(this.cylinderEdgeColor,      "#CC4C4CCC");
		this.cylinderPointColor     = Colour.setDefault(this.cylinderPointColor,     "#CC33CCCC");
		
		LineColour.CUBOIDBOX.setColour(this.cuboidEdgeColor);
		LineColour.CUBOIDGRID.setColour(this.cuboidGridColor);
		LineColour.CUBOIDPOINT1.setColour(this.cuboidFirstPointColor);
		LineColour.CUBOIDPOINT2.setColour(this.cuboidSecondPointColor);
		LineColour.POLYGRID.setColour(this.polyGridColor);
		LineColour.POLYBOX.setColour(this.polyEdgeColor);
		LineColour.POLYPOINT.setColour(this.polyPointColor);
		LineColour.ELLIPSOIDGRID.setColour(this.ellipsoidGridColor);
		LineColour.ELLIPSOIDCENTRE.setColour(this.ellipsoidPointColor);
		LineColour.CYLINDERGRID.setColour(this.cylinderGridColor);
		LineColour.CYLINDERBOX.setColour(this.cylinderEdgeColor);
		LineColour.CYLINDERCENTRE.setColour(this.cylinderPointColor);
		
		this.save();
	}
	
	public boolean isDebugMode()
	{
		return this.debugMode;
	}
	
	public boolean ignoreUpdates()
	{
		return this.ignoreUpdates;
	}
	
	public boolean isPromiscuous()
	{
		return this.promiscuous;
	}
	
	public void setPromiscuous(boolean promiscuous)
	{
		this.promiscuous = promiscuous;
	}
	
	public boolean isAlwaysOnTop()
	{
		return this.alwaysOnTop;
	}
	
	public void setAlwaysOnTop(boolean alwaysOnTop)
	{
		this.alwaysOnTop = alwaysOnTop;
	}
	
	public static CUIConfiguration create()
	{
		File jsonFile = new File(LiteLoader.getCommonConfigFolder(), CUIConfiguration.CONFIG_FILE_NAME);
		
		if (jsonFile.exists())
		{
			FileReader fileReader = null;
			
			try
			{
				fileReader = new FileReader(jsonFile);
				CUIConfiguration config = CUIConfiguration.gson.fromJson(fileReader, CUIConfiguration.class);
				return config;
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				try
				{
					if (fileReader != null) fileReader.close();
				}
				catch (IOException ex) {}
			}
		}
		
		return new CUIConfiguration();
	}
	
	public void save()
	{
		File jsonFile = new File(LiteLoader.getCommonConfigFolder(), CUIConfiguration.CONFIG_FILE_NAME);
		
		FileWriter fileWriter = null;
		
		try
		{
			fileWriter = new FileWriter(jsonFile);
			CUIConfiguration.gson.toJson(this, fileWriter);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if (fileWriter != null) fileWriter.close();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
}
