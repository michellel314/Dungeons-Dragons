public class Dungeons {
    private Player player1;
    private Player player2;

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
            return "Young Gold Dragon";
        }else if(monster == 2){
            return "Shadow Ghast";
        } else{
            return "Ancient Deep Crow";
        }
    }

    public int attackTarget(){
        dice.setSides(2);
        dice.roll();
        return dice.getRollValue();
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

}
