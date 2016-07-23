package pkg.ctrl;

//tiré de l'aide de Sun (Oracle maintenant...) sur les ComBoxRenderer
//http://download.oracle.com/javase/tutorial/uiswing/components/combobox.html
//note : toujours penser à rafraîchir le dossier images en cas d'ajout

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class ComboBoxRenderer extends JLabel implements ListCellRenderer<Object> {
	
	/**
	 * Cette classe permet de choisir entre le format PDF et le format docx lors d'un export de fichier
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon icon;
	private String txt;

	public ComboBoxRenderer() {
		setOpaque(false);
	}

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		int selectedIndex = ((Integer)value).intValue();
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		
		if(selectedIndex == 0) {
			icon 	= new ImageIcon(getClass().getResource("/images/gestion/pdf.png")); 
			txt		= "PDF (*.pdf)";
		}
		if(selectedIndex == 1) {
			icon 	= new ImageIcon(getClass().getResource("/images/gestion/docx.png")); 
			txt		= "DOCX (*.docx)";
		}
		setIcon(icon);
		if (icon != null) {
			setText(txt);
			setFont(list.getFont());
		}
		return this;
	}

}
