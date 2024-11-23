public class AncientDeepCrow {
    int hp = 0;
    Dice d = new Dice(0);
    public AncientDeepCrow(){
    }

    public String createADC(){
        d.setSides(12);
        d.roll();
        hp = (15 * d.getRollValue() + 90);
        return "An Ancient Deep Crow with an HP of " + hp + " has appeared";
    }

    public void hitADC(int hit){
        hp -= hit;
    }

    public int getHp(){
        return hp;
    }

    public String attack(){
        d.setSides(100);
        d.roll();
        if (d.getRollValue() >= 50){
            return "Claw";
        } else if(d.getRollValue() >= 30){
            return "Brave Bird";
        } else {
            return "Shadow Caw";
        }
    }


    public int DamageValADC(String move){
        if(move.equals("Claw")){
            return 11;
        } else if(move.equals("Brave Bird")){
            return 40;
        } else {
            return 25;
        }
    }

}
