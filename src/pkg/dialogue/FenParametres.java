package pkg.dialogue;


import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Doucouré Abdietou
 *
 */

public class FenParametres extends JFrame {
	/**
	 * Cette classe est une interface graphique
	 * Elle permet de d'entrer les paramètres de connection de sa base de donnée pour s'y connecter plus facilement
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel jLab_Reseau;
	private JLabel jLab_Hote;
	private JLabel jLab_Utilisateur;
	private JLabel jLab_MDP;
	private JLabel jLab_BD;
	private JTextField jTxT_Hote;
	private JTextField jTxT_Utilisateur;
	private JTextField jTxT_BD;
	private JPasswordField jTxT_MDP;
	private JButton Btn_Valider;
	private JButton Btn_Quitter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenParametres frame = new FenParametres();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenParametres() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(950, 400, 404, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 379, 281));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getJLab_Hote());
		contentPane.add(getJLab_Utilisateur());
		contentPane.add(getJLab_MDP());
		contentPane.add(getJLab_BD());
		contentPane.add(getJTxT_Hote());
		contentPane.add(getJTxT_Utilisateur());
		contentPane.add(getJTxT_BD());
		contentPane.add(getJTxT_MDP());
		contentPane.add(getBtn_Valider());
		contentPane.add(getBtn_Quitter());
		contentPane.add(getJLab_Reseau());

		setTitle("Paramètres");
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logoEclipse.png")));
	}
	private JLabel getJLab_Reseau() {
		if (jLab_Reseau == null) {
			jLab_Reseau = new JLabel("label");
			jLab_Reseau.setIcon(new ImageIcon(FenParametres.class.getResource("/images/connection/reseau.png")));
			jLab_Reseau.setBounds(0, 0, 155, 279);
		}
		return jLab_Reseau;
	}
	private JLabel getJLab_Hote() {
		if (jLab_Hote == null) {
			jLab_Hote = new JLabel();
			jLab_Hote.setText("Hôte/Chemin");
			jLab_Hote.setBounds(172, 50, 126, 14);
		}
		return jLab_Hote;
	}
	private JLabel getJLab_Utilisateur() {
		if (jLab_Utilisateur == null) {
			jLab_Utilisateur = new JLabel();
			jLab_Utilisateur.setText("Utilisateur");
			jLab_Utilisateur.setBounds(172, 97, 129, 14);
		}
		return jLab_Utilisateur;
	}
	private JLabel getJLab_MDP() {
		if (jLab_MDP == null) {
			jLab_MDP = new JLabel();
			jLab_MDP.setText("Mot de passe");
			jLab_MDP.setBounds(172, 144, 137, 14);
		}
		return jLab_MDP;
	}
	private JLabel getJLab_BD() {
		if (jLab_BD == null) {
			jLab_BD = new JLabel();
			jLab_BD.setText("Base de données");
			jLab_BD.setBounds(172, 191, 122, 14);
		}
		return jLab_BD;
	}
	private JTextField getJTxT_Hote() {
		if (jTxT_Hote == null) {
			jTxT_Hote = new JTextField();
			jTxT_Hote.setText("127.0.0.1");
			jTxT_Hote.setBounds(new Rectangle(165, 34, 202, 28));
			jTxT_Hote.setBounds(167, 65, 202, 28);
		}
		return jTxT_Hote;
	}
	private JTextField getJTxT_Utilisateur() {
		if (jTxT_Utilisateur == null) {
			jTxT_Utilisateur = new JTextField();
			jTxT_Utilisateur.setText("Azerty");
			jTxT_Utilisateur.setBounds(167, 113, 202, 28);
		}
		return jTxT_Utilisateur;
	}
	private JTextField getJTxT_BD() {
		if (jTxT_BD == null) {
			jTxT_BD = new JTextField();
			jTxT_BD.setText("bdindigo");
			jTxT_BD.setBounds(167, 209, 202, 28);
		}
		return jTxT_BD;
	}
	private JPasswordField getJTxT_MDP() {
		if (jTxT_MDP == null) {
			jTxT_MDP = new JPasswordField();
			jTxT_MDP.setBounds(167, 161, 202, 28);
			jTxT_MDP.setText("secret");
		}
		return jTxT_MDP;
	}
	private JButton getBtn_Valider() {
		if (Btn_Valider == null) {
			Btn_Valider = new JButton();
			Btn_Valider.setIcon(new ImageIcon(FenParametres.class.getResource("/images/connection/valider.png")));
			Btn_Valider.setText("Valider");
			Btn_Valider.setFont(new Font("Dialog", Font.PLAIN, 12));
			Btn_Valider.setBounds(166, 250, 98, 18);
		}
		return Btn_Valider;
	}
	private JButton getBtn_Quitter() {
		if (Btn_Quitter == null) {
			Btn_Quitter = new JButton();
			Btn_Quitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			Btn_Quitter.setIcon(new ImageIcon(FenParametres.class.getResource("/images/connection/cancel.png")));
			Btn_Quitter.setText("Quitter");
			Btn_Quitter.setFont(new Font("Dialog", Font.PLAIN, 12));
			Btn_Quitter.setBounds(271, 250, 98, 18);}

		return Btn_Quitter;
	}
}