/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingapplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kirill Okhotnitski
 */
public class DrawingApplication {

    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JMenuItem drawingMenuItem;
    private javax.swing.JMenuItem freeDraeingMenuItem;

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

    public DrawingApplication() {

        mainMenu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        drawingMenuItem = new javax.swing.JMenuItem();
        freeDraeingMenuItem = new javax.swing.JMenuItem();
        
        JFrame application = new JFrame("Paint Program");
        Dimension frameSize = application.getSize();

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");

        drawingMenuItem.setMnemonic('A');
        drawingMenuItem.setText("Drawing");
        drawingMenuItem.setToolTipText("Drawing");
        drawingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                
                InitComponents components = new InitComponents();
                application.setContentPane(components.createPanel(application.getSize()));
                application.revalidate();
                
            }
        });
        fileMenu.add(drawingMenuItem);

        freeDraeingMenuItem.setMnemonic('E');
        freeDraeingMenuItem.setText("free Drawing");
        freeDraeingMenuItem.setToolTipText("free Drawing");
        freeDraeingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                MouseDrawer mouseDrawer = new MouseDrawer();
                //application.add(mouseDrawer, BorderLayout.CENTER);
                application.setContentPane(mouseDrawer);

                JLabel statusTab = mouseDrawer.getStatusTab();
                application.add(statusTab, BorderLayout.SOUTH);
                application.revalidate();
                
            }
        });
        
        fileMenu.add(freeDraeingMenuItem);
        mainMenu.add(fileMenu);

        application.setJMenuBar(mainMenu);

        application.addWindowStateListener(new WindowStateListener() {
            
            @Override
            public void windowStateChanged(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        

//        JFrame application = new JFrame("Paint Program");
//        MouseDrawer mouseDrawer = new MouseDrawer();
//        application.add(mouseDrawer, BorderLayout.CENTER);
//
//        JLabel statusTab = mouseDrawer.getStatusTab();
//        application.add(statusTab, BorderLayout.SOUTH);
        
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        application.setVisible(true);

}

//    public void frame__windowStateChanged(WindowEvent e) {
//        // minimized
//        if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
//            
//            //_print("minimized");
//            String test = "";
//            
//            
//        } // maximized
//        else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
//            
//            //_print("maximized");
//            String test = "";
//        }
//    }
}
