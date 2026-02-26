package catan;
import test.util.ArrayList;
import test.util.List;

public class GameAgent {

    private final List<CatanAgent> agents;

    public GameAgent(List<CatanAgent> agents) {
        this.agents = agents;
    }

    public void initializeGame() {
        for (int i = 0; i < agents.size(); i++) {
            agents.get(i).init(i);
        }
    }

    public void playTurn(int playerId, GameState state) {

        CatanAgent agent = agents.get(playerId);

        Move move = agent.chooseMove(state);

        applyMove(move);
    }

    private void applyMove(Move move) {
  
    }
}