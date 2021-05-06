package fr.eni.papeterie.bll;



import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.Factory;

public class CatalogueManager {
	
	
	private ArticleDAO daoArticle = Factory.getArticleDAO();
	
	public CatalogueManager() throws BLLException{}
	
	
	public void addArticle(Article a) throws BLLException{
		validerArticle(a);
		try {
			daoArticle.insert(a);
		} catch (DALException e) {
			throw new BLLException("Erreur BLL", e);
		}
	}
	public void updateArticle(Article a) throws BLLException{
		validerArticle(a);
		try {
			daoArticle.update(a);
		} catch (DALException e) {
			throw new BLLException("Erreur BLL", e);
		}
	}
	public void removeArticle(Article a) throws BLLException{
		try {
			daoArticle.delete(a.getIdArticle());
		} catch (DALException e) {
			throw new BLLException("Erreur BLL", e);
		}
	}
	public List<Article> getCatalogue() throws BLLException{
		try {
			return daoArticle.selectAll();
		} catch (DALException e) {
			throw new BLLException("Erreur BLL", e);
		}
	}
	
	public void validerArticle(Article a) throws BLLException{
		//vérifier que l'article contient tous les attributs texte
		if(isBlank(a.getMarque())||isBlank(a.getDesignation())||isBlank(a.getReference())
				||(a instanceof Stylo && isBlank(((Stylo)a).getCouleur()))) {
			throw new BLLException("Tous les champs doivent être saisis");
		}
		//vérifier que la qteStock est positive
		if(a.getQteStock()<0) {
			throw new BLLException("Le stock ne peut pas être négatif");
		}
	}
	
	private boolean isBlank(String s) {
		return s==null||s.trim().isEmpty();
	}
	
}
