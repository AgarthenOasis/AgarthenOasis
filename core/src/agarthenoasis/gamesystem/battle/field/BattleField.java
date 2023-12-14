package agarthenoasis.gamesystem.battle.field;

import java.util.Arrays;
import java.util.Optional;

import agarthenoasis.character.status.CharacterAttribute;
import agarthenoasis.object.character.GameCharacter;

public class BattleField {

    private final Optional<GameCharacter>[] ally;   // 味方のマス目
    private final Optional<GameCharacter>[] enemy;  // 敵のマス目

    public BattleField() {
        this.ally = new Optional[9];
        this.enemy = new Optional[9];
        this.verticalIndex = 0;
    }

    public void removeCharacter(final FieldGrid fieldGrid) {
        final int index = fieldGrid.ordinal();
        this.enemy[index] = Optional.empty();  // 空にする
    }

    public void allyAttack(final CharacterAttribute attribute, final FieldGrid attackerPos) {
        final FieldGrid[] attackRange = this.getAttackRangeByAttribute(this.enemy, attribute, attackerPos);

        // 取得した要素を元に配列にアクセスし、キャラクターが存在した場合は攻撃を行う
        for (final FieldGrid fieldGrid : attackRange) {
            final int index = fieldGrid.ordinal();
            if (this.enemy[index].isPresent()) {
                final GameCharacter enemy = this.enemy[index].get();

            }
        }

    }

    /**
     * @param fieldUnderAttack 敵もしくは味方のフィールド
     * @param attribute        攻撃者の属性
     * @param attackerPos      攻撃者のフィールド上の位置
     */
    private static FieldGrid[] getAttackRangeByAttribute(final Optional<GameCharacter>[] fieldUnderAttack, final CharacterAttribute attribute, final FieldGrid attackerPos) {
        switch (attribute) {
            case RANGED:    // 遠
                return new FieldGrid[]{FieldGrid.BehindLeft, FieldGrid.BehindMiddle, FieldGrid.BehindRight};       // 遠属性は後方一列攻撃固定
            case SLASH:     // 斬
                return new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardMiddle, FieldGrid.ForwardRight};    // 遠属性は前方一列攻撃固定
            case STAMP:     // 打
                return BattleField.getAttackRangeByStampAttribute(fieldUnderAttack);
            case THRUST:    // 突
                return BattleField.getAttackRangeByThrustAttribute(fieldUnderAttack, attackerPos);
            default:
                break;
        }
        return new FieldGrid[]{};
    }

    private static FieldGrid[] getAttackRangeByStampAttribute(final Optional<GameCharacter>[] fieldUnderAttack) {
        // 打属性は先に前方の両端2点に敵がいるかチェック
        // 片側だけに存在する場合はその時点でその点を含めた2*2を返すだけで終了
        // 前方左チェック
        final boolean existForwardLeft = fieldUnderAttack[FieldGrid.ForwardLeft.ordinal()].isPresent();
        final boolean existForwardRight = fieldUnderAttack[FieldGrid.ForwardRight.ordinal()].isPresent();
        final boolean existBothSideFlag = existForwardLeft ^ existForwardRight;    // xorで両方存在するor存在しないかのフラグ
        if (existBothSideFlag) {
            if (existForwardLeft) {
                // 左前を起点とした2*2の範囲を返す
                return new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardMiddle, FieldGrid.MiddleLeft, FieldGrid.Center};
            }
            if (existForwardRight) {
                // 右前を起点とした2*2の範囲を返す
                return new FieldGrid[]{FieldGrid.ForwardRight, FieldGrid.ForwardMiddle, FieldGrid.MiddleRight, FieldGrid.Center};
            }
        }

        // 後方判定
        final boolean existBehindLeft = fieldUnderAttack[FieldGrid.BehindLeft.ordinal()].isPresent();
        final boolean existBehindRight = fieldUnderAttack[FieldGrid.BehindRight.ordinal()].isPresent();
        if (existBehindLeft) {
            // 左後を起点とした2*2の範囲を返す
            return new FieldGrid[]{FieldGrid.BehindLeft, FieldGrid.BehindMiddle, FieldGrid.MiddleLeft, FieldGrid.Center};
        }
        if (existBehindRight) {
            // 右後を起点とした2*2の範囲を返す
            return new FieldGrid[]{FieldGrid.BehindRight, FieldGrid.BehindMiddle, FieldGrid.MiddleRight, FieldGrid.Center};
        }

        return new FieldGrid[]{};
    }

    private static FieldGrid[] getAttackRangeByThrustAttribute(final Optional<GameCharacter>[] fieldUnderAttack, final FieldGrid attackerPos) {
        final FieldGrid[] leftRange = new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.MiddleLeft, FieldGrid.BehindLeft};
        final FieldGrid[] middleRange = new FieldGrid[]{FieldGrid.ForwardMiddle, FieldGrid.Center, FieldGrid.BehindMiddle};
        final FieldGrid[] rightRange = new FieldGrid[]{FieldGrid.ForwardRight, FieldGrid.MiddleRight, FieldGrid.BehindRight};
        final int leftCount = BattleField.countFieldCharacter(fieldUnderAttack, leftRange);
        final int middleCount = BattleField.countFieldCharacter(fieldUnderAttack, middleRange);
        final int rightCount = BattleField.countFieldCharacter(fieldUnderAttack, rightRange);

        switch (attackerPos) {
            // 左
            case ForwardLeft:
            case MiddleLeft:
            case BehindLeft:
                // 攻撃者の前一列が最大数であれば目の前の列を攻撃
                if (leftCount == leftRange.length) {
                    return leftRange;
                }
                // 同じ場合は中心を優先する
                if (rightCount > middleCount) {
                    return rightRange;
                } else {
                    return middleRange;
                }
                // 中
            case ForwardMiddle:
            case Center:
            case BehindMiddle:
                // 攻撃者の前一列が最大数であれば目の前の列を攻撃
                if (middleCount == middleRange.length) {
                    return middleRange;
                }
                // 同じ場合は左を優先する
                if (rightCount > leftCount) {
                    return rightRange;
                } else {
                    return leftRange;
                }

                // 右
            case ForwardRight:
            case MiddleRight:
            case BehindRight:
                // 攻撃者の前一列が最大数であれば目の前の列を攻撃
                if (rightCount == rightRange.length) {
                    return rightRange;
                }
                // 同じ場合は中心を優先する
                if (leftCount > middleCount) {
                    return leftRange;
                } else {
                    return middleRange;
                }
            default:
                break;
        }
        return new FieldGrid[]{};
    }

    private static int countFieldCharacter(final Optional<GameCharacter>[] field, final FieldGrid[] fieldGridList) {
        int count = 0;
        for (final FieldGrid fieldGrid : fieldGridList) {
            if (field[fieldGrid.ordinal()].isPresent()) {
                ++count;
            }
        }
        return count;
    }

}
