package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Score {

    private int score = 0;
    private final int lvl;
    private final SpriteBatch batch;

    private final int topScaleX;
    private final int topScaleY;
    private final int left1;
    private final int left2;
    private final int top1;
    private final int top2;

    public Score(int lvl, int topHeight, int topWidth){
        batch = new SpriteBatch();
        topScaleX = (int)(topWidth * 0.005);
        topScaleY = (int)(topHeight * 0.0033334);
        left1 = (int)(topWidth * 0.025);
        left2 = (int)(topWidth * 0.025);
        top1 = (int)(topHeight * 0.983334);
        top2 = (int)(topHeight * 0.9166667);
        this.lvl = lvl;
    }

    public int getScore(){
        return score;
    }

    public void addScore(){
        this.score += (250 / (1.25*lvl));
//        this.score += 250;
    }

    public void removeScore(){
        this.score -= 100;
    }

    public void removeDisparoBoss(){
        this.score -= 300;
    }
    public void removeAllScore(){
        this.score = -10000;
    }

    public void pintarScore(){
        BitmapFont font = new BitmapFont();
        batch.begin();
        font.getData().setScale(topScaleX, topScaleY);
        font.draw(batch, "Nivel: " + lvl, left1, top1);
        font.draw(batch, "Score: " + score, left2, top2);
        batch.end();
    }

    public void liberar(){
        batch.dispose();
    }
}
