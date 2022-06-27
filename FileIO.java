import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

/**
	mainで実行する場合
	java FileInput fname
**/

public class FileIO{
	Properties props = System.getProperties();
	public String HOME = props.getProperty("user.dir") + "/";

	String fname;
	ArrayList <String []> slist = new ArrayList <String []> ();

	public FileIO(String fname){
		this.fname = fname;
		input();
	}

	public ArrayList <String []> getFileDataArrayList(){
		return this.slist;
	}

	public void addFileDataArrayList(String [] s){
		slist.add(s);
	}

	public void insertFileDataArrayList(int index,String [] s){
        slist.add(index,s);
    }

	public void removeFileDataArrayList(int index){
		slist.remove(index);
	}

	public void input(){
        File file = new File(HOME + fname); // この中でパスを持っている

		String path = file.getAbsolutePath(); //絶対パスをとる

        try{
            FileInputStream fin = new FileInputStream(file);

            InputStreamReader is = new InputStreamReader(fin); //1文字ずつ
            BufferedReader reader = new BufferedReader(is); //Readerをとる

            while(true){
                String line = reader.readLine();
                if(line != null){
                    String [] s = line.split(",");
					slist.add(s);
                }
                else{
                    break;
                }
            }

            is.close();
            fin.close();
        }
        catch(FileNotFoundException e){
            System.out.println("There is no file : " + file.toString());
        }
        catch(IOException e){
			System.out.println(e.toString());
        }
    }

	public void output(){
		try{
			FileOutputStream fout = new FileOutputStream(fname);
			PrintStream ps = new PrintStream(fout);
			String [] s;
			for(int i=0;i<slist.size();i++){
				s = slist.get(i);
				for(int n=0;n<s.length;n++){
					if(n>0){
						ps.print(",");
					}
					ps.print(s[n]);
					if(n == s.length-1){
						ps.printf("\n");
					}
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

	/**
	  テストコード
	**/

	public static void main(String args[]){
		FileIO fi = new FileIO(args[0]);
		ArrayList <String []> slist = fi.getFileDataArrayList();
		
		String [] s = null;
		for(int i=0;i<slist.size();i++){
			s = slist.get(i);
			for(int n=0;n<s.length;n++){
				if(n>0){
					System.out.print(",");
				}
				System.out.print(s[n]);
				if(n == s.length-1){
					System.out.printf("\n");
				}
			}
		}
		fi.addFileDataArrayList(s);
		fi.removeFileDataArrayList(0);
		fi.output();
	}
}
