package kr.ac.kaist.se.view.parts;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TreeTable extends JTable {

    /** A subclass of JTree. */
    protected TreeTableCellRenderer tree;
    private TreeTableModel treeTableModel;


    public TreeTable(TreeTableModel aTreeTableModel){
        super();

        //Dummy model
        tree = new TreeTableCellRenderer(new DefaultTreeModel(new DefaultMutableTreeNode()));

        // Install a tableModel representing the visible rows in the tree.
        setTreeTableModel(treeTableModel);

        ListToTreeSelectionModelWrapper selectionWrapper =
                new ListToTreeSelectionModelWrapper();
        tree.setSelectionModel(selectionWrapper);
        setSelectionModel(selectionWrapper.getListSelectionModel());

        setDefaultRenderer(TreeTableModel.class, tree);
        //setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor());

        setShowGrid(false);

        setIntercellSpacing(new Dimension(0,0));

        if (tree.getRowHeight() < 1){
            setRowHeight(18);
        }

        addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName() == "rowMargin"){
                    tree.intercellSpacing = getIntercellSpacing();
                }
            }
        });


//        InputMap ipm = getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


    }

    public TreeTableCellRenderer getTree() {
        return tree;
    }

    public void setTree(TreeTableCellRenderer tree) {
        this.tree = tree;
    }

    public TreeTableModel getTreeTableModel() {
        return treeTableModel;
    }

    public void setTreeTableModel(TreeTableModel treeTableModel) {
        this.treeTableModel = treeTableModel;
    }

    /**
     *
     * @author ymbaek
     * (based on https://alvinalexander.com/java/jwarehouse/netbeans-src/tasklist/usertasks/src/org/netbeans/modules/tasklist/usertasks/treetable/TreeTable.java.shtml)
     */
    public class TreeTableCellRenderer extends JTree implements TableCellRenderer {

        /** Last table/tree row asked to renderer. */
        protected int visibleRow;
        private Border border;
        private Dimension intercellSpacing = new Dimension(1, 1);

        public TreeTableCellRenderer(TreeModel treeModel){
            super(treeModel);
        }

        public void setBounds(int x, int y, int w, int h) {
            super.setBounds(x, 0, w, TreeTable.this.getHeight());
        }

        @Override
        public void updateUI() {
            super.updateUI();
            // Make the tree's cell renderer use the table's cell selection
            // colors.
            TreeCellRenderer tcr = getCellRenderer();
            if (tcr instanceof DefaultTreeCellRenderer) {
                DefaultTreeCellRenderer dtcr = ((DefaultTreeCellRenderer)tcr);
                // For 1.1 uncomment this, 1.2 has a bug that will cause an
                // exception to be thrown if the border selection color is
                // null.
                // dtcr.setBorderSelectionColor(null);
                dtcr.setTextSelectionColor(UIManager.getColor
                        ("Table.selectionForeground"));
                dtcr.setBackgroundSelectionColor(UIManager.getColor
                        ("Table.selectionBackground"));
            }
        }

        @Override
        public void paint(Graphics g) {
            Rectangle oldClip = g.getClipBounds();
            Rectangle clip;
            //if (border == null)
            clip = oldClip.intersection(
                    new Rectangle(0, 0,
                            getWidth() - intercellSpacing.width,
                            getRowHeight() - intercellSpacing.height));
            /*else
                clip = oldClip.intersection(
                    new Rectangle(1, 1, getWidth() - 2, getRowHeight() - 2));*/
            g.setClip(clip);
            g.translate(0, -visibleRow * getRowHeight());
            super.paint(g);
            g.translate(0, visibleRow * getRowHeight());
            g.setClip(oldClip);
            if (border != null)
                border.paintBorder(this, g, 0, 0, getWidth(), getRowHeight());
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                                                       Object value,
                                                       boolean isSelected,
                                                       boolean hasFocus,
                                                       int row, int column) {

            if (hasFocus) {
                border = UIManager.getBorder("Table.focusCellHighlightBorder");
                if (table.isCellEditable(row, column)) {
                    super.setForeground( UIManager.getColor("Table.focusCellForeground") );
                    super.setBackground( UIManager.getColor("Table.focusCellBackground") );
                }
            } else {
                border = null;
            }

            if(isSelected)
                setBackground(table.getSelectionBackground());
            else
                setBackground(table.getBackground());

            visibleRow = row;
            return this;
        }
    }

    /**
     * ListToTreeSelectionModelWrapper extends DefaultTreeSelectionModel
     * to listen for changes in the ListSelectionModel it maintains. Once
     * a change in the ListSelectionModel happens, the paths are updated
     * in the DefaultTreeSelectionModel.
     */
    class ListToTreeSelectionModelWrapper extends DefaultTreeSelectionModel {

        private static final long serialVersionUID = 1;

        /** Set to true when we are updating the ListSelectionModel. */
        protected boolean         updatingListSelectionModel;

        public ListToTreeSelectionModelWrapper() {
            super();
            getListSelectionModel().addListSelectionListener
                    (createListSelectionListener());
        }

        /**
         * Returns the list selection model. ListToTreeSelectionModelWrapper
         * listens for changes to this model and updates the selected paths
         * accordingly.
         */
        ListSelectionModel getListSelectionModel() {
            return listSelectionModel;
        }

        /**
         * This is overridden to set updatingListSelectionModel
         * and message super. This is the only place DefaultTreeSelectionModel
         * alters the ListSelectionModel.
         */
        public void resetRowSelection() {
            if(!updatingListSelectionModel) {
                updatingListSelectionModel = true;
                try {
                    super.resetRowSelection();
                }
                finally {
                    updatingListSelectionModel = false;
                }
            }
            // Notice how we don't message super if
            // updatingListSelectionModel is true. If
            // updatingListSelectionModel is true, it implies the
            // ListSelectionModel has already been updated and the
            // paths are the only thing that needs to be updated.
        }

        /**
         * Creates and returns an instance of ListSelectionHandler.
         */
        protected ListSelectionListener createListSelectionListener() {
            return new ListSelectionHandler();
        }

        /**
         * If updatingListSelectionModel is false, this will
         * reset the selected paths from the selected rows in the list
         * selection model.
         */
        protected void updateSelectedPathsFromSelectedRows() {
            if(!updatingListSelectionModel) {
                updatingListSelectionModel = true;
                try {
                    // This is way expensive, ListSelectionModel needs an
                    // enumerator for iterating.
                    int        min = listSelectionModel.getMinSelectionIndex();
                    int        max = listSelectionModel.getMaxSelectionIndex();

                    clearSelection();
                    if(min != -1 && max != -1) {
                        for(int counter = min; counter <= max; counter++) {
                            if(listSelectionModel.isSelectedIndex(counter)) {
                                TreePath     selPath = tree.getPathForRow
                                        (counter);

                                if(selPath != null) {
                                    addSelectionPath(selPath);
                                }
                            }
                        }
                    }
                }
                finally {
                    updatingListSelectionModel = false;
                }
            }
        }

        /**
         * Class responsible for calling updateSelectedPathsFromSelectedRows
         * when the selection of the list changse.
         */
        class ListSelectionHandler implements ListSelectionListener {
            public void valueChanged(ListSelectionEvent e) {
                updateSelectedPathsFromSelectedRows();
            }
        }
    }
}
