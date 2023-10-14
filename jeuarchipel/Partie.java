package jeuarchipel;


import java.util.ArrayList;

import fenetres.FenetrePrincipale;
import io.Console;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import jeudeplateau.Case;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

// Lance la partie


public class Partie {

	private PlateauJeu pm;
	private FenetrePrincipale fp;
	private boolean pausePartie = false;
	public final static long VITESSE_PARTIE = 1000;
	public final static boolean PARTIE_AUTO = false;

	/* CONSTRUCTEUR PARTIE */

	//Crée une partie en fonction du nombre de joueurs
	
	public Partie(int nombreDeJoueurs, ArrayList<String> nomsDesJoueurs, FenetrePrincipale fp) {
		this.pm = new PlateauJeu(nombreDeJoueurs);
		this.fp = fp;

		for(int i=0; i<nombreDeJoueurs; i++) {
			pm.getJoueur(i).setNom(nomsDesJoueurs.get(i));
		}
	}

	// Pour Démarrer la partie
	public void demarrerLaPartie() {
//Le service est concu pour exécuter une tache
		final Service<Void> partieService = new Service<Void>() {

            @Override
            //Task Permet de programmer des taches asynchrones et qui doit override la méthode call
            protected Task<Void> createTask() {
                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {
                        
                    	Console es = new Console();
                    	es.println("La partie démarre!");
                        
                        
                        //Enregistrement des données dans un fichier 
    Path path = Paths.get("D:/sousou.txt");
    String str = "La partie démarre";
		try {
			
			byte[] bs = str.getBytes();
                        //Files.write() permet de créer et écrire dans un fichier
			Path writtenFilePath = Files.write(path, bs);
		
		} catch (Exception e) {
			e.printStackTrace();
		}


                		Joueur joueur;
                		int lancé;
                		Case caze;

                		while(!pm.finPartie() && pm.getNbTours() <= 100) {

                			joueur = pm.getJoueurActif();

                			if(pm.getJoueurActifID() == 0)
                                        {es.println("=== DEBUT DU TOUR " + pm.getNbTours() + " ===");
                                        
                                        
                                         str = str + System.getProperty("line.separator")+ "=== DEBUT DU TOUR " + pm.getNbTours() + " === ";
		try {
			
			byte[] bs = str.getBytes();
			Path writtenFilePath = Files.write(path, bs);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
                                        
                                        

                			es.println("C'est au tour de " + joueur.getNom() + " (possède " + joueur.getArgent() + "DT)");
                                        
                                        
                                                                   str = str +System.getProperty("line.separator")+ "C'est au tour de " + joueur.getNom() + " (possède " + joueur.getArgent() + "DT)";
		try {
			
			byte[] bs = str.getBytes();
			Path writtenFilePath = Files.write(path, bs);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
                			fp.afficherMessage("C'est au tour de " + joueur.getNom() + " (possède " + joueur.getArgent() + "DT)");
                                        
                                       
		}

                			if(!joueur.getEstBanqueroute()) {
                                            //La période qu'on va attendre entre 2 lancées de dés
                				Thread.sleep(VITESSE_PARTIE);
                				lancé = pm.des.lancerDes();

                				if(!joueur.getestDansPuits()) {

                    				fp.afficherDes(pm);
                					
                                               es.println("" + joueur.getNom() + " lance les dés... [" + pm.des.getDe1() + "][" + pm.des.getDe2() + "]... et obtient un " + lancé + " !");
                                                 str = str + System.getProperty("line.separator")+"" + joueur.getNom() + " lance les dés... [" + pm.des.getDe1() + "][" + pm.des.getDe2() + "]... et obtient un " + lancé + " !";
		try {
			
			byte[] bs = str.getBytes();
			Path writtenFilePath = Files.write(path, bs);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
                					pm.deplacerJoueur(joueur, lancé);
                					fp.deplacerPion(joueur);

                					caze = pm.getCase(joueur.getPosition());
                					es.println("" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur " + caze.getNom());
                                                         str = str +System.getProperty("line.separator")+ "" + joueur.getNom() + " avance de " + lancé + " cases et atterit sur " + caze.getNom();
		try {
			
			byte[] bs = str.getBytes();
			Path writtenFilePath = Files.write(path, bs);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
                                                        
                				}
                				else {
                					es.println("Le joueur est en puits.");
                                                         str = str +System.getProperty("line.separator")+"Le joueur est en puits.";
		try {
			
			byte[] bs = str.getBytes();
			Path writtenFilePath = Files.write(path, bs);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
                                                       
                					caze = pm.getCase(joueur.getPosition());
                				}
            					Thread.sleep(VITESSE_PARTIE);
                				pausePartie = true;
            					caze.fenetreAction(fp);

                    			while(pausePartie && !PARTIE_AUTO){ Thread.sleep(200); }

                				caze.actionCase(joueur, pm, fp);

                				es.println("" + joueur.getNom() + " possède à la fin de son tour " + joueur.getArgent() + "DT");
                                                    str = str +"" + System.getProperty("line.separator")+joueur.getNom() + " possède à la fin de son tour " + joueur.getArgent() + "DT";
		try {
			
			byte[] bs = str.getBytes();
			Path writtenFilePath = Files.write(path, bs);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
                				System.out.println("et les terrains suivants :\n" + joueur.getListeStringTerrains());
                			}
                			else {
                				es.println("" + pm.getJoueurActif().getNom() + " est en banqueroute, il ne joue pas.");
                                                str = str +System.getProperty("line.separator")+"" + pm.getJoueurActif().getNom() + " est en banqueroute, il ne joue pas.";
		try {
			
			byte[] bs = str.getBytes();
			Path writtenFilePath = Files.write(path, bs);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
                                                
                			}

                			Thread.sleep(400);
                			fp.deplacerPion(joueur);
                			fp.refreshLabels(pm);

                			pausePartie = !joueur.getEstBanqueroute();
                			while(pausePartie && !PARTIE_AUTO){ Thread.sleep(200); }

                			es.println("");
                			fp.effacerDes();
                			pm.setJoueurSuivant();

                		}

                		es.println("=== Fin de la partie ===");
                                
                		es.println(" Le vainqueur est " + pm.estVainqueur().getNom() + " !");
                                str = str +System.getProperty("line.separator")+"=== Fin de la partie ==="+" Le vainqueur est " + pm.estVainqueur().getNom() + " !";
		try {
			
			byte[] bs = str.getBytes();
			Path writtenFilePath = Files.write(path, bs);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

                		fp.afficherVainqueur(pm);

                		return null;
                    }
                };
            }
        };
        partieService.start();
	}


	public PlateauJeu getPM() {
		return this.pm;
	}

	//Permet de reprendre le cours de la partie
	 
	public void reprendrePartie() {
		this.pausePartie = false;
	}
	public void pausePartie(){
		this.pausePartie = true;
	}
	public boolean getPausePartie(){
		return this.pausePartie;
	}
	@Override
	public String toString() {
		return "Partie [plateauM=" + pm.toString() + "]";
	}

}