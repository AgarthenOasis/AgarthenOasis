package agarthenoasis.gamesystem.battle;

import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.Random;

import agarthenoasis.gamesystem.battle.field.BattleField;
import agarthenoasis.gamesystem.battle.field.FieldGrid;
import agarthenoasis.object.character.GameCharacter;

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

    public void initialize(final Group group, final GameCharacter gameCharacter) {
        this.battleField.setAllyCharacter(FieldGrid.Center, gameCharacter);
    }

    public void update(final float deltaTime) {
        // スケジューラ―の更新
        this.battleAttackScheduler.update(deltaTime);
        this.battleMotionScheduler.update(deltaTime);
    }

    public static float getDamageRandomPer() {
        return 1.03f;
    }

    private static long getSeed() {
        return 0xdeafbeef;
    }

    // クライアント側
    private static int getRand(final boolean isClient) {

        return 100;
    }
}
