package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

//Controller class, with the main code that functions the program.
public class Controller {
    //FXML attributes, such as text fields, labels, buttons, listviews, progress bars, and progress indicators.
    @FXML
    TextField txtInputName;
    @FXML
    Label lblDisplay, lblDisplayMoneyProfit, lblDisplayDeaths, lblDisplayInstructions2, lblDisplayMoneyLoss, lblDisplayText1, lblDisplayBuyStatus, lblDisplayHealth, lblDisplayKills, lblDisplayMoney, lblDisplayEnemy1HP, lblDisplayEnemy2HP, lblDisplayEnemy3HP, lblDisplayEnemy4HP, lblDisplayWinner, lblDisplayAttackStatus, lblDisplayLevel, lblDisplayOGEnemyHP, lblDisplayOGHealth, lblDisplayInstructions, lblDisplayItemShop, lblDisplayInventory, lblDisplayEffects, lblDisplayArmor, lblDisplayWeapons, lblDisplayArmor1, lblDisplayEffects1, lblDisplayWeapons1, lblDisplayInstructions1, lblDisplayInstructions11,lblDisplayInstructions111,lblDisplayInstructions1111;
    @FXML
    Button btnLvl1, btnLvl2, btnLvl3, btnLvl4, btnFinalLvl, btnRestart;
    @FXML
    ListView<String> lstViewWeapons, lstViewShopWeapons, lstViewShopArmor, lstViewArmor, lstViewShopEffects, lstViewEffects;
    @FXML
    ProgressBar progressBar;
    @FXML
    ProgressIndicator progressIndicator;
    //person object set to a new Person, as this is the player object.
    Person person = new Person();
    //enemies array list, which will contain objects from the Enemies class inside of it; this is made so that multiple enemies can be stored into one array list.
    ArrayList<Enemies> enemies = new ArrayList<>();
    //array lists for all the weapons, armor, and effects, which will contain objects from the Items class inside of it; this is made so that multiple weapons, armor, and effects can be stored inside their respective array lists.
    ArrayList<Items> allWeapons = new ArrayList<>();
    ArrayList<Items> allArmor = new ArrayList<>();
    ArrayList<Items> allEffects = new ArrayList<>();
    //array lists for the player's weapons, armor, and effects, which will contain objects from the Items class inside of it; this is made so that multiple weapons, armor, and effects can be stored inside their respective array lists; these array lists are made so that the items of the player can be separated from the total items array lists, in order to know which items the player has, but it still utilizes the items class since it is essentially copying items over from the array lists with all the items into these array lists with the player items.
    ArrayList<Items> playerWeapons = new ArrayList<>();
    ArrayList<Items> playerArmor = new ArrayList<>();
    ArrayList<Items> playerEffects = new ArrayList<>();

    @FXML
    //initialize method to initialize the program.
    public void initialize() {
        //loop that adds 10 enemy objects to the enemies array list, since there are 10 total enemies in the game.
        for (int i = 0; i < 11; i++) {
            //adds an enemy object to enemies array list.
            enemies.add(new Enemies());
        }
        //this calls a displayStats method.
        displayStats();
        //these disable the buttons for every level but level 1, as they are unlocked later on, as you complete the game.
        btnLvl2.setDisable(true);
        btnLvl3.setDisable(true);
        btnLvl4.setDisable(true);
        btnFinalLvl.setDisable(true);
        //runs a showFXML function.
        showFXML(false);
        //adds 9 weapons to the all weapons array list, which will consist of a variety of weapons.
        //the parameters used here are the name string, int d which is the damage/protection (damage in this case), int p which is the price, and the weapon boolean which states if the item is a weapon or not (weapons in this case).
        allWeapons.add(new Items("Knife", 5, 5, true));
        allWeapons.add(new Items("Bow", 10, 15, true));
        allWeapons.add(new Items("Axe", 20, 30, true));
        allWeapons.add(new Items("Sword", 35, 50, true));
        allWeapons.add(new Items("1000° Sword", 50, 100, true));
        allWeapons.add(new Items("Flamethrower", 150, 500, true));
        allWeapons.add(new Items("RPG", 500, 5000, true));
        allWeapons.add(new Items("Tank", 3000, 25000, true));
        allWeapons.add(new Items("B-52 Bomber", 15000, 100000, true));
        //adds 6 types of armor to the all armor array list, which will consist of a variety of armor.
        //the parameters used here are the name string, int d which is the damage/protection (protection in this case), int p which is the price, and the weapon boolean which states if the item is a weapon or not (not weapons in this case).
        allArmor.add(new Items("Basic Clothes Set", 1, 5, false));
        allArmor.add(new Items("Leather Armor Set", 10, 150, false));
        allArmor.add(new Items("Golden Armor Set", 30, 500, false));
        allArmor.add(new Items("Chain Armor Set", 45, 5000, false));
        allArmor.add(new Items("Iron Armor Set", 60, 25000, false));
        allArmor.add(new Items("Diamond Armor Set", 80, 100000, false));
        //adds 2 types of effects, each with 3 different strengths/concentrations, to the all effects array list, which will consist of a variety of effects.
        //the parameters used here are the name string, int d which is the damage/protection, int p which is the price, and the weapon boolean which states if the item is a weapon or not.
        allEffects.add(new Items(("Health Boost I"), 5, 100, false));
        allEffects.add(new Items(("Health Boost II"), 50, 2500, false));
        allEffects.add(new Items(("Health Boost III"), 1000, 25000, false));
        allEffects.add(new Items(("Strength I"), 5, 100, true));
        allEffects.add(new Items(("Strength II"), 50, 5000, true));
        allEffects.add(new Items(("Strength III"), 500, 30000, true));
        //loop which adds all the weapons from the all weapons array list to the weapons shop listview, so that the player can see all the weapons available to buy.
        for (int i = 0; i < allWeapons.size(); i++) {
            lstViewShopWeapons.getItems().add(allWeapons.get(i).getName() + ": $" + allWeapons.get(i).getPrice() + "; Damage: " + allWeapons.get(i).getDamage());
        }
        //loop which adds all the armor from the all armor array list to the armor shop listview, so that the player can see all the armor available to buy.
        for (int i = 0; i < allArmor.size(); i++) {
            lstViewShopArmor.getItems().add(allArmor.get(i).getName() + ": $" + allArmor.get(i).getPrice() + "; Protection: " + allArmor.get(i).getProtection() + "%");
        }
        //loop which adds all the effects from the all effects array list to the effects shop listview, so that the player can see all the effects available to buy.
        for (int i = 0; i < allEffects.size(); i++) {
            if (allEffects.get(i).getWeapon()) {
                lstViewShopEffects.getItems().add(allEffects.get(i).getName() + ": $" + allEffects.get(i).getPrice() + "; Extra Damage: " + allEffects.get(i).getDamage());
            } else {
                lstViewShopEffects.getItems().add(allEffects.get(i).getName() + ": $" + allEffects.get(i).getPrice() + "; Health Boost: " + allEffects.get(i).getProtection());
            }
        }
    }

    //showFXML method which basically either makes the majority of the program visible or not, depending on the boolean provided in the parameter.
    public void showFXML(boolean a) {
        //many lines of code that set different FXML attributes to visible or not, depending on the parameter.
        lstViewShopArmor.setVisible(a);
        lstViewShopEffects.setVisible(a);
        lstViewShopWeapons.setVisible(a);
        lstViewWeapons.setVisible(a);
        lstViewArmor.setVisible(a);
        lstViewEffects.setVisible(a);
        lblDisplayEffects.setVisible(a);
        lblDisplayArmor.setVisible(a);
        lblDisplayWeapons.setVisible(a);
        lblDisplayEffects1.setVisible(a);
        lblDisplayArmor1.setVisible(a);
        lblDisplayInstructions2.setVisible(a);
        lblDisplayInstructions1.setVisible(a);
        lblDisplayWeapons1.setVisible(a);
        lblDisplayInventory.setVisible(a);
        lblDisplayItemShop.setVisible(a);
        lblDisplayAttackStatus.setVisible(a);
        lblDisplayWinner.setVisible(a);
        lblDisplayOGEnemyHP.setVisible(a);
        lblDisplayOGHealth.setVisible(a);
        lblDisplayBuyStatus.setVisible(a);
        lblDisplayMoney.setVisible(a);
        lblDisplayDeaths.setVisible(a);
        lblDisplayKills.setVisible(a);
        lblDisplayEnemy1HP.setVisible(a);
        lblDisplayEnemy2HP.setVisible(a);
        lblDisplayEnemy3HP.setVisible(a);
        lblDisplayEnemy4HP.setVisible(a);
        lblDisplayHealth.setVisible(a);
        lblDisplayText1.setVisible(a);
        lblDisplayMoneyProfit.setVisible(a);
        lblDisplayMoneyLoss.setVisible(a);
        btnLvl1.setVisible(a);
        btnLvl2.setVisible(a);
        btnLvl3.setVisible(a);
        btnLvl4.setVisible(a);
        btnFinalLvl.setVisible(a);
        lblDisplayInstructions.setVisible(a);
        lblDisplay.setVisible(a);
        progressBar.setVisible(a);
        progressIndicator.setVisible(a);
        btnRestart.setVisible(a);
        lblDisplayLevel.setVisible(a);
        lblDisplayInstructions11.setVisible(a);
        lblDisplayInstructions111.setVisible(a);
        lblDisplayInstructions1111.setVisible(a);
        //this runs a displayStats method.
        displayStats();
    }

    @FXML
    //this method is ran everytime anything is entered into the text field for the name.
    private void handleClickName() {
        //this updates the player's name variable.
        person.setName(txtInputName.getText());
        //sets a label below the text field input for the name to the text shown below.
        lblDisplay.setText(person.getName() + ", you must battle all the enemies and fight your way to victory!");
        //runs a showFXML function.
        showFXML(true);
    }

    @FXML
    //this method displays many statistics about the players and other parts of the game.
    public void displayStats() {
        //the next two lines round the player's health to the nearest hundredth, since the player health is a double, in order to be more precise when displaying the health.
        person.setHealth(Math.round(person.getHealth() * 100));
        person.setHealth(person.getHealth() / 100);
        //displays the player's health.
        lblDisplayHealth.setText("Health: " + person.getHealth());
        //displays the player's kills.
        lblDisplayKills.setText("Kills: " + person.getKills());
        //displays the player's money.
        lblDisplayMoney.setText("Money: $" + person.getMoney());
        //displays the level the player has selected.
        lblDisplayLevel.setText("Level Selected: " + person.getLevel());
        //displays the player's deaths.
        lblDisplayDeaths.setText("Deaths: " + person.getDeaths());
        //displays the player's original health in the battle.
        lblDisplayOGHealth.setText("Your Original Health: " + person.getPermHealth());
        //series of if statements which sets certain aspects of the FXML to appear or not based on the level selected.
        //if the player selected level 1,
        if (person.getLevel() == 1) {
            //original enemy health label is set to 100.
            lblDisplayOGEnemyHP.setText("Original Enemy Health: 100");
            //the enemy 1 health label is set to visible, and the other 3 are set to be invisible.
            enemy1HPVisible(true);
            enemy2HPVisible(false);
            enemy3HPVisible(false);
            enemy4HPVisible(false);
            //runs a displayEnemyHealth method, and passes through 2 integers; the first one is the count of the enemies in the level (in this case, 1), and the second one is the starting position of the enemies in that level in the enemies array list (in this case 0).
            displayEnemyHealth(1, 0);
        }
        //if the player selected level 2,
        if (person.getLevel() == 2) {
            //original enemy health label is set to 1000.
            lblDisplayOGEnemyHP.setText("Original Enemy Health: 1000");
            //the enemy 1 and 2 health labels are set to visible, and the other 2 are set to be invisible.
            enemy1HPVisible(true);
            enemy2HPVisible(true);
            enemy3HPVisible(false);
            enemy4HPVisible(false);
            //runs a displayEnemyHealth method, and passes through 2 integers; the first one is the count of the enemies in the level (in this case, 2), and the second one is the starting position of the enemies in that level in the enemies array list (in this case 1).
            displayEnemyHealth(2, 1);
        }
        //if the player selected level 3,
        if (person.getLevel() == 3) {
            //original enemy health label is set to 2500.
            lblDisplayOGEnemyHP.setText("Original Enemy Health: 2500");
            //the enemy 1, 2, and 3 health labels are set to visible, and the other one is set to be invisible.
            enemy1HPVisible(true);
            enemy2HPVisible(true);
            enemy3HPVisible(true);
            enemy4HPVisible(false);
            //runs a displayEnemyHealth method, and passes through 2 integers; the first one is the count of the enemies in the level (in this case, 3), and the second one is the starting position of the enemies in that level in the enemies array list (in this case 3).
            displayEnemyHealth(3, 3);
        }
        //if the player selected level 4,
        if (person.getLevel() == 4) {
            //original enemy health label is set to 5000.
            lblDisplayOGEnemyHP.setText("Original Enemy Health: 5000");
            //all of the enemy health labels are set to visible.
            enemy1HPVisible(true);
            enemy2HPVisible(true);
            enemy3HPVisible(true);
            enemy4HPVisible(true);
            //runs a displayEnemyHealth method, and passes through 2 integers; the first one is the count of the enemies in the level (in this case, 4), and the second one is the starting position of the enemies in that level in the enemies array list (in this case 6).
            displayEnemyHealth(4, 6);
        }
        //if the player selected level 5,
        if (person.getLevel() == 5) {
            //original enemy health label is set to 50000.
            lblDisplayOGEnemyHP.setText("Original Enemy Health: 50000");
            //the enemy 1 health label is set to visible, and the other 3 are set to be invisible.
            enemy1HPVisible(true);
            enemy2HPVisible(false);
            enemy3HPVisible(false);
            enemy4HPVisible(false);
            //runs a displayEnemyHealth method, and passes through 2 integers; the first one is the count of the enemies in the level (in this case, 1), and the second one is the starting position of the enemies in that level in the enemies array list (in this case 10).
            displayEnemyHealth(1, 10);
        }
    }

    //this method displays the enemy health in their respective labels, based on how many enemies are being fought, also using two parameters.
    public void displayEnemyHealth(int enemy, int startingValue) {
        //the enemy parameter represents how many enemies are being fought, so that the loop knows how many enemy labels should be displayed; the second parameter represents what number the enemy should start being called at in the enemy array list (if it is level 2, the startingValue is 1, as the first enemy in level 2 is at position 1 in the enemies array list).
        //loop that runs for the amount of enemies there are.
        for (int i = startingValue; i < enemy + startingValue; i++) {
            //these series of if statements determine which labels should display which of the enemy health values.
            //if i is equal to the startingValue parameter,
            if (i == startingValue) {
                //sets the enemy 1 health label to display the health of the first enemy.
                lblDisplayEnemy1HP.setText("Enemy Health: " + (enemies.get(i).getHealth()));
            }
            //if i is equal to the startingValue parameter plus 1,
            if (i == startingValue + 1) {
                //sets the enemy 2 health label to display the health of the second enemy.
                lblDisplayEnemy2HP.setText("Enemy Health: " + (enemies.get(i).getHealth()));
            }
            //if i is equal to the startingValue parameter plus 2,
            if (i == startingValue + 2) {
                //sets the enemy 3 health label to display the health of the third enemy.
                lblDisplayEnemy3HP.setText("Enemy Health: " + (enemies.get(i).getHealth()));
            }
            //if i is equal to the startingValue parameter plus 3,
            if (i == startingValue + 3) {
                //sets the enemy 4 health label to display the health of the fourth enemy.
                lblDisplayEnemy4HP.setText("Enemy Health: " + (enemies.get(i).getHealth()));
            }
        }
    }

    //method that sets the visibility of the enemy 1 health label to the value provided in the boolean parameter.
    public void enemy1HPVisible(boolean a) {
        //sets the visibility of the enemy 1 health label to the value provided in the boolean parameter.
        lblDisplayEnemy1HP.setVisible(a);
    }

    //method that sets the visibility of the enemy 2 health label to the value provided in the boolean parameter.
    public void enemy2HPVisible(boolean a) {
        //sets the visibility of the enemy 2 health label to the value provided in the boolean parameter.
        lblDisplayEnemy2HP.setVisible(a);
    }

    //method that sets the visibility of the enemy 3 health label to the value provided in the boolean parameter.
    public void enemy3HPVisible(boolean a) {
        //sets the visibility of the enemy 3 health label to the value provided in the boolean parameter.
        lblDisplayEnemy3HP.setVisible(a);
    }

    //method that sets the visibility of the enemy 4 health label to the value provided in the boolean parameter.
    public void enemy4HPVisible(boolean a) {
        //sets the visibility of the enemy 4 health label to the value provided in the boolean parameter.
        lblDisplayEnemy4HP.setVisible(a);
    }

    @FXML
    //method which runs the attacking process.
    private void handleClickAttack() {
        //sets strings weapon and armor to the weapon and armor selected in the player listview, respectively.
        String weapon = lstViewWeapons.getSelectionModel().getSelectedItem();
        String armor = lstViewArmor.getSelectionModel().getSelectedItem();
        //sets the winner label to be visible, so that the player can see who won the battle.
        lblDisplayWinner.setVisible(true);
        //if the player weapons array list is empty (if the player has bought no weapons),
        if (playerWeapons.isEmpty()) {
            //this sets a label to display the text below, telling the player that they need to buy a weapon before battle.
            lblDisplayAttackStatus.setText("Please buy a weapon before fighting the enemies.");
            //else, if the player armor array list is empty (if the player has bought no armor),
        } else if (playerArmor.isEmpty()) {
            //this sets a label to display the text below, telling the player that they need to buy armor before battle.
            lblDisplayAttackStatus.setText("Please buy a set of armor before fighting the enemies.");
            //else, if the player's level is 0,
        } else if (person.getLevel() == 0) {
            //this sets a label to display the text below, telling the player that they need to select a level before battle.
            lblDisplayAttackStatus.setText("Please select a level.");
            //else,
        } else {
            //sets the attack status label to an empty string, to remove anything that may have been displayed before.
            lblDisplayAttackStatus.setText("");
            //loop that runs for however many weapons the player has.
            for (int i = 0; i < playerWeapons.size(); i++) {
                //loop that runs for however many types of armor the player has.
                for (int j = 0; j < playerArmor.size(); j++) {
                    //if the weapon selected and the armor selected are both equal to the player weapon and player armor in the loop,
                    if (weapon.equals(playerWeapons.get(i).getName()) && armor.equals(playerArmor.get(j).getName())) {
                        //if the player's level is 1,
                        if (person.getLevel() == 1) {
                            //run level 1 method, passing in parameters i and j.
                            level1(i, j);
                        }
                        //if the player's level is 2,
                        if (person.getLevel() == 2) {
                            //run level 2 method, passing in parameters i and j.
                            level2(i, j);
                        }
                        //if the player's level is 3,
                        if (person.getLevel() == 3) {
                            //run level 3 method, passing in parameters i and j.
                            level3(i, j);
                        }
                        //if the player's level is 4,
                        if (person.getLevel() == 4) {
                            //run level 4 method, passing in parameters i and j.
                            level4(i, j);
                        }
                        //if the player's level is 5,
                        if (person.getLevel() == 5) {
                            //run level 5 method, passing in parameters i and j.
                            finalLevel(i, j);
                        }
                        //sets i to the size of the player weapons array, in order to terminate the loop.
                        i = playerWeapons.size();
                        //sets j to the size of the player armor array, in order to terminate the loop.
                        j = playerArmor.size();
                        //runs a displayStats method.
                        displayStats();
                    }
                }
            }
        }
    }

    //method which runs the level 1 attack.
    public void level1(int i, int j) {
        //sets the enemy 1 health to 100.
        setEnemyHealth(0, 0, 100);
        //sets person health to the player's permHealth, which is its health in addition to the health boosts they have bought.
        person.setHealth(person.getPermHealth());
        //runs the displayStats method.
        displayStats();
        //loop that runs while the person health is greater than 0, and while the enemy 1 health is greater than 0.
        while (person.getHealth() > 0 && enemies.get(0).getHealth() > 0) {
            //subtracts the damage from the player's weapon as well as the player's strength attribute from the enemy 1 health.
            enemies.get(0).setHealth(enemies.get(0).getHealth() - playerWeapons.get(i).getDamage() - person.getStrength());
            //runs a calculate enemy damage method to calculate the player's new health.
            calculateEnemyDmg(6, 3, j, 1);
            //if the enemy's health is less than or equal to 0 (if the enemy has lost),
            if (enemies.get(0).getHealth() <= 0) {
                //this sets the enemy health to 0.
                setEnemyHealth(0, 0, 0);
                //if the level 2 button is disabled/not unlocked yet,
                if (btnLvl2.isDisabled()) {
                    //increase the progress bar by 20%.
                    progressBar.setProgress(0.2);
                    //increase the progress indicator by 20%.
                    progressIndicator.setProgress(0.2);
                }
                //unlocks the level 2 button.
                btnLvl2.setDisable(false);
                //runs a personWin method which edits certain attributes of the person based on the win statistics.
                personWin(1, 10, 1);
                //else, if the person's health is less than or equal to 0 (if the person has lost),
            } else if (person.getHealth() <= 0) {
                //runs an enemyWin method which edits certain attributes of the person based on the loss statistics.
                enemyWin(2, 1);
            }
            //sets a label visibility true, which displays the text "Results of last battle:".
            lblDisplayText1.setVisible(true);
            //runs a displayStats method.
            displayStats();
        }
    }

    //method which runs the level 2 attack.
    public void level2(int i, int j) {
        //sets the enemy 1 and 2 health values to 1000.
        setEnemyHealth(1, 2, 1000);
        //sets person health to the player's permHealth, which is its health in addition to the health boosts they have bought.
        person.setHealth(person.getPermHealth());
        //runs the displayStats method.
        displayStats();
        //loop that runs while the person health is greater than 0, and while the enemy 1 and 2 health values are greater than 0.
        while (person.getHealth() > 0 && (enemies.get(1).getHealth() > 0 || enemies.get(2).getHealth() > 0)) {
            //loop that runs for each enemy.
            for (int a = 1; a < 3; a++) {
                //this subtracts the damage from the player's weapon as well as the player's strength attribute from each enemy's health.
                enemies.get(a).setHealth(enemies.get(a).getHealth() - playerWeapons.get(i).getDamage() - person.getStrength());
            }
            //runs a calculate enemy damage method to calculate the player's new health.
            calculateEnemyDmg(15, 10, j, 2);
            //if the enemy health values are all less than or equal to 0 (if the enemies have lost),
            if (enemies.get(1).getHealth() <= 0 && enemies.get(2).getHealth() <= 0) {
                //this sets the enemy health values to 0.
                setEnemyHealth(1, 2, 0);
                //if the level 3 button is disabled/not unlocked yet,
                if (btnLvl3.isDisabled()) {
                    //increase the progress bar by 20%.
                    progressBar.setProgress(0.4);
                    //increase the progress indicator by 20%.
                    progressIndicator.setProgress(0.4);
                }
                //unlocks the level 3 button.
                btnLvl3.setDisable(false);
                //runs a personWin method which edits certain attributes of the person based on the win statistics.
                personWin(2, 100, 2);
                //else, if the person's health is less than or equal to 0 (if the person has lost),
            } else if (person.getHealth() <= 0) {
                //runs an enemyWin method which edits certain attributes of the person based on the loss statistics.
                enemyWin(10, 2);
            }
            //sets a label visibility true, which displays the text "Results of last battle:".
            lblDisplayText1.setVisible(true);
            //runs a displayStats method.
            displayStats();
        }
    }

    //method which runs the level 3 attack.
    public void level3(int i, int j) {
        //sets the enemy 1, 2, and 3 health values to 2500.
        setEnemyHealth(3, 5, 2500);
        //sets person health to the player's permHealth, which is its health in addition to the health boosts they have bought.
        person.setHealth(person.getPermHealth());
        //runs the displayStats method.
        displayStats();
        //loop that runs while the person health is greater than 0, and while the enemy 1, 2, and 3 health values are greater than 0.
        while (person.getHealth() > 0 && (enemies.get(3).getHealth() > 0 || enemies.get(4).getHealth() > 0 || enemies.get(5).getHealth() > 0)) {
            //loop that runs for each enemy.
            for (int a = 3; a < 6; a++) {
                //this subtracts the damage from the player's weapon as well as the player's strength attribute from each enemy's health.
                enemies.get(a).setHealth(enemies.get(a).getHealth() - playerWeapons.get(i).getDamage() - person.getStrength());
            }
            //runs a calculate enemy damage method to calculate the player's new health.
            calculateEnemyDmg(75, 70, j, 3);
            //if the enemy health values are all less than or equal to 0 (if the enemies have lost),
            if (enemies.get(3).getHealth() <= 0 && enemies.get(4).getHealth() <= 0 && enemies.get(5).getHealth() <= 0) {
                //this sets the enemy health values to 0.
                setEnemyHealth(3, 5, 0);
                //if the level 4 button is disabled/not unlocked yet,
                if (btnLvl4.isDisabled()) {
                    //increase the progress bar by 20%.
                    progressBar.setProgress(0.6);
                    //increase the progress indicator by 20%.
                    progressIndicator.setProgress(0.6);
                }
                //unlocks the level 4 button.
                btnLvl4.setDisable(false);
                //runs a personWin method which edits certain attributes of the person based on the win statistics.
                personWin(3, 500, 3);
                //else, if the person's health is less than or equal to 0 (if the person has lost),
            } else if (person.getHealth() <= 0) {
                //runs an enemyWin method which edits certain attributes of the person based on the loss statistics.
                enemyWin(50, 3);
            }
            //sets a label visibility true, which displays the text "Results of last battle:".
            lblDisplayText1.setVisible(true);
            //runs a displayStats method.
            displayStats();
        }
    }

    //method which runs the level 4 attack.
    public void level4(int i, int j) {
        //sets the enemy 1, 2, 3, and 4 health values to 5000.
        setEnemyHealth(6, 9, 5000);
        //sets person health to the player's permHealth, which is its health in addition to the health boosts they have bought.
        person.setHealth(person.getPermHealth());
        //runs the displayStats method.
        displayStats();
        //loop that runs while the person health is greater than 0, and while the enemy 1, 2, 3, and 4 health values are greater than 0.
        while (person.getHealth() > 0 && (enemies.get(6).getHealth() > 0 || enemies.get(7).getHealth() > 0 || enemies.get(8).getHealth() > 0 || enemies.get(9).getHealth() > 0)) {
            //loop that runs for each enemy.
            for (int a = 6; a < 10; a++) {
                //this subtracts the damage from the player's weapon as well as the player's strength attribute from each enemy's health.
                enemies.get(a).setHealth(enemies.get(a).getHealth() - playerWeapons.get(i).getDamage() - person.getStrength());
            }
            //runs a calculate enemy damage method to calculate the player's new health.
            calculateEnemyDmg(1000, 900, j, 4);
            //if the enemy health values are all less than or equal to 0 (if the enemies have lost),
            if (enemies.get(6).getHealth() <= 0 && enemies.get(7).getHealth() <= 0 && enemies.get(8).getHealth() <= 0 && enemies.get(9).getHealth() <= 0) {
                //this sets the enemy health values to 0.
                setEnemyHealth(6, 9, 0);
                //if the level 5 button is disabled/not unlocked yet,
                if (btnFinalLvl.isDisabled()) {
                    //increase the progress bar by 20%.
                    progressBar.setProgress(0.8);
                    //increase the progress indicator by 20%.
                    progressIndicator.setProgress(0.8);
                }
                //unlocks the level 5 button.
                btnFinalLvl.setDisable(false);
                //runs a personWin method which edits certain attributes of the person based on the win statistics.
                personWin(4, 5000, 4);
                //else, if the person's health is less than or equal to 0 (if the person has lost),
            } else if (person.getHealth() <= 0) {
                //runs an enemyWin method which edits certain attributes of the person based on the loss statistics.
                enemyWin(200, 4);
            }
            //sets a label visibility true, which displays the text "Results of last battle:".
            lblDisplayText1.setVisible(true);
            //runs a displayStats method.
            displayStats();
        }
    }

    //method which runs the final level attack.
    public void finalLevel(int i, int j) {
        //sets the boss health to 50000.
        setEnemyHealth(10, 10, 50000);
        //sets person health to the player's permHealth, which is its health in addition to the health boosts they have bought.
        person.setHealth(person.getPermHealth());
        //runs the displayStats method.
        displayStats();
        //loop that runs while the person health is greater than 0, and while the boss health is greater than 0.
        while (person.getHealth() > 0 && enemies.get(10).getHealth() > 0) {
            //this subtracts the damage from the player's weapon as well as the player's strength attribute from the boss's health.
            enemies.get(10).setHealth(enemies.get(10).getHealth() - playerWeapons.get(i).getDamage() - person.getStrength());
            //runs a calculate enemy damage method to calculate the player's new health.
            calculateEnemyDmg(5000, 4500, j, 1);
            //if the boss's health is less than or equal to 0 (if the boss has lost),
            if (enemies.get(10).getHealth() <= 0) {
                //this sets the boss health to 0.
                setEnemyHealth(10, 10, 0);
                //increase the progress bar by 20%.
                progressBar.setProgress(1);
                //increase the progress indicator by 20%.
                progressIndicator.setProgress(1);
                //runs a personWin method which edits certain attributes of the person based on the win statistics.
                personWin(5, 1000000, 1);
                //else, if the person's health is less than or equal to 0 (if the person has lost),
            } else if (person.getHealth() <= 0) {
                //runs an enemyWin method which edits certain attributes of the person based on the loss statistics.
                enemyWin(200, 5);
            }
            //sets a label visibility true, which displays the text "Results of last battle:".
            lblDisplayText1.setVisible(true);
            //runs a displayStats method.
            displayStats();
        }
    }

    //method that calculates the enemy damage, using the upper, lower, j, and enemy parameters.
    public void calculateEnemyDmg(int upper, int lower, int j, int enemies) {
        //sets an enemyDmg variable to a random integer between the upper and lower parameters given, which increase as the levels increase.
        double enemyDmg = Math.floor(Math.random() * (upper - lower + 1) + lower);
        //sets a protection variable to the protection level of the selected armor.
        double protection = playerArmor.get(j).getProtection();
        //sets protection to 1 minus the protection value.
        protection = 1 - (protection / 100);
        //multiplies enemyDmg by protection, essentially removing a certain amount of damage from the enemy attacks, which ends up protecting and helping the player.
        enemyDmg *= protection;
        //sets the person health to its original health minus the damage by the enemy multiplied by the number of enemies from that level.
        person.setHealth((person.getHealth() - (enemyDmg * enemies)));
    }

    //method that sets the enemy health using the lower, upper, and health parameters.
    public void setEnemyHealth(int lower, int upper, int health) {
        //loop that runs from the lower to upper parameters.
        for (int i = lower; i <= upper; i++) {
            //sets the enemy health of the specific positions in the enemies array list to the health parameter.
            enemies.get(i).setHealth(health);
        }
    }

    //method that simulates the situation of the enemy/enemies winning.
    public void enemyWin(int money, int level) {
        //sets the person health to 0, so that it does not display as a negative number.
        person.setHealth(0);
        //if the level is 1,
        if (level == 1) {
            //display that the enemy killed the player.
            lblDisplayWinner.setText(person.getName() + ", the enemy killed you before you could kill it.");
            //else, if the level is 5,
        } else if (level == 5) {
            //display that the boss killed the player.
            lblDisplayWinner.setText(person.getName() + ", the boss killed you before you could kill it.");
            //else,
        } else {
            //display that the enemies killed the player.
            lblDisplayWinner.setText(person.getName() + ", the enemies killed you before you could kill them.");
        }
        //subtract the money parameter from the player's current money amount.
        person.setMoney(person.getMoney() - money);
        //set the money loss label visibility to true, since the player has lost money.
        lblDisplayMoneyLoss.setVisible(true);
        //set the money profit label visibility to false, since the player has not gained money.
        lblDisplayMoneyProfit.setVisible(false);
        //display the amount of money the person lost.
        lblDisplayMoneyLoss.setText("-$" + money);
        //add a death to the player's total amount of deaths.
        person.setDeaths(person.getDeaths() + 1);
        //runs a displayStats method.
        displayStats();
    }

    //method that simulates the situation of the player winning.
    public void personWin(int level, int money, int kills) {
        //if the person's health is less than or equal to 0 (this may happen because technically the battle is in turns, and the person goes first, then the enemy goes second; for example, if the person and enemy health are both 5, and the person goes first and deals 10 damage, and the enemy deals 10 damage, the person won since he killed the enemy first, but his health will show up as -5, which we do not want),
        if (person.getHealth() <= 0) {
            //sets the player's health to 0.
            person.setHealth(0);
        }
        //if the level is 1
        if (level == 1) {
            //display that the player killed the enemy.
            lblDisplayWinner.setText(person.getName() + ", you killed the enemy before it could kill you!");
            //else, if the level is 5,
        } else if (level == 5) {
            //display that the player killed the boss, and that he can click restart to play again.
            lblDisplayWinner.setText(person.getName() + ", you defeated the boss and collected the jewel! Click restart to play again.");
            //else,
        } else {
            //display that the enemies killed the player.
            lblDisplayWinner.setText(person.getName() + ", you killed the enemies before they could kill you!");
        }
        //add the money parameter to the player's current money amount.
        person.setMoney(person.getMoney() + money);
        //set the money loss label visibility to false, since the player has not lost money.
        lblDisplayMoneyLoss.setVisible(false);
        //set the money profit label visibility to true, since the player has gained money.
        lblDisplayMoneyProfit.setVisible(true);
        //display the amount of money the person gained.
        lblDisplayMoneyProfit.setText("+$" + money);
        //add a kill to the player's total amount of kills.
        person.setKills(person.getKills() + kills);
        //runs a displayStats method.
        displayStats();
    }

    @FXML
    //method that runs when the level 1 button is clicked.
    private void handleClickLevel1() {
        //set the player's level to 1.
        person.setLevel(1);
        //runs a displayStats method.
        displayStats();
        //sets the winner display to false, so that we don't see that while switching levels.
        lblDisplayWinner.setVisible(false);
        //sets the level display to true, so that we can see what level we are on.
        lblDisplayLevel.setVisible(true);
    }

    @FXML
    //method that runs when the level 2 button is clicked.
    private void handleClickLevel2() {
        //set the player's level to 2.
        person.setLevel(2);
        //runs a displayStats method.
        displayStats();
        //sets the winner display to false, so that we don't see that while switching levels.
        lblDisplayWinner.setVisible(false);
    }

    @FXML
    //method that runs when the level 3 button is clicked.
    private void handleClickLevel3() {
        //set the player's level to 3.
        person.setLevel(3);
        //runs a displayStats method.
        displayStats();
        //sets the winner display to false, so that we don't see that while switching levels.
        lblDisplayWinner.setVisible(false);

    }

    @FXML
    //method that runs when the level 4 button is clicked.
    private void handleClickLevel4() {
        //set the player's level to 4.
        person.setLevel(4);
        //runs a displayStats method.
        displayStats();
        //sets the winner display to false, so that we don't see that while switching levels.
        lblDisplayWinner.setVisible(false);
    }

    @FXML
    //method that runs when the level 5 button is clicked.
    private void handleClickFinalLevel() {
        //set the player's level to 5.
        person.setLevel(5);
        //runs a displayStats method.
        displayStats();
        //sets the winner display to false, so that we don't see that while switching levels.
        lblDisplayWinner.setVisible(false);
    }

    @FXML
    //method that runs when the weapons item shop listview is clicked.
    private void handleClickBuyWeapons() {
        //weapon string set to the name of the weapon selected in the weapons listview.
        String weapon = lstViewShopWeapons.getSelectionModel().getSelectedItem();
        //sets boolean a to true, which is used later.
        boolean a = true;
        //loop that runs for the length of the player's weapons array list.
        for (int j = 0; j < playerWeapons.size(); j++) {
            //if the weapon selected is already in the player weapons array list,
            if (weapon.equals(playerWeapons.get(j).getName() + ": $" + allWeapons.get(j).getPrice() + "; Damage: " + allWeapons.get(j).getDamage())) {
                //display that the player has already bought that weapon.
                lblDisplayBuyStatus.setText("You already have that weapon.");
                //set boolean a to false, so that the actual buying code does not run, since the player has already bought this weapon.
                a = false;
            }
        }
        //if a (if the player has not already bought the weapon selected, therefore causing the boolean to stay true),
        if (a) {
            //loop that runs for the length of the weapons array list.
            for (int i = 0; i < allWeapons.size(); i++) {
                //if the weapon is equal to the current weapon in the all weapons array list, and if the price of that weapon is less than or equal to the amount of money the player has,
                if (weapon.equals(allWeapons.get(i).getName() + ": $" + allWeapons.get(i).getPrice() + "; Damage: " + allWeapons.get(i).getDamage()) && person.getMoney() >= allWeapons.get(i).getPrice()) {
                    //add the weapon to the player weapons array list.
                    playerWeapons.add(allWeapons.get(i));
                    //subtract the price of that weapon from the player's money.
                    person.setMoney(person.getMoney() - allWeapons.get(i).getPrice());
                    //add the weapon the player just bought to the player's weapons array list.
                    lstViewWeapons.getItems().add(allWeapons.get(i).getName());
                    //display the weapon that the player just purchased.
                    lblDisplayBuyStatus.setText("You bought a " + allWeapons.get(i).getName());
                    //sets i to the size of the all weapons array list, in order to stop the loop.
                    i = allWeapons.size();
                    //else,
                } else if (person.getMoney() < allWeapons.get(i).getPrice()) {
                    //display that the player cannot afford the weapon selected.
                    lblDisplayBuyStatus.setText("You cannot afford that.");
                }
                //runs a displayStats method.
                displayStats();
            }
        }
    }

    @FXML
    //method that runs when the armor item shop listview is clicked.
    private void handleClickBuyArmor() {
        //armor string set to the name of the armor selected in the armor listview.
        String armor = lstViewShopArmor.getSelectionModel().getSelectedItem();
        boolean a = true;
        //loop that runs for the length of the player's armor array list.
        for (int j = 0; j < playerArmor.size(); j++) {
            //if the armor selected is already in the player armor array list,
            if (armor.equals(playerArmor.get(j).getName() + ": $" + allArmor.get(j).getPrice() + "; Protection: " + allArmor.get(j).getProtection() + "%")) {
                //display that the player has already bought that type of armor.
                lblDisplayBuyStatus.setText("You already have that armor set.");
                //set boolean a to false, so that the actual buying code does not run, since the player has already bought this armor.
                a = false;
            }
        }
        //if a (if the player has not already bought the armor selected, therefore causing the boolean to stay true),
        if (a) {
            //loop that runs for the length of the armor array list.
            for (int i = 0; i < allArmor.size(); i++) {
                //if the armor is equal to the current armor in the all armor array list, and if the price of that armor is less than or equal to the amount of money the player has,
                if (armor.equals(allArmor.get(i).getName() + ": $" + allArmor.get(i).getPrice() + "; Protection: " + allArmor.get(i).getProtection() + "%") && person.getMoney() >= allArmor.get(i).getPrice()) {
                    //add the armor to the player armor array list.
                    playerArmor.add(allArmor.get(i));
                    //subtract the price of that armor from the player's money.
                    person.setMoney(person.getMoney() - allArmor.get(i).getPrice());
                    //add the armor the player just bought to the player's armor array list.
                    lstViewArmor.getItems().add(allArmor.get(i).getName());
                    //display the armor that the player just purchased.
                    lblDisplayBuyStatus.setText("You bought a " + allArmor.get(i).getName());
                    //sets i to the size of the all armor array list, in order to stop the loop.
                    i = allArmor.size();
                    //else,
                } else if (person.getMoney() < allArmor.get(i).getPrice()) {
                    //display that the player cannot afford the armor selected.
                    lblDisplayBuyStatus.setText("You cannot afford that.");
                }
                //runs a displayStats method.
                displayStats();
            }
        }
    }

    @FXML
    //method that runs when the effects item shop listview is clicked.
    private void handleClickBuyEffects() {
        //effect string set to the name of the effect selected in the effects listview.
        String effect = lstViewShopEffects.getSelectionModel().getSelectedItem();
        //loop that runs for the length of the effects array list.
        for (int i = 0; i < allEffects.size(); i++) {
            //if the effect is equal to the current effect in the all effects array list with the right wording, and if the price of that effect is less than or equal to the amount of money the player has,
            if ((effect.equals(allEffects.get(i).getName() + ": $" + allEffects.get(i).getPrice() + "; Extra Damage: " + allEffects.get(i).getDamage()) || effect.equals(allEffects.get(i).getName() + ": $" + allEffects.get(i).getPrice() + "; Health Boost: " + allEffects.get(i).getProtection())) && person.getMoney() >= allEffects.get(i).getPrice()) {
                //add the effect to the player effects array list.
                playerEffects.add(allEffects.get(i));
                //subtract the price of that effect from the player's money.
                person.setMoney(person.getMoney() - allEffects.get(i).getPrice());
                //add the effect the player just bought to the player's effects array list.
                lstViewEffects.getItems().add(allEffects.get(i).getName());
                //display the effect that the player just purchased.
                lblDisplayBuyStatus.setText("You bought " + allEffects.get(i).getName());
                //if the effect is a weapon,
                if (allEffects.get(i).getWeapon()) {
                    //add the damage of it to the person's strength attribute, as that effect deals more damage.
                    person.setStrength(allEffects.get(i).getDamage());
                    //else,
                } else {
                    //add the protection of it to the person's permHealth attribute, as that effect adds health to the player.
                    person.setPermHealth(person.getPermHealth() + allEffects.get(i).getProtection());
                }
                //sets i to the size of the all effects array list, in order to stop the loop.
                i = allEffects.size();
                //else,
            } else if (person.getMoney() < allEffects.get(i).getPrice()) {
                //display that the player cannot afford the armor selected.
                lblDisplayBuyStatus.setText("You cannot afford that.");
            }
            //runs a displayStats method.
            displayStats();
        }
    }

    @FXML
    //method that restarts the game.
    private void handleClickRestart() {
        //sets the player's name to an empty string.
        person.setName("");
        //sets the person's health to 100.
        person.setHealth(100);
        //sets the person's permanent health to 100.
        person.setPermHealth(100);
        //sets the person's kills to 0.
        person.setKills(0);
        //sets the person's money to 10.
        person.setMoney(10);
        //sets the person's level to 0.
        person.setLevel(0);
        //sets the person's deaths to 0.
        person.setDeaths(0);
        //sets the person's strength to 0.
        person.setStrength(0);
        //clears the effects, armor, and weapons listviews, since the player's purchases are all being reset.
        lstViewEffects.getItems().clear();
        lstViewArmor.getItems().clear();
        lstViewWeapons.getItems().clear();
        //clears the player's weapons, armor, and effects array list, since they are all being reset.
        playerWeapons.clear();
        playerArmor.clear();
        playerEffects.clear();
        //runs a showFXML method.
        showFXML(false);
        //disables buttons for levels 2, 3, 4, and 5 since we are restarting the game.
        btnLvl2.setDisable(true);
        btnLvl3.setDisable(true);
        btnLvl4.setDisable(true);
        btnFinalLvl.setDisable(true);
        //sets the money profit, money loss, original enemy health, buy status, winner, enemy 1, 2, 3, and 4 health labels to empty strings, in order to reset them.
        lblDisplayMoneyProfit.setText("");
        lblDisplayMoneyLoss.setText("");
        lblDisplayOGEnemyHP.setText("");
        lblDisplayBuyStatus.setText("");
        lblDisplayWinner.setText("");
        lblDisplayEnemy1HP.setText("");
        lblDisplayEnemy2HP.setText("");
        lblDisplayEnemy3HP.setText("");
        lblDisplayEnemy4HP.setText("");
        //clears the text input for the player's name.
        txtInputName.clear();
        //resets the progress indicator and progress bars to 0.
        progressIndicator.setProgress(0);
        progressBar.setProgress(0);
    }
}