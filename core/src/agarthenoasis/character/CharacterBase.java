package agarthenoasis.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import agarthenoasis.object.GameObject;

public class CharacterBase extends GameObject {
    private SpriteBatch batch;
    private Texture img;

    private final int characterID;
    public final String name;


    CharacterBase(final int id, final String name) {
        this.name = name;
        this.characterID = id;

    }

    @Override
    public void draw() {

    }

    @Override
    public void dispose() {

    }
}
