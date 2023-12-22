package agarthenoasis.scene;

import com.badlogic.gdx.InputProcessor;

public interface SceneListener {
    public void onSceneChanged();

    public void changeScene(final SceneType sceneType, final boolean stackClear);

    public void backScene(final boolean sceneStart);

}
