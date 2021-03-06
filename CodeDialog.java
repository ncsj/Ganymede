import java.awt.*;
import java.awt.event.*;

import java.io.*;

import java.util.ArrayList;
import java.util.Calendar;

public class CodeDialog extends Dialog implements Closable{
    TextField field = new TextField("IDを入力、または→");
    Button btn1     = new Button("登録");
    Button btn2     = new Button("選択");
    Label label     = new Label("ID");    
    Choice choice = new Choice();
    ArrayList <Master> masterlist = new ArrayList <Master> ();
    ArrayList <CSVItem> csvlist    = new ArrayList <CSVItem> ();

    boolean flag = false;
    int index;
    int year;
    int mon;
    int date;
    int hour;
    int min;
    int sec;

    public CodeDialog(Frame parent){

        super(parent,"位置情報を登録しましょう",true);
        setBounds(450,250,300,200);
        setLayout(null);

        add(field);
        field.setBounds(70,100,130,20);

        add(btn2);
        btn2.setBounds(200,100,40,20);
        btn2.addActionListener((ActionEvent e)->{ close();});

        add(btn1);
        btn1.setBounds(125,150,50,20);
        btn1.addActionListener((ActionEvent e)->{ setData();});

        add(label);
        label.setBounds(50,100,20,20);

        addWindowListener(new Closer(this));
        setVisible(true);
    }

    @Override
    public void close(){
        setVisible(false);
        dispose();
    }

    public void setData(){
        String id = field.getText();
        flag = check(id);
        String time = getTime();
        System.out.println(masterlist.get(0).id);
        CSVItem master = null;
        for(int i = 0;i<masterlist.size();i++){
            System.out.println(masterlist.get(i).name);
            if(flag = true){
                System.out.println("true");
                master = new CSVItem(masterlist.get(i).id,masterlist.get(i).time);
                //System.out.println(master.id);  
                csvlist.add(master);
        }
        loadcsv();
        //最後にCSVに書き出す
        writecsv();
        //System.out.println(csvlist.get(csvlist.size()-1).id);
        }
    }

    public void loadcsv(){
        String file = "konzatsu.csv";
        try{
            FileInputStream fin   = new FileInputStream(file);
            InputStreamReader is  = new InputStreamReader(fin);
            BufferedReader reader = new BufferedReader(is);

            while(true){
                String gyo = reader.readLine();
                if(gyo == null){
                    break;
                }
                try{
                    String [] items = gyo.split(",");
                    CSVItem jammaster = new CSVItem(items[1],items[3]);
                    csvlist.add(jammaster); 
                    choice.add(items[0]);
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
            }
            reader.close();
            is.close();
            fin.close();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public void writecsv(){
        try{
            FileOutputStream fout = new FileOutputStream("konzatsu.csv");
            PrintStream ps = new PrintStream(fout);
            for(int i=0;i<csvlist.size();i++){
                for(int n=0;n<csvlist.size();n++){
                    ps.print(csvlist.get(i).id);
                    ps.print(",");
                    ps.print(csvlist.get(i).time);
                    ps.printf("\n");
                }
                }
                ps.close();
                fout.close();
            }
            catch(FileNotFoundException e){
                System.out.println(e.toString());
            }
            catch(IOException e){
                System.out.println(e.toString());
            }
    }

    public boolean check(String id){  //idが一致するものがあるかチェックし、一致するもののindexを返す
        loadMasterData();
        int i = 0;
        Master master = null;
        for(i=0;i<masterlist.size();i++){
            master = masterlist.get(i);
            if(id.equals(master.id)){
                flag = true;
                index = i;
                System.out.println(index);
                break;
            }
        }
        System.out.println("@check");
        return flag;
    }

    public void loadMasterData(){  //マスタデータをリスト化
        String file = "MasterData.csv";
        try{
            FileInputStream fin   = new FileInputStream(file);
            InputStreamReader is  = new InputStreamReader(fin);
            BufferedReader reader = new BufferedReader(is);

            while(true){
                int i = 0;
                String gyo = reader.readLine();
                if(gyo == null){
                    break;
                }
                try{
                    String [] items = gyo.split(",");
                    Master coadmaster = new Master(items[0],items[1]);
                    masterlist.add(coadmaster); 
                    System.out.println(masterlist.get(i).name);
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
                i++;
            }
            reader.close();
            is.close();
            fin.close();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public String getTime(){
        Calendar cal = Calendar.getInstance();
        
        year = cal.get(Calendar.YEAR);
        mon  = cal.get(Calendar.MONTH);
        date = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        min  = cal.get(Calendar.MINUTE);
        sec  = cal.get(Calendar.SECOND);

        String s = year + "/" + mon + "/" + date + "/" + hour + "/" + min + "/" + sec;

        return s;
    }

    // Test Code
    public static void main(String args[]){
        Frame frame = new Frame();
        frame.setBounds(0,0,200,200);
        frame.setVisible(true);
        
        new CodeDialog(frame);
    }
}
