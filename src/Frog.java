import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

//Classe qui va définir les grenouilles du jeu et qui hérite de Case
public class Frog extends Case {
	//attribut de type random afin de faire apparaitre aléatoirement les grenouilles sur le canvas
	private final static Random rng  = new Random();
	
	public Frog() {
		super(getRandomX(), getRandomY());	
	}
	
	// retourne un indice x de manière aléatoire
	private static int getRandomX() {
		return rng.nextInt(NBR_COLONNES);
	}
	
	// retourne un indice y de manière aléatoire
	private static int getRandomY() {
		return rng.nextInt(NBR_LIGNES);
	}
	
	//méthode qui permet de faire apparaitre une nouvelle grenouille lorsque la précédente a été mangé
	public void newFrog() {
		this.setX_indice(getRandomX());
		this.setY_indice(getRandomY());
	}
	
	//Méthode qui permet d'afficher la grenouille
	public void affichage(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(this.getX() + 2, this.getY() + 2, this.getLargeur() - 4 , this.getHauteur() - 4 );
	}
}
