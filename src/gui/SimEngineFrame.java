package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimEngineFrame extends JFrame implements ActionListener {

    JButton aButton;

    public SimEngineFrame(){

        ImageIcon image = new ImageIcon("logo.png");

        JPanel redPanel = new JPanel();
        redPanel.setBackground(new Color(255, 0, 0));
        redPanel.setBounds(0, 0, 300, 300);
        redPanel.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.RED);
        panel2.setBackground(Color.GREEN);
        panel3.setBackground(Color.YELLOW);
        panel4.setBackground(Color.MAGENTA);
        panel5.setBackground(Color.BLUE);

        panel1.setPreferredSize(new Dimension(100, 100));
        panel2.setPreferredSize(new Dimension(100, 100));
        panel3.setPreferredSize(new Dimension(100, 100));
        panel4.setPreferredSize(new Dimension(100, 100));
        panel5.setPreferredSize(new Dimension(100, 100));


        JLabel label1 = new JLabel();
        label1.setOpaque(true);
        label1.setBackground(Color.RED);
        label1.setBounds(50, 50, 200, 200);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 500, 500);
        //layeredPane.add(label1, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(label1, Integer.valueOf(3));

        panel5.add(layeredPane);


        aButton = new JButton();
        aButton.setText("Button");
        aButton.setFocusable(false);
        aButton.setBounds(300, 0, 200, 150);
        aButton.addActionListener(this);
        aButton.addActionListener(e -> System.out.println("Direct listener"));
        aButton.setIcon(image);
        aButton.setHorizontalTextPosition(JButton.CENTER);
        aButton.setVerticalTextPosition(JButton.TOP);
        aButton.setFont(new Font("Consolas", Font.BOLD, 25));
        aButton.setIconTextGap(-15);
        aButton.setBackground(Color.white);
        aButton.setBorder(BorderFactory.createEmptyBorder());
        aButton.setEnabled(true);


        this.setTitle("Simulation Engine Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(720, 720);
        this.setLayout(new BorderLayout(10, 10));
        this.setVisible(true);

        this.setIconImage(image.getImage());

        //this.getContentPane().setBackground(Color.DARK_GRAY);
        //this.getContentPane().setBackground(new Color(0,0,0));

//        this.add(redPanel);
//        this.add(aButton);

        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.WEST);
        this.add(panel3, BorderLayout.EAST);
        this.add(panel4, BorderLayout.SOUTH);
        this.add(panel5, BorderLayout.CENTER);


        //this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aButton){
            System.out.println("aButton is clicked");
            aButton.setEnabled(false);
        }
    }
}
