package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.GraphicsMain;
import com.ndsl.graphics.pos.Line;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class LineDrawable implements ICustomDrawable {
    public Line line;
    public Color color= GraphicsMain.Default_Color;

    public LineDrawable(Line line){
        this.line=line;
    }

    public LineDrawable(Line line,Color color){
        this.line=line;
        this.color=color;
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        g.setColor(color);
        g.drawLine(line.one.x,line.one.y,line.two.x,line.two.y);
    }
}
