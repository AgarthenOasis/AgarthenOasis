package agarthenoasis.gamesystem.battle.state;

public class BattleStateStandbyAttack extends BattleStateBase {

    public BattleStateStandbyAttack(final EventTriggeredByState owner) {
        super(owner);
    }

    @Override
    public void startAction(final BattleCharacterContext context, final BattleCharacterState preState) {

    }

    @Override
    public boolean inAction(final BattleCharacterContext context, final float deltaTime) {
        return false;   // 特に待機する必要はないがあると調整しやすいので
    }

    @Override
    public void endAction(final BattleCharacterState nextState) {

    }

    @Override
    public BattleCharacterState nextState() {
        return new BattleStateAttack(this.ownerEventTrigger);
    }

    @Override
    public BattleState getState() {
        return BattleState.StandbyAttack;
    }
}
