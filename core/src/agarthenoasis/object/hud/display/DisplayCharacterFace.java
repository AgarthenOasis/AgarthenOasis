package agarthenoasis.object.hud.display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.UISizeSetting;
import agarthenoasis.object.GameObject;
import agarthenoasis.scene.ObjectRegistry;

public class DisplayCharacterFace extends GameObject {
    private final Mesh mesh;
    private final Texture texture;
    private final ShaderProgram shader;
    private final Matrix4 projectionMatrix;

    /**
     * @param sceneGroup 子オブジェクトを格納するためのグループ。シーンから渡される。
     */
    public DisplayCharacterFace(final ObjectRegistry registry, final Group sceneGroup, final int id, final float startX, final float startY) {
        super(registry, sceneGroup);
        this.texture = new Texture("character/IMG_3601.jpg"); //仮

        this.shader = new ShaderProgram(Gdx.files.internal("shader/vertex/CharacterFace.glsl"), Gdx.files.internal("shader" + "/fragment/CharacterFace.glsl"));
        if (!this.shader.isCompiled()) {
            Gdx.app.error("Shader Error", this.shader.getLog());
            Gdx.app.exit();
        }

        final float scaleX = UISizeSetting.ClippedBattleCharacters.x;
        final float scaleY = UISizeSetting.ClippedBattleCharacters.y;
        this.mesh = this.createMesh(startX, startY, scaleX, scaleY, 0.15f, 0.5f, 3.0f);

        this.projectionMatrix = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        this.texture.bind();
        this.shader.bind();
        this.shader.setUniformMatrix("u_projTrans", this.projectionMatrix);
        this.mesh.render(this.shader, GL20.GL_TRIANGLE_FAN);
    }

    @Override
    public void dispose() {
        this.mesh.dispose();
        this.texture.dispose();
        this.shader.dispose();
    }

    /**
     * @param startX                 左下の座標
     * @param startY                 左下の座標
     * @param scaleX                 横の大きさ
     * @param scaleY                 縦の大きさ
     * @param startU                 起点となるテクスチャのX座標
     * @param startV                 起点となるテクスチャのY座標
     * @param adjustmentTextureScale 元のイメージサイズに対する拡大率
     */
    private Mesh createMesh(final float startX, final float startY, final float scaleX, final float scaleY, final float startU, final float startV,
                            final float adjustmentTextureScale) {
        final float divX = 5.0f;    // 値が大きいほど左に寄る
        final float divY = 2.0f;    // 値が大きいほど下に寄る

        // 各頂点の座標を決める
        final float bottomLeftOffset = UISizeSetting.ParallelogramVertexShiftForBackground;
        final float lengthMiddleX = scaleX / divX;
        final float lengthMiddleY = scaleY / divY;
        final Vector2 bottomLeft = new Vector2(startX + bottomLeftOffset, startY);
        final Vector2 bottomRight = new Vector2(startX + scaleX, startY);
        final Vector2 topRight = new Vector2(startX + scaleX, startY + scaleY);
        final Vector2 topLeft = new Vector2(startX + lengthMiddleX, startY + scaleY);
        final Vector2 middleLeft = new Vector2(startX, startY + lengthMiddleY);

        // ポリゴンのサイズに関わらす指定の大きさの画像を切り抜いた表示にするため各頂点に対するUV座標を求める(テクスチャ比率を保持)
        // 各頂点のUV座標は画面からの相対位置から取得する
        // C++のoperatorが欲しかった(あんまり使わないけど)
        final Vector2 bottomLeftUV = this.getUV(bottomLeft, startX, startY, startU, startV, adjustmentTextureScale);
        final Vector2 bottomRightUV = this.getUV(bottomRight, startX, startY, startU, startV, adjustmentTextureScale);
        final Vector2 topRightUV = this.getUV(topRight, startX, startY, startU, startV, adjustmentTextureScale);
        final Vector2 topLeftUV = this.getUV(topLeft, startX, startY, startU, startV, adjustmentTextureScale);
        final Vector2 middleLeftUV = this.getUV(middleLeft, startX, startY, startU, startV, adjustmentTextureScale);

        final float[] vertices = new float[]{               // 五角形(左上が斜めになっている四角形)頂点生成
                bottomLeft.x, bottomLeft.y, bottomLeftUV.x, bottomLeftUV.y,         // 左下
                bottomRight.x, bottomRight.y, bottomRightUV.x, bottomRightUV.y,     // 右下
                topRight.x, topRight.y, topRightUV.x, topRightUV.y,                 // 右上
                topLeft.x, topLeft.y, topLeftUV.x, topLeftUV.y,                     // 中上
                middleLeft.x, middleLeft.y, middleLeftUV.x, middleLeftUV.y          // 左中
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

    private static Vector2 getUV(final Vector2 vertexPos, final float startX, final float startY, final float startU, final float startV,
                                 final float adjustmentTextureScale) {
        // 頂点座標の平行移動成分が含まれるので引いて頂点の形だけを抽出
        final float screenWidth = Gdx.graphics.getWidth();
        final float screenHeight = Gdx.graphics.getHeight();
        final float posX = (vertexPos.x - startX) / screenWidth * adjustmentTextureScale;
        final float posY = (vertexPos.y - startY) / screenHeight * adjustmentTextureScale;

        // UV座標用の平行移動成分を足す
        final float u = posX + startU;
        final float v = -posY + startV; // Vだけは反転させる

        return new Vector2(u, v);
    }
}
