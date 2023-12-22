package agarthenoasis.object.hud.display;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.UISizeSetting;
import agarthenoasis.object.GameObject;
import agarthenoasis.scene.ObjectRegistry;

public class DisplayCharacterBackground extends GameObject {
    private final ShapeRenderer shapeRenderer;
    private final Color color;

    /**
     * @param sceneGroup 子オブジェクトを格納するためのグループ。シーンから渡される。
     */
    public DisplayCharacterBackground(final ObjectRegistry registry, final Group sceneGroup, final float startX, final float startY, final Color color) {
        super(registry, sceneGroup);
        this.shapeRenderer = new ShapeRenderer();
        this.color = color;
        this.setPosition(startX, startY);
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.setColor(this.color);

        final float margin = UISizeSetting.ClippedBattleCharactersBackgroundMargin;
        final float scaleX = UISizeSetting.ClippedBattleCharacters.x;
        final float scaleY = UISizeSetting.ClippedBattleCharacters.y;
        final float shift = UISizeSetting.ParallelogramVertexShiftForBackground;
        final float posX = this.getX();
        final float posY = this.getY();
        final Vector2 bottomLeft = new Vector2(posX + shift - margin, posY - margin);
        final Vector2 bottomRight = new Vector2(posX + scaleX + shift + margin, posY - margin);
        final Vector2 topRight = new Vector2(posX + scaleX - shift + margin, posY + scaleY + margin);
        final Vector2 topLeft = new Vector2(posX - shift - margin, posY + scaleY + margin);

        // 直接平行四辺形のような形を描画する方法がないので三角形2つで描画する
        this.shapeRenderer.triangle(bottomLeft.x, bottomLeft.y, bottomRight.x, bottomRight.y, topRight.x, topRight.y);
        this.shapeRenderer.triangle(bottomLeft.x, bottomLeft.y, topRight.x, topRight.y, topLeft.x, topLeft.y);

        this.shapeRenderer.end();
    }

    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
    }

    private Mesh createMesh(final float startX, final float startY, final float scaleX, final float scaleY) {
        final float[] vertices = new float[]{                         // 五角形(左上が斜めになっている四角形)頂点生成
                startX, startY, 0.0f, 1.0f,                           // 左下
                startX + scaleX, startY, 1.0f, 1.0f,                  // 右下
                startX + scaleX, startY + scaleY, 1.0f, 0.0f,         // 右上
                startX + scaleX / 2, startY + scaleY, 0.5f, 0.0f,     // 中上
                startX, startY + scaleY / 2, 0.0f, 0.5f               // 左中
        };

        final short[] indices = new short[]{  // 三角形のインデックスを設定 (五角形を構成する三角形)
                0, 1, 4,   // 左下の三角形
                1, 2, 4,   // 右下の三角形
                2, 3, 4    // 上部の三角形
        };

        // メッシュを作成
        final Mesh mesh = new Mesh(true, 5, indices.length, new VertexAttribute(VertexAttributes.Usage.Position, 2, ShaderProgram.POSITION_ATTRIBUTE),
                new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE));

        mesh.setVertices(vertices);
        mesh.setIndices(indices);

        return mesh;
    }
}
