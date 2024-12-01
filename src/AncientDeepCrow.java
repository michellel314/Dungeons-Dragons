public class AncientDeepCrow {
    private int hp;
    Dice d = new Dice(0);
    public AncientDeepCrow(){
        createADC();
    }

    public void createADC(){
        d.setSides(12);
        d.roll();
        hp = (15 * d.getRollValue() + 90);
        System.out.println("An Ancient Deep Crow with an HP of " + hp + " has appeared");
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
    public boolean isAlive(){
        return hp > 0;
    }
}
