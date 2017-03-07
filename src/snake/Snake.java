package snake;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snake extends JFrame implements KeyListener, Runnable{
	
	/**
	 * Panel properties
	 */
	private JPanel p = new JPanel();
	private final int WIDTH = 300;
	private final int HEIGHT = 300;
	
	/**
	 * Snake properties
	 */
	private JButton [] sb = new JButton[200];
	private int x = 500, y = 250;
	private int su = 3;
	private int directionX = 1, directionY = 0, oldX, oldY;
	
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
	}
	
	/**
	 * The start of the game
	 */
	public void starting_Snake(){
		for(int i = 0; i < su; i++){
			sb[i] = new JButton("sb" + i);
			sb[i].setEnabled(false);
			p.add(sb[i]);
			
			sb[i].setBounds(snake_X[i], snake_Y[i], 10, 10);
			snake_X[i+1] = snake_X[i] = snake_X[i] - 10;
			snake_Y[i+1] = snake_Y[i];
		}
	}
	
	/**
	 * Resetting of the game
	 */
	public void reset(){
		initializeValues();
		p.removeAll();
		
		thread.stop();
		
		starting_Snake();
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Snake gets bigger as he eats
	 */
	public void grow(){
		sb[su] = new JButton();
		sb[su].setEnabled(false);
		
		p.add(sb[su]);
		
		int x = 10 + (10 * r.nextInt(48));
		int y = 10 + (10 * r.nextInt(23));
		
		snake_X[su] = x;
		snake_Y[su] = y;
		sb[su].setBounds(x,y,10,10);
		su++;
	}
	
	/**
	 * Move the snake forward
	 */
	public void move(){
		for(int i = 0; i < su; i++){
			snake_Point[i] = sb[i].getLocation();
		}
		
		/**
		 * Move the head of the snake
		 */
		snake_X[0] += directionX;
		snake_Y[0] += directionY;
		sb[0].setBounds(snake_X[0], snake_Y[0], 10, 10);
		
		for(int i = 0; i < su; i++){
			sb[i].setLocation(snake_Point[i-1]);
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
		
		if(snake_X[0] == snake_X[su - 1] && snake_Y[0] == snake_Y[su - 1]){
			food = false;
			score ++;
		}
		
		if(food == false){
			grow();
			food = true;
		} else{
			sb[su -1].setBounds(snake_X[su - 1], snake_Y[su -1], 10, 10);
		}
		
		
	}
	
	public void run(){
		
	}
	
	public void keyTyped(KeyEvent e){
		
	}
	
	public void keyPressed(KeyEvent e){
		
	}
	
	public void keyReleased(KeyEvent e){
		
	}
	
}