package com;

public class VisualNuts {

	public static void main(String[] args) {
		
		for(int i = 1; i <= 100; i++) {
			
			if(i % 15 == 0) {
				System.out.print("Visual Nuts");
			} else if(i % 5 == 0) {
				System.out.print("Nuts");
			} else if(i % 3 == 0) {
				System.out.print("Visual");
			} else {
				System.out.print(i);
			}
			System.out.print(",");
		}

	}

}
