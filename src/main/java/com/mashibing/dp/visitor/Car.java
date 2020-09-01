package com.mashibing.dp.visitor;

/**
 * @ClassName Car
 * @Description
 * 访问者模式(Visitor Pattern)模式是行为型(Behavioral)设计模式，
 * 提供一个作用于某种对象结构上的各元素的操作方式，可以使我们在不改变元素结构的前提下，定义作用于元素的新操作。
 * 换言之，如果系统的数据结构是比较稳定的，但其操作（算法）是易于变化的，
 * 那么使用访问者模式是个不错的选择；如果数据结构是易于变化的，则不适合使用访问者模式。
 * @Author fengxiaoxiao
 * @Date 2020/8/31 18:38
 * @Version 1.0
 */
public class Car {
	CarPart engine = new Engine();
	CarPart tires = new Tires();

	public void accept(Vistor vistor) {
		engine.accept(vistor);
		tires.accept(vistor);
	}

	public static void main(String[] args) {
		PersonVistor personVistor = new PersonVistor();
		new Car().accept(personVistor);
		System.out.println(personVistor.money);
	}
}


abstract class CarPart {
	abstract void accept(Vistor vistor);
	abstract double getPrice();
}

class Engine extends CarPart {

	@Override
	void accept(Vistor vistor) {
		vistor.visitEngine(this);
	}

	@Override
	double getPrice() {
		return 100d;
	}
}

class Tires extends CarPart {

	@Override
	void accept(Vistor vistor) {
		vistor.visitTires(this);
	}

	@Override
	double getPrice() {
		return 200;
	}
}

interface Vistor {
	void visitEngine(Engine engine);

	void visitTires(Tires tires);

}

class PersonVistor implements Vistor {
	double money = 0d;

	@Override
	public void visitEngine(Engine engine) {
		money += engine.getPrice();
	}

	@Override
	public void visitTires(Tires tires) {
		money += tires.getPrice();
	}
}