public class Dungeons {
    private String p1;
    private String p2;
    private int h1 = 100;
    private int h2 = 100;
    private int attk1 = 5;
    private int attk2 = 5;
    private String currentMonster = "";


    Dice dice = new Dice(0);
    YoungGoldDragon yGD = new YoungGoldDragon();
    ShadowGhast sGH = new ShadowGhast();
    AncientDeepCrow aDC = new AncientDeepCrow();


    public Dungeons(String p1, String p2){
        this.p1 = p1;
        this.p2 = p2;
    }


    public String monsterGenerator(){
        dice.setSides(3);
        dice.roll();
        int monster = dice.getRollValue();
        if (monster == 1){
            currentMonster = "Young Gold Dragon";
            return yGD.createYoungGoldDragon();
        }else if(monster == 2){
            currentMonster = "Shadow Ghast";
            return sGH.createShadowGhast();
        } else{
            return aDC.createADC();
        }
    }


    public int attackTarget(){
        dice.setSides(2);
        dice.roll();
        return dice.getRollValue();
    }


    public String encounter(){
        dice.setSides(3);
        dice.roll();
        if (dice.getRollValue() == 1){
            return "Treasure Chest";
        }else if (dice.getRollValue() == 2){
            return "Monster";
        }else {
            return "NPC";
        }
    }

}
