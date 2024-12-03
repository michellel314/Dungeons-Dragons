public class AncientDeepCrow {
    private int hp;
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
            return "Infectious Penalty";
        } else {
            return "Shadow Caw";
        }
    }

    public int DamageValADC(String move){
        if(move.equals("Claw")){
            return 11;
        } else if(move.equals("Infectious Penalty")){
            return 100;
        } else {
            return 25;
        }
    }
    
    public boolean isAlive(){
        return hp > 0;
    }
}
