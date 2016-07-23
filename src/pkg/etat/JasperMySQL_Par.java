package pkg.etat;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

// pour la gestion du chemin et des différents OS
import pkg.controle.utilitaires.Systeme;
// pour obtenir la connection statique
import pkg.controle.connexion.*;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class JasperMySQL_Par {
	/**
	 * Cette classe permet de réaliser les opération d'impression et d'exportation graçe au logciel jasper qui se connecte
	 * à notre base de données
	 */
	private static Connection laConnexion = ControleConnexion.getConnexion();
	private static JasperDesign design = null;
	private static JasperReport report = null;
	private static JasperPrint print = null;
	private static String ID_client = "";
	private static String ID_operation = "";
	
	/**
	 * Cette méthode permet d'obetenur l'ID du client
	 * @return l'ID du client
	 */
	public static String getID_client() {
		return ID_client;
	}
	/**
	 * Cette méthode permet d'obtenir l'ID de l'opération
	 * @return l'ID de l'opération
	 */
	public static String getID_operation() {
		return ID_operation;
	}
	
	/**
	 * Cette méthode permet de modifier l'ID client
	 * @param ID_client 
	 * le nouveau ID_client
	 */
	public static void setID_client (String ID_client) {
		JasperMySQL_Par.ID_client = ID_client;
	}
	/**
	 * Cette méthode permet de modifier l'ID de l'opération
	 * @param ID_operation 
	 * le nouveau ID_operation
	 */
	public static void setID_operation (String ID_operation) {
		JasperMySQL_Par.ID_operation = ID_operation;
	}
	
	/**
	 * Cette méthode reçoit en paramètre le nom du document jrxml en 3 étape
	 * @param rapport
	 * Objet pour charger et compiler les exports et impression
	 * 
	 */
	public static void chargeEtcompile(String rapport) {
		try {		
			// Etape 1 : Jasper doit savoir où se trouve le fichier
		    design = JRXmlLoader.load(Systeme.getRepertoireCourant()+Systeme.getSeparateur()+"jasper"+Systeme.getSeparateur()+rapport);			
			
		    // Etape 3 : il compile le fichier jrxml et génère
		    // le fichier binaire Jasper, nomé report
			report = JasperCompileManager.compileReport(design);
			// Etape 2 : On alimente le fichier Jasper en donnée
			// en fonctionb de la commande selectionnée
			HashMap<String, Object> mesParametres = new HashMap<String, Object>();
				
			mesParametres.put("ID_operation", new String(getID_operation()));

			print = JasperFillManager.fillReport(report, mesParametres, laConnexion);			
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null, "La compilation du rapport a échoué : \n"+e.getMessage()+
					"\nVeuillez contacter votre administrateur","Erreur",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Cette méthode permet de créer un aperçu avant l'impression
	 * @param rapport
	 * Objet pour charger et compiler les exports et impression
	 */
	public static void apercu(String rapport) {
		chargeEtcompile(rapport);
		try {
			JasperViewer.viewReport(print,false);
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erreur lors de l'aperçu : \n"+e.getMessage()+
					"\nVeuillez contacter votre administrateur","Erreur",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Cette méthode permet d'imprimer les données
	 * @param rapport
	 * Objet pour charger et compiler les exports et impression
	 */
	public static void imprimer(String rapport) {
		chargeEtcompile(rapport);
		try{
			JasperPrintManager.printReport(print, true);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "L'impression a échoué : \n"+e.getMessage()+
					"\nVeuillez contacter votre administrateur","Erreur",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Cette méthode permet d'exporter les données au format PDF
	 * @param rapport
	 * Objet pour charger et compiler les exports et impression
	 */
	public static void exporterPDF(String rapport) {
		JFileChooser save = new JFileChooser();
		save.setSelectedFile(new File("Fichier.pdf"));
		int retour = save.showSaveDialog(save);
		if(retour == JFileChooser.APPROVE_OPTION){
			try {	
				chargeEtcompile(rapport);
				JasperExportManager.exportReportToPdfFile(print, save.getSelectedFile().getAbsolutePath());
			} catch(Exception e){
				JOptionPane.showMessageDialog(null, "L'export au format PDF a échoué : \n"+e.getMessage()+
						"\nVeuillez contacter votre administrateur","Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Cette méthode permet d'exporter les données au format docx
	 * @param rapport
	 * Objet pour charger et compiler les exports et impression
	 */
		public static void exportDOCX(String rapport){
			chargeEtcompile(rapport);
			JFileChooser save = new JFileChooser();
			save.setSelectedFile(new File("Fichier.docx"));
			int retour = save.showSaveDialog(save);
			if(retour == JFileChooser.APPROVE_OPTION){
				try{
					JRDocxExporter exporter = new JRDocxExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
					exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, save.getSelectedFile().getAbsolutePath() );
					exporter.exportReport();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "L'export a rencontré une erreur : \n"+e.getMessage()+
							"\nVeuillez contacter votre administrateur","Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
}
