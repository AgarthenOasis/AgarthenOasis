package agarthenoasis.gamesystem.battle;

import java.util.LinkedList;
import java.util.Optional;

import agarthenoasis.Pair;
import agarthenoasis.gamesystem.LanePanel;
import agarthenoasis.object.character.GameCharacter;

public class ActiveLane {
    private final LinkedList<Pair<Optional<GameCharacter>, LanePanel>> activeLane; // キューではないのは状態異常によって途中の要素をremoveする可能性があるため
    private final static int MaxQueueSize = 5;
    private int maxLaneIndex;

    public ActiveLane() {
        this.activeLane = new LinkedList<>();
        this.maxLaneIndex = 0;
    }

    public boolean push() {
        // レーンに入る最大値の場合は追加しない
        if (this.activeLane.size() + 1 > this.MaxQueueSize) {
            return false;
        }

        ++this.maxLaneIndex;
        return true;
    }

    public void pop() {
        // そもそもレーンに何も追加されていない場合は何もしない
        if (this.activeLane.isEmpty()) {
            return;
        }

        // もしキャラクターオブジェクトが存在した場合は攻撃を行う
        final Pair<Optional<GameCharacter>, LanePanel> pair = this.activeLane.peek();
        if (pair.first.isPresent()) {
            final GameCharacter gameCharacter = pair.first.get();
        }

        this.activeLane.pop();
    }

    public void remove(final int characterID) {
        for (final Pair<Optional<GameCharacter>, LanePanel> pair : this.activeLane) {
            // 保持している場合はキャラクターを表示
            if (pair.first.isPresent()) {

                return;
            }
        }
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
