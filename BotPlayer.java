import java.util.Random;

public class BotPlayer {

    private int[] positionStore;

    public BotPlayer() {
        positionStore = new int[2];
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
        else if (mapp[y][x] == 'P') {
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
