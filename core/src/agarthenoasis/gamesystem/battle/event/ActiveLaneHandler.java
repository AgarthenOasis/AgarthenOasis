package agarthenoasis.gamesystem.battle.event;

public interface ActiveLaneHandler {

    /**
     * 行動ターンになった場合に呼び出しされる
     */
    void onActiveTurn();

    /**
     * 攻撃中かを判定する
     *
     * @return 攻撃中の場合はtrueを返す。それ以外はfalseを返す。
     */
    boolean isAttacking();




}
