package pkg.entite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import pkg.controle.connexion.ControleConnexion;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class Client extends Personne {
	
	/**
	 * Cette classe gère les données des clients
	 * Elle hérite de la classe Personne
	 * Elle transforme aussi les demandes des utilisateurs en requetes SQL pour y effectuer des ajouts, modifications...
	 */
	
	private String ID_client;
	private String rib;
	private String nom;
	private String prenom;
	private String adresse;
	private String ville;
	private int codepostale;
	private Date date;
	
	private static Connection laConnexion = ControleConnexion.getConnexion();
	
	//propriété de type arraylist qui contiendra les enregistrements dans la base de donnée
	private final ArrayList<Client> lesEnreg = new ArrayList<Client>();
	
	//getters
	public String getID_client() {
		return ID_client;
	}
	
	public String getRib() {
	    return rib;
	  }
	
	  public String getNom() {
	    return nom;
	  }
	 
	  public String getPrenom() {
	    return prenom;
	  }
	  
	  public String getAdresse() {
		  return adresse;
	  }
	  
	  public String getVille() {
		  return ville;
	  }
	 
	  public int getCodepostale() {
		  return codepostale;
	  }
	  
	  public Date getDate_creation() {
		  return date;
	  }
	  
	  public ArrayList<Client> getlesEnreg() {
		  return lesEnreg;
	  }
	  
	// Setters
	  public void setID_client (String ID_client) {
		  this.ID_client = ID_client;
	  }
		public void setRib(String rib) {
			this.rib = rib;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public void setAdresse(String adresse) {
			this.adresse=adresse;
		}
		public void setVille(String ville) {
			this.ville=ville;
		}
		public void setCodepostale(int codepostale) {
			this.codepostale=codepostale;
		}
		public void setDate_creation(Date date_creation) {
			this.date = date_creation;
		}
		
		/**
		 * Construcuteur pour la création d'un client
		 * @param vID_client
		 * L'ID client saisi
		 * @param vRib
		 * Le rib saisi
		 * @param vNom
		 * Le nom saisi
		 * @param vPrenom
		 * Le prénom saisi
		 * @param vAdresse
		 * L'adresse saisi
		 * @param vVille
		 * La ville saisi
		 * @param vCodepostale
		 * Le code postale saisi
		 * @param vDateCreation
		 * La date de création saisi
		 */
		public Client(String vID_client, String vRib, String vNom, String vPrenom, String vAdresse, String vVille, int vCodepostale, Date vDateCreation) {
			super (vID_client,vRib,vNom,vPrenom,vAdresse,vVille,vCodepostale);
			this.ID_client = vID_client;
			this.rib = vRib;
			this.nom = vNom;
			this.prenom = vPrenom;
			this.adresse = vAdresse;
			this.ville = vVille;
			this.codepostale = vCodepostale;
			date = vDateCreation;
		}	
		/**
		 * 1er Constructeur
		 * @param vID_client
		 * Le nouvel ID client
		 */
		public Client (String vID_client) {
			super(vID_client);
			this.ID_client = vID_client;
		}
		
		/**
		 * 2eme Constructeur
		 */
		
		public Client() {
			lireRecupCRUD();
		}
		
		/**
		 * Constructeur pour la création d'un client
		 * @param vID_client
		 * L'ID client saisi
		 * @param vRib
		 * Le rib saisi
		 * @param vNom
		 * Le nom saisi
		 * @param vPrenom
		 * Le prénom saisi
		 * @param vAdresse
		 * L'adresse saisi
		 * @param vVille
		 * La ville saisi
		 * @param vCodepostale
		 * Le code postale saisi
		 */
		public Client (String vID_client, String vRib, String vNom, String vPrenom, String vAdresse, String vVille, int vCodepostale) {
			ID_client = vID_client;
			rib = vRib;
			nom = vNom;
			prenom = vPrenom;
			adresse = vAdresse;
			ville = vVille;
			codepostale = vCodepostale;
		}
		
		/**
		 * Méthode qui lit et récupère la liste des clients dans la BD
		 */
		public void lireRecupCRUD() {
	    	try {
				Statement state = laConnexion.createStatement();
				ResultSet rs = state
						.executeQuery("SELECT * " +
								"FROM Personne ORDER BY nom");
				while (rs.next()) {
					String ID_client = rs.getString("ID_client");
		            String rib = rs.getString("rib");
		            String nom = rs.getString("nom");
		            String prenom = rs.getString("prenom");
	                String adresse = rs.getString("adresse");
	                String ville = rs.getString("ville");
	                int codepostale = rs.getInt("codepostale");
		            Date datedecreation = rs.getDate("date");
		            lesEnreg.add(new Client(ID_client, rib, nom, prenom, adresse, ville, codepostale, datedecreation));  
		        }			
			} catch (SQLException e) {
			    JOptionPane.showMessageDialog(null, 
			    	"Problème rencontré : " + e.getMessage(),
			    	"Résultat", JOptionPane.ERROR_MESSAGE);
			}
	    }	
		
		/**
		 * Ajouter un nouveau client dans la BD
		 * @param vID_client
		 * Le nouvel ID client
		 * @param vRib
		 * Le nouveau rib
		 * @param vNom
		 * Le nouveau nom
		 * @param vPrenom
		 * Le nouveau prénom
		 * @param vAdresse
		 * la nouvelle adresse
		 * @param vVille
		 * la nouvelle ville
		 * @param vCodepostale
		 * le nouveau codepostale
		 * @param strDateMySQL
		 * la nouvelle date
		 * @return
		 * l'ajout du client
		 */
		public boolean creerCRUD (String vID_client, String vRib, String vNom, String vPrenom, String vAdresse, String vVille, int vCodepostale, String strDateMySQL) {
			boolean bCreation = false;
			String requete = null;
			 try {
		        	requete = "INSERT INTO Personne(ID_client, rib, nom, " +
		        			"prenom, adresse, ville, codepostale, date) VALUES('"
		        			+ vID_client	+"', '"
		        			+ vRib			+ "','"
		        			+ vNom			+ "','"
		        			+ vPrenom		+ "','"
		        			+ vAdresse		+ "','"
		        			+ vVille		+ "','"
		        			+ vCodepostale  + "','"
		        			+ strDateMySQL		+ "'"
		        			+ ")";    	
		        	Statement state = laConnexion.createStatement();
		        	state.executeUpdate(requete);        	
		        	bCreation = true;
		        }         
		        catch (SQLException e){
		            JOptionPane.showMessageDialog(null, 
		    		    	"Problème rencontré : " + e.getMessage(),
		            		"Résultat", JOptionPane.ERROR_MESSAGE);
		        }
				return bCreation;        
			}
		
		/**
		 * Modification d'un client dans la BD
		 * @param vID_client
		 * Le nouvel ID client
		 * @param vRib
		 * Le nouveau Rib
		 * @param vNom
		 * Le nouveau nom
		 * @param vPrenom
		 * Le nouveau prenom
		 * @param vAdresse
		 * La nouvelle adresse
		 * @param vVille
		 * La nouvelle ville
		 * @param vCodepostale
		 * Le nouveau code postale
		 * @param strDateMySQL
		 * La nouvelle date
		 * @return
		 * la modification des données
		 */
		public boolean modifierCRUD (String vID_client, String vRib, String vNom, String vPrenom, String vAdresse, String vVille, int vCodepostale,  String strDateMySQL) {		    
	    	boolean bModification = true;    	
	        String requete = null;   
	        try {
	        	requete = "UPDATE Personne SET "
	        				+ "ID_client = '" + vID_client + "',"
				        	+ "nom = '" + vNom +"'," 
				        	+ "prenom = '" + vPrenom + "',"
				        	+ "adresse = '" + vAdresse + "',"
				        	+ "ville = '" + vVille + "',"
				        	+ "codepostale = '" + vCodepostale + "',"
				            + "date = '" + strDateMySQL + "'"
				            + " WHERE rib = '" + vRib + "'"; 
	            Statement state = laConnexion.createStatement();
	        	state.executeUpdate(requete); 
	        	state.close();
	        }         
	        catch (SQLException e) {
	        	bModification = false;
	            JOptionPane.showMessageDialog(null, 
	            		"Modification dans la BD non effectuée : "
	    		    	 + e.getMessage(),
	                     "Problème rencontré", JOptionPane.ERROR_MESSAGE);
	        }
	        return bModification;
		}	
		
		/**
		 * Suppression d'un client dans la BD
		 * @param vID_client
		 * l'ID client saisi
		 * @return
		 * la suppression d'une ligne
		 */
		public boolean supprimerCRUD(String vID_client){
	    	boolean bSuppression = true;    	
	        String requete = null;        
	        // Vérifier avant qu'il n'existe aucune opération
	        try { 
	            String requeteClient = "SELECT count(*) AS nbLignes FROM Personne "
	            		 + " WHERE nom LIKE '" + vID_client +"'";
	        	Statement state = laConnexion.createStatement();
	        	ResultSet jeuEnreg = state.executeQuery(requeteClient);
	        	int nbLignes=0;
	        	jeuEnreg.next();
	        	nbLignes = jeuEnreg.getInt("nbLignes");
	        	if(nbLignes > 0) {
	        		bSuppression = false;
	                JOptionPane.showMessageDialog(null, "Il existe des opérations pour ce client." +
	                		" Suppression interdite.",
	                		"Résultat", JOptionPane.ERROR_MESSAGE);
	        		bSuppression = false;
	        	} else {
	                JOptionPane.showMessageDialog(null, "Aucune opération pour ce client." +
	                		" Suppression autorisée.",
	                		"Résultat", JOptionPane.INFORMATION_MESSAGE);  
	        	}
	        }         
	        catch (SQLException e){
	        	bSuppression = false;
	            JOptionPane.showMessageDialog(null, "Aucune suppression effectuée dans la BD : "
	    		    	 + e.getMessage(),
	                     "Problème rencontré", JOptionPane.ERROR_MESSAGE);        
	        }
	        if (bSuppression == true) {
		        try { 
		        	requete = "DELETE FROM Personne" +
					  " WHERE ID_client = '"+vID_client+"'";
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
		 * Recherche dans la base de données à partir d'une chaine de caractère
		 * @param vID_client
		 * Id du client saisi
		 * @param vRib
		 * rib saisi
		 * @param vNom
		 * nom saisi
		 * @param vPrenom
		 * prénom saisi
		 * @return
		 * le résultat de la recherche
		 */
		public ArrayList<Client> chercherCRUD(String vID_client, String vRib, String vNom, String vPrenom) {
			
			if (vID_client.equals("")) {
				vID_client = "%";
			}
	        if(vRib.equals("")) {
	        	vRib = "%";
	        }
	        if(vNom.equals("")) {
	        	vNom = "%";
	        }
	        if(vPrenom.equals("")) {
	        	vPrenom = "%";
	        }        
	        
	        String requete = "SELECT * FROM Personne"
	        						 + "WHERE ID_client LIKE '" + vID_client +"'"
			        				 + " AND rib LIKE '" + vRib +"'"
			        				 + " AND nom LIKE '" + vNom + "'"
			        				 + " AND prenom LIKE '" + vPrenom + "'";
	    	try {
				Statement state = laConnexion.createStatement();
				ResultSet rs = state.executeQuery(requete);
				while (rs.next()) {
					String ID_client = rs.getString("ID_client");
		            String rib = rs.getString("rib");
		            String nom = rs.getString("nom");
		            String prenom = rs.getString("prenom");
	                String adresse = rs.getString("adresse");
	                String ville = rs.getString("ville");
	                int codepostale = rs.getInt("codepostale");
		            Date date_creation = rs.getDate("date");
		            // ajout à l'Arraylist
		            lesEnreg.add(new Client(ID_client, rib, nom, prenom, adresse, ville, codepostale, date_creation));  
		        }			
			} catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Problème rencontré : "
	    		    	 + e.getMessage(),
	                     "Résultat", JOptionPane.ERROR_MESSAGE);        
			}
	    	return lesEnreg;
	    }	
		
		/**
		 * Recherche dans la BD --> avec une chaîne de caracactère(s) quelconque
		 * @param recherche
		 * @return le résultat de la recharche
		 */
			    public ArrayList<Client> chercherCRUD(String recherche) {
		String requete = "SELECT * FROM Personne"
			+ " OR ID_client LIKE '%" + recherche + "%'"
			+ " OR rib LIKE '%" + recherche +"%'"
			+ " OR nom LIKE '%" + recherche + "%'"
			+ " OR prenom LIKE '%" + recherche + "%'";
		try {
		    Statement state = laConnexion.createStatement();
		    ResultSet rs = state.executeQuery(requete);
		    while (rs.next()) {
		    	String ID_client = rs.getString("ID_client");
		    	String rib = rs.getString("rib");
	            String nom = rs.getString("nom");
	            String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String ville = rs.getString("ville");
                int codepostale = rs.getInt("codepostale");
	            Date date_creation = rs.getDate("date");
	            // ajout à l'Arraylist
	            lesEnreg.add(new Client(ID_client, rib, nom, prenom, adresse, ville, codepostale, date_creation)); 
		    }			
		} catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Problème rencontré : "
			    	 + e.getMessage(),
	                 "Résultat", JOptionPane.ERROR_MESSAGE);    
		}
		return lesEnreg;
	    }
		}

	  
