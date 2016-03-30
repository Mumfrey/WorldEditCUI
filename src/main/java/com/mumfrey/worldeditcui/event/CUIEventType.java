package com.mumfrey.worldeditcui.event;

import com.mumfrey.worldeditcui.event.cui.CUIEventBounds;
import com.mumfrey.worldeditcui.event.cui.CUIEventCylinder;
import com.mumfrey.worldeditcui.event.cui.CUIEventEllipsoid;
import com.mumfrey.worldeditcui.event.cui.CUIEventPoint2D;
import com.mumfrey.worldeditcui.event.cui.CUIEventPoint3D;
import com.mumfrey.worldeditcui.event.cui.CUIEventPolygon;
import com.mumfrey.worldeditcui.event.cui.CUIEventSelection;
import com.mumfrey.worldeditcui.event.cui.CUIEventUpdate;

/**
 * Event type enum for CUI events. Also stores class, arguments, and key for each value.
 * 
 * @author yetanotherx
 * 
 */
public enum CUIEventType
{
	SELECTION(CUIEventSelection.class, "s",    1    ),
	POINT    (CUIEventPoint3D.class,   "p",    5, 6 ),
	POINT2D  (CUIEventPoint2D.class,   "p2",   4, 5 ),
	ELLIPSOID(CUIEventEllipsoid.class, "e",    4    ),
	CYLINDER (CUIEventCylinder.class,  "cyl",  5    ),
	MINMAX   (CUIEventBounds.class,    "mm",   2    ),
	UPDATE   (CUIEventUpdate.class,    "u",    1    ),
	POLYGON  (CUIEventPolygon.class,   "poly", 3, 99);
	
	private final Class<? extends CUIEvent> eventClass;
	private final String key;
	private final String name;
	private final int minParams;
	private final int maxParams;
	
	private CUIEventType(Class<? extends CUIEvent> eventClass, String key, int minParams, int maxParams)
	{
		this.eventClass = eventClass;
		this.key = key;
		this.name = eventClass.getSimpleName().substring(8);
		this.minParams = minParams;
		this.maxParams = maxParams;
	}
	
	private CUIEventType(Class<? extends CUIEvent> eventClass, String key, int paramCount)
	{
		this.eventClass = eventClass;
		this.key = key;
		this.name = eventClass.getSimpleName().substring(8);
		this.minParams = paramCount;
		this.maxParams = paramCount;
	}
	
	public Class<? extends CUIEvent> getEventClass()
	{
		return this.eventClass;
	}
	
	public String getKey()
	{
		return this.key;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getMaxParameters()
	{
		return this.maxParams;
	}
	
	public int getMinParameters()
	{
		return this.minParams;
	}
}
