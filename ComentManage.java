import  java.awt.*;
import  java.awt.event.*;

import  java.util.ArrayList;

public
class ComentManage extends Dialog implements Closable{
	ManageMenu mm = null;
	
	FileIO fio = null;
	static final String fname = "Coment.csv";

	ArrayList <String []> slist = new ArrayList <String []> ();

	Label		label1 = new Label("コメントリスト");
	Label       label2 = new Label("コメント存続期間");
	List		list1 = new List();
	Choice		choice1 = new Choice();
	Button		btn1  = new Button("削除");
	Button		btn2  = new Button("終了");
	int index;
	int comentType;
	String comentTypeName;

	public ComentManage(Frame parent){
		// modeless dialog box
		super(parent,"コメントの管理",true);
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
		int x2 = 250;
		int x3 = 700;

		int y1 = 50;
		int y2 = 80;
		int y3 = 400;
		int y4 = 520;
		
		add(label1);
        label1.setBounds(x1,y1,170,20);

		add(label2);
        label2.setBounds(x1,y3,170,20);
		
		String [] s;
		for(int i=0;i<slist.size();i++){
			s = slist.get(i);

			String ss = s[0];
			for(int n=1;n<s.length;n++){
				ss = ss + " , " + s[n];
			}
			list1.add(ss);
        }
		add(list1);
		list1.setBounds(x1,y2,640,300);
		list1.addItemListener((ItemEvent e)->{ checkSelectedItem(); });

		choice1.add("1週間");
		choice1.add("1ヶ月");
		choice1.add("1年間");
		add(choice1);
		choice1.setBounds(x2,y3,200,20);
		choice1.addItemListener((ItemEvent e)->{ checkSelectedItem(); });

		add(btn1);
		btn1.setBounds(x3,y3,60,40);
		btn1.addActionListener((ActionEvent e)->{ delete(); });
		
		add(btn2);
		btn2.setBounds(x3,y4,80,50);
		btn2.addActionListener((ActionEvent e)->{ close(); });
	}

	public void checkSelectedItem(){
		index = list1.getSelectedIndex();
		comentType = choice1.getSelectedIndex();
		comentTypeName= choice1.getSelectedItem();
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
		new ComentManage(frame);
	}
}
