package agarthenoasis.gamesystem.battle.field;

// このEnumの順序を変更するとシート毎のグリッドの配置が変わるので順序に関係なく描画するロジックにしたいところ(時間あれば...)
public enum FieldGrid {
    ForwardLeft, MiddleLeft, BehindLeft, ForwardMiddle, Center, BehindMiddle, ForwardRight, MiddleRight, BehindRight;
    public static final int squareSize = 3;

    public int getIndexX() {
        return this.ordinal() % squareSize;
    }

    public int getIndexY() {
        return this.ordinal() / squareSize;
    }

}
