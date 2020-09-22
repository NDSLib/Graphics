package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public interface IDrawable {
    void onDraw(Graphics g, Rect showingRect);

    Rect getShowingRect();

    boolean isShowing(Display display);

    String getID();
}
