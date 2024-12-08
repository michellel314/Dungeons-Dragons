import java.util.Scanner;
public class DungeonsLogic {
    private Player currentPlayer;
    Player player1;
    Player player2;
    Dice d = new Dice(0);
    boolean gameOver;
    Scanner scan;

    public DungeonsLogic() {
        player1 = new Player("Player 1", 120, 10);
        player2 = new Player();
        currentPlayer = null;
        gameOver = false;
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

    // When the players encounter a chest, there is a 50% chance of both players instantly dying
    // The other half is calling the helper method chestStuff in randomizing what reward they will get
    public void chestLoot() {
        d.setSides(2);
        d.roll();
        if (d.getRollValue() == 1) {
            System.out.println("The chest explodes.");
            setGameOver();
        } else {
            d.setSides(100);
            d.roll();
            chestStuff();
        }
    }

    private void chestStuff() {
        if (d.getRollValue() == 1) {
            System.out.println("You get a jeep");
            player1.setAtk(999999999);
            player2.setAtk(999999999);
        } else if (d.getRollValue() == 2) {
            System.out.println("You get a gun (+10 atk)");
            player1.setAtk(10);
            player2.setAtk();
        } else if (d.getRollValue() <= 7) {
            System.out.println("You upgraded to an iron sword (+6 atk)");
            player1.setAtk(6);
            player2.setAtk();
        } else if (d.getRollValue() <= 40) {
            d.setSides(2);
            if (d.getRollValue() == 1) {
                System.out.println("You obtained a bow and arrow (+3 atk)");
                player1.setAtk(3);
                player2.setAtk();
            } else {
                System.out.println("You obtained an artifact (+35 HP)");
                player1.setHealth(35);
                player2.setHealth(35);
            }
        } else {
            System.out.println("You obtained a rusty dagger (+2 atk)");
            player1.setAtk(2);
            player2.setAtk();
        }
    }
// This method is called by the Encounters class once the players are in an event
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

    // This method is when the game starts and calls upon another method called game
    // The player who starts gets randomized at the beginning of the game
    public void start() {
        chooseStartingPlayer();
        game();
    }

    // This method is what happens during the game
    // Once the first player enters the name (second one is default), they can choose which direction they want to go on
    // The direction they go will have a randomized event of finding a chest, monster, or NPC
    // The Encounters object that is made for the events has a parameter called 'this' as a reference to the DungeonsLogic object dndLogic
    // The game will end once both players die
    private void game(){
        System.out.print("Enter player 1 name: ");
        String p1 = scan.nextLine();
        player1.setName(p1);

        System.out.println("Enter player 2 name: Player");
        String ans = "";
        while (!gameOver) {
            System.out.print("You find yourselves at a crossroad. Would you like to go forward, left, or right? ");
            ans = scan.nextLine();
            while (!(ans.equals("forward") || ans.equals("left") || ans.equals("right"))) {
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

    // The helper method is called once the two players die
    // The first REAL player can decide whether they want to restart the game
    // If they choose to restart, the players' statistics on health and attack also reset back the original values
    // If not, then the game ends while thanking the player
    private void replay(){
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

    public void setGameOver(){
        gameOver = true;
    }
}