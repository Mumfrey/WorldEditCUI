package wecui.event.cui;

import wecui.WorldEditCUI;
import wecui.render.region.BaseRegion;
import wecui.render.region.CuboidRegion;
import wecui.render.region.CylinderRegion;
import wecui.render.region.EllipsoidRegion;
import wecui.render.region.PolygonRegion;
import wecui.render.region.PolyhedronRegion;

/**
 * Called when selection event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUISelectionEvent extends CUIBaseEvent {

    public CUISelectionEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public CUIEventType getEventType() {
        return CUIEventType.SELECTION;
    }

    @Override
    public String run() {

        BaseRegion newRegion = null;

        if (this.getString(0).equals("cuboid")) {
            newRegion = new CuboidRegion(this.controller);
        } else if (this.getString(0).equals("polygon2d")) {
            newRegion = new PolygonRegion(this.controller);
        } else if (this.getString(0).equals("ellipsoid")) {
            newRegion = new EllipsoidRegion(this.controller);
        } else if (this.getString(0).equals("cylinder")) {
            newRegion = new CylinderRegion(this.controller);
        } else if (this.getString(0).equals("polyhedron")) {
            newRegion = new PolyhedronRegion(this.controller);
        } else {
            return "Invalid selection type. Must be cuboid|polygon2d|ellipsoid|cylinder|polyhedron.";
        }

        this.controller.setSelection(newRegion);
        this.controller.getDebugger().debug("Received selection event, initalizing new region instance.");

        return null;
    }
}
