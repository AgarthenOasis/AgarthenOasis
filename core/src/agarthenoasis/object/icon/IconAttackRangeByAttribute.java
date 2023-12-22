package agarthenoasis.object.icon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.UISizeSetting;
import agarthenoasis.gamesystem.battle.status.CharacterAttribute;
import agarthenoasis.object.GameObject;
import agarthenoasis.scene.ObjectRegistry;

public class IconAttackRangeByAttribute extends GameObject {
    private final SpriteBatch spriteBatch;
    private final Texture textureAttribute;

    /**
     * @param sceneGroup 子オブジェクトを格納するためのグループ。シーンから渡される。
     */
    public IconAttackRangeByAttribute(final ObjectRegistry registry, final Group sceneGroup, final CharacterAttribute attribute, final float startX,
                                      final float startY) {
        super(registry, sceneGroup);

        this.spriteBatch = new SpriteBatch();
        //this.textureBack = IconAttackRangeByAttribute.getBackTexture(attribute);
        this.textureAttribute = IconAttackRangeByAttribute.getFrontTexture(attribute);

        final float margin = UISizeSetting.ClippedBattleCharactersBackgroundMargin;
        final float size = (UISizeSetting.ClippedBattleCharacters.y + margin * 2) / 2; // 切り抜き部分の半分のサイズで固定
        final float x = startX + UISizeSetting.ClippedBattleCharacters.x - size;
        final float y = startY + UISizeSetting.ClippedBattleCharacters.y - size + margin;
        this.setPosition(x, y);
        this.setHeight(size);   // x,yは同じ大きさにする
        this.setWidth(size);
    }

    @Override
    public void dispose() {
        this.textureAttribute.dispose();
        this.spriteBatch.dispose();
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        final float posX = this.getX();
        final float posY = this.getY();
        final float sizeX = this.getWidth();
        final float sizeY = this.getHeight();

        //if (true) return;

        //this.spriteBatch.setShader(null);
        this.spriteBatch.begin();
        this.spriteBatch.draw(this.textureAttribute, posX, posY, sizeX, sizeY);
        this.spriteBatch.end();
    }

    private static Texture getBackTexture(final CharacterAttribute attribute) {
        switch (attribute) {
            case SLASH:
                return new Texture(Gdx.files.internal("icon/Attribute2.png"));
            case STAMP:
                return new Texture(Gdx.files.internal("icon/Attribute6.png"));
            case RANGED:
                return new Texture(Gdx.files.internal("icon/Attribute8.png"));
            case THRUST:
                return new Texture(Gdx.files.internal("icon/Attribute4.png"));
        }

        return new Texture(Gdx.files.internal("icon/AttributeAttackRange2.png"));
    }

    private static Texture getFrontTexture(final CharacterAttribute attribute) {
        switch (attribute) {
            case SLASH:
                return new Texture(Gdx.files.internal("icon/AttributeAttackRange2.png"));
            case STAMP:
                return new Texture(Gdx.files.internal("icon/AttributeAttackRange6.png"));
            case RANGED:
                return new Texture(Gdx.files.internal("icon/AttributeAttackRange8.png"));
            case THRUST:
                return new Texture(Gdx.files.internal("icon/AttributeAttackRange4.png"));
        }

        return new Texture(Gdx.files.internal("icon/AttributeAttackRange2.png"));
    }
}
