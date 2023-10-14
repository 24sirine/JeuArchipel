
package cases;

import fenetres.FenetrePrincipale;
import io.Console;
import jeuarchipel.Joueur;
import jeuarchipel.PlateauJeu;
import jeudeplateau.Case;

//Crée l'action d'une case départ


public class CaseDepart extends Case {

	//Indique le nom de la case
	
	public CaseDepart() {
		super("Depart", 0);
	}

	//Ajoute l'argent du passage sur la case
	 
	public void actionCase(Joueur joueur, PlateauJeu plateau, FenetrePrincipale fp) {

		Console es = new Console();

		joueur.ajouterArgent(200);
		es.println(" > " + joueur.getNom() + " s'arrête sur la case départ: il reçoit 200 DT supplémentaire !");
		if(fp!=null) fp.afficherMessage(joueur.getNom() + " s'arrête sur la case départ et reçoit 200 DT !");
	}


	@Override
	//Reprend la partie
	
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}


	
	/* ===========================
	   Méthodes abstraites de Case
	   =========================== */

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

	@Override
	public String toString() {
		return "Case Depart ["+super.toString()+"]";
	}

}