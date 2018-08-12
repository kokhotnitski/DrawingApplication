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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jeremye
 */
public class PaintPanel extends JPanel {

    private final ArrayList shapesList = new ArrayList();
    private final ArrayList undoShapesList = new ArrayList();
    private Shape shape;
    private String type = "Line";
    private Color color;
    private Color fillColor;
    private final JLabel statusTab;
    private Shape selectedShape;
    
    
    public PaintPanel() {

        MouseHandler moushandler = new MouseHandler();
        addMouseListener(moushandler);
        addMouseMotionListener(moushandler);
        statusTab = new JLabel("0, 0");
    }

    private class MouseHandler implements MouseListener, MouseMotionListener {

        @Override
        public void mousePressed(MouseEvent e) {
            panelMousedPressed(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            statusTab.setText(String.format("%d, %d", e.getX(), e.getY()));
            panelMouseDragged(e);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            
            shapesList.remove(shapesList.size() - 1);
            for ( int i = shapesList.size() - 1; i >= 0; i-- ) 
            { 
                Shape s = (Shape) shapesList.get(i);
                
                if (s.containsPoint(e.getX(),e.getY())) {
                    selectedShape = s;
                    return;
                }
            }
            
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
            statusTab.setText(String.format("%d, %d", e.getX(), e.getY()));
        }

    }
    
    public JLabel getStatusTab() {
        return statusTab;
    }
    
    public void setShapeType(String shapeType) {
        type = shapeType;

    }

    public void setShapeColor(Color shapeColor) {
        color = shapeColor;

    }
    
    public void setShapeFillColor(Color shapeFillColor) {
        fillColor = shapeFillColor;

    }
    
    public void fillColor(Graphics g){

        selectedShape.setFillColor(fillColor);
        selectedShape.fill(g);
        
    }
    
    public void redoShape() {
        if(undoShapesList.size() > 0){
            int lastShape = (undoShapesList.size() - 1);
            shapesList.add(undoShapesList.get(lastShape));
            undoShapesList.remove((lastShape));
            repaint();
        }      
    }
    
    public void undoShape() {
        if(shapesList.size() > 0){
            int lastShape = (shapesList.size() - 1);
            undoShapesList.add(shapesList.get(lastShape));
            shapesList.remove((lastShape));
            repaint();
        }      
    }
    
    public void panelMousedPressed(MouseEvent e) {

        if (type.equals("Line")) {
            shape = new Line(e.getX(), e.getY(), e.getX(), e.getY(), color);
        }
        
        if (type.equals("Rectangle")) {
            shape = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), color, fillColor);
        }
        
        if (type.equals("Oval")) {
            shape = new Oval(e.getX(), e.getY(), e.getX(), e.getY(), color, fillColor);
        }
            
        shapesList.add(shape);
        if(!undoShapesList.isEmpty()){
            undoShapesList.clear();
        }
        
    }

    @Override
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
