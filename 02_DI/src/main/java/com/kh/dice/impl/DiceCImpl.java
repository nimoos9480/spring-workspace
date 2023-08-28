package com.kh.dice.impl;

import java.util.Random;

import com.kh.dice.Dice;

public class DiceCImpl implements Dice {

private int value;
	
	public DiceCImpl() {
		
		System.out.println(getClass().getName() + "생성자..");
	}

	@Override
	public void selectedNumber() {
		value = new Random().nextInt(6) + 1;
		
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

}
