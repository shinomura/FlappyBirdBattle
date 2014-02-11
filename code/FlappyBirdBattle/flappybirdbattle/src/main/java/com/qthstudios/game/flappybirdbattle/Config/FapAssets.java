package com.qthstudios.game.flappybirdbattle.config;

import com.qthstudios.game.flappybirdbattle.R;
import com.qthstudios.game.flappybirdbattle.framework.signature.Sound;
import com.qthstudios.game.flappybirdbattle.framework.gl.Animation;
import com.qthstudios.game.flappybirdbattle.framework.gl.Texture;
import com.qthstudios.game.flappybirdbattle.framework.gl.TextureRegion;
import com.qthstudios.game.flappybirdbattle.framework.impl.GLGame;
import com.qthstudios.game.flappybirdbattle.utils.ResourceUtils;

import java.util.HashMap;
import java.util.Map;

import static com.qthstudios.game.flappybirdbattle.utils.LogUtils.LOGE;

/**
 * How to use:
 * batcher.drawSprite(160, 200, 300, 110,  FapAssets.textureRegions.get(FapAssets.TextureAsset.button_menu));
 */
public class FapAssets {
    public static Texture atlas;

    public static Map<String, TextureRegion> textureRegions;
    public static Map<String, Animation> animations;

    public static Sound sfxDie;
    public static Sound sfxHit;
    public static Sound sfxPoint;
    public static Sound sfxSwooshing;
    public static Sound sfxWing;

    public static void load(GLGame game) {
        atlas = new Texture(game, "gfx/atlas.png");

        // Load textures
        String texture = ResourceUtils.readRawTextFile(game.getApplicationContext(), R.raw.atlas);
        String[] texLines = texture.split("\n");
        textureRegions = new HashMap<String, TextureRegion>();
        for (String line : texLines) {
            String[] parts = line.split(" ");
            textureRegions.put(parts[0],
                    new TextureRegion(atlas,
                            Float.parseFloat(parts[3]) * 1024,
                            Float.parseFloat(parts[4]) * 1024,
                            Float.parseFloat(parts[1]),
                            Float.parseFloat(parts[2])));
        }

        // Load animates
        String animates = ResourceUtils.readRawTextFile(game.getApplicationContext(), R.raw.atlas_animate);
        String[] aniLines = animates.split("\n");
        animations = new HashMap<String, Animation>();
        for (int i = 0; i < aniLines.length; i = i + 3) {
            // LOGE("TRUNGDQ", "ani: " + aniLines[i].split(" ")[0]);
            animations.put(aniLines[i].split(" ")[0],
                    new Animation(0.12f,
                            new TextureRegion(atlas,
                                    Float.parseFloat(aniLines[i].split(" ")[3]) * 1024,
                                    Float.parseFloat(aniLines[i].split(" ")[4]) * 1024,
                                    Float.parseFloat(aniLines[i].split(" ")[1]),
                                    Float.parseFloat(aniLines[i].split(" ")[2])),
                            new TextureRegion(atlas,
                                    Float.parseFloat(aniLines[i + 1].split(" ")[3]) * 1024,
                                    Float.parseFloat(aniLines[i + 1].split(" ")[4]) * 1024,
                                    Float.parseFloat(aniLines[i + 1].split(" ")[1]),
                                    Float.parseFloat(aniLines[i + 1].split(" ")[2])),
                            new TextureRegion(atlas,
                                    Float.parseFloat(aniLines[i + 2].split(" ")[3]) * 1024,
                                    Float.parseFloat(aniLines[i + 2].split(" ")[4]) * 1024,
                                    Float.parseFloat(aniLines[i + 2].split(" ")[1]),
                                    Float.parseFloat(aniLines[i + 2].split(" ")[2])),
                            new TextureRegion(atlas,
                                    Float.parseFloat(aniLines[i + 1].split(" ")[3]) * 1024,
                                    Float.parseFloat(aniLines[i + 1].split(" ")[4]) * 1024,
                                    Float.parseFloat(aniLines[i + 1].split(" ")[1]),
                                    Float.parseFloat(aniLines[i + 1].split(" ")[2]))
                    )
            );
        }


        // Load sounds
        sfxDie = game.getAudio().newSound("sounds/sfx_die.ogg");
        sfxHit = game.getAudio().newSound("sounds/sfx_hit.ogg");
        sfxPoint = game.getAudio().newSound("sounds/sfx_point.ogg");
        sfxSwooshing = game.getAudio().newSound("sounds/sfx_swooshing.ogg");
        sfxWing = game.getAudio().newSound("sounds/sfx_wing.ogg");
    }

    public static void reload() {
        atlas.reload();
    }

    public static void playSound(Sound sound) {
        if (Settings.soundEnabled)
            sound.play(1);
    }

    public static class AnimateAsset {
        public static final String yellow_bird = "bird0_0";
        public static final String blue_bird = "bird1_0";
        public static final String red_bird = "bird2_0";
        public static final String blink_0 = "blink_0";
    }

    public static class TextureAsset {
        public static final String bg_day = "bg_day";
        public static final String bg_night = "bg_night";
        public static final String brand_copyright = "brand_copyright";
        public static final String black = "black";
        public static final String button_menu = "button_menu";
        public static final String button_ok = "button_ok";
        public static final String button_pause = "button_pause";
        public static final String button_play = "button_play";
        public static final String button_rate = "button_rate";
        public static final String button_resume = "button_resume";
        public static final String button_score = "button_score";
        public static final String button_share = "button_share";
        public static final String font_048 = "font_048";
        public static final String font_049 = "font_049";
        public static final String font_050 = "font_050";
        public static final String font_051 = "font_051";
        public static final String font_052 = "font_052";
        public static final String font_053 = "font_053";
        public static final String font_054 = "font_054";
        public static final String font_055 = "font_055";
        public static final String font_056 = "font_056";
        public static final String font_057 = "font_057";
        public static final String land = "land";
        public static final String medals_0 = "medals_0";
        public static final String medals_1 = "medals_1";
        public static final String medals_2 = "medals_2";
        public static final String medals_3 = "medals_3";
        public static final String new_score = "new_score";
        public static final String number_context_00 = "number_context_00";
        public static final String number_context_01 = "number_context_01";
        public static final String number_context_02 = "number_context_02";
        public static final String number_context_03 = "number_context_03";
        public static final String number_context_04 = "number_context_04";
        public static final String number_context_05 = "number_context_05";
        public static final String number_context_06 = "number_context_06";
        public static final String number_context_07 = "number_context_07";
        public static final String number_context_08 = "number_context_08";
        public static final String number_context_09 = "number_context_09";
        public static final String number_context_10 = "number_context_10";
        public static final String number_score_00 = "number_score_00";
        public static final String number_score_01 = "number_score_01";
        public static final String number_score_02 = "number_score_02";
        public static final String number_score_03 = "number_score_03";
        public static final String number_score_04 = "number_score_04";
        public static final String number_score_05 = "number_score_05";
        public static final String number_score_06 = "number_score_06";
        public static final String number_score_07 = "number_score_07";
        public static final String number_score_08 = "number_score_08";
        public static final String number_score_09 = "number_score_09";
        public static final String pipe2_down = "pipe2_down";
        public static final String pipe2_up = "pipe2_up";
        public static final String pipe_down = "pipe_down";
        public static final String pipe_up = "pipe_up";
        public static final String score_panel = "score_panel";
        public static final String text_game_over = "text_game_over";
        public static final String text_ready = "text_ready";
        public static final String title = "title";
        public static final String tutorial = "tutorial";
        public static final String white = "white";
    }
}
