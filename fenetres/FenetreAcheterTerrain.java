
package fenetres;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

// Fenêtre à afficher lorqu'on atteint  une  Caseile , tresor sans propriétaire.
 
public class FenetreAcheterTerrain {

	private FenetrePrincipale fp;
	private Stage stage;
	private HBox root;
	private Label l_Texte;
	private Button b_Oui;
	private Button b_Non;

	//Unique constructeur de la classe FenetreAcheterTerrain, prenant en paramètre la FenetrePrincipale fp.
	
	public FenetreAcheterTerrain(FenetrePrincipale fp) {

		this.fp = fp;

		this.stage = new Stage();
		this.stage.setTitle("Voulez-vous acheter ?");
		this.stage.initOwner(fp.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);

		stage.setOnHiding(new EvtQuitter());
                 Image image = new Image("/images/j.png");
                stage.getIcons().add(image);
	}

	//Initialise la HBox root de la FenetreAcheterTerrain avec une image, un label posant une question et des boutons Oui/Non.
	 
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(10);
		root.setStyle("-fx-background-color: #CDE6D0; ");

		Image i_terrain;
switch(fp.getPartie().getPM().getCaseActive().getNom()) {
		case "Tresor des Templieres": i_terrain = new Image("images/tresor.png"); break;
		case "Or de Toulouse": i_terrain = new Image("images/tresor.png"); break;
		case "Tresor de lima": i_terrain = new Image("images/tresor.png"); break;
		case "Tresor de cathares": i_terrain = new Image("images/tresor.png"); break;
		default: {
			String couleur = fp.getPartie().getPM().getCaseActive().getCouleur();
		  i_terrain = new Image("images/a_"+couleur+".png");
		 
		}; break;

		
		}

		ImageView iv_terrain = new ImageView(i_terrain);
	iv_terrain.setFitWidth(150);
	iv_terrain.setFitHeight(150);
	
		root.getChildren().add(iv_terrain);

		VBox aside = new VBox();
		aside.setSpacing(15);
		root.getChildren().add(aside);

		l_Texte = new Label("Voulez vous acheter " + fp.getPartie().getPM().getCaseActive().getNom() + " pour " + fp.getPartie().getPM().getCaseActive().getPrix() + "DT ?");
		aside.getChildren().add(l_Texte);

		HBox buttons_horiz = new HBox();
		buttons_horiz.setSpacing(10);

		b_Oui = new Button("Oui");
		b_Oui.setOnAction(new EvtOui());
		buttons_horiz.getChildren().add(b_Oui);

		b_Non = new Button("Non");
		b_Non.setOnAction(new EvtNon());
		buttons_horiz.getChildren().add(b_Non);

		aside.getChildren().add(buttons_horiz);

		root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	           if(b_Oui.isFocused())
	        	   b_Oui.fire();
	           else
	        	   b_Non.fire();
	           ev.consume(); 
	        }
	    });
	}

	//Affiche la fenêtre en réinitialisant la HBox root à chaque appel.
	
	public void afficherFenetre() {
		root = new HBox();
		initRoot();

		Scene scene = new Scene(root,450,170);
		stage.setScene(scene);
		stage.show();
	}

	public Stage getStage() {
		return stage;
	}

	// Évènement qui ferme la fenêtre et change la réponse à vrai.
	 
	private class EvtOui implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			fp.getPartie().getPM().getCaseActive().setReponseQuestion(true);
			stage.close();
			event.consume();
		}
	}

	// Évènement qui ferme la fenêtre (la réponse n'est pas changée et reste à faux).
	
	private class EvtNon implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			stage.close();
			event.consume();
		}
	}

	// Évènement qui reprend la partie quand la fenêtre se ferme.
	
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			fp.getPartie().reprendrePartie();
			event.consume();
		}
	}
}