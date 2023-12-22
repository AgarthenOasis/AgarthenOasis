package agarthenoasis.object;

import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.scene.ObjectRegistry;

public class ObjectActiveLane extends GameObject {
    /**
     * @param sceneGroup 子オブジェクトを格納するためのグループ。シーンから渡される。
     */
    public ObjectActiveLane(final ObjectRegistry registry, final Group sceneGroup) {
        super(registry, sceneGroup);

    }

    @Override
    public void dispose() {

    }
}
