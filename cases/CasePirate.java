
package cases;

import fenetres.FenetrePrincipale;
import io.Console;
import jeuarchipel.Joueur;
import jeuarchipel.PlateauJeu;
import jeudeplateau.Case;

public class CasePirate extends Case {

	/// Indique le nom du pirate ainsi que le prix à payer 
	public CasePirate(String nom, int valeur) {
		super(nom, valeur);
	}

	// Méthode retirant de l'argent à un joueur et l'ajoutant au Case Bonus

	public void actionCase(Joueur joueur, PlateauJeu plateau, FenetrePrincipale fp) {

		Console es = new Console();

		es.println(" > " + joueur.getNom() + " dépose " + this.getPrix() + "DT au Case Bonus.");
		if(fp != null)
			fp.afficherMessage(joueur.getNom() + " dépose " + this.getPrix() + "DT au Case Bonus.");

		joueur.retirerArgent(this.getPrix());

		int nouveauMontant = plateau.getCase(20).getPrix() + this.getPrix();
		plateau.getCase(20).setPrix(nouveauMontant);
	}


	@Override
	//Reprend le cours de la partie
	
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}

	@Override
	public Joueur getProprietaire() {
		return null;
	}

	@Override
	public String getCouleur() {
		return null;
	}

	@Override
	public int getTaxe() {
		return 0;
	}

	@Override
	public int getprixArme() {
		return 0;
	}

	@Override
	public int getnbArme() {
		return 0;
	}

	@Override
	public boolean getReponseQuestion() {
		return false;
	}

	@Override
	public boolean getpeutMettreArme() {
		return false;
	}

	@Override
	public void setProprietaire(Joueur j) {}

	@Override
	public void setReponseQuestion(boolean b) {}

}