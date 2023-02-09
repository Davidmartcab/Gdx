package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.Collections;
import java.util.HashMap;

public class MyInput implements InputProcessor {
    private final MyGdxGame game;
    public MyInput(MyGdxGame game) {
        // inicializar
        this.game = game;
        WIDTH = MyGdxGame.WIDTH;
        HEIGHT = MyGdxGame.HEIGHT;
    }
    private final int HEIGHT;
    private final int WIDTH;

    private final HashMap<Integer, Character> pointers = new HashMap<>();

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT)game.izquierda = true;
        if(keycode == Input.Keys.RIGHT)game.derecha = true;
        if(keycode == Input.Keys.SPACE)game.disparando = true;
        if(keycode == Input.Keys.ESCAPE){
            game.pausa = !game.pausa;
            if(game.pausa)game.sound.pause();
            else game.sound.resume();
        }
        // manejar evento de tecla presionada
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT)game.izquierda = false;
        if(keycode == Input.Keys.RIGHT)game.derecha = false;
        if(keycode == Input.Keys.SPACE)game.disparando = false;
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenY < HEIGHT && screenY > HEIGHT-(HEIGHT/5)){
            if(screenX < WIDTH/2) {
                game.izquierda = true;
                pointers.put(pointer, 'l');
            }
            else {
                game.derecha = true;
                pointers.put(pointer, 'r');
            }
        }else{
            game.disparando = true;
            pointers.put(pointer, 's');
        }
        // manejar evento de toque
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenY < HEIGHT && screenY > HEIGHT-(HEIGHT/5)){
            if(screenX < WIDTH/2) {
                game.izquierda = false;
                pointers.remove(pointer);
            }
            else {
                game.derecha = false;
                pointers.remove(pointer);
            }
        }else {
            game.disparando = false;
            pointers.remove(pointer);
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Character c = pointers.get(pointer);
        boolean b = screenY < HEIGHT && screenY > HEIGHT - (HEIGHT / 5);
        switch (c) {
            case 'l':
                if(screenX > WIDTH/2) {
                    game.izquierda = false;
                    game.derecha = true;
                    pointers.put(pointer, 'r');
                }else if(!b) {
                    game.derecha = false;
                    game.disparando = true;
                    pointers.put(pointer, 's');
                }
            break;
            case 'r':
                if(screenX < WIDTH/2) {
                    game.derecha = false;
                    game.izquierda = true;
                    pointers.put(pointer, 'l');
                }else if(!(b)) {
                    game.derecha = false;
                    game.disparando = true;
                    pointers.put(pointer, 's');
                }
            break;
            case 's':
                if(b) {
                    if(Collections.frequency(pointers.values(), 's') < 2) game.disparando = false;
                    if(screenX < WIDTH/2) {
                        game.izquierda = true;
                        pointers.put(pointer, 'l');
                    }else {
                        game.derecha = true;
                        pointers.put(pointer, 'r');
                    }
                }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
