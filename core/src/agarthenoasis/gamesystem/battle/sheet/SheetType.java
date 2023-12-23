package agarthenoasis.gamesystem.battle.sheet;

import agarthenoasis.gamesystem.battle.field.FieldGrid;

public enum SheetType {
    Aegis1(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.MiddleLeft, FieldGrid.Center, FieldGrid.MiddleRight, FieldGrid.BehindRight}),
    Wing1(new FieldGrid[]{FieldGrid.ForwardRight, FieldGrid.MiddleRight, FieldGrid.Center, FieldGrid.BehindLeft, FieldGrid.BehindMiddle}),
    Wing2(new FieldGrid[]{FieldGrid.ForwardRight, FieldGrid.MiddleRight, FieldGrid.Center, FieldGrid.BehindLeft, FieldGrid.BehindMiddle}),
    Crescent1(new FieldGrid[]{FieldGrid.ForwardRight, FieldGrid.ForwardLeft, FieldGrid.MiddleRight, FieldGrid.BehindLeft, FieldGrid.BehindRight}),
    GeminiSword1(new FieldGrid[]{FieldGrid.ForwardRight, FieldGrid.MiddleRight, FieldGrid.MiddleLeft, FieldGrid.MiddleRight, FieldGrid.BehindMiddle}),
    Stinger1(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardMiddle, FieldGrid.MiddleLeft, FieldGrid.BehindMiddle, FieldGrid.BehindRight}),
    SpeedStar1(new FieldGrid[]{FieldGrid.ForwardMiddle, FieldGrid.Center, FieldGrid.BehindLeft, FieldGrid.BehindMiddle, FieldGrid.BehindRight}),
    SpeedRise1(new FieldGrid[]{FieldGrid.ForwardMiddle, FieldGrid.Center, FieldGrid.BehindLeft, FieldGrid.BehindMiddle, FieldGrid.BehindRight}),
    TidalWave1(new FieldGrid[]{FieldGrid.ForwardMiddle, FieldGrid.MiddleRight, FieldGrid.MiddleLeft, FieldGrid.BehindRight, FieldGrid.BehindLeft}),
    TidalWave2(new FieldGrid[]{FieldGrid.ForwardMiddle, FieldGrid.MiddleRight, FieldGrid.MiddleLeft, FieldGrid.BehindRight, FieldGrid.BehindLeft}),
    Titan1(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardMiddle, FieldGrid.ForwardRight, FieldGrid.Center, FieldGrid.BehindMiddle}),
    Titan2(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardMiddle, FieldGrid.ForwardRight, FieldGrid.Center, FieldGrid.BehindMiddle}),
    TwinGate1(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.MiddleLeft, FieldGrid.Center, FieldGrid.MiddleRight, FieldGrid.BehindLeft}),
    DecoyMaster1(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.MiddleRight, FieldGrid.BehindLeft, FieldGrid.BehindMiddle, FieldGrid.BehindRight}),
    Dominion1(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardRight, FieldGrid.Center, FieldGrid.BehindLeft, FieldGrid.BehindRight}),
    Dominion2(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardRight, FieldGrid.Center, FieldGrid.BehindLeft, FieldGrid.BehindRight}),
    Dragoon1(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.Center, FieldGrid.MiddleRight, FieldGrid.BehindLeft, FieldGrid.BehindRight}),
    NightForm1(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardRight, FieldGrid.Center, FieldGrid.BehindLeft, FieldGrid.BehindRight}),
    NightForm2(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardRight, FieldGrid.Center, FieldGrid.BehindLeft, FieldGrid.BehindRight}),
    PaladinGuard1(new FieldGrid[]{FieldGrid.ForwardMiddle, FieldGrid.MiddleRight, FieldGrid.MiddleLeft, FieldGrid.BehindRight, FieldGrid.BehindLeft}),
    Bullfighter2(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardMiddle, FieldGrid.ForwardRight, FieldGrid.Center, FieldGrid.BehindMiddle}),
    Pentagon1(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardRight, FieldGrid.MiddleLeft, FieldGrid.MiddleRight, FieldGrid.BehindMiddle}),
    Polaris1(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.Center, FieldGrid.MiddleRight, FieldGrid.BehindLeft, FieldGrid.BehindRight}),
    Lapis1(new FieldGrid[]{FieldGrid.ForwardRight, FieldGrid.MiddleLeft, FieldGrid.BehindLeft, FieldGrid.BehindMiddle, FieldGrid.BehindRight}),
    Lapis2(new FieldGrid[]{FieldGrid.ForwardRight, FieldGrid.MiddleLeft, FieldGrid.BehindLeft, FieldGrid.BehindMiddle, FieldGrid.BehindRight}),
    RapidRaider1(new FieldGrid[]{FieldGrid.ForwardLeft, FieldGrid.ForwardRight, FieldGrid.Center, FieldGrid.MiddleRight, FieldGrid.BehindLeft}),
    RobinHood1(new FieldGrid[]{FieldGrid.ForwardMiddle, FieldGrid.MiddleLeft, FieldGrid.MiddleRight, FieldGrid.BehindMiddle, FieldGrid.BehindRight});

    private final FieldGrid[] fieldGrids;

    SheetType(final FieldGrid[] fieldGrids) {
        this.fieldGrids = fieldGrids;
    }

    public FieldGrid[] getFieldGrids() {
        return this.fieldGrids;
    }

}
