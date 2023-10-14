package jeuarchipel;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import jeudeplateau.Case;

//Définit un joueur et toutes ses données dans le jeu Archipel
public class Joueur  {
	private String nom;
	private int id;
	private int position = 0;
	private int argent = 1000;
	private boolean estBanqueroute = false;
	private boolean estDansPuits = false;
	private int toursEnPuits = 1;
	private boolean possedeCarteSortiePuits = false;
	private int nombreTresorPossedees = 0;
	private ArrayList<Case> terrains = new ArrayList<Case>();
	private ArrayList<String> couleurs = new ArrayList<String>();

	/* CONSTRUCTEUR */

	public Joueur(String nom, int id, int argent) {
		setNom(nom);
		setID(id);
		this.argent = argent;
	}
	public String getNom() {
		return this.nom;
	}

	//Définit le nom d'un joueur (s'il est nul, une exception est levée)
	 
	public void setNom(String nom) {
		if(nom == null || nom.isEmpty())
			throw new IllegalArgumentException("Le nom ne peux pas être vide ou null");
		this.nom = nom;
	}
	// Renvoie l'identifiant du joueur
	public int getID() {
		return this.id;
	}

	// Définit l'identifiant d'un joueur
	public void setID(int id) {
		this.id = id;
	}

	// Renvoie la position d'un joueur
	public int getPosition() {
		return this.position;
	}

	// Définit la position d'un joueur
	
	public void setPosition(int pos) {
		this.position = pos;
	}




	/* PARTIE PUITS  */

	//Renvoie le nombre de tours en puits
	
	public int gettoursEnPuits() {
		return toursEnPuits;
	}

	/**
	 * Met à jour le nombre de tour en puits
	 * Le nouveau nombre de tour en puits
	 */
	public void settoursEnPuits(int toursEnPuits) {
		this.toursEnPuits = toursEnPuits;
	}

	//Renvoie si le joueur est en puits ou non
	
	public boolean getestDansPuits(){
		return this.estDansPuits;
	}

	// Met à jour si le joueur est en puits ou non
	
	public void setestDansPuits(boolean p){
		this.estDansPuits = p;
	}

	//Renvoie si le joueur possède la carte Sortie de Puits ou non
	 
	public boolean getCarteSortiePuits() {
		return possedeCarteSortiePuits;
	}

	//Met à jour si le joueur possède une carte de sortie de puits
	 
	public void setCarteSortiePuits(boolean b) {
		possedeCarteSortiePuits = b;
	}

	/*  PARTIE ILES */

		// TRESOR ET PIRATES

	//Renvoie le nombre de trésors qu'un joueur possède
	 
	public int getNbTresor() {
		return this.nombreTresorPossedees;
	}

	//Met à jour le nombre de trésors qu'un joueur possède
	 
	public void setNbTresor(int nb) {
		this.nombreTresorPossedees = nb;
	}



		// TERRAINS

	// Ajoute "terrain" à la liste des terrains
	
	public void ajouterTerrain(Case terrain) {
		this.terrains.add(terrain);
	}

	//Renvoie la liste "écrite" des terrains qu'un joueur possède
	
	public String getListeStringTerrains() {
		String s = "";
		for(Case t:this.terrains) {
			s+=(t.getNom()+",");
		}
		return s;
	}

	//Renvoie une liste de terrain que possède un joueur
	public ArrayList<Case> getListeTerrains(){
		return this.terrains;
	}

	// COULEURS		

	//Renvoie une liste correspondant aux couleurs que possède un joueur
	public ArrayList<String> getListeCouleur(){

		couleurs.clear();

		int 	        brun = 0,
				turquoise = 0,
				mauve = 0,
				orange = 0,
				rouge = 0,
				jaune = 0,
				vert = 0,
				bleu = 0;
//Parcourt de la liste des terrains q'un joueurs posséde et on calcule pour chaque couleur le nb aquérit
		for(Case t:this.getListeTerrains()) {

			if(t.getCouleur() == "brun")
				brun += 1;
			if(t.getCouleur() == "turquoise")
				turquoise += 1;
			if(t.getCouleur() == "mauve")
				mauve += 1;
			if(t.getCouleur() == "orange")
				orange += 1;
			if(t.getCouleur() == "rouge")
				rouge += 1;
			if(t.getCouleur() == "jaune")
				jaune += 1;
			if(t.getCouleur() == "vert")
				vert += 1;
			if(t.getCouleur() == "bleu")
				bleu += 1;
		}
//si on atteint le nombre maximale (càd tous les terrains de cette couleur) , on ajoute ce couleur çàd la liste des couleurs q'un joueur aquiert
		if(brun == 2) 		couleurs.add("brun");

		if(turquoise == 3) 	couleurs.add("turquoise");

		if(mauve == 3) 		couleurs.add("mauve");

		if(orange == 3) 	couleurs.add("orange");

		if(rouge == 3) 		couleurs.add("rouge");

		if(jaune == 3) 		couleurs.add("jaune");

		if(vert == 3) 		couleurs.add("vert");

		if(bleu == 2) 		couleurs.add("bleu");

		return this.couleurs;
	}

	/* PARTIE ARGENT */

	//Renvoie l'argent d'un joueur
	
	public int getArgent() {
		return this.argent;
	}

	//Met à jour l'argent d'un joueur en lui ajoutant un montant
	 
	public void ajouterArgent(int montant) {
		this.argent+=montant;
	}

	//Met à jour l'argent d'un joueur en lui retirant un montant
	 
	public void retirerArgent(int montant) {
		this.argent = this.argent - montant;
		if(this.argent <= 0) {
			this.argent = 0;
			this.setEstBanqueroute(true);//Càd le joueur a perdu 
		}
	}

	//Renvoie si un joueur est en banqueroute ou non (perdant ou pas )
	
	public boolean getEstBanqueroute() {
		return this.estBanqueroute;
	}

	//Met à jour si le joueur est banqueroute ou pas
	
	public void setEstBanqueroute(boolean banqueroute) {
		this.estBanqueroute = banqueroute;
		clearMarqueurs(); // appel de la méthode clearMarqueur qui supprime les marquers de possession du terrain
		this.terrains.clear();
	}

	// Supprime le marqueur de possession d'un terrain
	public void clearMarqueurs() {
//parcout de la liste des terrains que le joueur posséde
		for(Case t:getListeTerrains()){
			t.setProprietaire(null);
//classe platform qui posséde la méthode runlater 
			Platform.runLater(new Runnable() {
	            @Override public void run() {
                        //cette instruction se fait en paralléle lors du déroulement du jeu , de ce fait , on peut à la fois changer la couleur du marqueur et on joue 
	            	t.getMarqueur().setFill(Color.TRANSPARENT);
	            }
			});
		}
	}

	@Override
	public String toString() {
		return "JoueurArchipel [nom=" + nom + ", id=" + id + ", position=" + position + ", argent=" + argent + ", estBanqueroute=" + estBanqueroute + ", estDansPuits=" + estDansPuits
				+ ", toursEnPuits=" + toursEnPuits + ", possedeCarteSortiePuits=" + possedeCarteSortiePuits
				+ ", nombreTresorPossedees=" + nombreTresorPossedees +  ", \nterrains=[" + getListeStringTerrains() + "], \ncouleurs=" + getListeCouleur() + "]";
	}
}