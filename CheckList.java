public abstract class CheckList implements CheckItem{
	class ItemContainer{
		CheckItem item = null;
		public ItemContainer(CheckItem item){
			this.item = item;
		}

		ItemContainer next = null;
	}

	ItemContainer  con = null;
	int  count = 0;

	public void add(CheckItem item){
		ItemContainer con = new ItemContainer(item);

		count++;

		if(this.con == null){
			this.con = con;
		}
		else{
			ItemContainer cur = this.con;
			while(cur.next != null){
				cur = cur.next;
			}

			cur.next = con;
		}
	}

	public void remove(int index) throws ArrayIndexOutOfBoundsException{
		if(index >= count){
			throw new ArrayIndexOutOfBoundsException();
		}
		
		if(index == 0){
			this.con = this.con.next;
		}
		else{
			ItemContainer prev = this.con;
			ItemContainer cur = prev.next;
			for(int i=1;i<index;i++){
				prev = cur;
				cur = cur.next;
			}

			prev.next = cur.next;
		}
	}

	public CheckItem get(int index) throws ArrayIndexOutOfBoundsException{
		CheckItem item = null;

		if(index >= count){
			throw new ArrayIndexOutOfBoundsException();
		}

		ItemContainer cur = this.con;
		for(int i=0;i<index;i++){
			cur = cur.next;
		}

		item = cur.item;

		return item;
	}

	public int length(){
		return count;
	}
}
