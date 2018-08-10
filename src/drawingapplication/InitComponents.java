/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingapplication;

import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
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
    private PaintPanel painterPaintJPanel;
    
    private String[] shapeTypes = {"Line", "Rectangle", "Oval"};
    
    
    public InitComponents(){
        
        //createPanel();
    }
    
    public Container createPanel(Dimension frameSize){
        
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        
                       
        Panel = new JPanel();
        Panel.setBounds(0, 0, frameSize.width, 40);
        Panel.setLayout(null);
        contentPane.add(Panel);
        
        painterPaintJPanel = new PaintPanel();
        painterPaintJPanel.setBounds(0, 40, frameSize.width, frameSize.height);
        painterPaintJPanel.setBackground( Color.WHITE);
        contentPane.add(painterPaintJPanel);
        
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
        
        colorJButton = new JButton();
        colorJButton.setBounds(200, 2, 80, 24);
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
        
        return contentPane;
        
    }
            
}
