
package cases;

import jeudeplateau.Carte;
import cartes.CartePayerArgent;
import fenetres.FenetrePrincipale;
import io.Console;
import jeuarchipel.Joueur;
import jeuarchipel.PlateauJeu;
import jeudeplateau.Case;


public class CaseChance extends Case {

	//Indique le nom de la case
	
	public CaseChance() {
		super("Chance", 0);
	}

	@Override
	public void actionCase(Joueur joueur, PlateauJeu plateau, FenetrePrincipale fp) {}

	@SuppressWarnings("static-access")
	@Override
	//Permet de tirer et afficher une carte chance
	
	public void fenetreAction(FenetrePrincipale fp) {

		Console es = new Console();

		Carte carte = fp.getPartie().getPM().tirerCarteChance();
		es.println(" > " + fp.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());
		fp.afficherMessage(fp.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());

		if(fp.getPartie().PARTIE_AUTO)
			fp.getPartie().reprendrePartie();
		else
			fp.afficherFenetreCarteChance(carte.getNom(), carte.getDesc());

		fp.getPartie().pausePartie();
		while(fp.getPartie().getPausePartie() && !fp.getPartie().PARTIE_AUTO){
			try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} }

		carte.actionCarte(fp.getPartie().getPM().getJoueurActif(), fp.getPartie().getPM(), fp);
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
		return "Case Chance [" + super.toString() + "]";
	}


	
}