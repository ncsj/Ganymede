import  java.awt.*;
import  java.awt.event.*;

import  java.util.ArrayList;

public
class NewsManage extends Dialog implements Closable{
	ManageMenu mm = null;
	
	FileIO fio = null;
	static final String fname = "News.csv";

	ArrayList <String []> slist = new ArrayList <String []> ();

	Label		label1 = new Label("お知らせリスト");
	Label       label2 = new Label("カテゴリ");
	Label       label3 = new Label("期間");
	Label       label4 = new Label("本文");

	TextField   text1 = new TextField();

	List		list1 = new List();
	Choice		choice1 = new Choice();
	Choice		choice2 = new Choice();
	Button		btn1  = new Button("削除");
	Button		btn2  = new Button("追加");
	Button		btn3  = new Button("終了");
	String place;
	String time;
	String coment;

	int index;

	public NewsManage(Frame parent){
		// modeless dialog box
		super(parent,"お知らせの管理",true);
		if(parent instanceof ManageMenu){
			this.mm = (ManageMenu)parent;
		}

		fio = new FileIO(fname);
		slist = fio.getFileDataArrayList();

		setBounds(400,200,800,600);
		setLayout(null);

		initControls();

		addWindowListener(new Closer(this));
		setVisible(true);
	}

	void initControls(){
		int x1 = 50;
		int x2 = 120;
		int x3 = 680;
		int x4 = 740;

		int y1 = 50;
		int y2 = 90;
		int y3 = 350;
		int y4 = 400;
		int y5 = 450;
		int y6 = 500;
		int y7 = 550;
		
		add(label1);
        label1.setBounds(x1,y1,150,40);

		add(label2);
        label2.setBounds(x1,y4,60,40);
		
		add(label3);
        label3.setBounds(x1,y5,60,40);

		add(label4);
        label4.setBounds(x1,y6,60,40);

		String [] s;
        for(int i=0;i<slist.size();i++){
            s = slist.get(i);
            
            String ss = s[0] +" , "+ s[1];
            list1.add(ss);
        }
		add(list1);
		list1.setBounds(x1,y2,620,300);
		list1.addItemListener((ItemEvent e)->{ checkSelectedItem(); });

		choice1.add("客席");
		choice1.add("食堂");
		choice1.add("注意");
		add(choice1);
		choice1.setBounds(x2,y4,150,40);
		choice1.addItemListener((ItemEvent e)->{ checkSelectedItem(); });

		choice2.add("1日");
        choice2.add("1週間");
        choice2.add("1ヶ月");
		choice2.add("6ヶ月");
		choice2.add("1年");
		choice2.add("設定しない");
        add(choice2);
        choice2.setBounds(x2,y5,150,40);
        choice2.addItemListener((ItemEvent e)->{ checkSelectedItem(); });

		add(text1);
		text1.setBounds(x2,y6,550,40);

		add(btn1);
		btn1.setBounds(x3,y3,50,40);
		btn1.addActionListener((ActionEvent e)->{ delete(); });
		
		add(btn2);
		btn2.setBounds(x3,y6,50,40);
		btn2.addActionListener((ActionEvent e)->{ add(); });

		add(btn3);
        btn3.setBounds(x4,y7,50,50);
        btn3.addActionListener((ActionEvent e)->{ close(); });
	}

	public void checkSelectedItem(){
		index = list1.getSelectedIndex();
		place = choice1.getSelectedItem();
		place = "【" + place + "】";
		time = choice2.getSelectedItem();
	}

	public void add(){
        boolean ok = true;
        coment = text1.getText();
		
        if(coment == null || time == null || place == null){
			ok = false;
		}

		String [] s = new String[3];
		s[0] = place;
		s[1] = coment;
		s[2] = time;
		if(ok){
            list1.add(place + " , " + coment);
            fio.addFileDataArrayList(s);
        }
    }

	public void delete(){
		list1.remove(index);
		fio.removeFileDataArrayList(index);
	}

	@Override
	public void close(){
		fio.output();
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
		new NewsManage(frame);
	}
}
