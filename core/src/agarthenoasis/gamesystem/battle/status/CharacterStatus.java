package agarthenoasis.gamesystem.battle.status;

import agarthenoasis.gamesystem.battle.util.DamageCalculation;

public class CharacterStatus implements StatusGetter, StatusModifier {
    public final CharacterAttribute attribute;
    public final int maxHP;
    public final int attack;
    public final int defense;
    public final int speed;
    public final float maxSkillGauge;

    private int currentHP;
    private float skillGauge;

    private static final float PercentageCriticalCondition = 0.20f;

    public CharacterStatus(final int id) {
        this.attribute = CharacterAttribute.SLASH;
        this.maxHP = 1;
        this.attack = 1;
        this.defense = 1;
        this.speed = 0;
        this.currentHP = this.maxHP;
        this.skillGauge = 0.0f;
        this.maxSkillGauge = 1.0f;
    }

    @Override
    public void addSkillGauge(final float add) {
        this.skillGauge += add;

        if (this.skillGauge > this.maxSkillGauge) {
            this.skillGauge = this.maxSkillGauge;
        }
    }

    @Override
    public boolean useSkill() {
        // そもそもスキルを使用するまでに必要な量に達していなければ何もしない
        final float consumptionGauge = 1.0f;
        if (this.skillGauge < consumptionGauge) {
            return false;
        }

        this.skillGauge -= consumptionGauge;
        return true;
    }

    @Override
    public boolean useUltimateForce() {
        // そもそもスキルを使用するまでに必要な量に達していなければ何もしない
        final float consumptionGauge = 2.0f;
        if (this.skillGauge < consumptionGauge) {
            return false;
        }

        this.skillGauge -= consumptionGauge;
        return true;
    }

    @Override
    public void addDamage(final float attack, final float rand) {
        final int damage = DamageCalculation.getDamage(attack, this.defense, rand);
        this.currentHP -= damage;

        // マイナスにはせずに0に修正する
        if (this.currentHP < 0) {
            this.currentHP = 0;
        }
    }

    @Override
    public void heal(final int heal) {
        this.currentHP += heal;
        if (this.currentHP > this.maxHP) {
            this.currentHP = this.maxHP;
        }
    }

    @Override
    public CharacterAttribute getAttribute() {
        return this.attribute;
    }

    @Override
    public int getCurrentHP() {
        return this.currentHP;
    }

    @Override
    public int getAttack() {
        return this.attack;
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public float getSkillGauge() {
        return this.skillGauge;
    }

    @Override
    public float percentageOfHP() {
        return this.currentHP / this.maxHP;
    }

    @Override
    public float percentageOfSkillGauge() {
        return this.skillGauge / this.maxSkillGauge;
    }

    @Override
    public boolean isFullHP() {
        return this.currentHP == this.maxHP;
    }

    @Override
    public boolean isCriticalCondition() {
        return this.percentageOfHP() < this.PercentageCriticalCondition;
    }

    @Override
    public boolean isAlive() {
        return this.currentHP > 0;
    }

    @Override
    public boolean canUseSkill() {
        return this.skillGauge > this.maxSkillGauge - 0.01f;
    }

}
