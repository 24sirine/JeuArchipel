
package cases;

import jeudeplateau.Case;

import java.util.Random;
import fenetres.FenetrePrincipale;
import io.Console;
import jeuarchipel.Joueur;
import jeuarchipel.PlateauJeu;

//Crée l'action de la case puits

public class CasePuits extends Case {

	private boolean reponseQuestion = false;

	// Indique le nom de la case
	 
	public CasePuits() {
		super("Oups", 0);
	}

	/*
	 * Méthode gérant tous les cas d'un joueur en puits : 
	 * Si un joueur est resté 3 tours en puits, il doit payer 50 DT
	 * Si un joueur fait un double au lancé de dés, il peut sortir
	 * Si un joueur possède une carte Sortie de puits et qu'il l'utilise, il se libère
	*/
	public void actionCase(Joueur joueur, PlateauJeu plateau, FenetrePrincipale fp) {

		Console es = new Console();


		int lancé = plateau.des.lancerDes();
		int d1 = plateau.des.getDe1();
		int d2 = plateau.des.getDe2();

		if(joueur.getestDansPuits() == true){

			if(fp != null) fp.afficherDes(plateau);

			es.println("Voulez vous payer 50 DT pour sortir de puits ? ");

			if(getReponseQuestion()){
				es.println("OUI : " + joueur.getNom() + " décide de payer 50 DT  pour sortir du puits.");
				joueur.retirerArgent(50);
				reponseQuestion = false;
				joueur.setestDansPuits(false);
				joueur.settoursEnPuits(1);
				plateau.deplacerJoueur(joueur, lancé);
				es.println("" + joueur.getNom() + " lance les dés... [" + d1 + "][" + d2 + "]... et obtient un " + lancé + " !");
				es.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur " + plateau.getCaseActive().getNom());
				if(fp!= null) actionSortiepuits(plateau, joueur, fp);
			}
			else{
				if(joueur.gettoursEnPuits() > 2) {
					es.println("NON : " + joueur.getNom() + " est a son 3e tour en puits, il sort et paye 50 DT.");
					joueur.retirerArgent(50);
					joueur.setestDansPuits(false);
					joueur.settoursEnPuits(1);
					plateau.deplacerJoueur(joueur, lancé);
					es.println("" + joueur.getNom() + " lance les dés... [" + d1 + "][" + d2 + "]... et obtient un " + lancé + " !");
					es.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur " + plateau.getCaseActive().getNom());
					if(fp!=null) actionSortiepuits(plateau, joueur, fp);
				}
				else{
					es.println("NON : " + joueur.getNom() + " (tour " + joueur.gettoursEnPuits() + ") décide de ne pas payer et lance ses dés...");
					if(d1 == d2){
						es.println("  [" + d1 + "][" + d2 + "] Gagné! " + joueur.getNom() + " sort de puits sans payer!");
						joueur.setestDansPuits(false);
						joueur.settoursEnPuits(1);
						plateau.deplacerJoueur(joueur, lancé);
						es.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur " + plateau.getCaseActive().getNom());
						if(fp!=null) actionSortiepuits(plateau, joueur, fp);
					}
					else{
						es.println("  [" + d1 + "][" + d2 + "] Perdu!");
						joueur.settoursEnPuits(joueur.gettoursEnPuits() + 1);
					}
				}
			}
		}
		else{
			es.println(" > Attention , Danger !...");
			if(fp != null) fp.afficherMessage("Attention , Danger !...");
		}

	}

	@SuppressWarnings("static-access")
	@Override
	// Reprend le cours de la partie
	 
	public void fenetreAction(FenetrePrincipale fp) {

		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				reponseQuestion = true;
			fp.getPartie().reprendrePartie();
		}
		else if(fp.getPartie().getPM().getJoueurActif().getestDansPuits())
			fp.afficherFenetrePuits();
		else
			fp.getPartie().reprendrePartie();
	}

	@SuppressWarnings("static-access")
	public void actionSortiepuits(PlateauJeu plateau, Joueur joueur, FenetrePrincipale fp){

		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}

		plateau.getCase(joueur.getPosition()).fenetreAction(fp);
		fp.deplacerPion(joueur);
		fp.getPartie().pausePartie();
		while(fp.getPartie().getPausePartie() && !fp.getPartie().PARTIE_AUTO){ try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		} }

		plateau.getCase(joueur.getPosition()).actionCase(joueur, plateau, fp);
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
		return reponseQuestion;
	}

	@Override
	public boolean getpeutMettreArme() {
		return false;
	}

	@Override
	public void setProprietaire(Joueur j) {}

	@Override
	public void setReponseQuestion(boolean b) {
		this.reponseQuestion = b;
	}

	@Override
	public String toString() {
		return "Case puits [ " + super.toString() + ", reponseQuestion=" + reponseQuestion + "]";
	}

}