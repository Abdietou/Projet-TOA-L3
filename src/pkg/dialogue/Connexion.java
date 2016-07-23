package pkg.dialogue;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import pkg.controle.connexion.ControleConnexion;


/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class Connexion extends JFrame {
	
	/**
	 * Cette classe est une interface graphique qui gère les demande de connexion
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel conteneur;
	private JLabel jl_cadre;
	private JLabel jl_connexion;
	private JLabel jl_login;
	private JTextField jt_login;
	private JLabel jl_mdp;
	private JPasswordField jp_mdp;
	private JButton jbtn_valider;
	private JButton jbtn_quitter;
	private JLabel jl_parametre;
	
	public Connexion(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 311);
		setResizable(false);
		conteneur = new JPanel();
		conteneur.setBorder(new EmptyBorder(5, 5, 5, 5));
		conteneur.setLayout(null);
		setContentPane(conteneur);
		conteneur.add(getJL_connexion());
		conteneur.add(getJl_cadre());
		conteneur.add(getJt_login());
		conteneur.add(getJp_mdp());
		conteneur.add(getJbtn_valider());
		conteneur.add(getJbtn_quitter());
		conteneur.add(getJl_login());
		conteneur.add(getJl_mdp());
		conteneur.add(getJl_parametre());
		
		setTitle("Identification");
		setLocationRelativeTo(null);
		// bouton ayant le focus par défaut
		getRootPane().setDefaultButton(jbtn_valider);
	}
		
		private JLabel getJL_connexion() {
			if (jl_connexion == null) {
				jl_connexion = new JLabel ("Connexion au programme");
				jl_connexion.setFont(new Font("Segoe UI", Font.PLAIN, 24));
				jl_connexion.setBounds(5,10,400,74);
				
			}
			return jl_connexion;
		}
		
		private JLabel getJl_cadre() {
			if (jl_cadre == null) {
				jl_cadre = new JLabel("");
				jl_cadre.setBounds(12, 13, 414, 203);
			}
			return jl_cadre;
			
		}
		
		private JLabel getJl_login(){
			if (jl_login == null){
			jl_login = new JLabel();
			jl_login.setText("Nom d'utilisateur");
			jl_login.setBounds(43, 114, 129, 20);
		}
			return jl_login;
		}
		
		private JLabel getJl_mdp(){
			if (jl_mdp == null){
				jl_mdp = new JLabel();
				jl_mdp.setText("Mot de passe");
				jl_mdp.setBounds(43, 157, 129, 20);
			}
			
			return jl_mdp;
		}
		
		private JTextField getJt_login() {
			if (jt_login == null) {
				jt_login = new JTextField();
				jt_login.setBounds(181, 113, 224, 28);
			}
			return jt_login;
		}
		
		private JPasswordField getJp_mdp() {
			if (jp_mdp == null) {
				jp_mdp = new JPasswordField();
				jp_mdp.setBounds(181, 154, 225, 28);
		}
			return jp_mdp;
		
	}
		
		// label pour accéder à la fenêtre de paramètres de connexion
		private JLabel getJl_parametre() {
			if (jl_parametre == null) {
				jl_parametre = new JLabel("Paramètres de connexion");
				jl_parametre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				jl_parametre.setBounds(12, 216, 188, 28);
				jl_parametre.setIcon(new ImageIcon(Connexion.class.getResource("/images/connection/switch-on.png")));
				jl_parametre.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						FenParametres laFenetre = new FenParametres();
						laFenetre.setVisible(true);
					}
				});
				jl_parametre.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			return jl_parametre;
		}
		
		private JButton getJbtn_valider() {
			if (jbtn_valider == null) {
				jbtn_valider = new JButton();
				jbtn_valider.setText("Valider");
				jbtn_valider.setBounds(212, 216, 110, 28);
				jbtn_valider.setIcon(new ImageIcon(Connexion
						.class
							.getResource("/images/connection/valider.png")));
					jbtn_valider.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							controleConnexion_Appel();
						}
					});
				}
			
			return jbtn_valider;
		}
		
		private JButton getJbtn_quitter() {
			if (jbtn_quitter == null) {
				jbtn_quitter = new JButton();
				jbtn_quitter.setText("Quitter");
				jbtn_quitter.setBounds(325, 216, 110, 28);
				jbtn_quitter.setIcon(new ImageIcon(Connexion.class.getResource("/images/connection/cancel.png")));
				jbtn_quitter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}				
				});
				jbtn_quitter.addKeyListener(new KeyAdapter() {   
					public void keyPressed(KeyEvent e) {   
					    if(e.getKeyCode()== 20){		
					    	System.exit(0);
					    } 
					}
			    });
			}
			return jbtn_quitter;
		}
		
		/**
		 * Cette méthode faire appel à la classe Controle connexion pour authentifier l'utilisateur
		 */
		private void controleConnexion_Appel() {
			String leNom = jt_login.getText();
			String leMotDePasse = String.valueOf(jp_mdp.getPassword());  	     
			    if(ControleConnexion.controle(leNom, leMotDePasse)){
					if(ControleConnexion.getControleConnexion()){
						Connexion.this.dispose();     
						Main laFenetreMenu = new Main();
						laFenetreMenu.setVisible(true); 
					}
				else 
					JOptionPane.showMessageDialog(null, "Impossible de se connecter" +
							" à la base de données." +'\n' +'\n'
							+ "Vos nom et mot de passe sont corrects." +'\n'
							+ "Mais les paramètres pour le pilote et la base de données "
							+ "doivent être vérifiés." +'\n' +'\n'
							+ "Contactez le responsable informatique.",
							"ALERTE", JOptionPane.ERROR_MESSAGE);
				}
		}
		
		// méthode main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run(){
				try{
					Connexion frame = new Connexion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				}
			});
		}
	}

	
	

