
package cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import fenetres.FenetrePrincipale;
import io.Console;
import jeuarchipel.Joueur;
import jeuarchipel.PlateauJeu;
import jeudeplateau.Case;

//Crée l'action de la case ile



public class Caseile extends Case {

	private Joueur proprietaire;
	private ArrayList<Integer> taxe = new ArrayList<Integer>();
	private String couleur;
	private int prixArme;
	private int nbArme = 0;
	private boolean peutMettreArme = false;
	private boolean reponseQuestion = false;

	//Indique le nom, le prix d'ile, la liste de ses taxes, le prix d'un armé , le nombre d'armé, et la couleur que possède l'ile
	
	public Caseile(String nom, int valeur, ArrayList<Integer> taxe, int prixArme, int nbArme, String couleur) {
		super(nom, valeur);
		this.couleur = couleur;
		this.taxe = taxe;
		this.prixArme = prixArme;
		this.nbArme = nbArme;
	}

	/*Méthode permettant de vérifier  :
	 Si personne ne possède l'ile, un joueur peut l'acheter
	 Si un joueur tombe sur une ile appartenant à un autre joueur, il paye un taxe au joueur qui le possède
	 si un joueur tombe sur un de ses iles il ne se passe rien, mais peut acheter des armées
	 */
	

	@SuppressWarnings({ "unused", "static-access" })
	public void actionCase(Joueur joueur, PlateauJeu plateau, FenetrePrincipale fp) {

		Console es = new Console();

		if(this.getProprietaire() == null) {
			if(getReponseQuestion()) {
				if(acheterTerrain(joueur, fp))
					fp.setMarqueurProprietaire(joueur, this);
			}
			else {
				es.println(" > " + joueur.getNom() + " décide de ne pas acheter cette ile.");
				fp.afficherMessage(joueur.getNom() + " décide de ne pas acheter cette ile.");
                                
			}
		}

		else if(this.getProprietaire() != joueur)
			payertaxe(joueur, fp);

		else {
			es.println(" > " + joueur.getNom() + " est sur son propre ile");
			fp.afficherMessage(joueur.getNom() + " est sur son propre ile");

			if(this.getpeutMettreArme() && fp.getPartie().PARTIE_AUTO) {
				this.ajouterarme(fp);
				fp.setArme(this);
				es.println(" > " + joueur.getNom() + " possède désormais " + getnbArme() + " armés" + (getnbArme()>0?"s":"") + " sur cette ile.");
			}
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

	//Permet l'ajout d'un armé sur une ile
	 
	public void ajouterarme(FenetrePrincipale fp){

		nbArme++;
		proprietaire.retirerArgent(this.getprixArme());

		if(this.nbArme <=4 ){
			System.out.println(" > " + proprietaire.getNom() + " a posé un arme sur "+getNom()+" !");
			if(fp!=null) fp.afficherMessage(" > " + proprietaire.getNom() + " a posé un arme sur "+getNom()+" !");
		}
		else{
			System.out.println(" > " + proprietaire.getNom() + " a atteint le nombre maximale d'armes sur "+getNom()+" et ne peut plus ajouter d'autres!");
			if(fp!=null) fp.afficherMessage(" > " + proprietaire.getNom() + " a atteint le nombre maximale d'armes sur "+getNom()+" et ne peut plus ajouter d'autres!");
		}
	}

	//Méthode permettant l'affichage d'une fenêtre pour l'achat d'un terrain, et reprenant le cours de la partie
	 
	@SuppressWarnings("static-access")
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
	public boolean getpeutMettreArme() {
		if(proprietaire.getListeCouleur().contains(this.getCouleur())){

			ArrayList<Case> couleur = new ArrayList<Case>();
			for(Case c: proprietaire.getListeTerrains())
				if(c.getCouleur() == this.getCouleur() && c != this)
					couleur.add(c);

			this.peutMettreArme = true;
			for(Case c:couleur) {
				if(!(this.getnbArme() == c.getnbArme() || this.getnbArme() == c.getnbArme() - 1))
					this.peutMettreArme = false;
			}

			if(proprietaire.getArgent() < this.getprixArme()) {
				this.peutMettreArme = false;
				System.out.println("Vous n'avez pas assez d'argent pour acheter un arme !");
			}
			if(getnbArme() == 5) {
				this.peutMettreArme = false;
				System.out.println("Vous avez atteint le nombre maximale des armés !");
			}
		}
		else
			this.peutMettreArme = false;

		return this.peutMettreArme;
	}

	@Override
	public int getTaxe() {
		int aPayer = taxe.get(getnbArme());
		if(proprietaire.getListeCouleur().contains(this.getCouleur()) && getnbArme() == 0)
			aPayer*=2; // taxe double si le joueur possède tous les terrains d'une couleur mais sans arme.

		return aPayer;
	}

	@Override
	public String getCouleur() {
		return couleur;
	}

	@Override
	public int getprixArme() {
		return prixArme;
	}

	@Override
	public int getnbArme() {
		return nbArme;
	}
	public void setnbArme(int i) {
		this.nbArme = i;
	}

	@Override
	public Joueur getProprietaire() {
		return proprietaire;
	}

	@Override
	public boolean getReponseQuestion() {
		return reponseQuestion;
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
		return "CaseTerrain ["+ super.toString() +", proprietaire=" + (proprietaire==null?"null":proprietaire.getNom()) + ", couleur=" + couleur + ", taxe=" + taxe
				+ ", prixArme=" + prixArme + ", peutMettreArme=" + peutMettreArme + ", nbArme=" + nbArme + "]";
	}

	
}