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
	
	private JPanel p = new JPanel();
	
	private final int WIDTH = 300;
	private final int HEIGHT = 300;
	
	//Snake properties
	private JButton [] lb = new JButton[200];
	private int x = 500, y = 250;
	private int gu = 3;
	private int directionX = 1, directionY = 0, oldX, oldY;
	
	//Snake coordinates
	private int[] snake_X = new int[300];
	private int[] snake_Y = new int[300];
	
	//Snake movoments
	private boolean move_left = false;
	private boolean move_right = false;
	private boolean move_up = false;
	private boolean move_down = false;
	
	private Point[] snake_Point = new Point[300];
	
	//Start thread
	Thread thread;
	Random r = new Random();
	
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
	
	public void initializeValues(){
		gu = 3;
		snake_X[0] = 100;
		snake_Y[0] = 150;
		directionX = 10;
		directionY = 0;
		
		move_left = false;
		move_right = true;
		move_up = true;
		move_down = true;
	}
	
	public void starting_Snake(){
		for(int i = 0; i < gu; i++){
			lb[i] = new JButton("lb" + i);
			lb[i].setEnabled(false);
			p.add(lb[i]);
			
			lb[i].setBounds(snake_X[i], snake_Y[i], 10, 10);
			snake_X[i+1] = snake_X[i] = snake_X[i] - 10;
			snake_Y[i+1] = snake_Y[i];
		}
	}
	
	public void reset(){
		initializeValues();
		p.removeAll();
		
		thread.stop();
		
		starting_Snake();
		thread = new Thread(this);
		thread.start();
	}
	
	//Snake gets larger as he eats
	public void grow(){
		lb[gu] = new JButton();
		lb[gu].setEnabled(false);
		
		p.add(lb[gu]);
		
		int x = 10 + (10 * r.nextInt(48));
		int y = 10 + (10 * r.nextInt(23));
		
		snake_X[gu] = x;
		snake_Y[gu] = y;
		lb[gu].setBounds(x,y,10,10);
		gu++;
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