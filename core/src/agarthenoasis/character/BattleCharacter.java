package agarthenoasis.character;

import agarthenoasis.character.status.CharacterAttribute;
import agarthenoasis.character.status.GameCharacterStatus;

public class BattleCharacter {
    private final GameCharacterStatus status;
    private static final float maxSkillGage = 1.0f;

    public BattleCharacter(GameCharacterStatus status, final CharacterAttribute attribute) {
        this.status = new GameCharacterStatus(attribute);
    }
    // battle

    public void battleInit() {
        this.status.reset();
    }

    /**
     * @return 属性
     */
    public final CharacterAttribute getCharacterAttribute() {
        return this.status.attribute;
    }

    public boolean canUseSkill() {
        return this.status.getSkillGage() > this.maxSkillGage;
    }

    public boolean canUseUltimateForce() {
        return false;
    }

    public void onUseSkill() {

    }
}
