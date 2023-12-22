package agarthenoasis.gamesystem.battle.state;

public class BattleStateAttack extends BattleStateBase {

    public BattleStateAttack(final EventTriggeredByState owner) {
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
        return new BattleStateIdle(this.ownerEventTrigger);
    }

    @Override
    public BattleState getState() {
        return BattleState.Attack;
    }
}
