package pkg.entite;

/**
 * 
 * @author Doucouré Abdietou
 *
 */

public abstract class Personne {
	/**
	 * Cette classe contient les données personnelles des clients
	 */
	  String ID_client;
	  String rib;
	  String nom;
	  String prenom;
	  String adresse;
	  String ville;
	  int codepostale;
	  
	  // getters et setters
	  public String getID_client() {
		  return ID_client;
	  }
	  public void setID_client(String ID_client) {
		  this.ID_client=ID_client;
	  }
	  public String getRib() {
	    return rib;
	  }
	  public void setCode(String rib) {
	    this.rib = rib;
	  }
	  public String getNom() {
	    return nom;
	  }
	  public void setNom(String nom) {
	    this.nom = nom;
	  }
	  public String getPrenom() {
	    return prenom;
	  }
	  public void setPrenom(String prenom) {
	    this.prenom = prenom;
	  }
	  
	  public String getAdresse() {
		  return adresse;
	  }
	  
	  public void setAdresse(String adresse) {
		  this.adresse=adresse;
	  }
	  
	  public String getVille() {
		  return ville;
	  }
	  
	  public void setVille(String ville) {
		  this.ville=ville;
	  }
	  
	  public int getCodepostale() {
		  return codepostale;
	  }
	  
	  public void setCodepostale(int codepostale) {
		  this.codepostale=codepostale;
	  }
	  
	  /**
	   * 1er Constructeur
	   * @param vNum_client
	   * Le nouveau numéro de client
	   * @param vRib
	   * Le nouveau rib
	   * @param vNom
	   * Le nouveau nom
	   * @param vPrenom
	   * Le nouveau prénom
	   * @param vAdresse
	   * La nouvelle adresse
	   * @param vVille
	   * La nouvelle ville
	   * @param vCodepostale
	   * Le nouveau code postale
	   */
	    public Personne (String vNum_client, String vRib, String vNom, String vPrenom, String vAdresse, String vVille, int vCodepostale) {
	        ID_client = vNum_client;
	    	rib = vRib;
	        nom = vNom;
	        prenom = vPrenom;
	        adresse = vAdresse;
	        ville = vVille;
	        codepostale = vCodepostale;
	  }
	    /**
	     * 2ème contructeur pour les recherches
	     * @param vNum_client
	     * Le nouveau numéro de client
	     */
	  public Personne (String vNum_client){
	    ID_client = vNum_client;
	  }
	  /**
	   * 3ème contructeur pour une simple lecteure
	   */
	  public Personne () {
	  }
	}