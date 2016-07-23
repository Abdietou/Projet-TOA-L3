package pkg.dialogue;

import java.awt.Cursor;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;

import pkg.entite.Client;

import pkg.controle.modele.ModeleClient;
import pkg.controle.utilitaires.GestionDates;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class FenFicheClient extends JFrame {
	/**
	 * Cette classe est une interface grahique qui permet de modifier ou ajouter des clients
	 */
	private static final long serialVersionUID = 1L;
	
	private ModeleClient leModeleClients = null;
	private String actionDemande = "";
	
	private JPanel conteneur;
	
	private JLabel jl_action;
	private JScrollPane jspane;
	private JTable table;
	
	private JTextField jt_id_client;
	private JTextField jt_rib;
	private JTextField jt_nom;
	private JTextField jt_prenom;
	private JTextField jt_adresse;
	private JTextField jt_ville;
	private JTextField jt_codepostale;
	private JTextField jt_datecreation;
	
	private JLabel jl_titreclient;
	private JLabel jl_rib;
	private JLabel jl_datecreation;
	private JLabel jl_nom;
	private JLabel jl_prenom;
	private JLabel jl_adresse;
	private JLabel jl_ville;
	private JLabel jl_codepostale;
	private JLabel jl_retour;
	private JLabel jl_annuler;
	private JLabel jl_num_client;
	
	//public JLabel getJl_titreclient() {}
	//public JTextField getJt_rib() {}
	//public JTextField getJt_datecreation() {}


	// Constructeur
	// ************
	public FenFicheClient(ModeleClient modeleClientsTransmis, String demande) {
		super();
		leModeleClients = modeleClientsTransmis;
		actionDemande = demande;
		
		setBounds(100, 100, 1027, 642);
		conteneur = new JPanel();
		conteneur.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(conteneur);
		conteneur.setLayout(null);
		
		conteneur.add(getJLab_Action());
		conteneur.add(getJl_titreclient());
		conteneur.add(getJl_datecreation());
		conteneur.add(getJt_datecreation());
		conteneur.add(getJl_rib());
		conteneur.add(getJt_rib());
		conteneur.add(getJl_nom());
		conteneur.add(getJt_nom());
		conteneur.add(getJl_prenom());
		conteneur.add(getJt_prenom());
		conteneur.add(getJl_adresse());
		conteneur.add(getJt_adresse());
		conteneur.add(getJl_ville());
		conteneur.add(getJt_ville());
		conteneur.add(getJl_codepostale());
		conteneur.add(getJt_codepostale());
		conteneur.add(getJScrollPane());
		conteneur.add(getJTable());
		conteneur.add(getJl_retour());
		conteneur.add(getJl_annuler());
		conteneur.add(getJl_num_client());
		conteneur.add(getJt_id_client());
		
		setTitle("Clients");		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/gestion/logoEclipse.png")));
		setLocationRelativeTo(null);
		setResizable(false);
	}

	
	// Label Action
		// --------------
		public JLabel getJLab_Action() {
			if (jl_action == null) {
				jl_action = new JLabel("ACTION : Je vais changer...");
				jl_action.setIcon(null);
				jl_action.setForeground(Color.WHITE);
				jl_action.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jl_action.setBounds(22, 13, 200, 47);
				
				jl_action.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						String vID_client = jt_id_client.getText();
						String vRib = jt_rib.getText();
						String vNom = jt_nom.getText();
						String vPrenom = jt_prenom.getText();									
						String vAdresse = jt_adresse.getText();
						String vVille = jt_ville.getText();
						String vCP = jt_codepostale.getText();
						int vCodepostale = Integer.parseInt(vCP);
						String strDate =jt_datecreation.getText();
						
						//AJOUT
						if (actionDemande.equals("Ajouter")){
							if (!vID_client.equals("")){
								// variable String EN à partir  du String FR --> yyyy-MM-dd
								String strDateMySQL = GestionDates.chaineDateFRversEN(strDate);
								//pour la classe Client
								Date dtDate = new Date();
								dtDate = GestionDates.dateJavaEnDateSQL(dtDate);
								// création d'une instance client pour obtenir le CRUD	
								Client leClient = new Client(vID_client,vRib, vNom, vPrenom, vAdresse, vVille, vCodepostale, (java.sql.Date) dtDate);
								// 1. sauvegarde d'abord dans la BD
								boolean bCreation = false ;
								bCreation = leClient.creerCRUD(vID_client,vRib, vNom, vPrenom, vAdresse, vVille, vCodepostale, strDateMySQL);
								// 2. puis ajout dans le modèle
								if (bCreation) {				
									leModeleClients.creerClient(leClient);
								}
								
								// remise à vide des champs pour un nouvel ajout
								jt_id_client.setText("");
								jt_rib.setText("");
								jt_nom.setText("");
								jt_prenom.setText("");
								jt_adresse.setText("");
								jt_ville.setText("");
								jt_codepostale.setText("");
								jt_datecreation.setText("");
								// focus sur le textfield pour faciliterla saisie
								jt_id_client.requestFocus();													
								// on remet la date du jour par défaut
								dtDate = new Date();
								jt_datecreation.setText(GestionDates.dateEnChaineFR(dtDate));
							}
							else {
				                 JOptionPane.showMessageDialog(null, "La saisie du Numero client"
				                  		+ " est obligatoire", 
				                          "Vérifiez votre saisie", JOptionPane.ERROR_MESSAGE);
							}
						}
						//MODIFICATION
						if (actionDemande.equals("Modifier")){
							//Date pour MySQL
							String strDateMySQL = GestionDates.chaineDateFRversEN(strDate);
							// mise du String date FR en String date EN
							strDate = GestionDates.chaineDateFRversEN(strDate);
							try {
								//Date pour le constructeur client
								Date dtDate = GestionDates.chaineENversDateJava(strDate);
								dtDate = GestionDates.dateJavaEnDateSQL(dtDate);
								Client leClient = new Client(vID_client, vRib, vNom, vPrenom, vAdresse, vVille, vCodepostale, (java.sql.Date) dtDate);
								boolean bCreation = false;
								bCreation = leClient.modifierCRUD(vID_client, vRib, vNom, vPrenom, vAdresse, vVille, vCodepostale, strDateMySQL);
							
								if (bCreation) {
									int numLigne = leModeleClients.getNumLigne(vRib);
									leModeleClients.setValueAt(vID_client, numLigne, 0);
									leModeleClients.setValueAt(vRib, numLigne, 1);
									leModeleClients.setValueAt(vNom, numLigne, 2);
									leModeleClients.setValueAt(vPrenom, numLigne, 3);
									leModeleClients.setValueAt(vAdresse, numLigne, 4);
									leModeleClients.setValueAt(vVille, numLigne, 5);
									leModeleClients.setValueAt(vCodepostale, numLigne, 6);
									leModeleClients.setValueAt(dtDate, numLigne, 7);
									leModeleClients.modifierClient(numLigne, numLigne, leClient);
									dispose();
									
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
						
						// RECHERCHE
						if(actionDemande.equals("Rechercher")) {
							Client leClient = new Client(vID_client);
							// Recherche dans la BD
							ArrayList<Client> nouvelleListe = leClient.chercherCRUD(vID_client, vRib, vNom, vPrenom);
							//MAJ du modele
							leModeleClients.lireRecupClient(nouvelleListe);
						} 
					}
				});
				}
				return jl_action;
			}


		public JLabel getJl_titreclient() {
			if (jl_titreclient == null) {
				jl_titreclient = new JLabel();
				jl_titreclient.setText("Je vais changer...");
				jl_titreclient.setFont(new Font("Segoe UI", Font.BOLD, 24));
				jl_titreclient.setBounds(292, 13, 428, 67);
				jl_titreclient.setIcon(new ImageIcon(getClass().getResource("/images/gestion/clientTitre.png")));
			}
			return jl_titreclient;
		}


		public JTextField getJt_datecreation() {
			if (jt_datecreation == null) {
				jt_datecreation = new JTextField();
				jt_datecreation.setHorizontalAlignment(SwingConstants.CENTER);
				jt_datecreation.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jt_datecreation.setBounds(757, 97, 75, 28);
				jt_datecreation.setColumns(10);
			}
			return jt_datecreation;
		}
		
		private JLabel getJl_datecreation() {
			if (jl_datecreation == null) {
				jl_datecreation = new JLabel();
				jl_datecreation.setText("Date Création");
				jl_datecreation.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_datecreation.setBounds(643, 108, 112, 18);
			}
			return jl_datecreation;
		}
		
		 private JLabel getJl_num_client() {
			 if (jl_num_client == null) {
				 jl_num_client = new JLabel();
				 jl_num_client.setText("Numero client");
				 jl_num_client.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				 jl_num_client.setBounds(277, 226, 70, 15);
			 }
			 return jl_num_client;
		 }
		 
		 public JTextField getJt_id_client() {
				if (jt_id_client == null) {
					jt_id_client = new JTextField();
					jt_id_client.setForeground(new Color(26, 88, 131));
					jt_id_client.setFont(new Font("Segoe UI", Font.PLAIN, 14));
					jt_id_client.setBounds(375,215,118,28);
				}
				return jt_id_client;
			}
		
		private JLabel getJl_rib() {
			if (jl_rib == null) {
				jl_rib = new JLabel();
				jl_rib.setText("RIB");
				jl_rib.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_rib.setBounds(277, 108, 98, 18);
			}
			return jl_rib;
		}

		private JTextField getJt_rib() {
			if (jt_rib == null) {
				jt_rib = new JTextField();
				jt_rib.setForeground(new Color(26, 88, 131));
				jt_rib.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jt_rib.setBounds(375, 97, 232, 28);
			}
			return jt_rib;
		}
		
		private JLabel getJl_nom() {
			if (jl_nom == null) {
				jl_nom = new JLabel();
				jl_nom.setText("Nom");
				jl_nom.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_nom.setBounds(277, 138, 98, 18);
			}
			return jl_nom;
		}
		
		private JTextField getJt_nom() {
			if (jt_nom == null) {
				jt_nom = new JTextField();
				jt_nom.setForeground(new Color(26, 88, 131));
				jt_nom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jt_nom.setBounds(375, 127, 232, 28);
			}
			return jt_nom;
		}
		
		private JLabel getJl_prenom() {
			if (jl_prenom == null) {
				jl_prenom = new JLabel();
				jl_prenom.setText("Prénom");
				jl_prenom.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_prenom.setBounds(643, 138, 112, 18);
			}
			return jl_prenom;
		}
		
		private JTextField getJt_prenom() {
			if (jt_prenom == null) {
				jt_prenom = new JTextField();
				jt_prenom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jt_prenom.setBounds(757, 127, 220, 28);
			}
			return jt_prenom;
		}
		private JLabel getJl_adresse() {
			if (jl_adresse == null) {
				jl_adresse = new JLabel();
				jl_adresse.setText("Adresse");
				jl_adresse.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_adresse.setBounds(277, 167, 86, 18);
			}
			return jl_adresse;
		}
		
		private JTextField getJt_adresse() {
			if (jt_adresse == null) {
				jt_adresse = new JTextField();
				jt_adresse.setForeground(new Color(26, 88, 131));
				jt_adresse.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jt_adresse.setBounds(375, 156, 602, 28);
			}
			return jt_adresse;
		}
		
		private JLabel getJl_ville() {
			if (jl_ville == null) {
				jl_ville = new JLabel();
				jl_ville.setText("Ville");
				jl_ville.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_ville.setBounds(643, 196, 102, 18);
			}
			return jl_ville;
		}
		
		private JTextField getJt_ville() {
			if (jt_ville == null) {
				jt_ville = new JTextField();
				jt_ville.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jt_ville.setBounds(757, 186, 220, 28);
			}
			return jt_ville;
		}
		
		private JLabel getJl_codepostale() {
			if (jl_codepostale == null) {
				jl_codepostale = new JLabel();
				jl_codepostale.setText("Code postal");
				jl_codepostale.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_codepostale.setBounds(277, 196, 98, 18);
			}
			return jl_codepostale;
		}
		
		private JTextField getJt_codepostale() {
			if (jt_codepostale == null) {
				jt_codepostale = new JTextField();
				jt_codepostale.setForeground(new Color(26, 88, 131));
				jt_codepostale.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jt_codepostale.setBounds(375, 185, 232, 28);
			}
			return jt_codepostale;
		}
		
		//Setters
		public void setJt_id_client (String vID_client) {
			jt_id_client.setText(vID_client);
		}
		public void setJt_rib (String vRib) {
			jt_rib.setText(vRib);
		}
		public void setJt_nom (String vNom) {
			jt_nom.setText(vNom);
		}
		public void setJt_prenom(String vPrenom) {
			jt_prenom.setText(vPrenom);
		}
		public void setJt_adresse(String vAdresse) {
			jt_adresse.setText(vAdresse);
		}
		public void setJt_ville(String vVille) {
			jt_ville.setText(vVille);
		}
		public void setJt_codepostale(String vCodepostale) {
			jt_codepostale.setText(String.valueOf(vCodepostale));
		}
		public void setJt_datecreation(String vDate) {
			jt_datecreation.setText(vDate);
		}
		
		private JScrollPane getJScrollPane() {
			if (jspane == null) {
				jspane = new JScrollPane();
				jspane.setBounds(256, 381, 741, 143);
				jspane.setViewportView(getJTable());
			}
			return jspane;
		}
		
		// JTable
		// ******
		private JTable getJTable() {
			if (table == null) {
				table = new JTable();
				// ****************************************
				table = new JTable(leModeleClients);
				// ****************************************
			}
			return table;
		}	
		
		public JLabel getJl_retour() {
			if (jl_retour == null) {
				jl_retour = new JLabel("Retour au menu Clients");
				jl_retour.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				jl_retour.setIcon(new ImageIcon(FenFicheClient.class.getResource("/images/gestion/retour.png")));
				jl_retour.setForeground(Color.BLUE);
				jl_retour.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jl_retour.setBounds(22, 519, 210, 47);
				jl_retour.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			return jl_retour;
		}
		
		private JLabel getJl_annuler() {
			if (jl_annuler == null) {
				jl_annuler = new JLabel("Annuler");
				jl_annuler.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						dispose();
					}
				});
				jl_annuler.setForeground(Color.BLUE);
				jl_annuler.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jl_annuler.setBounds(22, 73, 200, 47);
				jl_annuler.setIcon(new ImageIcon(FenFicheClient.class.getResource("/images/gestion/annuler.png")));
			}
			return jl_annuler;
		}
}
		
			
		
		
		

				