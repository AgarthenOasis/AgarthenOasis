package agarthenoasis.gamesystem.battle.sheet;

import agarthenoasis.gamesystem.battle.field.FieldGrid;
import agarthenoasis.gamesystem.battle.status.CharacterAttribute;

public abstract class SheetEffect {

    protected final SheetType sheetType;

    public SheetEffect(final SheetType sheetType) {
        this.sheetType = sheetType;
    }

    float getEffectOfAttack(final CharacterAttribute attribute, final FieldGrid fieldGrid) {
        return 1;
    }

    float getEffectOfDefence(final CharacterAttribute attribute, final FieldGrid fieldGrid) {
        return 1;
    }

    int getEffectOfSpeed(final CharacterAttribute attribute, final FieldGrid fieldGrid) {
        return 0;
    }

    float getEffectOfSkillProbability(final CharacterAttribute attribute, final FieldGrid fieldGrid) {
        return 1;
    }

    float getEffectOfCastleBreak(final CharacterAttribute attribute, final FieldGrid fieldGrid) {
        return 0;
    }
}
