import  java.awt.event.*;

class Closer extends WindowAdapter{
	Closable  target = null;
	
	public Closer(Closable target){
		this.target = target;
	}

	@Override
	public void windowClosing(WindowEvent e){
		target.close();
	}
}
