package agarthenoasis.gamesystem.battle.util;

import java.util.Optional;

import agarthenoasis.gamesystem.battle.field.FieldGrid;
import agarthenoasis.gamesystem.battle.status.CharacterAttribute;
import agarthenoasis.object.character.GameCharacter;

public class AttackRangeByAttribute {

    /**
     * 属性に応じた攻撃範囲を取得する
     *
     * @param fieldUnderAttack 敵もしくは味方のフィールド
     * @param attribute        攻撃者の属性
     * @param attackerPos      攻撃者のフィールド上の位置
     */
    public static FieldGrid[] getAttackRangeByAttribute(final Optional<GameCharacter>[] fieldUnderAttack, final CharacterAttribute attribute,
                                                        final FieldGrid attackerPos) {
        switch (attribute) {
            case RANGED:    // 遠
                return AttackRangeByAttribute.getRangeAttribute(fieldUnderAttack);
            case SLASH:     // 斬
                return AttackRangeByAttribute.getSlashAttribute(fieldUnderAttack);
            case STAMP:     // 打
                return AttackRangeByAttribute.getStampAttribute(fieldUnderAttack);
            case THRUST:    // 突
                return AttackRangeByAttribute.getThrustAttribute(fieldUnderAttack, attackerPos);
            default:
                break;
        }
        return new FieldGrid[]{};
    }

    /**
     * 遠属性の攻撃範囲を決定する
     */
    private static FieldGrid[] getRangeAttribute(final Optional<GameCharacter>[] fieldUnderAttack) {
        // 遠属性は後方一列攻撃固定
        // 後ろから順に見ていき一体でも存在すればその範囲を攻撃
        // 後方横一列
        final FieldGrid[] behindRange = new FieldGrid[]{FieldGrid.BehindLeft, FieldGrid.BehindMiddle, FieldGrid.BehindRight};
        final int behindCount = AttackRangeByAttribute.countFieldCharacter(fieldUnderAttack, behindRange);
        if (behindCount > 0) {
            return behindRange;
        }

        // 真ん中横一列
        final FieldGrid[] middleRange = new FieldGrid[]{FieldGrid.MiddleLeft, FieldGrid.Center, FieldGrid.MiddleRight};
        final int middleCount = AttackRangeByAttribute.countFieldCharacter(fieldUnderAttack, middleRange);
        if (middleCount > 0) {
            return middleRange;
        }

        // 前方横一列
        final FieldGrid[] forwardRange = new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardMiddle, FieldGrid.ForwardRight};
        return forwardRange;
    }

    /**
     * 斬属性の攻撃範囲を決定する
     */
    private static FieldGrid[] getSlashAttribute(final Optional<GameCharacter>[] fieldUnderAttack) {
        // 斬属性は前方一列攻撃固定
        // 前から順に見ていき一体でも存在すればその範囲を攻撃
        // 前方横一列
        final FieldGrid[] forwardRange = new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardMiddle, FieldGrid.ForwardRight};
        final int forwardCount = AttackRangeByAttribute.countFieldCharacter(fieldUnderAttack, forwardRange);
        if (forwardCount > 0) {
            return forwardRange;
        }

        // 真ん中横一列
        final FieldGrid[] middleRange = new FieldGrid[]{FieldGrid.MiddleLeft, FieldGrid.Center, FieldGrid.MiddleRight};
        final int middleCount = AttackRangeByAttribute.countFieldCharacter(fieldUnderAttack, middleRange);
        if (middleCount > 0) {
            return middleRange;
        }

        // 後方横一列
        final FieldGrid[] behindRange = new FieldGrid[]{FieldGrid.BehindLeft, FieldGrid.BehindMiddle, FieldGrid.BehindRight};
        return behindRange;
    }

    /**
     * 打属性の攻撃範囲を決定する
     */
    private static FieldGrid[] getStampAttribute(final Optional<GameCharacter>[] fieldUnderAttack) {
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

    /**
     * 突属性の攻撃範囲を決定する
     */
    private static FieldGrid[] getThrustAttribute(final Optional<GameCharacter>[] fieldUnderAttack, final FieldGrid attackerPos) {
        final FieldGrid[] leftRange = new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.MiddleLeft, FieldGrid.BehindLeft};
        final FieldGrid[] middleRange = new FieldGrid[]{FieldGrid.ForwardMiddle, FieldGrid.Center, FieldGrid.BehindMiddle};
        final FieldGrid[] rightRange = new FieldGrid[]{FieldGrid.ForwardRight, FieldGrid.MiddleRight, FieldGrid.BehindRight};
        final int leftCount = AttackRangeByAttribute.countFieldCharacter(fieldUnderAttack, leftRange);
        final int middleCount = AttackRangeByAttribute.countFieldCharacter(fieldUnderAttack, middleRange);
        final int rightCount = AttackRangeByAttribute.countFieldCharacter(fieldUnderAttack, rightRange);

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
