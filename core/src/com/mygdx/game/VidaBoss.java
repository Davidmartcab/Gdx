package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VidaBoss {

    private final SpriteBatch batch;
    private final int topScaleX;
    private final int topScaleY;
    private final int left1;
    private final int top1;
    public VidaBoss(int topHeight, int topWidth){
        batch = new SpriteBatch();
        topScaleX = (int)(topWidth * 0.005);
        topScaleY = (int)(topHeight * 0.0033334);
        left1 = (int)(topWidth * 0.73);
        top1 = (int)(topHeight * 0.983334);
    }

    public void pintarScore(int vidaBoss, int maxVidaBoss){
        BitmapFont font = new BitmapFont();
        batch.begin();
        font.getData().setScale(topScaleX, topScaleY);
        font.draw(batch, vidaBoss+"/" + maxVidaBoss, left1, top1);
        batch.end();
    }

    public void liberar() {
        batch.dispose();
    }
}
