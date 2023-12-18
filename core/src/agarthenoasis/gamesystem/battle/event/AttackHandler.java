package agarthenoasis.gamesystem.battle.event;

public interface AttackHandler {

    /**
     * 攻撃時に呼び出しされる
     *
     * @return 攻撃した場合はtrueを返す
     */
    void onAttacked();

    /**
     * 被攻撃時に呼び出しされる
     * アビリティなどで回避する場合はここでfalseを返す
     *
     * @return 攻撃を受ける場合はtrueを返す。それ以外はfalseを返す。
     */
    boolean receiveAttack();

    /**
     * receiveAttackの結果に呼び出しされる
     * アビリティなどで反撃を行う場合はここでtrueを返す
     *
     * @return 反撃を行う場合はtrueを返す。それ以外はfalseを返す。
     */
    boolean canCounterattack();

    /**
     * 反撃時に呼び出しされる
     * canCounterattackでtrueを返していなければ呼び出しされない
     */
    void onCounterattack();

}
