import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class InputMapFile extends Frame{
	static ArrayList <Point> plist= new ArrayList <Point> ();
	static ArrayList <Item> list = new ArrayList <Item> ();

	Double maxLat;
	Double maxLon;
	Double minLat;
	Double minLon;

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	
	Properties props = System.getProperties();
	public String HOME = props.getProperty("user.dir") + "/";
	

	Image map;

	public InputMapFile(){
		InputMap();
		calcMinMax();

		//setLayout(null);
		//setBounds(0,0,0,0);
		//setVisible(true);
	}

	public ArrayList <Point> getMAPXY(int offset_x,int offset_y,int w,int h){
		calcXY(offset_x,offset_y,w,h);
		return this.plist;
    }

	public Point getXY(Double lat,Double lon,int offset_x,int offset_y,int w,int h){
		lon = (((lon - minLon) / ( maxLon - minLon)) * w) + offset_x;
        lat = (((lat - maxLat) / (minLat - maxLat)) * h) + offset_y;
		int y = lon.intValue();
        int x = lat.intValue();
		Point point = new Point(x,y);
		return point;
	}

	public Image getMapImage(int w,int h){
		calcXY(0,0,w,h);
		setLayout(null);
        setBounds(0,0,1200,800);
        setVisible(true);
		Image screen = createImage(w,h);
        Graphics g = screen.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,w,h);
        if(plist.size() > 0){
            g.setColor(Color.pink);
            for(int i=0;i<plist.size()-1;i++){
                Point sp = plist.get(i);
                Point ep = plist.get(i+1);
                int wb = 4;
                int hb = 4;
                g.fillOval(sp.x-wb/2,sp.y-hb/2,wb,hb);
                g.fillOval(ep.x-wb/2,ep.y-hb/2,wb,hb);
            }
            this.map = screen;
        }
		setVisible(false);
		dispose();
		return this.map;
	}

	public void InputMap(){
		File file = new File(HOME + "FSWGPS/FSWMAP.txt"); // この中でパスを持っている

		String path = file.getAbsolutePath(); //絶対パスをとる

		try{
			FileInputStream fin = new FileInputStream(file);

			InputStreamReader is = new InputStreamReader(fin); //1文字ずつ
			BufferedReader reader = new BufferedReader(is); //Readerをとる

			while(true){
                String line = reader.readLine();
                if(line != null){
                    String [] iti = line.split(",");
					Item item = new Item(iti);
					this.list.add(item);
                }
                else{
                    break;
                }
            }
			
			is.close();
            fin.close();
		}
		catch(FileNotFoundException e){
            System.out.println("There is no file : " + file.toString());
        }
        catch(IOException e){
            System.out.println(e.toString());
        }
	}

	public void calcMinMax(){
		Item item = null;
		for(int i=0;i<list.size();i++){
			item = list.get(i);
			
			if(i==0){
				this.maxLat = item.ido;
				this.minLat = item.ido;
				this.maxLon = item.keido;
				this.minLon = item.keido;
			}
            
            if(item.keido > this.maxLon){
				this.maxLon = item.keido;
			}
			if(item.ido > this.maxLat){
                this.maxLat = item.ido;
            }
			if(item.keido < this.minLon){
                this.minLon = item.keido;
            }
			if(item.ido < this.minLat){
                this.minLat = item.ido;
            }
        }
	}

	public void calcXY(int offset_x,int offset_y,int w,int h){
		int x;
		int y;
		Item item= null;
		for(int i=0;i<list.size();i++){
			item = list.get(i);
            item.keido = (((item.keido - minLon) / ( maxLon - minLon)) * w) + offset_x;
            item.ido = (((item.ido - maxLat) / (minLat - maxLat)) * h) + offset_y;
            y = item.ido.intValue();
            x = item.keido.intValue();
            Point point = new Point(x,y);
            plist.add(point);
        }
	}

	public void toWritePList(){
		for(int i=0;i<plist.size();i++){
			Point point = plist.get(i);
			System.out.println(point.x + "," + point.y);
		}
	}

	public void toWriteList(){
        for(int i=0;i<list.size();i++){
            Item item = list.get(i);
            System.out.println(item.keido + "," + item.ido);
        }
    }

	public void toWriteMINMAX(){
        System.out.println("minLON :" + this.minLon);
		System.out.println("maxLON :" + this.maxLon);
		System.out.println("minLAT :" + this.minLat);
		System.out.println("maxLAT :" + this.maxLat);
    }

	@Override
    public void paint(Graphics g){
        g.drawImage(this.map,0,0,this);
	}

	public static void main(String args[]){
		InputMapFile imf = new InputMapFile();
		Image img = imf.getMapImage(1200,800);
		imf.repaint();
	}
}
