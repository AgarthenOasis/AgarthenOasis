package agarthenoasis.gamesystem.battle.event;

public interface BattlePhase {
    void initialize();

    void gameOver();

    void victory();

    void nextWave();

}
