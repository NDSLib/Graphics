package com.ndsl.graphics.display.scene;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.layer.Layer;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scene {
    public String id;
    public Scene(String id) {
        this.id=id;
        add(new Layer("default"),0);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Scene) {
            return ((Scene)obj).id==this.id;
        }
        return false;
    }


    public Map<Integer,Layer> layer_map=new HashMap<>();

    public Scene add(Layer layer,int id){
        layer_map.put(id,layer);
        return this;
    }

    public Scene remove(int id) {
        layer_map.remove(id);
        return this;
    }

    public Scene remove(String id){
        for(Map.Entry<Integer, Layer> entry:layer_map.entrySet()) {
            if(entry.getValue().id.equals(id)){
                remove(entry.getKey());
            }
        }

        System.out.println("[ERROR]Not Found in Layer:"+id);
        return this;
    }

    public boolean isExistDrawable(String id){
        for(Map.Entry<Integer, Layer> entry:layer_map.entrySet()) {
            for (Drawable drawable : entry.getValue().drawableList.toArray(new Drawable[0])) {
                if (drawable.getID() == null) continue;
                if (drawable.getID().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    public Drawable getDrawableWithID(String id){
        for(Map.Entry<Integer, Layer> entry:layer_map.entrySet()) {
            for (Drawable drawable : entry.getValue().drawableList.toArray(new Drawable[0])) {
                if (drawable.getID() == null) continue;
                if (drawable.getID().equals(id)) {
                    return drawable;
                }
            }
        }
        System.out.println("Not Found in Scene:"+id);
        return null;
    }

    /**
     * for Display
     */
    private void clearDisplay(Display display) {
        for(Layer layer : display.layerManager.layers.values()){
            display.layerManager.remove(layer.id);
//            layer.drawableList.clear();
        }
    }

    private void addAllToDisplay(Display display){
        for(Map.Entry<Integer, Layer> entry:layer_map.entrySet()) {
            display.layerManager.set(entry.getValue(),entry.getKey());
        }
    }

    public void copyToDisplay(Display display){
        clearDisplay(display);
        addAllToDisplay(display);
    }
}
