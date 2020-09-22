package com.ndsl.graphics.display.drawable.synced;

import com.ndsl.graphics.display.drawable.non_sync.ImageDrawable;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class SyncedImageDrawable extends ImageDrawable {

    public SyncedImageDrawable(Image image, String id) {
        super(image, id);
    }

    public SyncedImageDrawable(Image image, Pos pos, String id) {
        super(image, pos, id);
    }

    public SyncedImageDrawable setImage(Image img) {
        this.image = img;
        this.rect = new Rect(rect.left_up.x, rect.left_up.y, image.getWidth(null) + rect.left_up.x, image.getHeight(null) + rect.left_up.y);
        return this;
    }
}
