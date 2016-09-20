import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.Random;

public class StartingPoint extends Applet implements Runnable,KeyListener , MouseMotionListener, MouseListener{
	

	private Image i;
	private Graphics doubleG;
	Ball b;
	Platform p[] = new Platform[7];
	Item item[] = new Item[3];
	private int score;
	double cityX = 0;
	double cityDx = .3;
	URL url;
	Image city;
	int levelcheck = 0;
	boolean gameOver = false;
	boolean mouseIn = false;
	
	
    public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void init(){
	  setSize(800,600);
	  addKeyListener(this);
	  addMouseListener(this);
	  addMouseMotionListener(this);
	 /* try{
		  url = getDocumentBase();
	  }catch(Exception e){
		  
	  }
	  city = getImage(url, "Images/city.png");
	  Pictures p = new Pictures(this);
    }*/
	}
    public void start(){
    	b = new Ball();
    	score = 0;
    	for(int i = 0; i<p.length; i++){
	  
    		Random r = new Random();
    		p[i] = new Platform(i*120 , 300);
    	}
    	for(int i = 0; i<item.length; i++){
    		  
    		Random r = new Random();
    		switch(r.nextInt(5)){
			case 0:
				item[i] = new GravUp(getWidth() + 2000 * i);
				break;
			case 1:
				item[i] = new GravDown(getWidth() + 2000 * i);
				break;
			case 2:
				item[i] = new AgilUp(getWidth() + 2000 * i);
				break;
			case 3:
				item[i] = new AgilDown(getWidth() + 2000 * i);
				break;	
			case 4:	
				item[i] = new ScorePlus(getWidth() + 2000 * i,this);
				break;
			}
    	
    		
    	}
    	Thread thread = new Thread(this);
	  thread.start();
	 
    }
    public void run(){
		while(true){
			
		
			gameOver = b.getGameOver();
		/*	
			if(levelcheck > 1000){
				Pictures.level++;
				levelcheck = 0;
				
			}
			levelcheck++;
			
			if(cityX > getWidth() * -1){
			cityX -= cityDx;
			}else{
				cityX = 0;
			}*/
			if(!gameOver){
			score++;
			}
			
			Random r = new Random();
			
			for(int i = 0; i < item.length; i++){
				if(item[i].isCreateNew()){
					item[i] = null;
					switch(r.nextInt(5)){
					case 0:
						item[i] = new GravUp(getWidth() + 10 * r.nextInt(500));
						break;
					case 1:
						item[i] = new GravDown(getWidth() + 10 * r.nextInt(500));
						break;
					case 2:
						item[i] = new AgilUp(getWidth() + 10 * r.nextInt(500));
						break;
					case 3:
						item[i] = new AgilDown(getWidth() + 10 * r.nextInt(500));
						break;	
					case 4:	
						item[i] = new ScorePlus(getWidth() + 10 * r.nextInt(500),this);
						break;
					}
					item[i].setCreateNew(false);
				}
				
			}
			b.update(this);
			for(int i = 0;i<p.length;i++){
			p[i].update(this,b);
			}
			for(int i = 0;i<item.length;i++){
				item[i].update(this,b);
				}
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void stop(){
		
		
	}
	public void destroy(){
		
		
	}
	
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
	    if(i == null){
	    	i = createImage(this.getSize().width, this.getSize().height);
	    	doubleG = i.getGraphics();
	    }    
	    
	    doubleG.setColor(getBackground());
	    doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
	    
	    doubleG.setColor(getForeground());
	    paint(doubleG);
	    
	    g.drawImage(i, 0, 0,this);
	}
	
	public void paint(Graphics g){
	   
		
		g.setColor(new Color(15,77,147));
		g.fillRect(0, 0, getWidth(), getHeight());
		//g.drawImage(city, (int) cityX, 0, this);
		//g.drawImage(city, (int) cityX + getWidth(), 0, this);
		
		for(int i = 0;i<p.length;i++){
		p[i].paint(g);
		}
		
		b.paint(g);
		String s = Integer.toString(score);
		Font font = new Font("Serif", Font.BOLD, 32);
	    g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(s, getWidth()-150+2 ,50+2);
		g.setColor(new Color(198,226,255));
		g.drawString(s, getWidth()-150 ,50);
		
		for(int i = 0;i<item.length;i++){
		item[i].paint(g);
		}
		if(gameOver){
			 g.setColor(Color.white);
			   g.drawString("GAME OVER", 300, 300);
			   //mouse check
			   g.drawRect(280, 320, 180, 40);
			if(mouseIn){
				 g.setColor(Color.RED);
				   g.drawString("Play again?", 280, 340);
			}else{
		
				 g.setColor(Color.white);
				   g.drawString("Play again?", 280, 340);
		}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
		    b.moveLeft();
		    break;
		case KeyEvent.VK_RIGHT:
			b.moveRight();
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(gameOver){
		if(e.getX() > 280 && e.getX() < 460){
			if(e.getY() > 320 && e.getY() < 360){
				mouseIn = true;
			}
		}
		if(e.getX() < 280 || e.getX() > 460){
			mouseIn = false;
		}
		if(e.getY() < 320 || e.getY() > 360){
			mouseIn = false;
		}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//start new game
		
		if(mouseIn){
			b = null;
			b = new Ball();
			score = 0;
		  //  Pictures.level = 1;
			for(int i = 0; i<p.length; i++){
				  
	    		Random r = new Random();
	    		p[i] = new Platform(i*120 , 300);
	    	}
	    	for(int i = 0; i<item.length; i++){
	    		  
	    		Random r = new Random();
	    		switch(r.nextInt(5)){
				case 0:
					item[i] = new GravUp(getWidth() + 2000 * i);
					break;
				case 1:
					item[i] = new GravDown(getWidth() + 2000 * i);
					break;
				case 2:
					item[i] = new AgilUp(getWidth() + 2000 * i);
					break;
				case 3:
					item[i] = new AgilDown(getWidth() + 2000 * i);
					break;	
				case 4:	
					item[i] = new ScorePlus(getWidth() + 2000 * i,this);
					break;
				}
	    	
	    		
	    	}
	    	mouseIn = false;
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
