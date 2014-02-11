package com.qthstudios.game.flappybirdbattle.model.custom_model;

import com.qthstudios.game.flappybirdbattle.framework.signature.GameObject;

/**
 * Created by ThaoHQSE60963 on 2/10/14.
 */
public class Spring extends GameObject {
    public static float SPRING_WIDTH = 0.3f;
    public static float SPRING_HEIGHT = 0.3f;

    public Spring(float x, float y) {
        super(x, y, SPRING_WIDTH, SPRING_HEIGHT);
    }
}

