import java.awt.*;
import java.awt.event.*;



public class ManageMenu extends Frame implements Closable{
    Frame frame  = new Frame();
    Button btn1  = new Button("コメント管理");
    Button btn2  = new Button("お知らせ管理");
    Button btn3  = new Button("写真管理");
    Button btn4  = new Button("混雑管理");
    Button btn5  = new Button("マスターデータの登録");
    Button btn6  = new Button("EXIT");

    Label label1  = new Label("MANAGEMENT MENU"); 


    public ManageMenu(){
        setBounds(0,0,1230,500);
        setLayout(null);

        initmenu();

        addWindowListener(new Closer(this));

        setVisible(true);
    }

    public void initmenu(){
        add(btn1);
        btn1.setBounds(140,100,250,150);
        btn1.addActionListener((ActionEvent e)->{new ComentManage(this);});
        
        add(btn2);
        btn2.setBounds(490,100,250,150);
        btn2.addActionListener((ActionEvent e)->{new NewsManage(this);});

        add(btn3);
        btn3.setBounds(840,100,250,150);
        btn3.addActionListener((ActionEvent e)->{close();});

        add(btn4);
        btn4.setBounds(140,270,250,150);
        btn4.addActionListener((ActionEvent e)->{new CrowdManage(this);});

        add(btn5);
        btn5.setBounds(490,270,250,150);
        btn5.addActionListener((ActionEvent e)->{new MasterManage(this);});

        add(btn6);
        btn6.setBounds(840,270,250,150);
        btn6.addActionListener((ActionEvent e)->{close();});

        add(label1);
        label1.setBounds(20,60,200,20);

 
    }

    @Override
    public void close(){
        setVisible(false);
        dispose();
    }



    public static void main(String args[]){
        new ManageMenu();        
    }
}
