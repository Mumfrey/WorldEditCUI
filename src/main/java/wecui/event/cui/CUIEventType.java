package wecui.event.cui;

/**
 * Event type enum for CUI events. Also stores class, arguments, and key for each value.
 * 
 * @author yetanotherx
 * 
 */
public enum CUIEventType {

    SELECTION(CUISelectionEvent.class, "s", 1),
    POINT(CUIPointEvent.class, "p", 5, 6),
    POINT2D(CUIPoint2DEvent.class, "p2", 4, 5),
    ELLIPSOID(CUIEllipsoidEvent.class, "e", 4),
    CYLINDER(CUICylinderEvent.class, "cyl", 5),
    MINMAX(CUIMinMaxEvent.class, "mm", 2),
    UPDATE(CUIUpdateEvent.class, "u", 1),
    POLYGON(CUIPolygonEvent.class, "poly", 3, 99);
    
    private final Class<? extends CUIBaseEvent> eventClass;
    private final String key;
    private final int min;
    private final int max;
    
    private CUIEventType(Class<? extends CUIBaseEvent> eventClass, String key, int min, int max) {
        this.eventClass = eventClass;
        this.key = key;
        this.min = min;
        this.max = max;
    }

    private CUIEventType(Class<? extends CUIBaseEvent> eventClass, String key, int paramCount) {
        this.eventClass = eventClass;
        this.key = key;
        this.min = paramCount;
        this.max = paramCount;
    }

    public Class<? extends CUIBaseEvent> getEventClass() {
        return this.eventClass;
    }

    public String getKey() {
        return this.key;
    }

    public int getMaxParameters() {
        return this.max;
    }

    public int getMinParameters() {
        return this.min;
    }

    public static CUIEventType getTypeFromKey(String key) {
        for (CUIEventType value : CUIEventType.values()) {
            if (value.getKey().equals(key)) {
                return value;
            }
        }
        return null;
    }
}
