package pkg.controle.connexion;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import pkg.entite.Parametres;

import javax.swing.JOptionPane;

/**
 * 
 * @author Doucouré Abdietou
 *
 */

public class ControleConnexion {
	/**
	 * Cette classe sert à vérifier les tentatives de connection
	 */
	
	static Parametres lesParametres;
	static boolean etatConnexion;
	
	static Connection laConnectionStatique;
	static {
		boolean ok = true;
		lesParametres = new Parametres();
		try {
			Class.forName(lesParametres.getDriverSGBD());
			etatConnexion = true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "Classes non trouvées" + "pour le chargement" + "du pilote JBDC MySQL",
			"Alerte", JOptionPane.ERROR_MESSAGE);
			ok = false;
			etatConnexion = false;
			e.printStackTrace();
		}
		
		//Etablissement de la connexion
		if (ok == true){
			try {
				//recupération des paramètres présents
				//dans la classe Parametres
				String urlBD = lesParametres.getServeurBD();
				String nomUtilisateur = lesParametres.getNomUtilisateur();
				String MDP = lesParametres.getMotDePasse();
				// création d'une connextion
				//conteneant les paramètres de connexion
				laConnectionStatique = (Connection) DriverManager.getConnection(urlBD, nomUtilisateur, MDP);
				etatConnexion = true;
			}
			
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Impossible de se connecter" +
			    		" à la base de données",
						"ALERTE", JOptionPane.ERROR_MESSAGE);
			    etatConnexion = false;
			}
		}
	}
	
	//les accesseurs statiques
	 // ------------------------
	public static Parametres getParametres() {
		return lesParametres;
	}
	
	public static boolean getControleConnexion() {
		return etatConnexion;
	}
	
	public static Connection getConnexion(){
		return laConnectionStatique;
	}
		
	/**
	 * 
	 * @param Nom
	 * Le login saisie par l'utilisateur
	 * @param MotDePasse
	 * Le mot de passe saisie par l'utilisateur
	 * @return la vérification de saisie
	 */
	public static boolean controle(String Nom, String MotDePasse) {	
		boolean verificationSaisie;
		if (Nom.equals(lesParametres.getNomUtilisateur())
			&& MotDePasse.equals(lesParametres.getMotDePasse())) {
			verificationSaisie = true;
		}
		else {
	        JOptionPane.showMessageDialog(null, "Vérifier votre saisie.", 
	            "ERREUR", JOptionPane.ERROR_MESSAGE);
	        verificationSaisie = false;
		}
		return verificationSaisie;
	}

	/**
	 * Cette méthode permet de fermer la session en cours et d'interrompre la connexion à la base de données
	 */
	public static void fermetureSession() {
		try {
			laConnectionStatique.close();
		}
		catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Problème rencontré" +
	            		"à la fermeture de la connexion", 
	    	            "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
	}
}

