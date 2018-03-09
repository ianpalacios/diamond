package com.ccir;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.ccri.Diamond;

public class BasicDiamondTest {

	/*
	 * Basic threshold tests
	 */
	@Test
	public void thresholdTest() {

		int initRow = 11;
		int initCol = 11;
		int[][] array = new int[initRow][initCol];
		
		int startRow = 5;
		int startCol = 5;		
		
		assertEquals(5, Diamond.calculate(array, startRow, startCol, 1));
		Diamond.print(array);
		
		assertEquals(13, Diamond.calculate(array, startRow, startCol, 2));
		Diamond.print(array);
		
		assertEquals(25, Diamond.calculate(array, startRow, startCol, 3));
		Diamond.print(array);
	}

	/*
	 * Test Cases for when the points are going off the map.
	 */
	@Test
	public void offsetTest() {

		int initRow = 11;
		int initCol = 11;
		int[][] array = new int[initRow][initCol];
		
		int startRow = 0;
		int startCol = 0;		
		
		assertEquals(3, Diamond.calculate(array, startRow, startCol, 1));
		Diamond.print(array);
		
		assertEquals(6, Diamond.calculate(array, startRow, startCol, 2));
		Diamond.print(array);
		
		assertEquals(10, Diamond.calculate(array, startRow, startCol, 3));
		Diamond.print(array);
	}
}
