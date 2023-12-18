package agarthenoasis.gamesystem.battle;

import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.Optional;
import java.util.Random;

import agarthenoasis.character.BaseBattleAction;
import agarthenoasis.gamesystem.battle.field.BattleField;
import agarthenoasis.gamesystem.battle.field.FieldGrid;

public class BattleSystem {
    private final Random random;
    private final BattleField battleField;      // フィールド
    private final BattleAttackScheduler battleAttackScheduler;
    private final BattleMotionScheduler battleMotionScheduler;

    public BattleSystem(final boolean isTurnBattle) {
        this.battleField = new BattleField();
        this.random = new Random(getSeed());
        this.battleMotionScheduler = new BattleMotionScheduler(this.battleField);
        this.battleAttackScheduler = new BattleAttackScheduler(isTurnBattle, this.battleField);
    }

    public void initialize(final Group group) {

    }

    public void update(final float deltaTime) {
        // スケジューラ―の更新
        this.battleAttackScheduler.update(deltaTime);
        this.battleMotionScheduler.update(deltaTime);


        /*
        if (this.battleAttackScheduler.canAttack()) {
            final Pair<Optional<GameCharacter>, LanePanel> pair = this.battleAttackScheduler.getAttackCharacterAndLaneType();
            if (pair.first.isPresent()) {

            }
        }
        * */

        //this.battleField.getAllyAttackRange();

        //BattleSystem.attack();
    }

    /**
     * 攻撃処理の順序などは全てここで決める
     */
    private static void attack(final Optional<BaseBattleAction>[] field, final FieldGrid[] attackRange, final BaseBattleAction attacker) {
        // 攻撃者に攻撃通知
        attacker.onAttacked();

        // 取得した要素を元に配列にアクセスし、キャラクターが存在した場合は攻撃を行う
        for (final FieldGrid fieldGrid : attackRange) {
            final int index = fieldGrid.ordinal();
            // いなければ何もしない
            if (!field[index].isPresent()) {
                continue;
            }

            final BaseBattleAction enemy = field[index].get();
            // 被攻撃側に攻撃されたことを通知
            if (enemy.receiveAttack()) {
                // ダメージ計算

                // 被攻撃側のHPを減らす

                // 再度通知

                // 倒された場合は通知
                enemy.onKnockedDown();
            }

            // 反撃処理
            if (enemy.canCounterattack()) {
                enemy.onCounterattack();
            }
        }
    }

    private static void speed() {

    }

    public static float getDamageRandomPer() {
        return 1.03f;
    }

    public static int getDamage(final float attackerAttack, final float suffererDefense, final float randPer) {
        final int constantDamage = (int) (250.0f * randPer);
        final int result = (int) ((attackerAttack - suffererDefense / 3) * randPer);

        // 防御力が高すぎる場合はマイナスになるので固定値を返す
        if (result < 0) {
            return constantDamage;
        }

        return result;
    }

    private static long getSeed() {
        return 0xdeafbeef;
    }

    // クライアント側
    private static int getRand(final boolean isClient) {

        return 100;
    }
}
