package FlowFree.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Auteveld
 * @version 1.0 12/02/2021 22:43
 */
public class FlowFreeModel {
    private boolean won = false;
    private List<Player> playerList;

    public FlowFreeModel() {
    }

    /*
    createboard?
    savegame
    loadgame
     */

    public void createPlayerlist(String name) {
        playerList = new ArrayList<>();
        Player player = new Player(name);

        playerList.add(player);
    }

    //get player

    //getters
    //setters
    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }
}
