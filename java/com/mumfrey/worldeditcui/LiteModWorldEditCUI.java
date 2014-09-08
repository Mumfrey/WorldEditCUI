package com.mumfrey.worldeditcui;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.server.S01PacketJoinGame;

import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.InitCompleteListener;
import com.mumfrey.liteloader.PluginChannelListener;
import com.mumfrey.liteloader.PostRenderListener;
import com.mumfrey.liteloader.core.ClientPluginChannels;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.core.PluginChannels.ChannelPolicy;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.worldeditcui.event.listeners.CUIListenerChannel;
import com.mumfrey.worldeditcui.event.listeners.CUIListenerWorldRender;
import com.mumfrey.worldeditcui.gui.CUIConfigPanel;
import com.mumfrey.worldeditcui.render.region.CuboidRegion;

public class LiteModWorldEditCUI implements InitCompleteListener, PluginChannelListener, PostRenderListener, Configurable
{
	private static final String CHANNEL_WECUI = "WECUI";
	private final static Charset UTF_8_CHARSET = Charset.forName("UTF-8");
	
	private WorldEditCUI controller;
	private WorldClient lastWorld;
	private EntityPlayerSP lastPlayer;
	
	private KeyBinding keyBindToggleUI = new KeyBinding("wecui.keys.toggle", Keyboard.KEY_NONE, "wecui.keys.category");
	private KeyBinding keyBindClearSel = new KeyBinding("wecui.keys.clear", Keyboard.KEY_NONE, "wecui.keys.category");
	
	private boolean visible = true;
	
	private CUIListenerWorldRender worldRenderListener;
	private CUIListenerChannel channelListener;
	
	@Override
	public void init(File configPath)
	{
		LiteLoader.getInput().registerKeyBinding(this.keyBindToggleUI);
		LiteLoader.getInput().registerKeyBinding(this.keyBindClearSel);
	}
	
	@Override
	public void upgradeSettings(String version, File configPath, File oldConfigPath)
	{
	}
	
	/* (non-Javadoc)
	 * @see com.mumfrey.liteloader.InitCompleteListener#onInitCompleted(net.minecraft.client.Minecraft, com.mumfrey.liteloader.core.LiteLoader)
	 */
	@Override
	public void onInitCompleted(Minecraft minecraft, LiteLoader loader)
	{
		this.controller = new WorldEditCUI();
		this.controller.initialize();
		
		this.worldRenderListener = new CUIListenerWorldRender(this.controller, minecraft);
		this.channelListener = new CUIListenerChannel(this.controller);
	}
	
	@Override
	public void onJoinGame(INetHandler netHandler, S01PacketJoinGame loginPacket)
	{
		this.visible = true;
		this.controller.getDebugger().debug("Joined game, sending initial handshake");
		this.hello();
	}
	
	/**
	 * 
	 */
	private void hello()
	{
		byte[] buffer = ("v|" + WorldEditCUI.protocolVersion).getBytes(UTF_8_CHARSET);
		ClientPluginChannels.sendMessage(CHANNEL_WECUI, buffer, ChannelPolicy.DISPATCH_ALWAYS);
	}
	
	@Override
	public List<String> getChannels()
	{
		return Arrays.asList(new String[] { CHANNEL_WECUI });
	}
	
	@Override
	public void onCustomPayload(String channel, int length, byte[] data)
	{
		try
		{
			String payload = new String(data, LiteModWorldEditCUI.UTF_8_CHARSET);
			this.channelListener.onMessage(payload);
		}
		catch (Exception ex) {}
	}
	
	@Override
	public void onTick(Minecraft mc, float partialTicks, boolean inGame, boolean clock)
	{
		if (inGame && mc.currentScreen == null)
		{
			if (this.keyBindToggleUI.isPressed())
			{
				this.visible = !this.visible;
			}
			
			if (this.keyBindClearSel.isPressed())
			{
				if (mc.thePlayer != null)
					mc.thePlayer.sendChatMessage("//sel");
			}
		}
		
		if (inGame && clock && this.controller != null)
		{
			if (mc.theWorld != this.lastWorld || mc.thePlayer != this.lastPlayer)
			{
				this.lastWorld = mc.theWorld;
				this.lastPlayer = mc.thePlayer;
				
				this.controller.getDebugger().debug("World change detected, sending new handshake");
				this.controller.setSelection(new CuboidRegion(this.controller));
				this.helo();
				if (mc.thePlayer != null) mc.thePlayer.sendChatMessage("/we cui"); //Tricks WE to send the current selection
			}
		}
	}
	
	@Override
	public String getName()
	{
		return "WorldEditCUI";
	}
	
	@Override
	public String getVersion()
	{
		return "1.7.10_00";
	}
	
	@Override
	public Class<? extends ConfigPanel> getConfigPanelClass()
	{
		return CUIConfigPanel.class;
	}
	
	@Override
	public void onPostRenderEntities(float partialTicks)
	{
		if (this.visible)
		{
			try
			{
				this.worldRenderListener.onRender(partialTicks);
			}
			catch (Exception ex) {}
		}
	}
	
	@Override
	public void onPostRender(float partialTicks)
	{
	}
	
	public WorldEditCUI getController()
	{
		return this.controller;
	}
}
