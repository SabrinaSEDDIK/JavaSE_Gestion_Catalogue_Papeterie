package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.papeterie.dal.Settings;

public class JdbcTools {
	
	/*JDBCTools comprend des fonctions utilitaires:
	 * Ouverture de connection
	 * Fermeture des ressources autocloseables par exemple : connection, statement ou resulset
	 */
	
	public static Connection getConnection() throws SQLException {
		// Chargement du pilote JDBC
		// Méthode recommandée par Oracle : DriverManager.registerDriver(new SQLServerDriver());
		// Mais ici méthode Class.forName qui permet d'être indépendant de la technologie (jdbc) de la BDD 
		try {
			Class.forName(Settings.getProperty("driverjdbc"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//obtenir une connexion
		//if(connection==null) {
		Connection connection = DriverManager.getConnection(Settings.getProperty("url"), Settings.getProperty("user"), Settings.getProperty("password"));
		//}
		return connection;
	}
	
	public static void fermer(AutoCloseable ressource) {
		if(ressource!=null) {
			try {
				ressource.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
