import  java.awt.*;
import  java.awt.event.*;

public
class PositionChecker extends MouseAdapter implements PaintComponent{
	boolean  isSelected = true;
	IntCheckList  list1 = new IntCheckList();
	IntCheckList  list2 = new IntCheckList();

	PhotoDialog2  frame = null;
	int x;
	int y;
	int w;
	int h;
	Color  color = null;

	String photo = null;
	Image image = null;

	Window  window = null;

	public PositionChecker(PhotoDialog2 frame,
							int x,int y,int w,int h,String photo){
		this.frame = frame;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.photo = photo;

		IntOverCheckItem xcheck1 = new IntOverCheckItem(x);
		IntUnderCheckItem xcheck2 = new IntUnderCheckItem(x+w);

		IntOverCheckItem ycheck1 = new IntOverCheckItem(y);
		IntUnderCheckItem ycheck2 = new IntUnderCheckItem(y+h);

		list1.add(xcheck1);
		list1.add(xcheck2);

		list2.add(ycheck1);
		list2.add(ycheck2);

		setImage();
	}

	public void setWindow(Window window){
		this.window = window;
	}

	void setImage(){
		this.image = frame.loadImage(this.photo);
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
			if(isSelected){
			}
			else{
				if(window != null){
					window.setVisible(true);
				}
				isSelected = true;
			}
			frame.repaint();
		}
		else{
				isSelected = false;
				if(window != null){
					window.setVisible(false);
				}
			frame.repaint();
		}
	}

	@Override
	public void paint(Graphics g){
		g.setColor(Color.red);
		g.drawImage(image,x,y,frame);
		if(isSelected){
			Graphics2D g2 = (Graphics2D)g;
			BasicStroke bs = new BasicStroke(2);
			g2.setStroke(bs);
			g.drawRect(x,y,w,h);
		}
	}
}
