package agarthenoasis.util;

import com.badlogic.gdx.math.Vector2;

public class Isometric {
    public static Vector2 convertIsometricX(final float x, final float y) {
        // 向きを反対にする場合
        //return new Vector2((x - y), (x + y) / 2);
        return new Vector2((y - x), (x + y) / 2);
    }

}
