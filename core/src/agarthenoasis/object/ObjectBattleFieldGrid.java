package agarthenoasis.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.gamesystem.battle.field.FieldGrid;
import agarthenoasis.util.Isometric;

public class ObjectBattleFieldGrid extends GameObject {
    private final Camera camera;
    private ShapeRenderer shapeRenderer;
    private FieldGrid[] ally;       // 味方のマス目表示用
    private FieldGrid[] enemy;      // 敵のマス目表示用

    /**
     * @param sceneGroup 子オブジェクトを格納するためのグループ。シーンから渡される。
     * @param camera
     */
    public ObjectBattleFieldGrid(final Group sceneGroup, final Camera camera) {
        super(sceneGroup);
        this.camera = camera;
        this.shapeRenderer = new ShapeRenderer();
        this.ally = new FieldGrid[]{FieldGrid.ForwardMiddle, FieldGrid.MiddleRight, FieldGrid.MiddleLeft, FieldGrid.BehindRight, FieldGrid.BehindLeft};
        this.enemy = new FieldGrid[]{FieldGrid.ForwardMiddle, FieldGrid.MiddleRight, FieldGrid.MiddleLeft, FieldGrid.BehindRight, FieldGrid.BehindLeft};
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        Gdx.gl.glEnable(GL20.GL_BLEND); // アルファブレンディングを有効にする
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glLineWidth(25);         // 線の太さを設定

        // カメラ設定
        this.shapeRenderer.setProjectionMatrix(this.camera.combined);

        final float cellSize = 150; // セルのサイズ
        final float allyStartX = (Gdx.graphics.getWidth() - 3 * cellSize) * 0.30f;  // グリッドの開始X座標
        final float enemyStartX = (Gdx.graphics.getWidth() - 3 * cellSize) * 0.70f; // グリッドの開始X座標
        final float startY = (Gdx.graphics.getHeight() - 3 * cellSize) / 2; // グリッドの開始Y座標

        final Color allyGridColor = new Color(0.0f, 0.75f, 1.0f, 0.125f);
        final Color allyLineColor = new Color(0.5f, 0.75f, 1.0f, 0.75f);
        final Color enemyGridColor = new Color(1.0f, 0.25f, 0.0f, 0.125f);
        final Color enemyLineColor = new Color(1.0f, 0.75f, 0.75f, 0.75f);

        this.drawGrid(cellSize, allyStartX, startY, allyGridColor, allyLineColor, this.ally, true);
        this.drawGrid(cellSize, enemyStartX, startY, enemyGridColor, enemyLineColor, this.enemy, false);

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void drawGrid(final float cellSize, final float startX, final float startY, final Color gridColor, final Color lineColor, final FieldGrid[] fieldGrids, final boolean isAlly) {
        // グリッドのマス目を描画
        //Gdx.app.log("None", "-------------------------------------------------");
        for (final FieldGrid grid : fieldGrids) {
            // セルの座標を計算
            final int indexX = isAlly ? FieldGrid.squareSize - grid.getIndexX() - 1 : grid.getIndexX();
            final int indexY = isAlly ? FieldGrid.squareSize - grid.getIndexY() - 1 : grid.getIndexY();
            final float baseX = startX + indexX * cellSize;
            final float baseY = startY + indexY * cellSize;

            // イソメトリック座標変換を適用
            final Vector2 bottomLeft = Isometric.convertIsometricX(baseX, baseY);
            final Vector2 bottomRight = Isometric.convertIsometricX(baseX + cellSize, baseY);
            final Vector2 topRight = Isometric.convertIsometricX(baseX + cellSize, baseY + cellSize);
            final Vector2 topLeft = Isometric.convertIsometricX(baseX, baseY + cellSize);

            this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            this.shapeRenderer.setColor(gridColor);

            // 直接平行四辺形のような形を描画する方法がないので三角形2つで描画する
            this.shapeRenderer.triangle(bottomLeft.x, bottomLeft.y, bottomRight.x, bottomRight.y, topRight.x, topRight.y);
            this.shapeRenderer.triangle(bottomLeft.x, bottomLeft.y, topRight.x, topRight.y, topLeft.x, topLeft.y);

            this.shapeRenderer.end();
        }

        // 3*3グリッドの枠線を描画
        final int gridNum = FieldGrid.squareSize;  // ここを弄れは好きなn*nのマスにできる(表示は)
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.shapeRenderer.setColor(lineColor); // 枠線の色
        for (int row = 0; row < gridNum + 1; row++) {
            final Vector2 startVec2 = Isometric.convertIsometricX(startX, startY + row * cellSize);
            final Vector2 endtVec2 = Isometric.convertIsometricX(startX + gridNum * cellSize, startY + row * cellSize);
            this.shapeRenderer.line(startVec2.x, startVec2.y, endtVec2.x, endtVec2.y);
        }
        for (int col = 0; col < gridNum + 1; col++) {
            final Vector2 startVec2 = Isometric.convertIsometricX(startX + col * cellSize, startY);
            final Vector2 endtVec2 = Isometric.convertIsometricX(startX + col * cellSize, startY + gridNum * cellSize);
            this.shapeRenderer.line(startVec2.x, startVec2.y, endtVec2.x, endtVec2.y);
        }
        this.shapeRenderer.end();
    }

    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
    }
}
