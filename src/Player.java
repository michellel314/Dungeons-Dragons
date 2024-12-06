public class Player {
    private String name;
    private int health;
    private int atk;
    public Player (String name, int health, int atk){
        this.name = name;
        this.health = health;
        this.atk = atk;
    }

    public Player(){
        health = 100;
        atk = 5;
    }

    public String getName(){
        return name;
    }

    public int getHealth(){
        return health;
    }

    public int getAtk(){
        return atk;
    }

    public void takeDamage(int dmg){
        int loss = health - dmg;
       if (loss > 0){
           health -= dmg;
       }

    }

    public boolean isDead(){
       return health <= 0;
    }

    public void setHealth(int newHp){
        health += newHp;
    }

    public void setAtk(int newAtk){
        atk = newAtk;
    }

    public void reset(){
        health = 100;
        atk = 5;
    }
}
