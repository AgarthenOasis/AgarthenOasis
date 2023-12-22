package agarthenoasis.gamesystem.battle.state;

import agarthenoasis.object.character.GameCharacter;

public class BattleCharacterContext {
    private final GameCharacter owner;
    private BattleCharacterState state;

    public BattleCharacterContext(final GameCharacter owner) {
        this.owner = owner;
        this.state = new BattleStateIdle(this.owner); // 初期状態
    }

    public void setState(final BattleCharacterState state) {
        final BattleCharacterState preState = this.state;
        this.state = state;

        preState.endAction(state);  // 前のステートの終了
        this.state.startAction(this, preState);
    }

    public void nextState() {
        final BattleCharacterState preState = this.state;
        this.state = this.state.nextState();

        preState.endAction(state);  // 前のステートの終了
        this.state.startAction(this, preState);
    }

    public BattleState getState() {
        return this.state.getState();
    }

    public boolean inAction(final float deltaTime) {
        return this.state.inAction(this, deltaTime);
    }

}
