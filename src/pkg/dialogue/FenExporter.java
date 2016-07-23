package pkg.dialogue;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pkg.ctrl.ComboBoxRenderer;
import pkg.etat.*;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class FenExporter extends JDialog {
	/**
	 * Cette classe est une interface graphique qui permet d'exporter nos données format PDF ou docx
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> type_export;
	private JPanel jContentPane = null;
	private String modeleJasper = null;
	private JButton export;
	private JLabel jLabel1;
	
	/**
	 * Create the dialog.
	 */
	public FenExporter(String modele) {		
		super();
		modeleJasper = modele;
		setSize(442, 150);
		setLocationRelativeTo(null);
		setContentPane(getJContentPane());
	}
	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(new java.awt.Color(255,255,255));
			jContentPane.setPreferredSize(new java.awt.Dimension(373, 112));
			jContentPane.add(getType_export());
			jContentPane.add(getJLabel1());
			jContentPane.add(getExport());
		}
		return jContentPane;
	}
	
	private JComboBox<Object> getType_export() {
		if(type_export == null) {
			Integer[] intArray = {0,1,2,3,4};
			ComboBoxRenderer renderer = new ComboBoxRenderer();
			type_export = new JComboBox<Object>(intArray);
			type_export.setBounds(18, 46, 155, 54);
			type_export.setRenderer(renderer);
		}
		return type_export;
	}
	
	private JLabel getJLabel1() {
		if(jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Choisissez dans la liste le type de document à exporter.");
			jLabel1.setBounds(18, 19, 387, 16);
		}
		return jLabel1;
	}
	
	private JButton getExport() {
		if(export == null) {
			export = new JButton();
			export.setText("Exporter");
			export.setBounds(245, 45, 151, 55);
			export.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					switch (type_export.getSelectedIndex()) {
					case 0 :
						JasperMySQL_Par.exporterPDF(modeleJasper);
						break;
					case 1 :
						JasperMySQL_Par.exportDOCX(modeleJasper);
						break;
					default: 
						break;
					}				
					FenExporter.this.dispose();
				}				
			});
		}
		return export;
	}	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FenExporter dialog = new FenExporter("client.jrxml");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
