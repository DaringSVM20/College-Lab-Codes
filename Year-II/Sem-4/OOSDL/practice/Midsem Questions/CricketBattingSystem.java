
enum ShotType {
    SINGLE(1), 
    DOUBLE(2), 
    FOUR(4), 
    SIX(6), 
    OUT(0);

    private final int runs;

    ShotType (int runs) {
        this.runs = runs;
    }

    public int getRuns() {
        return runs;
    }
}

interface BattingOperations {

    void playShot(ShotType shot);
    void getOut();
}

class CricketPlayer {

    String playerName;
    int totalRuns;
    boolean isOut;

    public CricketPlayer(String playerName) {
        this.playerName = playerName;
        this.totalRuns = 0;
        this.isOut = false;

    }
}

class Batsman extends CricketPlayer implements BattingOperations {

    public Batsman(String playerName) {
        super(playerName);
    }

    @Override
    public synchronized void playShot(ShotType shot) {
        if (isOut) return;

        if (shot == ShotType.OUT) {
            getOut();
            return;
        }

        totalRuns += shot.getRuns();
        System.out.println("Shot: " + shot + 
        " | Runs: " + shot.getRuns() +
        " | Total: " + totalRuns);
    }

    @Override
    public synchronized void getOut() {
        if (!isOut) {
            isOut = true;
            System.out.println(playerName + " is OUT! Total runs: " + totalRuns);
        }
    }

}

class BattingThread extends Thread {

    private Batsman batsman;

    public BattingThread(Batsman batsman) {
        this.batsman = batsman;
    }

    @Override
    public void run() {
        ShotType[] shots = {
            ShotType.SINGLE,
            ShotType.DOUBLE,
            ShotType.FOUR,
            ShotType.SIX
        };

        try {
            while (!batsman.isOut) {
                ShotType shot = shots[(int)(Math.random() * shots.length)];
                batsman.playShot(shot);
                Thread.sleep(500);
            }
        } catch (Exception e) {}

    }

}

class FieldingThread extends Thread {

    private Batsman batsman;

    public FieldingThread(Batsman batsman) {
        this.batsman = batsman;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            batsman.getOut();
        } catch (Exception e) {}
    }

}

class UmpireThread extends Thread {

    private Batsman batsman;

    public UmpireThread(Batsman batsman) {
        this.batsman = batsman;
    }

    @Override
    public void run() {
        try {
            while (!batsman.isOut) {
                Thread.sleep(500);
            }
            System.out.println("Umpire Decision: OUT Confirmed.");
            System.out.println("Total Runs: " + batsman.totalRuns);
        } catch (Exception e) {}
    }
}

public class CricketBattingSystem {

    public static void main(String[] args) {
        
        Batsman batsman = new Batsman("Virat Kohli");

        Thread batting = new BattingThread(batsman);
        Thread fielding = new FieldingThread(batsman);
        Thread umpire = new UmpireThread(batsman);

        batting.start();
        fielding.start();
        umpire.start();
    }

}