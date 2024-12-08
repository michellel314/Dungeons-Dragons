// This class is used for creating a Dice object
// It mainly helps in randomizing certain aspects of the game like the monster's attacks and etc

public class Dice {
    private int numSides;
    int roll = 0;
    int timesRolled = 0;

    public Dice (int numSides){
        this.numSides = numSides;
    }

    public void roll(){
        roll = ((int) (Math.random() * ((numSides - 1) + 1)));
        timesRolled++;
    }

    public int getRollValue(){
        return roll;
    }

    public void setSides(int newSide){
        numSides = newSide;
    }
}