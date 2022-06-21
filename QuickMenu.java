import java.awt.*;
import java.awt.event.*;



public class QuickMenu extends Frame{
    Frame frame  = new Frame();
    Button btn1  = new Button("混雑予測");
    Button btn2  = new Button("QR読み取り");
    Button btn3  = new Button("観覧エリアを写真から選択");
    Button ebtn  = new Button("EXIT");
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


    public QuickMenu(){
        setBounds(0,0,1230,800);
        setLayout(null);

        initmenu();
        setMbar();

        setVisible(true);
    }

    public void initmenu(){
        add(btn1);
        btn1.setBounds(140,200,250,150);
        
        add(btn2);
        btn2.setBounds(490,200,250,150);

        add(btn3);
        btn3.setBounds(840,200,250,150);

        add(olist);
        olist.setBounds(140,450,950,300);

        add(ebtn);
        ebtn.setBounds(990,760,100,20);

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
        listeners[0] = (ActionEvent e)->{;};	// 
		listeners[1] = null;								// ---
		listeners[2] = (ActionEvent e)->{;};		// 
        setMenu(regiMenu,regiMenuItems,listeners);
        }
        {
            ActionListener [] listeners = new ActionListener [listMenuItems.length];
            listeners[0] = (ActionEvent e)->{;};	// 
            listeners[1] = null;								// ---
            listeners[2] = (ActionEvent e)->{;};		// 
            setMenu(listMenu,listMenuItems,listeners);
        }
        {
            ActionListener [] listeners = new ActionListener [watchMenuItems.length];
            listeners[0] = (ActionEvent e)->{;};	// 
            listeners[1] = null;								// ---
            listeners[2] = (ActionEvent e)->{;};		// 
            setMenu(watchMenu,watchMenuItems,listeners);
        }

        setMenuBar(mbar);
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
        new QuickMenu();        
    }
}
