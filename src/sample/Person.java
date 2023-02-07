package sample;

//Person class to store the player and its attributes.
public class Person {
    //variables for the player's name, health, permHealth (health including health boost purchases), kills, money, level, deaths, and strength.
    private String name = "";
    double health = 100;
    double permHealth = 100;
    private int kills = 0;
    private int money = 10;
    private int level = 0;
    int deaths = 0;
    private int strength = 0;

    //getName accessor to return the name of the person.
    public String getName() {
        //returns the name of the person.
        return name;
    }

    //setName mutator to set the name of the person.
    public void setName(String n) {
        //sets name to the parameter n.
        name = n;
    }

    //getHealth accessor to return the health of the person.
    public double getHealth() {
        //returns the health of the person.
        return health;
    }

    //setHealth mutator to set the health of the person.
    public void setHealth(double n) {
        //sets health to the parameter n.
        health = n;
    }

    //getKills accessor to return the kills of the person.
    public int getKills() {
        //returns the kills of the person.
        return kills;
    }

    //setKills mutator to set the kills of the person.
    public void setKills(int n) {
        //sets kills to the parameter n.
        kills = n;
    }

    //getMoney accessor to return the money of the person.
    public int getMoney() {
        //returns the money of the person.
        return money;
    }

    //setMoney mutator to set the money of the person.
    public void setMoney(int n) {
        //sets money to the parameter n.
        money = n;
    }

    //getLevel accessor to return the level of the person.
    public int getLevel() {
        //returns the level of the person.
        return level;
    }

    //setLevel mutator to set the level of the person.
    public void setLevel(int n) {
        //sets level to the parameter n.
        level = n;
    }

    //getDeaths accessor to return the deaths of the person.
    public int getDeaths() {
        //returns the deaths of the person.
        return deaths;
    }

    //setDeaths mutator to set the deaths of the person.
    public void setDeaths(int n) {
        //sets deaths to the parameter n.
        deaths = n;
    }

    //getPermHealth accessor to return the permanent health of the person.
    public double getPermHealth() {
        //returns the permanent health of the person.
        return permHealth;
    }

    //setPermHealth mutator to set the permanent health of the person.
    public void setPermHealth(double n) {
        //sets permanent health to the parameter n.
        permHealth = n;
    }

    //getStrength accessor to return the strength of the person.
    public int getStrength() {
        //returns the strength of the person.
        return strength;
    }

    //setStrength mutator to set the strength of the person.
    public void setStrength(int n) {
        //sets strength to the parameter n.
        strength = n;
    }
}