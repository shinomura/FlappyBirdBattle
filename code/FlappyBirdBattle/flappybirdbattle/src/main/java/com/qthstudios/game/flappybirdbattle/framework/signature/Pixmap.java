package com.qthstudios.game.flappybirdbattle.framework.signature;


import static com.qthstudios.game.flappybirdbattle.framework.signature.Graphics.PixmapFormat;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
