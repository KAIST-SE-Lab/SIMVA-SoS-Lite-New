package kr.ac.kaist.se.view.frame;

import kr.ac.kaist.se.model.abst.obj._SimObject_;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.simdata.input.SimConfiguration;
import kr.ac.kaist.se.simdata.input.SimScenario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModelInfoFrame extends JFrame implements ActionListener {

    JScrollPane treePane;

//    private JTree tree;
//    private JTreeTable treeTable;

    private JTable simObjTable;
    private JScrollPane scrollPane;


    public ModelInfoFrame(SoS simModel, String isMapeOn, SimConfiguration simConfig, SimScenario simScenario) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(600, 600);
        this.setTitle(simModel.getName());


        String[] header = {"Class", "ObjId", "ObjName"};
        DefaultTableModel tableModel = new DefaultTableModel(header, 0);

        simObjTable = new JTable(tableModel);
        treePane = new JScrollPane(simObjTable);

        this.add(treePane, "Center");

        ArrayList<_SimObject_> allSimObjects = simModel.getAllSimObjects();

        Object objects[] = new Object[3];
        for (int i = 0; i < allSimObjects.size(); i++){

            _SimObject_ aSimObject = allSimObjects.get(i);

            objects[0] = aSimObject.getClass().getSimpleName();
            objects[1] = aSimObject.getId();
            objects[2] = aSimObject.getName();

            tableModel.addRow(objects);
        }


//        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Node");

//        treePane.setViewportView();

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }




}



