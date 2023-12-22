package agarthenoasis.motion;

import agarthenoasis.motion.state.MotionState;

public interface CharacterMotionState {

    /**
     *
     */
    void startAction(final CharacterMotionContext context, final CharacterMotionState preState);

    /**
     *
     */
    boolean inAction(final CharacterMotionContext context, final float deltaTime);

    /**
     *
     */
    void endAction(final CharacterMotionContext context);

    MotionState getState();

}
