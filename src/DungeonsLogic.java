import java.util.Scanner;
public class DungeonsLogic {
    private Player currentPlayer;
    Player player1 = new Player("Player 1", 100, 10);
    Player player2 = new Player();
    Dungeons dnd = new Dungeons(player1, player2);
    Dice d = new Dice(0);
    boolean gameOver;

    public DungeonsLogic(){
        currentPlayer = null;
        gameOver = false;
        Dungeons dnd = new Dungeons(player1, player2);
        player1 = dnd.getPlayer1();
        player2 = dnd.getPlayer2();
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
            if(currentPlayer == player1){
                player1.setAtk(100);
            } else {
                player2.setAtk(100);
            }
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

    public String encounters(){
        d.setSides(3);
        d.roll();
        int outcome = d.getRollValue();
        if(outcome == 1){
            return "Treasure Chest";
        } else if(outcome == 2){
            return "Monster";
        }else {
            return "NPC";
        }
    }

    public void start(){
        String ans = "";
        boolean gameover = false;
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter player 1 name: ");
        String p1 = scan.nextLine();

        System.out.println("Enter player 2 name: Player");

        Player first = new Player(p1, 100, 10);
        Player second = new Player();
        Dungeons dnd = new Dungeons(first, second);
        chooseStartingPlayer();
        while (!gameover) {
            System.out.print("You find yourselves at a crossroad. Would you like to go forwards, left, or right? ");
            ans = scan.nextLine();
            while (!(ans.equals("forwards") || ans.equals("left") || ans.equals("right"))) {
                System.out.print("Please choose an available path: ");
                ans = scan.nextLine();
            }
            Encounters en = new Encounters();
            String event = encounters();
            System.out.println("You go " + ans + " and find a " + event);

            if (event.equals("Treasure Chest")) {
                en.chest();
            } else if (event.equals("Monster")) {
                en.monster();
            } else {
                en.npc();
            }

            if (first.isDead() && second.isDead()) {
                System.out.print("Both players are dead, would both like to restart for a new game? (y / n): ");
                String choice = scan.nextLine();
                if (choice.equals("y")) {
                    gameover = false;
                    first.reset();
                    second.reset();
                }
            }
        }
        System.out.println("Thank you for playing the game!");
    }
}