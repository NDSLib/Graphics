package com.ndsl.graphics.display.drawable.synced;

import com.ndsl.graphics.display.drawable.non_sync.RectDrawable;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class SyncedRectDrawable extends RectDrawable {
    public SyncedRectDrawable(Rect r, Color c, String id) {
        super(r, c, id);
    }

    public SyncedRectDrawable(Rect r, Color c, String id, boolean isFill) {
        super(r, c, id, isFill);
    }

    public Rect getRect() {
        return this.r;
    }

    public SyncedRectDrawable setRect(Rect rect) {
        this.r = rect;
        return this;
    }
}
