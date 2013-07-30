/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.syscontrol.helper;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;


/**
 *
 * @author Diego
 */
public class TableUtil extends AbstractCellEditor implements TableCellEditor{

    Component component = new JTextField();

    @Override
    public Object getCellEditorValue() {
       return ((JTextField)component).getText();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object v, boolean isSelected, int row, int column) {         


         if (isSelected) {
            ((JTextField)component).setText((String) v);
        }
       
        
        return component;

    }

}
