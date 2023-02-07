package sample;

//Enemies class to store enemies and their health attribute.
public class Enemies {
    //variable for health of the enemies.
    private int health;

    //getHealth accessor to return the health of the enemy.
    public int getHealth() {
        //returns the health of the enemy.
        return health;
    }

    //setHealth mutator set the health of the enemy.
    public void setHealth(int n) {
        //sets health to the parameter n.
        health = n;
    }
}