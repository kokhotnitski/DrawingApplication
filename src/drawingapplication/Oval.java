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
//oval shape concrete class
public class Oval extends Shape{
    
    public Oval(int x1, int y1, int x2, int y2, Color color, Color fillColor){
        super (x1, y1, x2, y2, color, fillColor);
    }

    //here the actual shape gets drawn
    @Override
    public void draw(Graphics g, boolean newDrawing) {
        
        int upperLeftX = Math.min(getX1(), getX2());
        int upperLeftY = Math.min(getY1(), getY2());
        int width = Math.abs(getX1() - getX2());
        int height = Math.abs(getY1() - getY2());
        
        if(newDrawing == true){
            g.setColor(Color.BLACK);
        }else{
            g.setColor(getColor());
        }
        g.drawOval(upperLeftX, upperLeftY, width, height);
               
        
    }

    //here the shape get filled with the selected color
    @Override
    public void fill(Graphics g, boolean newShape) {
        int upperLeftX = Math.min(getX1(), getX2());
        int upperLeftY = Math.min(getY1(), getY2());
        int width = Math.abs(getX1() - getX2());
        int height = Math.abs(getY1() - getY2());
        
        if(newShape != true){
            g.setColor(getFillColor());
            g.fillOval(upperLeftX, upperLeftY, width, height);
        }
    }

    @Override
    public String getType() {
        return ("Oval");
    }
    
}
