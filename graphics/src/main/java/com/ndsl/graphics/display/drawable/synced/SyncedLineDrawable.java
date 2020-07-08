package com.ndsl.graphics.display.drawable.synced;

import com.ndsl.graphics.display.drawable.non_sync.LineDrawable;
import com.ndsl.graphics.pos.Line;

import java.awt.*;

public class SyncedLineDrawable extends LineDrawable {
    public SyncedLineDrawable(Line line, String id) {
        super(line, id);
    }

    public SyncedLineDrawable(Line line, Color color, String id) {
        super(line, color, id);
    }

    public Line getLine(){
        return this.line;
    }

    public SyncedLineDrawable setLine(Line line){
        this.line=line;
        return this;
    }
}
