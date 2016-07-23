package pkg.dialogue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import pkg.controle.modele.ModeleClient;
import pkg.controle.modele.RendreGras;
import pkg.controle.modele.RendreDate;
import pkg.controle.utilitaires.GestionDates;
import pkg.etat.JasperMySQL_Par;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class MenuClient extends JFrame implements TableModelListener{
	/**
	 * Cette classe est une interface graphique
	 * Elle permet d'accerder aux données des clients et d't faire divers opérations
	 */
	 private static final long serialVersionUID = 1L;
	 
	// propriétés non graphiques
		// -------------------------
		private ModeleClient leModeleClients = new ModeleClient();
		
	 private JTextField jt_num_client;
	 private JTextField jt_rib;
	 private JTextField jt_nom;
	 private JTextField jt_prenom;
	 private JTextField jt_adresse;
	 private JTextField jt_codepostale;
	 private JTextField jt_datecreation;
	 private JTextField jt_ville;
	 
	 private JPanel conteneur;
	 
	 private JLabel jl_num_client;
	 private JLabel jl_rib;
	 private JLabel jl_nom;
	 private JLabel jl_prenom;
	 private JLabel jl_adresse;
	 private JLabel jl_codepostale;
	 private JLabel jl_datecreation;
	 private JLabel jl_ville;
	 private JLabel jl_titreclient;
	 private JLabel jl_trier;
	 private JLabel jl_ajouter;
	 private JLabel jl_modifier;
	 private JLabel jl_supprimer;
	 private JLabel jl_rechercher;
	 private JLabel jl_apercu;
	 private JLabel jl_imprimer;
	 private JLabel jl_exporter;
	 private JLabel jl_retour;
	 
	 
	 private JComboBox <String> jcmb_trier;
	 
	 private JScrollPane Jscrollpane;
	 private JTable table;
	 
	 private ModeleClient letableauClients = new ModeleClient();
	 
	 public MenuClient(){
		 super();
		 leModeleClients.addTableModelListener(this);
		 
		 setBounds(100, 100, 1027, 642);
			conteneur = new JPanel();
			conteneur.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(conteneur);
			conteneur.setLayout(null);
			
			conteneur.add(getJl_titreclient());
			conteneur.add(getJl_num_client());
			conteneur.add(getJl_rib());
			conteneur.add(getJl_nom());
			conteneur.add(getJl_datecreation());
			conteneur.add(getJl_adresse());
			conteneur.add(getJl_CP());
			conteneur.add(getJl_prenom());
			conteneur.add(getJl_ville());
			conteneur.add(getJl_ajouter());
			conteneur.add(getJl_modifier());
			conteneur.add(getJl_supprimer());
			conteneur.add(getJl_rechercher());
			conteneur.add(getjl_apercu());
			conteneur.add(getJl_imprimer());
			conteneur.add(getJl_exporter());
			conteneur.add(getJl_retour());
			
			conteneur.add(getJt_num_client());
			conteneur.add(getJt_rib());
			conteneur.add(getJt_nom());
			conteneur.add(getJt_prenom());
			conteneur.add(getJt_datecreation());
			conteneur.add(getJt_adresse());
			conteneur.add(getJt_CP());
			conteneur.add(getJt_ville());
			
			conteneur.add(getJScrollPane());
			
			
			setTitle("Clients");		
			setLocationRelativeTo(null);
			setResizable(false);
			
			//rendre gras pour les colonnes numero client
			table.getColumnModel().getColumn(0).setCellRenderer(new RendreGras());
		 
	 }
	 
	 private JLabel getJl_titreclient() {
			if (jl_titreclient == null) {
				jl_titreclient = new JLabel();
				jl_titreclient.setText("Gestion des clients");
				jl_titreclient.setFont(new Font("Segoe UI", Font.BOLD, 24));
				jl_titreclient.setBounds(292, 13, 428, 67);
			}
			return jl_titreclient;
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
	 
	 private JLabel getJl_rib() {
			if (jl_rib == null) {
				jl_rib = new JLabel();
				jl_rib.setText("R I B");
				jl_rib.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_rib.setBounds(277, 108, 98, 18);
			}
			return jl_rib;
	 }
	 
	 private JLabel getJl_nom() {
			if (jl_nom == null) {
				jl_nom = new JLabel();
				jl_nom.setText("N o m");
				jl_nom.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_nom.setBounds(277, 138, 98, 18);
			}
			return jl_nom;
		}
	 
	 private JLabel getJl_datecreation() {
			if (jl_datecreation == null) {
				jl_datecreation = new JLabel();
				jl_datecreation.setText("D a t e  c r é a t i o n");
				jl_datecreation.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_datecreation.setBounds(643, 108, 112, 18);
			}
			return jl_datecreation;
		}
	 
	 private JLabel getJl_adresse() {
			if (jl_adresse == null) {
				jl_adresse = new JLabel();
				jl_adresse.setText("A d r e s s e");
				jl_adresse.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_adresse.setBounds(277, 167, 86, 18);
			}
			return jl_adresse;
		}
	 
	 private JLabel getJl_CP() {
			if (jl_codepostale == null) {
				jl_codepostale = new JLabel();
				jl_codepostale.setText("C o d e  p o s t a l");
				jl_codepostale.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_codepostale.setBounds(277, 196, 98, 18);
			}
			return jl_codepostale;
		}
	 
	 private JLabel getJl_prenom() {
			if (jl_prenom == null) {
				jl_prenom = new JLabel();
				jl_prenom.setText("P r é n o m");
				jl_prenom.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_prenom.setBounds(643, 138, 112, 18);
			}
			return jl_prenom;
		}
		private JLabel getJl_ville() {
			if (jl_ville == null) {
				jl_ville = new JLabel();
				jl_ville.setText("V i l l e");
				jl_ville.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_ville.setBounds(643, 196, 102, 18);
			}
			return jl_ville;
		}
		
		private JTextField getJt_rib() {
			if (jt_rib == null) {
				jt_rib  = new JTextField();
				jt_rib.setBackground(Color.WHITE);
				jt_rib.setEditable(false);
				jt_rib.setForeground(new Color(26, 88, 131));
				jt_rib.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jt_rib.setBounds(375, 97, 232, 28);
			}
			return jt_rib;
		}
		
		private JTextField getJt_num_client() {
			if (jt_num_client == null) {
				jt_num_client = new JTextField();
				jt_num_client.setBackground(Color.WHITE);
				jt_num_client.setEditable(false);
				jt_num_client.setForeground(new Color(26, 88, 131));
				jt_num_client.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jt_num_client.setBounds(375,215,118,28);
			}
			return jt_num_client;
		}
		
		private JTextField getJt_nom() {
			if (jt_nom == null) {
				jt_nom = new JTextField();
				jt_nom.setBackground(Color.WHITE);
				jt_nom.setEditable(false);
				jt_nom.setForeground(new Color(26, 88, 131));
				jt_nom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jt_nom.setBounds(375, 127, 232, 28);
			}
			return jt_nom;
		}
		
		private JTextField getJt_prenom() {
			if (jt_prenom == null) {
				jt_prenom = new JTextField();
				jt_prenom.setBackground(Color.WHITE);
				jt_prenom.setEditable(false);
				jt_prenom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jt_prenom.setBounds(757, 127, 220, 28);
			}
			return jt_prenom ;
		}
		
		private JTextField getJt_datecreation() {
			if (jt_datecreation == null) {
				jt_datecreation = new JTextField();
				jt_datecreation.setBackground(Color.WHITE);
				jt_datecreation.setEditable(false);
				jt_datecreation.setHorizontalAlignment(SwingConstants.CENTER);
				jt_datecreation.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jt_datecreation.setBounds(757, 97, 75, 28);
				jt_datecreation.setColumns(10);
			}
			return jt_datecreation;
		}
		
		private JTextField getJt_adresse() {
			if (jt_adresse == null) {
				jt_adresse = new JTextField();
				jt_adresse.setBackground(Color.WHITE);
				jt_adresse.setEditable(false);
				jt_adresse.setForeground(new Color(26, 88, 131));
				jt_adresse.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jt_adresse.setBounds(375, 156, 602, 28);
			}
			return jt_adresse;
		}
		
		private JTextField getJt_CP() {
			if (jt_codepostale == null) {
				jt_codepostale = new JTextField();
				jt_codepostale.setBackground(Color.WHITE);
				jt_codepostale.setEditable(false);
				jt_codepostale.setForeground(new Color(26, 88, 131));
				jt_codepostale.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jt_codepostale.setBounds(375, 185, 232, 28);
			}
			return jt_codepostale ;
		}
		
		private JTextField getJt_ville() {
			if (jt_ville == null) {
				jt_ville  = new JTextField();
				jt_ville .setBackground(Color.WHITE);
				jt_ville .setEditable(false);
				jt_ville .setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jt_ville .setBounds(757, 186, 220, 28);
			}
			return jt_ville ;
		}
		
		private JScrollPane getJScrollPane() {
			if (Jscrollpane == null) {
				Jscrollpane = new JScrollPane();
				Jscrollpane.setBounds(256, 381, 741, 143);
				Jscrollpane.setViewportView(getJTable());
			}
			return Jscrollpane;
		}
		
		// JTable
		// ******
		private JTable getJTable() {
			if (table == null) {
				// ****************************************
				table = new JTable(letableauClients);
				// ****************************************
				table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(MouseEvent e) {				
					int NumLigne;
					// gestion du SIMPLE-CLIC sur une ligne de la table
					// pour un report des données
					// dans les champs correspondants
					NumLigne = table.getSelectedRow();
					jt_num_client.setText(String.valueOf(table.getValueAt(NumLigne, 0)));
					jt_rib.setText(String.valueOf(table.getValueAt(NumLigne, 1)));
					jt_nom.setText(String.valueOf(table.getValueAt(NumLigne, 2)));
					jt_prenom.setText(String.valueOf(table.getValueAt(NumLigne, 3)));
					jt_adresse.setText(String.valueOf(table.getValueAt(NumLigne, 4)));
					jt_ville.setText(String.valueOf(table.getValueAt(NumLigne, 5)));
					jt_codepostale.setText(String.valueOf(table.getValueAt(NumLigne, 6)));
					//jt_datecreation.setText(String.valueOf(table.getValueAt(NumLigne, 6)));
					// la valeur date récupérée est au format yyyy-MM-dd
					String strDate = String.valueOf(table.getValueAt(NumLigne, 7));
					// on la met au format dd-MM-yyyy
					strDate = GestionDates.chaineDateENversFR(strDate);
					jt_datecreation.setText(strDate);
					
					// gestion du DOUBLE-CLIC sur une ligne de la table
					// pour une modif en mode fiche
					if (e.getClickCount() == 2) {
						prepaModif();   	
					}
				}
			});
		}
			return table;
}
		
		
		private JLabel getJl_ajouter() {
			if (jl_ajouter == null) {
				
				jl_ajouter = new JLabel ("Ajouter");
				jl_ajouter.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/ajouter.png")));
				jl_ajouter.setForeground(Color.BLUE);
				jl_ajouter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jl_ajouter.setBounds(22, 13, 200, 47);
				
				jl_ajouter.addMouseListener(new MouseAdapter() {	
					public void mouseClicked(MouseEvent arg0) {
						// --- AJOUT EN MODE FICHE ---
						// création de la fenêtre
						FenFicheClient laFenetre = new FenFicheClient(leModeleClients, "Ajouter");
						// on change le titre et son icône et le libellé du bouton
						laFenetre.getJl_titreclient().setText("Ajout d'un client");
						laFenetre.getJLab_Action().setIcon(new ImageIcon(FenFicheClient.class.getResource("/images/gestion/ajouter.png")));		
						laFenetre.getJLab_Action().setText("Sauvegarder");									  
						// focus sur le numero client
						laFenetre.getJt_id_client().requestFocus(true);
						// on met par défaut la date du jour
						Date dtDate = new Date();
						String strDate = GestionDates.dateEnChaineFR(dtDate);
						laFenetre.getJt_datecreation().setText(strDate);
						laFenetre.getJt_datecreation().setEditable(false);
						laFenetre.setVisible(true);
						table.setModel(leModeleClients);
						
					}
				});
			}
			return jl_ajouter;
		}
		
		private JLabel getJl_modifier() {
			if (jl_modifier == null) {
				
				jl_modifier = new JLabel("Modifier");
				jl_modifier.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/modifier.png")));
				jl_modifier.setForeground(Color.BLUE);
				jl_modifier.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jl_modifier.setBounds(22, 133, 200, 47);
				jl_modifier.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						prepaModif();
						//mise à jour de la table après la modification
						table.setModel(leModeleClients);
					}								
				});
			}
			return jl_modifier;
		}
		
		private JLabel getJl_supprimer() {
			if (jl_supprimer == null) {
				
				jl_supprimer = new JLabel ("Supprimer");
				jl_supprimer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jl_supprimer.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/supprimer.png")));
				jl_supprimer.setForeground(Color.BLUE);
				jl_supprimer.setBounds(22, 193, 200, 47);
				jl_supprimer.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int NumLigne = -1;
						NumLigne = table.getSelectedRow();
						// si une ligne est sélectionnée
						if (NumLigne != -1) {
							int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce client ?" 
									+ "/nnom : " + table.getValueAt(NumLigne, 2)
									+ "/nprenom : " + table.getValueAt(NumLigne, 3)
									, "SUPRESSION"
									,JOptionPane.YES_NO_OPTION);
							// 0 : non	1 : oui
							if (choix == 0) {
								String vNum_client;
								vNum_client = String.valueOf(table.getValueAt(NumLigne, 0));
								//pour récupérer la méthode CRUD
								pkg.entite.Client leClient = new pkg.entite.Client(vNum_client);
								// suppression de l'enregistrement dans la BD
								leClient.supprimerCRUD(vNum_client);
								// suppression de la ligne dans le modèle
								leModeleClients.supprimerClient(NumLigne);
								table.setModel(leModeleClients);
							}
						}
						// si aucune ligne est sélectionnée
						 if(NumLigne == -1) {
					            JOptionPane.showMessageDialog(null, "Sélectionnez une ligne avant.",
					            		"Suppression", JOptionPane.INFORMATION_MESSAGE);
					        }
						
					}
				});
			}
			return jl_supprimer;
		}
		
		private JLabel getJl_rechercher() {
			if (jl_rechercher == null) {
				
				jl_rechercher = new JLabel ("Rechercher");
				jl_rechercher.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/chercher.png")));
				jl_rechercher.setForeground(Color.BLUE);
				jl_rechercher.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jl_rechercher.setBounds(22, 73, 200, 47);
				
				jl_rechercher.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						// --- RECHERCHE EN MODE FICHE ---
						// création de la fenêtre
						FenFicheClient laFenetre = new FenFicheClient(leModeleClients, "Rechercher");
						// on change le libellé, l'icône et le titre
						laFenetre.getJLab_Action().setText("Rechercher");
						laFenetre.getJLab_Action().setIcon(new ImageIcon(FenFicheClient.class.getResource("/images/gestion/ajouter.png")));		
						laFenetre.getJl_titreclient().setText("Recherche de client(s)");
						laFenetre.setVisible(true);
			
					}
				});
			}
			return jl_rechercher;
		}
		
		
		private JLabel getjl_apercu() {
			if (jl_apercu == null) {
				
				jl_apercu = new JLabel ("Aperçu avant impression");
				jl_apercu.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/apercu2.png")));
				jl_apercu.setForeground(Color.BLUE);
				jl_apercu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jl_apercu.setBounds(22, 303, 210, 47);
				jl_apercu.setCursor(new Cursor(Cursor.HAND_CURSOR));
				jl_apercu.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						// aperçu état Jasper
						JasperMySQL_Par.apercu("personne.jrxml");
					}
					// survol de la souris
					public void mouseEntered (MouseEvent e) {
						jl_apercu.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/apercu.png")));
						jl_apercu.setForeground(Color.CYAN);
						jl_apercu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
						jl_apercu.setText("Aperçu avant impression");
					}
					// sortie de la zone survolé par la souris
					public void mouseExited (MouseEvent e) {
						jl_apercu.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/apercu2.png")));
						jl_apercu.setForeground(Color.BLUE);
						jl_apercu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
						jl_apercu.setText("Aperçu avant impression");
					}
				});
			}
			return jl_apercu;
		}
		
		private JLabel getJl_imprimer() {
			if (jl_imprimer == null) {
				
				jl_imprimer = new JLabel ("Imprimer");
				jl_imprimer.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/imprimer.png")));
				jl_imprimer.setForeground(Color.BLUE);
				jl_imprimer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jl_imprimer.setBounds(22, 363, 200, 47);
				jl_imprimer.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						// aperçu état Jasper
						JasperMySQL_Par.imprimer("personne.jrxml");
					}
					// survol de la souris
					public void mouseEntered (MouseEvent e) {
						jl_imprimer.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/imprimer2.png")));
						jl_imprimer.setForeground(Color.CYAN);
						jl_imprimer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
						jl_imprimer.setText("Imprimer");
					}
					// sortie de la zone survolé par la souris
					public void mouseExited (MouseEvent e) {
						jl_imprimer.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/imprimer.png")));
						jl_imprimer.setForeground(Color.BLUE);
						jl_imprimer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
						jl_imprimer.setText("Imprimer");
					}
				});
			}
			return jl_imprimer;
		}
		
		
		
		private JLabel getJl_exporter() {
			if (jl_exporter == null) {
				jl_exporter = new JLabel("Exporter");
				jl_exporter.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/exporter.png")));
				jl_exporter.setForeground(Color.BLUE);
				jl_exporter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jl_exporter.setBounds(22, 423, 200, 47);
				jl_exporter.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						FenExporter laFenetre = new FenExporter("personne.jrxml");
						laFenetre.setModal(true);
						laFenetre.setLocationRelativeTo(null);
						laFenetre.setVisible(true); 
					}
					// survol de la souris
					public void mouseEntered (MouseEvent e) {
						jl_exporter.setIcon(new ImageIcon(MenuClient.class.getResource("/images/logoEclipse.png")));
						jl_exporter.setForeground(Color.CYAN);
						jl_exporter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
						jl_exporter.setText("Exporter");
						
					}
					// sortie de la zone survolé par la souris
					public void mouseExited (MouseEvent e) {
						jl_exporter.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/exporter.png")));
						jl_exporter.setForeground(Color.BLUE);
						jl_exporter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
						jl_exporter.setText("Exporter");
					}
				});
			}
			return jl_exporter;
		}
		
		
		private JLabel getJl_retour() {
			if (jl_retour == null) {
				jl_retour = new JLabel("Retour au menu principal");
				jl_retour.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						dispose();
						Main menu = new Main();
						menu.setVisible(true);
					}
				});
				jl_retour.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/retour.png")));
				jl_retour.setForeground(Color.BLUE);
				jl_retour.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jl_retour.setBounds(22, 519, 210, 47);
				jl_retour.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			return jl_retour;
		}
		
		private void prepaModif() {
			// par défaut aucune ligne encore sélectionnée
			int NumLigne = -1;
			NumLigne = table.getSelectedRow();
			// si aucune ligne sélectionnée
			if(NumLigne == -1) {
				JOptionPane.showMessageDialog(null, "Sélectionnez auparavant" +
						" la ligne à modifier"
						+'\n'
						+ "ou effectuez un double-clic sur la ligne",
						"MODIFICATION", JOptionPane.INFORMATION_MESSAGE);							        	
			}
			if(NumLigne != -1) {
		// on récupère avant les données à partir d'une ligne sélectionnée
		String vNum_client = String.valueOf(table.getValueAt(NumLigne, 0));
		String vRib = String.valueOf(table.getValueAt(NumLigne, 1));
		String vNom = String.valueOf(table.getValueAt(NumLigne, 2));
		String vPrenom = String.valueOf(table.getValueAt(NumLigne, 3));
		String vAdresse = String.valueOf(table.getValueAt(NumLigne, 4));
		String vVille = String.valueOf(table.getValueAt(NumLigne, 5));
		String vCodepostale = String.valueOf(table.getValueAt(NumLigne, 6));
		String vDate = String.valueOf(table.getValueAt(NumLigne, 7));
		
		// changement du format chaîne date en format FR
		vDate = GestionDates.chaineDateENversFR(vDate);
		// on crée la fenêtre Fiche Client
		FenFicheClient laFenetre = new FenFicheClient(leModeleClients, "Modifier");
					
		// on affecte les valeurs aux champs correspondants de la fenêtre de la fiche client
		laFenetre.setJt_id_client(vNum_client);
		laFenetre.setJt_rib(vRib);
		laFenetre.setJt_nom(vNom);
		laFenetre.setJt_prenom(vPrenom);
		laFenetre.setJt_adresse(vAdresse);
		laFenetre.setJt_ville(vVille);
		laFenetre.setJt_codepostale(vCodepostale);
		laFenetre.setJt_datecreation(vDate);
		//Empecher la modification du numéro de client
		laFenetre.getJt_id_client().setEditable(false);
		// on change le titre de la fenêtre, le libellé du jLabel Action et son icône
		laFenetre.getJl_titreclient().setText("Modification d'un client");	
		laFenetre.getJLab_Action().setText("Sauvegarder");
		laFenetre.getJLab_Action().setIcon(new ImageIcon(FenFicheClient.class.getResource("/images/gestion/modifier.png")));
		// on masque le bouton retour
		// celui-ci reste visible seulement pour la recherche
		laFenetre.setVisible(true);
		}
		}
		
		

		
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
					MenuClient frame = new MenuClient();
					frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		@Override
		 public void tableChanged(TableModelEvent unEvenement) {					
			switch(unEvenement.getType()){
			case TableModelEvent.INSERT:
			  JOptionPane.showMessageDialog(null, "La table a été mise à jour", 
					  "Mise à jour", JOptionPane.INFORMATION_MESSAGE);
			  break ;
		    case TableModelEvent.DELETE:
		    	JOptionPane.showMessageDialog(null, "La table a été mise à jour, il y a eu une suppression !",
		    			"Mise à jour", JOptionPane.INFORMATION_MESSAGE);
			  break ;
			case TableModelEvent.UPDATE:
				JOptionPane.showMessageDialog(null, "La table a été mise à jour, il y a eu une modification !",
		    			"Mise à jour", JOptionPane.INFORMATION_MESSAGE);
			  break ;
			default : break ;
			} 
		}
}