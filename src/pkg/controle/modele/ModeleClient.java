package pkg.controle.modele;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import pkg.entite.Client;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class ModeleClient extends AbstractTableModel {
	/**
	 * Cette classe permet de récupéré les données saisie par l'utilisateur et de les transformer en modèle de table
	 * Elle met à jour le modèle 
	 * Elle notifie les vues d'un changement d'état du model
	 * Elle édite le tableau
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getRowCount() {
		return lesclients.size();
	}

	@Override
	public int getColumnCount() {
		return lesTitres.length;
	}
	
	public String getColumnName (int columnIndex) {
		return lesTitres[columnIndex];
	}

	
	// on récupère les enregistrements provenant de la class Client
	Client instanceClient = new Client();
	private ArrayList<Client> lesclients = instanceClient.getlesEnreg();

	//les en-têtes de colonnes pour le tableau
	private final String[] lesTitres = {"Num_client","Rib", "Nom", "Prenom", "Adresse", "Ville", "Code postale", "Date de création"};
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0:
			return lesclients.get(rowIndex).getID_client();
		case 1:
			return lesclients.get(rowIndex).getRib();
		case 2:
			return lesclients.get(rowIndex).getNom();
		case 3:
			return lesclients.get(rowIndex).getPrenom();
		case 4:
			return lesclients.get(rowIndex).getAdresse();
		case 5:
			return lesclients.get(rowIndex).getVille();
		case 6:
			return lesclients.get(rowIndex).getCodepostale();
		case 7:
			return lesclients.get(rowIndex).getDate_creation();
		default:
			return null;
		}
	}
	
	//Les méthodes fireXXX permettent de mettre à jour les données simultanément dans notre programme
	//donc pas besoin de le fermer et le reouvrir pour trouver les nouvelles données
	
	/**
	 * Cette méthode permet d'ajouter les données personnelles d'une personne dans une liste
	 * @param leClient
	 * Représente l'ensemble des données personnelles de la classe Client
	 */
	public void creerClient(Client leClient) {
		lesclients.add(leClient);
		fireTableRowsInserted(lesclients.size() -1, lesclients.size() -1);
	}
	/**
	 * Cette méthode permet de modifier les données personnelles d'une personne à partir de notre liste
	 * 
	 * @param firstRow
	 * La première ligne du tableau
	 * @param lastRow
	 * La dernière ligne du tableau
	 * @param leClient
	 * Représente l'ensemble des données personnelles de la classe Client
	 */
	public void modifierClient(int firstRow, int lastRow, Client leClient) {
		// set() attend 2 praramètres, le n° de la ligne et l'objet
		lesclients.set(firstRow, leClient);
		fireTableRowsUpdated(firstRow, lastRow);
		
	}
	/**
	 * Cette méthode permet de supprimer toutes les données personnelles d'une personne à partir de notre liste
	 * en se basant sur son indice de rangé
	 * @param rowIndex
	 * Indice de rangé
	 */
	public void supprimerClient(int rowIndex) {
		lesclients.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	
	/**
	 * Cette méthode permet de lire les données personnelles d'une personne à partir de notre liste 
	 * et elle permet également de récupérer ces données afin de les repertoiriés dans le tableau
	 * Elle informe également la vue en cas de changement de données
	 * @param nouvellesDonnees
	 * Les données des clients contenues dans notre liste
	 * 
	 */
	
	// permet de mettre à jour le modèle suite à de nouvelles recherches
		public void lireRecupClient(ArrayList<Client> nouvellesDonnees){
			lesclients = nouvellesDonnees;
			fireTableDataChanged();
		}
	
	/*
	 * utiles pour les renderers qui vont appliquer
	 * un style de présentation des données en fonction de la classe
	 */
	public Class<?> getColumnClass(int columnIndex){
		switch(columnIndex){
		case 0:
			return String.class; //ID_client
		case 1:
			return String.class; //rib
		case 2:
			return String.class; //nom
		case 3:
			return String.class; //prenom
		case 4:
			return String.class; //adresse
		case 5:
			return String.class; //ville
		case 6:
			return int.class; //code postale
		case 7:
			return Date.class; //date de création
		default:
			return Object.class;
		}
	}
	
	/**
	 * Cette Méthode permet de retrouver le numéro de la ligne à partir de l'ID client
	 * @param vID_client
	 * L'ID du client validé par l'utilisateur
	 * @return
	 * numéro de ligne de l'ID client
	 */
	public int getNumLigne (String vID_client) {
		String ID_client = "";
		int NumLigne = 0;
		lesclients.equals(vID_client);
		for (int i = 0; i <lesclients.size(); i++) {
			ID_client = lesclients.get(i).getRib();
			if(ID_client.equals(vID_client)) {
				NumLigne = i;
			}
		}
		return NumLigne;
	}
	
	
	
}