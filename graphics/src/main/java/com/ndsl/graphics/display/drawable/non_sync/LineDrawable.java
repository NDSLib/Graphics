package com.ndsl.graphics.display.drawable.non_sync;

import com.ndsl.graphics.GraphicsMain;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.pos.Line;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class LineDrawable implements IDrawable {
    public Line line;
    public Color color = GraphicsMain.Default_Color;
    public Rect rect;
    public String id;

    public LineDrawable(Line line, String id) {
        this.line = line;
        rect = new Rect(line);
        this.id = id;
    }

    public LineDrawable(Line line, Color color, String id) {
        this(line, id);
        this.color = color;
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        g.setColor(color);
        g.drawLine(line.one.x, line.one.y, line.two.x, line.two.y);
    }

    @Override
    public Rect getShowingRect() {
        return rect;
    }

    @Override
    public boolean isShowing(Display display) {
        return display.isShowing(getShowingRect());
    }

    @Override
    public String getID() {
        return id;
    }
}
