package pkg.controle.utilitaires;

import java.io.FileNotFoundException;

/**
 * 
 * @author Doucouré Abdietou
 *
 */

public class Systeme {
	/**
	 * Cette classe permet de localiser le fichier jrxml qui nous permet d'importer nos données au formats pdf et docx
	 */

	static private String nomOS = System.getProperty("os.name");
	static private  String versionOS = System.getProperty("os.version");
	static private String separateur = System.getProperty("file.separator");
	static private String nomUtilisateur = System.getProperty("user.name");
	static private String repertoireCourant = System.getProperty("user.dir");
	
	/**
	 * 
	 * @return les séparateurs des chemins des fichiers
	 */
	public static String getSeparateur() {
		return separateur;
	}
	/**
	 * 
	 * @return le repoertoire ou se trouve notre fichier jrxml
	 */
	public static String getRepertoireCourant() {
		return repertoireCourant;
	}
	
	/**
	 * 
	 * @return le système d'exploitation utilisé par l'utilisateur
	 */
	public static String getNomOS() {
		return nomOS;
	}
	/**
	 * 
	 * @return la version du système d'exploitation utilisé par l'utilisateur
	 */
	public static String getVersionOS() {
		return versionOS;
	}
	
	/**
	 * 
	 * @return le nom de l'utilisateur
	 */
	public static String getNomUtilisateur() {
		return nomUtilisateur;
	}
	
	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main (String[] args)
	throws FileNotFoundException {
		
	}
}
