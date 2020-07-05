package com.ndsl.graphics.display.scene;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.Drawable;
import com.ndsl.graphics.display.drawable.GUIBase;
import com.ndsl.graphics.display.drawable.RealTimeDrawable;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    public String id;
    public Scene(String id) {
        this.id=id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Scene) {
            return ((Scene)obj).id==this.id;
        }
        return false;
    }


    public List<Drawable> drawableList=new ArrayList<>();

    public Scene add(Object o){
        if(o instanceof Drawable){
            drawableList.add((Drawable)o);
        }
        return this;
    }

    public Scene remove(Object o) {
        if(o instanceof Drawable){
            drawableList.remove((Drawable)o);
        }
        return this;
    }

    public boolean isExistDrawable(String id){
        for(Drawable drawable:drawableList){
            if(drawable.getID()==null) continue;
            if(drawable.getID().equals(id)){
                return true;
            }
        }
        return false;
    }

    @Nullable
    public Drawable getDrawableWithID(String id){
        for(Drawable drawable:drawableList){
            if(drawable.getID()==null) continue;
            if(drawable.getID().equals(id)){
                return drawable;
            }
        }
        System.out.println("Not Found in Scene:"+id);
        return null;
    }

    /**
     * for Display
     */
    private void clearDisplay(Display display) {
        display.drawableList.clear();
    }

    private void addAllToDisplay(Display display){
        for(Drawable draw:drawableList){
            display.addDrawable(draw);
        }
    }

    public void copyToDisplay(Display display){
        clearDisplay(display);
        addAllToDisplay(display);
    }
}
