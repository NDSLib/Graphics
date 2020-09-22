package com.ndsl.graphics.display.drawable.synced;

import com.ndsl.graphics.display.drawable.non_sync.StringDrawable;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class SyncedStringDrawable extends StringDrawable {
    public SyncedStringDrawable(String s, Rect r, Font f, String id) {
        super(s, r, f, id);
    }

    public SyncedStringDrawable(String s, Rect r, int size, String id) {
        super(s, r, size, id);
    }

    public SyncedStringDrawable(String s, Rect r, String id) {
        super(s, r, id);
    }

    public String getText() {
        return this.data;
    }

    public SyncedStringDrawable setText(String s) {
        this.data = s;
        return this;
    }

    public Rect getRect() {
        return this.r;
    }

    public SyncedStringDrawable setRect(Rect r) {
        this.r = r;
        return this;
    }
}
