class Item{
    Double     keido;
    Double     ido;

	public Item(){
		;
	}
        
    public Item(String [] csv){
        keido   = Double.valueOf(csv[1]).doubleValue();
        ido     = Double.valueOf(csv[0]).doubleValue();
    }
    
    public void print(){
        System.out.println("longitude :" + keido + ", latitude :" + ido);
    }
}

