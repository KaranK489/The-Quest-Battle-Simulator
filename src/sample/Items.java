package sample;

//Items class to store items and their attributes.
public class Items {
    //variables for name, damage, price, protection, and isWeapon to know if an item is a weapon or not.
    private String name;
    private int damage;
    private int price;
    private int protection;
    private boolean isWeapon;

    //constructor that sets the name and price of the items to the respective parameters; then, if the item is a weapon, it will set the damage to that respective parameter, and if it is not a weapon, it will set the protection to that parameter.
    public Items(String n, int d, int p, boolean weapon) {
        //name, price, and isWeapon set to respective parameters.
        name = n;
        price = p;
        isWeapon = weapon;
        //if it is a weapon,
        if (weapon) {
            //the damage is set to the value of d,
            damage = d;
            //and if not,
        } else {
            //the protection is set to the value of d.
            protection = d;
        }
    }

    //getName accessor to return the name of the item.
    public String getName() {
        //returns the name of the item.
        return name;
    }

    //setName mutator to set the name of the item.
    public void setName(String n) {
        //sets name to the parameter n.
        name = n;
    }

    //getDamage accessor to return the damage of the item.
    public int getDamage() {
        //returns the damage of the item.
        return damage;
    }

    //setDamage mutator set the damage of the item.
    public void setDamage(int n) {
        //sets damage to the parameter n.
        damage = n;
    }

    //getPrice accessor to return the price of the item.
    public int getPrice() {
        //returns the price of the item.
        return price;
    }

    //setPrice mutator set the price of the item.
    public void setPrice(int n) {
        //sets price to the parameter n.
        price = n;
    }

    //getProtection accessor to return the protection of the item.
    public int getProtection() {
        //returns the protection of the item.
        return protection;
    }

    //setProtection mutator set the protection of the item.
    public void setProtection(int n) {
        //sets protection to the parameter n.
        protection = n;
    }

    //getWeapon accessor to return the boolean stating if the item is a weapon or not.
    public boolean getWeapon() {
        //returns the state of the item.
        return isWeapon;
    }
}