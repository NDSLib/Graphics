package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.pos.Rect;

import java.awt.*;
@Deprecated
public interface ICustomGui {
    void onDraw(Graphics g, Rect showingRect);
}
