package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.Timer;


//http://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
//http://www.programcreek.com/java-api-examples/java.awt.Dimension


//arraylist

public class Snake implements ActionListener {
	
	public JFrame frame;
	public Panel panel;
	public static Snake snake;
	public Timer timer = new Timer(20, this);
	
	public LinkedList<Point> snakeParts = new LinkedList<Point>();
	
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	
	public int ticks = 0, direction = DOWN, score;
	
	public Point head,apple;
	
	
	public Snake() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Snake");
		frame.setVisible(true);
		frame.setSize(750,650);
		frame.setLocation(dim.width / 2 - frame.getWidth()/2, dim.height / 2 - frame.getHeight()/2);
		frame.add(panel = new Panel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		head = new Point(0,0);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0){
		panel.repaint();
		ticks++;
		
		if(ticks % 10 == 0 && head != null){
			if(direction == UP)
				snakeParts.add(new Point(head.x, head.y - 1));
			if(direction == DOWN)
				snakeParts.add(new Point(head.x, head.y + 1));
			if(direction == LEFT)
				snakeParts.add(new Point(head.x - 1, head.y));
			if(direction == RIGHT)
				snakeParts.add(new Point(head.x + 1, head.y));
			if(apple != null){
				
			}
		}
		
	}
	
	public static void main(String[] args){
		snake = new Snake();
	}
}
