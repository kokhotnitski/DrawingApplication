/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingapplication;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author jeremye
 */
public class PaintPanel extends JPanel {

    private ArrayList shapesList = new ArrayList();
    private Shape shape;
    private String type = "Line";
    private Color color = new Color(204, 204, 204);

    public PaintPanel() {

        MouseHandler moushandler = new MouseHandler();
        addMouseListener(moushandler);
        addMouseMotionListener(moushandler);
    }

    private class MouseHandler implements MouseListener, MouseMotionListener {

        @Override
        public void mousePressed(MouseEvent e) {
            panelMousedPressed(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            panelMouseDragged(e);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

    }
    
    public void setShapeType(String shapeType) {
        type = shapeType;

    }

    public void setShapeColor(Color shapeColor) {
        color = shapeColor;

    }

    public void panelMousedPressed(MouseEvent e) {

        if (type.equals("Line")) {
            shape = new Line(e.getX(), e.getY(), e.getX(), e.getY(), color);
        }
        
        if (type.equals("Rectangle")) {
            shape = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), color);
        }
        
        if (type.equals("Oval")) {
            shape = new Oval(e.getX(), e.getY(), e.getX(), e.getY(), color);
        }

        shapesList.add(shape);

    }

    

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Shape nextShape;
        Iterator shapeIterator = shapesList.iterator();

        while (shapeIterator.hasNext()) {
            nextShape = (Shape) shapeIterator.next();
            nextShape.draw(g);
        }

    }

    public void panelMouseDragged(MouseEvent e) {
        shape.setX2(e.getX());
        shape.setY2(e.getY());
        repaint();
    }

}
