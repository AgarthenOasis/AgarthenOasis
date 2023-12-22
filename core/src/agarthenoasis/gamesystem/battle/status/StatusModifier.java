package agarthenoasis.gamesystem.battle.status;

public interface StatusModifier {
    void addSkillGauge(final float add);

    boolean useSkill();

    boolean useUltimateForce();

    void addDamage(final float attack, final float rand);

    void heal(final int heal);

}
