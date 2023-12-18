package agarthenoasis.object;

public interface GameObjectLifecycle {
    void update(final float deltaTime);

    void dispose();
}
