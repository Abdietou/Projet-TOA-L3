package pkg.dialogue;

import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import pkg.controle.connexion.ControleConnexion;
import pkg.etat.JasperMySQL_Par;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Doucouré Abdietou
 *
 */
public class FenStats extends JDialog {
	/**
	 * Cette classe est une interface graphique
	 * Elle permet d'accder au camembert des opération par an 
	 */

    private static final long serialVersionUID = 1L;
    private JPanel panel;
    // tableau de données associé au JFreeChart
    private DefaultPieDataset data = new DefaultPieDataset();
    private JButton print;
    private String annee;

    public FenStats(String annee) {
    	this.annee = annee;
    	initialize();
    }

    private void initialize() {
    	setBounds(100, 100, 747, 534);
    	getContentPane().setLayout(null);
    	getContentPane().add(getPanel());
    	getContentPane().add(getPrint());
    }
    private JPanel getPanel() {
		if (panel == null) {
		    panel = genereGraphicStats();
		    panel.setBounds(10, 58, 711, 428);
		    panel.setLayout(null);
		}
		return panel;
    }
    private JPanel genereGraphicStats(){
		JPanel panel;
		// classe pour création et importation des données pour générer le graphique
		JFreeChart chart;
		// ChartPanel : composant qui transforme le graphique en JPanel
		ChartPanel chartPane = null;
		String [] month = {"Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"};
		try{
		    ResultSet rs = ControleConnexion.getConnexion().createStatement().executeQuery("SELECT MONTH(date) AS mois, COUNT(*) AS nbr " +
			    "FROM Operation2 " +
			    "WHERE YEAR(date) = '"+ annee +"' " +
			    "GROUP BY MONTH(date)");
		    if(!rs.next()){
		    	JOptionPane.showMessageDialog(null, "Aucune données trouvées pour cette année");
		    }
		    rs.beforeFirst();
		    while(rs.next()){
		    	// injection des données dans le tableau
			data.setValue(month[rs.getInt("mois")-1], rs.getInt("nbr"));
		    }
		 // 1er paramètre : titre du graphique
		    // 2ème para : données
		    // 3ème : toolTip
		    // 4ème : url
		    chart = ChartFactory.createPieChart("Commandes " + annee, data, true, true, false);	   
		    chartPane = new ChartPanel(chart);
		    chartPane.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 1, true));
	
		    panel = chartPane;
		}catch(Exception e){
		    panel = new JPanel();
		    JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la génération du graphique", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		return panel;
    }
    
    private JButton getPrint() {
		if (print == null) {
		    print = new JButton("Imprimer le graphique");
		    print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    JasperMySQL_Par.imprimer("stats.jrxml");
			}
		    });
		    print.setIcon(new ImageIcon(FenStats.class.getResource("/images/gestion/imprimer2.png")));
		    print.setBounds(10, 10, 240, 42);
		}
		return print;
    }
}
