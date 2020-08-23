package com.ndsl.graphics.display.key;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.DisplayAttitude;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.drawable.base.GUIBase;
import com.ndsl.graphics.display.drawable.non_sync.StringDrawable;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyInputHandler implements KeyListener {
    public Display display;
    public DisplayAttitude attitude;
    public List<Integer> active_KeyList=new ArrayList<>();
    public List<KeyInputListener> typed_listeners=new ArrayList<>();

    public KeyInputHandler(Display display){
        this.display=display;
        this.attitude=display.attitude;
        this.display.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for(KeyInputListener l:typed_listeners){l.onTyped(e);}
    }

    @SuppressWarnings({"UnnecessaryBoxing", "CachedNumberConstructorCall"})
    @Override
    public void keyPressed(KeyEvent e) {
        for(KeyInputListener l:typed_listeners){l.onPressed(e);}
        if(!active_KeyList.contains(e.getKeyCode())) active_KeyList.add(new Integer(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(KeyInputListener l:typed_listeners){l.onReleased(e);}
        //noinspection CachedNumberConstructorCall
        active_KeyList.remove(new Integer(e.getKeyCode()));
    }

    public boolean isKeyPressing(int key_code){
        return active_KeyList.contains(key_code);
    }

    public boolean isHook(){
        return attitude.equals(DisplayAttitude.AlwaysUpdate) || display.hasFocus();
    }

    public void setDebugGUI(){
        display.addDrawable(new Drawable(new GUIBase(new StringDrawable(getActives(),new Rect(new Pos(300,300)),"keyinput_debug"))));
    }

    public String getActives(){
        StringBuilder string= new StringBuilder();
        for (Integer integer : active_KeyList.toArray(new Integer[0])) {
            string.append(getKeyString(integer)).append("\n");
        }
        return string.toString();
    }

    public String getKeyString(int key_code){
        return KeyEvent.getKeyText(key_code);
    }
}
