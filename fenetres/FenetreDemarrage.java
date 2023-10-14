
package fenetres;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FenetreDemarrage {

	private FenetrePrincipale fp;
	private Stage stage;
	private HBox root;
	private VBox vb;
	private Label l_NbJoueurs;private Label l_NbJoueurs2;
	private ArrayList<TextField> listeJoueurs = new ArrayList<TextField>();
	private Button b_Valider;
	private int choix = 0;

	
	public FenetreDemarrage(FenetrePrincipale fp) {

		this.fp = fp;

		this.stage = new Stage();

		this.stage.setTitle("Archipel");
		this.stage.initOwner(fp.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);
                Image image = new Image("/images/j.png");
                stage.getIcons().add(image);
	

		root = new HBox();

		initRoot();
	    Scene scene = new Scene(root, 884.0,495.0);
	
		stage.setScene(scene);

		stage.setOnHiding(new EvtQuitter());
	}

	//Initialise la VBox root de la FenetreDemarrage avec une  ListView de nombres de joueurs et un bouton de validation.
	 
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(5);
		root.setStyle("-fx-background-color: #3895D3");
       

		l_NbJoueurs = new Label("Noms des joueurs (2 minimum) :");
		l_NbJoueurs2 = new Label("                                                        © Sirine Bourbiaa & Yassmine Loussayef ");


		VBox vBox1 =new VBox();
		vBox1.setPrefHeight(500.0);
		vBox1.setPrefWidth(500.);
		vBox1.setBlendMode(BlendMode.SRC_OVER);
		vBox1.setDepthTest(DepthTest.INHERIT);
		vBox1.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		vBox1.setCenterShape(true);
		l_NbJoueurs2.setCenterShape(true);

		ImageView imageView1=new ImageView();
		imageView1.setFitHeight(500.0);
		imageView1.setFitWidth(500.0);
		imageView1.setPickOnBounds(true);
		imageView1.setPreserveRatio(true);

		imageView1.setImage(new Image("/images/xx.png"));


		vBox1.getChildren().add(imageView1);



		vBox1.getChildren().add(l_NbJoueurs2);
	


           
		root.getChildren().add(vBox1);
		
		VBox vbin = new VBox();
		
        vb = new VBox();
        VBox vbimage = new VBox(); 
        ImageView imgviewVB = new ImageView();
		vbimage.getChildren().add(imgviewVB);
		vbimage.setPadding(new Insets(0,0,0,20) );
		imgviewVB.setImage(new Image("/images/pirateimgresized.png"));
		vb.getChildren().add(vbimage);
        vbin.setPadding(new Insets(50,0,0,100) );
        vb.setSpacing(15);
		vb.getChildren().add(l_NbJoueurs);
		for(int i=0; i<4; i++) {
			listeJoueurs.add(new TextField(i<2?"Joueur"+(i+1):""));
			listeJoueurs.get(i).setPromptText("Nom du joueur "+(i+1));
			vb.getChildren().add(listeJoueurs.get(i));
		}
		VBox vbbutton = new VBox(); 
		
		
		b_Valider = new Button("Play");
		b_Valider.setOnAction(new EvtValider());
		b_Valider.setDefaultButton(true);
		b_Valider.setOnAction(new EvtValider());
		b_Valider.setStyle("-fx-background-color: #1261A0; -fx-text-fill: white; -fx-alignment: CENTER;");
		b_Valider.setAlignment(Pos.BASELINE_CENTER);
		vb.getChildren().add(vbbutton);
		vbbutton.setAlignment(Pos.CENTER);
		vbin.getChildren().add(vb);
		vbbutton.getChildren().add(b_Valider);
        root.getChildren().add(vbin);    


		
	}
	public Stage getStage() {
		return stage;
	}

	// Évènement qui récupère dans la ListView le nombre de joueurs désiré et lance une partie avec ce nombre.
	
	private class EvtValider implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			ArrayList<String> champs = new ArrayList<String>();
			for(int i=0; i<4; i++) {
				if(listeJoueurs.get(i).getText() != null && !listeJoueurs.get(i).getText().isEmpty())
					champs.add(listeJoueurs.get(i).getText());
			}
			if(champs.size()>=2) {
				choix = 1;
				fp.setPartie(champs.size(), champs);
				fp.getStage().show();
				stage.close();
			}
			event.consume();
		}
	}

	// Évènement qui ferme la fenêtre de démarrage et arrête le programme si l'on a pas cliqué sur le bouton Valider.
	 
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			if(choix == 0)
				System.exit(0);
			event.consume();
		}
	}
}