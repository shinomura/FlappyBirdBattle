package com.qthstudios.game.flappybirdbattle.drawer;

import com.qthstudios.game.flappybirdbattle.framework.gl.SpriteBatcher;

/**
 * Created by ThaoHQSE60963 on 2/13/14.
 * use this class to draw pipe with different setting
 */
public class PipeDrawer {

    /** draw two pipe up and down. that distance between them is Default */
    public static void DrawPipe(SpriteBatcher batcher, int y) {
        DrawPipe(batcher, y, CoordinateConfig.DEFAULT_PIPE_DISTANCE);
    }

    /** draw two pipe on same column */
    public static void DrawPipe(SpriteBatcher batcher, int y, int distance) {
        DrawPipe(batcher, y, CoordinateConfig.DEFAULT_PIPE_DISTANCE, CoordinateConfig.DEFAULT_BACKGROUND_WIDTH);
    }

    /** draw two pipe on same column. that top and bottom equal to margin */
    public static void DrawPipe(SpriteBatcher batcher, int y, int distance, int margin) {
        // TODO implement drawing method here
    }
}
