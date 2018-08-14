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
//this is the draw shape component panel
public class PaintPanel extends JPanel {

    private final ArrayList shapesList = new ArrayList();
    private final ArrayList undoShapesList = new ArrayList();
    private final ArrayList eraseShapesList = new ArrayList();
    private final ArrayList changes = new ArrayList();
    private final ArrayList undoChanges = new ArrayList();
    private Shape shape;
    private String type = "Line";
    private Color color;
    private Color fillColor;
    private final JLabel statusTab;
    private Shape selectedShape;
    
    public boolean isSelectd = false;
    public boolean isCopied = false;
    private int undoCounter = 0;
    
    public PaintPanel() {

        MouseHandler moushandler = new MouseHandler();
        addMouseListener(moushandler);
        addMouseMotionListener(moushandler);
        statusTab = new JLabel("0, 0");
    }

    //this is the mouse event listener override methods
    private class MouseHandler implements MouseListener, MouseMotionListener {

        //this is the starting point where the shape start to draw
        @Override
        public void mousePressed(MouseEvent e) {
            panelMousedPressed(e);
        }

        //this is the mouse event where the shape gets drawn
        @Override
        public void mouseDragged(MouseEvent e) {
            statusTab.setText(String.format("%d, %d", e.getX(), e.getY()));
            panelMouseDragged(e);
            undoCounter = 0;
        }

        //this is the event where the shape get selected for fill
        @Override
        public void mouseClicked(MouseEvent e) {
            if(isCopied) {
                shapesList.remove(shapesList.size() - 1);
                changes.remove(changes.lastIndexOf("add"));
                //changesList();
                pasteShape(e.getX(), e.getY());
                return;
            }
            isSelectd = false;
            shapesList.remove(shapesList.size() - 1);
            changes.remove(changes.lastIndexOf("add"));
            //changesList();
            for ( int i = shapesList.size() - 1; i >= 0; i-- ) 
            { 
                Shape s = (Shape) shapesList.get(i);
                
                if (s.containsPoint(e.getX(),e.getY())) {
                    selectedShape = s;
                    isSelectd = true;
                    statusTab.setText(String.format("%d, %d Shape Selected", e.getX(), e.getY()));
                    return;
                }
                
            }
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            statusTab.setText(String.format("%d, %d", e.getX(), e.getY()));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(isSelectd){
                statusTab.setText(String.format("%d, %d Shape Selected", e.getX(), e.getY()));
            } else {
                statusTab.setText(String.format("%d, %d", e.getX(), e.getY()));
            }
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
    
    //this is where the shape method get called to fill the shape
    public void fillColor(Graphics g){

        selectedShape.setFillColor(fillColor);
        selectedShape.fill(g, false);
        repaint();
        
    }
    
    //this is where the method is called to paste a shape
    private void pasteShape(int x, int y) {
        
        
        int newX1, newY1, newX2, newY2, offsetX, offsetY;
        
        Math.abs((selectedShape.getX1() - selectedShape.getX2()) / 2);
        Math.abs((selectedShape.getY1() - selectedShape.getY2()) / 2);
        offsetX = Math.abs((selectedShape.getX1() - selectedShape.getX2()) / 2);
        offsetY = Math.abs((selectedShape.getY1() - selectedShape.getY2()) / 2);
        newX1 = x - offsetX;
        newX2 = x + offsetX;
        newY1 = y - offsetY;
        newY2 = y + offsetY;
        
        if (selectedShape.getType().equals("Line")) {
            shape = new Line(newX1, newY1, newX2, newY2, color);
        }
        
        if (selectedShape.getType().equals("Rectangle")) {
            fillColor = selectedShape.getFillColor();
            shape = new Rectangle(newX1, newY1, newX2, newY2, color, fillColor);
        }
        
        if (selectedShape.getType().equals("Oval")) {
            fillColor = selectedShape.getFillColor();
            shape = new Oval(newX1, newY1, newX2, newY2, color, fillColor);
        }
        
        shapesList.add(shape);
        changes.add("add");
        //changesList();
        isCopied = false;
        isSelectd = false;
        undoCounter = 0;
        repaint();
    }
    
    //this is where the method is called to copy a shape
    public void copyShape() {
        if(isSelectd){
            isCopied = true;
        }
    }
    
    //this is where the method is called to erase a shape
    public void eraseShape() {
        if(isSelectd){
            eraseShapesList.add(selectedShape);
            shapesList.remove(selectedShape);
            //shapeList();
            changes.add("erase");
            //changesList();
            isSelectd = false;
            undoCounter = 0;
            repaint();
        }
    }
    
    //this is the method called to copy your shape
    
    //this is the method called to redo you work shapes
    public void redoShape() {
        if(undoChanges.size() > 0 && undoCounter > 0){
            undoCounter--;
            int lastChange = (undoChanges.size() - 1);
            int lastShape = (undoShapesList.size() - 1);
            switch((String)undoChanges.get(lastChange)){
                case "add":
                    shapesList.add(undoShapesList.get(lastShape));
                    undoShapesList.remove(lastShape);
                    changes.add("add");
                    undoChanges.remove(lastChange);
                    break;
                case "erase":
                    eraseShapesList.add(undoShapesList.get(lastShape));
                    shapesList.remove(shapesList.get((shapesList.size()-1)));
                    undoShapesList.remove(lastShape);
                    changes.add("erase");
                    undoChanges.remove(lastChange);
                    break;
            }
            //changesList();
            repaint();
        }            
    }
    
    //this is the method called to undo you shape created
    public void undoShape() {
        if(changes.size() > 0){
            undoCounter++;
            int lastChange = (changes.size() - 1);
            switch((String)changes.get(lastChange)){
                case "add":
                    int lastShape = (shapesList.size() - 1);
                    undoShapesList.add(shapesList.get(lastShape));
                    shapesList.remove(lastShape);
                    undoChanges.add("add");
                    changes.remove(lastChange);
                    break;
                case "erase":
                    int lastErase = (eraseShapesList.size() - 1);
                    undoShapesList.add(eraseShapesList.get(lastErase));
                    shapesList.add(eraseShapesList.get(lastErase));
                    eraseShapesList.remove(lastErase);
                    undoChanges.add("erase");
                    changes.remove(lastChange);
                    break;
            }
            //changesList();
            repaint();
        }      
    }
    
    //this is the method where the new shape gets drawn
    public void panelMousedPressed(MouseEvent e) {

        if (type.equals("Line")) {
            shape = new Line(e.getX(), e.getY(), e.getX(), e.getY(), color);
        }
        
        if (type.equals("Rectangle")) {
            fillColor = null;
            shape = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), color, fillColor);
        }
        
        if (type.equals("Oval")) {
            fillColor = null;
            shape = new Oval(e.getX(), e.getY(), e.getX(), e.getY(), color, fillColor);
        }
            
        shapesList.add(shape);
        //shapeList();
        changes.add("add");
        //changesList();
    }

    //this is where the shape is drawn and filled on the panel vie the paint component method
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Shape nextShape;
        Iterator shapeIterator = shapesList.iterator();

        while (shapeIterator.hasNext()) {
            nextShape = (Shape) shapeIterator.next();
            
            if(nextShape.getColor() != null){
                nextShape.draw(g, false);
            }else{
                nextShape.draw(g, true);
            }
          
            if(nextShape.getFillColor() != null){
                nextShape.fill(g, false);
            }
            
        }
    }
    
    //here the x and y co-ordinates here set for the new shape
    public void panelMouseDragged(MouseEvent e) {
        shape.setX2(e.getX());
        shape.setY2(e.getY());
        repaint();
    }

/*    public void changesList() {
        for(int i = 0; i < changes.size(); i++) {
            System.out.print(changes.get(i) + " ");
        }
        System.out.println();
    }
    
    
    public void shapeList() {
        for(int i = 0; i < shapesList.size(); i++) {
            System.out.print(shapesList.get(i).toString() + " ");
        }
        System.out.println();
    }*/
}
