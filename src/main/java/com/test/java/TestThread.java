package com.test.java;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestThread {
	public static void main(String[] args) {
        Set<Player> players = new HashSet<Player>();
        //实例化一个游戏
        Game game = new Game();
        
        //实例化3个玩家
        for (int i = 0; i < 3; i++)
            players.add(new Player(i, game));
        
        //启动3个玩家
        Iterator<Player> iter = players.iterator();
        while (iter.hasNext())
            new Thread(iter.next()).start();
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //游戏启动
        new Thread(game).start();
    }

}

/**
 * 玩游戏的人.
 * @version V1.0 ,2011-4-8
 * @author xiahui
 */
 class Player implements Runnable {
    private final int id;
    private Game game;

    public Player(int id, Game game) {
        this.id = id;
        this.game = game;
    }


    public String toString() {
        return "Athlete<" + id + ">";
    }

    public int hashCode() {
        return new Integer(id).hashCode();
    }
    
    public void playGame() throws InterruptedException{
        System.out.println(this.toString() + " ready!");
        game.play(this);
    }

    public void run() {
        try {
            playGame();
        } catch (InterruptedException e) {
            System.out.println(this + " quit the game");
        }
    }
}
 
 /**
  * 游戏类.
  * @version V1.0 ,2011-4-8
  * @author xiahui
  */
  class Game implements Runnable {
     private boolean start = false;

     public void play(Player player) throws InterruptedException {
         synchronized (this) {
             while (!start)
                 wait();
             if (start)
                 System.out.println(player + " have played!");
         }
     }

     //通知所有玩家
     public synchronized void beginStart() {
         start = true;
         notifyAll();
     }

     public void run() {
         start = false;
         System.out.println("Ready......");
         System.out.println("Ready......");
         System.out.println("game start");
         beginStart();//通知所有玩家游戏准备好了
     }

     
 }

