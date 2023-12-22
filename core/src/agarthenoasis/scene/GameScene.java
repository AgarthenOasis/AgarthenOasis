package agarthenoasis.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.LinkedList;

import agarthenoasis.object.GameObjectLifecycle;

public abstract class GameScene implements Screen, ObjectRegistry {
    protected final SceneListener listener;
    protected final OrthographicCamera camera;  // 平行投影用のカメラ
    private final Stage stage;                  // カメラに追従しない(ワールド座標に置く)場合はこちらに追加する
    private final Stage hudStage;               // カメラに追従するものはこちらに追加する

    private final LinkedList<GameObjectLifecycle> objectList;

    public GameScene(final SceneListener listener) {
        this.listener = listener;
        this.stage = new Stage();
        this.hudStage = new Stage(new ScreenViewport());
        this.camera = new OrthographicCamera();
        this.objectList = new LinkedList<>();

        // カメラの設定
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.update();
        Gdx.input.setInputProcessor(this.stage);
        Gdx.input.setInputProcessor(this.hudStage);
    }

    public void addActor(final Actor actor) {
        this.stage.addActor(actor);
    }

    public void addHUDActor(final Actor actor) {
        this.hudStage.addActor(actor);
    }

    /**
     * 初期化が終わるまではシーンの切り替えは行われない
     */
    public abstract void initialize();

    public abstract void start();

    public void update(final float deltaTime) {
        this.camera.update();
        this.stage.act(deltaTime);
        this.hudStage.act(deltaTime);
    }

    public void draw() {
        this.stage.draw();
        this.hudStage.draw();
    }

    public void registerObject(final GameObjectLifecycle object) {
        this.objectList.add(object);
    }

    @Override
    public void dispose() {
        for (final GameObjectLifecycle object : this.objectList) {
            object.dispose();
        }
        this.stage.dispose();
        this.hudStage.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(final float delta) {

    }

    @Override
    public void resize(final int width, final int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

}
