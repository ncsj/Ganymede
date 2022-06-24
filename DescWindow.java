import  java.awt.*;
import  java.io.*;

public class DescWindow extends Window{
	Label label1 = new Label("氏名");
	Label label2 = new Label("生年月日");
	Label label3 = new Label("出身地");
	Label label4 = new Label("就任時年齢");
	Label label5 = new Label("在職期間");
	Label label6 = new Label("在職日数");
	Label label7 = new Label("通算在職日数");

	Label desc1 = new Label("no data");
	Label desc2 = new Label("no data");
	Label desc3 = new Label("no data");
	Label desc4 = new Label("no data");
	Label desc5 = new Label("no data");
	Label desc6 = new Label("no data");
	Label desc7 = new Label("no data");
	
	String desc_file;					// description file name

	public DescWindow(Dialog frame,Double lat,Double lon){
		super(frame);
		setLayout(null);

		//this.desc_file =;

		int  x1 = 10;
		int  w1 = 120;
		int  x2 = 150;
		int  w2 = 220;

		add(label1);
		label1.setBounds(x1,20,w1,20);
		add(desc1);
		desc1.setBounds(x2,20,w2,20);

		add(label2);
		label2.setBounds(x1,40,w1,20);
		add(desc2);
		desc2.setBounds(x2,40,w2,20);

		add(label3);
		label3.setBounds(x1,60,w1,20);
		add(desc3);
		desc3.setBounds(x2,60,w2,20);

		add(label4);
		label4.setBounds(x1,80,w1,20);
		add(desc4);
		desc4.setBounds(x2,80,w2,20);

		add(label5);
		label5.setBounds(x1,100,w1,20);
		add(desc5);
		desc5.setBounds(x2,100,w2,20);

		add(label6);
		label6.setBounds(x1,120,w1,20);
		add(desc6);
		desc6.setBounds(x2,120,w2,20);

		add(label7);
		label7.setBounds(x1,140,w1,20);
		add(desc7);
		desc7.setBounds(x2,140,w2,20);

		loadDescription();

	}

	void loadDescription(){
		try{
			FileInputStream fin = new FileInputStream(desc_file);
			InputStreamReader is = new InputStreamReader(fin);
			BufferedReader reader = new BufferedReader(is);

			Label [] labels = new Label [7];
			labels[0] = desc1;
			labels[1] = desc2;
			labels[2] = desc3;
			labels[3] = desc4;
			labels[4] = desc5;
			labels[5] = desc6;
			labels[6] = desc7;

			for(Label label : labels){
				String s = reader.readLine();
				label.setText(s);
			}

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
