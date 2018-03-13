package com.ccri;

/*
 * Write a function that takes a 2-dimensional array of integers as well as a distance threshold (I'll call it n), 
 * and returns the number of cells in the array that are within n steps of a positive value. 
 * Here "steps" means just up/down/left/right steps in the grid.
 * 
 * NEW FEATURE: 
 * If the value of a cell goes out of bounds, have it wrap around and display on the other side of the array.
 * See update to drawRow.
 * 
 */
public class Diamond {

	/*
	 * Setup of the 2 dimensional array, starting point and threshold. 
	 * The call to calculate runs the process and returns the total number of cells.
	 */
	public static void main(String[] args) {
		
		// WARNING: Array Size must have the same number of rows and columns or the system may throw ArrayIndexOutOfBoundsException
		int initRow = 101;
		int initCol = 101;
		int[][] array = new int[initRow][initCol];
		
		int startRow = 0;
		int startCol = 0;
		
		int n = 50;	
				
		int total = calculate(array, startRow, startCol, n);
		
		System.out.println("\n\n Total: " + total);
		
		print(array);
	}
	
	public static int calculate(int[][] array, int startRow, int startCol, int n) {
		
		drawRow(array, startRow, startCol, n);		
		drawVertical('+', array, startRow, startCol, n);
		drawVertical('-', array, startRow, startCol, n);		
		
		return count(array);		
	}
	
	/*
	 * This method takes a starting point on an array [startRow & startCol] and plots a row based on the threshold n.
	 * Starting from the center column point: it first goes to the right, and then starts over again from the center to complete the left side.
	 * 
	 * Since the array defaults to zeros, the 1 digits will print the pattern over top.
	 * 
	 * If the value of a cell goes out of bounds, have it wrap around and display on the other side of the array.
	 * (Previous code commented out below for easy comparison).
	 * 
	 * TODO:
	 * 1. The try/catch blocks were handy to avoid ArrayIndexOutOfBoundsException when going off the map.
	 *    Checks could easily be put into place instead.
	 *    
	 * 2. I might have made the for conditions a little more complex than needed here. Come back to simplify.
	 *  
	 */
	public static void drawRow(int[][] array, int startRow, int startCol, int n) {
		
		int displayInt = 1;
		
		int d = startCol + n;
		// right		
		for(int i=startCol; i<=d; i++) 
		{
			if(startRow < 0 && i >= array.length) // UPPER RIGHT
			{				
				array[array[0].length + startRow][i % array.length] = displayInt;
			}
			else if(startRow < 0) // UP CENTERED
			{				
				array[array[0].length + startRow][i] = displayInt;
			}
			else if(startRow > array[0].length - 1 && i >= array.length) // LOWER RIGHT
			{
				array[startRow - array[0].length][i % array.length] = displayInt;
			}
			else if(startRow > array[0].length - 1) // LOWER CENTERED
			{
				array[startRow - array[0].length][i] = displayInt;
			}
			else if(i >= array.length) // JUST RIGHT
			{
				array[startRow][i % array.length] = displayInt;
			} 
			else // CENTERED
			{
				array[startRow][i] = displayInt;
			}
		}
		
		// left
		for(int i=startCol; i>(startCol - n - 1); i--) {
			
			if(startRow < 0 && i < 0) // UPPER LEFT
			{
				array[array[0].length + startRow][array.length + i] = displayInt;
			}
			else if(startRow < 0) // UP CENTERED			
			{
				array[array[0].length + startRow][i] = displayInt;
			}
			else if(startRow > array[0].length - 1 && i < 0) // LOWER LEFT
			{
				array[startRow - array[0].length][array.length + i] = displayInt;
			}
			else if(startRow > array[0].length - 1) // LOWER CENTERED
			{
				array[startRow - array[0].length][i] = displayInt;
			}
			else if(i < 0) // JUST LEFT
			{
				array[startRow][array.length + i] = displayInt;
			}
			else // CENTERED
			{
				array[startRow][i] = displayInt;
			}
		}		
		
		/* Original Setting
		int d = startCol + n;
		// right		
		for(int i=startCol; i<=d; i++) {
			try {
				array[startRow][i] = 1;
			}catch(Exception e) {
				System.out.println("\nDiamond - drawRow - right: " + e.getMessage());
			}
		}
		
		// left
		for(int i=startCol; i>(startCol - n - 1); i--) {
			try {
				array[startRow][i] = 1;
			}catch(Exception e) {
				System.out.println("\nDiamond - drawRow - left: " + e.getMessage());	
			}
		}
		*/
	}
	
	/*
	 * With the center line drawn as done in drawRow, we can reuse the method and simply shift up or down.
	 * (The direction takes + for filling in up, and - for going down.)
	 * 
	 *  By decrementing the threshold for each row that gets farther from the center,
	 *  and redrawing the lines shorter and shorter, the loop eventually meets zero and finishes.
	 */
	public static void drawVertical(char direction, int[][] array, int startRow, int startCol, int n) {
		int tempStartRow = startRow;
		while(n >= 0) {
			n--;
			if(direction == '+') {				
				tempStartRow--;
			}else if(direction == '-') {
				tempStartRow++;
			}
			drawRow(array, tempStartRow, startCol, n);
		}		
	}
		
	public static void print(int[][] array) {
		
		System.out.println("");
		
		for(int i=0; i<array.length; i++) {
			System.out.println("");
			for(int j=0; j<array[0].length; j++) {
				System.out.print(array[i][j]);
				System.out.print("|");
			}
		}
	}
	
	/*
	 * An easy way to count all the points is to just loop through the entire array and look for indicators left behind. In this case a 1.
	 */
	public static int count(int[][] array) {
		int total = 0;
		
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[0].length; j++) {
				if(array[i][j] == 1) {
					total++;
				}
			}
		}
		
		return total;
	}	
}