public class DungeonsLogic {
    private Player currentPlayer;
    private Dungeons dnd;
    private Player player1 = dnd.getPlayer1();
    private Player player2 = dnd.getPlayer2();
    Dice d = new Dice(0);
    boolean gameOver;

    public DungeonsLogic(){
        currentPlayer = null;
        gameOver = false;
    }
    public void chooseStartingPlayer(){
        int randomNum  = (int)(Math.random()* 1) + 1;
        if(randomNum == 1){
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
    }

    public void chestLoot () {
        d.setSides(2);
        d.roll();
        if (d.getRollValue() == 1) {
            System.out.println("Gameover!");
            gameOver = true;
        } else {
            d.setSides(100);
            d.roll();
            chestStuff();
        }
    }

    private void chestStuff(){
        if (d.getRollValue() <= 1) {
            System.out.println ("You get a jeep");
        } else if (d.getRollValue() == 2) {
            System.out.println("You get a gun (+10 atk)");
            if(currentPlayer == player1){
                player1.setHealth(10);
            } else {
                player2.setHealth(10);
            }
        } else if (d.getRollValue() <= 7){
            System.out.println("You upgraded to an iron sword (+6 atk)");
            if(currentPlayer == player1){
                player1.setAtk(6);
            } else {
                player2.setAtk(6);
            }
        } else if (d.getRollValue() <= 40){
            d.setSides(2);
            if(d.getRollValue() == 1){
                System.out.println("You obtained a bow and arrow (+3 atk)");
                if(currentPlayer == player1){
                    player1.setAtk(3);
                } else {
                    player2.setAtk(3);
                }
            } else {
                System.out.println("You obtained an artifact (+35 HP)");
                if(currentPlayer == player1){
                    player1.setHealth(35);
                } else {
                    player2.setHealth(35);
                }
            }
        } else {
            System.out.println("You obtained a rusty dagger (+2 atk)");
            if(currentPlayer == player1){
                player1.setAtk(2);
            } else {
                player2.setAtk(2);
            }
        }
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }
}