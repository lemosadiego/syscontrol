/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.helper;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Diego
 */
public class ModeloTableColumn implements TableColumnModel{

    public List colunas = new ArrayList();

    @Override
    public void addColumn(TableColumn aColumn) {
        colunas.add(aColumn);

    }

    @Override
    public void removeColumn(TableColumn column) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void moveColumn(int columnIndex, int newIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setColumnMargin(int newMargin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Enumeration<TableColumn> getColumns() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getColumnIndex(Object columnIdentifier) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TableColumn getColumn(int columnIndex) {
        return (TableColumn) colunas.get(columnIndex);
    }

    @Override
    public int getColumnMargin() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getColumnIndexAtX(int xPosition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTotalColumnWidth() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setColumnSelectionAllowed(boolean flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean getColumnSelectionAllowed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int[] getSelectedColumns() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getSelectedColumnCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSelectionModel(ListSelectionModel newModel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListSelectionModel getSelectionModel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addColumnModelListener(TableColumnModelListener x) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeColumnModelListener(TableColumnModelListener x) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
