package pkg.controle.modele;

import java.awt.Component;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 
 * @author Doucouré Abdietou
 *
 */

public class RendreDate extends DefaultTableCellRenderer {
	/**
	 * Cette classe permet mettre les dates au format français dans nos tableaux "opération" et "client" 
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
	
	public Component getTableCellRendererComponent(JTable table, Object
		value, boolean isSelected,  boolean hasFocus,  int row,   int column) {
		Date date = (Date) value;
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRENCH);
		String str = df.format(date);
		this.setText(str);
	    this.setHorizontalAlignment(CENTER);
	    this.setBackground(null);
	    return this;
	}
}