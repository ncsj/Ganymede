import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Properties;

import java.io.*;

public class PhotoDialog2 extends Dialog implements Closable{
    Panel photopanel = new Panel();
    Label label1     = new Label("写真をクリック");
	Label label2     = new Label("あなたが選択した写真は.....");
    Button btn1      = new Button("EXIT");
    Button btn2      = new Button("混雑状況を見る");
    Image image      = null;

    PositionChecker [] checkers		= null;
	PaintComponent [] comps			= null;
	MouseAdapter [] mouseListeners = null;

	boolean isReady = false;

	public static String HOME = null;
	public static final String Ganymede = "/Ganymede/";

	static{
		Properties props = System.getProperties();
		HOME = props.getProperty("user.dir") + "/";
	}

	ArrayList <Integer> xlist = new ArrayList <Integer> ();		// X
	ArrayList <Integer> ylist = new ArrayList <Integer> ();		// Y
	ArrayList <Integer> wlist = new ArrayList <Integer> ();		// Width
	ArrayList <Integer> hlist = new ArrayList <Integer> ();		// Hight
	ArrayList <String>	plist = new ArrayList <String> ();		// Photo(Image) File
	ArrayList <Double>	latlist = new ArrayList <Double> ();		// Desc File
	ArrayList <Double>  lonlist = new ArrayList <Double> ();

	public PhotoDialog2(Frame parent){
        super(parent,"写真から選択",true);
		setBounds(0,0,1230,850);
		setLayout(null);

		loadContents();
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

		
	
		add(btn1);
		btn1.setBounds(1050,700,100,40);
		btn1.addActionListener((ActionEvent e)->{close();});
	
		add(btn2);
		btn2.setBounds(850,700,150,40);
		btn2.addActionListener((ActionEvent e)->{close();});
	
		add(label1);
		label1.setBounds(20,40,100,20);

		add(label2);
		label2.setBounds(900,130,200,20);

		setVisible(true);
	}


	void setReady(){
		isReady = true;
		System.out.println("setReady()");
		repaint();
	}

	int image_count = 0;
	public Image loadImage(String photo){
		Image image = null;
		try{
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			image = toolkit.getImage(photo);

			MediaTracker mt = new MediaTracker(this);
			mt.addImage(image,image_count);
			mt.waitForID(image_count);

			image_count++;
		}
		catch(Exception e){
		}
		System.out.println("loadImage()");
		return image;
	}

	void loadContents(){
		try{
			FileInputStream fin = new FileInputStream(HOME + "rect.csv");
			FileInputStream fin2 = new FileInputStream(HOME + "Photos.csv");
			InputStreamReader is = new InputStreamReader(fin);
			InputStreamReader is2 = new InputStreamReader(fin2);
			BufferedReader reader = new BufferedReader(is);
			BufferedReader reader2 = new BufferedReader(is2);

			while(true){
				String line = reader.readLine();
				String line2 = reader2.readLine();
				if(line == null || line2 == null){
					break;
				}

				String [] cols = line.split(",");
				String [] cols2 = line2.split(",");
				try{
					Integer ix = Integer.valueOf(cols[0]);
					Integer iy = Integer.valueOf(cols[1]);
					Integer iw = Integer.valueOf(cols[2]);
					Integer ih = Integer.valueOf(cols[3]);

					xlist.add(ix);
					ylist.add(iy);
					wlist.add(iw);
					hlist.add(ih);

					plist.add(cols2[0]);
					latlist.add(Double.valueOf(cols2[1]).doubleValue());
					lonlist.add(Double.valueOf(cols2[2]).doubleValue());

				}
				catch(NumberFormatException ne){
					;
				}
			}
			reader2.close();
			reader.close();
			is2.close();
			is.close();
			fin2.close();
			fin.close();
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		System.out.println("loadContents()");
	}

	void initChecker(){
		this.mouseListeners = new MouseAdapter [plist.size()];
		this.comps			= new PaintComponent [plist.size()];
		this.checkers		= new PositionChecker [plist.size()];

		for(int i=0;i<plist.size();i++){
			int x			= xlist.get(i).intValue();
			int y			= ylist.get(i).intValue();
			int w			= wlist.get(i).intValue();
			int h			= hlist.get(i).intValue();
			String photo	= plist.get(i);
			Double lat		= latlist.get(i);
			Double lon      = lonlist.get(i);


			PositionChecker checker = new PositionChecker(this,x,y,w,h,photo);

			MapWindow window = new MapWindow(this,lat,lon);
			window.setBounds(820,200,400,480);
			checker.setWindow(window);

			this.checkers[i] = checker;
			this.comps[i] = checker;
			this.mouseListeners[i] = checker;
		}
		System.out.println("initChecker()");
	}


	public void paint(Graphics g){
		g.setColor(Color.lightGray);
		g.fillRect(20,60,788,745);

		if(isReady){
			if(this.comps != null){
				for(PaintComponent comp : this.comps){
					System.out.println("Paint OK");
					comp.paint(g);
				}
			}
		}
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

        new PhotoDialog2(frame);
    }
}
