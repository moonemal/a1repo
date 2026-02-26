import catan;
package part3.test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

public class CatanTest {


    interface Move {}

    interface GameState {}

    interface CatanAgent {
        void init(int playerId);
        Move chooseMove(GameState state);
    }



    class GameAgent {

        private final List<CatanAgent> agents;

        GameAgent(List<CatanAgent> agents) {
            this.agents = agents;
        }

        void playTurn(int playerId, GameState state) {
            CatanAgent agent = agents.get(playerId);
            Move move = agent.chooseMove(state);
            applyMove(move);
        }

        void applyMove(Move move) {
        }
    }


    @Test
    public void testAgentDuringTurn() {

        CatanAgent mockAgent = mock(CatanAgent.class);
        GameState mockState = mock(GameState.class);
        Move mockMove = mock(Move.class);

        when(mockAgent.chooseMove(mockState)).thenReturn(mockMove);

        List<CatanAgent> agents = new ArrayList<>();
        agents.add(mockAgent);

        GameAgent engine = new GameAgent(agents);

        engine.playTurn(0, mockState);

        verify(mockAgent).chooseMove(mockState);
    }

    @Test
    public void testAgentsAreIndependent() {

        CatanAgent agentone = mock(CatanAgent.class);
        CatanAgent agenttwo = mock(CatanAgent.class);

        GameState mockState = mock(GameState.class);
        Move move1 = mock(Move.class);
        Move move2 = mock(Move.class);

        when(agent1.chooseMove(mockState)).thenReturn(move1);
        when(agent2.chooseMove(mockState)).thenReturn(move2);

        List<CatanAgent> agents = Arrays.asList(agent1, agent2);

        GameAgent engine = new GameAgent(agents);

        engine.playTurn(1, mockState);

        verify(agenttwo).chooseMove(mockState);
        verify(agentone, never()).chooseMove(mockState);
    }

	private Move mock(Class<Move> class1) {
		return null;
	}

	private CatanAgent mock(Class<CatanAgent> class1) {
		return null;
	}

	private CatanAgent mock(Class<Move> class1) {
		return null;
	}
}