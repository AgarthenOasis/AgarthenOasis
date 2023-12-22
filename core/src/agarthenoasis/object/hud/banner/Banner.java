package agarthenoasis.object.hud.banner;

import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.object.GameObject;
import agarthenoasis.scene.ObjectRegistry;

public class Banner extends GameObject {

    /**
     * @param group 子オブジェクトを格納するためのグループ。シーンから渡される。
     */
    public Banner(final ObjectRegistry registry, final Group group) {
        super(registry, group);
    }

    public void draw() {

    }

    @Override
    public void dispose() {

    }
}
