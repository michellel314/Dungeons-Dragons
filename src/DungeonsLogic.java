import java.util.Scanner;
public class DungeonsLogic {
    private Player currentPlayer;
    Player player1;
    Player player2;
    Dice d = new Dice(0);
    boolean gameOver;
    Scanner scan;

    public DungeonsLogic() {
        player1 = new Player("Player 1", 100, 10);
        player2 = new Player();
        currentPlayer = null;
        gameOver = false;
        dnd = new Dungeons(player1, player2);
        d = new Dice(0);
        scan = new Scanner(System.in);
    }

    public void chooseStartingPlayer() {
        d.setSides(2);
        d.roll();
        int randomNum = d.getRollValue();
        if (randomNum == 1) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
    }

    public void chestLoot() {
        d.setSides(2);
        d.roll();
        if (d.getRollValue() == 1) {
            System.out.println("Gameover!");
            gameOver = true;
            replay();
        } else {
            d.setSides(100);
            d.roll();
            chestStuff();
        }
    }

    private void chestStuff() {
        if (d.getRollValue() <= 1) {
            System.out.println("You get a jeep");
            if (currentPlayer == player1) {
                player1.setAtk(100);
            } else {
                player2.setAtk(100);
            }
        } else if (d.getRollValue() == 2) {
            System.out.println("You get a gun (+10 atk)");
            player1.setAtk(10);
            player2.setAtk();
        } else if (d.getRollValue() <= 7) {
            System.out.println("You upgraded to an iron sword (+6 atk)");
            if (currentPlayer == player1) {
                player1.setAtk(6);
                player2.setAtk();
        } else if (d.getRollValue() <= 40) {
            d.setSides(2);
            if (d.getRollValue() == 1) {
                System.out.println("You obtained a bow and arrow (+3 atk)");
                if (currentPlayer == player1) {
                    player1.setAtk(3);
                    player2.setAtk();
            } else {
                System.out.println("You obtained an artifact (+35 HP)");
                if (currentPlayer == player1) {
                    player1.setHealth(35);
                } else {
                    player2.setHealth(35);
                }
            }
        } else {
            System.out.println("You obtained a rusty dagger (+2 atk)");
            player1.setAtk(2);
            player2.setAtk();
        }
    }


    public String encounters() {
        d.setSides(3);
        d.roll();
        int outcome = d.getRollValue();
        if (outcome == 1) {
            return "Treasure Chest";
        } else if (outcome == 2) {
            return "Monster";
        } else {
            return "NPC";
        }
    }

    public void start() {
        chooseStartingPlayer();
        game();
    }

    private void game(){
        System.out.print("Enter player 1 name: ");
        String p1 = scan.nextLine();

        System.out.println("Enter player 2 name: Player");
        Player first = new Player(p1, 100, 10);
        Player second = new Player();
        String ans = "";
        while (!gameOver) {

            System.out.print("You find yourselves at a crossroad. Would you like to go forwards, left, or right? ");
            ans = scan.nextLine();
            while (!(ans.equals("forwards") || ans.equals("left") || ans.equals("right"))) {
                System.out.print("Please choose an available path: ");
                ans = scan.nextLine();
            }
            Encounters en = new Encounters(this, player1, player2);
            String event = encounters();
            System.out.println("You go " + ans + " and find a " + event);

            if (event.equals("Treasure Chest")) {
                en.chest();
            } else if (event.equals("Monster")) {
                en.combat();
            } else {
                en.npc();
            }

            if(player1.isDead() && player2.isDead()){
                setGameOver();
            }
        }
        replay();
    }

    private void replay(){
        if (player1.isDead() && player2.isDead() && gameOver) {
            System.out.print("Both players are dead, would both like to restart for a new game? (y / n): ");
            String choice = scan.nextLine();
            if (choice.equals("y")) {
                gameOver = false;
                player1.reset();
                player2.reset();
                game();
            } else if (choice.equals("n")){
                System.out.println("Thank you for playing the game!");
            }
        }
    }

    public void setGameOver(){
        gameOver = true;
    }
}
