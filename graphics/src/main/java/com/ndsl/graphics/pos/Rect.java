package com.ndsl.graphics.pos;

import java.awt.geom.Rectangle2D;

public class Rect {
    public Pos left_up;
    public Pos right_down;

    public Rect(Pos left_up,Pos right_down){
        this.left_up=left_up;
        this.right_down=right_down;
    }

    public Rect(Pos pos){this(pos,pos);}

    public Rect(Line line){
        this(line.one,line.two);
    }

    public Rect(int l_u_x,int l_u_y,int r_u_x,int r_u_y){
        this(new Pos(l_u_x,l_u_y),new Pos(r_u_x,r_u_y));
    }

    public Rect(Rectangle2D rect){
        this(new Pos((int)rect.getX(),(int)rect.getY()),new Pos((int)rect.getX()+(int)rect.getWidth(),(int)rect.getY()+(int)rect.getHeight()));
    }

    public int getHeight(){
        return right_down.y - left_up.y;
    }

    public int getWidth(){
        return right_down.x - left_up.x;
    }

    public boolean contain(Rect displayShowingRect) {
        for(Pos pos:getAllPoint()){
            if (pos.contain(displayShowingRect)) return true;
        }
        for(Pos pos:displayShowingRect.getAllPoint()){
            if(pos.contain(this)) return true;
        }
        return false;
    }

    public boolean contain(Pos pos){
        return left_up.x<=pos.x && left_up.y<=pos.y && right_down.x>=pos.x && right_down.y >=pos.y;
    }

    public Pos[] getAllPoint(){
        Pos[] pos=new Pos[4];
        pos[0]=left_up;
        pos[1]=new Pos(left_up.x,right_down.y);
        pos[2]=new Pos(right_down.x,left_up.y);
        pos[3]=right_down;
        return pos;
    }

    public void zoom(double zoomScale) {
        this.left_up.x*=zoomScale;
        this.left_up.y*=zoomScale;
        this.right_down.x*=zoomScale;
        this.right_down.y*=zoomScale;
    }

    @Override
    public String toString() {
        return "{"+this.left_up.toString() + " " + this.right_down.toString()+"}";
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj)) return true;
        if(obj instanceof Rect){
            Rect r=((Rect)obj);
            if(left_up.equals(r.left_up) && right_down.equals(r.right_down)){
                return true;
            }
        }
        return false;
    }

    public Rect shift(int x,int y){
        this.left_up.shift(x,y);
        this.right_down.shift(x,y);
        return this;
    }
}
