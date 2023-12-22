package agarthenoasis.gamesystem.battle.state;

import agarthenoasis.gamesystem.battle.event.EventType;

public class BattleStateReceiveAttack extends BattleStateBase {

    public BattleStateReceiveAttack(final EventTriggeredByState owner) {
        super(owner);
    }

    @Override
    public void startAction(final BattleCharacterContext context, final BattleCharacterState preState) {
        this.ownerEventTrigger.startEvent(EventType.ReceiveAttack);
    }

    @Override
    public boolean inAction(final BattleCharacterContext context, final float deltaTime) {
        return this.ownerEventTrigger.inEvent(EventType.ReceiveAttack, deltaTime);
    }

    @Override
    public void endAction(final BattleCharacterState nextState) {
        this.ownerEventTrigger.endEvent(EventType.ReceiveAttack);
    }

    @Override
    public BattleCharacterState nextState() {
        return new BattleStateIdle(this.ownerEventTrigger);
    }

    @Override
    public BattleState getState() {
        return BattleState.ReceiveAttack;
    }
}
