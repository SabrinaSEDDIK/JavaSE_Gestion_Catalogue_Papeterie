package fr.eni.papeterie.ihm.ecrcatalogue;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;


public class TableCatalogueModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	private final String[] entetes = {"", "Référence", "Marque", "Désignation", "Prix unitaire", "Stock"};
	
	
	CatalogueManager cm = new CatalogueManager();
	private List<Article> catalogue = cm.getCatalogue();
	private Article[] articles = new Article[catalogue.size()];
	
    public TableCatalogueModel() throws BLLException {
        super();
        //on transfère la liste des articles du catalogue dans un tableau
        for(int i=0; i<catalogue.size(); i++) {
    		articles[i] = catalogue.get(i);
    	}
    }
 
    public int getRowCount() {
        return articles.length;
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return articles[rowIndex].getClass().getSimpleName();
            case 1:
                return articles[rowIndex].getReference();
            case 2:
                return articles[rowIndex].getMarque();
            case 3:
                return articles[rowIndex].getDesignation();
            case 4:
                return articles[rowIndex].getPrixUnitaire();
            case 5:
            	return articles[rowIndex].getQteStock();
            default:
                return null; //Ne devrait jamais arriver
        }
    }


}
