import java.util.Random;

/**
 * Runs the game with a human player and contains code needed to read inputs.
 *
 */
public class HumanPlayer {

    private int goldOwned;
    private int[] positionStore;

    public HumanPlayer() {
        int goldOwned = 0;
        positionStore = new int[2];
    }

    protected int getGoldOwned() {
        return goldOwned;
    }
    protected void setGoldOwned(int newValue) {
        goldOwned = goldOwned + newValue;
    }

    protected int[] getPositionStore(){
        return positionStore;
    }

    protected void setPositionStore(int[] currentPosition){
        positionStore = currentPosition;
    }

    protected char[][] startPosition(Main logic) {
        char[][] mapp;
        mapp = logic.getMap().getMap();

        int y = getRandomY(logic);
        int x = getRandomX(logic);

        if (mapp[y][x] == 'G') {
            startPosition(logic);
        }
        else if (mapp[y][x] == '#') {
            startPosition(logic);
        }
        else if (mapp[y][x] == 'B') {
            startPosition(logic);
        }
        else {
            positionStore[0] = y;
            positionStore[1] = x;
        }

        return null;
    }

    private int getRandomY(Main logic) {
        Random rand = new Random();
        int j = rand.nextInt(logic.getMap().getMap().length);
        return j;
    }

    private int getRandomX(Main logic) {
        Random rand = new Random();
        int i = rand.nextInt(logic.getMap().getMap()[0].length);
        return i;
    }
}