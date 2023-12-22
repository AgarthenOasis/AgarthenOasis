package agarthenoasis.gamesystem.battle.state;

public interface BattleCharacterState {

    /**
     * このステート開始時に呼び出される
     *
     * @param context  コンテキスト
     * @param preState 前回のステート
     */
    void startAction(final BattleCharacterContext context, final BattleCharacterState preState);

    /**
     * このステート中は毎フレーム呼び出される
     *
     * @param context   コンテキスト
     * @param deltaTime デルタタイム
     */
    boolean inAction(final BattleCharacterContext context, final float deltaTime);

    /**
     * このステート終了時に呼び出される
     *
     * @param nextState 次に呼び出されるステート
     */
    void endAction(final BattleCharacterState nextState);

    /**
     * このステート終了時に次に開始されることを期待するステートを取得する
     * バトル中にスキル発動などのステートが変更される条件を満たした場合は呼び出しされない
     *
     * @return 外部からの変更以外でこのステートが終了した時に次に呼び出すステート
     */
    BattleCharacterState nextState();

    /**
     * このステートを識別するEnumを返す
     *
     * @return このステートを表すEnum値
     */
    BattleState getState();

}
