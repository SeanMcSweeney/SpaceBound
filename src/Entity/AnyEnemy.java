// commented *

package Entity;

// imports

import TileMap.SquareMap;

public class AnyEnemy extends AnyEntity 
{
	
	// declare variables
	// long is used here as the size of the variable may be bigger than an int
	// protected so that variables cannot be declared outside of the package
	
	protected int enemyHealth;
	protected int enemyMaxHealth;
	protected boolean enemyDead;
	protected int enemyDamage;
	protected boolean enemyHurt;
	protected long enemyHurtTimer;
	
	// takes everything in SquareMap and allows it to be used
	
	public AnyEnemy(SquareMap sm) 
	{
		super(sm);
	}
	
	// if the enemy is dead method is true return enemyDead
	
	public boolean isEnemyDead() 
	{ 
		return enemyDead; 
	}
	
	// return the enemy damage
	
	public int getEnemyDamage() 
	{ 
		return enemyDamage; 
	}
	
	// method checks if enemy should be dead or just hurt
	// takes away the damage
	// if enemy health is 0 or less enemy is dead
	// if not enemy is just hurt
	
	public void damage(int d) 
	{
		if(enemyDead || enemyHurt) return;
		enemyHealth -= d;
		if(enemyHealth < 0) enemyHealth = 0;
		if(enemyHealth == 0) enemyDead = true;
		enemyHurt = true;
		enemyHurtTimer = System.nanoTime();
	}
	
	public void update(){}

	// returns the enemies health
	
	public int getEnemyHealth() 
	{
		return enemyHealth;
	}

	// sets the enemy health
	
	public void setEnemyHealth(int enemyHealth) 
	{
		this.enemyHealth = enemyHealth;
	}
	
}














