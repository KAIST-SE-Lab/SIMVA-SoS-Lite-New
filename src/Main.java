import gui.LaunchWindow;
import gui.SimEngineFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        try {
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Error Message: " + e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        LaunchWindow launchWindow = new LaunchWindow();

        //SimEngineFrame simEngineFrame = new SimEngineFrame();
        /*
        JFrame simEngineFrame = new JFrame();
        simEngineFrame.setTitle("Simulation Engine Frame");
        simEngineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simEngineFrame.setResizable(false);
        simEngineFrame.setSize(420, 420);
        simEngineFrame.setVisible(true);

        ImageIcon image = new ImageIcon("logo.png");
        simEngineFrame.setIconImage(image.getImage());

        simEngineFrame.getContentPane().setBackground(Color.DARK_GRAY);
        simEngineFrame.getContentPane().setBackground(new Color(0,0,0));

         */

    }


}
