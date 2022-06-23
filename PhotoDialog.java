import java.awt.*;
import java.awt.event.*;

public class PhotoDialog extends Dialog implements Closable{
    
    public PhotoDialog(Frame parent){
        super(parent,"PhotoDialog",true);

        addWindowListener(new Closer(this));

        setVisible(true);
    }
    
    @Override
    public void close(){
        setVisible(false);
        dispose();
    }

    public static void main(String args[]){
        Frame frame = new Frame();
        frame.setBounds(0,0,200,200);
        frame.setVisible(true);

        new PhotoDialog(frame);
    }
}
