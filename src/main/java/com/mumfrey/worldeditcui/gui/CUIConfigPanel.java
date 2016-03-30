package com.mumfrey.worldeditcui.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;

import com.mumfrey.liteloader.client.gui.GuiCheckbox;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigPanelHost;
import com.mumfrey.worldeditcui.LiteModWorldEditCUI;
import com.mumfrey.worldeditcui.config.CUIConfiguration;
import com.mumfrey.worldeditcui.gui.controls.GuiColourButton;
import com.mumfrey.worldeditcui.gui.controls.GuiControl;
import com.mumfrey.worldeditcui.render.LineColour;

public class CUIConfigPanel extends Gui implements ConfigPanel
{
	private static final int CONTROL_SPACING = 24;
	private static final int CONTROL_TOP = 80;

	private Minecraft mc;
	
	private LiteModWorldEditCUI mod;
	
	private List<GuiButton> controlList = new ArrayList<GuiButton>();
	
	private List<GuiColourButton> colourButtonList = new ArrayList<GuiColourButton>();
	
	private GuiButton activeControl;
	
	private GuiCheckbox chkPromiscuous, chkAlwaysOnTop;
	
	public CUIConfigPanel()
	{
		this.mc = Minecraft.getMinecraft();
	}
	
	@Override
	public String getPanelTitle()
	{
		return I18n.format("wecui.options.title");
	}
	
	@Override
	public int getContentHeight()
	{
		return LineColour.values().length * CUIConfigPanel.CONTROL_SPACING + CUIConfigPanel.CONTROL_TOP;
	}
	
	@Override
	public void onPanelShown(ConfigPanelHost host)
	{
		this.mod = host.getMod();
		
		ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		GuiControl.setScreenSizeAndScale(host.getWidth(), this.getContentHeight(), scaledresolution.getScaleFactor());
		
		this.controlList.clear();
		int nextId = 0;
		int top = CUIConfigPanel.CONTROL_TOP;
		
		for (LineColour colour : LineColour.values())
		{
			this.controlList.add(new GuiColourButton(this.mc, nextId, 24, top + nextId * CUIConfigPanel.CONTROL_SPACING, 40, 20, colour));
			this.controlList.add(new GuiControl(this.mc, 100 + nextId, 234, top + nextId * CUIConfigPanel.CONTROL_SPACING, 60, 20, "Reset"));
			nextId++;
		}
		
		this.controlList.add(this.chkPromiscuous = new GuiCheckbox(nextId, 24, 26, I18n.format("gui.options.compat.spammy")));
		this.controlList.add(this.chkAlwaysOnTop = new GuiCheckbox(nextId, 24, 42, I18n.format("gui.options.compat.ontop")));
		
		for (GuiButton control : this.controlList)
		{
			if (control instanceof GuiColourButton)
				this.colourButtonList.add((GuiColourButton)control);
		}

		CUIConfiguration config = this.mod.getController().getConfiguration();
		this.chkPromiscuous.checked = config.isPromiscuous();
		this.chkAlwaysOnTop.checked = config.isAlwaysOnTop();
	}
	
	@Override
	public void onPanelResize(ConfigPanelHost host)
	{
	}
	
	@Override
	public void onPanelHidden()
	{
		for (GuiColourButton colourButton : this.colourButtonList)
		{
			colourButton.save();
		}
		
		CUIConfiguration config = this.mod.getController().getConfiguration();
		config.setPromiscuous(this.chkPromiscuous.checked);
		config.setAlwaysOnTop(this.chkAlwaysOnTop.checked);
		config.save();
	}
	
	@Override
	public void onTick(ConfigPanelHost host)
	{
	}
	
	@Override
	public void drawPanel(ConfigPanelHost host, int mouseX, int mouseY, float partialTicks)
	{
		this.drawString(this.mc.fontRendererObj, I18n.format("gui.options.compat.title"),  10, 10, 0xFFFFFF55);
		this.drawString(this.mc.fontRendererObj, I18n.format("gui.options.colours.title"), 10, 64, 0xFFFFFF55);
		
		for (GuiButton control : this.controlList)
		{
			control.drawButton(this.mc, mouseX, mouseY);
		}
		
		for (GuiColourButton colourButton : this.colourButtonList)
		{
			colourButton.drawPicker(this.mc, mouseX, mouseY);
		}
	}
	
	@Override
	public void mousePressed(ConfigPanelHost host, int mouseX, int mouseY, int mouseButton)
	{
		boolean makeActive = true;
		
		for (GuiButton control : this.controlList)
		{
			if (control.mousePressed(this.mc, mouseX, mouseY))
			{
				if (makeActive)
				{
					makeActive = false;
					this.activeControl = control;
					this.actionPerformed(control);
				}
			}
		}
	}
	
	private void actionPerformed(GuiButton control)
	{
		if (control instanceof GuiCheckbox)
		{
			GuiCheckbox chk = (GuiCheckbox)control;
			chk.checked = !chk.checked;
		}
		
		if (control.id >= 100)
		{
			LineColour lineColour = LineColour.values()[control.id - 100];
			lineColour.setDefaultColour();
			
			for (GuiColourButton colourButton : this.colourButtonList)
				colourButton.updateColour(lineColour);
		}
	}
	
	@Override
	public void mouseReleased(ConfigPanelHost host, int mouseX, int mouseY, int mouseButton)
	{
		if (this.activeControl != null)
		{
			this.activeControl.mouseReleased(mouseX, mouseY);
			this.activeControl = null;
		}
	}
	
	@Override
	public void mouseMoved(ConfigPanelHost host, int mouseX, int mouseY)
	{
	}
	
	@Override
	public void keyPressed(ConfigPanelHost host, char keyChar, int keyCode)
	{
		if (keyCode == Keyboard.KEY_ESCAPE)
		{
			host.close();
			return;
		}
		
		for (GuiColourButton colourButton : this.colourButtonList)
		{
			colourButton.keyTyped(keyChar, keyCode);
		}
	}
}
