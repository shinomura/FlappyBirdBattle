package com.qthstudios.game.flappybirdbattle.framework;

import com.qthstudios.game.flappybirdbattle.framework.impl.AndroidSound;

public interface Audio {
    public Music newMusic(String filename);

    public AndroidSound newSound(String filename);
}
