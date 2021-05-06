package fr.eni.papeterie.ihm.ecrcatalogue;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TypeCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	private Icon stylo;
    private Icon ramette;
    
    public TypeCellRenderer(){
    	super();
        stylo = new ImageIcon(".\\src\\img\\pencil.gif");
        ramette = new ImageIcon(".\\src\\img\\ramette.gif");
    }
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
        String type = (String) value;
 
        setText("");
        
        if(type.equalsIgnoreCase("Stylo")){
            setIcon(stylo);
        } else {
            setIcon(ramette);
        }
 
        return this;
    }
	

}
