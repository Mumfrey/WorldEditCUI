package wecui.event.cui;

import wecui.WorldEditCUI;

/**
 * Called when polygon event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIPolygonEvent extends CUIBaseEvent {

    public CUIPolygonEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public CUIEventType getEventType() {
        return CUIEventType.POLYGON;
    }

    @Override
    public String run() {
        final int[] vertexIds = new int[args.length];
        for (int i = 0; i < args.length; ++i) {
            vertexIds[i] = this.getInt(i);
        }

        this.controller.getSelection().addPolygon(vertexIds);
        //this.controller.getDebugger().debug("Setting point #" + id);

        return null;
    }
}
