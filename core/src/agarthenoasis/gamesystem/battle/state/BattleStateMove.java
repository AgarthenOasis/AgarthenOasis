package agarthenoasis.gamesystem.battle.state;

public class BattleStateMove extends BattleStateBase {

    public BattleStateMove(final EventTriggeredByState owner) {
        super(owner);
    }

    @Override
    public void startAction(final BattleCharacterContext context, final BattleCharacterState preState) {

    }

    @Override
    public boolean inAction(final BattleCharacterContext context, final float deltaTime) {
        return false;
    }

    @Override
    public void endAction(final BattleCharacterState nextState) {

    }

    @Override
    public BattleCharacterState nextState() {
        return null;
    }

    @Override
    public BattleState getState() {
        return BattleState.Move;
    }
}