package snake;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Snake extends JFrame implements KeyListener, Runnable{
	
	/**
	 * Panel properties
	 */
	private JPanel p = new JPanel();
	private final int WIDTH = 500;
	private final int HEIGHT = 500;
	
	/**
	 * Snake properties
	 */
	private JButton [] snakebody = new JButton[300];
	private int x = 500, y = 500;
	private int su = 3;
	private int directionX = 1, directionY = 0;
	private final int speed = 100;
	
	/**
	 * Snake coordinates
	 */
	private int[] snake_X = new int[300];
	private int[] snake_Y = new int[300];
	
	/**
	 * Snake movements
	 */
	private boolean move_left = false;
	private boolean move_right = false;
	private boolean move_up = false;
	private boolean move_down = false;
	
	private Point[] snake_Point = new Point[300];
	private boolean food = false;
	private int score = 0;
	private boolean gameover = false;
	double xcoordinatehead,ycoordinatehead, xcoordinatebody, ycoordinatebody;
	
	/**
	 * Start the thread
	 */
	Thread thread;
	
	/**
	 * Creation of a new random
	 */
	Random r = new Random();
	
	/**
	 * Class snake
	 */
	@SuppressWarnings("deprecation")
	public Snake(){
		super("Snake");
		
		setSize(WIDTH,HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		starting_Snake();
		
		p.setLayout(null);
		p.setBackground(Color.BLACK);
		
		add(p);
		
		show();
		
		initializeValues();
		addKeyListener(this);
		
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Initialize the values for the game
	 */
	public void initializeValues(){
		su = 3;
		
		snake_X[0] = 100;
		snake_Y[0] = 150;
		directionX = 10;
		directionY = 0;
		
		food = false;
		score = 0;
		
		move_left = false;
		move_right = true;
		move_up = true;
		move_down = true;
		
		gameover = false;
	}
	
	/**
	 * The start of the game
	 */
	public void starting_Snake(){
		for(int i = 0; i < su; i++){
			snakebody[i] = new JButton("sb" + i);
			snakebody[i].setEnabled(false);
			p.add(snakebody[i]);
			
			snakebody[i].setBounds(snake_X[i], snake_Y[i], 10, 10);
			snake_X[i+1] = snake_X[i] = snake_X[i] - 10;
			snake_Y[i+1] = snake_Y[i];
		}
	}
	
	/**
	 * Snake gets bigger as he eats
	 */
	public void grow(){
		snakebody[su] = new JButton();
		snakebody[su].setEnabled(false);
		
		p.add(snakebody[su]);
		
		int x = 10 + (10 * r.nextInt(44));
		int y = 10 + (10 * r.nextInt(44));
		
		snake_X[su] = x;
		snake_Y[su] = y;
		snakebody[su].setBounds(x,y,10,10);
		su++;
	}
	
	/**
	 * Move the snake 
	 */
	public void move(){
		
		for(int i = 0; i < su; i++){
			snake_Point[i] = snakebody[i].getLocation();
		}
		
		/**
		 * Move the head of the snake
		 */
		snake_X[0] += directionX;
		snake_Y[0] += directionY;
		snakebody[0].setBounds(snake_X[0], snake_Y[0], 10, 10);
		
		for(int i = 1; i < su; i++){
			snakebody[i].setLocation(snake_Point[i-1]);
		}
		
		/**
		 * Logic for snake movements
		 */
		if(snake_X[0] == x){
			snake_X[0] = 10;
		}else if(snake_X[0] == 0){
			snake_X[0] = x - 10;
		}else if(snake_Y[0] == y){
			snake_Y[0] = 10;
		}else if(snake_Y[0] == 0){
			snake_Y[0] = y - 10;
		}
		
		/**
		 * Increase score when snake eats food
		 */
		if(snake_X[0] == snake_X[su - 1] && snake_Y[0] == snake_Y[su - 1]){
			food = false;
			score += 5;
			System.out.println("Score: " + score);
		}
		
		/**
		 * Generate new food when food is false
		 */
		if(food == false){
			grow();
			food = true;
		} else{
			snakebody[su -1].setBounds(snake_X[su - 1], snake_Y[su -1], 10, 10);
		}	
		
		/**
		 * Logic for when the snake hits itself ( =gameover)
		 */
		for(int i = 1; i < su-1; i++){
			xcoordinatehead = snake_Point[0].getX();
			ycoordinatehead = snake_Point[0].getY();
			
			xcoordinatebody = snake_Point[i].getX();
			ycoordinatebody = snake_Point[i].getY(); 
			
			if(xcoordinatehead == xcoordinatebody && ycoordinatehead == ycoordinatebody){
				gameover = true;
				System.out.println("Game over! Your score is: " + score);
				try{
					thread.join();
				}catch(Exception e){
					
				}
				break;	
			}
		}
		p.repaint();
	}

	@Override
	public void run(){
		/**
		 * Starts the game by moving the snake to the right
		 */	
		while(!gameover){
			
			move();
			
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Moves the snake to the direction of the arrow key that the user pressed
	 */
	@Override
	public void keyPressed(KeyEvent e){
		
		/**
		 * Change direction to LEFT
		 */
		if(move_left == true && e.getKeyCode() == 37){
			directionX = -10;
			directionY = 0;
			move_right = false;
			move_up = true;
			move_down = true;
		}
		
		/**
		 * Change direction to UP
		 */
		if(move_up == true && e.getKeyCode() == 38){
			directionX = 0;
			directionY = -10;
			move_down = false;
			move_left = true;
			move_right = true;
		}
		
		/**
		 * Change direction to RIGHT
		 */
		if(move_right == true && e.getKeyCode() == 39){
			directionX = 10;
			directionY = 0;
			move_left = false;
			move_up = true;
			move_down = true;
		}
		
		/**
		 * Change direction to DOWN
		 */
		if(move_down == true && e.getKeyCode() == 40){
			directionX = 0;
			directionY = 10;
			move_up = false;
			move_left = true;
			move_right = true;
		}	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}