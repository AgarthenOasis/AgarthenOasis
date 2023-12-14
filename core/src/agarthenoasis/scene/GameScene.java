package agarthenoasis.scene;

import com.badlogic.gdx.InputProcessor;

import java.util.LinkedList;

import agarthenoasis.object.GameObject;

public abstract class GameScene implements InputProcessor {
    protected final SceneListener listener;
    protected final LinkedList<GameObject> gameObjectList;

    public GameScene(final SceneListener listener) {
        this.listener = listener;
        this.gameObjectList = new LinkedList<>();
    }

    public abstract void initialize();

    public abstract void start();

    public abstract void update(final float deltaTime);

    public void draw() {
        for (final GameObject object : this.gameObjectList) {
            object.draw();
        }
    }

    public abstract void dispose();

    @Override
    public boolean keyDown(final int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(final int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(final char character) {
        return false;
    }

    @Override
    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        // 全オブジェクトに通知
        for (final GameObject object : this.gameObjectList) {
            if (object.canTouch(screenX, screenY)) {
                object.onTouch(screenX, screenY);
            }
        }

        return true;
    }

    @Override
    public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(final int screenX, final int screenY, final int pointer, final int button) {
        return false;
    }

    @Override
    public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(final int screenX, final int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(final float amountX, final float amountY) {
        return false;
    }

}
