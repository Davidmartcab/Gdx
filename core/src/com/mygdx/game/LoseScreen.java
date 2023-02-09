package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoseScreen {
    private final SpriteBatch batch;
    private final int topScaleX;
    private final int topScaleY;
    private final int left1;
    private final int left2;
    private final int left3;
    private final int top1;
    private final int top2;
    private final int top3;
    public LoseScreen(int topHeight, int topWidth){
        topScaleX = (int)(topWidth * 0.005);
        topScaleY = (int)(topHeight * 0.0033334);
        left1 = (int)(topWidth * 0.325);
        left2 = (int)(topWidth * 0.375);
        left3 = (int)(topWidth * 0.325);
        top1 = (int)(topHeight * 0.56667);
        top2 = (int)(topHeight * 0.5);
        top3 = (int)(topHeight * 0.433334);
        batch = new SpriteBatch();
    }


    public void printarLose(int lv){
        BitmapFont font = new BitmapFont();
        batch.begin();
        font.getData().setScale(topScaleX, topScaleY);
        font.draw(batch, "Has perdido", left1, top1);
        font.draw(batch, "Nivel: " + lv, left2, top2);
        font.draw(batch, "Pulsa Enter", left3, top3);
        batch.end();
    }

    public void liberar(){
        batch.dispose();
    }
}
