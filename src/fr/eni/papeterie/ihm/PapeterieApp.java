package fr.eni.papeterie.ihm;


import javax.swing.SwingUtilities;

import fr.eni.papeterie.bll.BLLException;

public class PapeterieApp {

	public static void main(String[] args) {
		
		//EXECUTER L'ECRAN PRINCIPAL dans un thread spécifique
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				EcranPrincipal frame;
				try {
					frame = new EcranPrincipal();
					frame.setVisible(true);
				} catch (BLLException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		
		

	}

}
