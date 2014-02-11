package com.qthstudios.game.flappybirdbattle.model;

import com.qthstudios.game.flappybirdbattle.framework.signature.DynamicGameObject;
import com.qthstudios.game.flappybirdbattle.model.custom_model.World;

/**
 * Created by ThaoHQSE60963 on 2/11/14.
 */
public class Bird extends DynamicGameObject {

    public Bird(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public static final int BOB_STATE_FLY = 0;
    public static final int BOB_STATE_FALL = 1;
    public static final int BOB_STATE_HIT = 2;
    public static final float BOB_JUMP_VELOCITY = 11;
    public static final float BOB_MOVE_VELOCITY = 20;
    public static final float BIRD_WIDTH = 0.8f;
    public static final float BIRD_HEIGHT = 0.8f;

    int state;
    float stateTime;

    public Bird(float x, float y) {
        super(x, y, BIRD_WIDTH, BIRD_HEIGHT);
        state = BOB_STATE_FALL;
        stateTime = 0;
    }

    public void update(float deltaTime) {
        velocity.add(World.gravity.x * deltaTime, World.gravity.y * deltaTime);
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);

        if(velocity.y > 0 && state != BOB_STATE_HIT) {
            if(state != BOB_STATE_FLY) {
                state = BOB_STATE_FLY;
                stateTime = 0;
            }
        }

        if(velocity.y < 0 && state != BOB_STATE_HIT) {
            if(state != BOB_STATE_FALL) {
                state = BOB_STATE_FALL;
                stateTime = 0;
            }
        }

        if(position.x < 0)
            position.x = World.WORLD_WIDTH;
        if(position.x > World.WORLD_WIDTH)
            position.x = 0;

        stateTime += deltaTime;
    }

    public void hitPipe() {
        throw new UnsupportedOperationException();
    }

    public void hitTop() {
        throw new UnsupportedOperationException();
    }
    public void hitBot() {
        throw new UnsupportedOperationException();
    }

    public void hitSquirrel() {
        velocity.set(0,0);
        state = BOB_STATE_HIT;
        stateTime = 0;
    }

    public void hitPlatform() {
        velocity.y = BOB_JUMP_VELOCITY;
        state = BOB_STATE_FLY;
        stateTime = 0;
    }

    public void hitSpring() {
        velocity.y = BOB_JUMP_VELOCITY * 1.5f;
        state = BOB_STATE_FLY;
        stateTime = 0;
    }
}
