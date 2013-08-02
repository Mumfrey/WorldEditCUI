package wecui.event.cui;

import wecui.WorldEditCUI;

/**
 * Called when point event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIPointEvent extends CUIBaseEvent {

    public CUIPointEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public CUIEventType getEventType() {
        return CUIEventType.POINT;
    }

    @Override
    public String run() {
        int id = this.getInt(0);
        double x = this.getDouble(1);
        double y = this.getDouble(2);
        double z = this.getDouble(3);

        this.controller.getSelection().setCuboidPoint(id, x, y, z);
        this.setLocalPoint(id, x, y, z);
        this.controller.getDebugger().debug("Setting point #" + id);

        return null;
    }

    protected void setLocalPoint(int id, double x, double y, double z) {
        /*if (controller.getLocalPlugin().isEnabled()) {

            WorldEdit plugin = controller.getLocalPlugin().getPlugin();
            CUIWorld world = controller.getLocalPlugin().getWorld();

            WorldVector clicked = new WorldVector(world, x, y, z);
            LocalSession session = plugin.getSession("player");
            RegionSelector selector = session.getRegionSelector(world);

            if (id == 0) {
                selector.selectPrimary(clicked);
            } else {
                selector.selectSecondary(clicked);
            }
        }*/
    }
}
