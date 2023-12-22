package agarthenoasis.gamesystem.battle.event;

import agarthenoasis.gamesystem.battle.status.StatusGetter;

public class EventAttack extends TurnEvent {

    protected EventAttack(final StatusGetter statusGetter) {
        super(statusGetter);
    }

    @Override
    public EventType getEventType() {
        return EventType.Attack;
    }

    @Override
    public void startEvent() {

    }

    @Override
    public boolean inEvent(final float deltaTime) {
        return false;
    }

    @Override
    public void endEvent() {

    }

}
