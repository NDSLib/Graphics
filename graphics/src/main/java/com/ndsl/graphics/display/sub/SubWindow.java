package com.ndsl.graphics.display.sub;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.NotNull;


public class SubWindow extends Display {
    public SubWindow(Display display){
        this(display.getTitle(),display.bufferSize,new Rect(display.getBounds()));
    }

    private SubWindow(String title, int bufferSize, @NotNull Rect displayBound) {
        super(title, bufferSize, displayBound);
    }

    public SubWindow(String title,Display display) {
        super(title,display.bufferSize,new Rect(display.getBounds()));
    }

    /**
     * If use this,You must init yourself.
     * like above.
     */
    protected SubWindow(){

    }
}
