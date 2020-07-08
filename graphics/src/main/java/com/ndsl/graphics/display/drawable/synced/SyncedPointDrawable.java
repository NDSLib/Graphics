package com.ndsl.graphics.display.drawable.synced;

import com.ndsl.graphics.display.drawable.non_sync.PointDrawable;
import com.ndsl.graphics.pos.Pos;

import java.awt.*;

public class SyncedPointDrawable extends PointDrawable {
    public SyncedPointDrawable(Pos pos, Color color, String id) {
        super(pos, color, id);
    }

    public SyncedPointDrawable(Pos pos, String id) {
        super(pos, id);
    }

    public Pos getPos(){
        return this.pos;
    }

    public SyncedPointDrawable setPos(Pos pos){this.pos=pos;return this;}
}
