
package cartes;

import fenetres.FenetrePrincipale;
import io.Console;
import jeuarchipel.Joueur;
import jeuarchipel.PlateauJeu;
import jeudeplateau.Carte;

public class CarteRecevoirArgent extends Carte {

	private int montant;

	public CarteRecevoirArgent(String titre, String description, int montant) {
		super(titre, description);
		this.montant = montant;
	}

	// Méthode réalisant l'action de la carte.
	

	@Override
	public void actionCarte(Joueur joueur, PlateauJeu plateau, FenetrePrincipale fp) {

		Console es = new Console();

		if(getNom().equals("Lampe Merveilleuse")) {
			for(int i=0; i<plateau.getNbJoueurs(); i++) {
				if(plateau.getJoueur(i) != joueur && !plateau.getJoueur(i).getEstBanqueroute()) {
					plateau.getJoueur(i).retirerArgent(10);
					joueur.ajouterArgent(10);
				}
			}
			es.println(" > "+joueur.getNom()+" reçoit 10DT de chaque joueur.");
			if(fp != null)
				fp.afficherMessage(joueur.getNom()+" reçoit 10DT de chaque joueur.");
		}

		else {
			joueur.ajouterArgent(montant);
			es.println(" > "+joueur.getNom()+" reçoit "+montant+"DT de la Banque");
		}
	}

	@Override
	public String toString()
	{
		return "CarteRecevoirArgent [" + super.toString() + " montant= " + montant + "]";
	}

}