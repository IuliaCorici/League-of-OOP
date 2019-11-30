package main;

import main.game.GameEngine;
import main.game.GameInput;
import main.game.GameInputLoader;

public class Main {

    public static void main(String[] args) {
	// write your code here
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();
        GameEngine.getInitialData(gameInput);
        GameEngine.startGame(gameInput);
    }


}
