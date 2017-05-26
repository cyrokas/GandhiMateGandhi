package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Hittable;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Mobile;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;

/**
 * Created by codecadet on 24/05/17.
 */
public abstract class Enemy extends GameObject implements Mobile, Hittable{
    private int health;
    private boolean dead;
    private Direction currentDirection;
    private FieldPosition pos;
    private Field field;
    private EnemyType enemyType;

    public Enemy(int health, FieldPosition pos){

        this.pos = pos;
        this.health = health;
        dead = false;

        currentDirection = Direction.values()[(int)(Math.random()*Direction.values().length)];

        //test
        System.out.println(currentDirection.toString());
        System.out.println("health "+health);
    }


    //getters
    public int getHealth(){
        return health;
    }

    public Direction getCurrentDirection(){
        return currentDirection;
    }

    public FieldPosition getPos(){
        return pos;
    }

    public EnemyType getEnemyType(){
        return enemyType;
    }

    @Override
    public boolean isDead(){
        return dead;
    }

    @Override
    public void playRound(){



    }

    @Override
    public void hit(int damage){

        if(!dead){
            health -= damage;
        }
        if(health <= 0){
            dead = true;
        }
    }

    //implement method depending on player position
    @Override
    public Direction chooseDirection(){
        return null;}


    public Direction chooseDirection(FieldPosition playerpos){

        Direction vertical=null;

        Direction horiz=null;

        if (playerpos.getCol() > pos.getCol()   ){

            horiz=Direction.RIGHT;

        } else if (playerpos.getCol() < pos.getCol()){
            horiz=Direction.LEFT;
        }
        if (playerpos.getRow() > pos.getRow()){
            vertical=Direction.DOWN;

        } else if (playerpos.getRow() < pos.getRow()){
            vertical=Direction.UP;
        }
        if(horiz == null) {
            return vertical;}

        if(vertical== null){
            return horiz;}

        int rnd=(int)(Math.random()*2);

        switch (rnd){
            case 0:
                return horiz;
            case 1:
                return vertical;
        }
        return null;
    }


    @Override
    public void move(Direction direction, int speed){
        pos.moveInDirection(direction,speed);
    }

}
