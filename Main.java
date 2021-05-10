import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Contains the main logic part of the game, as it processes.
 *
 */
public class Main {

    private HumanPlayer humanplayer;
    private Map map = new Map();
    private boolean gameRunning;
    private BotPlayer botplayer;


    /**
     * Default constructor
     */
    public Main() {
        humanplayer = new HumanPlayer();
        humanplayer.startPosition(this);
        botplayer = new BotPlayer();
        botplayer.startPosition(this);
        gameRunning = true;
    }

    public Map getMap(){
        return map;
    }



    /**
     * Returns the gold required to win.
     *
     * @return : Gold required to win.
     */
    protected String hello() {
        System.out.println("Gold to win: " + map.getGoldRequired());
        return null;
    }

    /**
     * Returns the gold currently owned by the player.
     *
     * @return : Gold currently owned.
     */
    protected String gold() {
        System.out.println("Gold owned: " + humanplayer.getGoldOwned());
        return null;
    }

    /**
     * Checks if movement is legal and updates player's location on the map.
     *
     * @param direction : The direction of the movement.
     * @return : Protocol if success or not.
     */
    protected String move(String direction) {
        char[][] mapAdjacent = map.getMap();
        int[] currentPosition = humanplayer.getPositionStore();

        if (direction.equalsIgnoreCase("move n")) {
            if (mapAdjacent[currentPosition[0] - 1][currentPosition[1]] == '#') {
                System.out.println("FAILED");
            } else {
                currentPosition[0]--;
                System.out.println("SUCCESS");
            }
        } else if (direction.equalsIgnoreCase("move e")) {
            if (mapAdjacent[currentPosition[0]][currentPosition[1] + 1] == '#') {
                System.out.println("FAILED");
            } else {
                currentPosition[1]++;
                System.out.println("SUCCESS");
            }
        } else if (direction.equalsIgnoreCase("move s")) {
            if (mapAdjacent[currentPosition[0] + 1][currentPosition[1]] == '#') {
                System.out.println("FAILED");
            } else {
                currentPosition[0]++;
                System.out.println("SUCCESS");
            }
        } else if (direction.equalsIgnoreCase("move w")) {
            if (mapAdjacent[currentPosition[0]][currentPosition[1] - 1] == '#') {
                System.out.println("FAILED");
            } else {
                currentPosition[1]--;
                System.out.println("SUCCESS");
            }

        }
        humanplayer.setPositionStore(currentPosition);
        return null;
    }

    protected String botMove(String direction) throws InterruptedException {
        char[][] mapAdjacent = map.getMap();
        int[] currentBotPosition = botplayer.getPositionStore();
        int[] currentPosition = humanplayer.getPositionStore();

        if (direction.equals("0")) {
            if (mapAdjacent[currentBotPosition[0] - 1][currentBotPosition[1]] == '#') {
            } else {
                currentBotPosition[0]--;
            }
        } else if (direction.equals("1")) {
            if (mapAdjacent[currentBotPosition[0]][currentBotPosition[1] + 1] == '#') {
            } else {
                currentBotPosition[1]++;
            }
        } else if (direction.equals("2")) {
            if (mapAdjacent[currentBotPosition[0] + 1][currentBotPosition[1]] == '#') {
            } else {
                currentBotPosition[0]++;
            }
        } else if (direction.equals("3")) {
            if (mapAdjacent[currentBotPosition[0]][currentBotPosition[1] - 1] == '#') {
            } else {
                currentBotPosition[1]--;
            }

        }
        botplayer.setPositionStore(currentBotPosition);

        if ((currentPosition[0] == currentBotPosition[0]) && (currentPosition[1] == currentBotPosition[1])) {
            System.out.println("LOSE");
            System.out.println("You have been caught!");
            System.out.println("You have lost the game, exiting with: " + humanplayer.getGoldOwned() + " out of the: " + map.getGoldRequired() + " gold needed to exit!");
            System.out.println("All progress will be lost, quitting game in 6 seconds...");
            TimeUnit.SECONDS.sleep(6);
            gameRunning = false;
        }

        return null;
    }

    /**
     * Converts the map from a 2D char array to a single string.
     *
     * @return : A String representation of the game map.
     */
    protected String look() {
        char[][] mapArea = map.getMap();
        String mapString = "";
        String areaString = "";
        int[] currentPosition = humanplayer.getPositionStore();
        int[] currentBotPosition = botplayer.getPositionStore();

        for(int c = 0; c < mapArea.length; c++) {
            for(int d = 0; d < mapArea[0].length; d++) {
                if(c == currentPosition[0] && d == currentPosition[1]) {
                    mapString = mapString + "P";
                } else if (c == currentBotPosition[0] && d == currentBotPosition[1]) {
                    mapString = mapString + "B";
                } else {
                    try {
                        mapString = mapString + mapArea[c][d];
                    } catch (IndexOutOfBoundsException e) {
                        mapString = mapString + "#";
                    }
                }
            }
            mapString = mapString + "\n";
        }

        for(int y = currentPosition[0] - 2; y < currentPosition[0] + 3; y++) {
            for(int x = currentPosition[1] - 2; x < currentPosition[1] + 3; x++) {
                if(y == currentPosition[0] && x == currentPosition[1]) {
                    areaString = areaString + "P";
                } else if (y == currentBotPosition[0] && x == currentBotPosition[1]) {
                    areaString = areaString + "B";
                } else {
                    try {
                        areaString = areaString + mapArea[y][x];
                    } catch (IndexOutOfBoundsException e) {
                        areaString = areaString + "#";
                    }
                }
            }
            areaString = areaString + "\n";
        }
        System.out.println(mapString);
        System.out.println(areaString);
        return null;
    }

    /**
     * Processes the player's pickup command, updating the map and the player's gold amount.
     *
     * @return If the player successfully picked-up gold or not.
     */
    protected String pickup() {
        char[][] mapPickup = map.getMap();
        int[] currentPosition = humanplayer.getPositionStore();
        if (mapPickup[currentPosition[0]][currentPosition[1]] == 'G') {
            mapPickup[currentPosition[0]][currentPosition[1]] = '.';
            humanplayer.setGoldOwned(1);
            System.out.println("SUCCESS");
        } else {
            System.out.println("FAIL");
        }

        return null;
    }

    /**
     * Quits the game, shutting down the application.
     */
    protected void quitGame() throws InterruptedException {
        char[][] mapQuit = map.getMap();
        if (mapQuit[humanplayer.getPositionStore()[0]][humanplayer.getPositionStore()[1]] == 'E') {
            if (map.getGoldRequired() == humanplayer.getGoldOwned()) {
                System.out.println("WIN");
                System.out.println("You have won the game, collecting: " + humanplayer.getGoldOwned() + " out of: " + map.getGoldRequired() + " gold on the map!");
                System.out.println("All progress will be lost, quitting game in  6 seconds...");
                TimeUnit.SECONDS.sleep(6);
                gameRunning = false;

            }
        } else {
            System.out.println("LOSE");
            System.out.println("You have lost the game, exiting with: " + humanplayer.getGoldOwned() + " out of the: " + map.getGoldRequired() + " gold needed to exit!");
            System.out.println("All progress will be lost, quitting game in 6 seconds...");
            TimeUnit.SECONDS.sleep(6);
            gameRunning = false;

        }
    }

    public static void main(String[] args) throws InterruptedException {

        Main logic = new Main();
        System.out.println("Welcome to the Dungeons of Doom: Default Map");
        while (logic.gameRunning) {
            Random rand = new Random();
            String x = String.valueOf(rand.nextInt(4));

            Scanner input = new Scanner(System.in);
            String word = input.nextLine();
            if (word.equalsIgnoreCase("hello")) {
                logic.hello();

            } else if (word.equalsIgnoreCase("gold")) {
                logic.gold();

            } else if (word.equalsIgnoreCase("look")) {
                logic.look();

            } else if (word.equalsIgnoreCase("pickup")) {
                logic.pickup();

            } else if (word.equalsIgnoreCase("quit")) {
                logic.quitGame();

            } else if (word.equalsIgnoreCase("move n") || word.equalsIgnoreCase("move e") || word.equalsIgnoreCase("move s") || word.equalsIgnoreCase("move w")) {
                logic.move(word);

            } else {
                System.out.println("INVALID COMMAND");
            }
            logic.botMove(x);
        }
    }
}