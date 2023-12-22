package agarthenoasis.gamesystem.battle.sheet;

import agarthenoasis.gamesystem.battle.field.FieldGrid;

public enum SheetType {
    Aegis1(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.MiddleLeft, FieldGrid.Center, FieldGrid.MiddleRight, FieldGrid.BehindRight}),
    Wing1(new FieldGrid[]{}), Wing2(new FieldGrid[]{}), Crescent1(new FieldGrid[]{}), GeminiSword1(new FieldGrid[]{}), Stinger1(new FieldGrid[]{}),
    SpeedStar1(new FieldGrid[]{}), SpeedRise1(new FieldGrid[]{}), TidalWave1(new FieldGrid[]{FieldGrid.ForwardMiddle, FieldGrid.MiddleRight,
            FieldGrid.MiddleLeft, FieldGrid.BehindRight, FieldGrid.BehindLeft}), TidalWave2(new FieldGrid[]{FieldGrid.ForwardMiddle, FieldGrid.MiddleRight,
            FieldGrid.MiddleLeft, FieldGrid.BehindRight, FieldGrid.BehindLeft}), Titan1(new FieldGrid[]{}), Titan2(new FieldGrid[]{}),
    TwinGate1(new FieldGrid[]{}), DecoyMaster1(new FieldGrid[]{}), Dominion1(new FieldGrid[]{}), Dominion2(new FieldGrid[]{}), Dragoon1(new FieldGrid[]{}),
    NightForm1(new FieldGrid[]{}), NightForm2(new FieldGrid[]{}), PaladinGuard1(new FieldGrid[]{}), Bullfighter2(new FieldGrid[]{}),
    Pentagon1(new FieldGrid[]{}), Polaris1(new FieldGrid[]{}), Meteor1(new FieldGrid[]{}), Lapis1(new FieldGrid[]{}), Lapis2(new FieldGrid[]{}),
    RapidRaider1(new FieldGrid[]{}), RobinHood1(new FieldGrid[]{});

    private final FieldGrid[] fieldGrids;

    SheetType(final FieldGrid[] fieldGrids) {
        this.fieldGrids = fieldGrids;
    }

    public FieldGrid[] getFieldGrids() {
        return this.fieldGrids;
    }

}
