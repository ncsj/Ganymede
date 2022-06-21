import java.awt.*;
import java.awt.event.*;



public class QuickMenu extends Frame{
    Frame frame = new Frame();
    Button btn1 = new Button("混雑予測");
    Button btn2 = new Button("QR読み取り");
    Button btn3 = new Button("観覧エリアを写真から選択");
    Button ebtn = new Button("EXIT");
    MenuBar mbar = new MenuBar();

    Menu	regiMenu	= new Menu("登録");
	Menu	listMenu	= new Menu("リスト");
	Menu	watchMenu	= new Menu("どこで見る？？");

	String [] regiMenuItems = {"バーコード読み取り","-","コメント登録"};
	String [] listMenuItems = {"混雑状況","-","コメントリスト"};
	String [] watchMenuItems = {"写真から探す","-","要素から探す"};

    List olist  = new List();


    public QuickMenu(){
        setBounds(0,0,1230,800);
        setLayout(null);

        initmenu();

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
        }
    }

    public static void main(String args[]){
        new QuickMenu();        
    }
}
