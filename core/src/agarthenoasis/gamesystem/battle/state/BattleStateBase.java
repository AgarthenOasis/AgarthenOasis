package agarthenoasis.gamesystem.battle.state;

public abstract class BattleStateBase implements BattleCharacterState {
    protected final EventTriggeredByState ownerEventTrigger;
    protected BattleCharacterState nextState;

    public BattleStateBase(final EventTriggeredByState owner) {
        this.ownerEventTrigger = owner;
        this.nextState = this;
    }

    public void setNextState(final BattleCharacterState state) {
        this.nextState = state;
    }

}
