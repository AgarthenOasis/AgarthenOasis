package agarthenoasis.object.button;

import agarthenoasis.scene.SceneListener;
import agarthenoasis.scene.SceneType;

public class BackHomeButton extends Button {
    private static final String backgroundPath = "background/BackHomeButton.bmp";
    protected final SceneListener listener;

    public BackHomeButton(final SceneListener listener) {
        super(BackHomeButton.backgroundPath);
        this.listener = listener;
    }

    public boolean onTouch(final int posX, final int posY) {
        this.listener.changeScene(SceneType.Home, true);
        return true;
    }

}
