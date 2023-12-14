package agarthenoasis.character.status;

public class GameCharacterStatus {
    public final CharacterAttribute attribute;
    public final int maxHP;
    public final int attack;
    public final int defense;
    public final int speed;
    private int currentHP;
    private float skillGage;

    public GameCharacterStatus(final CharacterAttribute attribute) {
        this.attribute = attribute;
        this.maxHP = 1;
        this.attack = 1;
        this.defense = 1;
        this.speed = 0;
        this.currentHP = this.maxHP;
        this.skillGage = 0.0f;
    }

    public void reset() {
        this.currentHP = this.maxHP;
        this.skillGage = 0.0f;
    }

    public void damage(final int damage) {
        this.currentHP -= damage;
        if (this.currentHP < 0) {
            this.currentHP = 0;
        }
    }

    public void heal(final int heal) {
        this.currentHP += heal;
        if (this.currentHP > this.maxHP) {
            this.currentHP = this.maxHP;
        }
    }

    public boolean isAlive() {
        return this.currentHP > 0;
    }

    public float getSkillGage() {
        return this.skillGage;
    }

}
