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
