package com.ndsl.graphics.display.mouse;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.Drawable;
import com.ndsl.graphics.display.drawable.LineDrawable;
import com.ndsl.graphics.display.drawable.PointDrawable;
import com.ndsl.graphics.pos.Line;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputHandler implements MouseMotionListener,MouseListener {
    public Pos now_mouse_pos=new Pos(0,0);
    public Pos old_mouse_pos=new Pos(0,0);
    public boolean isClicking=false;
    public boolean isDoubleClicked=false;

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
        isClicking=true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setDoubleClick(e);
        setNow_mouse_pos(e);
        isClicking=false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setDoubleClick(e);
        setNow_mouse_pos(e);
        isClicking=true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setDoubleClick(e);
        setNow_mouse_pos(e);
        isClicking=true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setDoubleClick(e);
        setNow_mouse_pos(e);
        isClicking=false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setDoubleClick(e);
        setNow_mouse_pos(e);
        isClicking=false;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setDoubleClick(e);
        setNow_mouse_pos(e);
        isClicking=false;
    }

    private void setDoubleClick(MouseEvent event) {
        isDoubleClicked = event.getClickCount() >= 2;
    }

    private void setNow_mouse_pos(MouseEvent event){
        old_mouse_pos=now_mouse_pos;
        now_mouse_pos=getPos(event);
    }

    public Pos getNow_mouse_pos(){
        return now_mouse_pos;
    }

    public void setDebugDrawable() {
        if(genDrawable()!=null) {
            display.addDrawable(genDrawable());
        }
    }

    private Drawable genDrawable() {
        Color c;
        if(isClicking){
            c=Color.RED;
        }else{
            c=Color.BLACK;
        }
        if(now_mouse_pos.x<=0 || now_mouse_pos.y<=0){
            return null;
        }
        return new Drawable(new LineDrawable(new Line(old_mouse_pos,now_mouse_pos),c),new Rect(old_mouse_pos,now_mouse_pos));
    }
}
