package fr.eni.papeterie.dal;

import java.util.List;

import fr.eni.papeterie.bo.Article;

public interface ArticleDAO {
	
	public abstract void insert (Article article) throws DALException;
	public abstract Article selectById(int id) throws DALException;
	public abstract List<Article> selectAll() throws DALException;
	public abstract void update(Article article) throws DALException;
	public abstract void delete(int id) throws DALException;
	
	
}
