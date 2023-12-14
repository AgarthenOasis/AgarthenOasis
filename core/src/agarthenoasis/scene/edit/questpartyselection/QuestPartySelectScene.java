package agarthenoasis.scene.edit.questpartyselection;

import com.badlogic.gdx.Gdx;

import agarthenoasis.object.button.BackHomeButton;
import agarthenoasis.scene.GameScene;
import agarthenoasis.scene.SceneListener;

public class QuestPartySelectScene extends GameScene {

    public QuestPartySelectScene(final SceneListener listener) {
        super(listener);
    }

    @Override
    public void initialize() {
        this.gameObjectList.clear();
        final BackHomeButton backHomeButton = new BackHomeButton(this.listener);
        final int scaleX = 256;
        final int scaleY = 256;
        final int posX = Gdx.graphics.getWidth() - scaleX;
        final int posY = Gdx.graphics.getHeight() - scaleY;

        backHomeButton.setPosition(posX, posY);
        backHomeButton.setScale(scaleX, scaleY);

        this.gameObjectList.add(backHomeButton);
    }

    @Override
    public void start() {

    }

    @Override
    public void update(final float deltaTime) {

    }

    @Override
    public void dispose() {

    }

}
