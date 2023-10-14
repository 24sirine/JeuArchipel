package jeudeplateau;

import java.util.ArrayList;
import jeuarchipel.Joueur;

//Définit le plateau du jeu

public abstract class Plateau {

	private int joueurActifID = 0;
	private int nombreDeJoueurs = 2;
	private int nombreDeCases = 20;
	private int nombreDeTours = 1;
	private ArrayList<Case> cases = new ArrayList<Case>();
	public Dés des = new Dés();

	// Crée un plateau en fonction du nombre de joueur et nombre de cases
	
	public Plateau(int nombreDeJoueurs, int nbCases) {
		this.nombreDeJoueurs = nombreDeJoueurs;
		this.nombreDeCases = nbCases;
		for(int i=0; i<nombreDeCases; i++) {
			cases.add(null);
		}
	}

	/* PARTIE CASE */

	// Renvoie une case en fonction d'un index i
	public Case getCase(int i) {
		return this.cases.get(i);
	}

	//Définit une case en fonction d'un index i 
	public void setCase(int i, Case caze) {
		this.cases.set(i, caze);
		cases.get(i).setId(i);
	}

	// Renvoie le nombre de cases
	public int getNbCases() {
		return this.nombreDeCases;
	}

	/* PARTIE JOUEUR */

	// renvoie le nombre de joueurs
	public int getNbJoueurs() {
		return this.nombreDeJoueurs;
	}

	// Renvoie le joueur actif
	public int getJoueurActifID() {
		return this.joueurActifID;
	}

	// Sélectionne le joueur suivant
	public void setJoueurSuivant() {
		this.joueurActifID++;
		if(this.joueurActifID >= this.nombreDeJoueurs) {
			this.joueurActifID = 0;
			this.nombreDeTours++;
		}
	}

	/* PARTIE PLATEAU */

	//Renvoie le nombre de tours
	
	public int getNbTours() {
		return this.nombreDeTours;
	}

	/* PARTIE ABSTRAITE */

	// Méthode abstraite renvoyant vrai si la partie est finie
	
	public abstract boolean finPartie();
	//Méthode abstraite renvoyant le gagnant du jeu
	 
	public abstract Joueur estVainqueur();

}
