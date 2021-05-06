package fr.eni.papeterie.ihm;

public interface IPanelBoutonsObserver {
	//une interface ne contient que des méthodes abstraites et des constantes
	abstract void precedent();
	abstract void suivant();
	abstract void nouveau();
	abstract void enregistrer();
	abstract void supprimer();
}
