package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public interface IRealTimeCustomDrawable {
    void onDraw(Graphics g, Rect showingRect);
    Rect getShowingRect();
}
