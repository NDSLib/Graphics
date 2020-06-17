package com.ndsl.graphics.display.mouse;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.Drawable;
import com.ndsl.graphics.display.drawable.LineDrawable;
import com.ndsl.graphics.display.drawable.ui.MouseUIListener;
import com.ndsl.graphics.pos.Line;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MouseInputHandler implements MouseMotionListener,MouseListener {
    public Pos now_mouse_pos=new Pos(0,0);
    public Pos old_mouse_pos=new Pos(0,0);
    public boolean isClicking=false;
    public boolean isDoubleClicked=false;
    public int Current_Mouse_Button=0;

    public Display display;

    public MouseInputHandler(Display display){
        display.addMouseMotionListener(this);
        display.addMouseListener(this);
        this.display=display;
    }

    public Pos getPos(MouseEvent e){
        return new Pos(e.getX(),e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setDoubleClick(e);
        setNow_mouse_pos(e);
        register.hook(e,MouseEventType.Drug);
        isClicking=true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setDoubleClick(e);
        setNow_mouse_pos(e);
        register.hook(e,MouseEventType.Hover);
        isClicking=false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setDoubleClick(e);
        setNow_mouse_pos(e);
        setMouseButton(e);
        register.hook(e,MouseEventType.Click);
        isClicking=true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setDoubleClick(e);
        setNow_mouse_pos(e);
        setMouseButton(e);
        register.hook(e,MouseEventType.Click);
        isClicking=true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setDoubleClick(e);
        setNow_mouse_pos(e);
        setMouseButton(e);
        register.hook(e,MouseEventType.Hover);
        isClicking=false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setDoubleClick(e);
        setNow_mouse_pos(e);
        setMouseButton(e);
        register.hook(e,MouseEventType.Hover);
        isClicking=false;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setDoubleClick(e);
        setNow_mouse_pos(e);
        setMouseButton(e);
        register.hook(e,MouseEventType.Hover);
        isClicking=false;
    }

    private void setDoubleClick(MouseEvent event) {
        isDoubleClicked = event.getClickCount() >= 2;
    }

    private void setNow_mouse_pos(MouseEvent event){
        old_mouse_pos=now_mouse_pos;
        now_mouse_pos=getPos(event);
    }

    private void setMouseButton(MouseEvent e){
        Current_Mouse_Button=e.getButton();
    }

    public Pos getNow_mouse_pos(){
        return now_mouse_pos;
    }

    public void setDebugDrawable() {
        if(genDrawable()!=null) {
            display.addDrawable(genDrawable());
        }
    }

    @Nullable
    private Drawable genDrawable() {
        Color c;
        if(isClicking){
            switch (getButton()){
                case 1:
                    c=Color.RED;
                    break;
                case 2:
                    c=Color.BLUE;
                    break;
                case 3:
                    c=Color.GREEN;
                    break;
                case 4:
                    c=Color.MAGENTA;
                    break;
                case 5:
                    c=Color.ORANGE;
                    break;
                default:
                    c=Color.YELLOW;
                    break;
            }
        }else{
            c=Color.BLACK;
        }
        if(now_mouse_pos.x<=0 || now_mouse_pos.y<=0){
            return null;
        }
        return new Drawable(new LineDrawable(new Line(old_mouse_pos,now_mouse_pos),c),new Rect(old_mouse_pos,now_mouse_pos));
    }

    public int getButton(){
        if(!isClicking) return 0;
        return Current_Mouse_Button;
    }

    public register register=new register();
    public class register{
        private register(){}

        public ArrayList<MouseUIListener> listenerList=new ArrayList<>();

        public register add(MouseUIListener listener){
            listenerList.add(listener);
            return this;
        }

        public register remove(MouseUIListener listener){
            listenerList.remove(listener);
            return this;
        }

        public void hook(MouseEvent e,MouseEventType eventType){
            CustomMouseEvent event=genEvent(e,eventType);
            for(MouseUIListener listener:listenerList){
                switch (eventType){
                    case Drug:
                        listener.onDrug(event);
                        break;
                    case Click:
                        listener.onClick(event);
                        break;
                    case Hover:
                        listener.onHover(event);
                        break;
                    case DoubleClick:
                        listener.onDoubleClick(event);
                        break;
                }
            }
        }

        public CustomMouseEvent genEvent(MouseEvent event,MouseEventType type){
            return new CustomMouseEvent(event,MouseInputHandler.this,type);
        }
    }
}