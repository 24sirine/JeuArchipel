
package cartes;

import fenetres.FenetrePrincipale;
import io.Console;
import jeuarchipel.Joueur;
import jeuarchipel.PlateauJeu;
import jeudeplateau.Carte;

public class CartePayerArgent extends Carte {

	private int montant;

	public CartePayerArgent(String titre, String description, int montant) {
		super(titre, description);
		this.montant = montant;
	}

	// Méthode réalisant l'action de la carte. 
	
	@Override
	public void actionCarte(Joueur joueur, PlateauJeu plateau, FenetrePrincipale fp) {

		Console es = new Console();

		if(getNom().equals("Journée de taxe")) {
                    //si la carte chance est journée taxe : Le joueur dépose 50Dt à chaque joueur en plateau
			for(int i=0; i<plateau.getNbJoueurs(); i++) {
				if(plateau.getJoueur(i) != joueur && !plateau.getJoueur(i).getEstBanqueroute()) {
					plateau.getJoueur(i).ajouterArgent(50);
					joueur.retirerArgent(50);
				}
			}
			es.println(" > "+joueur.getNom()+" verse 50DT à chaque joueur.");
			if(fp!=null)
				fp.afficherMessage(joueur.getNom()+" verse 50DT à chaque joueur.");
		}
		else { 
                    //Dans tous les autres cas de cartePayerArgent (attention arnaqeur , impots et taxes et emplacement interdit ) , cette joueur dépose de l'argent dans la case BONUS(ayant l'indice 20)
			joueur.retirerArgent(montant);
			plateau.getCase(20).setPrix(plateau.getCase(20).getPrix() + montant);
			es.println(" > "+joueur.getNom()+" dépose "+montant+"DT au case bonus");
			if(fp !=null)
				fp.afficherMessage(joueur.getNom()+" dépose "+montant+"DT au case bonus");
		}
	}
	public int getMontant(){
		return this.montant;
	}

	@Override
	public String toString() {
		return "CartePayerArgent ["+ super.toString() +", montant= " + montant + "]";
	}
}