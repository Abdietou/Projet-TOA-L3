package pkg.controle.modele;

import java.awt.Component;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 
 * @author Doucouré Abdietou
 *
 */

public class RendreGras extends DefaultTableCellRenderer {
	/**
	 * Cette Classe permet de rendre les cases souhaités de notre nos tableau en gras
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Cette méthode attend 6 paramétres
	 * value  : valeur à attribuer à la cellule qui sera affectée par le render
	 * isSelected : vrai si la cellule est selectionnée
	 * hasFocus : vrai si la celulle à le focus
	 * row : numéro de ligne de la cellule concernée
	 * column : numéro de colonne de la cellule concernée
	 */
	
	 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        setFont(getFont().deriveFont(Font.BOLD));
	        return this;
	    }

}