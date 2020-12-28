package kr.ac.kaist.se.view.parts;

import javax.swing.*;
import java.awt.*;

public class FileChooserPanel extends JPanel {

    protected String fileName;

    protected String targetLabelText = "Target File";
    protected boolean needMultiLine;
    //    protected String filePathText;
    protected String btnText = "Open";

    JLabel fileTargetLabel;
    JTextField filePathField;
    JTextArea filePathArea;
    JButton fileOpenButton;

    public FileChooserPanel() {
        setLayout(new BorderLayout(5, 3));
        setBorder(BorderFactory.createEmptyBorder(3, 3, 5, 5));

        initComponents(this.targetLabelText, this.btnText, this.needMultiLine);
    }

    public FileChooserPanel(String targetLabelText, String btnText, boolean needMultiLine) {
        this.targetLabelText = targetLabelText;
        this.btnText = btnText;
        this.needMultiLine = needMultiLine;

        initComponents(this.targetLabelText, this.btnText, this.needMultiLine);
    }

    public FileChooserPanel(String fileName) {
        this.fileName = fileName;
    }

    private void initComponents(String targetLabelText, String btnText, boolean needMultiLine) {

        this.needMultiLine = needMultiLine;

        fileTargetLabel = new JLabel(targetLabelText);
        fileTargetLabel.setPreferredSize(new Dimension(150, 25));
        fileTargetLabel.setMinimumSize(new Dimension(50, 25));

        if (this.needMultiLine == true) {
            filePathArea = new JTextArea();
            filePathArea.setPreferredSize(new Dimension(500, 100));
        } else {
            filePathField = new JTextField();
            filePathField.setPreferredSize(new Dimension(500, 23));
            filePathField.setMinimumSize(new Dimension(100, 23));
        }

        fileOpenButton = new JButton(btnText);
        fileOpenButton.setPreferredSize(new Dimension(150, 25));
        fileTargetLabel.setMinimumSize(new Dimension(50, 25));

        add(fileTargetLabel, BorderLayout.WEST);
        if (this.needMultiLine == true) {
            add(filePathArea, BorderLayout.CENTER);
        } else {
            add(filePathField, BorderLayout.CENTER);
        }
        add(fileOpenButton, BorderLayout.EAST);
    }
}
