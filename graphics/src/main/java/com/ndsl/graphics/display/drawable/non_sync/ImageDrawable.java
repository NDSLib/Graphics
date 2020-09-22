package com.ndsl.graphics.display.drawable.non_sync;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class ImageDrawable implements IDrawable {
    public Image image;
    public Rect rect;
    public String id;

    public ImageDrawable(Image image, String id) {
        this(image, new Pos(0, 0), id);
    }

    public ImageDrawable(Image image, Pos pos, String id) {
        this.image = image;
        this.rect = new Rect(pos.x, pos.y, image.getWidth(null) + pos.x, image.getHeight(null) + pos.y);
        this.id = id;
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        g.drawImage(image, showingRect.left_up.x, showingRect.left_up.y, null);
    }

    @Override
    public Rect getShowingRect() {
        return rect;
    }

    @Override
    public boolean isShowing(Display display) {
        return display.isShowing(getShowingRect());
    }

    @Override
    public String getID() {
        return id;
    }
}
