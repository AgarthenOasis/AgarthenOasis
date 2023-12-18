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

public class ObjectSpeedAdjustmentButton extends GameObject {

    private final Texture texture1xSpeed;
    private final Texture texture2xSpeed;
    private final Texture texture4xSpeed;
    private final Drawable drawable1xSpeed;
    private final Drawable drawable2xSpeed;
    private final Drawable drawable4xSpeed;

    private ImageButton button;

    /**
     * @param sceneGroup 子オブジェクトを格納するためのグループ。シーンから渡される。
     */
    public ObjectSpeedAdjustmentButton(final Group sceneGroup) {
        super(sceneGroup);

        // 画像の読み込み
        this.texture1xSpeed = new Texture(Gdx.files.internal("hud/button/SpeedButton1.png"));
        this.texture2xSpeed = new Texture(Gdx.files.internal("hud/button/SpeedButton2.png"));
        this.texture4xSpeed = new Texture(Gdx.files.internal("hud/button/SpeedButton3.png"));

        // Drawableの作成
        this.drawable1xSpeed = new TextureRegionDrawable(this.texture1xSpeed);
        this.drawable2xSpeed = new TextureRegionDrawable(this.texture2xSpeed);
        this.drawable4xSpeed = new TextureRegionDrawable(this.texture4xSpeed);

        // ボタンの作成
        final float buttonSizeX = 256;
        final float buttonSizeY = 128;
        this.button = new ImageButton(this.drawable1xSpeed);
        this.button.setPosition(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight() - buttonSizeY); // ボタンの位置設定
        this.button.setSize(buttonSizeX, buttonSizeY);

        // ボタンのクリック時の設定
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                Drawable imageUp = button.getStyle().imageUp;
                if (imageUp.equals(drawable1xSpeed)) {
                    button.getStyle().imageUp = drawable2xSpeed;
                } else if (imageUp.equals(drawable2xSpeed)) {
                    button.getStyle().imageUp = drawable4xSpeed;
                } else if (imageUp.equals(drawable4xSpeed)) {
                    button.getStyle().imageUp = drawable1xSpeed;
                }
            }
        });

        this.group.addActor(button);
    }

    @Override
    public void dispose() {
        this.texture1xSpeed.dispose();
        this.texture2xSpeed.dispose();
        this.texture4xSpeed.dispose();
    }
}
