import  java.awt.*;
import  java.awt.event.*;

import java.util.ArrayList;

public
class MasterManage extends Dialog implements Closable{
	ManageMenu mm = null;

	FileIO fio = null;
    static final String fname = "MasterData.csv";
	
	Label		label = new Label("登録地点リスト");
	Label		label1 = new Label("基本情報");
	Label       label2 = new Label("名前");
	Label       label3 = new Label("ID");
	Label       label4 = new Label("緯度");
	Label       label5 = new Label("経度");
	Label       label6 = new Label("評価点");
	Label       label7 = new Label("スピード");
	Label       label8 = new Label("テクニック");
	Label       label9 = new Label("競り合い");
	Label       label10 = new Label("子連れも安心");
	Label       label11 = new Label("トイレが近い");

	List		list1 = new List();
	
	TextField	text1 = new TextField("メイン");
	TextField   text2 = new TextField("100001");
	TextField   text3 = new TextField("3522.2952");
	TextField   text4 = new TextField("13855.7499");

	Choice choice [] = new Choice [5];

	Button		btn1  = new Button("追加");
	Button		btn2  = new Button("削除");
	Button      btn3  = new Button("終了");

	int listIndex;
	String  [] index = new String [5];

	ArrayList <String []> slist = new ArrayList <String []> ();

	public MasterManage(Frame parent){
		// modeless dialog box
		super(parent,"登録地点の管理",true);
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
		int x2 = 590;
		int x3 = 640;
		int x4 = 690;
		int x5 = 720;

		int y1 = 50;
		int y2 = 90;
		int y3 = 135;
		int y4 = 180;
		int y5 = 225;
		int y6 = 270;
		int y7 = 315;
		int y8 = 360;
		int y9 = 405;
		int y10 = 450;
		int y11 = 505;
		int y12 = 550;
		
		add(label);
        label.setBounds(x1,y1,100,40);

		add(label1);
        label1.setBounds(x2,y1,100,40);

		add(label2);
        label2.setBounds(x2,y2,100,40);

		add(label3);
        label3.setBounds(x2,y3,100,40);

		add(label4);
        label4.setBounds(x2,y4,100,40);

		add(label5);
        label5.setBounds(x2,y5,100,40);

		add(label6);
        label6.setBounds(x2,y6,100,40);

		add(label7);
        label7.setBounds(x2,y7,100,40);

		add(label8);
        label8.setBounds(x2,y8,100,40);

		add(label9);
        label9.setBounds(x2,y9,100,40);

		add(label10);
        label10.setBounds(x2,y10,100,40);

		add(label11);
        label11.setBounds(x2,y11,100,40);

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
        list1.setBounds(x1,y2,530,500);
		list1.addItemListener((ItemEvent e)->{ checkSelectedItem(); });

		add(text1);
        text1.setBounds(x4,y2,100,40);
		
		add(text2);
        text2.setBounds(x4,y3,100,40);

		add(text3);
        text3.setBounds(x4,y4,100,40);

		add(text4);
        text4.setBounds(x4,y5,100,40);

		for(int i=0;i<5;i++){
			choice[i] = new Choice();
			choice[i].add("5");
			choice[i].add("4");
			choice[i].add("3");
			choice[i].add("2");
			choice[i].add("1");
		}

		add(choice[0]);
		choice[0].setBounds(x4,y7,100,40);
		choice[0].addItemListener((ItemEvent e)->{ checkSelectedItem(); });
		
		add(choice[1]);
		choice[1].setBounds(x4,y8,100,40);
		choice[1].addItemListener((ItemEvent e)->{ checkSelectedItem(); });
		
		add(choice[2]);
		choice[2].setBounds(x4,y9,100,40);
		choice[2].addItemListener((ItemEvent e)->{ checkSelectedItem(); });
		
		add(choice[3]);
		choice[3].setBounds(x4,y10,100,40);
		choice[4].addItemListener((ItemEvent e)->{ checkSelectedItem(); });

		add(choice[4]);
		choice[4].setBounds(x4,y11,100,40);
		choice[4].addItemListener((ItemEvent e)->{ checkSelectedItem(); });
		
		add(btn1);
		btn1.setBounds(x2,y12,50,30);
		btn1.addActionListener((ActionEvent e)->{ add(); });
		
		add(btn2);
		btn2.setBounds(x3,y12,50,30);
		btn2.addActionListener((ActionEvent e)->{ remove(); });

		add(btn3);
        btn3.setBounds(x5,y12,50,40);
        btn3.addActionListener((ActionEvent e)->{ close(); });
	}

	public void checkSelectedItem(){
		listIndex = list1.getSelectedIndex();
		for(int i=0;i<choice.length;i++){
			index[i] = choice[i].getSelectedItem();
		}
    }

	public void add(){
		boolean ok = true;
		String [] s = new String [4];

		s[0] = text1.getText();
		s[1] = text2.getText();
		s[2] = text3.getText();
		s[3] = text4.getText();

		String s1 = s[0];
		String s2 = index[0];
		String s3 = s[0];
		String s4 = index[0];

		for(int i=1;i<index.length;i++){
			if(i<4){
				if(s[i] == null){
					ok = false;
					break;
				}
				s1 = s1 + " , " + s[i];
			}
			if(index[i] == null){
				ok = false;
				break;
			}
			s2 = s2 + " , " + index[i];
		}
		if(ok){
			list1.add(s1 + " , " + s2);
			fio.addFileDataArrayList(match(s,index));
		}
    }

	public void remove(){
        list1.remove(listIndex);
        fio.removeFileDataArrayList(listIndex);
    }

	public String [] match(String [] bef,String [] aft){
		int len = bef.length + aft.length;
		String [] a = new String [len];
		for(int i=0;i<bef.length;i++){
			a[i] = bef[i];
		}
		for(int i=0;i<aft.length;i++){
            a[i+bef.length] = aft[i];
        }
		return a;
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
		new MasterManage(frame);
	}
}
