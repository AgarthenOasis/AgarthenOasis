package agarthenoasis.object;

import com.badlogic.gdx.Gdx;

public abstract class GameObject {
    protected int posX;
    protected int posY;
    protected int scaleX;
    protected int scaleY;

    public GameObject() {
        this.posX = 0;
        this.posY = 0;
        this.scaleX = 100;
        this.scaleY = 100;
    }

    public void update(final float elapsedTime) {

    }

    public abstract void draw();

    public abstract void dispose();

    public void setPosition(final int posX, final int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void addPosition(final int posX, final int posY) {
        this.posX += posX;
        this.posY += posY;
    }

    public void setScale(final int scaleX, final int scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public void addScale(final int scaleX, final int scaleY) {
        this.scaleX += scaleX;
        this.scaleY += scaleY;
    }

    /**
     * このオブジェクトに対してタッチされたかをBooleanで返す
     *
     * @param posX タッチしたX座標
     * @param posY タッチしたY座標
     * @return オブジェクトに触れた場合はtrueを返す。それ以外はfalseを返す。
     */
    public boolean canTouch(final int posX, final int posY) {
        final int minPosX = this.posX;
        final int minPosY = this.posY;
        final int maxPosX = this.posX + this.scaleX;
        final int maxPosY = this.posY + this.scaleY;

        // Gdx.app.log("Touch", "posX: " + posX + " posY: " + posY + " minX: " + minPosX + " minY: " + minPosY + " maxX: " + maxPosX + " maxY: " + maxPosY);
        // 範囲内か判定
        if (minPosX > posX) {
            return false;
        }
        if (minPosY > posY) {
            return false;
        }
        if (maxPosX < posX) {
            return false;
        }
        if (maxPosY < posY) {
            return false;
        }

        return true;
    }

    public boolean onTouch(final int posX, final int posY) {
        return false;
    }

    public boolean onDrag(final int posX, final int posY) {
        return false;
    }

}
