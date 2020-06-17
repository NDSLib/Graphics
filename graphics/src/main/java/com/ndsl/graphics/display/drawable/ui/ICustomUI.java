package com.ndsl.graphics.display.drawable.ui;

import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public interface ICustomUI {
    void onDraw(Graphics g, Rect showingRect);
}
