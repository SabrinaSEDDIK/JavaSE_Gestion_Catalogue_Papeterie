package fr.eni.papeterie.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;

public class AppliTestBLL {

	public static void main(String[] args) throws DALException {
	
		// Instanciation du jeu d'essai
		List<Article> articles = new ArrayList<>();
		Stylo stylo = new Stylo("Bic", "BBOrange", "Bic bille Orange", 1.2f, 20, "bleu");
		articles.add(stylo);
		articles.add(new Ramette("Clairef", "CRA4S", "Ramette A4 Sup", 9f, 20, 80));
		articles.add(new Stylo("Stypen", "PlumeS", "Stylo Plume Stypen", 5.5f, 20, "jaune"));
		articles.add(new Stylo("Waterman", "WOBGreen", "Waterman Orion Bille vert", 4.2f, 35, "vert"));
		articles.add(new Stylo("Parker", "PlumeP", "Stylo Plume Parker", 6.0f, 12, "noir"));
		articles.add(new Ramette("ProDesign", "ForLaser", "A4 Special laser", 5.5f, 55, 100));
		
		
		CatalogueManager mger = null;
		try {
			mger = new CatalogueManager();
		} catch (BLLException e1) {
			e1.printStackTrace();
		}

		// Ajout d'un article au catalogue
		try {
			for (Article art : articles) {
				mger.addArticle(art);
			}
			System.out.println(mger.getCatalogue());

		} catch (BLLException e) {
			e.printStackTrace();
		}
		/*
		// Modification d'un article
		try {
			((Stylo) stylo).setCouleur("noir");
			((Stylo) stylo).setDesignation("Bic bille noir");
			((Stylo) stylo).setReference("BBNoir");
			mger.updateArticle(stylo);
			System.out.println("Article après modification  : " + stylo.toString());
		} catch (BLLException e) {
			e.printStackTrace();
		}

		// Suppression d'un article
		try {
			mger.removeArticle(stylo);
			System.out.println(mger.getCatalogue());
		} catch (BLLException e) {
			e.printStackTrace();
		}
		*/
	}

}