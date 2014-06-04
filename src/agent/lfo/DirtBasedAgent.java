package agent.lfo;

import sandbox.Creature;
import sandbox.MovementAction;
import agent.AbstractSandboxAgent;

public class DirtBasedAgent extends AbstractSandboxAgent {

	public DirtBasedAgent(int size, Creature c) {
		super(size, c);
	}

	@Override
	public MovementAction testAction(Creature c) {
		return null;
	}

	@Override
	protected Creature createCreature() {
		return null;
	}

	@Override
	public void runAgent(int iterations) {

	}

	@Override
	public void saveTrace(String filename) {

	}

}
