package agarthenoasis.object.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import agarthenoasis.object.GameObject;

public class ObjectSwitchAutoButton extends GameObject {
    private final Texture textureManual;
    private final Texture textureAuto;
    private final Drawable drawableManual;
    private final Drawable drawableAuto;
    private ImageButton button;

    /**
     * @param sceneGroup 子オブジェクトを格納するためのグループ。シーンから渡される。
     */
    public ObjectSwitchAutoButton(final Group sceneGroup) {
        super(sceneGroup);

        // 画像の読み込み
        this.textureManual = new Texture(Gdx.files.internal("hud/button/AutoButton1.png"));
        this.textureAuto = new Texture(Gdx.files.internal("hud/button/AutoButton2.png"));

        // Drawableの作成
        this.drawableManual = new TextureRegionDrawable(this.textureManual);
        this.drawableAuto = new TextureRegionDrawable(this.textureAuto);

        // ボタンの作成
        final float buttonSizeX = 512;
        final float buttonSizeY = 128;
        this.button = new ImageButton(this.drawableManual);
        this.button.setPosition(0, Gdx.graphics.getHeight() - buttonSizeY); // ボタンの位置設定
        this.button.setSize(buttonSizeX, buttonSizeY);

        // ボタンのクリック時の設定
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                Drawable imageUp = button.getStyle().imageUp;
                if (imageUp.equals(drawableManual)) {
                    button.getStyle().imageUp = drawableAuto;
                } else if (imageUp.equals(drawableAuto)) {
                    button.getStyle().imageUp = drawableManual;
                }
            }
        });

        this.group.addActor(button);
    }

    @Override
    public void dispose() {
        this.textureAuto.dispose();
        this.textureManual.dispose();
    }
}
