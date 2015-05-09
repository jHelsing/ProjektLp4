package com.icyvenom.needforghetto.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.icyvenom.needforghetto.model.Bullet;
import com.icyvenom.needforghetto.model.Enemy;
import com.icyvenom.needforghetto.model.Player;
import com.icyvenom.needforghetto.model.World;

/**
 * Created by Marcus on 2015-04-29.
 */
public class WorldRenderer {

    private World world;
    private OrthographicCamera cam;

    private static final float CAMERA_WIDTH = 10f;
    private static final float CAMERA_HEIGHT = 10f;

    /** for debug rendering **/
    ShapeRenderer debugRenderer = new ShapeRenderer();

    private Texture playerTexture;
    private Texture enemyTexture;
    private Texture bulletTexture;
    private Texture backgroundTexture;

    private Sprite backgroundSprite;

    private SpriteBatch spriteBatch;
    private boolean debug = false;
    private int width;
    private int height;
    private float ppuX; // pixels per unit on the X axis
    private float ppuY; // pixels per unit on the Y axis

    public WorldRenderer(World world, boolean debug){
        this.world = world;
        this.cam = new OrthographicCamera(10, 10);
        this.cam.position.set(5, 5, 0);
        this.cam.update();
        spriteBatch = new SpriteBatch();
        this.debug = debug;
        loadTextures();
        loadBackground();
    }
    public void render(){
        spriteBatch.begin();
            backgroundSprite.draw(spriteBatch);
            drawEnemies();
            drawPlayer();
            drawBullets();
        spriteBatch.end();
        if(debug){
            drawDebug();
        }
    }

    public void drawPlayer(){
        Player player = world.getPlayer();
        spriteBatch.draw(playerTexture, player.getPosition().x * ppuX, player.getPosition().y * ppuY,
                        player.SIZE * ppuX, player.SIZE * ppuY);
    }

    public void drawEnemies(){
        for (Object e : world.getEnemies()) {
            Enemy enemy = (Enemy) e;
            spriteBatch.draw(enemyTexture, enemy.getPosition().x * ppuX, enemy.getPosition().y * ppuY,
                            enemy.SIZE * ppuX, enemy.SIZE * ppuY);
        }
    }

    public void drawBullets(){
        if(!world.getPlayer().getWeapon().getBullets().isEmpty()) {
            for (Bullet b : world.getPlayer().getWeapon().getBullets()) {
                spriteBatch.draw(bulletTexture, b.getPosition().x * ppuX, b.getPosition().y * ppuY,
                        b.SIZE * ppuX, b.SIZE * ppuY);
            }
        }
    }
    public void setSize(int w, int h){
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
    }
    public void loadTextures(){
        playerTexture = new Texture(Gdx.files.internal("images/car.png"));
        enemyTexture = new Texture(Gdx.files.internal("images/enemyCar.jpg"));
        bulletTexture = new Texture(Gdx.files.internal("images/bullet.png"));
    }

    private void loadBackground() {
        backgroundTexture = new Texture(Gdx.files.internal("images/car.png"));
        backgroundSprite = new Sprite(backgroundTexture);
    }

    public OrthographicCamera getCamera(){
        return cam;
    }

    public void drawDebug(){
        debugRenderer.setProjectionMatrix(cam.combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (Object e : world.getEnemies()) {
            Enemy enemy = (Enemy)e;
            Rectangle rect = enemy.getBounds();
            float x1 = rect.x;
            float y1 = rect.y;
            debugRenderer.setColor(new Color(1, 0, 0, 1));
            debugRenderer.rect(x1, y1, rect.width, rect.height);
        }

        for(Bullet b : world.getPlayer().getWeapon().getBullets()) {
            Rectangle rect = b.getBounds();
            float x1 = rect.x;
            float y1 = rect.y;
            debugRenderer.setColor(new Color(1, 0, 0, 1));
            debugRenderer.rect(x1, y1, rect.width, rect.height);
        }

        Player player = world.getPlayer();
        Rectangle rect = player.getBounds();
        float x1 = rect.x;
        float y1 = rect.y;
        debugRenderer.setColor(new Color(0, 1, 0, 1));
        debugRenderer.rect(x1, y1, rect.width, rect.height);
        debugRenderer.end();
    }

}
