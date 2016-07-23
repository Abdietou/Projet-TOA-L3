package pkg.dialogue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class Main extends JFrame {
	/**
	 * Cette classe est une interface graphique
	 * Elle permet d'acceder aux principales option de notre programme
	 */
	 private static final long serialVersionUID = 1L;
	 
	 private JPanel conteneur;
	 private JLabel jl_client;
	 private JLabel jl_operation;
	 private JLabel jl_quitter;
	 private JLabel jl_fond;
	 private JLabel jl_titre;
	 private JLabel jl_graphe;
	 
	 public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
			    public void run() {
					try {
					    Main frame = new Main();
					    frame.setVisible(true);
					} catch (Exception e) {
					    e.printStackTrace();
					  }
				    }
			});
	    }
	 
	 /**
	     * Création de la fenêtre.
	     */
	    public Main() {
			super();
			setBounds(100, 100, 1028, 596);
			conteneur = new JPanel();
			conteneur.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(conteneur);
			conteneur.setLayout(null);
			
			conteneur.add(getJl_titre());
			conteneur.add(getJl_client());
			conteneur.add(getJl_operation());
			conteneur.add(getJl_quitter());
			conteneur.add(getJLab_Fond());
			conteneur.add(getJl_stats());
			
			setTitle("Menu principal");
			setLocationRelativeTo(null);
			setResizable(false);
			setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logoEclipse.png")));
	    }
	    
	    private JLabel getJl_titre() {
			if (jl_titre == null) {
				jl_titre = new JLabel("Gestion de budget");
				jl_titre.setForeground(SystemColor.activeCaption);
				jl_titre.setHorizontalAlignment(SwingConstants.CENTER);
				jl_titre.setToolTipText("");
				jl_titre.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
				jl_titre.setBounds(12, 13, 218, 66);
			}
			return jl_titre;
	    }
	    
	    private JLabel getJl_client() {
			if (jl_client == null) {
				jl_client = new JLabel("");
			    jl_client.setIcon(new ImageIcon(Main.class.getResource("/images/accueil/marcheur1.png")));
			    jl_client.setBounds(295, 204, 218, 142);
			    // pour avoir le pointeur de la souris sous forme de main
			    jl_client.setCursor(new Cursor(Cursor.HAND_CURSOR));
			    
			    jl_client.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent e) {
						jl_client.setIcon(new ImageIcon(Main.class.getResource("/images/accueil/marcheur2.png")));
						jl_client.setForeground(SystemColor.activeCaption);
						jl_client.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
						jl_client.setText("Clients");
					}
					// sortie de la zone survolé par la souris
					public void mouseExited(MouseEvent e) {
					    jl_client.setIcon(new ImageIcon(Main.class.getResource("/images/accueil/marcheur1.png")));
					    jl_client.setText("");
					}
					
					public void mouseClicked(MouseEvent e) {										
					    MenuClient laFenetre = new MenuClient();
					    laFenetre.setVisible(true);
					    dispose();
					}
			    });
			}
			return jl_client;
	    }
	    
	   private JLabel getJl_stats() {
			if (jl_graphe == null) {
			    jl_graphe = new JLabel("");
			    jl_graphe.setIcon(new ImageIcon(Main.class.getResource("/images/accueil/camembert.png")));
			    jl_graphe.setBounds(525, 349, 263, 142);
			    jl_graphe.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
			    jl_graphe.addMouseListener(new MouseAdapter() {
			    	public void mouseClicked(MouseEvent e) {
			    		 FenCamenbert FenCamb = new FenCamenbert();
						    FenCamb.setModal(true);
						    FenCamb.setLocationRelativeTo(FenCamb.getParent());
						    FenCamb.setVisible(true);
			    	}
			    		// survol de la souris
			    		public void mouseEntered(MouseEvent e) {
						    jl_graphe.setIcon(new ImageIcon(Main.class.getResource("/images/accueil/histogramme.png")));
						    jl_graphe.setForeground(SystemColor.activeCaption);
						    jl_graphe.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
						    jl_graphe.setText("Stats");
						}
			    		// sortie de la zone survolé
					public void mouseExited(MouseEvent e) {
					    jl_graphe.setIcon(new ImageIcon(Main.class.getResource("/images/accueil/camembert.png")));
					    jl_graphe.setText("");
					}
			    });	
			}
			return jl_graphe;
	    }
	    
	    
	    private JLabel getJl_operation() {
			if (jl_operation == null) {
			    jl_operation = new JLabel("");
			    jl_operation.setIcon(new ImageIcon(Main.class.getResource("/images/accueil/iBook.png")));
			    jl_operation.setBounds(512, 23, 276, 142);
			    jl_operation.setCursor(new Cursor(Cursor.HAND_CURSOR));
			    
			    jl_operation.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						FenOperation2 fenops2 = new FenOperation2();
						fenops2.setVisible(true);
						dispose();
					
					}
					public void mouseEntered(MouseEvent e) {
					    jl_operation.setIcon(new ImageIcon(Main.class.getResource("/images/accueil/powerBook.png")));
					    jl_operation.setForeground(SystemColor.activeCaption);
					    jl_operation.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
					    jl_operation.setText("Opération");
					}
					public void mouseExited(MouseEvent e) {
					    jl_operation.setIcon(new ImageIcon(Main.class.getResource("/images/accueil/iBook.png")));
					    jl_operation.setText("");
					}
					
					
			    });
			}
			return jl_operation;
	    }
	    
	    private JLabel getJl_quitter() {
			if (jl_quitter == null) {
			    jl_quitter = new JLabel("");
			    jl_quitter.setIcon(new ImageIcon(Main.class.getResource("/images/accueil/quitter1.png")));
			    jl_quitter.setBounds(22, 500, 185, 55);
			    jl_quitter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
			    jl_quitter.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
					    System.exit(0);
					}
					public void mouseEntered(MouseEvent e) {
					    jl_quitter.setIcon(new ImageIcon(Main.class.getResource("/images/accueil/quitter2.png")));
					    jl_quitter.setForeground(SystemColor.activeCaption);
					    jl_quitter.setForeground(Color.WHITE);
					    jl_quitter.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
					    jl_quitter.setText("Quitter");					
					}
					public void mouseExited(MouseEvent e) {
					    jl_quitter.setIcon(new ImageIcon(Main.class.getResource("/images/accueil/quitter1.png")));
					    jl_quitter.setText("");					
					}
			    });
			}
			return jl_quitter;
	    }
	    private JLabel getJLab_Fond() {
			if (jl_fond == null) {
				jl_fond = new JLabel("");
			    jl_fond.setIcon(new ImageIcon(Main.class.getResource("/images/fonds/fond.jpg")));
			    jl_fond.setBounds(0, 0, 1018, 569);
			}
			return jl_fond;
	    }
	    
	   


}