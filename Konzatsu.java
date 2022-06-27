import  java.awt.*;
import  java.awt.event.*;

import java.util.ArrayList;
import  java.util.Calendar;

public class Konzatsu{
	FileIO fio = null;
    static final String fname = "Konzatsu.csv";

	ArrayList <String []> slist = new ArrayList <String []> ();
	ArrayList <Integer []> ilist = new ArrayList <Integer []> ();

	Calendar cal = null;

	public Konzatsu(){
		this.fio = new FileIO(fname);
        this.slist = fio.getFileDataArrayList();
		makeKonzatsuTimeList();
	}

	public void makeKonzatsuTimeList(){
		for(int i=0;i<slist.size();i++){
			String [] s = slist.get(i);
			String [] ss = s[1].split("/");
			Integer [] il = new Integer[ss.length];
			for(int n=0;n<ss.length;n++){
				il[n] = Integer.valueOf(ss[n]);
			}
			this.ilist.add(il);
		}
	}

	public int countOneHour(int id){
		this.cal = Calendar.getInstance();
		int count = 0;
		int compTime;
		for(int i=0;i<ilist.size();i++){
			String [] s = slist.get(i);
			if(id == Integer.valueOf(s[0]).intValue()){
				Integer [] il = ilist.get(i);
				int [] ill = new int [il.length];
				for(int n=0;n<il.length;n++){
					ill[n] = il[n].intValue();
				}
				Calendar bef = Calendar.getInstance();
				bef.set(ill[0],ill[1],ill[2],ill[3],ill[4],ill[5]);
				compTime = this.cal.compareTo(bef);
				if(compTime <= 0){
					bef.set(ill[0],ill[1],ill[2],ill[3]-1,ill[4],ill[5]+1);
					compTime = this.cal.compareTo(bef);
					if(compTime >= 0){
						count++;
					}
				}
			}
		}
		return count;
	}

	public int countOneDay(int id){
		this.cal = Calendar.getInstance();
		int count = 0;
		int compTime;
		for(int i=0;i<ilist.size();i++){
			String [] s = slist.get(i);
			if(id == Integer.valueOf(s[0]).intValue()){
				Integer [] il = ilist.get(i);
				int [] ill = new int [il.length];
				for(int n=0;n<il.length;n++){
					ill[n] = il[n].intValue();
				}
				Calendar bef = Calendar.getInstance();
				bef.set(ill[0],ill[1],ill[2],ill[3],ill[4],ill[5]);
				compTime = this.cal.compareTo(bef);
				if(compTime <= 0){
					bef.set(ill[0],ill[1],ill[2]-1,ill[3],ill[4],ill[5]+1);
					compTime = this.cal.compareTo(bef);
					if(compTime >= 0){
						count++;
					}
				}
			}
		}
		return count;
	}

	public int countOneDay(int id,int day){
		Calendar daybef = Calendar.getInstance();
		daybef.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)-day);
        int count = 0;
        int compTime;
        for(int i=0;i<ilist.size();i++){
            String [] s = slist.get(i);
            if(id == Integer.valueOf(s[0]).intValue()){
                Integer [] il = ilist.get(i);
                int [] ill = new int [il.length];
                for(int n=0;n<il.length;n++){
                    ill[n] = il[n].intValue();
                }
                Calendar bef = Calendar.getInstance();
                bef.set(ill[0],ill[1],ill[2]-day,ill[3],ill[4],ill[5]);
                compTime = daybef.compareTo(bef);
                if(compTime <= 0){
                    bef.set(ill[0],ill[1],ill[2]-day-1,ill[3],ill[4],ill[5]+1);
                    compTime = this.cal.compareTo(bef);
                    if(compTime >= 0){
                        count++;
                    }
                }
            }
        }
        return count;
    }

	public int countOneMonth(int id){
		this.cal = Calendar.getInstance();
		int count = 0;
		int compTime;
		for(int i=0;i<ilist.size();i++){
			String [] s = slist.get(i);
			if(id == Integer.valueOf(s[0]).intValue()){
				Integer [] il = ilist.get(i);
				int [] ill = new int [il.length];
				for(int n=0;n<il.length;n++){
					ill[n] = il[n].intValue();
				}
				Calendar bef = Calendar.getInstance();
				bef.set(ill[0],ill[1],ill[2],ill[3],ill[4],ill[5]);
				compTime = this.cal.compareTo(bef);
				if(compTime <= 0){
					bef.set(ill[0],ill[1]-1,ill[2],ill[3],ill[4],ill[5]+1);
					compTime = this.cal.compareTo(bef);
					if(compTime >= 0){
						count++;
					}
				}
			}
		}
		return count;
	}

	public int countOneYear(int id){
		this.cal = Calendar.getInstance();
		int count = 0;
		int compTime;
		for(int i=0;i<ilist.size();i++){
			String [] s = slist.get(i);
			if(id == Integer.valueOf(s[0]).intValue()){
				Integer [] il = ilist.get(i);
				int [] ill = new int [il.length];
				for(int n=0;n<il.length;n++){
					ill[n] = il[n].intValue();
				}
				Calendar bef = Calendar.getInstance();
				bef.set(ill[0],ill[1],ill[2],ill[3],ill[4],ill[5]);
				compTime = this.cal.compareTo(bef);
				if(compTime <= 0){
					bef.set(ill[0]-1,ill[1],ill[2],ill[3],ill[4],ill[5]+1);
					compTime = this.cal.compareTo(bef);
					if(compTime >= 0){
						count++;
					}
				}
			}
		}
		return count;
	}

	public static void main(String args[]){
		Konzatsu k = new Konzatsu();
		int i1 = Integer.valueOf(args[0]).intValue();
		System.out.println(k.countOneHour(i1));
		System.out.println(k.countOneDay(i1));
		System.out.println(k.countOneMonth(i1));
		System.out.println(k.countOneYear(i1));
	}
}
