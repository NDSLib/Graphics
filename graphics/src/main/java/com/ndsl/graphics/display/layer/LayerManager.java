package com.ndsl.graphics.display.layer;

import org.jetbrains.annotations.Nullable;

import java.util.*;

public class LayerManager {
    public static LayerManager INSTANCE = new LayerManager();

    public LayerManager() {
        set(new Layer("default"), 0);
    }

    public Map<Integer, Layer> layers = new HashMap<>();

    public LayerManager set(Layer layer, Integer id) {
        layers.put(id, layer);
        sort();
        return this;
    }

    @Nullable
    public Layer get(String layer_id) {
        for (Layer layer : layers.values()) {
            if (layer.id.equals(layer_id)) return layer;
        }
        return null;
    }

    public Layer[] getAll() {
        return layers.values().toArray(new Layer[0]);
    }

    public Layer get(Integer id) {
        return layers.get(id);
    }

    public Integer getLatest() {
        return layers.keySet().toArray(new Integer[0])[layers.size() - 1] + 1;
    }

    public void sort() {
        List<Map.Entry<Integer, Layer>> list = new ArrayList<>(layers.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Layer>>() {
            public int compare(Map.Entry<Integer, Layer> obj1, Map.Entry<Integer, Layer> obj2) {
                return obj1.getKey() - obj2.getKey();
            }
        });
        Map<Integer, Layer> layer = new HashMap<>();
        for (Map.Entry<Integer, Layer> entry : list) {
            layer.put(entry.getKey(), entry.getValue());
        }
        this.layers = layer;
    }

    public void remove(int id) {
        layers.remove(id);
    }

    public void remove(String id) {
        for (Map.Entry<Integer, Layer> entry : layers.entrySet()) {
            if (entry.getValue().id.equals(id)) {
                remove(entry.getKey());
                return;
            }
        }

        System.out.println("[ERROR]Not Found Layer:" + id);
    }
}