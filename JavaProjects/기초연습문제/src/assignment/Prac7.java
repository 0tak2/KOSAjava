package assignment;

import java.util.ArrayList;
import java.util.List;

public class Prac7 {

	public static void main(String[] args) {
		double A, B, C, D, E;
		double score;
		String level;
		
		List<Double> numbers = new ArrayList<Double>(5);
		
		if (args.length != 5) {
			System.out.println("다시 입력하세요.");
			return;
		}
		
		for(String input : args) {
			Double number = Double.parseDouble(input);
			
			if (number < 10 && number > 99) {
				System.out.println("다시 입력하세요.");
				return;
			}
			
			numbers.add(number);
		}
		
		A = numbers.get(0);
		B = numbers.get(1);
		C = numbers.get(2);
		D = numbers.get(3);
		E = numbers.get(4);
		
		score = ((A+B)/2)*0.6 + ((C+D)/2) * 0.2 + E * 0.2;
		if (score >= 90) level = "Gold";
		else if (score >= 80) level = "Silver";
		else if (score >= 70) level = "Bronze";
		else level = "Normal";
		
		
		System.out.println("평가 점수: " + score);
		System.out.println("Class: " + level + " Class");
		
	}
}
