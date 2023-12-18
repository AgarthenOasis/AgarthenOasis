package agarthenoasis.scene;

import java.util.Stack;

import agarthenoasis.scene.battle.quest.SceneQuestBattle;
import agarthenoasis.scene.edit.questpartyselection.SceneQuestPartySelect;
import agarthenoasis.scene.home.SceneHome;

public class SceneChanger implements SceneListener {
    private Stack<GameScene> sceneStack;

    public SceneChanger() {
        this.sceneStack = new Stack<>();
    }

    @Override
    public void onSceneChanged() {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return;
        }

    }

    @Override
    public void changeScene(final SceneType sceneType, final boolean stackClear) {
        // 必要であればシーン変更前にスタックを空にする
        if (stackClear) {
            for (; !this.sceneStack.isEmpty(); ) {
                this.backScene(false);
            }
        }

        // シーンの変更を伝える
        this.onSceneChanged();

        // シーンの切り替え(スタックに積む)
        switch (sceneType) {
            case Home:
                this.sceneStack.push(new SceneHome(this));
                break;
            case QuestPartySelection:
                this.sceneStack.push(new SceneQuestPartySelect(this));
                break;
            case QuestBattle:
                this.sceneStack.push(new SceneQuestBattle(this));
                break;
            case ArenaPartySelection:
            case CharacterSelection:
            case GuildBattlePartySelection:

            case Exit:
            default:
                return;
        }

        this.sceneInitialize();
    }

    @Override
    public void backScene(final boolean sceneStart) {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return;
        }

        // 破棄処理後にスタックから除去
        this.sceneDispose();
        this.sceneStack.pop();

        // シーンの変更を伝える
        this.onSceneChanged();

        // 必要であればシーンの開始処理を呼ぶ
        if (sceneStart && !this.sceneStack.isEmpty()) {
            this.sceneStack.peek().start();
        }
    }

    public void sceneInitialize() {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return;
        }

        this.sceneStack.peek().initialize();
        this.sceneStack.peek().start();
    }

    public void sceneUpdate(final float deltaTime) {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return;
        }

        this.sceneStack.peek().update(deltaTime);
    }

    public void sceneDraw() {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return;
        }

        this.sceneStack.peek().draw();
    }

    public void sceneDispose() {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return;
        }

        this.sceneStack.peek().dispose();
    }

}
