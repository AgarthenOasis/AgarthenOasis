package agarthenoasis.object.hud.display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;

import agarthenoasis.UISizeSetting;
import agarthenoasis.gamesystem.battle.status.CharacterAttribute;
import agarthenoasis.object.GameObject;
import agarthenoasis.object.icon.IconAttackRangeByAttribute;
import agarthenoasis.scene.ObjectRegistry;

public class DisplayCharacterList extends GameObject {

    /**
     * @param sceneGroup 子オブジェクトを格納するためのグループ。シーンから渡される。
     */
    public DisplayCharacterList(final ObjectRegistry registry, final Group sceneGroup) {
        super(registry, sceneGroup);

        final float interval = UISizeSetting.ClippedBattleCharactersInterval;
        final float bottomMargin = UISizeSetting.ClippedBattleCharactersBottomMargin;
        final float settingX = UISizeSetting.ClippedBattleCharacters.x;
        final float settingY = UISizeSetting.ClippedBattleCharacters.y;
        final Color color = new Color(0.0f, 0.25f, 0.5f, 1.0f);
        final Color color2 = new Color(0.45f, 0.0f, 0.0f, 1.0f);
        for (int i = 0; i < 5; ++i) {
            final float x = Gdx.graphics.getWidth() - settingX;
            final float y = settingY * i + interval * i + bottomMargin;
            final DisplayCharacterBackground characterBackground = new DisplayCharacterBackground(registry, sceneGroup, x, y, color);
            final DisplayCharacterFace characterFace = new DisplayCharacterFace(registry, sceneGroup, 0, x, y);
            final IconAttackRangeByAttribute icon = new IconAttackRangeByAttribute(registry, sceneGroup, CharacterAttribute.STAMP, x, y);

            //final DisplayCharacterBackground characterBackground2 = new DisplayCharacterBackground(registry, sceneGroup, 0, y, color2);

            sceneGroup.addActor(characterBackground);   // 背景
            sceneGroup.addActor(characterFace);         // キャラのクリップ
            sceneGroup.addActor(icon);                  // 属性毎の攻撃範囲を表すアイコン

            //sceneGroup.addActor(characterBackground2);   // 背景
        }
    }

    @Override
    public void dispose() {

    }
}
