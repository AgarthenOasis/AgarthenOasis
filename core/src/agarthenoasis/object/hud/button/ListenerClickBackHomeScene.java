package agarthenoasis.object.hud.button;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import agarthenoasis.scene.SceneListener;
import agarthenoasis.scene.SceneType;

public class ListenerClickBackHomeScene extends ClickListener {
    protected final SceneListener sceneListener;

    public ListenerClickBackHomeScene(final SceneListener listener) {
        this.sceneListener = listener;
    }

    @Override
    public void clicked(final InputEvent event, final float x, final float y) {
        this.sceneListener.changeScene(SceneType.Home, true);
    }
}
