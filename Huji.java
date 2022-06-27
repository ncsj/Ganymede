import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Huji extends Dialog implements Closable{
	MainMenu mm = null;

	static ArrayList <Point> plist= new ArrayList <Point> ();
	InputMapFile imf = null;
	static int w = 1200;
	static int off_set = 50;
	static int h = 600;

	Konzatsu konzatsu = null;
	boolean isReady = false;
	public static String HOME = null;
	Image imap = null;

	static{
        Properties props = System.getProperties();
        HOME = props.getProperty("user.dir");
    }

	PositionCheckerYamaguchi [] checkers     = null;
	PaintComponent [] comps         = null;
	MouseAdapter [] mouseListeners = null;

	ArrayList <String> slist = new ArrayList <String> ();
    ArrayList <Integer> idlist = new ArrayList <Integer> ();
    ArrayList <Double> xlist = new ArrayList <Double> ();
    ArrayList <Double> ylist = new ArrayList <Double> ();

	public Huji(Frame parent){
		// modeless dialog box
        super(parent,"混雑状況",true);
        if(parent instanceof MainMenu){
            this.mm = (MainMenu)parent;
        }
		Konzatsu konzatsu = new Konzatsu();
		InputMapFile imf = new InputMapFile();
		Image img = imf.getMapImage(w,h);
        this.imap = img;
		this.imf = imf;
		this.konzatsu = konzatsu;
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0,0,w,off_set+h);

		loadRectangles();
        initChecker();

		for(MouseAdapter l : mouseListeners){
            addMouseListener( l );
            addMouseMotionListener( l );
        }

		addWindowListener(new Closer(this));
		addWindowListener(new WindowAdapter(){
            @Override
            public void windowOpened(WindowEvent e){
                setReady();
            }
        });
		setVisible(true);
		repaint();
	}

	@Override
	public void paint(Graphics g){
		g.drawImage(this.imap,0,off_set,this);

		if(isReady){
			if(this.comps != null){
				for(PaintComponent comp : this.comps){
					comp.paint(g);
				}
			}
		}
	}

	void setReady(){
        isReady = true;
    }

	void initChecker(){
        this.mouseListeners = new MouseAdapter [xlist.size()];
		this.comps          = new PaintComponent [xlist.size()];
        this.checkers       = new PositionCheckerYamaguchi [xlist.size()];

        for(int i=0;i<xlist.size();i++){
            String str      = slist.get(i);
            int id          = idlist.get(i).intValue();
            Double ix       = xlist.get(i);
            Double iy		= ylist.get(i);
			Point p = imf.getXY(iy,ix,0,off_set,w,h);

			PositionCheckerYamaguchi checker = new PositionCheckerYamaguchi(this,p.x,p.y,str,id,konzatsu);

            // DescWindow window = new DescWindow(this,desc);
            Window window = new Window(this);
            checker.setWindow(window);

            this.checkers[i] = checker;
			this.comps[i] = checker;
            this.mouseListeners[i] = checker;
        }
    }

	void loadRectangles(){
		try{
			FileInputStream fin = new FileInputStream(HOME + "/MasterData.csv");
			InputStreamReader is = new InputStreamReader(fin);
			BufferedReader reader = new BufferedReader(is);

			while(true){
				String line = reader.readLine();
				if(line == null){
					break;
				}

				String [] cols = line.split(",");
				try{
					String str = cols[0];
					Integer id = Integer.valueOf(cols[1]);
					Double iy = Double.valueOf(cols[2]);
					Double ix = Double.valueOf(cols[3]);
					slist.add(str);
					idlist.add(id);
					xlist.add(ix);
					ylist.add(iy);
				}
				catch(NumberFormatException ne){
					;
				}
			}
			reader.close();
			is.close();
			fin.close();
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}

	@Override
	public void close(){
		setVisible(false);
		dispose();
	}

	/**
      Test Code
    **/
    public static void main(String args[]){
        Frame frame = new Frame();
        frame.setBounds(100,100,100,100);
        frame.setVisible(true);
        new Huji(frame);
    }
}
