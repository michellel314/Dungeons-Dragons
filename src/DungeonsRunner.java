import java.util.Scanner;
public class DungeonsRunner {
    public static void main(String[] args) {
        String ans = "";
        String encounter = "";
        boolean gameover = false;
        Scanner scan = new Scanner(System.in);


        System.out.print("Enter player 1 name: ");
        String p1 = scan.nextLine();


        System.out.print("Enter player 2 name: ");
        String p2 = scan.nextLine();


        Dungeons dungeon = new Dungeons(p1, p2);


        while (gameover = false) {
            System.out.print("You find yourselves at a crossroad. Would you like to go forwards, left, or right? ");
            ans = scan.nextLine();
            while (!(ans.equals("forwards") || ans.equals("left") || ans.equals("right"))) {
                System.out.print("Please choose an available path: ");
                ans = scan.nextLine();
            }
            encounter = dungeon.encounter();
            System.out.print("You go  " + ans + " and find a " + encounter);


            if (encounter.equals("Treasure Chest")) {
                DungeonsLogic.chest();
            } else if (encounter.equals("Monster")) {
                DungeonsLogic.monster();
            } else {
                DungeonsLogic.npc();
            }
        }

    }
}
