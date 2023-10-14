package application;
import fenetres.FenetrePrincipale;
import javafx.application.Application;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;



public class Main extends Application {

   

MediaPlayer mediaPlayer;

	@Override
	public void start(Stage primaryStage) throws Exception {


	Media music=new Media("file:///C:/Users/user/Documents/NetBeansProjects/Archipel_Jeu/src/pirate.mp3");
       MediaPlayer mediaPlayer = new MediaPlayer(music);
	mediaPlayer.setAutoPlay(true);
	mediaPlayer.setVolume(10);



try { new FenetrePrincipale(primaryStage); } catch(Exception e) {e.printStackTrace();}
		}



	public static void main(String[] args) {

		launch(args);
	}
}
