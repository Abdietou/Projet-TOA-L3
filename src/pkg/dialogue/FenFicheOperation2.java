package pkg.dialogue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import pkg.controle.modele.ModeleOperation2;
import pkg.controle.utilitaires.GestionDates;
import pkg.entite.Operation2;
import pkg.etat.JasperMySQL_Par;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class FenFicheOperation2 extends JFrame {
	/**
	 * Cette classe est une interface graphique qui permet de modifier ou ajouter une opération
	 */
	private static final long serialVersionUID = 1L;

	// pour travailler sur le même modèle que la fenêtre en mode table
	private ModeleOperation2 leModeleOperation2 = null;
	// pour connaître l'actionDemande à réaliser : ajout ou modification
	private String actionDemande = "";
	
	private JPanel conteneur;
	
	private JLabel jl_action;
	private JScrollPane jspane;
	private JTable table;
	
	private JLabel jl_titreclient;
	private JLabel jl_mdp;
	private JTextField jt_mdp;
	private JLabel jl_tiers;
	private JTextField jt_tiers;
	private JLabel jl_categorie;
	private JTextField jt_categorie;
	private JLabel jl_montant;
	private JTextField jt_montant;
	private JLabel jl_date;
	private JTextField jt_date;
	private JTextField jt_id_ope;
	private JLabel jl_id_ope;
	private JLabel jl_id_cli;
	private JTextField jt_id_cli;
	private JLabel jl_retour;
	private JLabel jl_annuler;
	private JLabel jl_action2;
	
	public FenFicheOperation2(ModeleOperation2 modeleOperation2Transmis, String demande) {
		super();
		leModeleOperation2 = modeleOperation2Transmis;
		actionDemande = demande;
		
		setBounds(100, 100, 1027, 642);
		conteneur = new JPanel();
		conteneur.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(conteneur);
		conteneur.setLayout(null);
		
		conteneur.add(getJLab_Action());
		conteneur.add(getJl_titreclient());
		conteneur.add(getJScrollPane());
		conteneur.add(getJTable());
		conteneur.add(getLabel_ModePaiement());
		conteneur.add(getJTxt_MDP());
		conteneur.add(getLabel_Tiers());
		conteneur.add(getJtxt_Tiers());
		conteneur.add(getLabel_Categorie());
		conteneur.add(getJtxt_Cat());
		conteneur.add(getLabel_Mont());
		conteneur.add(getJTxt_Mont());
		conteneur.add(getLabel_Date());
		conteneur.add(getJTxt_Date());
		conteneur.add(getJl_id_ope());
		conteneur.add(getJTxt_id_ope());
		conteneur.add(getJl_id_cli());
		conteneur.add(getJtxt_id_cli());
		conteneur.add(getJLab_Action());
		conteneur.add(getJl_annuler());
		conteneur.add(getJLab_ActionDep());
		
		setTitle("Opération");		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/gestion/logoEclipse.png")));
		setLocationRelativeTo(null);
		setResizable(false);
		
}
	
	 private JScrollPane getJScrollPane() {
			if (jspane == null) {
			    jspane = new JScrollPane();
			    jspane.setBounds(256, 272, 741, 252);
			}
			return jspane;
	    }
	 
	 public JLabel getJl_titreclient() {
			if (jl_titreclient == null) {
				jl_titreclient = new JLabel();
				jl_titreclient.setText("Modifcation , Ajout");
				jl_titreclient.setFont(new Font("Segoe UI", Font.BOLD, 24));
				jl_titreclient.setBounds(292, 13, 428, 67);
				jl_titreclient.setIcon(new ImageIcon(getClass().getResource("/images/gestion/clientTitre.png")));
			}
			return jl_titreclient;
		}
	 
	 private JLabel getLabel_ModePaiement() {
			if (jl_mdp == null) {
			    jl_mdp = new JLabel("Mode Paiement");
			    jl_mdp.setBounds(620, 150, 104, 14);
			}
			return jl_mdp;
	    }
	    private JTextField getJTxt_MDP() {
			if (jt_mdp == null) {
				jt_mdp = new JTextField();
				jt_mdp.setColumns(10);
				jt_mdp.setBounds(720, 143, 100, 28);
			}
			return jt_mdp;
	    }
	    
	    private JLabel getLabel_Tiers() {
	    	if (jl_tiers == null) {
	    		jl_tiers = new JLabel ("Tiers");
	    		jl_tiers.setBounds(273, 150, 104, 14);
	    	}
	    	return jl_tiers;
	    }
	    private JTextField getJtxt_Tiers() {
	    	if (jt_tiers == null) {
	    		jt_tiers = new JTextField();
	    		jt_tiers.setColumns(10);
	    		jt_tiers.setBounds(385, 143, 158,28);
	    	}
	    	return jt_tiers;
	    }
	    
	    private JLabel getLabel_Categorie() {
			if (jl_categorie == null) {
				jl_categorie = new JLabel("Catégorie");
				jl_categorie.setBounds(273, 184, 90, 14);
			}
			return jl_categorie;
	    }
	    
	    private JTextField getJtxt_Cat() {
	    	if (jt_categorie == null) {
	    		jt_categorie = new JTextField();
	    		jt_categorie.setColumns(10);
	    		jt_categorie.setBounds(385, 177, 158, 28);
	    	}
	    	return jt_categorie;
	    }
	    
	    private JLabel getLabel_Mont() {
			if (jl_montant == null) {
				jl_montant = new JLabel("Montant");
				jl_montant.setBounds(620, 184, 160, 14);
			}
			return jl_montant;
	    }
	    
	    private JTextField getJTxt_Mont() {
			if (jt_montant == null) {
				jt_montant  = new JTextField();
				jt_montant .setColumns(10);
				jt_montant .setBounds(700, 177, 163, 28);
			}
			return jt_montant ;
	    }
	    
	    private JLabel getLabel_Date() {
	    	if (jl_date == null) {
	    		jl_date = new JLabel ("Date");
	    		jl_date.setBounds(273, 116, 48, 14);
	    		}
	    	return jl_date;
	    }
	    
	    public JTextField getJTxt_Date() {
			if (jt_date == null) {
				jt_date  = new JTextField();
				jt_date .setBounds(385, 109, 167, 28);
				jt_date .setColumns(10);
			}
			return jt_date ;
	    }
	    
	    public JLabel getJl_id_ope() {
	    	if (jl_id_ope == null) {
	    		jl_id_ope = new JLabel ("N° opération");
	    		jl_id_ope.setBounds(273, 84, 100, 15);
	    	}
	    	return jl_id_ope;
	    }
	    
	    public JTextField getJTxt_id_ope() {
	    	if (jt_id_ope == null) {
	    		jt_id_ope = new JTextField();
	    		jt_id_ope.setBounds(385, 80, 59, 24);
	    		jt_id_ope.setColumns(10);
	    	}
	    	return jt_id_ope;
	    }
	    
	    public JLabel getJl_id_cli() {
	    	if (jl_id_cli == null) {
	    		jl_id_cli = new JLabel ("N° client");
	    		jl_id_cli.setBounds(620, 84, 70, 15);
	    	}
	    	return jl_id_cli;
	    }
	    
	    public JTextField getJtxt_id_cli(){
	    	if (jt_id_cli == null) {
	    		jt_id_cli = new JTextField();
	    		jt_id_cli.setBounds(700, 80, 59, 24);
	    		jt_id_cli.setColumns(10);
	    	}
	    	return jt_id_cli;
	    }
	    
	    public JLabel getJl_retour() {
			if (jl_retour == null) {
				jl_retour = new JLabel("Retour au menu Client");
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
						FenOperation2 fenops2 = new FenOperation2();
						fenops2.setVisible(true);
					}
				});
				jl_annuler.setForeground(Color.BLUE);
				jl_annuler.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jl_annuler.setBounds(38, 271, 200, 47);
				jl_annuler.setIcon(new ImageIcon(FenFicheClient.class.getResource("/images/gestion/annuler.png")));
			}
			return jl_annuler;
		}

	 // JTable
	 		// ******
	 		private JTable getJTable() {
	 			if (table == null) {
	 				table = new JTable();
	 				// ****************************************
	 				table = new JTable(leModeleOperation2);
	 				// ****************************************
	 			}
	 			return table;
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
					
					jl_action.addMouseListener(new MouseAdapter()  {
						public void mouseClicked(MouseEvent arg0) {
							String vID_ope = jt_id_ope.getText();
							String vID_client = jt_id_cli.getText();
							String vDate = jt_date.getText();
							String vModePaiement = jt_mdp.getText();
							String vTiers = jt_tiers.getText();
							String vCategorie = jt_categorie.getText();
							String vMt = jt_montant.getText();
							double vMontant = Double.parseDouble(vMt.replaceAll(",","."));
							
								//AJOUT Revenus
							if (actionDemande.equals("Revenu")){
								if (!vID_ope.equals("")){
									// variable String EN à partir  du String FR --> yyyy-MM-dd
									String strDateMySQL = GestionDates.chaineDateFRversEN(vDate);
									//pour la classe Operation2
									Date dtDate = new Date();
									dtDate = GestionDates.dateJavaEnDateSQL(dtDate);
									// création d'une instance client pour obtenir le CRUD	
									Operation2 lesOperations2 = new Operation2(vID_ope, vID_client, (java.sql.Date) dtDate, vModePaiement, vTiers, vCategorie, vMontant);
									// 1. sauvegarde d'abord dans la BD
									boolean bCreation = false ;
									bCreation = lesOperations2.creerCRUD(vID_ope, vID_client, strDateMySQL, vModePaiement, vTiers, vCategorie, vMontant);
									// 2. puis ajout dans le modèle
									if (bCreation) {				
										leModeleOperation2.creerOPS2(lesOperations2);
								}
									// remise à vide des champs pour un nouvel ajout
									jt_id_ope.setText("");
									jt_id_cli.setText("");
									jt_date.setText("");
									jt_mdp.setText("");
									jt_tiers.setText("");
									jt_categorie.setText("");
									jt_montant.setText("");
									// focus sur le textfield pour faciliterla saisie
									jt_id_ope.requestFocus();													
									// on remet la date du jour par défaut
									dtDate = new Date();
									jt_date.setText(GestionDates.dateEnChaineFR(dtDate));
									FenOperation2 fenops2 = new FenOperation2();
									dispose();
									fenops2.setVisible(true);
								}  
								else {
					                 JOptionPane.showMessageDialog(null, "La saisie du numero d'opération"
					                  		+ " est obligatoire", 
					                          "Vérifiez votre saisie", JOptionPane.ERROR_MESSAGE);
							}
						}
										//MODIFICATION
					if (actionDemande.equals("Modifier")){
						//Date pour MySQL
						String strDateMySQL = GestionDates.chaineDateFRversEN(vDate);
						// mise du String date FR en String date EN
						vDate = GestionDates.chaineDateFRversEN(vDate);
						try {
							//Date pour le constructeur client
							Date dtDate = GestionDates.chaineENversDateJava(vDate);
							dtDate = GestionDates.dateJavaEnDateSQL(dtDate);
							Operation2 lesOperations2 = new Operation2(vID_ope, vID_client,(java.sql.Date) dtDate, vModePaiement, vTiers, vCategorie, vMontant);
							boolean bCreation = false;
							bCreation = lesOperations2.modifierCRUD(vID_ope, vID_client, strDateMySQL, vModePaiement, vTiers, vCategorie, vMontant);
						
							if (bCreation) {
								int numLigne = leModeleOperation2.getNumLigne(vID_ope);
								leModeleOperation2.setValueAt(vID_ope, numLigne, 0);
								leModeleOperation2.setValueAt(vID_client, numLigne, 1);
								leModeleOperation2.setValueAt(vDate, numLigne, 4);
								leModeleOperation2.setValueAt(vModePaiement, numLigne, 5);
								leModeleOperation2.setValueAt(vTiers, numLigne, 6);
								leModeleOperation2.setValueAt(vCategorie, numLigne, 7);
								leModeleOperation2.setValueAt(vMontant, numLigne, 8);
								leModeleOperation2.modifierOPS2(numLigne, numLigne, lesOperations2);
								FenOperation2 fenops2 = new FenOperation2();
								dispose();
								fenops2.setVisible(true);
								
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					
					/*// RECHERCHE
					if(actionDemande.equals("Rechercher")) {
						Operation2 lesOperations2 = new Operation2(vID_ope);
						// Recherche dans la BD
						ArrayList<Operation2> nouvelleListe = lesOperations2.chercherCRUD(vID_ope, vID_client, vRib);
						//MAJ du modele
						leModeleOperation2.lireRecupMOD(nouvelleListe);
					} */
				}
			});
			}
			return jl_action;
		}
			
		public JLabel getJLab_ActionDep() {
				if (jl_action2 == null) {
					jl_action2 = new JLabel("ACTION : Je vais changer...");
					jl_action2.setIcon(null);
					jl_action2.setForeground(Color.WHITE);
					jl_action2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
					jl_action2.setBounds(22, 73, 200, 47);
					
					jl_action2.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent arg0) {
							String vID_ope = jt_id_ope.getText();
							String vID_client = jt_id_cli.getText();
							String vDate = jt_date.getText();
							String vModePaiement = jt_mdp.getText();
							String vTiers = jt_tiers.getText();
							String vCategorie = jt_categorie.getText();
							String vMt = jt_montant.getText();
							double vMontant = Double.parseDouble("-" + vMt.replaceAll(",","."));
							
							// Ajout Dépense	
							if (actionDemande.equals("Paiement")){
								if (!vID_ope.equals("")){
									// variable String EN à partir  du String FR --> yyyy-MM-dd
									String strDateMySQL = GestionDates.chaineDateFRversEN(vDate);
									//pour la classe Operation2
									Date dtDate = new Date();
									dtDate = GestionDates.dateJavaEnDateSQL(dtDate);
									// création d'une instance client pour obtenir le CRUD	
									Operation2 lesOperations2 = new Operation2(vID_ope, vID_client, (java.sql.Date) dtDate, vModePaiement, vTiers, vCategorie, vMontant);
									// 1. sauvegarde d'abord dans la BD
									boolean bCreation = false ;
									bCreation = lesOperations2.creerCRUD(vID_ope, vID_client, strDateMySQL, vModePaiement, vTiers, vCategorie, vMontant);
									// 2. puis ajout dans le modèle
									if (bCreation) {				
										leModeleOperation2.creerOPS2(lesOperations2);
								}
									// remise à vide des champs pour un nouvel ajout
									jt_id_ope.setText("");
									jt_id_cli.setText("");
									jt_date.setText("");
									jt_mdp.setText("");
									jt_tiers.setText("");
									jt_categorie.setText("");
									jt_montant.setText("");
									// focus sur le textfield pour faciliterla saisie
									jt_id_ope.requestFocus();													
									// on remet la date du jour par défaut
									dtDate = new Date();
									jt_date.setText(GestionDates.dateEnChaineFR(dtDate));
									FenOperation2 fenops2 = new FenOperation2();
									dispose();
									fenops2.setVisible(true);
								}  
								else {
					                 JOptionPane.showMessageDialog(null, "La saisie du numero d'opération"
					                  		+ " est obligatoire", 
					                          "Vérifiez votre saisie", JOptionPane.ERROR_MESSAGE);
							}
						}
							
						}
					});
					}
					return jl_action2;
			}
				
							

			
			//Setters
			public void setJt_ID_ope (String vID_ope) {
				   jt_id_ope.setText(vID_ope);
			   }
			   public void setJt_ID_cli (String vID_cli) {
				   jt_id_cli.setText(vID_cli);
			   }
			   public void setJt_date(String vDate) {
				   jt_date.setText(vDate);
			   }
			   public void setJt_ModePaiement(String vModePaiement) {
				   jt_mdp.setText(vModePaiement);
			   }
			   public void setJt_tiers(String vTiers) {
				   jt_tiers.setText(vTiers);
			   }
			   public void setJt_categorie(String vCategorie) {
				   jt_categorie.setText(vCategorie);
			   }
			   public void setJt_montant(String vMontant) {
				   jt_montant.setText(String.valueOf(vMontant));
			   }
    
	
}

