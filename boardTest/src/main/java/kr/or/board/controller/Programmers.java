package kr.or.board.controller;

import java.util.Arrays;

public class Programmers {
	public static void main(String[] args) {
		
		int[]ex = {1, 5, 2, 6, 3, 7, 4}; 
		
		int a = 1;
		int b = 7;
		int	c = 3;
		
		int[]array = {a, b, c};
		int[][] commands;
		
		
		
		int answerIndex = array[1] - array[0] + 1;
		
		int[]answer = new int[answerIndex];
		
		for (int i = array[0]-1; i < array[1]; i++) {
			int index = i - (array[0]-1);
			answer[index] = ex[i];
		}
		
		Arrays.sort(answer);
		
		System.out.println(answer[array[2]-1]);
		
	}
}
