package agarthenoasis.gamesystem.battle;

import java.util.LinkedList;
import java.util.Optional;

import agarthenoasis.gamesystem.LanePanel;
import agarthenoasis.gamesystem.battle.event.EventType;
import agarthenoasis.gamesystem.battle.field.BattleField;
import agarthenoasis.gamesystem.battle.field.FieldGrid;
import agarthenoasis.gamesystem.battle.state.EventTriggeredByState;
import agarthenoasis.gamesystem.battle.status.CharacterAttribute;
import agarthenoasis.gamesystem.battle.status.StatusGetter;
import agarthenoasis.object.character.GameCharacter;
import agarthenoasis.util.Pair;

// あくまで行動順を決めるだけのクラス
public class BattleAttackScheduler {
    private final BattleField battleField;
    private final ActiveLane activeLane;
    private boolean isPVP;
    private Optional<EventTriggeredByState> attackerEvent;
    private LinkedList<EventTriggeredByState> receiver;

    public BattleAttackScheduler(final boolean isTurnBattle, final BattleField battleField) {
        this.battleField = battleField;
        this.activeLane = new ActiveLane(isTurnBattle);
        this.isPVP = isTurnBattle;
        this.attackerEvent = Optional.empty();
        this.receiver = new LinkedList<>();
    }

    public void update(final float deltaTime) {
        this.updateActiveLane();

        // PVPの場合はターン制のバトルになるのでレーンに追加されるまで何もしない
        if (this.isPVP && this.activeLane.isEmpty()) {
            return;
        }

        if (this.attackerEvent.isPresent()) {
            // 攻撃通知
            final EventTriggeredByState event = this.attackerEvent.get();
            if (!event.inEvent(EventType.Attack, deltaTime)) {
                event.endEvent(EventType.Attack);

                this.attackerEvent = Optional.empty();
            }
        } else {
            // 攻撃中(スキル発動含め)のキャラが存在する場合はスキル発動しない
            this.activateSkill();
            this.attack();
        }

        // 被攻撃処理
        if (!this.receiver.isEmpty()) {
            final LinkedList<EventTriggeredByState> deleteList = new LinkedList<>();

            // 更新
            for (final EventTriggeredByState event : this.receiver) {
                if (!event.inEvent(EventType.ReceiveAttack, deltaTime)) {
                    event.endEvent(EventType.ReceiveAttack);
                    deleteList.add(event);
                }
            }

            // 削除
            for (final EventTriggeredByState eventTriggeredByState : deleteList) {
                this.receiver.remove(eventTriggeredByState);
            }

        }

    }

    private void updateActiveLane() {
        // キューに空きがある場合は行動待ちがいないかチェック
        if (this.activeLane.canPushLane()) {
            return;
        }
        this.battleField.getAllyList();
        this.battleField.getEnemyList();

        // 敵と味方同時に存在した場合は乱数で決める
        // 味方,敵のみの場合はマス目順に追加
        this.activeLane.pushToActiveLane();
    }

    private void activateSkill() {
        // スキル発動待ちであればスキル発動
        if (!this.activeLane.canUseSkill()) {
            return;
        }
        this.activeLane.popFromSkillQueue();
    }

    private void attack() {
        // フィールド上のキャラが行動中でなければ攻撃するキャラを決める
        if (this.activeLane.isEmpty()) {
            return;
        }
        final Pair<GameCharacter, LanePanel> pair = this.activeLane.popFromActiveLane();
        final GameCharacter attacker = pair.first;

        // 攻撃開始
        attacker.startEvent(EventType.Attack);
        final StatusGetter attackerStatus = attacker.getStatus();
        final CharacterAttribute attribute = attackerStatus.getAttribute();

        // 攻撃範囲を取得し攻撃範囲にいたキャラクターに攻撃
        final FieldGrid[] fieldGrids = this.battleField.getAllyAttackRange(attribute, attacker.getFiledGrid());
        for (final FieldGrid fieldGrid : fieldGrids) {
            final Optional<GameCharacter> character = this.battleField.getEnemyCharacter(fieldGrid);
            if (character.isPresent()) {
                // 攻撃されたことを通知
                final GameCharacter receiveAttackCharacter = character.get();
                receiveAttackCharacter.startEvent(EventType.ReceiveAttack);
                this.receiver.add(receiveAttackCharacter);

                // 先にHPは減らしておく
                final float attack = attackerStatus.getAttack();
                receiveAttackCharacter.getStatusModifier().addDamage(attack, 1.0f);
            }
        }

    }

}
