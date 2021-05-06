/**
 * 
 */
package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;



public class ArticleDAOJdbcImpl implements ArticleDAO {
	/*Fournit les services pour gérer le cycle de vie de l'objet Article
	 * c'est-à-dire le CRUD : Create Read Update Delete
	 */
	
	//constructeur
	public ArticleDAOJdbcImpl(){
	}
	
	
	//Requêtes SQL
	String insert = "INSERT INTO Articles (reference, marque, designation, prixUnitaire, "
			+ "qteStock, grammage, couleur, type) VALUES (?,?,?,?,?,?,?,?);";
	String select = "SELECT * FROM Articles WHERE idArticle = ?;";
	String selectAll = "SELECT * FROM Articles;";
	String update = "UPDATE Articles SET reference = ?, marque = ?, designation = ?, prixUnitaire = ?,"
			+ "qteStock = ?, grammage = ?, couleur=? "
			+ "WHERE idArticle = ?;";
	String delete = "DELETE FROM Articles WHERE idArticle = ?;";
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//METHODES DU CRUD
	
	//INSERT
	public void insert (Article article) throws DALException{
		
		//application du try with resources TWR
		try (Connection connection = JdbcTools.getConnection();
			PreparedStatement stmt = connection.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);) 
		{
			stmt.setString(1, article.getReference());
			stmt.setString(2, article.getMarque());
			stmt.setString(3, article.getDesignation());
			stmt.setFloat(4, article.getPrixUnitaire());
			stmt.setInt(5, article.getQteStock());
			if (article instanceof Stylo) {
				stmt.setNull(6, Types.INTEGER);
				stmt.setString(7, ((Stylo)article).getCouleur());
			}else { //ramette
				stmt.setInt(6, ((Ramette)article).getGrammage());
				stmt.setNull(7, Types.VARCHAR);
			}
			stmt.setString(8, article.getClass().getSimpleName());
			// Executer une requête
			stmt.execute();
			//récupérer idArticle de la BDD et l'affecter à l'attribut d'instance
			try (ResultSet rs = stmt.getGeneratedKeys();){
				if(rs.next())
					{article.setIdArticle(rs.getInt(1));}
			}
		} catch (SQLException e) {
			DALException ex = new DALException("Erreur : ", e);
			throw ex;
		} 
	}
	

	/////////////////////////////////////////////////////////////////////
	
	//SELECTBYID
	public Article selectById(int id) throws DALException{
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Article article = null;
		try {
			connection = JdbcTools.getConnection();
			// Créer une requête paramétrée
			stmt = connection.prepareStatement(select);
			stmt.setInt(1, id);
			// Executer la requête et stocker son résultat (executeQuery renvoie un ResultSet exploitable)
			rs = stmt.executeQuery();
			//Construction d'une instance d'article
			if(rs.next()) {
				if(rs.getString("type").equalsIgnoreCase(Stylo.class.getSimpleName())) {
					article = new Stylo(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference"), rs.getString("designation"),
							rs.getFloat("prixUnitaire"), rs.getInt("qteStock"), rs.getString("couleur"));
				}else { //ramette
					article = new Ramette(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference"), rs.getString("designation"),
							rs.getFloat("prixUnitaire"), rs.getInt("qteStock"), rs.getInt("grammage"));
				}
			}
			//retourner l'instance
			return article;
		} catch (SQLException e) {
			throw new DALException("Erreur : ", e);
		} finally {
			JdbcTools.fermer(rs);
			JdbcTools.fermer(stmt);
			JdbcTools.fermer(connection);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	//SELECTALL
	public List<Article> selectAll() throws DALException{
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		Article article = null;
		List<Article> articles = new ArrayList<>();
		try {
			connection = JdbcTools.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(selectAll);
			
			//Construction des instances d'articles
			while(rs.next()) {
				if(rs.getString("type").trim().equalsIgnoreCase(Stylo.class.getSimpleName())) {
					article = new Stylo(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference"), rs.getString("designation"),
							rs.getFloat("prixUnitaire"), rs.getInt("qteStock"), rs.getString("couleur"));
	
				}else { //ramette
					article = new Ramette(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference"), rs.getString("designation"),
							rs.getFloat("prixUnitaire"), rs.getInt("qteStock"), rs.getInt("grammage"));
				}
				articles.add(article);
			}
			return articles;
		} catch (SQLException e) {
			throw new DALException("Erreur : ", e);
		} finally {
			JdbcTools.fermer(rs);
			JdbcTools.fermer(stmt);
			JdbcTools.fermer(connection);
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	//UPDATE
	public void update(Article article) throws DALException{
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = JdbcTools.getConnection();
			stmt = connection.prepareStatement(update);
			stmt.setString(1, article.getReference() );
			stmt.setString(2, article.getMarque());
			stmt.setString(3, article.getDesignation());
			stmt.setFloat(4, article.getPrixUnitaire());
			stmt.setInt(5, article.getQteStock());
			if(article instanceof Stylo) {
				stmt.setNull(6, Types.INTEGER);
				stmt.setString(7, ((Stylo) article).getCouleur());
			}else {
				stmt.setInt(6,((Ramette)article).getGrammage());
				stmt.setNull(7, Types.VARCHAR);
			}
			stmt.setInt(8, article.getIdArticle());
			stmt.execute();
		} catch (SQLException e) {
			throw new DALException("Erreur : ", e);
		} finally {
			JdbcTools.fermer(stmt);
			JdbcTools.fermer(connection);
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	//DELETE
	public void delete(int id) throws DALException{
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = JdbcTools.getConnection();
			stmt = connection.prepareStatement(delete);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			throw new DALException("Erreur : ", e);
		} finally {
			JdbcTools.fermer(stmt);
			JdbcTools.fermer(connection);
		}
	}
}


