/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingapplication;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kirill Okhotnitski
 */
//this is the free draw component panel
public class MouseDrawer extends JPanel {
    private final JLabel statusTab;
    
    private final ArrayList<Point> points = new ArrayList();
    
    public MouseDrawer() {
        setBackground(Color.WHITE);
        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
        addMouseMotionListener(handler);
        statusTab = new JLabel("Mouse outside of the canvas.");
    }
    
    public JLabel getStatusTab() {
        return statusTab;
    }
    
    //here the mouse pointer get painted onto the paint panel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Point point : points)
            g.fillOval(point.x, point.y, 4, 4);
    }
    
    //this is the mouse event listener override methods
    private class MouseHandler implements MouseListener, MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent event) {
        }
        
        //this is the event that draws the line as you drag your mouse
        @Override
        public void mouseDragged(MouseEvent event) {
            statusTab.setText(String.format("Moved to %d, %d.",
                    event.getX(), event.getY()));
            points.add(event.getPoint());
            repaint();
        }
        @Override
        public void mouseEntered(MouseEvent event) {
        }
        @Override
        public void mouseExited(MouseEvent event) {
            statusTab.setText("Mouse outside of the canvas.");
        }
        
        @Override
        public void mouseMoved(MouseEvent event) {
            statusTab.setText(String.format("Moved to %d, %d.",
                    event.getX(), event.getY()));
        }
        
        //this is where the intial point is set to start drawing
        @Override
        public void mousePressed(MouseEvent event) {
            points.add(event.getPoint());
            repaint();
        }
        @Override
        public void mouseReleased(MouseEvent event) {
        }
    }
}
