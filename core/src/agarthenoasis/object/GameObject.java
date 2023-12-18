package agarthenoasis.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class GameObject extends Actor implements GameObjectLifecycle {

    final protected Group group;

    /**
     * @param sceneGroup 子オブジェクトを格納するためのグループ。シーンから渡される。
     */
    public GameObject(final Group sceneGroup) {
        this.group = new Group();
        sceneGroup.addActor(this.group);
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {

    }

    @Override
    public void update(final float deltaTime) {

    }

}
