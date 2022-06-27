import  java.awt.*;
import  java.awt.event.*;

public
class PositionCheckerYamaguchi extends MouseAdapter implements PaintComponent{
	boolean  state = false;
	boolean  entered = false;
	IntCheckList  list1 = new IntCheckList();
	IntCheckList  list2 = new IntCheckList();
	
	Huji  frame = null;
	int x;
	int y;

	Window  window = null;

	public PositionCheckerYamaguchi(Huji frame,int x,int y){
		this.frame = frame;
		this.x = x;
		this.y = y;

		IntPointCheckItem xcheck = new IntPointCheckItem(x);
		IntPointCheckItem ycheck = new IntPointCheckItem(y);

		list1.add(xcheck);
		list2.add(ycheck);

		//setImage();
	}

	public void setWindow(Window window){
		this.window = window;
	}

	void setImage(){
		//this.image = frame.loadImage(this.photo);
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
					window.setVisible(true);
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

	@Override
	public void paint(Graphics g){
		if(state){
			g.drawRect(x-10,y-10,20,20);
		}
		else{
			g.fillRect(x-10,y-10,20,20);
		}
	}
}
