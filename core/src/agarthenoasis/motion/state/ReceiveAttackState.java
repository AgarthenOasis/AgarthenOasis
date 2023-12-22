package agarthenoasis.motion.state;

import agarthenoasis.motion.CharacterMotionContext;
import agarthenoasis.motion.CharacterMotionState;

public class ReceiveAttackState implements CharacterMotionState {
    @Override
    public void startAction(final CharacterMotionContext context, final CharacterMotionState preState) {

    }

    @Override
    public boolean inAction(final CharacterMotionContext context, final float deltaTime) {
        return false;
    }

    @Override
    public void endAction(final CharacterMotionContext context) {

    }

    @Override
    public MotionState getState() {
        return MotionState.ReceiveAttack;
    }
}
