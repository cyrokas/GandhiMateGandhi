package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Hittable;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Movable;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;

/**
 * Created by codecadet on 24/05/17.
 */
public abstract class Enemy extends GameObject implements Movable, Hittable{
    private int health;
    private boolean isDead;
    private Direction currentDir;
    private FieldPosition pos;
    private Field field;
    private EnemyType enemyType;

    public Enemy(int health, FieldPosition pos){
        this.pos=pos;
        this.health=health;
        isDead=false;
        currentDir=Direction.values()[(int)Math.random()*Direction.values().length];
        System.out.println(currentDir.toString());
        System.out.println("health "+health);
    }


    //getters
    public int getHealth(){
        return health;
    }

    public Direction getCurrentDir(){
        return currentDir;
    }

    public FieldPosition getPos(){
        return pos;
    }

    public EnemyType getEnemyType(){
        return enemyType;
    }

    @Override
    public boolean isDead(){
        return isDead;
    }

    //What is this
    //void play();

    @Override
    public void hit(int damage){
        if(!isDead){
            health =health-damage;}
        if(health <=0){
            isDead=true;
        }
    }

    //implement method depending on player position
    @Override
    public Direction chooseDirection(){
        Direction newDir=currentDir;

        return currentDir;
    }

    @Override
    public void move(Direction direction, int speed1){
        switch (direction){
            case UP:

        }
    }

}
