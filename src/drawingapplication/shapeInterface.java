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
public interface shapeInterface {
    public void setColor(Color c);
    public Color getColor();
    public abstract void draw(Graphics g);
}
