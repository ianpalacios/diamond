package com.ccir;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.ccri.Diamond;

public class BasicDiamondTest {

	/*
	 * Basic threshold tests
	 * 
	 * TODO: Verify that the positions are correct too.
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
	 * This goes through iterations of rows and columns and verifies the calculations.
	 */
	@Test
	public void outOfBoundsTest() {

		int initRow = 5;
		int initCol = 5;		

		for(int i=0; i<initRow; i++) {
			
			for(int j=0; j<initCol; j++) {
			
				int[][] array = new int[initRow][initCol];
				
				assertEquals(13, Diamond.calculate(array, i, j, 2));
				Diamond.print(array);
				System.out.println("Iteration: [" + i + "][" + j + "] success");
			}
		}		
	}	
}
