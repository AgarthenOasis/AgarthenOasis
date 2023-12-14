package agarthenoasis.object.character;

import com.badlogic.gdx.graphics.Texture;

import agarthenoasis.character.status.GameCharacterStatus;
import agarthenoasis.object.GameObject;
import jdk.internal.org.jline.utils.Status;

public class GameCharacter extends GameObject {

    private final Texture sdImage; // SDイメージ
    private final Texture laneImage; // レーンイメージ
    private final GameCharacterStatus status;   // ステータス情報

    public GameCharacter(final String sdPath, final String lanePath, final GameCharacterStatus status) {
        this.sdImage = new Texture(sdPath);
        this.laneImage = new Texture(lanePath);
        this.status = status;
    }

    @Override
    public void draw() {

    }

    @Override
    public void dispose() {

    }

}
