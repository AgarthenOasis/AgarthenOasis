package agarthenoasis.gamesystem.battle.status;

public interface StatusGetter {

    /**
     * 属性を取得する
     *
     * @return 設定された属性を返す
     */
    CharacterAttribute getAttribute();

    /**
     * 現在HPを取得する
     *
     * @return 現在HPを返す
     */
    int getCurrentHP();

    int getAttack();

    int getDefense();

    int getSpeed();

    /**
     * スキルゲージの量を取得する
     *
     * @return スキルゲージの量を返す(2.0f ~ 0.0f)
     */
    float getSkillGauge();

    /**
     * 最大HPに対する現在HPの割合を取得する
     *
     * @return 最大HPに対する現在HPの割合を返す(1.0f ~ 0.0f)
     */
    float percentageOfHP();

    /**
     * 最大スキルゲージに対する現在スキルゲージの割合を取得する
     *
     * @return 最大スキルゲージに対する現在スキルゲージの割合を返す(1.0f ~ 0.0f)
     */
    float percentageOfSkillGauge();

    /**
     * HPが満タンかどうかを取得する
     *
     * @return HPが満タンの場合はTrueを返す。それ以外はFalseを返す。
     */
    boolean isFullHP();

    /**
     * 瀕死状態かどうかを取得する
     *
     * @return 瀕死状態(現在HPが一定以下)の場合はTrueを返す。それ以外はFalseを返す。
     */
    boolean isCriticalCondition();

    /**
     * 生存しているかどうかを取得する
     *
     * @return ＨＰが0より大きい場合はTrueを返す。それ以外はFalseを返す。
     */
    boolean isAlive();

    boolean canUseSkill();
}
