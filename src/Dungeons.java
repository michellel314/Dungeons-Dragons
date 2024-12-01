public class Dungeons {
    private Player player1;
    private Player player2;
    private String currentMonster = "";


    Dice dice = new Dice(0);
    YoungGoldDragon yGD = new YoungGoldDragon();
    ShadowGhast sGH = new ShadowGhast();
    AncientDeepCrow aDC = new AncientDeepCrow();


    public Dungeons(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }


    public String monsterGenerator(){
        dice.setSides(3);
        dice.roll();
        int monster = dice.getRollValue();
        if (monster == 1){
            currentMonster = "Young Gold Dragon";
            return currentMonster;
        }else if(monster == 2){
            currentMonster = "Shadow Ghast";
            return currentMonster;
        } else{
            currentMonster = "Ancient Deep Crow";
            return currentMonster;
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

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

}
