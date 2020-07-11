package com.ndsl.graphics.display.util;

import java.awt.event.WindowEvent;

public interface ExitAttitude {
    /**
     * It will sent next ExitAttitude.
     * if all is done,It'll exit
     */
    void onClose(WindowEvent e);
}
