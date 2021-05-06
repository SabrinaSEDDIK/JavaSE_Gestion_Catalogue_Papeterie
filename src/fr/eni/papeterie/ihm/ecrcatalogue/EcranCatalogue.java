package fr.eni.papeterie.ihm.ecrcatalogue;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import fr.eni.papeterie.bll.BLLException;


public class EcranCatalogue extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable tableau=null;

	public EcranCatalogue() {
        super("Catalogue");
        setSize(new Dimension(430,200));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); //centre la fenêtre
        
		try {
			tableau = new JTable(new TableCatalogueModel());
			//bloquer redimensionnment
			tableau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//fixer la largeur d'une colonne
			tableau.getColumnModel().getColumn(0).setPreferredWidth(25);
			
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

       
        tableau.getColumnModel().getColumn(0).setCellRenderer(new TypeCellRenderer());
        /*Pour affecter les colonnes dont les données appartiennent à une classe particulière
         * tableau.setDefaultRenderer(Color.class, new ColorCellRenderer());*/
 
        /*Pour adapter la taille de la frame au contenu
         * pack();
         */
    }
 
    public static void main(String[] args) {
   
    
  //EXECUTER L'ECRAN PRINCIPAL dans un thread spécifique
  		SwingUtilities.invokeLater(new Runnable() {
  			public void run() {
  				EcranCatalogue frame;
  				frame = new EcranCatalogue();
				frame.setVisible(true);
  			}
  		});
    }
	
}
