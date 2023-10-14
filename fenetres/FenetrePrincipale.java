
package fenetres;

import java.util.ArrayList;
import java.util.Random;
import cases.Caseile;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import jeuarchipel.Joueur;
import jeuarchipel.Partie;
import jeuarchipel.PlateauJeu;
import jeudeplateau.Case;

public class FenetrePrincipale {

	private Stage stage;
	private StackPane root;
	private Label l_Bonus = new Label("0DT");
	private Label l_Message = new Label("");
	private ArrayList <Label> l_Joueurs = new ArrayList <Label>();
	private ArrayList <Label> l_ListeTerrains = new ArrayList <Label>();
	private ArrayList <Circle> l_Pions = new ArrayList <Circle>();
	private ArrayList<Label> l_Logs = new ArrayList<Label>();
	private ArrayList<Image> imageDes = new ArrayList<Image>();
	private ArrayList<ImageView> Des = new ArrayList<ImageView>();
	private Button tourSuivant = new Button("Lancer Dés");
	private Button newPartie = new Button("Nouvelle partie");
	public Random rand = new Random();
	public Color[] Couleurs = new Color[] {Color.RED, Color.BLUE, Color.ORANGE, Color.GREEN};
	private FenetreDemarrage fd = new FenetreDemarrage(this);
	private FenetreCarteChance fch = new FenetreCarteChance(this);
	private FenetreAcheterTerrain fat = new FenetreAcheterTerrain(this);
	private FenetreSortirPuits fpuits = new FenetreSortirPuits(this);
	private FenetreActionSurTerrain fact_ter = new FenetreActionSurTerrain(this);
	private Partie partie;


	public FenetrePrincipale(Stage primaryStage) {
		//Constructeur de la classe FenetrePrincipale

		this.stage = primaryStage;

		root = new StackPane();
		root.setOnMouseClicked(new EvtClicRoot());
		initRoot();

		Scene scene = new Scene(root,655,655);
		stage.setScene(scene);
		stage.setTitle("Archipel");
                 Image image = new Image("/images/j.png");
                stage.getIcons().add(image);

		fd.getStage().show();
	}

	//Initialise la StackPane root de la FenetrePrincipale avec les images, les labels et les boutons adéquates au jeu.
	 
	@SuppressWarnings("static-access")
	private void initRoot() {
		root.setStyle("-fx-background-image: url('file:/C:/Users/user/Documents/NetBeansProjects/Archipel_Jeu/src/images/f.png'); -fx-background-repeat: no-repeat");
                root.setAlignment(Pos.TOP_LEFT);

		for(int i=1; i<7; i++)
			imageDes.add(new Image("images/de"+i+".jpg"));

		Des.add(new ImageView());
		Des.add(new ImageView());
		Des.get(0).setTranslateX(280);
		Des.get(0).setTranslateY(360);
		Des.get(1).setTranslateX(337);
		Des.get(1).setTranslateY(360);
                Des.get(0).setFitHeight(50);
		Des.get(0).setFitWidth(50);
		Des.get(1).setFitHeight(50);
		Des.get(1).setFitWidth(50);
		root.getChildren().add(Des.get(0));
		root.getChildren().add(Des.get(1));

		l_Bonus.setTranslateX(3);
		l_Bonus.setTranslateY(68);
		root.getChildren().add(l_Bonus);

		l_Message.setFont(Font.font("Consolas", 14));
		l_Message.setTranslateX(150);
		l_Message.setTranslateY(480);
		l_Message.setMaxWidth(470);
		root.getChildren().add(l_Message);

		tourSuivant.setTranslateX(290);
		tourSuivant.setTranslateY(510);
		tourSuivant.setOnAction(new EvtTourSuivant());
		tourSuivant.setDefaultButton(true);
		if(!partie.PARTIE_AUTO)
			root.getChildren().add(tourSuivant);
	}

	
	public StackPane getRoot() {
		return root;
	}

	public Stage getStage() {
		return stage;
	}

	// Renvoie le Circle pion du joueur actif dans la Partie
	
	public Circle getPionActif() {
		return l_Pions.get(partie.getPM().getJoueurActifID());
	}

	
	public Partie getPartie() {
		return partie;
	}

	/**
	 * Méthode permettant de lancer une partie. Elle instanciera une nouvelle  Partie avec le bon nombre de joueurs
	 * et se chargera de placer les éléments graphiques tels que : les noms des joueurs, l'argent qu'ils possèdent,
	 * la liste de leurs terrains et les pions.
	 */
	public void setPartie(int nbJoueurs, ArrayList<String> nomsDesJoueurs) {

		partie = new Partie(nbJoueurs, nomsDesJoueurs, this);

		for(int i=0; i<nbJoueurs; i++) {
			Label l_nomJoueur = new Label(partie.getPM().getJoueur(i).getNom());
			l_nomJoueur.setTextFill(Couleurs[i]);
			
                        l_nomJoueur.setTranslateX(150+i*90);
			l_nomJoueur.setTranslateY(120);
			root.getChildren().add(l_nomJoueur);

			l_Joueurs.add(new Label(""+partie.getPM().getJoueur(i).getArgent()+"DT"));
			l_Joueurs.get(i).setTranslateX(150+i*90);
			l_Joueurs.get(i).setTranslateY(140);
			l_Joueurs.get(i).setFont(Font.font("Arial", 15));
			root.getChildren().add(l_Joueurs.get(i));

			l_ListeTerrains.add(new Label("\n"));
			l_ListeTerrains.get(i).setTranslateX(150+i*90);
			l_ListeTerrains.get(i).setTranslateY(160);
			l_ListeTerrains.get(i).setMaxWidth(110);
			root.getChildren().add(l_ListeTerrains.get(i));

			l_Pions.add(new Circle(15));
			l_Pions.get(i).setFill(Couleurs[i]);
			if(i<2) {
				l_Pions.get(i).setTranslateX(598 + i*15);
				l_Pions.get(i).setTranslateY(605);
			}
			else {
				l_Pions.get(i).setTranslateX(598 + (i-2)*15);
				l_Pions.get(i).setTranslateY(620);
			}
			root.getChildren().add(l_Pions.get(i));
		}

		refreshLabels(partie.getPM());
		partie.demarrerLaPartie();
	}

	//Affiche le message passé en paramètre dans la fenêtre.
	
	public void afficherMessage(String msg) {

		Platform.runLater(new Runnable() {
            @Override public void run() {

            	l_Message.setTextFill(Couleurs[getPartie().getPM().getJoueurActifID()]);
            	l_Message.setText(msg);
            }
        });
	}

	/**
	 * Cette méthode est appelé à chaque fois qu'un rafrichissement des labels est nécessaire. Elle va chercher les informations dans
	 * les champs de la partie pour mettre à jours les labels.
	 */
	public void refreshLabels(PlateauJeu pm) {

		Platform.runLater(new Runnable() {
            @Override public void run() {

        		l_Bonus.setText(""+pm.getCase(20).getPrix()+"DT");

        		for(int i=0; i<pm.getNbJoueurs(); i++) {
            		l_Joueurs.get(i).setText(""+pm.getJoueur(i).getArgent()+"DT "+(pm.getJoueur(i).getCarteSortiePuits()?"[S]":""));

            		String listeTerrains = pm.getJoueur(i).getListeStringTerrains();
            		listeTerrains = listeTerrains.replaceAll(",", "\n");
            		l_ListeTerrains.get(i).setText(listeTerrains);
        		}

            }
        });
	}

	// Affiche la fenêtre FenetreAcheterTerrain.
	
	public void afficherFenetreAchatTerrain() {

		Platform.runLater(new Runnable() {
            @Override public void run() {

            	fat.afficherFenetre();
            }
		});
	}

	//Affiche la fenêtre  FenetreSortirPuits.
	
	public void afficherFenetrePuits() {

		Platform.runLater(new Runnable() {
            @Override public void run() {

            	fpuits.afficherFenetre();
            }
		});
	}

	/**
	 * Affiche la fenêtre  FenetreCarteChance
	 * Les paramètres String titre et String description passés seront utilisés dans la fenêtre pour indiquer qu'elle carte on a tiré.
	 */
	public void afficherFenetreCarteChance(String titre, String description) {

		Platform.runLater(new Runnable() {
            @Override public void run() {

				fch.setTitre(titre);
				fch.setDescription(description);
				fch.afficherCarte();
            }
		});
	}


	//Méthode plaçant un marqueur désignant le propriétaire du terrain quand le joueur achète le terrain.
	
	public void setMarqueurProprietaire(Joueur joueur, Case caze) {

		Platform.runLater(new Runnable() {
            @Override public void run() {

            	caze.getMarqueur().setFill(getPionActif().getFill());

            	double x = 100, y = 100;
        		int pos = joueur.getPosition();

        		if(caze.getMarqueur().getPoints().isEmpty())
        			root.getChildren().add(caze.getMarqueur());

        		if(pos > 0 && pos < 10) {
        			if(caze.getMarqueur().getPoints().isEmpty())
        				caze.getMarqueur().getPoints().addAll(new Double[] {0.,0.,0.,12.,12.,12.});
        			x = 517 - ((pos-1) * 54);
        			y = 642;
        		}
        		else if(pos > 10 && pos < 20) {
        			if(caze.getMarqueur().getPoints().isEmpty())
        				caze.getMarqueur().getPoints().addAll(new Double[] {0.,12.,12.,12.,12.,0.});
        			x = 51;
        			y = 558 - ((pos-11) * 54);
        		}
        		else if(pos > 20 && pos < 30) {
        			if(caze.getMarqueur().getPoints().isEmpty())
        				caze.getMarqueur().getPoints().addAll(new Double[] {0.,0.,0.,12.,12.,12.});
        			x = 85 + ((pos-21) * 54);
        			y = 51;
        		}
        		else if(pos > 30 && pos < 40) {
        				if(caze.getMarqueur().getPoints().isEmpty())
        			caze.getMarqueur().getPoints().addAll(new Double[] {0.,0.,12.,0.,0.,12.});
        			x = 592;
        			y = 85 + ((pos-31) * 54);
        		}

        		if(pos == 15 || pos == 12)
        			x+=21;
        		else if(pos == 25 || pos == 28)
        			y+=21;
        		else if(pos == 35)
        			x-=21;

        		caze.getMarqueur().setTranslateX(x);
        		caze.getMarqueur().setTranslateY(y);
            }
        });
	}
	
	public void setArme(Caseile caze){

		Platform.runLater(new Runnable() {
            @Override public void run() {

            	Polygon arme = caze.armes.get(caze.getnbArme());

            	arme.setFill(Color.BLACK);

            	int x = -50;
            	int y = -50;
            	int pos = caze.getId();

            	if(caze.getMarqueur().getPoints().isEmpty())
            		root.getChildren().add(arme);

            	boolean hotel = (caze.getnbArme() == 5);
            	if(!hotel)
            		arme.getPoints().addAll(new Double[] {0., 11., 0., 3., 5., 0., 10., 3., 10., 11.});
            	else if((pos > 0 && pos < 10) || (pos > 20 && pos < 30))
            		arme.getPoints().addAll(new Double[] {0., 0., 0., 11., 46., 11., 46., 0.});
            	else
            		arme.getPoints().addAll(new Double[] {0., 0., 0., 50., 10., 50., 10., 0.});


            	if(pos > 0 && pos < 10) {
        			x = 520 - ((pos-1) * 54) + (hotel?0:(caze.getnbArme()-1)*12);
        			y = 577;
        		}
        		else if(pos > 10 && pos < 20) {
        			x = 69;
        			y = 519 - ((pos-11) * 54) + (hotel?0:(caze.getnbArme()-1)*13);
        		}
        		else if(pos > 20 && pos < 30) {
        			x = 87 + ((pos-21) * 54)  + (hotel?0:(caze.getnbArme()-1)*12);
        			y = 69;
        		}
        		else if(pos > 30 && pos < 40) {
        			x = 576;
        			y = 87 + ((pos-31) * 54) + (hotel?0:(caze.getnbArme()-1)*13);
        		}

            	arme.setTranslateX(x);
            	arme.setTranslateY(y);

            }
		});
	}

	// Afficher les images des dés dans la FenetrePrincipale.
	
	public void afficherDes(PlateauJeu pm) {
		Platform.runLater(new Runnable() {
            @Override public void run() {

            	effacerDes();
				Des.get(0).setImage(imageDes.get(pm.des.getDe1()-1));
				Des.get(1).setImage(imageDes.get(pm.des.getDe2()-1));

            }
       });
	}

	public void effacerDes() {
		Des.get(0).setImage(null);
		Des.get(1).setImage(null);
	}

	//Déplace le pion du joueur actif en fonction de la position sur le plateau de joueur passé en paramètre.
	 
	public void deplacerPion(Joueur joueur){

		double x, y;
		int pos = joueur.getPosition();
		TranslateTransition tt = new TranslateTransition(Duration.millis(500), getPionActif());

		if(joueur.getEstBanqueroute()) {
			x = 103;
			y = 538;
		}
		else if(pos == 0) {
			x = 604;
			y = 604;
		}
		else if(pos == 10) {
			if(joueur.getestDansPuits()) {
				x = 47;
				y = 598;
			}
			else if(joueur.getID() == 0 || joueur.getID() == 1){
				x = 16;
				y = 644;
			}
			else  {
				x = 48;
				y = 628;
			}
		}
		else if(pos == 20) {
			x = 30;
			y = 34;
		}
		else if(pos == 30) {
			x = 604;
			y = 34;
		}
		else if(pos > 0 && pos < 10) {
			x = 537 - ((pos-1) * 54);
			y = 620;
		}
		else if(pos > 10 && pos < 20) {
			x = 30;
			y = 538 - ((pos-11) * 54);
		}
		else if(pos > 20 && pos < 30) {
			x = 104 + ((pos-21) * 54);
			y = 30;
		}
		else if(pos > 30 && pos < 40) {
			x = 612;
			y = 106 + ((pos-31) * 54);
		}
		else {
			x = -50;
			y = -50;
		}

		switch(joueur.getID()) {
		case 0: x-=8; y-=8; break;
		case 1: x+=8; y-=8; break;
		case 2: x-=8; y+=8; break;
		case 3: x+=8; y+=8; break;
		default: break;
		}

	    tt.setToX(x);
	    tt.setToY(y);
	    tt.play();
	}

	//Affiche le vainqueur de la partie. Ajoute également le bouton newPartie à la fenêtre princiaple.
	 
	public void afficherVainqueur(PlateauJeu pm) {

		Platform.runLater(new Runnable() {
            @Override public void run() {

            	Label vainqueur = new Label("Le vainqueur est "+pm.estVainqueur().getNom()+" !");
            	vainqueur.setTextFill(l_Pions.get(pm.estVainqueur().getID()).getFill());
            	vainqueur.setFont(Font.font("Arial", 26));
            	vainqueur.setTranslateX(145);
            	vainqueur.setTranslateY(525);

        		root.getChildren().add(vainqueur);

        		root.getChildren().remove(tourSuivant);

        		newPartie.setTranslateX(463);
        		newPartie.setTranslateY(533);
        		newPartie.setOnAction(new EvtNewPartie());
        		root.getChildren().add(newPartie);

            }
		});
	}

	// Réinitialise les éléments graphiques de la fenêtre tels que les labels, les pions et les logs.
	 
	public void resetElementsGraphiques() {
		l_Bonus.setText("0DT");
		l_Joueurs.clear();
		l_ListeTerrains.clear();
		l_Pions.clear();
		l_Logs.clear();
	}

	// Évènement lorque l'on appuie sur le bouton tourSuivant : la partie reprend.
	 
	private class EvtTourSuivant implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			partie.reprendrePartie();
		}
	}
	/*
	 * Évènement lorque l'on appuie sur le bouton newPartie : la fenètre principale se ferme, les éléments graphiques sont
	 * réinitialisés, la StackPane root est redéfinie et on réaffiche la fenêtre de démarrage.
	 */
	private class EvtNewPartie implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			stage.close();
			resetElementsGraphiques();
			root = new StackPane();
			initRoot();
			Scene scene = new Scene(root,655,655);
			stage.setScene(scene);
			fd.getStage().show();
		}
	}
	/**
	 * Évènement lorqu'on clic dans la StackPane root :
	 * en fonction des coordonnées du pointeurs, on peux obtenir la position de la case visée. 
	 * Si cette position est une position valide (càd que l'on clic sur une Caseile qui appartient au joueur dont
	 * c'est le tour), alors on peut déclencher l'affichage d'une  FenetreAcheterTerrain avec en paramètre la position cliquée.
	
	 */
	private class EvtClicRoot implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {

			int pos = -1;

			if(event.getSceneX() < 84) {
				if(event.getSceneY() < 84)
					pos = 20;
				else if(event.getSceneY() < 570)
					pos = 19 - (int)((event.getSceneY()-84)/54);
				else
					pos = 10;
			}
			else if(event.getSceneX() < 570) {
				if(event.getSceneY() < 84)
					pos = 21 + (int)((event.getSceneX()-84)/54);
				else if(event.getSceneY() >= 570)
					pos = 9 - (int)((event.getSceneX()-84)/54);
			}
			else {
				if(event.getSceneY() < 84)
					pos = 30;
				else if(event.getSceneY() < 570)
					pos = 31 + (int)((event.getSceneY()-84)/54);
				else
					pos = 0;
			}

			ArrayList<Integer> CasesInterdites = new ArrayList<Integer>();
			for(int i=0; i<40; i++) {
				CasesInterdites.add(i);
			}
			CasesInterdites.add(-1);
			for(Case t:getPartie().getPM().getJoueurActif().getListeTerrains()) {
				CasesInterdites.remove((Object)(t.getId()));
			}

			if(!CasesInterdites.contains(pos)) {
				fact_ter.afficherFenetre(pos);
			}
		}
	}

}