package agarthenoasis.gamesystem.battle.event;

import agarthenoasis.gamesystem.battle.status.StatusGetter;

public class EventCounterAttack extends TurnEvent {
    protected EventCounterAttack(final StatusGetter statusGetter) {
        super(statusGetter);
    }

    @Override
    public EventType getEventType() {
        return EventType.CounterAttack;
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
