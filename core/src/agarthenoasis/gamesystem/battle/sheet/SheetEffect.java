package agarthenoasis.gamesystem.battle.sheet;

import agarthenoasis.gamesystem.battle.field.FieldGrid;
import agarthenoasis.gamesystem.battle.status.CharacterAttribute;

public abstract class SheetEffect {

    protected final FieldGrid fieldGrid;
    protected final CharacterAttribute attribute;

    public SheetEffect(final FieldGrid fieldGrid, final CharacterAttribute attribute) {
        this.fieldGrid = fieldGrid;
        this.attribute = attribute;
    }

    float getEffectOfAttack() {
        return 1;
    }

    float getEffectOfDefence() {
        return 1;
    }

    int getEffectOfSpeed() {
        return 0;
    }

    float getEffectOfSkillProbability() {
        return 1;
    }

    float getEffectOfCastleBreak() {
        return 0;
    }
}
