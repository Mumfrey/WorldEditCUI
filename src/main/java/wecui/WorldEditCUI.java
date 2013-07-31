package wecui;

import java.io.File;

import com.mumfrey.liteloader.core.LiteLoader;

import net.minecraft.src.Minecraft;
import wecui.config.CUIConfiguration;
import wecui.event.CUIEvent;
import wecui.event.ChannelEvent;
import wecui.event.ChatCommandEvent;
import wecui.event.WorldRenderEvent;
import wecui.event.listeners.CUIListener;
import wecui.event.listeners.ChannelListener;
import wecui.event.listeners.WorldEditCommandListener;
import wecui.event.listeners.WorldRenderListener;
import wecui.exception.InitializationException;
import wecui.fevents.EventManager;
import wecui.fevents.Order;
import wecui.render.region.BaseRegion;
import wecui.render.region.CuboidRegion;

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
public class WorldEditCUI {

    public static final String VERSION = "1.6.2";
    public static final String MCVERSION = "1.6.2";
    public static final int protocolVersion = 3;
    protected Minecraft minecraft;
    protected EventManager eventManager;
    protected BaseRegion selection;
    protected CUIDebug debugger;
    protected CUIConfiguration configuration;
    //protected LocalPlugin localPlugin;

    public WorldEditCUI(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public void initialize() {
        this.eventManager = new EventManager(this);
        this.selection = new CuboidRegion(this);
        this.configuration = new CUIConfiguration(this);
        this.debugger = new CUIDebug(this);
        //this.localPlugin = new LocalPlugin(this);

        try {
            this.eventManager.initialize();
            this.selection.initialize();
            this.configuration.initialize();
            this.debugger.initialize();
            //this.localPlugin.initialize();
        } catch (InitializationException e) {
            e.printStackTrace();
            return;
        }

        this.registerListeners();
    }

    protected void registerListeners() {
        CUIEvent.handlers.register(new CUIListener(this), Order.Default);
        ChannelEvent.handlers.register(new ChannelListener(this), Order.Default);
        WorldRenderEvent.handlers.register(new WorldRenderListener(this), Order.Default);

        WorldEditCommandListener commListener = new WorldEditCommandListener(this);
        ChatCommandEvent.getHandlers("worldedit").register(commListener, Order.Default);
        ChatCommandEvent.getHandlers("we").register(commListener, Order.Default);
    }

    public CUIConfiguration getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(CUIConfiguration configuration) {
        this.configuration = configuration;
    }

    public CUIDebug getDebugger() {
        return this.debugger;
    }

    public void setDebugger(CUIDebug debugger) {
        this.debugger = debugger;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /*public LocalPlugin getLocalPlugin() {
        return localPlugin;
    }

    public void setLocalPlugin(LocalPlugin localPlugin) {
        this.localPlugin = localPlugin;
    }*/

    public Minecraft getMinecraft() {
        return this.minecraft;
    }

    public void setMinecraft(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public BaseRegion getSelection() {
        return this.selection;
    }

    public void setSelection(BaseRegion selection) {
        this.selection = selection;
    }

    public static String getVersion() {
        return VERSION + " for Minecraft version " + MCVERSION;
    }

	public static File getWorldEditCUIDir()
	{
		return new File(LiteLoader.getInstance().getModsFolder(), "WorldEditCUI");
	}
}
