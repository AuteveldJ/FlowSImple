package FlowFree.Model;

/**
 * @author Jonathan Auteveld
 * @version 1.0 7/04/2021 22:12
 */
public class Player {
    private String name;
    private int moves;
    private int level;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int addMove(int move) {
        return this.moves += move;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
