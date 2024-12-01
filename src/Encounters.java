import java.util.Scanner;
public class Encounters {
    private Dungeons dnd;
    private Player currentPlayer;
    private Player player1 = dnd.getPlayer1();
    private Player player2 = dnd.getPlayer2();
    DungeonsLogic dndLogic = new DungeonsLogic();
    String str = "";
    Dice d = new Dice(0);
    Scanner scan = new Scanner(System.in);
    public Encounters(Player player1, Player player2){
        dnd = new Dungeons(player1, player2);
    }

    public String chest(){
        str = dndLogic.chestLoot();
        if (str.equals("Gameover")){
            return "Gameover";
        }else {
            return str;
        }
    }

    private void playerSwap(){
        if(currentPlayer == player1){
            if(player2.isDead()){
                currentPlayer = player2;
            }
        } else if (currentPlayer == player2){
            if(player1.isDead()){
                currentPlayer = player1;
            }
        }
    }

    public void monster(){
        combat();
    }

    public void npc(){
        d.setSides(100);
        d.roll();
        System.out.print("You meet an old man by the hallway, do you want to talk to him? (y / n): ");
        String ans = scan.nextLine();
        if (ans.equals("y")) {
            if(d.getRollValue() <= 5){
                System.out.print("The old man decided to give you full iron armor (+20 HP)");
                if(currentPlayer == player1){
                    player1.setHealth(20);
                } else {
                    player2.setHealth(20);
                }
            } else if(d.getRollValue() <= 10){
                System.out.println("The old man felt nice enough to give you a new weapon out of pity, you upgraded to a iron sword");
                if(currentPlayer == player1){
                    player1.setAtk(7);
                } else {
                    player2.setAtk(7);
                }
            } else if(d.getRollValue() <= 30){
                System.out.println("You meet the old man and he promises to lead you to treasure, but it turns out he led you to an unknown monster");
                monster();
            } else {
                System.out.print("The old man asks you to give him a word: ");
                String word = scan.nextLine();
                for (int i = word.length(); i > 0; i--){
                    System.out.print(word.substring(i - 1, i));
                }
                System.out.print("It looks like the old man said the word backwards! \nHe has wasted your time. \nYou move onto the next crossroad");
            }
        } else {
            System.out.print("You decide to continue on your journey");
        }
    }

    private void combat(){
        DungeonsLogic dndLogic = new DungeonsLogic();
        dndLogic.chooseStartingPlayer();
        String monster = dnd.monsterGenerator();
        Player currentPlayer = dndLogic.getCurrentPlayer();
        while (player1.isDead() || player2.isDead()){
            if(monster.equals("Young Gold Dragon")){
                YoungGoldDragon yGD = new YoungGoldDragon();
                System.out.println("It's " + currentPlayer + "'s turn!");
                int dmg = currentPlayer.getAtk();
                System.out.print(currentPlayer.getName() + " attacks the Young Gold Dragon for " + dmg + " damage!");
                yGD.hitYGD(dmg);
                playerSwap();
                if(yGD.isAlive()){
                    String move = yGD.attack();
                    int monsterDmg = yGD.dmgValue(move);
                    int victim = dnd.attackTarget();
                    if(victim == 1 && currentPlayer == player1){
                        System.out.println("The Young Gold Dragon attacks " + player1.getName() + " with " + move + " for " + monsterDmg + " damage!");
                        player1.takeDamage(monsterDmg);
                    } else if (currentPlayer == player2){
                        System.out.println("The Young Gold Dragon attacks " + player2.getName() + " with " + move + " for " + monsterDmg + " damage!");
                        player2.takeDamage(monsterDmg);
                    }

                    if(currentPlayer.isDead()){
                        System.out.println(currentPlayer.getName() + " has died");
                        playerSwap();
                    }
                } else {
                    System.out.println("The dragon has been defeated!");
                    System.out.println("Player 1's HP: " + player1.getHealth());
                    System.out.println("Player 2's HP: " + player2.getHealth());
                    System.out.println("You continue on the crossroad");
                }
            } else if(monster.equals("Shadow Ghast")){
                ShadowGhast sGH = new ShadowGhast();
                System.out.println("It's " + currentPlayer + "'s turn!");
                int dmg = currentPlayer.getAtk();
                System.out.print(currentPlayer.getName() + " attacks the Young Gold Dragon for " + dmg + " damage!");
                sGH.hitSG(dmg);
                playerSwap();
                if(sGH.isAlive()){
                    String move = sGH.attack();
                    int monsterDmg = sGH.DamageValSG(move);
                    int victim = dnd.attackTarget();
                    if(victim == 1 && currentPlayer == player1){
                        System.out.println("The Shadow Ghast attacks " + player1.getName() + " with " + move + " for " + monsterDmg + " damage!");
                        player1.takeDamage(monsterDmg);
                    } else if (currentPlayer == player2){
                        System.out.println("The Shadow Ghast attacks " + player2.getName() + " with " + move + " for " + monsterDmg + " damage!");
                        player2.takeDamage(monsterDmg);
                    }
                } else {
                    System.out.println("The Shadow Ghast has been defeated!");
                    System.out.println("You continue on the crossroad");
                }
            } else {
                AncientDeepCrow aDC = new AncientDeepCrow();
                System.out.println("It's " + currentPlayer + "'s turn!");
                int dmg = currentPlayer.getAtk();
                System.out.print(currentPlayer.getName() + " attacks the Young Gold Dragon for " + dmg + " damage!");
                aDC.hitADC(dmg);
                playerSwap();
                if(aDC.isAlive()){
                    String move = aDC.attack();
                    int monsterDmg = aDC.DamageValADC(move);
                    int victim = dnd.attackTarget();
                    if(victim == 1 && currentPlayer == player1){
                        System.out.println("The Shadow Ghast attacks " + player1.getName() + " with " + move + " for " + monsterDmg + " damage!");
                        player1.takeDamage(monsterDmg);
                    } else if (currentPlayer == player2){
                        System.out.println("The Shadow Ghast attacks " + player2.getName() + " with " + move + " for " + monsterDmg + " damage!");
                        player2.takeDamage(monsterDmg);
                    }
                } else {
                    System.out.println("The Ancient Deep Crow has been defeated!");
                    System.out.println("You continue on the crossroad");
                }
            }
        }
        System.out.println("Both players have died");

    }
}
