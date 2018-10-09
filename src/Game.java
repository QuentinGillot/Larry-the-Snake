import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

//Classe Game qui s'occupe de faire bouger/animer le jeu.
public class Game {
	
	//Le serpent du jeu, appelé ici Larry
	private Snake larry;
	//Les grenouilles qui apparaissent tout au long du jeu
	private Frog froggy;
	//Boolean qui permet de terminer ou non le jeu
	private boolean gameOver;
	
	//Constructeur de la classe Game
	public Game () {
		this.larry = new Snake();
		this.froggy = new Frog();
		this.gameOver = false;
	}
	
	//Méthode qui s'occupe d'animer le jeu
	public void calcul() {
		if (!gameOver) {
			this.larry.calcul(this.froggy);
		}
		//Test si larry est mort afin de déclencher le game over
		if(this.larry.estMort()){this.gameOver = true;}
	}
	
	//Méthode qui s'occupe de dessiner le jeu
	public void affichage(Graphics g) {
		
		//Affichage de Larry
		this.larry.affichage(g);
		
		//Affichage de Froggy
		this.froggy.affichage(g);
		
		//Affichage du Game Over
		if(this.gameOver) {
			String str = "R.I.P LARRY";
			g.setColor(Color.RED);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
			FontMetrics fm = g.getFontMetrics();
			int x = (g.getClipBounds().width - fm.stringWidth(str)) / 2;
			int y = (g.getClipBounds().height / 2) + fm.getMaxDescent();
			g.drawString(str, x, y);
		}
		
		//Affichage du niveau de difficulté
		g.setColor(Color.BLUE);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.drawString(String.valueOf(this.getNiveau()), 5, 25);
	}
	
	//Méthode qui s'occupe d'envoyer au snake la direction souhaitée par le joueur à travers le clavier 
	public void gestionClavier(KeyEvent event) {
		//Touche flèche droite
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.larry.setDemande(Direction.VERS_LA_DROITE);
		}
		//Touche flèche gauche
		else if(event.getKeyCode() == KeyEvent.VK_LEFT) {
			this.larry.setDemande(Direction.VERS_LA_GAUCHE);
		}
		//Touche flèche bas
		else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			this.larry.setDemande(Direction.VERS_LE_BAS);
		}
		//Touche flèche haut
		else if (event.getKeyCode() == KeyEvent.VK_UP) {
			this.larry.setDemande(Direction.VERS_LE_HAUT);
		}
	}
	
	// Méthode qui permet de changer le timer du sleep du thread en fonction du niveau du jeu et ainsi la vitesse du snake
	public int getTemporisation() {
		switch (getNiveau()) {
		case 1:	
			return 500;
		case 2 : 
			return 400;
		case 3:	
			return 350;
		case 4 : 
			return 300;
		case 5 :	
			return 250;
		case 6 : 
			return 200;
		case 7 :	
			return 150;
		case 8 : 
			return 100;
		case 9 : 
			return 80;
		default : 
			return 50;
		}
	}
	
	//Méthode qui retourne le niveau en fonction du nbr de grenouille mangé(+1 niveau tous les 5 grenouilles mangés)
	private int getNiveau() {
		return (this.larry.getEatCount() / 5) + 1;
	}
}
