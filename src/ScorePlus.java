import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class ScorePlus extends Item {
	private StartingPoint appletInfo;
	public ScorePlus(int x, StartingPoint appletInfo) {
		// TODO Auto-generated constructor stub
	   super(x);
	   this.appletInfo = appletInfo;
	}
    
	public void performAciotn(Ball b){
		Random r = new Random();
		appletInfo.setScore(appletInfo.getScore() + 500 + r.nextInt(2000));
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.CYAN);
		super.paint(g);
	}
}
