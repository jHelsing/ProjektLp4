package com.icyvenom.needforghetto.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.icyvenom.needforghetto.model.Bullet;
import com.icyvenom.needforghetto.model.Enemy;
import com.icyvenom.needforghetto.model.EnemyAwp;
import com.icyvenom.needforghetto.model.EnemyPistol;
import com.icyvenom.needforghetto.model.EnemyStalker;
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
    private Texture enemyPistolTexture;
    private Texture enemyAWPTexture;
    private Texture enemyStalkerTexture;
    private Texture bulletTexture;

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
    }

    /**
     * Calls the functions for drawing the objects on to the screen,
     * is called continuously.
     */
    public void render(){
        spriteBatch.begin();
            drawEnemies();
            drawPlayer();
            drawBullets();
        spriteBatch.end();
        if(debug){
            drawDebug();
        }
    }

    /**
     * Draws the player sprite on to the screen.
     */
    public void drawPlayer(){
        Player player = world.getPlayer();
        spriteBatch.draw(playerTexture, player.getPosition().x * ppuX, player.getPosition().y * ppuY,
                player.SIZE * ppuX, player.SIZE * ppuY);
    }

    /**
     * Draws the enemy sprites on to the screen.
     */
    public void drawEnemies(){
        for (Object e : world.getEnemies()) {
            Enemy enemy = (Enemy) e;
            if (enemy instanceof EnemyPistol) {
                spriteBatch.draw(enemyPistolTexture, enemy.getPosition().x * ppuX, enemy.getPosition().y * ppuY,
                        enemy.SIZE * ppuX, enemy.SIZE * ppuY);
            }else if (enemy instanceof EnemyAwp){
                spriteBatch.draw(enemyAWPTexture, enemy.getPosition().x * ppuX, enemy.getPosition().y * ppuY,
                        enemy.SIZE * ppuX, enemy.SIZE * ppuY);
            }else if (enemy instanceof EnemyStalker){
                spriteBatch.draw(enemyStalkerTexture, enemy.getPosition().x * ppuX, enemy.getPosition().y * ppuY,
                        enemy.SIZE * ppuX, enemy.SIZE * ppuY);
            }
        }
    }

    /**
     * Draws the bullets on to the screen.
     * Flips the texture of the enemy bullets.
     */
    public void drawBullets(){
        if(!world.getPlayer().getWeapon().getBullets().isEmpty()) {
            for (Bullet b : world.getPlayer().getWeapon().getBullets()) {
                spriteBatch.draw(bulletTexture, b.getPosition().x * ppuX, b.getPosition().y * ppuY,
                        b.SIZE * ppuX, b.SIZE * ppuY);
            }
        }
        if(!world.getEnemies().isEmpty()) {
            for(Enemy e: world.getEnemies()){
                if(!e.getWeapon().getBullets().isEmpty()){
                    for (Bullet b : e.getWeapon().getBullets()) {
                        spriteBatch.draw(bulletTexture, b.getPosition().x * ppuX, b.getPosition().y
                                * ppuY, b.SIZE * ppuX, b.SIZE * ppuY, 0, 0, bulletTexture.getWidth(),
                                bulletTexture.getHeight(), false, true);
                    }
                }
            }
        }
        if(!world.getBullets().isEmpty()) {
            for(Bullet b : world.getBullets()){
                spriteBatch.draw(bulletTexture, b.getPosition().x * ppuX, b.getPosition().y
                        * ppuY, b.SIZE * ppuX, b.SIZE * ppuY, 0, 0, bulletTexture.getWidth(),
                         bulletTexture.getHeight(), false, true);
            }
        }
    }

    /**
     * Translate pixels to the defined Camera layout.
     *
     * @param w
     * @param h
     */
    public void setSize(int w, int h){
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
    }

    /**
     * Loads the correct textures into the memory for drawing purposes.
     */
    public void loadTextures(){
        playerTexture = new Texture(Gdx.files.internal("images/car.png"));
        enemyPistolTexture = new Texture(Gdx.files.internal("images/enemyPistol.png"));
        enemyAWPTexture = new Texture(Gdx.files.internal("images/enemyAWP.png"));
        enemyStalkerTexture = new Texture(Gdx.files.internal("images/enemyStalker.png"));
        bulletTexture = new Texture(Gdx.files.internal("images/bullet.png"));
    }

    public OrthographicCamera getCamera(){
        return cam;
    }

    /**
     * Function for debugging, draws additional rectangles which show the actual hitboxes and
     * position of the objects in the game.
     */
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

        for(Enemy e : world.getEnemies()) {
            for(Bullet b : e.getWeapon().getBullets()){
                Rectangle rect = b.getBounds();
                float x1 = rect.x;
                float y1 = rect.y;
                debugRenderer.setColor(new Color(1, 0, 0, 1));
                debugRenderer.rect(x1, y1, rect.width, rect.height);
            }
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
