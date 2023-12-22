package agarthenoasis.object;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.scene.ObjectRegistry;

public abstract class GameObjectAffectedByCamera extends GameObject {
    protected final Camera camera;

    /**
     * @param sceneGroup 子オブジェクトを格納するためのグループ。シーンから渡される。
     * @param camera     影響を受けるカメラ
     */
    public GameObjectAffectedByCamera(final ObjectRegistry registry, final Group sceneGroup, final Camera camera) {
        super(registry, sceneGroup);
        this.camera = camera;
    }

}