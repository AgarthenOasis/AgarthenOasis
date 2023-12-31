package agarthenoasis;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import agarthenoasis.scene.SceneChanger;
import agarthenoasis.scene.SceneListener;
import agarthenoasis.scene.SceneType;

public class AgarthenOasis extends ApplicationAdapter implements InputProcessor {
    private float elapsedTime;
    private SceneChanger sceneChanger;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(this);
        this.sceneChanger = new SceneChanger();
        this.sceneChanger.changeScene(SceneType.EditQuestPartySelection, false);
        this.elapsedTime = 0.0f;
    }

    @Override
    public void render() {
        final float deltaTime = Gdx.graphics.getDeltaTime();
        this.elapsedTime += deltaTime;
        ScreenUtils.clear(0.4f, 0.4f, 0.8f, 1.0f);

        this.sceneChanger.sceneUpdate(deltaTime);
        this.sceneChanger.sceneDraw();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(final int width, final int height) {
        Gdx.app.log("resize", "width: " + width + "height: " + height);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        // y座標は反転させる
        final int fixed_y = Gdx.graphics.getHeight() - screenY;
        return this.sceneChanger.touchDown(screenX, fixed_y, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
