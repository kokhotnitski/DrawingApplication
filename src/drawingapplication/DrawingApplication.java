/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingapplication;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kirill Okhotnitski
 */
public class DrawingApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Create and display the form.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DrawingApplication();
            }
        });  
    }
    
    public DrawingApplication()
    {
        JFrame application = new JFrame("Paint Program");
        MouseDrawer mouseDrawer = new MouseDrawer();
        application.add(mouseDrawer, BorderLayout.CENTER);
        
        JLabel statusTab = mouseDrawer.getStatusTab();
        application.add(statusTab, BorderLayout.SOUTH);
        
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setSize(500, 300);
        application.setVisible(true);
    }
}
