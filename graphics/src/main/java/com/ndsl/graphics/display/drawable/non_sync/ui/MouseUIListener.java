package com.ndsl.graphics.display.drawable.non_sync.ui;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.mouse.CustomMouseEvent;
import com.ndsl.graphics.pos.Rect;

public interface MouseUIListener {
    void onHover(CustomMouseEvent e);
    void onClick(CustomMouseEvent e);
    void onDrug(CustomMouseEvent e);
    void onDoubleClick(CustomMouseEvent e);
    void onRelease(CustomMouseEvent e);

    Rect getUIRect(Display display);
}
