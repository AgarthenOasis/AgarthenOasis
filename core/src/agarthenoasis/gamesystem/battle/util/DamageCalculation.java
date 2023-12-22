package agarthenoasis.gamesystem.battle.util;

public class DamageCalculation {
    public static int getDamage(final float attackerAttack, final float suffererDefense, final float randPer) {
        final int constantDamage = (int) (250.0f * randPer);
        final int result = (int) ((attackerAttack - suffererDefense / 3) * randPer);

        // 防御力が高すぎる場合はマイナスになるので固定値を返す
        if (result < 0) {
            return constantDamage;
        }

        return result;
    }
}
