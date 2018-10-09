import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;

//Classe qui va définir le serpent présent dans le jeu
public class Snake {
	//Attribut qui représente la taille du serpent par une liste de case
	private LinkedList<Case> tail;
	//Attribut qui représente la direction actuelle du serpent
	private Direction dir;
	//Attribut qui représente si le serpent est mort ou pas (hors limite ou mordu la queue)
	private boolean estMort;
	//Attribut qui représente la direction que le joueur souhaite voir le serpent prendre
	private Direction demande;
	//Attribut qui représente le nombre de grenouilles mangées par le serpent, utilisé pour la difficulté
	private int eatCount;
	
	//Constructeur de Snake
	public Snake() {
		//Création du serpent avec trois cases de départ, en vie, se dirigeant à gauche par défaut et avec 0 grenouilles mangées
		this.tail = new LinkedList<Case>();
		this.tail.add(new Case(14, 15));
		this.tail.add(new Case(15, 15));
		this.tail.add(new Case(16, 15));
		this.demande = Direction.VERS_LA_GAUCHE;
		this.dir = Direction.VERS_LA_GAUCHE;
		this.estMort = false;
		this.eatCount = 0;
	}
	
	//méthode qui permet de faire bouger/manger/tuer le serpent 
	public void calcul(Frog f) {
		tourner();
		if (peutManger(f)) {
			mange();
			f.newFrog();
		}
		else if(peutAvancer()) {
			avance();	
		} else {
			//Gestion du GAME OVER
			this.estMort = true;
		}
	}
	
	//méthode qui permet de dessiner le serpent
	public void affichage (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(Case c : this.tail) {
			if (c == this.tail.getFirst()) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.WHITE);
			}
			g.fillOval(c.getX(), c.getY(), c.getLargeur(), c.getHauteur());
		}
	}
	
	//Méthode qui permet de déterminer quelle est la case suivante sur laquelle va bouger le serpent
	private Case getNextCase() {
		Case head = this.tail.getFirst();
		switch (this.dir) {
			case VERS_LE_HAUT : 
				return new Case(head.getX_indice(), head.getY_indice()-1);
			case VERS_LE_BAS :
				return new Case(head.getX_indice(), head.getY_indice()+1);
			case VERS_LA_GAUCHE :
				return new Case(head.getX_indice()-1, head.getY_indice());
			case VERS_LA_DROITE :
				return new Case(head.getX_indice()+1, head.getY_indice());
		}
		return null;
	}
	
	//méthode qui permet de modifier la direction du serpent en fonction de l'utilisateur et des contraintes du jeu
	private void tourner() {
		if(this.demande != null) {
			//Si Larry se dirige vers le haut OU le bas
			if(this.dir == Direction.VERS_LE_HAUT || this.dir == Direction.VERS_LE_BAS) {
				// et que le joueur demande qu'il aille vers la droite, le faire tourner à droite
				if (this.demande == Direction.VERS_LA_DROITE) {this.dir = Direction.VERS_LA_DROITE;}
				// et que le joueur demande qu'il aille vers la gauche, le faire tourner à gauche
				else if (this.demande == Direction.VERS_LA_GAUCHE) {this.dir = Direction.VERS_LA_GAUCHE;}
			}
			//Si Larry se dirige ni vers le haut OU le bas  (donc gauche ou droite)
			// et que le joueur demande qu'il aille vers le haut, le faire tourner en haut
			else if(this.demande == Direction.VERS_LE_HAUT) {
				this.dir = Direction.VERS_LE_HAUT;
			}
			// et que le joueur demande qu'il aille vers le bas, le faire tourner en bas
			else if(this.demande == Direction.VERS_LE_BAS) {
				this.dir = Direction.VERS_LE_BAS;
			}
		}
		//demande est mis à null pour que le joueur doit réappuyer sur une touche pour refaire tourner le serpent
		this.demande = null;
	}
	
	//méthode qui fait bouger le serpent 
	private void avance() {
		this.tail.addFirst(getNextCase());
		this.tail.removeLast();
	}
	
	//Méthode qui permet de faire grandir le serpent et d'augmenter de 1 le nombre de grenouille mangé (eatcount)
	private void mange() {
		this.tail.addFirst(getNextCase());
		this.eatCount++;
	}
	//Méthode qui renvoie true/false en fonction de si le serpent peut avancer sur la case souhaité ou pas sans mourrir
	private boolean peutAvancer() {
		Case nextCase = getNextCase();
		return nextCase.estValide() && !this.tail.contains(nextCase);
	}
	//Méthode qui détermine si la prochaine case sur laquelle le serpent bouge va lui faire manger une grenouille
	private boolean peutManger(Frog f) {
		Case nextCase = getNextCase();
		return nextCase.getX_indice() == f.getX_indice() && f.getY_indice() == nextCase.getY_indice();
	}
	
	//indique si le serpent est mort ou non
	public boolean estMort() {
		return this.estMort;
	}
	
	//set la direction demandé par le joueur
	public void setDemande(Direction d) {
		this.demande = d;
	}
	
	//retourne le nb de grenouilles mangé par le serpent
	public int getEatCount() {
		return this.eatCount;
	}
}
