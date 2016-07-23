package pkg.entite;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class Parametres {
	/**
	 * Cette classe permet de géré les paramètre de connexion
	 */
	private String nomUtilisateur;
	private String motDePasse;
	private String serveurBD;
	private String driverSGBD;
	
	/**
	 * Cette méthode donne le login de l'utilisateur
	 * @return le login de l'utilisateur
	 */
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	/**
	 * Cette méthode permet de modifier le login de l'utilisateur
	 * @param nomUtilisateur login de l'utilisateur
	 */
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	
	/**
	 * Cette méthode donne le mot de passe
	 * @return le mot de passe
	 */
	
	public String getMotDePasse() {
		return motDePasse;
	}
	/**
	 * Cette méthode permet de modifier le mot de passe
	 * @param motDePasse
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	/**
	 * Cette méthode donne le driver du SGBD
	 * @return le driver du SGBD
	 */
	public String getDriverSGBD() {
		return driverSGBD;
	}
	/**
	 * Cette méthode modifie le serveur de BD
	 * @param serveurBD
	 */
	public void setServeurBD(String serveurBD) {
		this.serveurBD = serveurBD;
	}

	/**
	 * Cette méthode donne le serveur de BD
	 * @return le serveur de BD
	 */
	public String getServeurBD() {
		return serveurBD;
		
	}
	public void setDriverSGBD(String driverSGBD) {
		this.driverSGBD = driverSGBD;
	}
	
	/**
	 * Ce sont les paramètres de connection correcte si l'utilisateur entre d'autres paramètres de connexion alors
	 * l'accès au logiciel lui sera refusé
	 * si il donne le bon login et mot de passe il aura accès au logiciel
	 */
	public Parametres () {
	    nomUtilisateur = "root"; 
		motDePasse = "pipemast19881031";
		driverSGBD = "org.gjt.mm.mysql.Driver";
		serveurBD = "jdbc:mysql://localhost/projet"; 
	}
}