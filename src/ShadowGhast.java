public class ShadowGhast {
    private int SGHP = 0;
    Dice d = new Dice(0);

    public ShadowGhast() {
        createShadowGhast();
    }

    public void createShadowGhast() {
        d.setSides(8);
        d.roll();
        SGHP = (9 * d.getRollValue() + 9);
        System.out.println("A Shadow Ghast with " + SGHP + " HP has appeared");
    }

    public void hitSG (int hit) {
        SGHP -= hit;
    }


    public int getSGHP() {
        return SGHP;
    }

    public String attack() {
        d.setSides(100);
        d.roll();
        if (d.getRollValue() >= 40) {
            return "Claw";
        } else if (d.getRollValue() >= 35) {
            return "Bite";
        } else {
            return "Multiattack";
        }
    }

    public int DamageValSG(String a){
        if(a.equals("Claw")){
            return 14;
        } else if(a.equals("Bite")){
            return 7;
        } else {
            return 21;
        }
    }
    public boolean isAlive(){
        return SGHP > 0;
    }
}
