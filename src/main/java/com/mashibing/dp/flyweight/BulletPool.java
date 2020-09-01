package com.mashibing.dp.flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 在计算机编程中，flyweight是一种软件设计模式。
 * flyweight是通过与其他类似对象共享尽可能多的数据来最大程度减少内存使用的对象。
 * 当简单的重复表示使用不可接受的内存量时，这是大量使用对象的方法。
 * 通常，对象状态的某些部分可以共享，通常的做法是将它们保存在外部数据结构中，并在使用它们时将它们临时传递给对象
 */
class Bullet{
    public UUID id = UUID.randomUUID();
    boolean living = true;

    @Override
    public String toString() {
        return "Bullet{" +
                "id=" + id +
                '}';
    }
}

public class BulletPool {
    List<Bullet> bullets = new ArrayList<>();
    {
        for(int i=0; i<5; i++) bullets.add(new Bullet());
    }

    public Bullet getBullet() {
        for(int i=0; i<bullets.size(); i++) {
            Bullet b = bullets.get(i);
            if(!b.living) return b;
        }

        return new Bullet();
    }

    public static void main(String[] args) {
        BulletPool bp = new BulletPool();

        for(int i=0; i<10; i++) {
            Bullet b = bp.getBullet();
            System.out.println(b);
        }
    }

}
