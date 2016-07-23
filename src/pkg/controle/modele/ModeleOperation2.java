package pkg.controle.modele;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import pkg.entite.Client;
import pkg.entite.Operation2;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class ModeleOperation2 extends AbstractTableModel {
	/**
	 * Cette classe classe permet de récupéré les données saisie par l'utilisateur en ce qui concerne les opérations
	 *  et de les transformer en modèle de table
	 * Elle met à jour le modèle 
	 * Elle notifie les vues d'un changement d'état du model
	 * Elle édite le tableau
	 */
	
	 private static final long serialVersionUID = 1L;
	 
	 // On récupère les enregistrements venant de la classe Opération2
	    Operation2 instanceOperation2 = new Operation2();
	    private ArrayList<Operation2> lesOperations2 = instanceOperation2.getLesOperations2();
	    
	    // Nos en-tête de colonne pour notre tableau
	    private final String[] lesTitres = {"N° opération", "N° client","Nom", "Prenom", "Date", "Mode Paiement", "Tiers", "Catégorie", "Montant"};
	    
	    public int getRowCount() {
	    	return lesOperations2.size();
	    }
	    public int getColumnCount() {
	    	return lesTitres.length;
	    }
	    public String getColumnName(int columnIndex) {
	    	return lesTitres[columnIndex];
	    }
	    
	    public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex){
			case 0:
				return lesOperations2.get(rowIndex).getID_ope();
			case 1:
				return lesOperations2.get(rowIndex).getID_client();
			case 2:
				return lesOperations2.get(rowIndex).getNomOpe2();
			case 3:
				return lesOperations2.get(rowIndex).getPrenomOpe2();
			case 4:
				return lesOperations2.get(rowIndex).getDate();
			case 5:
				return lesOperations2.get(rowIndex).getModedepaiement();
			case 6:
				return lesOperations2.get(rowIndex).getTiers();
			case 7:
				return lesOperations2.get(rowIndex).getCategorie();
			case 8:
				return lesOperations2.get(rowIndex).getMontant();
				default:
					return null;
			}

}
	    /*
		 * utiles pour les renderers qui vont appliquer
		 * un style de présentation des données en fonction de la classe
		 */
	    public Class<?> getColumnClass(int columnIndex){
			switch(columnIndex){
			case 0:
				return String.class; // ID opération
			case 1:
				return String.class; // ID client 
			case 2:
				return String.class; // Nom du client
			case 3:
				return String.class; // Prénom du client
			case 4:
				return Date.class; // Date de l'opération
			case 5:
				return String.class; // Mode de paiement de l'opération
			case 6:
				return String.class; // Catégorie de l'opération
			case 7:
				return String.class; // tiers de l'opération
			case 8:
				return Double.class; // Montant de l'opération
			default:
				return Object.class;
			}
		}
	    
	    /**
	     * Cette méthode permet d'ajouter les opérations dans une liste
	     * @param uneOperation2
	     * Représente la opération des clients
	     */
	    public void creerOPS2(Operation2 uneOperation2) {
			lesOperations2.add(uneOperation2);
			fireTableRowsInserted(lesOperations2.size() -1, lesOperations2.size() -1);
	    }
	    /**
	     * Cette méthode permet de supprimer une opération de la liste
	     * @param rowIndex
	     * Indice de rangé
	     */

	    public void supprimerOPS2(int rowIndex) {
			lesOperations2.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
	    }
	    
	    /**
	     * Cette méthode permet de modifier une opération de la liste
	     * @param firstRow
	     * La premère ligne du tableau
	     * @param lastRow
	     * La denière ligne du tableau
	     * @param uneOperation2
	     * Représente les données de la classe Opération2
	     */

	    public void modifierOPS2(int firstRow, int lastRow, Operation2 uneOperation2) {
			lesOperations2.set(firstRow, uneOperation2);
			fireTableRowsUpdated(firstRow, lastRow);
	    }

	    /**
	     * Cette méthode permet de lire les différentes opération à partir de notre liste 
	     * Elle récupérer également ces données afin de les repertoiriés dans le tableau
	 	 * Elle informe également la vue en cas de changement de données
	     * @param nouvellesOperations2
	     * Les données de nos opérations dans la liste
	     */
	    public void lireRecupMOD(ArrayList<Operation2> nouvellesOperations2){
			lesOperations2 = nouvellesOperations2;
			fireTableDataChanged();
	    }
	    
	    /**
	     * Cette méthode permet de retrouver le numéro de ligne à partir de l'ID d'opération
	     * @param vID_ope
	     * @return L'ID d'opération validée par l'utilisateur
	     */
	  //Méthode qui va retrouver le numéro de la ligne à partir du numéro d'opération
		public int getNumLigne (String vID_ope) {
			String ID_operation = "";
			int NumLigne = 0;
			lesOperations2.equals(vID_ope);
			for (int i = 0; i <lesOperations2.size(); i++) {
				ID_operation = lesOperations2.get(i).getID_ope();
				if(ID_operation.equals(vID_ope)) {
					NumLigne = i;
				}
			}
			return NumLigne;
		}
}