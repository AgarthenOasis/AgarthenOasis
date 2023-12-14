package agarthenoasis.scene.home;

import com.badlogic.gdx.Gdx;

import agarthenoasis.object.GameObject;
import agarthenoasis.object.button.BackHomeButton;
import agarthenoasis.object.button.Button;
import agarthenoasis.scene.GameScene;
import agarthenoasis.scene.SceneListener;

public class HomeScene extends GameScene {

    public HomeScene(final SceneListener listener) {
        super(listener);
    }

    @Override
    public void initialize() {
        this.gameObjectList.clear();
        final BackHomeButton backHomeButton = new BackHomeButton(this.listener);
        backHomeButton.setPosition(100, 100);
        this.gameObjectList.add(backHomeButton);
    }

    @Override
    public void start() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void dispose() {

    }



}
