package agarthenoasis.scene.battle.quest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.gamesystem.battle.BattleSystem;
import agarthenoasis.object.field.ObjectBattleFieldGrid;
import agarthenoasis.object.hud.button.ButtonSpeedAdjustment;
import agarthenoasis.object.hud.button.ButtonSwitchAuto;
import agarthenoasis.object.hud.button.ButtonSwitchSkill;
import agarthenoasis.object.hud.display.DisplayCharacterList;
import agarthenoasis.scene.GameScene;
import agarthenoasis.scene.SceneListener;

public class SceneQuestBattle extends GameScene {
    private BattleSystem battleSystem;

    public SceneQuestBattle(final SceneListener listener) {
        super(listener);

        this.battleSystem = new BattleSystem(false);

        // カメラの位置は何とかしたいところ
        this.camera.position.set(-Gdx.graphics.getWidth() * 0.15f, Gdx.graphics.getHeight() * 0.85f, 0);

        this.camera.update();
    }

    @Override
    public void initialize() {
        final Group group = new Group();
        final Group hudGroup = new Group();
        //this.battleSystem.initialize(group);

        group.addActor(new ObjectBattleFieldGrid(this, group, this.camera));    // グリッド

        this.addActor(group);

        // HUD配置
        hudGroup.addActor(new ButtonSpeedAdjustment(this, hudGroup));   // 倍速ボタン
        hudGroup.addActor(new ButtonSwitchAuto(this, hudGroup));        // 自動手動切り替えボタン
        hudGroup.addActor(new ButtonSwitchSkill(this, hudGroup));       // スキル切り替えボタン
        hudGroup.addActor(new DisplayCharacterList(this, hudGroup));    // 戦闘キャラ一覧

        this.addHUDActor(hudGroup);
    }

    @Override
    public void start() {

    }

    @Override
    public void update(final float deltaTime) {
        super.update(deltaTime);
        this.battleSystem.update(deltaTime);
        //this.camera.zoom += 0.001f;
    }
}
