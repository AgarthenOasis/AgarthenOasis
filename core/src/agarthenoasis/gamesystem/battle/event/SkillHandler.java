package agarthenoasis.gamesystem.battle.event;

import agarthenoasis.character.status.CharacterAttribute;

public interface SkillHandler {

    /**
     * @return 属性
     */
    public CharacterAttribute getCharacterAttribute();

    public boolean canUseSkill();

    public boolean canUseUltimateForce();

    public void onUseSkill();
}
