package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {

	private SpriteBatch batch;
	public static int WIDTH = 400;
	public static int HEIGHT = 600;
	public static final String TITLE = "Marcianitos";
	private BackGround fondo;
	private Tanque tanque;
	private Disparo disparo;
	private Marcianito marcianito;
	private Score score;
	private LoseScreen loseScreen;
	private WinScreen winScreen;
	private StartScreen startScreen;
	private Boss boss;
	private DisparoBoss disparoBoss, disparoBoss2;
	private VidaBoss vidaBoss;
	private Explosion explosion;
	private boolean stateOfGame = true;
	private boolean fisrtTime = true;
	private int subFps = 0;
	private boolean justBoss = false;
	private int lvl = 1;
	public boolean pausa = false;
	public Sound sound;
	public boolean disparando = false, derecha = false, izquierda = false;
	private MyInput myInput = new MyInput(this);
	public MyGdxGame() {
	}

	public MyGdxGame(int height, int width) {
		HEIGHT = height;
		WIDTH = width;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		fondo = new BackGround(batch);
		disparo = new Disparo(HEIGHT, WIDTH);
		tanque = new Tanque(this.disparo, HEIGHT, WIDTH);
		score = new Score(lvl, HEIGHT, WIDTH);
		marcianito = new Marcianito(score, lvl, HEIGHT, WIDTH);
		loseScreen = new LoseScreen(HEIGHT, WIDTH);
		winScreen = new WinScreen(HEIGHT, WIDTH);
		startScreen = new StartScreen(HEIGHT, WIDTH);
		disparoBoss = new DisparoBoss(lvl, HEIGHT, WIDTH);
		disparoBoss2 = new DisparoBoss(lvl, HEIGHT, WIDTH);
		boss = new Boss(score, lvl, HEIGHT, WIDTH);
		vidaBoss = new VidaBoss(HEIGHT, WIDTH);
		explosion = new Explosion(HEIGHT, WIDTH);
		sound = Gdx.audio.newSound(Gdx.files.internal("backgroundSound.mp3"));
		sound.loop(-100);
		Gdx.input.setInputProcessor(myInput);
	}

	@Override
	public void render () {
		if(pausa) return;

		fondo.pintar(WIDTH, HEIGHT, lvl);
//		Comprueba que sea la primera vez que se inicia para mostrar la pantalla principal
		if(fisrtTime){
			startScreen.printarStart();
//			if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
			if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.justTouched()){
				Gdx.audio.newSound(Gdx.files.internal("enterButton.mp3")).play();
				marcianito.start();
				fisrtTime = false;
			}
			return;
		}
//		Si no se ha acabado el juego (muerto o ganado al boss)
		if(stateOfGame){
			if(!justBoss) {
				disparoBoss.setX(tanque.getX()-(int)(WIDTH*0.1));
				disparoBoss2.setX(tanque.getX()+(int)(WIDTH*0.1));
			}
			subFps++;
//			Pinta los objetos principales
			score.pintarScore();
			tanque.pintarPersonaje();
			disparo.pintarDisparo();
			disparo.mover(Gdx.graphics.getDeltaTime());
			explosion.pintarExplosion();
//			Controles
			if(derecha && !izquierda) tanque.moverDerecha();
			if(izquierda && !derecha) tanque.moverIzquierda();
			if(disparando) tanque.disparar();
//			if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
//				pausa = true;
//				sound.pause();
//			}
//			Aquí se ejecuta el boss
			if(score.getScore() >= 1000 || justBoss){
				switch (lvl) {
					case 1:
						lvl1Boss();
					break;
					case 2:
						lvl2Boss();
					break;
					case 3:
						lvl3Boss();
						break;
					case 4:
						lvl4Boss();
					break;
					case 5:
						lvl5Boss();
					break;
					case 6:
						lvl6Boss();
					break;
					default:
						lvl7Boss();
				}
			}else{
				switch (lvl) {
					case 1:
					case 2:
					case 3:
					case 4:
						lvl1();
					break;
					default:
						lvl5();
				}
			}
//			Si pierde todos los puntos muere
			if(score.getScore() < 0) {
				Gdx.audio.newSound(Gdx.files.internal("lose.mp3")).play();
				stateOfGame = false;
			}
		}else{
//			Reiniciar partida
			if(score.getScore() < 0) {
				loseScreen.printarLose(lvl);
				if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.justTouched()) {
					Gdx.audio.newSound(Gdx.files.internal("enterButton.mp3")).play();
					myInput = new MyInput(this);
					lvl = 1;
					fisrtTime = true;
					subFps = 0;
					stateOfGame = true;
					score = new Score(lvl, HEIGHT, WIDTH);
					disparo = new Disparo(HEIGHT, WIDTH);
					tanque = new Tanque(this.disparo, HEIGHT, WIDTH);
					marcianito = new Marcianito(score, lvl, HEIGHT, WIDTH);
					disparoBoss = new DisparoBoss(lvl, HEIGHT, WIDTH);
					disparoBoss2 = new DisparoBoss(lvl, HEIGHT, WIDTH);
					boss = new Boss(score, lvl, HEIGHT, WIDTH);
					justBoss = false;
				}
			}
//			Siguiente nivel
			else {
				winScreen.printarWin(lvl);
				if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.justTouched()) {
					Gdx.audio.newSound(Gdx.files.internal("enterButton.mp3")).play();
					myInput = new MyInput(this);
					lvl ++;
					subFps = 0;
					stateOfGame = true;
					score = new Score(lvl, HEIGHT, WIDTH);
					disparo = new Disparo(HEIGHT, WIDTH);
					tanque = new Tanque(this.disparo, HEIGHT, WIDTH);
					marcianito = new Marcianito(score, lvl, HEIGHT, WIDTH);
					disparoBoss = new DisparoBoss(lvl, HEIGHT, WIDTH);
					disparoBoss2 = new DisparoBoss(lvl, HEIGHT, WIDTH);
					boss = new Boss(score, lvl, HEIGHT, WIDTH);
					justBoss = false;
					marcianito.start();
				}
			}

		}
	}


	private void lvl1Boss() {
		justBoss = true;
		boss.pintarBoss();
		boss.mover(Gdx.graphics.getDeltaTime());
		vidaBoss.pintarScore(boss.getVida(), boss.getMaxVida());
		if(boss.tocado(disparo.getArea())){
			explosion.hit(disparo.getX()-(int)(WIDTH*0.02), disparo.getY());
			boss.restVida();
			disparo.recarga();
			if(boss.getVida() == 0){
				stateOfGame = false;
				Gdx.audio.newSound(Gdx.files.internal("win.mp3")).play();
			}
			else boss.subir();
		}
	}

	private void lvl2Boss() {
		lvl1Boss();
		disparoBoss.mover(Gdx.graphics.getDeltaTime(), tanque.getX(), boss.getY());
		disparoBoss.pintarDisparoBoss();
		if(tanque.tocado(disparoBoss.getArea())){
			explosion.hit(disparoBoss.getX()-(int)(WIDTH*0.04), disparoBoss.getY());
			disparoBoss.setState(1);
			disparoBoss.recarga(tanque.getX(), boss.getY());
			score.removeDisparoBoss();
		}
	}

	private void lvl3Boss(){
		lvl1Boss();
		disparoBoss.mover(Gdx.graphics.getDeltaTime(), tanque.getX()-(int)(WIDTH*0.1), boss.getY());
		disparoBoss.pintarDisparoBoss();
		if(tanque.tocado(disparoBoss.getArea())){
			explosion.hit(disparoBoss.getX()-(int)(WIDTH*0.02), disparoBoss.getY());
			disparoBoss.setState(1);
			disparoBoss.recarga(tanque.getX(), boss.getY());
			score.removeDisparoBoss();
		}
		disparoBoss2.mover(Gdx.graphics.getDeltaTime(), tanque.getX()+(int)(WIDTH*0.1), boss.getY());
		disparoBoss2.pintarDisparoBoss();
		if(tanque.tocado(disparoBoss2.getArea())){
			explosion.hit(disparoBoss2.getX()-(int)(WIDTH*0.02), disparoBoss2.getY());
			disparoBoss2.setState(1);
			disparoBoss2.recarga(tanque.getX(), boss.getY());
			score.removeDisparoBoss();
		}
	}

	private void lvl4Boss(){
		lvl2Boss();
		lvl1();
	}

	private void lvl5Boss(){
		lvl3Boss();
		lvl1();
	}

	private void lvl6Boss(){
		lvl2Boss();
		lvl5();
	}

	private void lvl7Boss(){
		lvl3Boss();
		lvl5();
	}


	private void lvl1() {
		marcianito.pintarMarciano();
		if(subFps > 20) marcianito.mover(Gdx.graphics.getDeltaTime(), boss.getY());
		if(marcianito.tocado(disparo.getArea())){
			Gdx.audio.newSound(Gdx.files.internal("pop1.mp3")).play();
			explosion.hit(disparo.getX()-(int)(WIDTH*0.02), disparo.getY());
			subFps = 0;
			this.score.addScore();
			marcianito.start();
			marcianito.start(boss.getY());
			disparo.recarga();
		}
	}

	private void lvl5(){
		marcianito.pintarMarciano();
		if(subFps > 20) marcianito.zigzag(Gdx.graphics.getDeltaTime(), boss.getY(), subFps);
		if(marcianito.tocado(disparo.getArea())){
			Gdx.audio.newSound(Gdx.files.internal("pop1.mp3")).play();
			explosion.hit(disparo.getX()-(int)(WIDTH*0.02), disparo.getY());
			subFps = 0;
			this.score.addScore();
			marcianito.start();
			marcianito.start(boss.getY());
			disparo.recarga();
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		tanque.liberar();
		fondo.liberar();
		disparo.liberar();
		marcianito.liberar();
		disparoBoss.liberar();
		boss.liberar();
		loseScreen.liberar();
		score.liberar();
		startScreen.liberar();
		vidaBoss.liberar();
		winScreen.liberar();
	}
}
