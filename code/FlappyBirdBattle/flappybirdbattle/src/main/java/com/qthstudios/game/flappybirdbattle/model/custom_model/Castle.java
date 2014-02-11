package com.qthstudios.game.flappybirdbattle.model.custom_model;

import com.qthstudios.game.flappybirdbattle.framework.signature.GameObject;

/**
 * Created by ThaoHQSE60963 on 2/10/14.
 */
public class Castle extends GameObject {
    public static float CASTLE_WIDTH = 1.7f;
    public static float CASTLE_HEIGHT = 1.7f;

    public Castle(float x, float y) {
        super(x, y, CASTLE_WIDTH, CASTLE_HEIGHT);
    }

}

