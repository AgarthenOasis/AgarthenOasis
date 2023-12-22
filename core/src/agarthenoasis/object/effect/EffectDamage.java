package agarthenoasis.object.effect;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.object.GameObjectAffectedByCamera;
import agarthenoasis.scene.ObjectRegistry;

public class EffectDamage extends GameObjectAffectedByCamera {

    /**
     * @param sceneGroup 子オブジェクトを格納するためのグループ。シーンから渡される。
     * @param camera     影響を受けるカメラ
     */
    public EffectDamage(final ObjectRegistry registry, final Group sceneGroup, final Camera camera) {
        super(registry, sceneGroup, camera);
    }

    @Override
    public void update(final float deltaTime) {

    }

    @Override
    public void dispose() {

    }
}
