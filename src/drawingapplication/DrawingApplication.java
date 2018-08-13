/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawingapplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kirill Okhotnitski
 */
public class DrawingApplication {

    private final javax.swing.JMenu fileMenu;
    private final javax.swing.JMenuBar mainMenu;
    private final javax.swing.JMenuItem drawingMenuItem;
    private final javax.swing.JMenuItem freeDraeingMenuItem;

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

        //here we create the menu bar which allows you to select what you would like to draw
        mainMenu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        drawingMenuItem = new javax.swing.JMenuItem();
        freeDraeingMenuItem = new javax.swing.JMenuItem();

        JFrame application = new JFrame("Paint Program");
        Dimension frameSize = application.getSize();

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");

        //here we add the draw shape menu item
        drawingMenuItem.setMnemonic('A');
        drawingMenuItem.setText("Drawing");
        drawingMenuItem.setToolTipText("Drawing");
        drawingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                //this adds the draw component panel 
                InitComponents components = new InitComponents();
                application.setContentPane(components.createPanel(application.getSize()));
                application.revalidate();

            }
        });
        fileMenu.add(drawingMenuItem);

        //here we add the free drawing menu item
        freeDraeingMenuItem.setMnemonic('E');
        freeDraeingMenuItem.setText("free Drawing");
        freeDraeingMenuItem.setToolTipText("free Drawing");
        freeDraeingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                //this add the free draw component panel
                MouseDrawer mouseDrawer = new MouseDrawer();
                application.setContentPane(mouseDrawer);

                JLabel statusTab = mouseDrawer.getStatusTab();
                application.add(statusTab, BorderLayout.SOUTH);
                application.revalidate();

            }
        });

        fileMenu.add(freeDraeingMenuItem);
        mainMenu.add(fileMenu);

        application.setJMenuBar(mainMenu);

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setExtendedState(JFrame.MAXIMIZED_BOTH);
        application.setVisible(true);

    }

}
