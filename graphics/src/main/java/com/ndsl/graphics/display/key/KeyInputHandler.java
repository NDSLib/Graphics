package com.ndsl.graphics.display.key;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.DisplayAttitude;
import com.ndsl.graphics.display.drawable.GUIBase;
import com.ndsl.graphics.display.drawable.StringDrawable;
import com.ndsl.graphics.pos.Pos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyInputHandler implements KeyListener {
    public Display display;
    public DisplayAttitude attitude;
    public List<Integer> active_KeyList=new ArrayList<>();

    public KeyInputHandler(Display display){
        this.display=display;
        this.attitude=display.attitude;
        this.display.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        if(isHook()){
//            active_KeyList.add(e.getKeyCode());
//        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!active_KeyList.contains(e.getKeyCode())) active_KeyList.add(new Integer(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        active_KeyList.remove(new Integer(e.getKeyCode()));
    }

    public boolean isKeyPressing(int key_code){
        return active_KeyList.contains(key_code);
    }

    public boolean isHook(){
        return attitude.equals(DisplayAttitude.AlwaysUpdate) || display.hasFocus();
    }

    public void setDebugGUI(){
        display.addGui(new GUIBase(new StringDrawable(getActives()),new Pos(300,300),"keyinput_debug"));
    }

    public String getActives(){
        StringBuilder string= new StringBuilder();
        for (Integer integer : active_KeyList) {
            string.append(getKeyString(integer)).append("\n");
        }
        return string.toString();
    }

    public String getKeyString(int key_code){
        return KeyEvent.getKeyText(key_code);
    }
}
