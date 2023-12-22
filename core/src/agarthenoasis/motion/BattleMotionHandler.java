package agarthenoasis.motion;

import agarthenoasis.gamesystem.battle.field.FieldGrid;

public interface BattleMotionHandler {

    /**
     * 攻撃のための移動を開始する場合に一度だけ呼び出しされる
     */
    void startMovementForAttack();

    /**
     * 攻撃中は毎フレーム呼び出しされる
     * 主にモーションの調整を行う
     *
     * @return モーションを継続する場合はtrueを返す。終了時はfalseを返す。
     */
    boolean movementForAttack(final float deltaTime);

    void endMovementForAttack();

    /**
     * 攻撃を開始する場合に一度だけ呼び出しされる
     */
    void onAttackStart();

    /**
     * 主に攻撃モーションの時間調整を行う。
     * trueを返している間は毎フレーム呼び出しされ、falseを返した瞬間に終了する。
     *
     * @return モーションを継続する場合はtrueを返す。終了時はfalseを返す。
     */
    boolean onAttacking(final float deltaTime);

    /**
     * 主に攻撃が相手にヒットするまでの時間調整を行う。
     * onAttackingがtrueを返している間は毎フレーム呼び出しされる。
     * trueを返した瞬間に相手に被攻撃時のモーションが呼び出しされる。
     * 複数存在する場合は相手の数だけ呼び出しされる。
     *
     * @param deltaTime デルタタイム
     * @param fieldGrid 攻撃する相手の位置
     * @return モーションを継続する場合はfalseを返す。終了時はtrueを返す。
     */
    boolean attackHit(final float deltaTime, final FieldGrid fieldGrid);

    /**
     * 攻撃を終了する場合に一度だけ呼び出しされる
     */
    void onAttackEnd();

    void onReceiveAttackStart();

    boolean onReceiveAttacking();

    void onReceiveAttackEnd();
}
