import  java.awt.*;
import  java.awt.event.*;

import  java.util.ArrayList;

public
class CrowdManage extends Dialog implements Closable{
	ManageMenu mm = null;
	
	

	Label		label1 = new Label("表示対象");
	Label       label2 = new Label("表示過去データ");
	Label		label3 = new Label("名称");
	List		list1 = new List();
	List		list2 = new List();
	TextField	text1 = new TextField();
	Button		btn1  = new Button("編集");
	Button		btn2  = new Button("削除");
	Button      btn3  = new Button("追加");
	Button      btn4  = new Button("終了");

	public CrowdManage(Frame parent){
		// modeless dialog box
		super(parent,"混雑の管理",true);
		if(parent instanceof ManageMenu){
			this.mm = (ManageMenu)parent;
		}
			
		setBounds(400,200,800,600);
		setLayout(null);

		initControls();

		addWindowListener(new Closer(this));
		setVisible(true);
	}

	void initControls(){
		int x1 = 50;
		int x2 = 150;
		int x3 = 630;
		int x4 = 720;

		int y1 = 50;
		int y2 = 230;
		int y3 = 280;
		int y4 = 460;
		int y5 = 530;
		int y6 = 540;
		
		add(label1);
        label1.setBounds(x1,y1,100,40);

		add(label2);
        label2.setBounds(x1,y3,100,40);

		add(label3);
        label3.setBounds(x1,y6,100,40);

		list1.add("メインスタンド");
        list1.add("第一コーナースタンド");
        list1.add("コカコーラスタンド");
		add(list1);
		list1.setBounds(x2,y1,470,220);

		list2.add("4.9 SUPER FOMURA 2022");
        list2.add("5.4 AUTOBACKS SUPER GT");
        list2.add("9.9 FIA WEC / 世界耐久選手権");
        add(list2);
        list2.setBounds(x2,y3,470,220);

		add(text1);
		text1.setBounds(x2,y6,470,40);

		add(btn1);
		btn1.setBounds(x3,y2,70,40);
		btn1.addActionListener((ActionEvent e)->{ checkSelectedItem(); });
		
		add(btn2);
		btn2.setBounds(x3,y4,70,40);
		btn2.addActionListener((ActionEvent e)->{ close(); });

		add(btn3);
        btn3.setBounds(x3,y6,70,40);
        btn3.addActionListener((ActionEvent e)->{ close(); });

		add(btn4);
        btn4.setBounds(x4,y5,70,50);
        btn4.addActionListener((ActionEvent e)->{ close(); });
	}

	public void checkSelectedItem(){
		int index = list1.getSelectedIndex();
		String item = list1.getSelectedItem();
		System.out.println(index + "is " + item);
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
		new CrowdManage(frame);
	}
}
