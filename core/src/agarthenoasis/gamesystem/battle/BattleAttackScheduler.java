package agarthenoasis.gamesystem.battle;

import java.util.Optional;

import agarthenoasis.gamesystem.LanePanel;
import agarthenoasis.gamesystem.battle.field.BattleField;
import agarthenoasis.object.character.GameCharacter;
import agarthenoasis.util.Pair;

// あくまで行動順を決めるだけのクラス
public class BattleAttackScheduler {
    private final BattleField battleField;
    private final ActiveLane activeLane;
    private boolean isUpdate;
    private boolean isSkip;

    private Optional<GameCharacter> attackCharacter;

    public BattleAttackScheduler(final boolean isTurnBattle, final BattleField battleField) {
        this.battleField = battleField;
        this.activeLane = new ActiveLane(isTurnBattle);
        this.isUpdate = true;
        this.isSkip = false;
        this.attackCharacter = Optional.empty();
    }

    public void setSkip(final boolean isSkip) {
        this.isSkip = isSkip;
    }

    /**
     * キャラクターがアクティブレーンに追加できるかを返す
     *
     * @return キャラクターがアクティブレーンに追加できる場合はtrueを返す。それ以外はfalseを返す。
     */
    public boolean canPushLane() {
        return this.activeLane.canPushLane();
    }

    /**
     * キャラクターが攻撃可能かを返す
     *
     * @return キャラクターが攻撃可能な場合はtrueを返す。それ以外はfalseを返す。
     */
    public boolean canAttack() {
        return !this.activeLane.isEmpty();
    }

    public boolean canUseSkill() {
        return this.activeLane.isEmpty();
    }

    public Pair<Optional<GameCharacter>, LanePanel> getAttackCharacterAndLaneType() {
        return this.activeLane.pop();
    }

    // 更新するか
    public void setIsUpdate(final boolean enable) {
        this.isUpdate = enable;
    }

    public void update(final float deltaTime) {
        if (!this.isUpdate && !this.isSkip) {
            return;
        }

        // this.isAttacking;

    }

}
