/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingapplication;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jeremye
 */
public abstract class Shape implements shapeInterface {
    
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;
    private Color fillColor;
    
    public Shape(int x1, int y1, int x2, int y2, Color color){
        
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        setColor(color);
    }
    
    public Shape(int x1, int y1, int x2, int y2, Color color, Color fillColor){
        
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        setColor(color);
        setFillColor(fillColor);
    }
    
    public void setX1(int x){
        this.x1 = x;
    }
    public void setY1(int y){
        this.y1 = y;
    }
    public void setX2(int x2){
        this.x2 = x2;
    }
    public void setY2(int y2){
        this.y2 = y2;
    }
    
    public int getX1(){
        return x1;
    }
    public int getY1(){
        return y1;
    }
    public int getX2(){
        return x2;
    }
    public int getY2(){
        return y2;
    }
    
    @Override
    public boolean containsPoint(int x, int y) {
        
        if (x >= x1 && x < x1+x2 && y >= y1 && y < y1+y2)
            return true;
        else
            return false;
    }

    
    @Override
    public void setColor(Color c){
        color = c;
    }
    
    public void setFillColor(Color c){
        fillColor = c;
    }
    
    @Override
    public Color getColor(){
        return color;
    }
    
    public Color getFillColor(){
        return fillColor;
    }
    
    @Override
    public abstract void draw(Graphics g);
    
    @Override
    public abstract void fill(Graphics g);
}
