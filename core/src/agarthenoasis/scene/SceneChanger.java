package agarthenoasis.scene;

import java.util.Stack;

import agarthenoasis.scene.edit.questpartyselection.QuestPartySelectScene;
import agarthenoasis.scene.home.HomeScene;

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
                this.sceneStack.push(new HomeScene(this));
                break;
            case EditQuestPartySelection:
                this.sceneStack.push(new QuestPartySelectScene(this));
                break;
            case EditArenaPartySelection:
            case EditCharacterSelection:
            case EditGuildBattlePartySelection:
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

    @Override
    public void sceneInitialize() {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return;
        }

        this.sceneStack.peek().initialize();
        this.sceneStack.peek().start();
    }

    @Override
    public void sceneUpdate(final float deltaTime) {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return;
        }

        this.sceneStack.peek().update(deltaTime);
    }

    @Override
    public void sceneDraw() {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return;
        }

        this.sceneStack.peek().draw();
    }

    @Override
    public void sceneDispose() {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return;
        }

        this.sceneStack.peek().dispose();
    }

    @Override
    public boolean keyDown(final int keycode) {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return false;
        }

        return this.sceneStack.peek().keyDown(keycode);
    }

    @Override
    public boolean keyUp(final int keycode) {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return false;
        }

        return this.sceneStack.peek().keyUp(keycode);
    }

    @Override
    public boolean keyTyped(final char character) {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return false;
        }

        return this.sceneStack.peek().keyTyped(character);
    }

    @Override
    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return false;
        }

        return this.sceneStack.peek().touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return false;
        }

        return this.sceneStack.peek().touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchCancelled(final int screenX, final int screenY, final int pointer, final int button) {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return false;
        }

        return this.sceneStack.peek().touchCancelled(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return false;
        }

        return this.sceneStack.peek().touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(final int screenX, final int screenY) {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return false;
        }

        return this.sceneStack.peek().mouseMoved(screenX, screenY);
    }

    @Override
    public boolean scrolled(final float amountX, final float amountY) {
        // シーンがそもそも存在しなければ何もしない
        if (this.sceneStack.isEmpty()) {
            return false;
        }

        return this.sceneStack.peek().scrolled(amountX, amountY);
    }
}
