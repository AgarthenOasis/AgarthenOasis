package agarthenoasis.character;

import agarthenoasis.character.status.CharacterAttribute;
import agarthenoasis.character.status.GameCharacterStatus;
import agarthenoasis.gamesystem.battle.event.ActiveLaneHandler;
import agarthenoasis.gamesystem.battle.event.AttackHandler;
import agarthenoasis.gamesystem.battle.event.SkillHandler;
import agarthenoasis.gamesystem.battle.event.StatusEffectHandler;

public class BaseBattleAction implements ActiveLaneHandler, AttackHandler, SkillHandler, StatusEffectHandler {
    public final GameCharacterStatus status;
    private static final float maxSkillGage = 1.0f;

    public BaseBattleAction(final int characterID) {
        this.status = new GameCharacterStatus(characterID);
    }

    public GameCharacterStatus getStatus() {
        return this.status;
    }

    @Override
    public void onActiveTurn() {

    }

    @Override
    public boolean isAttacking() {
        return false;
    }

    @Override
    public void onAttacked() {

    }

    @Override
    public boolean receiveAttack() {
        return true;
    }

    @Override
    public boolean canCounterattack() {
        return false;
    }

    @Override
    public void onCounterattack() {

    }

    @Override
    public CharacterAttribute getCharacterAttribute() {
        return this.status.attribute;
    }

    @Override
    public boolean canUseSkill() {
        return this.status.getSkillGauge() > this.maxSkillGage;
    }

    @Override
    public boolean canUseUltimateForce() {
        return false;
    }

    @Override
    public void onUseSkill() {

    }

    @Override
    public void onKnockedDown() {

    }
}
