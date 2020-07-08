package com.ndsl.graphics.display.drawable.non_sync.ui;

import com.ndsl.graphics.pos.Rect;

import java.awt.*;
@Deprecated
public interface ICustomUI {
    void onDraw(Graphics g, Rect showingRect);
}
