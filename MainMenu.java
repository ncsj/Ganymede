import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class MainMenu extends Frame implements Closable{

	FileIO fio = null;
    static final String fname = "News.csv";

	ArrayList <String []> slist = new ArrayList <String []> ();

    Frame frame  = new Frame();
    Button btn1  = new Button("混雑状況");
    Button btn2  = new Button("QR読み取り");
    Button btn3  = new Button("観覧エリアを写真から選択");
    Button ebtn  = new Button("EXIT");
    Button btn4  = new Button("コメントを見る");
    Button btn5  = new Button("コメントを投稿する");
    Button btn6  = new Button ("観覧エリアを要素から選択");

    MenuBar mbar = new MenuBar();
    Label label1  = new Label("QUICK MENU"); 
    Label label2  = new Label("お知らせ");

    Menu	regiMenu	= new Menu("登録する");
	Menu	listMenu	= new Menu("情報を見る");
	Menu	watchMenu	= new Menu("どこで観覧する？？");

	String [] regiMenuItems = {"バーコード読み取り","-","コメント登録"};
	String [] listMenuItems = {"混雑状況","-","コメントリスト"};
	String [] watchMenuItems = {"写真から探す","-","要素から探す"};

    List olist  = new List();


    public MainMenu(){
        setBounds(0,0,1230,800);
        setLayout(null);

		fio = new FileIO(fname);
        slist = fio.getFileDataArrayList();

        initmenu();
        setMbar();

        addWindowListener(new Closer(this));

        setVisible(true);
    }

    public void initmenu(){
        add(btn1);
        btn1.setBounds(140,100,250,150);
		btn1.addActionListener((ActionEvent e)->{ new Huji(this); });
        
        add(btn2);
        btn2.setBounds(490,100,250,150);
        btn2.addActionListener((ActionEvent e)->{new CodeDialog(this);});

        add(btn3);
        btn3.setBounds(840,100,250,150);
        btn3.addActionListener((ActionEvent e)->{new PhotoDialog2(this);});

		String [] s;
        for(int i=0;i<slist.size();i++){
            s = slist.get(i);

            String ss = s[0] +" , "+ s[1];
            olist.add(ss);
        }
        add(olist);
        olist.setBounds(140,450,950,300);

        add(btn4);
        btn4.setBounds(140,270,250,150);
        btn4.addActionListener((ActionEvent e)->{new ComentList();});

        add(btn5);
        btn5.setBounds(490,270,250,150);
        btn5.addActionListener((ActionEvent e)->{close();});

        add(btn6);
        btn6.setBounds(840,270,250,150);
        btn6.addActionListener((ActionEvent e)->{new YousoDialog(this);});

        add(ebtn);
        ebtn.setBounds(990,760,100,20);
        ebtn.addActionListener((ActionEvent e)->{close();});

        add(label1);
        label1.setBounds(20,60,100,20);

        add(label2);
        label2.setBounds(140,430,100,20);
    }

    public void setMbar(){
        mbar.add(regiMenu);
        mbar.add(listMenu);
        mbar.add(watchMenu);
        {
        ActionListener [] listeners = new ActionListener [regiMenuItems.length];
        listeners[0] = (ActionEvent e)->{new CodeDialog();};	// 
		listeners[1] = null;								// ---
		listeners[2] = (ActionEvent e)->{;};		// 
        setMenu(regiMenu,regiMenuItems,listeners);
        }
        {
            ActionListener [] listeners = new ActionListener [listMenuItems.length];
            listeners[0] = (ActionEvent e)->{ new MapFrame();};	// 
            listeners[1] = null;								// ---
            listeners[2] = (ActionEvent e)->{new ComentList();};		// 
            setMenu(listMenu,listMenuItems,listeners);
        }
        {
            ActionListener [] listeners = new ActionListener [watchMenuItems.length];
            listeners[0] = (ActionEvent e)->{new PhotoDialog2();};	// 
            listeners[1] = null;								// ---
            listeners[2] = (ActionEvent e)->{new Yousodialog();};		// 
            setMenu(watchMenu,watchMenuItems,listeners);
        }

        setMenuBar(mbar);
    }

    @Override
    public void close(){
        setVisible(false);
        dispose();
    }

    void setMenu(Menu menu,String [] items,ActionListener [] listeners){
		for(int i=0;i<items.length;i++){
			MenuItem  menuItem = new MenuItem(items[i]);
			if(listeners[i] != null){
				menuItem.addActionListener(listeners[i]);
			}
			menu.add(menuItem);
		}
	}

    public static void main(String args[]){
        new MainMenu();        
    }
}
