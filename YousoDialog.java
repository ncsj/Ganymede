import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class YousoDialog extends Dialog implements Closable{
    Label label1 = new Label("重視する要素を選択してください（最大3位まで）");
    Label label2 = new Label("１位");
    Label label3 = new Label("２位");
    Label label4 = new Label("３位");
    Label error  = new Label("ERROR!");
    Label label5 = new Label("こちらがおすすめです↓");
    Button enter = new Button("確定");
    List arealist = new List();   

    ArrayList <String> lines = new ArrayList <String> ();
    
    ArrayList <Master>       list = new ArrayList <Master> ();
    ArrayList <Calculator> calist = new ArrayList <Calculator> (); 
    ArrayList <Integer> indexlist = new ArrayList <Integer> ();

    Choice choice1 = new Choice();
    Choice choice2 = new Choice();
    Choice choice3 = new Choice();

    double first;
    double second;
    double third;
    
    int Mdex;

    public YousoDialog(Frame parent){
        super(parent,"要素から観覧場所を選択",true);
        setBounds(200,200,600,400);
        setLayout(null);

        add(label1);
        label1.setBounds(20,40,250,20);

        add(label2);
        label2.setBounds(20,80,40,20);
        add(choice1);
        choice1.setBounds(60,80,200,20);
        choice1.add("ここから選択");
        choice1.add("スピード");
        choice1.add("テクニック");
        choice1.add("競り合い");
        choice1.add("トイレが近い");
        choice1.add("子連れも安心");

        add(label3);
        label3.setBounds(20,140,40,20);
        add(choice2);
        choice2.setBounds(60,140,200,20);
        choice2.add("ここから選択");
        choice2.add("スピード");
        choice2.add("テクニック");
        choice2.add("競り合い");
        choice2.add("トイレが近い");
        choice2.add("子連れも安心");


        add(label4);
        label4.setBounds(20,200,40,20);
        add(choice3);
        choice3.setBounds(60,200,200,20);
        choice3.add("ここから選択");
        choice3.add("スピード");
        choice3.add("テクニック");
        choice3.add("競り合い");
        choice3.add("トイレが近い");
        choice3.add("子連れも安心");

        error.setBounds(110,350,40,20);

        add(label5);
        label5.setBounds(300,40,250,20);

        add(enter);
        enter.setBounds(110,300,40,20);
        enter.addActionListener((ActionEvent e)->{
            if(calist.size()>0){
                calist.removeAll(calist);
                indexlist.removeAll(indexlist);
            }
            arealist.removeAll();
            remove(error);
            if(check()){
                getTotal();
                Mdex = getMax();
                System.out.println(list.get(Mdex).name); //test
                arealist.add(list.get(Mdex).name);
                if(indexlist.size()>0){
                    for(int i=0;i<indexlist.size();i++){
                        arealist.add(list.get(indexlist.get(i)).name);
                    }
                }
            }
            else{
                add(error);
            }

        });

        add(arealist);
        arealist.setBounds(300,80,250,300);

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowOpened(WindowEvent e){
                loaddata();
            }
        });
        

        addWindowListener(new Closer(this));
        setVisible(true);
    }

    public void loaddata(){
        try{
            FileInputStream fin   = new FileInputStream("MasterData.csv");
            InputStreamReader is  = new InputStreamReader(fin);
            BufferedReader reader = new BufferedReader(is);
            for(int i=0;true;i++){
                String s = reader.readLine();
                if(s == null){
                    System.out.println("Break");
                    break;
                }
              
                lines.add(s); 
                System.out.println("add!");

/* 
                String [] items = s.split(",");
                
                String name = items[0];
                double spd  = Double.valueOf(items[4]).doubleValue();
                double tec  = Double.valueOf(items[5]).doubleValue();
                double chs  = Double.valueOf(items[6]).doubleValue();
                double toi  = Double.valueOf(items[7]).doubleValue();
                double chi  = Double.valueOf(items[8]).doubleValue();
                
                Master myouso = new Master(name,spd,tec,chs,toi,chi);

                list.add(myouso);
                System.out.println("add(myouso)");
*/                
            }
            reader.close();
            is.close();
            fin.close();

        }
        catch(FileNotFoundException e){
            System.out.println(e.toString());
        }
        catch(IOException e){
            System.out.println(e.toString());
        }

    }
/* 
    public void getFirst(){
        int index = choice1.getSelectedIndex();
        for(int i=0;i<lines.length;i++)
       // first =
    }
    public void getSecond(){
        int index2 = choice2.getSelectedIndex();
    }
    public void getThird(){
        int index3 = choice3.getSelectedIndex();
    }
*/

    int index1;
    int index2;
    int index3;
    public boolean check(){
        boolean result = false;
        index1 = choice1.getSelectedIndex();
        index2 = choice2.getSelectedIndex();
        index3 = choice3.getSelectedIndex();
        if(index1 != index2 || index1 != index3){
            result = true;}
        if(index2 != index3){
            result = true;
        }
        return result;
    }

    public void getTotal(){
            
        for(int i=0;i<lines.size();i++){
            String [] items = lines.get(i).split(",");
            String name = items[0];
            double spd  = Double.valueOf(items[4]).doubleValue();
            double tec  = Double.valueOf(items[5]).doubleValue();
            double chs  = Double.valueOf(items[6]).doubleValue();
            double toi  = Double.valueOf(items[7]).doubleValue();
            double chi  = Double.valueOf(items[8]).doubleValue();
            Master myouso = new Master(name,spd,tec,chs,toi,chi);
            list.add(myouso);
            first  = Double.valueOf(items[index1 + 3]).doubleValue();
            second = Double.valueOf(items[index2 + 3]).doubleValue();
            third  = Double.valueOf(items[index3 + 3]).doubleValue();
            if(index1 == 0){
                first = 0;
            }
            if(index2 == 0){
                second = 0;
            }
            if(index3 == 0){
                third  = 0; 
            }
            Calculator cal = new Calculator(first,second,third,spd,tec,chs,toi,chi);
            calist.add(cal);
            System.out.println("calist add!");

        }
    }    

    public int getMax(){
        double max = calist.get(0).sum;
        int maxindex = 0;
        for(int i=1;i<calist.size();i++){
            double cur = calist.get(i).sum;
            if(max < cur){
                max = cur;
                maxindex = i;
            }
            if(max == cur){
                indexlist.add(i);
            }
            
        }
        return maxindex;
    }

    @Override
    public void close(){
        setVisible(false);
        dispose();
    }

    public static void main(String args[]){
        Frame frame = new Frame();
        frame.setBounds(0,0,200,200);
        frame.setVisible(true);
        
        new YousoDialog(frame);
    }
}
    

