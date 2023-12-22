package agarthenoasis.gamesystem.battle.field;

import java.util.LinkedList;
import java.util.Optional;

import agarthenoasis.gamesystem.battle.util.AttackRangeByAttribute;
import agarthenoasis.gamesystem.battle.status.CharacterAttribute;
import agarthenoasis.object.character.GameCharacter;

public class BattleField {
    private final Optional<GameCharacter>[] ally;   // 味方のマス目
    private final Optional<GameCharacter>[] enemy;  // 敵のマス目

    public BattleField() {
        final int length = FieldGrid.values().length;
        this.ally = new Optional[length];
        this.enemy = new Optional[length];

        for (int index = 0; index < length; ++index) {
            this.ally[index] = Optional.empty();
            this.enemy[index] = Optional.empty();
        }
    }

    public void update(final float deltaTime) {
        for (final Optional<GameCharacter> character : this.ally) {
            if (character.isPresent()) {
                character.get().update(deltaTime);
            }
        }
        for (final Optional<GameCharacter> character : this.enemy) {
            if (character.isPresent()) {
                character.get().update(deltaTime);
            }
        }
    }

    public void setAllyCharacter(final FieldGrid fieldGrid, final GameCharacter actionCharacter) {
        final int index = fieldGrid.ordinal();
        this.ally[index] = Optional.of(actionCharacter);
    }

    public void removeAllyCharacter(final FieldGrid fieldGrid) {
        final int index = fieldGrid.ordinal();
        this.ally[index] = Optional.empty();
    }

    public void setEnemyCharacter(final FieldGrid fieldGrid, final GameCharacter actionCharacter) {
        final int index = fieldGrid.ordinal();
        this.enemy[index] = Optional.of(actionCharacter);
    }

    public void removeEnemyCharacter(final FieldGrid fieldGrid) {
        final int index = fieldGrid.ordinal();
        this.enemy[index] = Optional.empty();
    }

    public final Optional<GameCharacter> getAllyCharacter(final FieldGrid fieldGrid) {
        final int index = fieldGrid.ordinal();
        return this.ally[index];
    }

    public final Optional<GameCharacter> getEnemyCharacter(final FieldGrid fieldGrid) {
        final int index = fieldGrid.ordinal();
        return this.enemy[index];
    }

    public final LinkedList<GameCharacter> getAllyList() {
        final LinkedList<GameCharacter> list = new LinkedList<>();
        for (final Optional<GameCharacter> character : this.ally) {
            if (character.isPresent()) {
                list.add(character.get());
            }
        }
        return list;
    }

    public final LinkedList<GameCharacter> getEnemyList() {
        final LinkedList<GameCharacter> list = new LinkedList<>();
        for (final Optional<GameCharacter> character : this.enemy) {
            if (character.isPresent()) {
                list.add(character.get());
            }
        }
        return list;
    }

    public final FieldGrid[] getAllyAttackRange(final CharacterAttribute attribute, final FieldGrid attackerPos) {
        return AttackRangeByAttribute.getAttackRangeByAttribute(this.enemy, attribute, attackerPos);
    }

    public final FieldGrid[] getEnemyAttackRange(final CharacterAttribute attribute, final FieldGrid attackerPos) {
        return AttackRangeByAttribute.getAttackRangeByAttribute(this.ally, attribute, attackerPos);
    }

    private static Optional<GameCharacter> getGameCharacter(final Optional<GameCharacter>[] field, final FieldGrid attackerPos) {
        return field[attackerPos.ordinal()];
    }

}
