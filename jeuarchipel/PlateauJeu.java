package jeuarchipel;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import cartes.*;
import cases.CaseChance;
import cases.CaseDepart;
import cases.CaseTresor;
import cases.CasePirate;
import cases.CaseBonus;
import cases.CasePuits;
import cases.Caseile;
import jeudeplateau.Carte;
import jeudeplateau.Case;

public class PlateauJeu extends jeudeplateau.Plateau {

	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

	private ArrayList<Carte> chance = new ArrayList<Carte>();


	public PlateauJeu(int nombreDeJoueurs) {
		super(nombreDeJoueurs, 40);


		/* INITIALISATION DES JOUEURS */
		for(int i = 0; i < this.getNbJoueurs(); i++) {
			this.joueurs.add(new Joueur("Joueur"+(i+1), i, 1000));
		}

		/* INITIALISATION DES CASES*/
		setCase(0, new CaseDepart());
		setCase(2, new CaseChance());
		setCase(4, new CasePirate("Alvida", 200));
		setCase(5, new CaseTresor("Tresor des Templieres"));
		setCase(7, new CaseChance());
		setCase(10, new CasePuits());
		setCase(12, new CasePirate("Barbaros",150));
		setCase(15, new CaseTresor("Or de Toulouse"));
		setCase(17, new CaseChance());
		setCase(20, new CaseBonus());
		setCase(22, new CaseChance());
		setCase(25, new CaseTresor("Tresor de lima"));
		setCase(28, new CasePirate("Hecto",250));
		setCase(30, new CasePuits());
		setCase(33, new CaseChance());
		setCase(35, new CaseTresor("Tresor de cathares"));
		setCase(36, new CaseChance());
		setCase(38, new CasePirate("Jack-sparrow", 100));

		/* INITIALISATION DES TERRAINS */
		setCase(1, new Caseile("Madura", 60, new ArrayList<Integer>(Arrays.asList(2, 10, 30, 90, 160, 250)), 50, 0, "brun"));
		setCase(3, new Caseile("Santorin", 60, new ArrayList<Integer>(Arrays.asList(4, 20, 60, 180, 320, 450)), 50, 0, "brun"));

		setCase(6, new Caseile("caraibes", 100, new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise"));
		setCase(8, new Caseile("Tahiti", 100, new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise"));
		setCase(9, new Caseile("Bora Bora", 120, new ArrayList<Integer>(Arrays.asList(8, 40, 100, 300, 450, 600)), 50, 0, "turquoise"));

		setCase(11, new Caseile("Sicile", 140, new ArrayList<Integer>(Arrays.asList(10, 50, 150, 450, 625, 750)), 100, 0, "mauve"));
		setCase(13, new Caseile("Malta", 140, new ArrayList<Integer>(Arrays.asList(10, 50, 150, 450, 625, 750)), 100, 0, "mauve"));
		setCase(14, new Caseile("capri", 160, new ArrayList<Integer>(Arrays.asList(12, 60, 180, 500, 700, 900)), 100, 0, "mauve"));

		setCase(16, new Caseile("Hawai", 180, new ArrayList<Integer>(Arrays.asList(14, 70, 200, 550, 750, 950)), 100, 0, "orange"));
		setCase(18, new Caseile("Victoria", 180, new ArrayList<Integer>(Arrays.asList(14, 70, 200, 550, 750, 950)), 100, 0, "orange"));
		setCase(19, new Caseile("Roto", 200, new ArrayList<Integer>(Arrays.asList(16, 80, 220, 600, 800, 1000)), 100, 0, "orange"));

		setCase(21, new Caseile("Bali", 220, new ArrayList<Integer>(Arrays.asList(18, 90, 250, 700, 875, 1050)), 150, 0, "rouge"));
		setCase(23, new Caseile("Negros", 220, new ArrayList<Integer>(Arrays.asList(18, 90, 250, 700, 875, 1050)), 150, 0, "rouge"));
		setCase(24, new Caseile("Maldive", 240, new ArrayList<Integer>(Arrays.asList(20, 100, 300, 750, 925, 1100)), 150, 0, "rouge"));

		setCase(26, new Caseile("Zanzibar", 260, new ArrayList<Integer>(Arrays.asList(22, 110, 330, 800, 975, 1150)), 150, 0, "jaune"));
		setCase(27, new Caseile("Zembra", 260, new ArrayList<Integer>(Arrays.asList(22, 110, 330, 800, 975, 1150)), 150, 0, "jaune"));
		setCase(29, new Caseile("Maurice", 280, new ArrayList<Integer>(Arrays.asList(24, 120, 360, 850, 1025, 1200)), 150, 0, "jaune"));

		setCase(31, new Caseile("Sychelle", 300, new ArrayList<Integer>(Arrays.asList(26, 130, 390, 900, 1100, 1275)), 200, 0, "vert"));
		setCase(32, new Caseile("Porto Rico", 300, new ArrayList<Integer>(Arrays.asList(26, 130, 390, 900, 1100, 1275)), 200, 0, "vert"));
		setCase(34, new Caseile("Calero", 320, new ArrayList<Integer>(Arrays.asList(28, 150, 450, 1000, 1200, 1400)), 200, 0, "vert"));

		setCase(37, new Caseile("Mykonos", 350, new ArrayList<Integer>(Arrays.asList(35, 175, 500, 1100, 1300, 1500)), 200, 0, "bleu"));
		setCase(39, new Caseile("Ibiza", 400, new ArrayList<Integer>(Arrays.asList(50, 200, 600, 1400, 1700, 2000)), 200, 0, "bleu"));

  //*************************Initialisation des cartes chance *****************
		
		chance.add(new CarteDeplacement("Case Départ", "Avancez jusqu'à la case départ. \n(Recevez 200DT)", 0, false));
		chance.add(new CarteDeplacement("Ibiza", "Aller à l'ile IBIZA.", 39, false));
		chance.add(new CarteDeplacement("Maldive", "Aller à l'ile de maldive. \nSi vous passez par la case départ, recevez 200DT.", 24, false));
		chance.add(new CarteDeplacement("Sicile", "Avancez à l'ile de sicile. \nSi vous passez par la case départ, recevez 200DT.", 11, false));
		chance.add(new CarteDeplacement("Or de Toulouse", "Avancez jusqu'au trésor de toulouse. \nSi vous passez par la case départ, recevez 200DT.", 15, false));
		chance.add(new CarteDeplacement("Reculez", "Reculez de 3 cases.", -3, true));
		chance.add(new CarteDeplacement("Nv Depart", "Le joueur déménage et prend un \n nouveau départ ", 0, false));
		
		
		chance.add(new CartePayerArgent("Attention Arnaqeur !", "Payez 150DT aux arnaqeurs !", 150));
		chance.add(new CartePayerArgent("Impots et taxe ", "Payez 100DT des taxes et des impots", 100));
		chance.add(new CartePayerArgent("Emplacement interdit", "Emplacement interdit ! : payez 50DT.", 50));
                chance.add(new CartePayerArgent("Journée de taxe", "vous devez payer à tous le monde 50DT.", 50));

		chance.add(new CarteRecevoirArgent("Remboursement", "Les impôts vous remboursent 20DT.", 20));
		chance.add(new CarteRecevoirArgent("Lampe Merveilleuse", "une lampe Merveilleuse de Aladain , chaque joueur doit vous payer 10Dt.", 10));
		chance.add(new CarteRecevoirArgent("Trésor", "Un trésor complémentaire ! Félicitations. \nRecevez 25DT.", 25));
		chance.add(new CarteRecevoirArgent("Panier de fruits", "Une panier de fruit vous apporte 50DT.", 50));
		chance.add(new CarteRecevoirArgent("Bateau perdu", "Vous avez trouvez un bateau perdu ! Plein d'argent \nRecevez 100DT.", 100));
		chance.add(new CarteRecevoirArgent("Prix de Meilleur joueur", "Vous avez gagné le prix de meilleur joueur ! Félicitations . \nRecevez 200DT.", 200));

		chance.add(new CarteDeplacement("Puits", "Allez en puits. \nAvancez tout droit en puits. \n ne passez pas par la case départ, ne recevez pas 200DT.", 10, false));
		chance.add(new CarteSortirPuits("Sortie", "Avec cette carte vous pouvez s'echapper du puits. \n(Cette carte doit être conservée)"));

		Collections.shuffle(chance); //Mélange des cartes
	}

	/* PARTIE JOUEUR */

	// Permet de déplacer un joueur d'un certain nombre de cases
	
	public void deplacerJoueur(Joueur joueur, int nombreDeCases) {
		int pos;

		if((joueur.getPosition() + nombreDeCases) >= getNbCases()) {
			pos = (joueur.getPosition() + nombreDeCases) % getNbCases();
			System.out.println(" > " + joueur.getNom() + " passe par la case départ et gagne 200DT !");
			joueur.ajouterArgent(200);
		}
		else
			pos = joueur.getPosition() + nombreDeCases;

		if(!joueur.getEstBanqueroute()) {
			joueur.setPosition(pos);
		}
	}

	//Renvoie le joueur qui doit jouer son tour
	
	public Joueur getJoueurActif() {
		return this.joueurs.get(getJoueurActifID());
	}

	//Permet de changer de joueur en fonction de l'indice i

	public Joueur getJoueur(int i) {
		return this.joueurs.get(i);
	}

	// Renvoie le joueur vainqueur
	 
	@Override
	public Joueur estVainqueur() {
		int res = 0;
		for(int i=0; i<joueurs.size(); i++) {
			if(getJoueur(i).getArgent() > getJoueur(res).getArgent())
				res = i;
		}
		return getJoueur(res);
	}

	/* PARTIE CASE */

	// Renvoie la case où  le joueur est actif
	
	public Case getCaseActive() {
		return getCase(getJoueurActif().getPosition());
	}

	/* PARTIE JEU */

	// Met fin à la partie
	
	@Override
	public boolean finPartie() {
		int nbJoueursEnJeu = 0;
		for(Joueur j:joueurs) {
			if(!j.getEstBanqueroute()) nbJoueursEnJeu++;
		}
		return (nbJoueursEnJeu <= 1);
	}

	/*PARTIE CARTE */

	// Renvoie la liste des cartes chances
	
	public Carte tirerCarteChance() {
		Carte c = chance.remove(0);
		if(!c.getNom().equals("Sortie"))
			chance.add(c);
		return c;
	}


	// Permet l'ajout de la carte Sortie Puits lorsqu'un joueur qui la possède l'utilise
	 
	public void remettreCarteSortiePuitsDansPaquet() {

		boolean carteDansPaquetChance = false;
		for(Carte c:chance) {
			if(c.getNom().equals("Sortie"))
				carteDansPaquetChance = true;  
		}

		if(carteDansPaquetChance)
			chance.add(new CarteSortirPuits("Sortie", "Vous êtes libéré de puits. \n(Cette carte doit être conservée)"));
	}


	/* TOSTRING */

	@Override

	public String toString() {
		return "PlateauJeu [toString()=" + super.getCase(1) + "]";
	}

}

