import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

import java.io.*;

public class PhotoDialog extends Dialog implements Closable{
    Panel photopanel = new Panel();
    Label label1     = new Label("写真をクリック");
    Button btn1      = new Button("EXIT");
    Button btn2      = new Button("混雑状況を見る");
    Image image      = null;

    ArrayList <Integer> xlist = new ArrayList <Integer> ();
    ArrayList <Integer> ylist = new ArrayList <Integer> ();
    ArrayList <Integer> wlist = new ArrayList <Integer> ();
    ArrayList <Integer> hlist = new ArrayList <Integer> ();
    ArrayList <Photo> photolist = new ArrayList <Photo> ();
    ArrayList <String> desklist = new ArrayList <String> ();

    String [] photofiles = new String [4];
    Image [] photos      = null;

    int count = 0;


    public PhotoDialog(Frame parent){
        super(parent,"PhotoDialog",true);
        setBounds(0,0,1230,800);
        setLayout(null);

        {
        add(photopanel);
        photopanel.setBounds(20,60,768,720);
        photopanel.setLayout(null);
        photopanel.setBackground(Color.lightGray);
        }

        add(btn1);
        btn1.setBounds(1050,700,100,40);
        btn1.addActionListener((ActionEvent e)->{close();});

        add(btn2);
        btn2.setBounds(850,700,150,40);
        btn2.addActionListener((ActionEvent e)->{close();});

        add(label1);
        label1.setBounds(20,40,100,20);

        addWindowListener(new Closer(this));
        addWindowListener(new WindowAdapter(){
            public void windowOpened(WindowEvent e){
                loadRects();
                loadPhotos();
                System.out.println(photolist.size());
                for(count = 0;count<photolist.size();count++){
                    System.out.println(count);
                    photofiles[count] = photolist.get(count).pfile;
                    System.out.println(photolist.get(count).pfile);
                    loadImage();
                }
            }
        });
        //addMouseListener(new MouseAdapter(){
          //  public void mouseClicked(MouseEvent e){

            //}
       // });

        setVisible(true);
    }
    
    @Override
    public void close(){
        setVisible(false);
        dispose();
    }

    public void loadRects(){
        try{
            FileInputStream fin   = new FileInputStream("rect.csv");
            InputStreamReader is  = new InputStreamReader(fin);
            BufferedReader reader = new BufferedReader(is);
            while(true){
                String gyo = reader.readLine();
                if(gyo == null){
                    break;
                }
                    String [] rps = gyo.split(",");
                try{
                    Integer x1 = Integer.valueOf(rps[0]);
                    Integer y1 = Integer.valueOf(rps[1]);
                    Integer w1 = Integer.valueOf(rps[2]);
                    Integer h1 = Integer.valueOf(rps[3]);

                    xlist.add(x1);
                    ylist.add(y1);
                    wlist.add(w1);
                    hlist.add(h1);

                }
                catch(NumberFormatException ne){
                    System.out.println(ne.toString());
                }
                reader.close();
                is.close();
                fin.close();
            }
        }    
        catch(Exception e){
            System.out.println(e.toString());
        }
         
    }

    public void loadPhotos(){
        try{
            FileInputStream fin   = new FileInputStream("photos.csv");
            InputStreamReader is  = new InputStreamReader(fin);
            BufferedReader reader = new BufferedReader(is);
            while(true){
                String photoinfo = reader.readLine();
                if(photoinfo == null){
                    break;
                }
                    String [] info = photoinfo.split(",");
                try{
                    String pfile = info[0];
                    double plat  = Double.valueOf(info[1]).doubleValue();
                    double plon  = Double.valueOf(info[2]).doubleValue();

                    Photo p = new Photo(pfile,plat,plon);
                     

                    photolist.add(p);
                }
                catch(NumberFormatException ne){
                    System.out.println(ne.toString());
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

    void loadImage(){
		if(this.image == null){
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			this.photos = new Image [photofiles.length];

				photos[count] = toolkit.getImage(photofiles[count]);			
			try{
				MediaTracker mt = new MediaTracker(this);
					mt.addImage(photos[count],count);
					mt.waitForID(count);
				}
				
			catch(Exception e){
				System.out.println("ERROR");
			}
			drawPhoto(xlist.get(count).intValue(),ylist.get(count).intValue()
                                        ,wlist.get(count).intValue(),hlist.get(count).intValue());
		}
	}

    public void drawPhoto(int x,int y,int w,int h){
        Image screen = photopanel.createImage(w,h);

        Graphics g = screen.getGraphics();
        g.setColor(Color.white);
        g.drawRect(x,y,w,h);
        g.drawImage(photos[count],0,0,this);

        this.image = screen;
        repaint();
    }

    public void paint(Graphics g){
        if(this.image != null){
            g.drawImage(this.image,20,60,this);
        }
    }

    public static void main(String args[]){
        Frame frame = new Frame();
        frame.setBounds(0,0,200,200);
        frame.setVisible(true);

        new PhotoDialog(frame);
    }
}
