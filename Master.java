public class Master {
    String name = null;
    String id = null;
    double lat;
    double lon;
    double spd; //Speed
    double tec; //Technic
    double chs; //Chase
    double toi; //toilet
    double chi; //child
    String time = null;

    public Master(String name,String id){
        this.name = name;
        this.id = id;
    }

    public Master(String name,String id,String time){
        this.name = name;
        this.id = id;
        this.time = time;
    }

}
