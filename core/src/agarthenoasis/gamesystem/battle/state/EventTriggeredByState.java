package agarthenoasis.gamesystem.battle.state;

import agarthenoasis.gamesystem.battle.event.EventType;

public interface EventTriggeredByState {
    void startEvent(final EventType eventType);

    boolean inEvent(final EventType eventType, final float deltaTime);

    void endEvent(final EventType eventType);

    void startBattle(final EventType eventType);

    void endBattle(final EventType eventType);

    void startMyTurn(final EventType eventType);

    void endMyTurn(final EventType eventType);

    void startTurn(final EventType eventType);

    void endTurn(final EventType eventType);

}
