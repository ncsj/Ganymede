public class IntCheckList extends CheckList{
	@Override
	public boolean check(Object o) throws CheckItemException{
		if(o instanceof Integer){
			;
		}
		else{
			throw new CheckItemException("Not Support Type");
		}

		boolean rtc = true;

		for(int i=0;i<length();i++){
			CheckItem item = get(i);
			if(item.check(o)){
				rtc = true;
			}
			else{
				rtc = false;
				break;
			}
		}

		return rtc;
	}

	public boolean check(Object o,boolean positive) throws CheckItemException{
		if(o instanceof Integer){
			;
		}
		else{
			throw new CheckItemException("Not Support Type");
		}

		boolean rtc = true;

		if(positive){
			rtc = checkPositive(o);
		}
		else{
			rtc = checkNegative(o);
		}

		return  rtc;
	}

	public boolean checkPositive(Object o) throws CheckItemException{
		boolean rtc = false;

		for(int i=0;i<length();i++){
			CheckItem item = get(i);
			if(item.check(o)){
				rtc = true;
				break;
			}
		}

		return rtc;
	}

	public boolean checkNegative(Object o) throws CheckItemException{
		boolean rtc = true;

		for(int i=0;i<length();i++){
			CheckItem item = get(i);
			if(item.check(o)){
			}
			else{
				rtc = false;
				break;
			}
		}
		
		return rtc;
	}

	/**
	  TEST CODE
	**/
	public static void main(String args[]){
		Integer value = Integer.valueOf(args[0]);

		IntUnderCheckItem item1 = new IntUnderCheckItem(100);
		IntOverCheckItem item2 = new IntOverCheckItem(80);

		IntCheckList list = new IntCheckList();
		list.add(item1);
		list.add(item2);

		IntCheckList list2 = new IntCheckList();
		IntEquivCheckItem item3 = new IntEquivCheckItem(3);
		IntEquivCheckItem item4 = new IntEquivCheckItem(7);
		IntEquivCheckItem item5 = new IntEquivCheckItem(91);

		list2.add(item3);
		list2.add(item4);
		list2.add(item5);

		try{
			if(list.check(value)){
				System.out.println("OK");
			}
			else{
				System.out.println("NG");
			}

			if(list2.check(value,true)){
				System.out.println("POSITIVE");
			}


		}
		catch(CheckItemException e){
			System.out.println(e.toString());
		}
	}
}
