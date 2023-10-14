package jeudeplateau;

import java.util.Random;


public class Dés {

	private int de1;
	private int de2;
        //La classe Random permet de générer des nombres random
	private Random rand = new Random();

	// Renvoie le nombre obtenu par le lancé de dé
	
	public int getDes() {
		return (this.de1 + this.de2);
	}

	//Renvoie le chiffre obtenu par le premier dé
	
	public int getDe1(){
		return this.de1;
	}

	//Renvoie le chiffre obtenu par le deuxième dé
	
	public int getDe2(){
		return this.de2;
	}

	//Attribue un nombre aléatoire aux 2 dés et les additionne
	//NextInt() est une méthode de la classe Random
        //NextInt(6) permet de générer des valeurs aléatoires de 0 à 5
	public int lancerDes() {
		this.de1 = 1+this.rand.nextInt(6);
		this.de2 = 1+this.rand.nextInt(6);

		return getDes();
	}

}