import java.util.Scanner;
public class DungeonsLogic {
    static Dice d = new Dice(0);
    static Scanner scan = new Scanner(System.in);
    static


    String ans = "";

    public static void chest() {
        System.out.println("You find a chest, open it?");
        ans = scan.nextLine();

        if (ans.equals("yes")) {
            DungeonsLogic.chestLoot();
        } else {
            System.out.println("You walk away from the chest, and come across another crossroad.");
        }
    }


    public static void monster() {
        d.setSides(3);
        d.roll();
        if(d.getRollValue() == 1){

        } else if(d.getRollValue() == 2){

        } else {

        }

    }

    public static void npc() {
        d.setSides(100);
        d.roll();
        System.out.print("You meet an old man by the hallway, do you want to talk to him? (y / n): ");
        ans = scan.nextLine();
        if (ans.equals("y")) {
            if(d.getRollValue() < 5 && d.getRollValue() == 5){
                System.out.print("The old man decided to give you full iron armor (+20 HP)");
            } else if(d.getRollValue() <= 10){
                System.out.println("The old man felt nice enough to give you a new weapon out of pity, you upgraded to a iron sword");
            } else if(d.getRollValue() <= 30){
                System.out.println("You meet the old man and he promises to lead you somewhere to treasure, but it turns out he led you to an unknown monster");
            } else {
                System.out.print("The old man asks you to give him a word: ");
                String word = scan.nextLine();
                for (int i = word.length(); i > 0; i--){
                    System.out.print(word.substring(i - 1, i));
                }
                System.out.print("It looks like the old man said the word backwards! He has wasted your time, you move onto the next crossroad");
            }
        } else {
            System.out.print("You decide to continue on your journey");
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
                if (d.getRollValue() <= 1) {
                    return "You die instantly from a jeep";
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
    }
}
