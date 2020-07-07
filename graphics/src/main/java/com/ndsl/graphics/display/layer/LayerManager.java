package com.ndsl.graphics.display.layer;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LayerManager {
    public static LayerManager INSTANCE=new LayerManager();

    public LayerManager(){
        set(new Layer("default"),0);
    }

    public Map<Integer,Layer> layers = new HashMap<>();

    public LayerManager set(Layer layer,Integer id) {
        layers.put(id,layer);
        return this;
    }

    @Nullable
    public Layer get(String layer_id) {
        for(Layer layer : layers.values()){
            if(layer.id.equals(layer_id)) return layer;
        }
        return null;
    }

    public Layer[] getAll(){
        return layers.values().toArray(new Layer[0]);
    }

    public Layer get(Integer id){
        return layers.get(id);
    }

    public Integer getLatest(){
        return layers.keySet().toArray(new Integer[0])[layers.size()-1]+1;
    }
}