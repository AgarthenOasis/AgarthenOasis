package agarthenoasis.gamesystem.battle.status;

import agarthenoasis.gamesystem.battle.field.FieldGrid;
import agarthenoasis.gamesystem.battle.sheet.SheetEffect;

public interface BattleStatusGetter {

    FieldGrid getFiledGrid();

    StatusGetter getStatus();

    SheetEffect getSheetEffect();

    StatusModifier getStatusModifier();

}
