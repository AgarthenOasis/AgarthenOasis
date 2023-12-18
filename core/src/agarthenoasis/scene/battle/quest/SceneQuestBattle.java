package agarthenoasis.scene.battle.quest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.gamesystem.battle.BattleSystem;
import agarthenoasis.object.ObjectBattleFieldGrid;
import agarthenoasis.object.button.ObjectSpeedAdjustmentButton;
import agarthenoasis.object.button.ObjectSwitchAutoButton;
import agarthenoasis.scene.GameScene;
import agarthenoasis.scene.SceneListener;

public class SceneQuestBattle extends GameScene {
    private BattleSystem battleSystem;

    public SceneQuestBattle(final SceneListener listener) {
        super(listener);

        this.battleSystem = new BattleSystem(false);

        this.camera.position.set(-Gdx.graphics.getWidth() * 0.25f, Gdx.graphics.getHeight() * 0.8f, 0);

        this.camera.update();
    }

    @Override
    public void initialize() {
        final Group group = new Group();
        final Group hudGroup = new Group();
        this.battleSystem.initialize(group);

        group.addActor(new ObjectBattleFieldGrid(group, this.camera));    // グリッド

        this.addActor(group);

        // HUD配置
        hudGroup.addActor(new ObjectSpeedAdjustmentButton(hudGroup));   // 倍速ボタン
        hudGroup.addActor(new ObjectSwitchAutoButton(hudGroup));   // 倍速ボタン
        this.addHUDActor(hudGroup);
    }

    @Override
    public void start() {

    }

    @Override
    public void update(final float deltaTime) {
        super.update(deltaTime);
        this.battleSystem.update(deltaTime);
    }
}
