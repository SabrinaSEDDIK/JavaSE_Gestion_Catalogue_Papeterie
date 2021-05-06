package fr.eni.papeterie.ihm;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;


public class EcranPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ATTRIBUTS D'INSTANCE
	private JPanel panelRadio, panelCheckBox, panelBoutons;
	private JLabel lblReference, lblDesignation, lblMarque, lblStock, lblPrix, lblType, lblGrammage, lblCouleur, lblMessage;
	private JTextField txtReference, txtDesignation, txtMarque, txtStock, txtPrix;
	private JRadioButton radioStylo, radioRamette;
	private JCheckBox check80Grammes, check100Grammes;
	private JComboBox<String> choixCouleur;
	private JButton btnPrecedent, btnNouveau, btnSave, btnSupp, btnSuivant;
	private CatalogueManager cm = new CatalogueManager();
	private List<Article> catalogue = cm.getCatalogue();
	private String[] couleurs = {"","bleu","vert","rouge","jaune","noir"};
	
	private static int index = 0;
	private ButtonGroup groupeRadio;
	ButtonGroup groupeCheckBox;
	
	
	//CONSTRUCTEUR

	public EcranPrincipal() throws BLLException{
		super("Catalogue d'articles"); //this.setTitle("Papeterie");
		setSize(new Dimension(350,450)); //largeur hauteur
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		// JFrame.HIDE_ON_CLOSE est la valeur par défaut : cache la fenêtre sans terminer l'appli
		// DISPOSE_ON_CLOSE : l'appli se termine quand la fenêtre fermée est la dernière de l'appli
		//EXIT_ON_CLOSE : l'appli se ferme à la fermeture de la fenêtre
		setResizable(true);
		setLocationRelativeTo(null); //centre la fenêtre
		initIHM();
	}

	

	private void initIHM() throws BLLException  {
		//instanciation du Container principal
		JPanel panel = new JPanel();
		
		//Attribuer un layout au conteneur
		panel.setOpaque(true); //recommandé par Oracle
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Ajout des composants graphiques aux JPanel
		//Ligne 1 (Lignes 1 à 5 : Label + champ de saisie)
		gbc.insets = new Insets(5,5,5,5); //padding externe
		gbc.gridy = 0;
		gbc.gridx = 0;
		panel.add(getLblReference(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtReference(), gbc);
		
		//Ligne 2
		gbc.gridy = 1;
		gbc.gridx = 0;
		panel.add(getLblDesignation(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtDesignation(), gbc);
		
		//Ligne 3
		gbc.gridy = 2;
		gbc.gridx = 0;
		panel.add(getLblMarque(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtMarque(), gbc);
		
		//Ligne 4
		gbc.gridy = 3;
		gbc.gridx = 0;
		panel.add(getLblStock(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtStock(), gbc);
		
		//Ligne 5
		gbc.gridy = 4;
		gbc.gridx = 0;
		panel.add(getLblPrix(), gbc);
		gbc.gridx = 1;
		panel.add(getTxtPrix(), gbc);
		
		//Lignes 6  Boutons Radio (les affilier à un groupe pour ne pouvoir en sélectionner qu'un seul)
		gbc.gridy = 5;
		gbc.gridx = 0;
		panel.add(getLblType(), gbc);
		//on affilie les boutons à un groupe (attention à l'ordre)
		groupeRadio = new ButtonGroup();
		groupeRadio.add(getRadioRamette());
		groupeRadio.add(getRadioStylo());
		//on ajoute le conteneur radio au conteneur principal
		gbc.gridx = 1;
		panel.add(getPanelRadio(), gbc);
		
		
		//Lignes 7   Checkbox
		gbc.gridy = 6;
		gbc.gridx = 0;
		panel.add(getLblGrammage(), gbc);
		groupeCheckBox = new ButtonGroup();
		groupeCheckBox.add(getCheck80Grammes());
		groupeCheckBox.add(getCheck100Grammes());
		gbc.gridx = 1;
		panel.add(getPanelCheckBox(), gbc);
		
		
		//Ligne 8 - Sélecteur de couleur
		gbc.gridy = 7;
		gbc.gridx = 0;
		panel.add(getLblCouleur(), gbc);
		gbc.gridx = 1;
		panel.add(getChoixCouleur(), gbc);
		
		//Ligne 9 - Panel de boutons
		gbc.gridy = 8;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		panel.add(getPanelBoutons(), gbc);
		
		//Ligne 10 - Message
		gbc.gridy = 9;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		panel.add(getLblMessage(), gbc);
		
		
		//ETAPE FINALE : Affecter le panel à l'écran principal (attacher le Container au JFrame)
		//contentPane est de type Container et getPanelPrincipal renvoie un JPanel
		//on ajoute des composants au Container via .add(Component c)
		this.setContentPane(panel);    
		
		
	}

	//GETTERS

	// le if pmt de créer l'instance que si nécessaire et de bien ordonner le code(lazy instanciation)
	//c'est une BP (bonne pratique)
	public JLabel getLblReference() {
		if(lblReference == null) {
			lblReference = new JLabel("Référence ");
		}
		return lblReference;
	}
	public JLabel getLblDesignation() {
		if(lblDesignation == null) {
			lblDesignation = new JLabel("Désignation ");
		}
		return lblDesignation;
	}
	public JLabel getLblMarque() {
		if(lblMarque == null) {
			lblMarque = new JLabel("Marque ");
		}
		return lblMarque;
	}
	public JLabel getLblStock() {
		if(lblStock == null) {
			lblStock = new JLabel("Stock ");
		}
		return lblStock;
	}
	public JLabel getLblPrix() {
		if(lblPrix == null) {
			lblPrix = new JLabel("Prix ");
		}
		return lblPrix;
	}
	public JLabel getLblType() {
		if(lblType == null) {
			lblType = new JLabel("Type ");
		}
		return lblType;
	}
	public JLabel getLblGrammage() {
		if(lblGrammage == null) {
			lblGrammage = new JLabel("Grammage ");
		}
		return lblGrammage;
	}
	public JLabel getLblCouleur() {
		if(lblCouleur == null) {
			lblCouleur = new JLabel("Couleur ");
		}
		return lblCouleur;
	}
	public JTextField getTxtReference() throws BLLException{
		if(txtReference==null) {
			txtReference= new JTextField(catalogue.get(index).getReference(), 20);
			//txtReference.getText(); .setText();
		}
		return txtReference;
	}
	public JTextField getTxtDesignation() throws BLLException{
		if(txtDesignation==null) {
			txtDesignation=new JTextField(catalogue.get(index).getDesignation(),20);
		}
		return txtDesignation;
	}
	public JTextField getTxtMarque() throws BLLException{
		if(txtMarque==null) {
			txtMarque=new JTextField(catalogue.get(index).getMarque(),20);
		}
		return txtMarque;
	}
	public JTextField getTxtStock() throws BLLException {
		if(txtStock==null) {
			txtStock =new JTextField(String.valueOf(catalogue.get(index).getQteStock()),20);
		}
		return txtStock;
	}
	public JTextField getTxtPrix() throws BLLException {
		if(txtPrix==null) {
			txtPrix=new JTextField(String.valueOf(catalogue.get(index).getPrixUnitaire()),20);
		}
		return txtPrix;
	}
	private JPanel getPanelRadio() throws BLLException {
		if(panelRadio==null) {
			panelRadio = new JPanel();
			//on attribue un layout
			panelRadio.setLayout(new BoxLayout(panelRadio, BoxLayout.PAGE_AXIS));
			//on ajoute les boutons au conteneur
			panelRadio.add(getRadioRamette());
			panelRadio.add(getRadioStylo());
		}
		return panelRadio;
	}
	private JRadioButton getRadioRamette() throws BLLException {
		if(radioRamette==null) {
			radioRamette = new JRadioButton("Ramette", catalogue.get(index) instanceof Ramette);
			radioRamette.setAlignmentX(Component.LEFT_ALIGNMENT);
			radioRamette.setEnabled(false);
		}
		return radioRamette;
	}
	private JRadioButton getRadioStylo() throws BLLException {
		if(radioStylo==null) {
			radioStylo = new JRadioButton("Stylo", catalogue.get(index) instanceof Stylo);
			radioStylo.setAlignmentX(Component.LEFT_ALIGNMENT);
			radioStylo.setEnabled(false);
		}
		return radioStylo;
	}
	private JPanel getPanelCheckBox() throws BLLException {
		if(panelCheckBox==null) {
			panelCheckBox = new JPanel();
			panelCheckBox.setLayout(new BoxLayout(panelCheckBox, BoxLayout.PAGE_AXIS));
			panelCheckBox.add(getCheck80Grammes());
			panelCheckBox.add(getCheck100Grammes());
		}
		return panelCheckBox;
	}
	private JCheckBox getCheck80Grammes() throws BLLException {
		if(check80Grammes==null) {
			check80Grammes = new JCheckBox("80 grammes");
			check80Grammes.setAlignmentX(Component.LEFT_ALIGNMENT);
			if(catalogue.get(index) instanceof Stylo) {
				check80Grammes.setEnabled(false);
			}else {
				check80Grammes.setEnabled(true);
				check80Grammes.setSelected(((Ramette)catalogue.get(index)).getGrammage()==80);
			}
			
		}
		return check80Grammes;
	}
	private JCheckBox getCheck100Grammes() throws BLLException {
		if(check100Grammes==null) {
			check100Grammes = new JCheckBox("100 grammes");
			check100Grammes.setAlignmentX(Component.LEFT_ALIGNMENT);
			if(catalogue.get(index) instanceof Stylo) {
				check100Grammes.setEnabled(false);
			}else {
				check100Grammes.setEnabled(true);
				check100Grammes.setSelected(((Ramette)catalogue.get(index)).getGrammage()==100);
			}
		}
		return check100Grammes;
	}
	private JComboBox<String> getChoixCouleur() throws BLLException {
		if(choixCouleur==null) {
			choixCouleur = new JComboBox<String>(couleurs);
			//choixCouleur.setMaximumRowCount(3);
			//choixCouleur.setSelectedIndex(index);
			if(catalogue.get(index) instanceof Stylo) {
				choixCouleur.setSelectedItem(((Stylo)catalogue.get(index)).getCouleur());
			}else {
				choixCouleur.setEnabled(false);
			}
		}
		return choixCouleur;
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
					try {
						index--;
						if (index<0) {
							index=catalogue.size()-1;
						}
						miseAJour();
						
					} catch (BLLException e1) {
						e1.printStackTrace();
					}
					
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
					nouvelArticle();
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
					}
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
					try {
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
					}
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
					try {
						index++;
						if (index >= catalogue.size()) {
							index=0;
						}
						miseAJour();
						
					} catch (BLLException e1) {
						e1.printStackTrace();
					}
					
				}
			});
		}
		return btnSuivant;
	}
	private JLabel getLblMessage() {
		if(lblMessage==null) {
			lblMessage = new JLabel("Article " + (index +1));
		}
		
		return lblMessage;
	}
	
	private void miseAJour() throws BLLException {
		btnSupp.setEnabled(true);
		lblMessage.setText("Article " + (index+1));
		//champs texte
		txtReference.setText(catalogue.get(index).getReference());
		txtDesignation.setText(catalogue.get(index).getDesignation());
		txtMarque.setText(catalogue.get(index).getMarque());
		txtStock.setText(String.valueOf(catalogue.get(index).getQteStock()));
		txtPrix.setText(String.valueOf(catalogue.get(index).getPrixUnitaire()));
		//type
		radioRamette.setEnabled(false);
		radioStylo.setEnabled(false);
		radioRamette.setSelected(catalogue.get(index) instanceof Ramette);
		radioStylo.setSelected(catalogue.get(index) instanceof Stylo);
		//grammage
		check80Grammes.setEnabled(catalogue.get(index) instanceof Ramette);
		check100Grammes.setEnabled(catalogue.get(index) instanceof Ramette);
		if(catalogue.get(index) instanceof Ramette) {
			check80Grammes.setSelected(((Ramette)catalogue.get(index)).getGrammage()==80);
			check100Grammes.setSelected(((Ramette)catalogue.get(index)).getGrammage()==100);
		}
		else { //ne marche pas : (
			check80Grammes.setSelected(false);
			check100Grammes.setSelected(false);
		}
		//couleur
		choixCouleur.setEnabled(catalogue.get(index) instanceof Stylo);
		if(catalogue.get(index) instanceof Stylo) {
			choixCouleur.setSelectedItem(((Stylo)catalogue.get(index)).getCouleur());
		}else {
			choixCouleur.setSelectedItem(couleurs[0]);
		}
	}
	
	private void radioRametteEcoute() {
		radioRamette.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//grammage
				check80Grammes.setEnabled(true);
				check100Grammes.setEnabled(true);
				//couleur
				choixCouleur.setEnabled(false);
			}
		});
			
	}
	private void radioStyloEcoute() {
		radioStylo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//grammage
				check80Grammes.setEnabled(false);
				check100Grammes.setEnabled(false);
				//couleur
				choixCouleur.setEnabled(true);
				
			}
		});
			
	}

	private void viderChamps() {
		//champs texte
		txtReference.setText("");
		txtDesignation.setText("");
		txtMarque.setText("");
		txtStock.setText("");
		txtPrix.setText("");
		groupeRadio.clearSelection();
		//type ON
		radioRamette.setEnabled(true);
		radioStylo.setEnabled(true);
		radioRametteEcoute();
		radioStyloEcoute();
		
		
		
		
		//couleur
		choixCouleur.setSelectedItem(couleurs[0]);
		lblMessage.setText("Ajouter un nouvel article");
	}
	
	private void nouvelArticle() {
		viderChamps();
		index = catalogue.size();
		System.out.println("Nouveau " + index);
		btnSupp.setEnabled(false);
		lblMessage.setText("Nouvel article");
	}
}


