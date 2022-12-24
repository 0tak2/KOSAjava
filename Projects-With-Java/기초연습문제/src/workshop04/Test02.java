package workshop04;

import java.util.ArrayList;

class Calc {
	int root;
	ArrayList<Integer> evenList = 
			new ArrayList<Integer>();
	int evenSum;
	
	Calc() {
		
	}
	
	public int getRoot() {
		return root;
	}

	public void setRoot(int root) {
		this.root = root;
	}

	public ArrayList<Integer> getEvenList() {
		return evenList;
	}

	public void setEvenList(ArrayList<Integer> evenList) {
		this.evenList = evenList;
	}

	public int getEvenSum() {
		return evenSum;
	}

	public void setEvenSum(int evenSum) {
		this.evenSum = evenSum;
	}

	Calc(int root) {
		this.root = root;
		calcEvens();
		calculate();
	}
	
	public void calcEvens() {
		for(int i=1; i<=root; i++) {
			if(i % 2 == 0)
				evenList.add(i);
		}
	}
	
	public int calculate() {
		int sum = 0;
		for (int i : evenList)
			sum += i;
		this.evenSum = sum;
		return sum;
	}
}

public class Test02 {
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		Calc calc = new Calc(num);
		
		System.out.print("짝수: ");
		ArrayList<Integer> evenList = calc.getEvenList();
		for(int i : evenList)
			System.out.print(i + " ");
		
		System.out.println();
		System.out.println("결과: " + calc.getEvenSum());
	}
}
