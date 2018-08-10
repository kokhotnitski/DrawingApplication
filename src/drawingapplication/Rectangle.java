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
public class Rectangle extends Shape {
    
    public Rectangle(int x1, int y1, int x2, int y2, Color color){
        super (x1, y1, x2, y2, color);
        
    }

    @Override
    public void draw(Graphics g) {
        
        int upperLeftX = Math.min(getX1(), getX2());
        int upperLeftY = Math.min(getY1(), getY2());
        int width = Math.abs(getX1() - getX2());
        int height = Math.abs(getY1() - getY2());
        
        g.setColor(getColor());
        g.drawRect(upperLeftX, upperLeftY, width, height);
        
        
    }

    @Override
    public void fill(Graphics g) {
        int upperLeftX = Math.min(getX1(), getX2());
        int upperLeftY = Math.min(getY1(), getY2());
        int width = Math.abs(getX1() - getX2());
        int height = Math.abs(getY1() - getY2());
        
        g.setColor(getColor());
        g.fillRect(upperLeftX, upperLeftY, width, height);
    }
    
}
