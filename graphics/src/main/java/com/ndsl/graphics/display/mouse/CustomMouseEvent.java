package com.ndsl.graphics.display.mouse;

import java.awt.event.MouseEvent;

public class CustomMouseEvent {
    public MouseEvent event;
    public MouseInputHandler inputHandler;
    public MouseEventType type;
    public CustomMouseEvent(MouseEvent event,MouseInputHandler inputHandler,MouseEventType type){
        this.event=event;
        this.inputHandler=inputHandler;
        this.type=type;
    }
}
