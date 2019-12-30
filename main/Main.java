package main;
import main.game.GameEngine;
import main.game.GameInput;
import main.game.GameInputLoader;

/**
 * Main class to read the input data and also print the final situation of the heroes.
 */
public final class Main {
    private Main() {
    }
    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();
        GameEngine.getInitialData(gameInput);
        GameEngine.startGame(gameInput, args[0], args[1]);
//        try {
//            FileSystem fs = new FileSystem(args[0], args[1]);
//            for (Hero hero : GameEngine.getHeroes()) {
//                fs.writeCharacter(hero.getName());
//                fs.writeWord(" ");
//                if (hero.getState().equals("alive")) {
//                fs.writeInt(hero.getLevel());
//                fs.writeWord(" ");
//                fs.writeInt(hero.getXp());
//                fs.writeWord(" ");
//                fs.writeInt(hero.getCurrHp());
//                fs.writeWord(" ");
//                fs.writeInt(hero.getLocation().getRow());
//                fs.writeWord(" ");
//                fs.writeInt(hero.getLocation().getCol());
//                } else {
//                    fs.writeWord("dead");
//                }
//                fs.writeWord("\n");
//            }
//            fs.close();
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
    }
}
