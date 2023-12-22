package agarthenoasis.gamesystem.battle.event;

public interface BattleEvent {

    /**
     * イベントの種類
     */
    EventType getEventType();

    /**
     * イベント開始時に一度だけ呼び出しされる。
     */
    void startEvent();

    /**
     * イベント中に呼び出し。毎フレーム呼び出しされる。
     *
     * @return trueを返している間はイベント続行。falseを返すとイベント終了。
     */
    boolean inEvent(final float deltaTime);

    /**
     * イベント終了時に一度だけ呼び出しされる。
     */
    void endEvent();


}
