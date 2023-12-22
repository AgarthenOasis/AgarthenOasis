package agarthenoasis.object.hud.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import agarthenoasis.object.GameObject;
import agarthenoasis.scene.ObjectRegistry;
import agarthenoasis.scene.SceneListener;

public class ButtonBackHome extends GameObject {
    private static final String backgroundPath = "background/BackHomeButton.bmp";
    private final Texture texture;

    public ButtonBackHome(final ObjectRegistry registry, final Group sceneGroup, final SceneListener listener) {
        super(registry, sceneGroup);
        final int scaleX = 256;
        final int scaleY = 256;
        final int posX = Gdx.graphics.getWidth() - scaleX;
        final int posY = Gdx.graphics.getHeight() - scaleY;

        this.texture = new Texture(this.backgroundPath);

        final Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        final ImageButton imageButton = new ImageButton(drawable);

        imageButton.setPosition(posX, posY);
        imageButton.setScale(scaleX, scaleY);
        imageButton.addListener(new ListenerClickBackHomeScene(listener)); // シーン切り替え用のリスナー設定

        this.addActor(imageButton);
    }

    @Override
    public void update(final float deltaTime) {

    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }

}
