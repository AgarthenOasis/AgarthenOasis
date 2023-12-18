package agarthenoasis.gamesystem.battle;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import agarthenoasis.util.Pair;
import agarthenoasis.gamesystem.LanePanel;
import agarthenoasis.object.character.GameCharacter;

// キャラクター行動順の変更管理、及び保持するだけのクラス
public class ActiveLane {
    private final LinkedList<Pair<Optional<GameCharacter>, LanePanel>> activeLane; // キューではないのは状態異常によって途中の要素をremoveする可能性があるため
    private final Queue<GameCharacter> skillQueue; // スキル発動用のキュー
    private final static int MaxQueueSize = 5;
    private int maxLaneIndex;

    private final boolean isTurnBattle; // ターン制バトルか

    public ActiveLane(final boolean isTurnBattle) {
        this.isTurnBattle = isTurnBattle;
        this.activeLane = new LinkedList<>();
        this.skillQueue = new LinkedList<>();
        this.maxLaneIndex = 0;
    }

    // レーンにキャラクターを追加できるかのチェック
    public boolean canPushLane() {
        return this.activeLane.size() < this.MaxQueueSize;
    }

    public boolean isEmpty() {
        return this.activeLane.isEmpty();
    }

    public boolean push() {
        // レーンに入る最大値の場合は追加しない
        if (this.activeLane.size() + 1 > this.MaxQueueSize) {
            return false;
        }

        ++this.maxLaneIndex;
        return true;
    }

    public Pair<Optional<GameCharacter>, LanePanel> pop() {
        // キャラクターを取得してからキューから削除
        final Pair<Optional<GameCharacter>, LanePanel> pair = this.activeLane.peek();
        this.activeLane.pop();
        return pair;
    }

    public void remove(final int characterID) {
        for (final Pair<Optional<GameCharacter>, LanePanel> pair : this.activeLane) {
            // 保持している場合はキャラクターを表示
            if (pair.first.isPresent()) {
                return;
            }
        }
    }

    public void update() {


    }

    public void draw() {
        // レーンの情報を追加
        for (final Pair<Optional<GameCharacter>, LanePanel> pair : this.activeLane) {
            // 保持している場合はキャラクターを表示
            if (pair.first.isPresent()) {

            }
            //final int id = pair.first.isPresent() getCharacterID();

        }
    }

}
