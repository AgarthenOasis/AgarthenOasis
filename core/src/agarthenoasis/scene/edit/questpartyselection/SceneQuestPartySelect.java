package agarthenoasis.scene.edit.questpartyselection;

import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.object.button.ObjectBackHomeButton;
import agarthenoasis.scene.GameScene;
import agarthenoasis.scene.SceneListener;

public class SceneQuestPartySelect extends GameScene {

    public SceneQuestPartySelect(final SceneListener listener) {
        super(listener);
    }

    @Override
    public void initialize() {
        final Group group = new Group();

        group.addActor(new ObjectBackHomeButton(group, this.listener));

        this.addActor(group);
    }

    @Override
    public void start() {

    }

}
