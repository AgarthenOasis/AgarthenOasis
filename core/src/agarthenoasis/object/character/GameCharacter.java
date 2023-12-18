package agarthenoasis.object.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.character.status.GameCharacterStatus;
import agarthenoasis.motion.CharacterMotionContext;
import agarthenoasis.object.GameObject;

public class GameCharacter extends GameObject {
    private final SDAnimation sdAnimation;
    private final GameCharacterStatus status;
    private final CharacterMotionContext motionContext;
    private final int id;

    public GameCharacter(final Group group, final String sdPath, final String lanePath, final int characterID) {
        super(group);
        this.sdAnimation = new SDAnimation(group, sdPath, 1);
        this.status = new GameCharacterStatus(characterID);
        this.motionContext = new CharacterMotionContext();
        this.id = characterID;
    }

    @Override
    public void update(final float deltaTime) {
        // アニメーション更新

        this.sdAnimation.update(deltaTime);
        this.motionContext.inAction(deltaTime);
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        this.sdAnimation.draw(batch, parentAlpha);
    }

    @Override
    public void dispose() {

    }

}
