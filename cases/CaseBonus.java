
package cases;

import fenetres.FenetrePrincipale;
import io.Console;
import jeuarchipel.Joueur;
import jeuarchipel.PlateauJeu;
import jeudeplateau.Case;

//Crée l'action de la case Bonus

public class CaseBonus extends Case {

	//Indique le nom de la case et initialise sa valeur
	 
	public CaseBonus() {
		super("Case Bonus", 0);
	}

	//Méthode permettant à un joueur de récupérer l'argent de la case Bonus, réinitialisant ensuite celle-ci à 0
	
	public void actionCase(Joueur joueur, PlateauJeu plateau, FenetrePrincipale fp) {

		Console es = new Console();

		es.println(" > " + joueur.getNom() + " ramasse " + this.getPrix() + "DT de la case Bonus!");
		if(fp!=null)
			fp.afficherMessage(joueur.getNom() + " ramasse " + this.getPrix() + "DT de la case Bonus !");
		joueur.ajouterArgent(this.getPrix());
		this.setPrix(0);
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

	@Override
	public String toString() {
		return "Case Bonus [" + super.toString() + "]";
	}

}
