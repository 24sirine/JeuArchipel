
package cartes;

import fenetres.FenetrePrincipale;
import io.Console;
import jeuarchipel.Joueur;
import jeuarchipel.PlateauJeu;
import jeudeplateau.Carte;

import fenetres.FenetrePrincipale;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Type de Carte pour les déplacements de joueurs.
 *  Liste des champs :
 * position : int - position à laquelle on va placer le joueur.
 * deplacementRelatif : boolean - true si le déplacement est relatif à la position (ex: avancez de 3 cases) ou absolue (ex: avancez jusqu'à l'ile hawai).
 */
public class CarteDeplacement extends Carte {


	private int position;
	private boolean deplacementRelatif;

	public CarteDeplacement(String titre, String description, int pos, boolean deplacementRelatif) {
		super(titre, description);
		this.position = pos;
		this.deplacementRelatif = deplacementRelatif;
	}

	// Méthode réalisant l'action de la carte. 
	
	//@SuppressWarnings("static-access")
	@Override
	public void actionCarte(Joueur joueur, PlateauJeu plateau, FenetrePrincipale fp) {

		Console es = new Console();

		if(deplacementRelatif) //Pour les cartes "Reculez/avancez et X cases" //deplacement relatif par rapport à votre position
			plateau.deplacerJoueur(joueur, position);
		else {
			if(getNom().equals("Puits")) {
				if(joueur.getCarteSortiePuits()) {
					es.println(" > " + joueur.getNom() + " utilise sa carte et évite la Puits !");

					if(fp != null)
						fp.afficherMessage(joueur.getNom() + " utilise sa carte et évite la Puits !");
					joueur.setCarteSortiePuits(false);
					plateau.remettreCarteSortiePuitsDansPaquet();
				}
				else {
					joueur.setestDansPuits(true);
					plateau.deplacerJoueur(joueur, position-joueur.getPosition());
				}
			}
			else if(position-joueur.getPosition()<0)
				plateau.deplacerJoueur(joueur, position-joueur.getPosition()+40);
			else
				plateau.deplacerJoueur(joueur, position-joueur.getPosition());
		}

		if(getNom().equals("Puits")) {
			es.println(" > "+joueur.getNom()+" se retrouve en Puits.");
			if(fp != null)
				fp.afficherMessage(joueur.getNom()+" se retrouve en Puits.");
		}
		else {
			es.println(" > "+joueur.getNom()+" atterit sur "+plateau.getCaseActive().getNom());
			if(fp != null)
				fp.afficherMessage(joueur.getNom()+" atterit sur "+plateau.getCaseActive().getNom());

			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
                        //pour afficher la fenetre FenetreActionSurTerrain
			plateau.getCase(joueur.getPosition()).fenetreAction(fp);
			fp.deplacerPion(joueur);
			
			plateau.getCase(joueur.getPosition()).actionCase(joueur, plateau, fp);
		}

	}
	@Override
	public String toString() {
		return "CarteDeplacement [" + super.toString() + "]";
	}

}