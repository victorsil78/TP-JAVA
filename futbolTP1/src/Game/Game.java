package Game;

public class Game {

    public static void pause (int mellisec){
        try {
            Thread.sleep(mellisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
