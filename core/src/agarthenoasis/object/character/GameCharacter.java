package agarthenoasis.object.character;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.HashMap;

import agarthenoasis.gamesystem.battle.event.EventType;
import agarthenoasis.gamesystem.battle.event.TurnEvent;
import agarthenoasis.gamesystem.battle.field.FieldGrid;
import agarthenoasis.gamesystem.battle.sheet.SheetEffect;
import agarthenoasis.gamesystem.battle.sheet.SheetType;
import agarthenoasis.gamesystem.battle.state.BattleState;
import agarthenoasis.gamesystem.battle.state.EventTriggeredByState;
import agarthenoasis.gamesystem.battle.status.BattleStatusGetter;
import agarthenoasis.gamesystem.battle.status.CharacterStatus;
import agarthenoasis.gamesystem.battle.status.StatusGetter;
import agarthenoasis.gamesystem.battle.status.StatusModifier;
import agarthenoasis.motion.CharacterMotionContext;
import agarthenoasis.object.GameObjectAffectedByCamera;
import agarthenoasis.scene.ObjectRegistry;

public class GameCharacter extends GameObjectAffectedByCamera implements EventTriggeredByState, BattleStatusGetter {
    private final int id;
    private final SDAnimation sdAnimation;
    private final CharacterStatus status;
    private final SheetEffect sheetEffect;
    private final CharacterMotionContext motionContext;
    private final HashMap<EventType, TurnEvent> eventMap; // このキャラクター自身へのイベントを保管
    private final FieldGrid fieldGridPosition;
    private float idleTime;
    private BattleState state;

    public GameCharacter(final ObjectRegistry registry, final Group group, final Camera camera, final String sdPath, final int characterID,
                         final SheetType sheetType, final FieldGrid fieldGrid) {
        super(registry, group, camera);
        this.idleTime = 10;

        this.id = characterID;
        this.fieldGridPosition = fieldGrid;
        this.eventMap = new HashMap<>();
        this.sdAnimation = new SDAnimation(registry, group, sdPath, 1);
        this.status = new CharacterStatus(characterID);
        this.motionContext = new CharacterMotionContext();
        this.state = BattleState.Idle;
        this.sheetEffect = new SheetEffect(fieldGrid, this.status.attribute) {
        };
    }

    @Override
    public void update(final float deltaTime) {
        // アニメーション更新

        this.sdAnimation.update(deltaTime);
        this.motionContext.inAction(deltaTime);
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        this.sdAnimation.draw(batch, parentAlpha);
    }

    @Override
    public void dispose() {

    }

    @Override
    public FieldGrid getFiledGrid() {
        return this.fieldGridPosition;
    }

    @Override
    public StatusGetter getStatus() {
        return this.status;
    }

    @Override
    public SheetEffect getSheetEffect() {
        return this.sheetEffect;
    }

    @Override
    public StatusModifier getStatusModifier() {
        return this.status;
    }

    public void setState(final BattleState state) {
        this.state = state;
    }

    protected void addEvent(final TurnEvent event) {
        this.eventMap.put(event.getEventType(), event);
    }

    @Override
    public void startEvent(final EventType eventType) {
        if (!this.eventMap.containsKey(eventType)) {
            return;
        }

        this.eventMap.get(eventType).startEvent();
    }

    @Override
    public boolean inEvent(final EventType eventType, final float deltaTime) {
        if (!this.eventMap.containsKey(eventType)) {
            return false;
        }

        return this.eventMap.get(eventType).inEvent(deltaTime);
    }

    @Override
    public void endEvent(final EventType eventType) {
        if (!this.eventMap.containsKey(eventType)) {
            return;
        }

        this.eventMap.get(eventType).endEvent();
    }

    @Override
    public void startBattle(final EventType eventType) {
        if (!this.eventMap.containsKey(eventType)) {
            return;
        }

        this.eventMap.get(eventType).startBattle();
    }

    @Override
    public void endBattle(final EventType eventType) {
        if (!this.eventMap.containsKey(eventType)) {
            return;
        }

        this.eventMap.get(eventType).endBattle();
    }

    @Override
    public void startMyTurn(final EventType eventType) {
        if (!this.eventMap.containsKey(eventType)) {
            return;
        }

        this.eventMap.get(eventType).startMyTurn();
    }

    @Override
    public void endMyTurn(final EventType eventType) {
        if (!this.eventMap.containsKey(eventType)) {
            return;
        }

        this.eventMap.get(eventType).endMyTurn();
    }

    @Override
    public void startTurn(final EventType eventType) {
        if (!this.eventMap.containsKey(eventType)) {
            return;
        }

        this.eventMap.get(eventType).startTurn();
    }

    @Override
    public void endTurn(final EventType eventType) {
        if (!this.eventMap.containsKey(eventType)) {
            return;
        }

        this.eventMap.get(eventType).endTurn();
    }
}
