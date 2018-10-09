//Classe Case qui définit les cases du jeu, du serpent et des grenouilles, afin de placer et faire bouger facilement ceux-ci
public class Case implements Constantes{
	//indice x de la case
	private int x_indice;
	//indice y de la case
	private int y_indice;
	
	//Constructeur de la classe case
	public Case(int x, int y) {
		this.setX_indice(x);
		this.setY_indice(y);
	}
	
	//Override de la méthode equals de la classe Object afin de pouvoir comparer les différentes cases du serpent et savoir s'il se mord
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Case ) {
			Case box = (Case) obj;
			return this.x_indice == box.x_indice && this.y_indice == box.y_indice;
		}
		return false;
	}
	
	//Getter de l'indice x de la case
	public int getX_indice() {
		return x_indice;
	}
	//Setter de l'indice x de la case
	public void setX_indice(int x_indice) {
		this.x_indice = x_indice;
	}
	//Getter de l'indice y de la case
	public int getY_indice() {
		return y_indice;
	}
	//Setter de l'indice y de la case
	public void setY_indice(int y_indice) {
		this.y_indice = y_indice;
	}
	//Getter du pixel x auquel se situe le case
	public int getX() {
		return this.x_indice * DIM_CASE;
	}
	//Getter du pixel y auquel se situe le case
	public int getY() {
		return this.y_indice * DIM_CASE;
	}
	//Getter de la dimension de la case en vertical
	public int getHauteur() {
		return DIM_CASE;
	}
	//Getter de la dimension de la case en horizontal
	public int getLargeur() {
		return DIM_CASE;
	}
	
	//méthode qui retourne true ou false en fonction que la case en cours est hors limite ou non
	public boolean estValide() {
		return this.x_indice >= 0 && this.x_indice < NBR_COLONNES && this.y_indice >= 0 && this.y_indice < NBR_LIGNES; 
	}
}
