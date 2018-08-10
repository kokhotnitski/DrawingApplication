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

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setExtendedState(JFrame.MAXIMIZED_BOTH);
        application.setVisible(true);

    }

}
