package com.qthstudios.game.flappybirdbattle.model.custom_model;

import com.qthstudios.game.flappybirdbattle.framework.signature.GameObject;

/**
 * Created by ThaoHQSE60963 on 2/10/14.
 */
public class Coin extends GameObject {
    public static final float COIN_WIDTH = 0.5f;
    public static final float COIN_HEIGHT = 0.8f;
    public static final int COIN_SCORE = 10;

    float stateTime;
    public Coin(float x, float y) {
        super(x, y, COIN_WIDTH, COIN_HEIGHT);
        stateTime = 0;
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
    }
}
