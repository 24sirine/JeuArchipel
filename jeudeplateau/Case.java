package jeudeplateau;

import java.util.ArrayList;
import fenetres.FenetrePrincipale;
import javafx.scene.shape.Polygon;
import jeuarchipel.Joueur;
import jeuarchipel.PlateauJeu;

public abstract class Case {

	private String nom;
	private int id = 0;
	private int valeur = 0; //prix de la case
	private Polygon marqueur = new Polygon();
	public ArrayList<Polygon> armes = new ArrayList<Polygon>();

	// Définit le nom de la case
	 
	public Case(String nom, int valeur) {
		this.nom = nom;
		this.valeur = valeur;

	
		for(int i=0; i<6; i++) {
			Polygon arme = new Polygon();
			armes.add(arme);
		}
	}

	//Renvoie le nom de la case
	
	public String getNom() {
		return nom;
	}

	// Définit l'identifiant de la case
	
	public int getId(){
		return id;
	}

	// Met à jour l'identifiant de la case
	
	public void setId(int id){
		this.id = id;
	}

	// Renvoie le prix de la case
	
	public int getPrix() {
		return valeur;
	}

	//Définit le prix de la case
	
	public void setPrix(int valeur) {
		this.valeur = valeur;
	}

	

	//Renvoie le marqueur de possession d'une case d'un joueur
	
	public Polygon getMarqueur(){
		return this.marqueur;
	}

	//Définit un marqueur de possession d'une case
	 
	public void setMarqueur(Polygon r){
		this.marqueur = r;
	}

	/* PARTIE ABSTRAITE */

	//Renvoie le joueur propriétaire d'une case
	
	public abstract Joueur getProprietaire();

	//Renvoie la couleur de la case
	
	public abstract String getCouleur();

	// Renvoie le taxe du terrain en fonction du nombre des armées posées sur le terrain
	
	public abstract int getTaxe();

	//Renvoie le prix d'un armé
	
	public abstract int getprixArme();

	//Renvoie le nombre des armés posédées sur un terrain
	
	public abstract int getnbArme();

	//Renvoie la réponse à une question (Pour l'achat d'un terrain par exemple)
	
	public abstract boolean getReponseQuestion();

	/* Permet de savoir si un joueur peut ajouter un armé ou non, il peut si : 
	  Il possède tous les terrains d'une même couleur ( série des terrains )
	 Le nombre de armes sur chaque terrain doit être identique pour en rajouter (cad 1 arme sur chaque terrain pour pouvoir en poser une deuxième)
	 */
	public abstract boolean getpeutMettreArme();

	// Définit le propriétaire de la case.
	
	public abstract void setProprietaire(Joueur j);

	//Permet de définir la réponse de l'utilisateur choisit via les boutons de l'interface
	
	public abstract void setReponseQuestion(boolean b);

	//Appelle la fenêtre d'action de la case (ex: 'Acheter un terrain', 'Tirer une carte', etc.)
	
	public abstract void fenetreAction(FenetrePrincipale fp);

	//Action de la case lorsqu'un joueur tombe dessus

	public abstract void actionCase(Joueur joueur, PlateauJeu plateau, FenetrePrincipale fp);


	@Override
	public String toString() {
		return "Case [nom=" + nom + ", id=" + id + ", valeur=" + valeur + "]";
	}

}