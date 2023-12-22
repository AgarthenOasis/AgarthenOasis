package agarthenoasis.gamesystem.battle.event;

import agarthenoasis.gamesystem.battle.status.StatusGetter;

public class EventSkill extends TurnEvent {

    protected EventSkill(final StatusGetter statusGetter) {
        super(statusGetter);
    }

    @Override
    public EventType getEventType() {
        return EventType.Skill;
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

    @Override
    public void startBattle() {

    }

    @Override
    public void endBattle() {

    }

    @Override
    public void startMyTurn() {

    }

    @Override
    public void endMyTurn() {

    }

    @Override
    public void startTurn() {

    }

    @Override
    public void endTurn() {

    }
}
