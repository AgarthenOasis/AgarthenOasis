package agarthenoasis.object.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.object.GameObject;

public class SDAnimation extends GameObject {
    private static final float next = 0.16f;

    private final SpriteBatch spriteBatch;  // スプライトバッチ
    private final TextureAtlas sdImage;     // SDイメージ
    private int animationCounter;
    private final int maxAnimationCounter;

    public SDAnimation(final Group group, final String sdPath, final int maxAnimationCount) {
        super(group);
        this.spriteBatch = new SpriteBatch();
        this.sdImage = new TextureAtlas(sdPath);

        this.animationCounter = 0;
        this.maxAnimationCounter = maxAnimationCount;

    }

    @Override
    public void update(final float deltaTime) {
        // 次のアニメーションまで時間が経過していなければ何もしない
        if (SDAnimation.next < deltaTime) {
            return;
        }

        // 更新
        ++this.animationCounter;
        if (this.animationCounter > this.maxAnimationCounter) {
            this.animationCounter = 0;
        }
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        final TextureAtlas.AtlasRegion region1 = this.sdImage.findRegion("001");

        this.spriteBatch.begin();
        this.spriteBatch.draw(region1, this.getX(), this.getY());
        this.spriteBatch.end();
    }

    @Override
    public void dispose() {
        this.sdImage.dispose();
        this.spriteBatch.dispose();
    }

}
