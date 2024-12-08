import java.util.Scanner;
public class Encounters {
    private Player currentPlayer;
    private Player player1;
    private Player player2;
    DungeonsLogic dndLogic;
    Dice d = new Dice(0);
    Scanner scan = new Scanner(System.in);

    // This constructor is whenever an Encounters object is created
    // It allows a DungeonsLogic object to be created to share the information from the two players
    public Encounters(DungeonsLogic dndLogic, Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        this.dndLogic = dndLogic;
        currentPlayer = player1;
    }

    public void chest(){
        dndLogic.chestLoot();
    }

    private void playerSwap(){
        if (currentPlayer == player1) {
            if(!player2.isDead()){
                currentPlayer = player2;
                currentPlayer.setName(player2.getName());
            }
        } else if (currentPlayer == player2) {
            if (!player1.isDead()) {
                currentPlayer = player1;
                currentPlayer.setName(player1.getName());
            }
        }
    }

    // The method is when the players interact with an NPC
    // There is a certain chance for the players to receive a reward or nothing (maybe forced to fight)
    // The most likely chance is the NPC saying a word backwards
    public void npc(){
        d.setSides(100);
        d.roll();
        System.out.print("You meet an old man along the crossroad, do you want to talk to him? (y / n): ");
        String ans = scan.nextLine();
        if (ans.equals("y")) {
            if(d.getRollValue() <= 5){
                System.out.println("The old man decided to give you full iron armor (+20 HP)");
                player1.setHealth(20);
                player2.setHealth(20);
            } else if(d.getRollValue() <= 10){
                System.out.println("The old man felt nice enough to give you a new weapon out of pity, you upgraded to a iron sword (+6 Atk)");
                player1.setAtk(7);
                player2.setAtk();
            } else if(d.getRollValue() <= 30){
                System.out.println("You meet the old man and he promises to lead you to treasure, but it turns out he led you to an unknown monster");
                combat();
            } else {
                System.out.print("The old man asks you to give him a word: ");
                String word = scan.nextLine();
                for (int i = word.length(); i > 0; i--){
                    System.out.print(word.substring(i - 1, i));
                }
                System.out.println();
                System.out.println("It looks like the old man said the word backwards! \nHe has wasted your time.");
            }
        }
    }

    // This method is for the fight between the two players and the monster
    // The monster is randomized every time the two players encounter one
    // The players take turns attacking the monster, if they both die then the game restarts
    // If the monster gets defeated, it will show the statistics of both players, whether one of them dies
    public void combat(){
        d.setSides(3);
        d.roll();
        int num = d.getRollValue();
        if (num == 1){
            YoungGoldDragon yGD = new YoungGoldDragon();
            System.out.println(yGD.createYoungGoldDragon());
            while(!(player1.isDead()) || (!player2.isDead())){
                System.out.println("It's " + currentPlayer.getName() + "'s turn!");
                int dmg = currentPlayer.getAtk();
                System.out.println(currentPlayer.getName() + " attacks the Young Gold Dragon for " + dmg + " damage!");
                yGD.hitYGD(dmg);
                if(yGD.getYGDHP() > 0){
                    System.out.println("Young Gold Dragon's HP: " + yGD.getYGDHP());
                }
                playerSwap();
                if(yGD.isAlive()){
                    String move = yGD.attack();
                    int monsterDmg = yGD.dmgValue(move);
                    int victim = attackTarget();
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
                    if(player1.isDead()){
                        System.out.println("Player 1's HP: 0");
                    } else {
                        System.out.println("Player 1's HP: " + player1.getHealth());
                    }
                    System.out.println("Player 1's Atk: " + player1.getAtk());
                    if(player2.isDead()){
                        System.out.println("Player 2's HP: 0");
                    } else {
                        System.out.println("Player 2's HP: " + player2.getHealth());
                    }
                    System.out.println("Player 2's Atk: " + player2.getAtk());
                    break;
                }
            }
        } else if (num == 2) {
            ShadowGhast sGH = new ShadowGhast();
            System.out.println(sGH.createShadowGhast());
            while (!(player1.isDead()) || (!player2.isDead())) {
                System.out.println("It's " + currentPlayer.getName() + "'s turn!");
                int dmg = currentPlayer.getAtk();
                System.out.println(currentPlayer.getName() + " attacks the Shadow Ghast with " + dmg + " damage!");
                sGH.hitSG(dmg);
                if(sGH.getSGHP() > 0){
                    System.out.println("Shadow Ghast's HP: " + sGH.getSGHP());
                }
                playerSwap();
                if (sGH.isAlive()) {
                    String move = sGH.attack();
                    int monsterDmg = sGH.DamageValSG(move);
                    int victim = attackTarget();
                    if (victim == 1 && currentPlayer == player1) {
                        System.out.println("The Shadow Ghast attacks " + player1.getName() + " with " + move + " for " + monsterDmg + " damage!");
                        player1.takeDamage(monsterDmg);
                    } else if (currentPlayer == player2) {
                        System.out.println("The Shadow Ghast attacks " + player2.getName() + " with " + move + " for " + monsterDmg + " damage!");
                        player2.takeDamage(monsterDmg);
                    }

                    if (currentPlayer.isDead()) {
                        System.out.println(currentPlayer.getName() + " has died");
                        playerSwap();
                    }
                } else {
                    System.out.println("The Shadow Ghast has been defeated!");
                    if(player1.isDead()){
                        System.out.println("Player 1's HP: 0");
                    } else {
                        System.out.println("Player 1's HP: " + player1.getHealth());
                    }
                    System.out.println("Player 1's Atk: " + player1.getAtk());
                    if(player2.isDead()){
                        System.out.println("Player 2's HP: 0");
                    } else {
                        System.out.println("Player 2's HP: " + player2.getHealth());
                    }
                    System.out.println("Player 2's Atk: " + player2.getAtk());
                    break;
                }
            }
        } else {
            AncientDeepCrow aDC = new AncientDeepCrow();
            System.out.println(aDC.createADC());
            while(!(player1.isDead()) || (!player2.isDead())){
                System.out.println("It's " + currentPlayer.getName() + "'s turn!");
                int dmg = currentPlayer.getAtk();
                System.out.println(currentPlayer.getName() + " attacks the Ancient Deep Crow for " + dmg + " damage!");
                aDC.hitADC(dmg);
                if(aDC.getHp() > 0){
                    System.out.println("Ancient Deep Crow's HP: " + aDC.getHp());
                }
                playerSwap();
                if(aDC.isAlive()){
                    String move = aDC.attack();
                    int monsterDmg = aDC.DamageValADC(move);
                    if(move.equals("Infectious Penalty")){
                        System.out.println("The Ancient Deep Crow attacks " + player1.getName() + " with " + move + " for " + monsterDmg + " damage!");
                        player1.takeDamage(monsterDmg);
                        System.out.println("The Ancient Deep Crow attacks " + player2.getName() + " with " + move + " for " + monsterDmg + " damage!");
                        player2.takeDamage(monsterDmg);
                    } else {
                        int victim = attackTarget();
                        if (victim == 1 && currentPlayer == player1) {
                            System.out.println("The Ancient Deep Crow attacks " + player1.getName() + " with " + move + " for " + monsterDmg + " damage!");
                            player1.takeDamage(monsterDmg);
                        } else if (currentPlayer == player2) {
                            System.out.println("The Ancient Deep Crow attacks " + player2.getName() + " with " + move + " for " + monsterDmg + " damage!");
                            player2.takeDamage(monsterDmg);
                        }
                    }
                    if (currentPlayer.isDead()) {
                        System.out.println(currentPlayer.getName() + " has died");
                        playerSwap();
                    }
                } else {
                    System.out.println("The Ancient Deep Crow has been defeated!");
                    if(player1.isDead()){
                        System.out.println("Player 1's HP: 0");
                    } else {
                        System.out.println("Player 1's HP: " + player1.getHealth());
                    }
                    System.out.println("Player 1's Atk: " + player1.getAtk());
                    if(player2.isDead()){
                        System.out.println("Player 2's HP: 0");
                    } else {
                        System.out.println("Player 2's HP: " + player2.getHealth());
                    }
                    System.out.println("Player 2's Atk: " + player2.getAtk());
                    break;
                }
            }
        }
    }
    public int attackTarget() {
        d.setSides(2);
        d.roll();
        return d.getRollValue();
    }
}