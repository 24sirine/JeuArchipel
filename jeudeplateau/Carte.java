package jeudeplateau;

import fenetres.FenetrePrincipale;
import jeuarchipel.Joueur;
import jeuarchipel.PlateauJeu;

// Définit les cartes du Jeu Archipel

public abstract class Carte {

	private String titre;
	private String description;

	// Définit une carte avec un titre et une description
	 
	public Carte(String titre, String description) {
		this.titre = titre;
		this.description = description;
	}
// Renvoie le titre de la carte
	
	public String getNom() {
		return this.titre;
	}

	// Renvoie la description de la carte
	
	public String getDesc() {
		return this.description;
	}

	//Action de la carte en fonction du joueur, sur le plateau
	
	public abstract void actionCarte(Joueur joueur, PlateauJeu plateau, FenetrePrincipale fp);

	@Override
	public String toString() {
		return "Carte [titre=" + titre + ", description=" + description + "]";
	}
}