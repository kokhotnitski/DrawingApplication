/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingapplication;

import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author jeremye
 */
public class InitComponents extends JFrame {
    
    private JPanel Panel;
    private JComboBox  shapJComboBox;
    private JButton colorJButton;
    private JButton fillColorJButton;
    private JButton undoJButton;
    private JButton redoJButton;
    private JButton eraserJButton;
    private JButton copyJButton;
    private PaintPanel painterPaintJPanel;
    private JLabel statusTabJLabel;
    
    
    private String[] shapeTypes = {"Line", "Rectangle", "Oval"};
    
    public InitComponents(){
        
        //createPanel();
    }
    
    public Container createPanel(Dimension frameSize){
        
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        
        //here we create the panel to insert the buttons               
        Panel = new JPanel();
        Panel.setBounds(0, 0, frameSize.width, 40);
        Panel.setLayout(null);
        contentPane.add(Panel);
        
        //here we create the paint panel to draw on
        painterPaintJPanel = new PaintPanel();
        painterPaintJPanel.setBounds(0, 55, frameSize.width, 
                (frameSize.height - 40));
        painterPaintJPanel.setBackground(Color.WHITE);
        contentPane.add(painterPaintJPanel);
        
        //the status label that shows you where the mouse is
        statusTabJLabel = painterPaintJPanel.getStatusTab();
        statusTabJLabel.setBounds(0 , 40, 
                frameSize.width, 15);
        contentPane.add(statusTabJLabel);
        
        //the combobox that we use to select the sahpe you want to draw
        shapJComboBox = new JComboBox(shapeTypes);
        shapJComboBox.setBounds(90, 2, 100, 24);
        Panel.add(shapJComboBox);
        
        shapJComboBox.addActionListener(
                
            new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    
                    painterPaintJPanel.setShapeType((String)shapJComboBox.getSelectedItem());
                }

            }
        
        );
        
        //the button to select the color of the line draw
        colorJButton = new JButton();
        colorJButton.setBounds(200, 2, 110, 24);
        colorJButton.setText("Choose Color");
        Panel.add(colorJButton);
        
        colorJButton.addActionListener(
                
            new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    
                    Color selection = JColorChooser.showDialog(null, "select Color", Color.black);
                    colorJButton.setBackground(selection);
                    
                    painterPaintJPanel.setShapeColor(selection);
                    
                }

            }
        
        );
        
        //the button to select the color for the fill
        fillColorJButton = new JButton();
        fillColorJButton.setBounds(330, 2, 100, 24);
        fillColorJButton.setText("Fill Color");
        Panel.add(fillColorJButton);
        
        fillColorJButton.addActionListener(
                
            new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    
                    Color selection = JColorChooser.showDialog(null, "select Color", Color.WHITE);
                    fillColorJButton.setBackground(selection);
                    
                    painterPaintJPanel.setShapeFillColor(selection);
                    Graphics graphics = painterPaintJPanel.getGraphics();
                    painterPaintJPanel.fillColor(graphics);
                    painterPaintJPanel.update(graphics);
                        
                }

            }
        
        );
        
        //the undo button 
        undoJButton = new JButton();
        undoJButton.setBounds(440, 2, 70, 24);
        undoJButton.setText("Undo");
        Panel.add(undoJButton);
        
        undoJButton.addActionListener(
                
            new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    painterPaintJPanel.undoShape();
                }

            }
        
        );
        
        //the redo button that
        redoJButton = new JButton();
        redoJButton.setBounds(510, 2, 70, 24);
        redoJButton.setText("Redo");
        Panel.add(redoJButton);
        
        redoJButton.addActionListener(
                
            new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    painterPaintJPanel.redoShape();
                }

            }
        
        );
         
        //the erase button
        eraserJButton = new JButton();
        eraserJButton.setBounds(580, 2, 70, 24);
        eraserJButton.setText("Erase");
        Panel.add(eraserJButton);
        
        eraserJButton.addActionListener(
                
            new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    painterPaintJPanel.eraseShape();
                }
            }
        
        );
        
        //the copy button
        copyJButton = new JButton();
        copyJButton.setBounds(650, 2, 70, 24);
        copyJButton.setText("Copy");
        Panel.add(copyJButton);
        
        copyJButton.addActionListener(
                
            new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    painterPaintJPanel.copyShape();
                }
            }
        
        );
        
        return contentPane;
        
    }
            
}
