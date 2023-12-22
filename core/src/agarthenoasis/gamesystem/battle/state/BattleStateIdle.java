package agarthenoasis.gamesystem.battle.state;

public class BattleStateIdle extends BattleStateBase {

    private final float idleTime;
    private float elapsedTime;

    public BattleStateIdle(final EventTriggeredByState owner) {
        super(owner);
        this.idleTime = 10;
        this.elapsedTime = 0;
    }

    @Override
    public void startAction(final BattleCharacterContext context, final BattleCharacterState preState) {

    }

    @Override
    public boolean inAction(final BattleCharacterContext context, final float deltaTime) {
        // 行動可能時間になるまでは待機する
        if (this.idleTime < this.elapsedTime) {
            return true;
        }
        return false;
    }

    @Override
    public void endAction(final BattleCharacterState nextState) {

    }

    @Override
    public BattleCharacterState nextState() {
        return new BattleStateStandbyAttack(this.ownerEventTrigger);
    }

    @Override
    public BattleState getState() {
        return BattleState.Idle;
    }
}
