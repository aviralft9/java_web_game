import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Item {
	private int x, y, dx, radius;
	private StartingPoint sp;
	private boolean CreateNew = false;
	
	public boolean isCreateNew() {
		return CreateNew;
	}
	public void setCreateNew(boolean createNew) {
		this.CreateNew = createNew;
	}
	public Item(int x ) {
		// TODO Auto-generated constructor stub
	
		this.x = x;
		Random r = new Random();
		y = r.nextInt(400) + radius;
		dx = -2;
		radius = 10;
	}
	public void update(StartingPoint sp,Ball b){
		  
		x += dx;
		this.sp = sp;
		checkForCollision(b);
		if(x < 0 - radius){
			CreateNew = true;
					
		}
		
	}
	private void checkForCollision(Ball b) {
		// TODO Auto-generated method stub
		int ballX = b.getX();
		int ballY = b.getY();
	    int ballR = b.getRadius();
	    
		int a = x - ballX;
		int bb = y - ballY;
		int collide = radius + ballR;
		//distance b/w object centers
		double c = Math.sqrt((double) (a*a) + (double) (bb*bb));
	   	
		if( c < collide){
			performAction(b);
		CreateNew = true;
		}
		
		
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void performAction(Ball b) {
		// TODO Auto-generated method stub
		
	}
	public void paint(Graphics g){
		   //g.setColor(Color.GREEN);
		   g.fillOval(x-radius, y-radius, radius*2, radius*2);
		   
		   
	   }

}
