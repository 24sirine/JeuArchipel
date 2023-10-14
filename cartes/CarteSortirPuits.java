package cartes;

import fenetres.FenetrePrincipale;
import io.Console;
import jeuarchipel.Joueur;
import jeuarchipel.PlateauJeu;
import jeudeplateau.Carte;

// Type de carte pour la sortie de puits. Cette carte est conservée par le joueur.

public class CarteSortirPuits extends Carte {
	
	public CarteSortirPuits(String titre, String description) {
		super(titre, description);
	}

	//Méthode réalisant l'action de la carte. 
	
	@Override
	public void actionCarte(Joueur joueur, PlateauJeu plateau, FenetrePrincipale fp) {

		Console es = new Console();
		es.println(" > "+joueur.getNom()+" recoit la carte 'Sortir de puits' !");
		if(fp != null)
			fp.afficherMessage(joueur.getNom()+" recoit la carte 'Sortir de puits' !");

		joueur.setCarteSortiePuits(true);
	}
	@Override
	public String toString() {
		return "CarteSortirPuits [" + super.toString() +"]";
	}
}
