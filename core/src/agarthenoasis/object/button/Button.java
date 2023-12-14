package agarthenoasis.object.button;

import agarthenoasis.object.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button extends GameObject {
    private final Texture image;
    protected final SpriteBatch batch;

    public Button(final String filePath) {
        this.batch = new SpriteBatch();
        this.image = new Texture(filePath);
    }

    @Override
    public void draw() {
        this.batch.begin();
        batch.draw(this.image, this.posX, this.posY, this.scaleX, this.scaleY);
        this.batch.end();
    }

    @Override
    public void dispose() {
        this.image.dispose();
        this.batch.dispose();
    }
}
