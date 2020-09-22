package com.ndsl.graphics.display.drawable.animate;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.display.drawable.img.GImage;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class Animator implements IDrawable {
    public GImage[] images;
    public int CurrentImageIndex = 0;
    public GImage CurrentImage;
    public String id;
    public Rect rect;

    public Animator(String id, GImage... images) {
        if (!isAllSameSize(images)) throw new IllegalArgumentException("Not Match Image Size");
        this.id = id;
        this.images = images;
        this.CurrentImage = this.images[this.CurrentImageIndex];
        this.rect = this.images[this.CurrentImageIndex].size_rect;
    }

    public Animator(String id, int startIndex, GImage... images) {
        this(id, images);
        this.CurrentImageIndex = startIndex;
    }

    public Animator(String id, Pos left_up, GImage... images) {
        this(id, images);
        this.rect.shift(left_up.x, left_up.y);
    }

    public Animator(String id, int startIndex, Pos left_up, GImage... images) {
        this(id, startIndex, images);
        this.rect.shift(left_up.x, left_up.y);
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        next();
        g.drawImage(CurrentImage.getCachedImage(), showingRect.left_up.x, showingRect.left_up.y, showingRect.getWidth(), showingRect.getHeight(), null);
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

    private boolean isSameSize(GImage obj1, GImage obj2) {
        return obj1.getSize().equals(obj2.getSize());
    }

    private boolean isAllSameSize(GImage... objs) {
        for (int i = 0; i < objs.length - 1; i++) {
            if (!objs[i].getSize().equals(objs[i + 1].getSize())) return false;
        }
        return true;
    }

    public void next() {
        ++CurrentImageIndex;
        if (images.length - 1 < CurrentImageIndex) {
            CurrentImageIndex = 0;
        }
        this.CurrentImage = images[CurrentImageIndex];
    }
}
