public class IntOverCheckItem implements CheckItem{
	int threashold = 0;
	public IntOverCheckItem(int threashold){
		this.threashold = threashold;
	}

	@Override
	public boolean check(Object o) throws CheckItemException{
		if(o instanceof Integer){
			;
		}
		else{
			throw new CheckItemException("Not Support Type");
		}

		boolean rtc = true;

		Integer v = (Integer)o;
		int value = v.intValue();

		if(value > threashold){
			rtc = true;
		}
		else{
			rtc = false;
		}

		return rtc;
	}

	public String toString(){
		String s =  " > " + this.threashold;
		return s;
	}

	/**
	  Test Code
	**/
	public static void main(String args[]){
		int  th = Integer.valueOf(args[0]).intValue();
		Integer  n  = Integer.valueOf(args[1]);

		try{
			IntOverCheckItem item = new IntOverCheckItem(th);
			if(item.check(n)){
				System.out.println(item.toString());
				System.out.println("CHECK : OK");
				System.out.println("value : " + n);
			}
		}
		catch(CheckItemException e){
			System.out.println(e.toString());
		}
	}
}
