package pkg.dialogue;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class FenCamenbert extends JDialog {
	/**
	 * Cette classe est une interface graphique qui sert à montrer 
	 * un bilan desopérations sous forme de camenbert
	 */
	 private static final long serialVersionUID = 1L;
	    private JLabel jl_Camembert;
	    private JLabel jl__nombre;
	    private JLabel jl_stats;
	    /**
	     * Launch the application.
	     */
	    public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
			    public void run() {
					try {
					    FenCamenbert dialog = new FenCamenbert();
					    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					    dialog.setVisible(true);
					} catch (Exception e) {
					    e.printStackTrace();
					}
			    }
			});
	    }

	    /**
	     * Create the dialog.
	     */
	    public FenCamenbert() {
	    	initialize();
	    }
	    
	    public void initialize() {
	    	getContentPane().setBackground(Color.WHITE);
			setBounds(100, 100, 417, 236);
			getContentPane().setLayout(null);
			setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/gestion/logoEclipse.png")));
			setTitle("Camenbert");
			getContentPane().add(getJl_stats());
	    }
	    
	    
	    private JLabel getJl_stats() {
	    	if (jl_stats == null) {
	    		jl_stats = new JLabel("Stats");
	    		jl_stats.setIcon(new ImageIcon(FenCamenbert.class.getResource("/images/gestion/chart.png")));
	    		jl_stats.setFont(new Font("Segoe UI", Font.BOLD, 18));
	    		jl_stats.setBounds(24, 64, 164, 48);
	    		jl_stats.addMouseListener(new MouseAdapter() {
	    			public void mouseEntered(MouseEvent e) {
	    				jl_stats.setBorder(new LineBorder(Color.GRAY, 1, true));
	    			}
	    			public void mouseExited(MouseEvent e) {
	    				jl_stats.setBorder(null);
	    			}
	    			// récupération du paramètre ici année
	    			// puis ouverture de la fenêtre contenant le document jasper avec le graphique
	    			public void mouseClicked(MouseEvent e) {
	    				String vAnnee = JOptionPane.showInputDialog(null, "Entrez une année : ");
	    				FenStats stats = new FenStats(vAnnee);
	    				stats.setModal(true);
	    				stats.setLocationRelativeTo(stats.getParent());
	    				stats.setVisible(true);
	    			}
	    		});
	    	}
	    	return jl_stats;
	    }

}
