package com.mygdx.brainman;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
//import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
//import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class Brainman implements ApplicationListener {
	private Texture piou;
	private Texture platform;
	private Texture water;
	private Texture cloudImg;
	
	private Sound jump;
	private Music brainmanMusic;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private Rectangle piouHitBox;
	
	private Array<Rectangle> platforms;
	
	private Array<Rectangle> clouds;
	private long lastCloudTime;
	
	private void spawnCloud(){
		Rectangle cloud = new Rectangle();
		cloud.x = 800;
		cloud.y = MathUtils.random(128, 480-16);
		cloud.width=16;
		cloud.height=16;
		clouds.add(cloud);;
		lastCloudTime = TimeUtils.nanoTime();
	}
	
	@Override
	public void create () {
		piou = new Texture(Gdx.files.internal("piou_anim/piou_0001.png"));
		platform = new Texture(Gdx.files.internal("world/platform_0001.png"));
		cloudImg=new Texture(Gdx.files.internal("world/cloud_0001.png"));
		
		jump = Gdx.audio.newSound(Gdx.files.internal("sfx/flap.wav"));
		brainmanMusic = Gdx.audio.newMusic(Gdx.files.internal("sfx/BoxCat_Games_-_08_-_CPU_Talk.mp3"));
		
		brainmanMusic.setLooping(true);
		brainmanMusic.play();
		
		camera= new OrthographicCamera();
		camera.setToOrtho(false, 800,480);
		batch = new SpriteBatch();
		
		piouHitBox=new Rectangle();
		piouHitBox.x=800/2 - 16/2;
		piouHitBox.y=64;
		piouHitBox.width=16;
		piouHitBox.height=16;
		
		clouds = new Array<Rectangle>();
		spawnCloud();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0/255f, 187/255f, 249/255f, 0/255f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) camera.translate(-200*Gdx.graphics.getDeltaTime(),0);
		if(Gdx.input.isKeyPressed(Keys.LEFT))  camera.translate(+200*Gdx.graphics.getDeltaTime(),0);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(piou, piouHitBox.x, piouHitBox.y);
		for(Rectangle cloud: clouds){
			batch.draw(cloudImg, cloud.x, cloud.y);
		}
		batch.end();
		
		if(Gdx.input.isKeyPressed(Keys.Q)) piouHitBox.x -=200*Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.D)) piouHitBox.x +=200*Gdx.graphics.getDeltaTime();
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){ jump.play();
		};
		
		if(piouHitBox.x < 0) piouHitBox.x =0;
		if(piouHitBox.x > 800 - 16) piouHitBox.x =800-16;
		
		if(TimeUtils.nanoTime() - lastCloudTime - 100000000 > 1000000000) spawnCloud();
		
		Iterator<Rectangle> iter = clouds.iterator();
		while(iter.hasNext()){
			Rectangle cloud =iter.next();
			cloud.x -= 43 * Gdx.graphics.getDeltaTime();
			
			if (cloud.x +16 <0) iter.remove();
			
			
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		piou.dispose();
		cloudImg.dispose();
		jump.dispose();
		brainmanMusic.dispose();
		batch.dispose();
	}
}

