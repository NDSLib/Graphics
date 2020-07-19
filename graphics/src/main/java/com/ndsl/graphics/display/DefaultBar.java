package com.ndsl.graphics.display;

import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class DefaultBar implements BorderBar {
    public Rect bar_rect;
    public Color c;
    public DefaultBar(BorderLessDisplay borderLessDisplay) {
        this.bar_rect=new Rect(0,0,borderLessDisplay.getWidth(),40);
        this.c=new Color(120, 120, 120, 255);
    }

    @Override
    public void onDraw(Graphics g) {
        g.setColor(c);
        g.fillRect(bar_rect.left_up.x,bar_rect.left_up.y,bar_rect.getWidth(),bar_rect.getHeight());
    }
}
