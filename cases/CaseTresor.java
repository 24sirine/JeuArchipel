
package cases;

import java.util.Random;
import fenetres.FenetrePrincipale;
import io.Console;
import jeuarchipel.Joueur;
import jeuarchipel.PlateauJeu;
import jeudeplateau.Case;

//Crée l'action d'une case tresor


public class CaseTresor extends Case {

	private Joueur proprietaire;
	private boolean reponseQuestion = false;

	// Indique le nom et ajoute le prix d'un trésor
	
	public CaseTresor(String nom) {
		super(nom, 200);
	}

	@Override
	//Méthode gérant l'appropriation d'un trésor à un joueur 
	 // Gère le changement du taxe en fonction du nombre de trésors possédés par un joueur
	
	public void actionCase(Joueur joueur, PlateauJeu plateau, FenetrePrincipale fp) {

		Console es = new Console();

		if(this.getProprietaire() == null) {
			if(getReponseQuestion()) {
				if(acheterTerrain(joueur, fp))
					fp.setMarqueurProprietaire(joueur, this);
			}
			else {
				es.println(" > " + joueur.getNom() + " décide de ne pas acheter ce tresor .");
				fp.afficherMessage(joueur.getNom() + " décide de ne pas acheter ce tresor.");
			}
		}
		else if(this.getProprietaire() != joueur)
			payertaxe(joueur, fp);

		else {
			es.println(" > " + joueur.getNom() + " est dans sa propre case de tresor.");
			fp.afficherMessage(joueur.getNom() + " est dans sa propre case de tresor.");
		}
	}


	public boolean acheterTerrain(Joueur joueur, FenetrePrincipale fp) {
		if((joueur.getArgent() - this.getPrix()) <= 0) {
			System.out.println("Vous n'avez pas assez d'argent!");
			return false;
		}
		else {
			setProprietaire(joueur);
			joueur.ajouterTerrain(this);
			joueur.retirerArgent(this.getPrix());
			joueur.setNbTresor(joueur.getNbTresor() + 1);

			System.out.println(" > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "DT");
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "DT");
			return true;
		}
	}

	public void payertaxe(Joueur joueur, FenetrePrincipale fp) {
		String beneficiaire = "la Banque";

		if(!this.getProprietaire().getestDansPuits()) {

			joueur.retirerArgent(getTaxe());

			if(!this.getProprietaire().getEstBanqueroute()) {
				this.getProprietaire().ajouterArgent(getTaxe());
				beneficiaire = this.getProprietaire().getNom();
			}
			System.out.println(" > " + joueur.getNom() + " paye un taxe de " + getTaxe() + "DT à " + beneficiaire);
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " paye un taxe de " + getTaxe() + "DT à " + beneficiaire);
		}
		else {
			System.out.println(" > Le propriétaire est en puits. " + joueur.getNom() + " ne paye pas de taxe.");
			if(fp!=null) fp.afficherMessage("Le propriétaire est en puits. " + joueur.getNom() + " ne paye pas de taxe.");
		}
	}


	@SuppressWarnings("static-access")
	@Override
	// Affiche une fenêtre d'achat de terrain
	
	public void fenetreAction(FenetrePrincipale fp) {

		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				reponseQuestion = true;
			fp.getPartie().reprendrePartie();
		}
		else if(this.getProprietaire() == null)
			fp.afficherFenetreAchatTerrain();
		else
			fp.getPartie().reprendrePartie();
	}


	/* ===========================
	   Méthodes abstraites de Case
	   =========================== */

	@Override
	public Joueur getProprietaire() {
		return proprietaire;
	}

	@Override
	public String getCouleur() {
		return null;
	}

	@Override
	public int getTaxe() {
		return proprietaire != null ? 50 * this.getProprietaire().getNbTresor() : 0;
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
	public void setProprietaire(Joueur j) {
		this.proprietaire = j;
	}

	@Override
	public void setReponseQuestion(boolean b) {
		this.reponseQuestion = b;
	}

	@Override
	public String toString() {
		return "Case tresor [" + super.toString() + ", proprietaire=" + (proprietaire==null?"null":proprietaire.getNom()) + "]";
	}

	

}