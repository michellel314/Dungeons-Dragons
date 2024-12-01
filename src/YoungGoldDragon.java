public class YoungGoldDragon {
    private int hp = 0;

    public YoungGoldDragon(){
        createYoungGoldDragon();
    }
    Dice d = new Dice(0);

    public void createYoungGoldDragon(){
        d.setSides(10);
        d.roll();
        hp = (17 * d.getRollValue() + 85);
        System.out.print("A Young Gold Dragon with " + hp + " HP has appeared");
    }
    public void hitYGD(int hit){
        hp -= hit;
    }
    public int getYGDHP(){
        return hp;
    }
    public String attack(){
        d.setSides(100);
        d.roll();
        if(d.getRollValue() >= 50){
            return "Bite";
        }else if (d.getRollValue() >= 35){
            return "MultiAttack";
        }else {
            return "Flame Breath";
        }
    }
    public int dmgValue(String atk){
        if(atk.equals("Bite")){
            return 10;
        }else if (atk.equals("Multiattack")){
            return 15;
        }else{
            return 35;
        }
    }

    public boolean isAlive(){
        return hp > 0;
    }


}
