import  java.awt.*;
import  java.awt.event.*;

public class PaintComp2 implements PaintComponent{
	int now;
	int bef;
	int nowbig = 0;
	double h;
	int h2;

	public PaintComp2(int now,int bef){
		this.now = now;
		this.bef = bef;
		System.out.println(now + bef);
		ref();
	}

	public void ref(){
		double now2 = (double)now;
		double bef2 = (double)bef;

		if(now == bef){
			nowbig = 0;
			h = 210;
		}
		else if(now > bef){
			nowbig = 1;
			h = bef/now*210;
		}
		else if(now < bef){
			nowbig = -1;
			h = now/bef*210;
		}
		h2 = (int)h;
		System.out.println(h2);
	}

	@Override
	public void paint(Graphics g){
		if(nowbig == 1){
			g.setColor(Color.blue);
			g.fillRect(220,40,60,210);
			g.setColor(Color.pink);
			g.fillRect(120,40+(210-h2),60,h2);
		}
		else if(nowbig == 0){
            g.setColor(Color.blue);
            g.fillRect(220,40,60,210);
            g.setColor(Color.pink);
            g.fillRect(120,40,60,210);
        }
		else if(nowbig == -1){
            g.setColor(Color.blue);
            g.fillRect(220,40+(210-h2),60,h2);
            g.setColor(Color.pink);
            g.fillRect(120,40,60,210);
        }
	}
}
