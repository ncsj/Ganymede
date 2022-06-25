import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Huji extends Frame{

	static ArrayList <Point> plist= new ArrayList <Point> ();
	static int w = 1200;
	static int off_set = 50;
	static int h = 600;
	static Graphics map;
	
	Toolkit toolkit = Toolkit.getDefaultToolkit(); //staticメソッド newしない

	boolean isReady = true;
	public static String HOME = null;
	Image imap = null;

	static{
        Properties props = System.getProperties();
        HOME = props.getProperty("user.dir");
    }

	PositionChecker [] checkers     = null;
	PaintComponent [] comps         = null;
	MouseAdapter [] mouseListeners = null;

	ArrayList <String> slist = new ArrayList <String> ();
    ArrayList <Integer> idlist = new ArrayList <Integer> ();
    ArrayList <Double> xlist = new ArrayList <Double> ();
    ArrayList <Double> ylist = new ArrayList <Double> ();

	public Huji(Double maxido,Double maxkeido,Double minido,Double minkeido){
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0,0,w,off_set+h);

		loadRectangles();
        initChecker(maxido,maxkeido,minido,minkeido);

		for(MouseAdapter l : mouseListeners){
            addMouseListener( l );
            addMouseMotionListener( l );
        }

		addWindowListener(new WindowAdapter(){
			@Override
            public void windowOpened(WindowEvent e){
                //setReady();
            }

			@Override
			public void windowClosing(WindowEvent e){
				close();
			}
		});

		setVisible(true);
		if(isReady){
			drawPhoto();
		}
		repaint();
	}

	void drawPhoto(){
		Image screen = createImage(w,off_set+h);
		Graphics g = screen.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0,0,w,off_set+h);
		if(plist.size() > 1){
			g.setColor(Color.black);
			for(int i=0;i<plist.size()-1;i++){
				Point sp = plist.get(i);
				Point ep = plist.get(i+1);
				int w = 4;
				int h = 4;
				g.fillOval(sp.x-w/2,sp.y-h/2,w,h);
				g.fillOval(ep.x-w/2,ep.y-h/2,w,h);
			}
			this.imap = screen;
		}
		repaint();
        isReady = false;
    }

	@Override
	public void paint(Graphics g){
		g.drawImage(this.imap,0,0,this);

        if(this.comps != null){
            for(PaintComponent comp : this.comps){
				System.out.println("OK");
			    comp.paint(g);
            }
        }
	}

	void initChecker(Double maxido,Double maxkeido,Double minido,Double minkeido){
        this.mouseListeners = new MouseAdapter [xlist.size()];
		this.comps          = new PaintComponent [xlist.size()];
        this.checkers       = new PositionChecker [xlist.size()];

        for(int i=0;i<xlist.size();i++){
            String str      = slist.get(i);
            int id          = idlist.get(i).intValue();
            Double ix       = xlist.get(i);
            Double iy		= ylist.get(i);
			Double dx		= ((ix - minkeido) / ( maxkeido - minkeido)) * w;
            Double dy		= (((iy - maxido) / (minido - maxido)) * h) + off_set;
			int y = dy.intValue();
            int x = dx.intValue();

            PositionChecker checker = new PositionChecker(this,x,y);

            // DescWindow window = new DescWindow(this,desc);
            Window window = new Window(this);
            window.setBounds(x+30,y+100,400,180);
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

	void close(){
		setVisible(false);
		dispose();
	}

	public static void main(String args[]){
		File file = new File("FSWGPS/FSWMAP.txt"); // この中でパスを持っている

		String path = file.getAbsolutePath(); //絶対パスをとる

		try{
			FileInputStream fin = new FileInputStream(file);

			InputStreamReader is = new InputStreamReader(fin); //1文字ずつ
			BufferedReader reader = new BufferedReader(is); //Readerをとる

			ArrayList <Item> list = new ArrayList <Item> ();

			while(true){
                String line = reader.readLine();
                if(line != null){
                    String [] iti = line.split(",");
					Item item = new Item(iti);
					list.add(item);
                }
                else{
                    break;
                }
            }
			
			is.close();
            fin.close();
			Item [] items = new Item [list.size()];
            for(int i=0;i<items.length;i++){
                items[i] = list.get(i);
            }
			Double maxido = items[0].ido;
			Double minido = items[0].ido;
			Double maxkeido = items[0].keido;
			Double minkeido = items[0].keido;
			for(int i=0;i<items.length;i++){
                if(items[i].keido > maxkeido){
					maxkeido = items[i].keido;
				}
				if(items[i].ido > maxido){
                    maxido = items[i].ido;
                }
				if(items[i].keido < minkeido){
                    minkeido = items[i].keido;
                }
				if(items[i].ido < minido){
                    minido = items[i].ido;
                }
            }

			int x;
			int y;

			for(Item i:items){
				i.keido = ((i.keido - minkeido) / ( maxkeido - minkeido)) * w;
				i.ido = (((i.ido - maxido) / (minido - maxido)) * h) + off_set;
				y = i.ido.intValue();
				x = i.keido.intValue();
				Point point = new Point(x,y);
				plist.add(point);
			}

			new Huji(maxido,maxkeido,minido,minkeido);
		}
		catch(FileNotFoundException e){
			System.out.println("There is no file : " + file.toString());
		}
		catch(IOException e){
			System.out.println(e.toString());
		}
	}
}

