package agarthenoasis.object.hud.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import agarthenoasis.UISizeSetting;
import agarthenoasis.object.GameObject;
import agarthenoasis.scene.ObjectRegistry;

public class ButtonSwitchSkill extends GameObject {
    private final Texture texture;
    private final Drawable drawable;
    private ImageButton button;

    /**
     * @param sceneGroup 子オブジェクトを格納するためのグループ。シーンから渡される。
     */
    public ButtonSwitchSkill(final ObjectRegistry registry, final Group sceneGroup) {
        super(registry, sceneGroup);

        // 画像の読み込み
        this.texture = new Texture(Gdx.files.internal("hud/button/SkillChangeButton.png"));

        // Drawableの作成
        this.drawable = new TextureRegionDrawable(this.texture);

        // ボタンの作成
        this.button = new ImageButton(this.drawable);
        this.button.setPosition(0, Gdx.graphics.getHeight() - UISizeSetting.SwitchSkillButton.y - UISizeSetting.SwitchAutoButtonSize.y - 32); // ボタンの位置設定
        this.button.setSize(UISizeSetting.SwitchSkillButton.x, UISizeSetting.SwitchSkillButton.y);

        // ボタンのクリック時の設定
        this.button.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                Drawable imageUp = button.getStyle().imageUp;
                if (imageUp.equals(drawable)) {

                }
            }
        });

        this.addActor(button);
    }

    @Override
    public void dispose() {

    }
}
