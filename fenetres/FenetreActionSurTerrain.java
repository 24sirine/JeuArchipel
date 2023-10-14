
package fenetres;

import cases.Caseile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jeudeplateau.Case;

// Fenêtre à afficher lorqu'on est sur une caseile 
public class FenetreActionSurTerrain {

	private FenetrePrincipale fp;
	private Stage stage;
	private HBox root;
	private int position;
	private Label l_Texte;
	private Label l_TexteErreur;
	private Button b_Poser;
	

	public FenetreActionSurTerrain(FenetrePrincipale fp) {

		this.fp = fp;

		this.stage = new Stage();
		this.stage.setTitle("Action sur le terrain :");
		this.stage.initOwner(fp.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);
 Image image = new Image("/images/j.png");
                stage.getIcons().add(image);
		stage.setOnHiding(new EvtQuitter());
	}

	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(10);
		root.setStyle("-fx-background-color: #CDE6D0; ");

		Image i_terrain;

		switch(position) {
		case 5: i_terrain = new Image("images/tresor.png"); break;
		case 15: i_terrain = new Image("images/tresor.png"); break;
		case 25: i_terrain = new Image("images/tresor.png"); break;
		case 35: i_terrain = new Image("images/tresor.png"); break;
		case 28: i_terrain = new Image("images/pirate2.png"); break;
		case 12: i_terrain = new Image("images/pirate1.png"); break;
		default: {
			String couleur = fp.getPartie().getPM().getCase(position).getCouleur();
			i_terrain = new Image("images/a_"+couleur+".png");
		}; break;
		}

		ImageView iv_terrain = new ImageView(i_terrain);
		root.getChildren().add(iv_terrain);

		VBox aside = new VBox();
		aside.setSpacing(15);
		root.getChildren().add(aside);

		l_Texte = new Label("Que voulez-vous faire sur le terrain "+ fp.getPartie().getPM().getCase(position).getNom() +" ?");
		aside.getChildren().add(l_Texte);

		HBox buttons_horiz = new HBox();
		buttons_horiz.setSpacing(10);

		b_Poser = new Button("Ajouter un arme ("+fp.getPartie().getPM().getCase(position).getprixArme()+"DT)");
		b_Poser.setOnAction(new EvtPoser());
		if(position != 5 && position != 15 && position != 25 && position != 35 && position != 12 && position != 28)
			buttons_horiz.getChildren().add(b_Poser);

		

		aside.getChildren().add(buttons_horiz);

		l_TexteErreur = new Label("");
		l_TexteErreur.setTextFill(Color.RED);
		aside.getChildren().add(l_TexteErreur);

		root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	           if(b_Poser.isFocused())
	        	   b_Poser.fire();
	         
	           ev.consume(); 
	        }
	    });
	}

	public void afficherFenetre(int pos) {
		position = pos;
		root = new HBox();
		initRoot();

		Scene scene = new Scene(root,470,130);
		stage.setScene(scene);
		stage.show();
	}
	public Stage getStage() {
		return stage;
	}

	// Évènement qui pose un arme dans la fenêtre principale et l'ajoute dans la Case.
	
	private class EvtPoser implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			Caseile c = (Caseile) fp.getPartie().getPM().getCase(position);
			if(c.getpeutMettreArme()) {
				c.ajouterarme(fp);
				fp.setArme(c);
				stage.close();
			}
			else l_TexteErreur.setText("Impossible de placer un arme ici.");
			event.consume();
		}
	}

	
	// Évènement qui ferme la fenêtre et rafraichit les labels.
	 
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			fp.refreshLabels(fp.getPartie().getPM());
			event.consume();
		}
	}
}