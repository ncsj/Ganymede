import  java.awt.*;
import  java.awt.event.*;

import java.util.ArrayList;

public
class PositionCheckerYamaguchi extends MouseAdapter implements PaintComponent{
	boolean  state = false;
	boolean  entered = false;
	IntCheckList  list1 = new IntCheckList();
	IntCheckList  list2 = new IntCheckList();
	PaintComponent comp = null;
	PaintComp2 comp2 = null;

	Huji  frame = null;
	Konzatsu konzatsu = null;
	int x;
	int y;
	String str;
	int id;
	int now;
	int bef;
	boolean isReady = false;

	Window  window = null;

	public PositionCheckerYamaguchi(Huji frame,int x,int y,String str,int id,Konzatsu konzatsu){
		this.frame = frame;
		this.x = x;
		this.y = y;
		this.str = str;
		this.id = id;
		this.konzatsu = konzatsu;

		IntPointCheckItem xcheck = new IntPointCheckItem(x);
		IntPointCheckItem ycheck = new IntPointCheckItem(y);

		list1.add(xcheck);
		list2.add(ycheck);
	}

	public void setWindow(Window window){
		this.window = window;
	}

	public boolean check(int x,int y){
		boolean rtc = false;
		try{
			if(list1.check(x) && list2.check(y)){
				rtc = true;
			}
			else{
				rtc = false;
			}
		}
		catch(CheckItemException e){
		}

		return rtc;
	}

	@Override
	public void mouseClicked(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(check(x,y)){
			if(state){
				state = false;
			}
			else{
				state = true;
			}
				
			frame.repaint();
		}
	}


	@Override
	public void mouseMoved(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(check(x,y)){
			if(entered){
			}
			else{
				if(window != null){
					if(x < 200){
						x = x + 200;
					}
					if(y < 320){
						y = y + 370;
					}
					now = konzatsu.countOneDay(id);
					bef = konzatsu.countOneDay(id,7);
					System.out.println(now + "," +bef);
					this.comp2 = new PaintComp2(now,bef);
					window.setBounds(x-200,y-320,400,300);
					init(window);
					comp = comp2;
					window.setVisible(true);
					window.repaint();
				}
				entered = true;
			}
		}
		else{
			if(entered){
				if(window != null){
					window.setVisible(false);
				}
				entered = false;
			}
		}
	}

	public void init(Window window){
		window.setLayout(null);
		Label label1 = new Label("混雑状況",Label.CENTER);
		Label label2 = new Label(str,Label.CENTER);
		window.add(label1);
		label1.setBounds(170,20,60,20);
		window.add(label2);
		label2.setBounds(100,250,200,40);
	}

	@Override
	public void paint(Graphics g){
		g.setColor(Color.blue);
		if(state){
			g.drawRect(x-10,y-10,20,20);
		}
		else{
			g.fillRect(x-10,y-10,20,20);
		}
		if(this.comp != null){
            comp.paint(g);
        }
	}
}
