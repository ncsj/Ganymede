import  java.awt.*;
import  java.awt.event.*;

import  java.util.ArrayList;

public
class ComentList extends Dialog implements Closable{
	ManageMenu mm = null;
	
	FileIO cfio = null;
	FileIO mfio = null;
	static final String cfname = "Coment.csv";
	static final String mfname = "MasterData.csv";

	ArrayList <String []> cslist = new ArrayList <String []> ();
	ArrayList <String []> mslist = new ArrayList <String []> ();
	ArrayList <String> idlist = new ArrayList <String> ();

	Label		label1 = new Label("エリアで絞り込む");
	Label       label2 = new Label("コメントリスト");
	List		list1 = new List();
	Choice		choice1 = new Choice();
	Button		btn1  = new Button("終了");

	int index;
	String areaName;
	String id;

	public ComentList(Frame parent){
		// modeless dialog box
		super(parent,"コメントリスト",true);
		if(parent instanceof ManageMenu){
			this.mm = (ManageMenu)parent;
		}

		cfio = new FileIO(cfname);
		mfio = new FileIO(mfname);
		cslist = cfio.getFileDataArrayList();
		mslist = mfio.getFileDataArrayList();

		setBounds(400,200,800,600);
		setLayout(null);

		initControls();

		addWindowListener(new Closer(this));
		setVisible(true);
	}

	void initControls(){
		int x1 = 50;
		int x2 = 170;
		int x3 = 740;

		int y1 = 50;
		int y2 = 100;
		int y3 = 150;
		int y4 = 540;
		
		add(label1);
        label1.setBounds(x1,y1,120,40);

		add(label2);
        label2.setBounds(x1,y2,110,50);
		
		String [] s;
		for(int i=0;i<cslist.size();i++){
			s = cslist.get(i);

			String s1 = "評価【" + s[0] + "】";
			id = s[1];
			idlist.add(id);
			String [] a;
			for(int m=0;m<mslist.size();m++){
				a = mslist.get(m);
            
				if(a[1].equals(id)){
					areaName = a[0];
				}
			}
			String ss = s1 +" , "+ areaName + " , " + dataString(s[2]) + " , " + s[3];
			list1.add(ss);
        }
		
		add(list1);
		list1.setBounds(x1,y3,680,440);

		choice1.add("1週間");
		choice1.add("1ヶ月");
		choice1.add("1年間");
		add(choice1);
		choice1.setBounds(x2,y1,200,40);
		choice1.addItemListener((ItemEvent e)->{ checkSelectedItem(); });

		add(btn1);
		btn1.setBounds(x3,y4,50,50);
		btn1.addActionListener((ActionEvent e)->{ close(); });
	}

	public void checkSelectedItem(){
		index = choice1.getSelectedIndex();
		areaName= choice1.getSelectedItem();
	}

	public void delete(){
		list1.remove(index);
		cfio.removeFileDataArrayList(index);
	}

	public String dataString(String s){
		String [] ss = s.split("/");
		String a = "2" + ss[0] + "年" + ss[1] + "月" + ss[2] + "日" 
			+ ss[3] + "時" + ss[4] + "分" + ss[5] + "秒";
		return a;
	}

	@Override
	public void close(){
		cfio.output();
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
		new ComentList(frame);
	}
}
