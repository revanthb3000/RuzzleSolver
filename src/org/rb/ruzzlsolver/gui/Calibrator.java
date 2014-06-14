package org.rb.ruzzlsolver.gui;
 
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.geom.Rectangle2D;

import static java.awt.GraphicsDevice.WindowTranslucency.*;
 
/**
 * This frame lets the user configure the Configuration file easily.
 * Gets to user to click on the 16 tiles thereby getting the pixel positions automatically.
 * Perfect for the average Joe !
 * @author RB
 *
 */
public class Calibrator extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
    public Calibrator() {
        super("ShapedWindow");
        getContentPane().setLayout(new GridBagLayout());
 
        // It is best practice to set the window's shape in
        // the componentResized method.  Then, if the window
        // changes size, the shape will be correctly recalculated.
        addComponentListener(new ComponentAdapter() {
            // Give the window an elliptical shape.
            // If the window is resized, the shape is recalculated here.
            @Override
            public void componentResized(ComponentEvent e) {
            	int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
            	int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
                setShape(new Rectangle2D.Double(0,0,screenWidth,screenHeight));
            }
        });

        //A full screen window !!
    	int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    	int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        setUndecorated(true);
        setSize(screenWidth,screenHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel jLabel = new JLabel("Start a practice round and click on tile #1 (Row No. 1 & Column No. 1)");
        jLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        getContentPane().add(jLabel);
        
        CalibratorMouseListener calibratorMouseListener = new CalibratorMouseListener();
        calibratorMouseListener.setLabel(jLabel);
        calibratorMouseListener.setParentHandle(this);
        addMouseListener(calibratorMouseListener);
    }
    
    public static void main(String[] args) {
        // Determine what the GraphicsDevice can support.
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        final boolean isTranslucencySupported = gd.isWindowTranslucencySupported(TRANSLUCENT);
 
        //If shaped windows aren't supported, exit.
        if (!gd.isWindowTranslucencySupported(PERPIXEL_TRANSPARENT)) {
            System.err.println("Shaped windows are not supported");
            System.exit(0);
        }
 
        //If translucent windows aren't supported,
        //create an opaque window.
        if (!isTranslucencySupported) {
            System.out.println(
                "Translucency is not supported, creating an opaque window");
        }
 
        // Create the GUI on the event-dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Calibrator sw = new Calibrator();
 
                // Set the window to 70% translucency, if supported.
                if (isTranslucencySupported) {
                    sw.setOpacity(0.5f);
                }
 
                // Display the window.
                sw.setVisible(true);
            }
        });
    }
}