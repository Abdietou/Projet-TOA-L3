
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import pkg.controle.modele.ModeleOperation2;
import pkg.controle.modele.RendreGras;
import pkg.controle.utilitaires.GestionDates;
import pkg.entite.Operation2;
import pkg.etat.JasperMySQL_Par;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class FenOperation2 extends JFrame implements TableModelListener {
	/**
	 * Cette classe est une interface graphique
	 * Elle permet de consulter les opération des client et de les gérer
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel conteneur;
	private JLabel jl_titre_client;
	private JTable table = null;
	 private JScrollPane jScrollPane = null;
	private ModeleOperation2 leModeleOperation2 = new ModeleOperation2();
    private int num_ligne_modify = 0;
    
    private String ID_operation;
    
    private JLabel jl_total; 
    private JLabel jl_ajouter;
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
	private JLabel jl_depense;
	private JLabel jl_modifierRev;
	private JLabel jl_supprimer;
	private JLabel jl_apercu;
	private JLabel jl_exporter;
	private JLabel jl_imprimer;
	private JLabel jl_retour;
	
	 /**
     * Launch the application.
     */
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		    public void run() {
				try {
				    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				    FenOperation2 frame = new FenOperation2();
				    frame.setVisible(true);
				} catch (Exception e) {
				    e.printStackTrace();
				}
		    }
		});
    }
    
    
    /*
     * Constructeur
     */
    public FenOperation2() {
		conteneur = new JPanel();
		conteneur.setBorder(new EmptyBorder(5, 5, 5, 5));
		conteneur.setLayout(null);
		
		conteneur.add(getJLTitreClient());
		conteneur.add(getJScrollPane());
		conteneur.add(getTotal_lab());
		conteneur.add(getJLab_Revenus());
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
		conteneur.add(getJl_Depense());
		conteneur.add(getJl_modifierRev());
		conteneur.add(getJl_supprimer());
		conteneur.add(getJl_apercu());
		conteneur.add(getJl_exporter());
		conteneur.add(getJl_imprimer());
		conteneur.add(getJl_retour());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1028, 616);
		setTitle("Opérations");
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/gestion/logoEclipse.png")));
		setContentPane(conteneur);
		
		//rendre gras pour les colonnes nom, prénom et le numéro client
		table.getColumnModel().getColumn(1).setCellRenderer(new RendreGras());
		table.getColumnModel().getColumn(2).setCellRenderer(new RendreGras());
		table.getColumnModel().getColumn(3).setCellRenderer(new RendreGras());
		
		
    }
    
    private JLabel getJLTitreClient() {
		if (jl_titre_client == null) {
			jl_titre_client = new JLabel();
			jl_titre_client.setText("Gestion des opérations");
			jl_titre_client.setFont(new Font("Segoe UI", Font.BOLD, 24));
			jl_titre_client.setBounds(292, 13, 428, 67);
			jl_titre_client.setIcon(new ImageIcon(getClass().getResource("/images/gestion/Database.png")));
		}
		return jl_titre_client;
    }    
    
    private JTable getJTable() {
	if (table == null) {
	    table = new JTable(leModeleOperation2);		
	    // bulle d'aide : utilisation de code html dans Java
	    table.setToolTipText("<html><img src=\""+
		    		   FenOperation2.class.getResource("/images/gestion/retour.png")+
		    		   "\" />Pour <b>modifier</b> unne ligne, vous devez <b>double-cliquez</b> sur <b>celle-ci</b></html>");
	    table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				int NumLigne;
				// gestion du SIMPLE-CLIC sur une ligne de la table
				// pour un report des données
				// dans les champs correspondants
				NumLigne = table.getSelectedRow();
				jt_id_ope.setText(String.valueOf(table.getValueAt(NumLigne, 0)));
				jt_id_cli.setText(String.valueOf(table.getValueAt(NumLigne, 1)));
				jt_mdp.setText(String.valueOf(table.getValueAt(NumLigne, 5)));
				jt_tiers.setText(String.valueOf(table.getValueAt(NumLigne, 6)));
				jt_categorie.setText(String.valueOf(table.getValueAt(NumLigne, 7)));
				jt_montant.setText(String.valueOf(table.getValueAt(NumLigne, 8)));
				//jt_datecreation.setText(String.valueOf(table.getValueAt(NumLigne, 6)));
				// la valeur date récupérée est au format yyyy-MM-dd
				String strDate = String.valueOf(table.getValueAt(NumLigne, 4));
				// on la met au format dd-MM-yyyy
				strDate = GestionDates.chaineDateENversFR(strDate);
				jt_date.setText(strDate);
			    if( e.getClickCount() == 2 ){
					//num_ligne_modify = table.getSelectedRow();
			    	Modif(); // méthode qui permet de modifier les données
			    }
			}
	    });
	}	
	return table;
}
    
    private JLabel getJl_modifierRev() {
		if (jl_modifierRev == null) {
			
			jl_modifierRev = new JLabel("Modifier");
			jl_modifierRev.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/modifier.png")));
			jl_modifierRev.setForeground(Color.BLUE);
			jl_modifierRev.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			jl_modifierRev.setBounds(22, 133, 200, 47);
			jl_modifierRev.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					Modif();
					//mise à jour de la table après la modification
					table.setModel(leModeleOperation2);
				}								
			});
		}
		return jl_modifierRev;
	}
    
    public void Modif() {
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
						String vID_ope = String.valueOf(table.getValueAt(NumLigne, 0));
						String vNum_cli = String.valueOf(table.getValueAt(NumLigne, 1));
						String vDate = String.valueOf(table.getValueAt(NumLigne, 4));
						String vModePaiement = String.valueOf(table.getValueAt(NumLigne, 5));
						String vTiers = String.valueOf(table.getValueAt(NumLigne, 6));
						String vCategorie = String.valueOf(table.getValueAt(NumLigne, 7));
						String vMontant = String.valueOf(table.getValueAt(NumLigne, 8));

						// changement du format chaîne date en format FR
						vDate = GestionDates.chaineDateENversFR(vDate);
						dispose();
						
						// on crée la fenêtre Fiche Client
						FenFicheOperation2 ops2 = new FenFicheOperation2(leModeleOperation2, "Modifier");
						
						ops2.setJt_ID_ope(vID_ope);
						ops2.setJt_ID_cli(vNum_cli);
						//ops2.setJt_rib(vRib);
						ops2.setJt_date(vDate);
						ops2.setJt_ModePaiement(vModePaiement);
						ops2.setJt_tiers(vTiers);
						ops2.setJt_categorie(vCategorie);
						ops2.setJt_montant(vMontant);
						
						//Empecher la modification du numéro d'opération, du numéro client et du rib
						ops2.getJl_id_ope().setEnabled(false); //N° opération
						ops2.getJTxt_id_ope().setEnabled(false);
						ops2.getJl_id_cli().setEnabled(false); // N° client
						ops2.getJtxt_id_cli().setEnabled(false);
						
						ops2.getJl_titreclient().setText("Modification d'un client");	
						ops2.getJLab_Action().setText("Sauvegarder");
						ops2.getJLab_Action().setIcon(new ImageIcon(FenFicheClient.class.getResource("/images/gestion/modifier.png")));
						// on masque le bouton retour
						// celui-ci reste visible seulement pour la recherche
						ops2.setVisible(true);
					}
	   }
    
    private JLabel getJl_supprimer() {
    	if (jl_supprimer == null) {
    		jl_supprimer = new JLabel ("Supprimer");
    		jl_supprimer.setIcon(new ImageIcon(MenuClient.class.getResource("/images/gestion/supprimer.png")));
			jl_supprimer.setForeground(Color.BLUE);
			jl_supprimer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
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
							String vID_ope;
							vID_ope = String.valueOf(table.getValueAt(NumLigne, 0));
							//pour récupérer la méthode CRUD
							Operation2 lesOperations2 = new Operation2(vID_ope);
							// suppression de l'enregistrement dans la BD
							lesOperations2.supprimerCRUD(vID_ope);
							// suppression de la ligne dans le modèle
							leModeleOperation2.supprimerOPS2(NumLigne);
							table.setModel(leModeleOperation2);
						}
					}
					// si aucune ligne n'est sélectionnée
					 if(NumLigne == -1) {
				            JOptionPane.showMessageDialog(null, "Sélectionnez une ligne avant.",
				            		"Suppression", JOptionPane.INFORMATION_MESSAGE);
				        }
					
				}
			});
		}
		return jl_supprimer;
    	}
    
    
    
    private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
		    jScrollPane = new JScrollPane();
		    jScrollPane.setBounds(256, 272, 741, 252);
		    jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
    }
    

	 private JLabel getTotal_lab() {
		 if (jl_total == null) {
			 jl_total = new JLabel();
			 jl_total.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			 jl_total.setBounds(700, 550, 261, 17);
			 double total = 0;
			 DecimalFormat pattern = new DecimalFormat("#,##0.00");
			 for(int i = 0;i<table.getRowCount();i++){
				 total += Double.parseDouble(table.getValueAt(i, 8).toString());
			 }
			 jl_total.setText("<html>Total des opérations : <b>" + pattern.format(total) + " € "); //"</b></html>"
		 }
		 return jl_total;
	 }
	 
	 private JLabel getJLab_Revenus() {
			if (jl_ajouter == null) {
				jl_ajouter = new JLabel("Revenu");
			    jl_ajouter.setIcon(new ImageIcon(FenOperation2.class.getResource("/images/gestion/ajouter.png")));
			    jl_ajouter.setForeground(Color.BLUE);
			    jl_ajouter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			    jl_ajouter.setBounds(22, 13, 200, 47);
			    this.setVisible(false);
			    
			    jl_ajouter.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						dispose();
						// --- AJOUT EN MODE FICHE ---
						//FenOperation2 fenops2 = new FenOperation2();
						// création de la fenêtre
						FenFicheOperation2 ops2 = new FenFicheOperation2(leModeleOperation2, "Revenu");
						// on change le titre et son icône et le libellé du bouton
						ops2.getJl_titreclient().setText("Ajout d'un revenu");
						ops2.getJLab_Action().setIcon(new ImageIcon(FenFicheClient.class.getResource("/images/gestion/ajouter.png")));		
						ops2.getJLab_Action().setText("Sauvegarder");									  
						// focus sur le numero opération
						ops2.getJTxt_id_ope().requestFocus(true);
						// on met par défaut la date du jour
						Date dtDate = new Date();
						String strDate = GestionDates.dateEnChaineFR(dtDate);
						ops2.getJTxt_Date().setText(strDate);
						ops2.getJTxt_Date().setEditable(true);
						ops2.setVisible(true);
						//table.setModel(leModeleOperation2);
						//DefaultTableModel dm = (DefaultTableModel)table.getModel();
						//dm.fireTableDataChanged(); // notifies the JTable that the model has changed
						//table.repaint();
						leModeleOperation2.fireTableDataChanged();
						
						
					}

				});
			}
			return jl_ajouter;
	    }
	 
	 private JLabel getJl_Depense() {
		 if (jl_depense == null) {
			 jl_depense = new JLabel("Paiement");
			 jl_depense.setIcon(new ImageIcon(FenOperation2.class.getResource("/images/gestion/ajouter2.png")));
			 jl_depense.setForeground(Color.BLUE);
			 jl_depense.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			 jl_depense.setBounds(22, 73, 200, 47);
			 this.setVisible(false);
			 
			 jl_depense.addMouseListener(new MouseAdapter() {
				 public void mouseClicked(MouseEvent arg0) {
					 dispose();
					 // --- AJOUT EN MODE FICHE ---
						//FenOperation2 fenops2 = new FenOperation2();
						// création de la fenêtre
						FenFicheOperation2 ops2 = new FenFicheOperation2(leModeleOperation2, "Paiement");
						// on change le titre et son icône et le libellé du bouton
						ops2.getJl_titreclient().setText("Ajout d'un paiement");
						ops2.getJLab_ActionDep().setIcon(new ImageIcon(FenFicheClient.class.getResource("/images/gestion/ajouter2.png")));		
						ops2.getJLab_ActionDep().setText("Sauvegarder");									  
						// focus sur le numero opération
						ops2.getJTxt_id_ope().requestFocus(true);
						// on met par défaut la date du jour
						Date dtDate = new Date();
						String strDate = GestionDates.dateEnChaineFR(dtDate);
						ops2.getJTxt_Date().setText(strDate);
						ops2.getJTxt_Date().setEditable(true);
						ops2.setVisible(true);
						leModeleOperation2.fireTableDataChanged();
				 }
			 });
		 }
		 return jl_depense;
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
				jt_mdp.setEnabled(false);
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
	    		jt_tiers.setEnabled(false);
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
	    		jt_categorie.setEnabled(false);
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
				jt_montant.setEnabled(false);
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
	    
	    private JTextField getJTxt_Date() {
			if (jt_date == null) {
				jt_date  = new JTextField();
				jt_date.setEnabled(false);
				jt_date .setBounds(385, 109, 167, 28);
				jt_date .setColumns(10);
			}
			return jt_date ;
	    }
	    
	    private JLabel getJl_id_ope() {
	    	if (jl_id_ope == null) {
	    		jl_id_ope = new JLabel ("N° opération");
	    		jl_id_ope.setBounds(273, 84, 100, 15);
	    	}
	    	return jl_id_ope;
	    }
	    
	    private JTextField getJTxt_id_ope() {
	    	if (jt_id_ope == null) {
	    		jt_id_ope = new JTextField();
	    		jt_id_ope.setEnabled(false);
	    		jt_id_ope.setBounds(385, 80, 59, 24);
	    		jt_id_ope.setColumns(10);
	    	}
	    	return jt_id_ope;
	    }
	    
	    private JLabel getJl_id_cli() {
	    	if (jl_id_cli == null) {
	    		jl_id_cli = new JLabel ("N° client");
	    		jl_id_cli.setBounds(620, 84, 70, 15);
	    	}
	    	return jl_id_cli;
	    }
	    
	    private JTextField getJtxt_id_cli(){
	    	if (jt_id_cli == null) {
	    		jt_id_cli = new JTextField();
	    		jt_id_cli.setEnabled(false);
	    		jt_id_cli.setBounds(700, 80, 59, 24);
	    		jt_id_cli.setColumns(10);
	    	}
	    	return jt_id_cli;
	    }
	    
	   
	   
	   public void tableChanged2(TableModelEvent unEvenement) {					
			switch(unEvenement.getType()){
			case TableModelEvent.INSERT:
			  JOptionPane.showMessageDialog(null, "L'opération a été mise à jour !", 
					  "Mise à jour", JOptionPane.INFORMATION_MESSAGE);
			  break ;
		    case TableModelEvent.DELETE:
		    	JOptionPane.showMessageDialog(null, "L'opération a été supprimer !",
		    			"Mise à jour", JOptionPane.INFORMATION_MESSAGE);
			  break ;
			case TableModelEvent.UPDATE:
				JOptionPane.showMessageDialog(null, "L'opération a été supprimer !",
		    			"Mise à jour", JOptionPane.INFORMATION_MESSAGE);
			  break ;
			default : break ;
			} 
		}


	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private JLabel getJl_imprimer() {
		if (jl_imprimer == null) {
			jl_imprimer = new JLabel("Imprimer");
			jl_imprimer.setIcon(new ImageIcon(FenOperation2.class.getResource("/images/gestion/imprimer.png")));
			jl_imprimer.setForeground(Color.BLUE);
			jl_imprimer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			jl_imprimer.setBounds(22, 363, 200, 47);
			jl_imprimer.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					JasperMySQL_Par.imprimer("operation2.jrxml");
				}
				public void mouseEntered(MouseEvent e) {
					jl_imprimer.setIcon(new ImageIcon(FenOperation2.class.getResource("/images/gestion/imprimer2.png")));
					jl_imprimer.setForeground(Color.CYAN);
					jl_imprimer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
					jl_imprimer.setText("Imprimer");
				}
				public void mouseExited(MouseEvent e) {
					jl_imprimer.setIcon(new ImageIcon(FenOperation2.class.getResource("/images/gestion/imprimer.png")));
					jl_imprimer.setForeground(Color.BLUE);
					jl_imprimer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
					jl_imprimer.setText("Imprimer");
				}
			});
		}
		return jl_imprimer;
	}
	
	 private JLabel getJl_apercu() {
		   if (jl_apercu == null) {
			   jl_apercu = new JLabel("Aperçu");
			   jl_apercu.setIcon(new ImageIcon(FenOperation2.class.getResource("/images/gestion/apercu.png")));
			   jl_apercu.setForeground(Color.BLUE);
			   jl_apercu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			   jl_apercu.setBounds(22, 264, 200, 47);
			   jl_apercu.addMouseListener(new MouseAdapter() {
				   public void mouseClicked (MouseEvent e) {
						   JasperMySQL_Par.apercu("operation2.jrxml");
				   }
			   });
		   }
		   return jl_apercu;
	   }
	 
	 private JLabel getJl_exporter() {
			if (jl_exporter == null) {
				jl_exporter = new JLabel("Exporter");
				jl_exporter.setIcon(new ImageIcon(FenOperation2.class.getResource("/images/gestion/exporter.png")));
				jl_exporter.setForeground(Color.BLUE);
				jl_exporter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				jl_exporter.setBounds(22, 423, 200, 47);
				jl_exporter.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						FenExporter laFenetre = new FenExporter("operation2.jrxml");
						laFenetre.setModal(true);
						laFenetre.setLocationRelativeTo(null);
						laFenetre.setVisible(true); 
					}
					// survol de la souris
					public void mouseEntered (MouseEvent e) {
						jl_exporter.setIcon(new ImageIcon(FenOperation2.class.getResource("/images/logoEclipse.png")));
						jl_exporter.setForeground(Color.CYAN);
						jl_exporter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
						jl_exporter.setText("Exporter");
						
					}
					// sortie de la zone survolé par la souris
					public void mouseExited (MouseEvent e) {
						jl_exporter.setIcon(new ImageIcon(FenOperation2.class.getResource("/images/gestion/exporter.png")));
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
}