package com.ndsl.graphics.display.key;

import java.awt.event.KeyEvent;

public interface KeyInputListener {
    void onTyped(KeyEvent e);
    void onPressed(KeyEvent e);
    void onReleased(KeyEvent e);
}
