package agarthenoasis.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.scene.ObjectRegistry;

public abstract class GameObject extends Actor implements GameObjectLifecycle {

    final private Group group;
    final private ObjectRegistry registry;

    /**
     * @param registry   オブジェクト内に生成したリソースを解放するためのレジストリ
     * @param sceneGroup 子オブジェクトを格納するためのグループ。シーンから渡される。
     */
    public GameObject(final ObjectRegistry registry, final Group sceneGroup) {
        this.group = new Group();
        sceneGroup.addActor(this.group);
        this.registry = registry;
        this.registry.registerObject(this); // 自身を解放の対象にする
    }

    protected void addObject(final GameObject object) {
        this.registry.registerObject(object);
        this.group.addActor(object);
    }

    protected void registerObject(final GameObjectLifecycle object) {
        this.registry.registerObject(object);
    }

    protected void addActor(final Actor actor) {
        this.group.addActor(actor);
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {

    }

    @Override
    public void update(final float deltaTime) {

    }

}
