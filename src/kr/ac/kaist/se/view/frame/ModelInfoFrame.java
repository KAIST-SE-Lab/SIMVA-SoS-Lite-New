package kr.ac.kaist.se.view.frame;

import kr.ac.kaist.se.controller.sim.SimEngine;
import kr.ac.kaist.se.model.abst.obj._SimActionableObject_;
import kr.ac.kaist.se.model.abst.obj._SimObject_;
import kr.ac.kaist.se.model.intf.Stateful;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModelInfoFrame extends JFrame implements ActionListener {

    JScrollPane treePane;

    SimEngine simEngine;

//    private JTree tree;
//    private JTreeTable treeTable;

    private final JTable simObjTable;
    private JScrollPane scrollPane;


    public ModelInfoFrame(SimEngine simEngine) {
        this.simEngine = simEngine;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(1500, 600);
        this.setTitle(simEngine.getSimModel().getName());


        String[] header = {"Class", "ObjId", "ObjName", "ObjState", "ObjCapableActions"};
        DefaultTableModel tableModel = new DefaultTableModel(header, 0);

        simObjTable = new JTable(tableModel);
        treePane = new JScrollPane(simObjTable);

        this.add(treePane, "Center");

        ArrayList<_SimObject_> allSimObjects = simEngine.getSimModel().getAllSimObjects();

        Object[] objects = new Object[5];
        for (int i = 0; i < allSimObjects.size(); i++) {

            _SimObject_ aSimObject = allSimObjects.get(i);

            objects[0] = aSimObject.getClass().getSimpleName();
            objects[1] = aSimObject.getId();
            objects[2] = aSimObject.getName();

            if (aSimObject instanceof Stateful) {
                objects[3] = "Stateful";
            }

            if (aSimObject instanceof _SimActionableObject_) {
                objects[4] = ((_SimActionableObject_) aSimObject).getCapableActionList().size() + "";
            }


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



