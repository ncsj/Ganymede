public class Calculator {
    double spd;
    double tec;
    double chs;
    double toi;
    double chi;
    double sum;

    public Calculator(double first,double second,double third,double spd,double tec,double chs,double toi,double chi){
        this.spd = spd;
        this.tec = tec;
        this.chs = chs;
        this.toi = toi;
        this.chi = chi;
      
        this.sum = spd + tec + chs + toi + chi + first + second + (first * 2) + second + (third /2);
    } 

}
