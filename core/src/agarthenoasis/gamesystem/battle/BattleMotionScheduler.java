package agarthenoasis.gamesystem.battle;

import java.util.HashMap;
import java.util.Optional;

import agarthenoasis.gamesystem.battle.field.BattleField;
import agarthenoasis.gamesystem.battle.field.FieldGrid;
import agarthenoasis.motion.BattleMotionHandler;

// 攻撃時の待機時間などを決めるクラス
public class BattleMotionScheduler {
    private final BattleField battleField;
    private Optional<BattleMotionHandler> attackerBattleMotion;
    private Optional<HashMap<FieldGrid, BattleMotionHandler>> receiveAttackBattleMotion;
    private HashMap<FieldGrid, BattleMotionHandler> tempReceiveAttackBattleMotion;

    private boolean isMoveMotion;
    private boolean isAttackMotion;
    private boolean isSkillMotion;

    public BattleMotionScheduler(final BattleField battleField) {
        this.attackerBattleMotion = Optional.empty();
        this.receiveAttackBattleMotion = Optional.empty();

        this.battleField = battleField;
        this.resetFlag();
    }

    public boolean isInMotion() {
        return this.isAttackMotion | this.isMoveMotion | this.isSkillMotion;
    }

    public void motionForcedTermination() {

    }

    private void resetFlag() {
        this.isMoveMotion = false;
        this.isAttackMotion = false;
        this.isSkillMotion = false;
    }

    public boolean setAttackerBattleMotion(final BattleMotionHandler attackerBattleMotion, final HashMap<FieldGrid, BattleMotionHandler> receiveAttackBattleMotion) {
        // 何かしらモーション中であればセットしない
        if (this.isInMotion()) {
            return false;
        }

        this.attackerBattleMotion = Optional.of(attackerBattleMotion);
        this.receiveAttackBattleMotion = Optional.of(receiveAttackBattleMotion);

        attackerBattleMotion.onAttackStart();
        return true;
    }

    public void update(final float deltaTime) {
        // 攻撃モーション再生
        this.attackMotion(deltaTime);

        // 被攻撃モーション再生
        if (this.attackerBattleMotion.isPresent()) {
            //final BattleMotionHandler receiveMotion = this.receiveAttackBattleMotion.get();

            //receiveMotion.onReceiveAttackStart();

        }
    }

    // 攻撃待機
    private void standbyToAttackMotion(final float deltaTime) {

    }

    // 行動待機
    private void waitMotion(final float deltaTime) {

    }

    private void attackMotion(final float deltaTime) {
        // 攻撃者のモーションが設定されていない場合は何もしない
        if (!this.attackerBattleMotion.isPresent()) {
            return;
        }

        final BattleMotionHandler attackerMotion = this.attackerBattleMotion.get();

        // 移動モーション
        // 移動完了までは攻撃モーションを行わない
        if (!this.isAttackMotion) {
            if (this.isMoveMotion) {
                if (!attackerMotion.movementForAttack(deltaTime)) {
                    attackerMotion.endMovementForAttack();
                    this.isAttackMotion = true;
                }
            } else {
                attackerMotion.startMovementForAttack();
                this.isMoveMotion = true;
            }
            return;
        }

        // 攻撃時モーション
        if (attackerMotion.onAttacking(deltaTime)) {
            // 被攻撃時モーション再生

        } else {
            // 攻撃終了
            attackerMotion.onAttackEnd();
            this.attackerBattleMotion = Optional.empty();   // モーション終了なので空にする
        }
    }

}
