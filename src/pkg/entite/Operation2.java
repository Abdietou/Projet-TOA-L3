package pkg.entite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import pkg.controle.connexion.ControleConnexion;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class Operation2 {
	
	/**
	 * Cette classe contient les données des opérations
	 * Elle transforme aussi les demandes des utilisateurs en requetes SQL pour y effectuer des ajouts, modifications...
	 */
	
	private String ID_operation;
	private String ID_client;
	private String nom;
	private String prenom;
	private Date date;
	private String modedepaiement;
	private String tiers;
	private String categorie;
	private double montant;
	
	private static Connection laConnexion = ControleConnexion.getConnexion();
    private ArrayList<Operation2> lesOperations2 = new ArrayList<Operation2>();
    

    /**
     * 3eme constructeur
     * @param ID_operation
     * @param ID_client
     * @param nom
     * @param prenom
     * @param date
     * @param modedepaiement
     * @param tiers
     * @param categorie
     * @param montant
     */
    public Operation2(String ID_operation, String ID_client, String nom, String prenom, Date date, String modedepaiement, String tiers, 
    		String categorie, double montant) {
    	this.ID_operation=ID_operation;
    	this.ID_client = ID_client;
    	this.nom = nom;
    	this.prenom=prenom;
    	this.date=date;
    	this.modedepaiement=modedepaiement;
		this.tiers=tiers;
		this.categorie=categorie;
		this.montant=montant;
    }
    /**
     * 1er constructeur
     */
    public Operation2(){
    	lireRecupCRUD();
    }
    /**
     * 2eme contructeur
     * @param ID_operation
     * Le nouvel ID opération
     */
  public Operation2(String ID_operation){
    	this.ID_operation=ID_operation;
    }
    
    /**
     * Contructeur pour la création d'une opération
     * @param ID_operation
     * Le nouvel ID opération
     * @param ID_client
     * Le nouvel ID client
     * @param date
     * La nouvelle date
     * @param modedepaiement
     * Le nouveau mode de paiement
     * @param tiers
     * Le nouveau tiers
     * @param categorie
     * La nouvelle catégorie
     * @param montant
     * Le nouveau montant
     */
    public Operation2(String ID_operation, String ID_client, Date date, String modedepaiement, String tiers, 
    		String categorie, double montant) {
    	this.ID_operation=ID_operation;
    	this.ID_client=ID_client;
    	this.date=date;
    	this.modedepaiement=modedepaiement;
		this.tiers=tiers;
		this.categorie=categorie;
		this.montant=montant;
    }
    
    /**
     * Construcuteur pour lire les opérations
     * @param ID_operation
     * @param date
     * @param ID_client
     * @param nom
     * @param prenom
     * @param modedepaiement
     * @param tiers
     * @param categorie
     * @param montant
     */
    public Operation2(String ID_operation, Date date, String ID_client, String nom, String prenom, String modedepaiement, String tiers, 
    		String categorie, double montant) {
    	this.ID_operation=ID_operation;
    	this.date=date;
    	this.ID_client=ID_client;
    	this.nom = nom;
    	this.prenom=prenom;
    	this.modedepaiement=modedepaiement;
		this.tiers=tiers;
		this.categorie=categorie;
		this.montant=montant;
    }
    
  //Getters
    public String getID_ope(){
    	return ID_operation;
    }
    
  	public String getNomOpe2() {
  		return nom;
  	}
  	
  	public String getPrenomOpe2() {
  		return prenom;
  	}
  	
  	public String getID_client() {
  		return ID_client;
  	}
  	
  	
  	public Date getDate() {
  		return date;
  	}
  	
  	public String getModedepaiement() {
  		return modedepaiement;
  	}
  	
  	public String getTiers() {
  		return tiers;
  	}
  	
  	public String getCategorie() {
  		return categorie;
  	}
  	
  	public double getMontant() {
  		return montant;
  	}
  	
  	public ArrayList<Operation2> getLesOperations2() {
  		return lesOperations2;
  	}
  	
  	//setters
  	public void setNom (String nom) {
  		this.nom=nom;
  	}
  	public void setPrenom(String prenom) {
  		this.prenom=prenom;
  	}
  	public void setID_ope(String ID_operation) {
  		this.ID_operation=ID_operation;
  	}
  	public void setID_client(String ID_client) {
  		this.ID_client=ID_client;
  	}
  	public void setDate(Date date) {
  		this.date=date;
  	}
  	public void setModedepaiement (String modedepaiement){
  		this.modedepaiement=modedepaiement;
  	}
  	public void setTiers(String tiers){
  		this.tiers=tiers;
  	}
  	public void setCategorie(String categorie) {
  		this.categorie=categorie;
  	}
  	public void setMontant(double montant) {
  		this.montant=montant;
  	}
  	
  	/**
  	 * Lecture et récupération des opérations de la BD
  	 */
    public void lireRecupCRUD() {
		try {    
		    Statement state = laConnexion.createStatement();
		    ResultSet rs = state.executeQuery(
		    		"SELECT Personne.nom, Personne.prenom, Operation2.ID_operation, Operation2.ID_client, Operation2.date, Operation2.modedepaiement, Operation2.tiers, Operation2.categorie, Operation2.montant " +
		    		"FROM Personne, Operation2 " +
		    		"WHERE Personne.ID_client = Operation2.ID_client");
		    while (rs.next()) {
		    	// Information client
		    	String ID_operation = rs.getString("ID_operation");
		    	String ID_client = rs.getString("ID_client");
		    	String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			Date date = rs.getDate("date");
			String modedepaiement = rs.getString("modedepaiement");
			String tiers = rs.getString("tiers");
			String categorie = rs.getString("categorie");
			double montant = rs.getDouble("montant");
			lesOperations2.add(new Operation2(ID_operation, date , ID_client,nom,prenom, modedepaiement,
					tiers, categorie, montant));  
		    }			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, 
			    	"Problème rencontré : " + e.getMessage(),
			    	"Résultat", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    /**
     * Cette méthode crée une opération dans la BD
     * @param vID_ope
     * Le nouvel ID opération
     * @param vID_client
     * Le nouvel ID client
     * @param vDate
     * La nouvelle date
     * @param vModedepaiement
     * Le nouveau mode de paiement
     * @param vTiers
     * Le nouveau tiers
     * @param vCategorie
     * La nouvelle catégorie
     * @param vMontant
     * Le nouveau montant
     * @return
     * la nouvelle opérations saisi
     */
    public boolean creerCRUD(String vID_ope, String vID_client, String vDate, String vModedepaiement, String vTiers, String vCategorie, double vMontant) {
		boolean bCreation = false ;
		String requete = null;   
		try {
			requete = "INSERT INTO Operation2(ID_operation, ID_client, date, modedepaiement, tiers, categorie, montant) VALUES('"
					+ vID_ope			+"' , '"
					+ vID_client			+"' , '"
        			+ vDate					+ "','"
        			+ vModedepaiement		+ "','"
        			+ vTiers				+ "','"
        			+ vCategorie			+ "','"
        			+ vMontant  			+ "'"
        			+  ")";   	
        	Statement state = laConnexion.createStatement();
        	state.executeUpdate(requete);        	
        	bCreation = true;
		}         
		catch (SQLException e){
		    JOptionPane.showMessageDialog(null, "Ajout dans la BD non effectué : " + e.getMessage(), "Problème rencontré", JOptionPane.ERROR_MESSAGE);
		}
		return bCreation;        
    }
    
    /**
     * Supprime une opération de la BD
     * @param vID_ope
     * L'ID opération saisit
     * @return
     * la suppression de l'opération
     */
   public boolean supprimerCRUD (String vID_ope) {
	   boolean bSuppression = true;
	   String requete = null;
	   try {
		   String requeteClient = " SELECT count(*) AS nbLignes FROM Operation2 "
				   + " WHERE ID_operation LIKE '" + vID_ope +"'";
		   Statement state = laConnexion.createStatement();
		   ResultSet jeuEnreg = state.executeQuery(requeteClient);
       	int nbLignes=0;
       	jeuEnreg.next();
       	nbLignes = jeuEnreg.getInt("nbLignes");
       	bSuppression = true;
       	JOptionPane.showMessageDialog(null, "Opération supprimée" +
        		" Suppression autorisée.",
        		"Résultat", JOptionPane.INFORMATION_MESSAGE);  
       	bSuppression = true;
	   }
	   catch (SQLException e){
       	bSuppression = false;
           JOptionPane.showMessageDialog(null, "Aucune suppression effectuée dans la BD : "
   		    	 + e.getMessage(),
                    "Problème rencontré", JOptionPane.ERROR_MESSAGE);        
       }
	   if (bSuppression == true) {
	        try { 
	        	requete = "DELETE FROM Operation2" +
				  " WHERE ID_operation = '"+vID_ope+"'";
	        	Statement state = laConnexion.createStatement();
	        	int nbEnregSup = state.executeUpdate(requete); 
	            if (nbEnregSup == 0){
	                JOptionPane.showMessageDialog(null, "Aucun enregistrement correspondant.",
	                		"Résultat", JOptionPane.ERROR_MESSAGE);         	
	            }
	        }         
	        catch (SQLException e){
	        	bSuppression = false;
	            JOptionPane.showMessageDialog(null, "Aucune suppression effectuée dans la BD : "
	    		    	 + e.getMessage(),
	                     "Problème rencontré", JOptionPane.ERROR_MESSAGE);        
	        }
   }
	   return bSuppression;
   }
    
    /**
     * Modification d'une opération dans BD
     * @param vID_ope
     * Le nouvel ID opération
     * @param vID_client
     * Le nouvel ID client
     * @param vDate
     * La nouvelle date
     * @param vModePaiement
     * Le nouveau mode de paiement
     * @param vTiers
     * Le nouveau Tiers
     * @param vCategorie
     * La nouvelle catégorie
     * @param vMontant
     * Le nouveau montant
     * @return
     * la modification de l'opération
     */
    public boolean modifierCRUD(String vID_ope, String vID_client, String vDate, String vModePaiement, String vTiers, String vCategorie, double vMontant) {
		boolean bModification = true;    	
		String requete = null;   
		try {
		    requete = "UPDATE Operation2 SET " 
		    		+ "ID_client = '" + vID_client + "',"
		    		+ "date = '" + vDate + "',"
	    			+ "modedepaiement = '" + vModePaiement + "',"
	    	      	+ "tiers = '" + vTiers + "',"
	    			+ "categorie = '" + vCategorie + "',"
	    	      	+ "montant = '" + vMontant + "'"
		            + " WHERE ID_operation = '" + vID_ope + "'"; 
		    Statement prepare = laConnexion.createStatement();
		    prepare.executeUpdate(requete); 
        	prepare.close();
		}         
		catch (SQLException e) {
		    bModification = false;
		    JOptionPane.showMessageDialog(null, "Modification dans la BD non effectuée : " + e.getMessage(), 
			    "Problème rencontré", JOptionPane.ERROR_MESSAGE);
		}
		return bModification;
    }	

    
}