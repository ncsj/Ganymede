import  java.awt.*;
import  java.io.*;

import java.util.ArrayList;

public class MapWindow extends Window{
	Label label1 = new Label("エリア名");
	Label label2 = new Label("緯度：");
	Label label3 = new Label("経度：");
	Label info1  = new Label("no info");
	Label info2  = new Label("no info");
	Label info3  = new Label("no info");
	ArrayList <Point> ptlist = new ArrayList <Point> ();
	
	String desc_file;					// description file name

	public MapWindow(Dialog frame,Double lat,Double lon){
		super(frame);
		setLayout(null);
		InputMapFile ipmap = new InputMapFile();


		//this.desc_file =;

		int  x1 = 10;
		int  w1 = 120;
		int  x2 = 140;
		int  w2 = 150;


		add(label1);
		label1.setBounds(x1,20,w1,20);
		add(info1);
		info1.setBounds(x2,20,w2,20);

		add(label2);
		label2.setBounds(x1,40,w1,20);
		add(info2);
		info2.setBounds(x2,40,w2,20);

		add(label3);
		label3.setBounds(x1,60,w1,20);
		add(info3);
		info3.setBounds(x2,60,w2,20);

		//loadDescription();

	}

	void loadDescription(){
		try{
			FileInputStream fin = new FileInputStream(desc_file);
			InputStreamReader is = new InputStreamReader(fin);
			BufferedReader reader = new BufferedReader(is);

			

			reader.close();
			is.close();
			fin.close();
		}
		catch(FileNotFoundException e){
		}
		catch(IOException e){
		}
	}


}
