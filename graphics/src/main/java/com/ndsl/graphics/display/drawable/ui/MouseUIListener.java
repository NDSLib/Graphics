package com.ndsl.graphics.display.drawable.ui;

import com.ndsl.graphics.display.mouse.CustomMouseEvent;
import com.ndsl.graphics.pos.Pos;

public interface MouseUIListener {
    void onHover(CustomMouseEvent e);
    void onClick(CustomMouseEvent e);
    void onDrug(CustomMouseEvent e);
    void onDoubleClick(CustomMouseEvent e);
}
