package agarthenoasis.character.status;

public class GameCharacterStatus {
    public final CharacterAttribute attribute;
    public final int maxHP;
    public final int attack;
    public final int defense;
    public final int speed;
    public final float maxSkillGauge;

    private int currentHP;
    private float skillGauge;

    private static final float PercentageCriticalCondition = 0.20f;

    public GameCharacterStatus(final int id) {
        this.attribute = CharacterAttribute.SLASH;
        this.maxHP = 1;
        this.attack = 1;
        this.defense = 1;
        this.speed = 0;
        this.currentHP = this.maxHP;
        this.skillGauge = 0.0f;
        this.maxSkillGauge = 1.0f;
    }

    public int getCurrentHP() {
        return this.currentHP;
    }

    public float getSkillGauge() {
        return this.skillGauge;
    }

    public void addSkillGage(final float add) {
        this.skillGauge += add;

        if (this.skillGauge > this.maxSkillGauge) {
            this.skillGauge = this.maxSkillGauge;
        }
    }

    public boolean useSkill() {
        // そもそもスキルを使用するまでに必要な量に達していなければ何もしない
        final float consumptionGauge = 1.0f;
        if (this.skillGauge < consumptionGauge) {
            return false;
        }

        this.skillGauge -= consumptionGauge;
        return true;
    }

    public boolean useUltimateForce() {
        // そもそもスキルを使用するまでに必要な量に達していなければ何もしない
        final float consumptionGauge = 2.0f;
        if (this.skillGauge < consumptionGauge) {
            return false;
        }

        this.skillGauge -= consumptionGauge;
        return true;
    }

    public void damage(final int damage) {
        this.currentHP -= damage;
        if (this.currentHP < 0) {
            this.currentHP = 0;
        }
    }

    public void heal(final int heal) {
        this.currentHP += heal;
        if (this.currentHP > this.maxHP) {
            this.currentHP = this.maxHP;
        }
    }

    /**
     * 最大HPに対する現在HPの割合を取得する
     *
     * @return 最大HPに対する現在HPの割合を返す(1.0f ~ 0.0f)
     */
    public float percentageOfHP() {
        return this.currentHP / this.maxHP;
    }

    /**
     * 最大スキルゲージに対する現在スキルゲージの割合を取得する
     *
     * @return 最大スキルゲージに対する現在スキルゲージの割合を返す(1.0f ~ 0.0f)
     */
    public float percentageOfSkillGauge() {
        return this.skillGauge / this.maxSkillGauge;
    }

    /**
     * HPが満タンかどうかを取得する
     *
     * @return HPが満タンの場合はTrueを返す。それ以外はFalseを返す。
     */
    public boolean isFullHP() {
        return this.currentHP == this.maxHP;
    }

    /**
     * 瀕死状態かどうかを取得する
     *
     * @return 瀕死状態(現在HPが一定以下)の場合はTrueを返す。それ以外はFalseを返す。
     */
    public boolean isCriticalCondition() {
        return this.percentageOfHP() < this.PercentageCriticalCondition;
    }

    /**
     * 生存しているかどうかを取得する
     *
     * @return ＨＰが0より大きい場合はTrueを返す。それ以外はFalseを返す。
     */
    public boolean isAlive() {
        return this.currentHP > 0;
    }

    public boolean canUseSkill() {
        return this.skillGauge > this.maxSkillGauge - 0.01f;
    }

}
