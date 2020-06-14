package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public interface ICustomDrawable {
    void onDraw(Graphics g, Rect showingRect);
}
