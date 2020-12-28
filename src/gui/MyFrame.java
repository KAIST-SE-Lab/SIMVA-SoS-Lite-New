package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener, ChangeListener {

    JTextField textField;
    JButton button;
    JCheckBox checkBox;
    JRadioButton radioButton1;
    JRadioButton radioButton2;
    JRadioButton radioButton3;

    String[] comboOptions = {"opt1", "opt2", "opt3"};
    JComboBox comboBox;

    JPanel panel;
    JLabel label;
    JSlider slider;

    public MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250, 40));
        textField.setText("username");

        button = new JButton("Submit");
        button.setFocusable(false);
        button.addActionListener(this);

        checkBox = new JCheckBox();
        checkBox.setText("checkbox text");
        checkBox.setFocusable(false);

        radioButton1 = new JRadioButton("rb1");
        radioButton2 = new JRadioButton("rb2");
        radioButton3 = new JRadioButton("rb3");

        radioButton1.addActionListener(this);
        radioButton2.addActionListener(this);
        radioButton3.addActionListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);

        comboBox = new JComboBox(comboOptions);
        comboBox.addActionListener(this);
//        comboBox.setEditable(true);
        comboBox.addItem("opt4");
        comboBox.insertItemAt("opt2-B", 2);
        comboBox.setSelectedIndex(3);

        panel = new JPanel();
        label = new JLabel("Label");
        panel.setPreferredSize(new Dimension(500, 300));

        slider = new JSlider(1, 0, 100, 50);
        slider.setPreferredSize(new Dimension(400, 200));
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setPaintTrack(true);
        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(25);
        slider.setOrientation(SwingConstants.HORIZONTAL);
        slider.addChangeListener(this);

        panel.add(slider);
        panel.add(label);

        this.add(textField);
        this.add(button);
        this.add(checkBox);

        this.add(radioButton1);
        this.add(radioButton2);
        this.add(radioButton3);

        this.add(comboBox);

        this.add(panel);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String text = textField.getText();
            System.out.println(text);
//            button.setEnabled(false);

            System.out.println(checkBox.isSelected());
        } else if (e.getSource() == radioButton1) {
            System.out.println(radioButton1.getText());
        } else if (e.getSource() == radioButton2) {
            System.out.println(radioButton2.getText());
        } else if (e.getSource() == radioButton3) {
            System.out.println(radioButton3.getText());
        } else if (e.getSource() == comboBox) {
            System.out.println(comboBox.getSelectedIndex());
            System.out.println(comboBox.getSelectedItem());
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == slider) {
            label.setText("" + slider.getValue());
        }
    }
}
