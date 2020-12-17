package kr.ac.kaist.se.view.parts;

import javax.swing.tree.TreeModel;

public interface TreeTableModel extends TreeModel {
    /**
     * Returns the number ofs availible column.
     */
    public int getColumnCount();

    /**
     * Returns the name for column number column.
     */
    public String getColumnName(int column);

    /**
     * Returns the type for column number column.
     */
    public Class getColumnClass(int column);

    /**
     * Returns the value to be displayed for node node,
     * at column number column.
     */
    public Object getValueAt(Object node, int column);

    /**
     * Indicates whether the the value for node node,
     * at column number column is editable.
     */
    public boolean isCellEditable(Object node, int column);

    /**
     * Sets the value for node node,
     * at column number column.
     *
     * @param aValue new value
     * @param node a node from this model
     * @param column column index
     */
    public void setValueAt(Object aValue, Object node, int column);
}
