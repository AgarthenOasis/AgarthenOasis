package agarthenoasis.scene;

import com.badlogic.gdx.InputProcessor;

public interface SceneListener extends InputProcessor {
    public void onSceneChanged();

    public void changeScene(final SceneType sceneType, final boolean stackClear);

    public void backScene(final boolean sceneStart);

    public void sceneInitialize();

    public void sceneUpdate(final float deltaTime);

    public void sceneDraw();

    public void sceneDispose();

}
