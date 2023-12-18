package agarthenoasis.motion;

import agarthenoasis.motion.state.IdleState;

public class CharacterMotionContext {
    private CharacterMotionState state;

    public CharacterMotionContext() {
        this.state = new IdleState(); // 初期状態
    }

    public void setState(final CharacterMotionState state) {
        final CharacterMotionState preState = this.state;
        this.state = state;

        preState.endAction(this);
        this.state.startAction(this, preState);
    }

    public boolean inAction(final float deltaTime) {
        return this.state.inAction(this, deltaTime);
    }

}
