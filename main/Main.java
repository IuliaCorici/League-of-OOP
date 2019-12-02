package main;
import fileio.FileSystem;
import main.game.GameEngine;
import main.game.GameInput;
import main.game.GameInputLoader;
import main.hero.Hero;

public class Main {

    public static void main(String[] args) {
	// write your code here
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();
        GameEngine.getInitialData(gameInput);
        GameEngine.startGame(gameInput);
        try {
            FileSystem fs = new FileSystem(args[0], args[1]);
            for (Hero hero : GameEngine.getHeroes()) {
                fs.writeCharacter(hero.getName());
                fs.writeWord(" ");
                if (hero.getState() == "alive") {
                fs.writeInt(hero.getLevel());
                fs.writeWord(" ");
                fs.writeInt(hero.getXP());
                fs.writeWord(" ");
                fs.writeInt(hero.getCURR_HP());
                fs.writeWord(" ");
                fs.writeInt(hero.getLocation().getRow());
                fs.writeWord(" ");
                fs.writeInt(hero.getLocation().getCol());
                } else {
                    fs.writeWord("dead");
                }
                fs.writeWord("\n");
            }
            fs.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }


}
