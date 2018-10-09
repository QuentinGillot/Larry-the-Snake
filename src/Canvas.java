import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Class Canvas qui s'occuppe d'afficher le jeu et les composants
public class Canvas extends JFrame implements Constantes {
	private Game game;
	
	//Constructeur du Canvas
	public Canvas() {
		super("Larry, the snake");
		this.game = new Game();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(false);
		
		//Panel dans lequel le jeu va être affiché et ses composants
		final JPanel content = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.BLACK);
				Canvas.this.game.affichage(g);
			}
		};
		content.setFocusable(true);
		content.setPreferredSize(new Dimension(NBR_COLONNES*DIM_CASE, NBR_LIGNES*DIM_CASE));
		//JPanel content se fait rajouter un listener afin de pouvoir détecter les saisies claviers
		content.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Canvas.this.game.gestionClavier(e);
			}
		});
		this.setContentPane(content);	
		
		//Thread qui permet de faire tourner le jeu en boucle jusqu'au game over
		Thread thread = new Thread(new Runnable() {
			@Override public void run(){ 
				while(true) { 
					Canvas.this.game.calcul();
					content.repaint();
					try {
						//le Thread sleep en fonction du niveau de la partie
						Thread.sleep(Canvas.this.game.getTemporisation());
					} catch(InterruptedException e) {
						System.err.println("Erreur : " + e.toString() + " " + e.getMessage());
					}
				} 
			}
		});
		thread.start();
	}
	
	//fonction main du jeu
	public static void main(String[] args) {
		Canvas c = new Canvas();
		c.pack();
		c.setLocationRelativeTo(null);
		c.setVisible(true);
	}
	
}
