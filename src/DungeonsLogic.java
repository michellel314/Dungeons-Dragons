
public class DungeonsLogic {
    private Player currentPlayer;
    private Player player1;
    private Player player2;
    static Dice d = new Dice(0);

    public DungeonsLogic(){
    }
    public void chooseStartingPlayer(){
        int randomNum  = (int)(Math.random()* 1) + 1;
        if(randomNum == 1){
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
    }

    public static String chestLoot () {
        d.setSides(2);
        d.roll();
        if (d.getRollValue() == 1) {
            return "Gameover";
        } else {
            d.setSides(100);
            d.roll();
            return chestStuff();
        }
    }
    private static String chestStuff(){
        if (d.getRollValue() <= 1) {
            return "You get a jeep";
        } else if (d.getRollValue() <= 2) {
            return "You get a gun (+10 atk)";
        } else if (d.getRollValue() <= 7){
            return "You upgraded to an iron sword (+6 atk)";
        } else if (d.getRollValue() <= 40){
            d.setSides(2);
            if(d.getRollValue() == 1){
                return "You obtained a bow and arrow (+3 atk)";
            } else {
                return "You obtained an artifact (+35 HP)";
            }
        } else {
            return "You obtained a rusty dagger (+2 atk)";
        }
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }


}
