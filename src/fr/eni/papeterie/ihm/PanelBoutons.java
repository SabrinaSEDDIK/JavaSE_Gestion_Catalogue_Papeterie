package fr.eni.papeterie.ihm;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PanelBoutons extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<IPanelBoutonsObserver> observateurs;


	private JPanel panelBoutons;


	private JButton btnPrecedent;


	private JButton btnNouveau;


	private JButton btnSave;


	private JButton btnSuivant;


	private JButton btnSupp;
	/*private int index;
	private CatalogueManager cm = new CatalogueManager();
	private List<Article> catalogue = cm.getCatalogue();*/
	
	
	public PanelBoutons() {
		super();
		observateurs = new ArrayList<>();
		
		for (IPanelBoutonsObserver observateur : observateurs) {
			addPanelBoutonObserver(observateur);
		}
	}
	
	public void addPanelBoutonObserver(IPanelBoutonsObserver obs) {
		//this.add(obs);
	}
	
	public JPanel getPanelBoutons() {
		if(panelBoutons==null) {
			panelBoutons = new JPanel();
			//layout par défaut du JPanel est le FlowLayout
			panelBoutons.add(getBtnPrecedent());
			panelBoutons.add(getBtnNouveau());
			panelBoutons.add(getBtnSave());
			panelBoutons.add(getBtnSupp());
			panelBoutons.add(getBtnSuivant());
		}
		return panelBoutons;
	}
	public JButton getBtnPrecedent() {
		if(btnPrecedent==null) {
			Icon icon = new ImageIcon(".\\src\\img\\Back24.gif");
			btnPrecedent = new JButton(icon);
			btnPrecedent.setToolTipText("Article précédent");
			btnPrecedent.addActionListener(new ActionListener() {
				
				

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Précédent");
					/*try {
						index--;
						if (index<0) {
							index=catalogue.size()-1;
						}
						//miseAJour();
						
					} catch (BLLException e1) {
						e1.printStackTrace();
					}*/
					
				}
			});
		}
		return btnPrecedent;
	}
	
	public JButton getBtnNouveau() {
		if(btnNouveau==null) {
			Icon icon = new ImageIcon(".\\src\\img\\New24.gif");
			btnNouveau = new JButton(icon);
			btnNouveau.setToolTipText("Nouveau");
			btnNouveau.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//nouvelArticle();
				}

			});
		}
		return btnNouveau;
	}
	
	public JButton getBtnSave() {
		if(btnSave==null) {
			Icon icon = new ImageIcon(".\\src\\img\\Save24.gif");
			btnSave = new JButton(icon);
			btnSave.setToolTipText("Enregistrer");
			btnSave.addActionListener(new ActionListener() {
				
				

				@Override
				public void actionPerformed(ActionEvent e) {
					/*
					//activer les boutons suivant et précédent
					//dans le cas où ils avaient été désactivés par le fait qu'il n'y ait plus d'articles dans le catalogue
					btnPrecedent.setEnabled(true);
					btnSuivant.setEnabled(true);
					try {
						//prélèvement et stockage des infos
						String designation = txtDesignation.getText();
						String reference = txtReference.getText();
						String marque = txtMarque.getText();
						float prix =  Float.parseFloat(txtPrix.getText());
						int stock = Integer.parseInt(txtStock.getText());
						int grammage = check80Grammes.isSelected()?80:100;
						String couleur = choixCouleur.getSelectedItem().toString();
					
						if(btnSupp.isEnabled()==true) { //mode modification
							catalogue.get(index).setDesignation(designation);
							catalogue.get(index).setMarque(marque);
							catalogue.get(index).setReference(reference);
							catalogue.get(index).setPrixUnitaire(prix);
							catalogue.get(index).setQteStock(stock);
							if(catalogue.get(index) instanceof Stylo) {
								((Stylo)catalogue.get(index)).setCouleur(couleur);
							}else if(catalogue.get(index) instanceof Ramette) {
								((Ramette)catalogue.get(index)).setGrammage(grammage);
							}
							cm.updateArticle(catalogue.get(index));
						}else { //mode ajout
							if(radioRamette.isSelected()) {
									cm.addArticle(new Ramette(marque, reference, designation, prix, 
										stock, grammage));
							}else if(radioStylo.isSelected()) {
								cm.addArticle(new Stylo(marque, reference, designation, prix, 
										stock, couleur));
							}
						}
						catalogue = cm.getCatalogue();
						miseAJour();
					}catch (NumberFormatException e1) {
						lblMessage.setText("Les champs sont incomplets ou incorrects");
						e1.printStackTrace();
					}catch(BLLException e2) {
						lblMessage.setText(e2.getMessage());
						e2.printStackTrace();
					}*/
				}
			});
		}
		return btnSave;
	}
	public JButton getBtnSupp() {
		if(btnSupp==null) {
			Icon icon = new ImageIcon(".\\src\\img\\Delete24.gif");
			btnSupp = new JButton(icon);
			btnSupp.setToolTipText("Supprimer");
			btnSupp.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					/*try {
						cm.removeArticle(catalogue.get(index));
						//réactualisation de la liste du catalogue
						catalogue = cm.getCatalogue();
						//gérer l'affichage après suppression
						if(index<catalogue.size()) {
							miseAJour(); 
							
						}else if(catalogue.size()==0) {
							nouvelArticle();
							btnPrecedent.setEnabled(false);
							btnSuivant.setEnabled(false);
						}else if(index==catalogue.size()) { 
							index=0; 
							miseAJour();
							
						}
					} catch (BLLException e1) {
						lblMessage.setText("La suppression n'a pu être effectuée");
						e1.printStackTrace();
					}*/
				}
			});
		}
		return btnSupp;
	}
	public JButton getBtnSuivant() {
		if(btnSuivant==null) {
			Icon icon = new ImageIcon(".\\src\\img\\Forward24.gif");
			btnSuivant = new JButton(icon);
			btnSuivant.setToolTipText("Article suivant");
			btnSuivant.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					/*try {
						index++;
						if (index >= catalogue.size()) {
							index=0;
						}
						miseAJour();
						
					} catch (BLLException e1) {
						e1.printStackTrace();
					}
					*/
				}
			});
		}
		return btnSuivant;
	}
	
	}

