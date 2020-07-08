package com.ndsl.graphics.display.scene;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.layer.Layer;
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


    public List<Layer> layerList=new ArrayList<>();

    public Scene add(Layer layer){
        layerList.add(layer);
        return this;
    }

    public Scene remove(Layer layer) {
        layerList.remove(layer);
        return this;
    }

    public boolean isExistDrawable(String id){
        for(Layer layer:layerList) {
            for (Drawable drawable : layer.drawableList.toArray(new Drawable[0])) {
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
        for(Layer layer:layerList) {
            for (Drawable drawable : layer.drawableList.toArray(new Drawable[0])) {
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
            layer.drawableList.clear();
        }
    }

    private void addAllToDisplay(Display display){
        for(Layer layer:layerList) {
            display.addLayer(layer);
        }
    }

    public void copyToDisplay(Display display){
        clearDisplay(display);
        addAllToDisplay(display);
    }
}
