package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartScreen {
    private final SpriteBatch batch;
    private final int topScaleX;
    private final int topScaleY;
    private final int left1;
    private final int left2;
    private final int top1;
    private final int top2;

    public StartScreen(int topHeight, int topWidth){
        topScaleX = (int)(topWidth * 0.005);
        topScaleY = (int)(topHeight * 0.0033334);
        left1 = (int)(topWidth * 0.4125);
        left2 = (int)(topWidth * 0.325);
        top1 = (int)(topHeight * 0.56667);
        top2 = (int)(topHeight * 0.48333334);
        batch = new SpriteBatch();
    }


    public void printarStart(){
        BitmapFont font = new BitmapFont();
        batch.begin();
        font.getData().setScale(topScaleX, topScaleY);
        font.draw(batch, "Start", left1, top1);
        font.draw(batch, "Pulsa Enter", left2, top2);
        batch.end();
    }

    public void liberar() {
        batch.dispose();
    }
}
