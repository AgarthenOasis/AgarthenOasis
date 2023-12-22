package agarthenoasis.gamesystem.battle.event;

import agarthenoasis.gamesystem.battle.status.StatusGetter;

public abstract class TurnEvent implements BattleEvent {

    final StatusGetter statusGetter;

    protected TurnEvent(final StatusGetter statusGetter) {
        this.statusGetter = statusGetter;
    }

    /**
     * バトル開始時に呼び出しされる。
     * こちらは自身にターンが回ってくるたびに呼び出しされる。
     */
    public void startBattle() {
    }

    /**
     * バトル終了時に呼び出しされる。
     * こちらは自身にターンが回ってくるたびに呼び出しされる。
     */
    public void endBattle() {
    }

    /**
     * 自身のターン開始時に呼び出しされる。
     * こちらは自身にターンが回ってくるたびに呼び出しされる。
     */
    public void startMyTurn() {
    }

    /**
     * 自身のターン終了時に呼び出しされる。
     * こちらは自身にターンが回ってくるたびに呼び出しされる。
     */
    public void endMyTurn() {
    }

    /**
     * ターン開始時に呼び出しされる。
     * こちらは毎ターン呼び出しされる。
     */
    public void startTurn() {
    }

    /**
     * ターン終了時に呼び出しされる。
     * こちらは毎ターン呼び出しされる。
     */
    public void endTurn() {
    }
}
