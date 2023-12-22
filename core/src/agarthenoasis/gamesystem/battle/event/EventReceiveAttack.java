package agarthenoasis.gamesystem.battle.event;

import agarthenoasis.gamesystem.battle.status.StatusGetter;

public class EventReceiveAttack extends TurnEvent {
    private int beforeDamageHP;

    protected EventReceiveAttack(final StatusGetter statusGetter) {
        super(statusGetter);
        this.beforeDamageHP = this.statusGetter.getCurrentHP();
    }

    @Override
    public EventType getEventType() {
        return EventType.ReceiveAttack;
    }

    @Override
    public void startEvent() {
        this.beforeDamageHP = this.statusGetter.getCurrentHP();
    }

    @Override
    public boolean inEvent(final float deltaTime) {



        return false;
    }

    @Override
    public void endEvent() {

    }

}
