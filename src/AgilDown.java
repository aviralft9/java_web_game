import java.awt.Color;
import java.awt.Graphics;


public class AgilDown extends Item {

	public AgilDown(int x) {
		super(x);
		// TODO Auto-generated constructor stub
	}

	 public void performAction(Ball b){
		 if(b.getAgility() >= 2){
		 b.setAgility(b.getAgility() - 1);
	 }
	 }
	@Override
	public void paint(Graphics g) {
	
		   g.setColor(Color.RED);
		// TODO Auto-generated method stub
		super.paint(g);
	}
}
