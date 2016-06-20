package map.filter.reduce;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class MapFilterReduce {
	
	public static int reduce (
			List<Integer> values,
			int valueIfEmpty,
			BinaryOperator<Integer> reduction
			) {
		
		int result = valueIfEmpty;
		
		for (int value: values) {
			result = reduction.apply(result, value);
		}
		return result;
	}

	public static void main(String[] args) {
		
		List<Integer> ints = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
		
		List<Integer> ints1 = Arrays.asList(0,1,2,3,4);
		List<Integer> ints2 = Arrays.asList(5,6,7,8,9);
		
		BinaryOperator<Integer> sum = (n1, n2) -> n1 + n2;
		BinaryOperator<Integer> max  = (n1, n2) -> Integer.max(n1, n2);
		BinaryOperator<Integer> newOp  = (n1, n2) -> (n1 + n2) * (n1 + n2);
		BinaryOperator<Integer> returnFirst = (n1, n2) -> n1;
		BinaryOperator<Integer> average = (n1, n2) -> (n1 + n2)/2;
		
		//0 is used here as it is identity element of sum
		int reduction1 = reduce(ints1, 0, sum);
		int reduction2 = reduce(ints2, 0, sum);
		int reductionBefore = reduce(ints, 0, sum);
		
		//Reduction works for sum
		int reduction = reduce(Arrays.asList(reduction1, reduction2), 0, sum);
		
		int reduction3 = reduce(ints1, 0, max);
		int reduction4 = reduce(ints2, 0, max);
		int reductionBeforeMax = reduce(ints, 0, max);
		int maxReduction = reduce(Arrays.asList(reduction3, reduction4), 0, max);
		
		//Reduction for new op
		int reduction5 = reduce(ints1, 0, newOp);
		int reduction6 = reduce(ints2, 0, newOp);
		int newopReduction = reduce(Arrays.asList(reduction5, reduction6), 0, newOp);
		
		System.out.println("Reduciton: "+ reductionBefore);
		System.out.println("Reduciton: "+ reduction);
		
		//Works for max too as it is associative
		System.out.println("Max Reduciton: "+ reductionBeforeMax);
		System.out.println("Max Reduciton: "+ maxReduction);
		
		//Try for non associative 
		int reductionWithwholeArray = reduce(ints, 0, newOp);
		System.out.println("New op Reduciton with whole list: "+ reductionWithwholeArray);
		System.out.println("New op Reduciton: "+ newopReduction);
		//So doesn't work as it's not associative, no compile or tun time errors but result is wrong
		
		//Return first parameter
		int reduction7 = reduce(ints1, 0, returnFirst);
		int reduction8 = reduce(ints2, 0, returnFirst);
		int wholeArray = reduce(ints, 0, returnFirst);
		
		//Reduction works for sum
		int reductionReturnFrist = reduce(Arrays.asList(reduction7, reduction8), 0, returnFirst);
		System.out.println("Return first Reduciton: "+ wholeArray);
		System.out.println("Return first Reduciton: "+ reductionReturnFrist);
		
	
		//Reduction works for sum
		
		int reduction9 = reduce(ints1, 0, average);
		int reduction10 = reduce(ints2, 0, average);
		int individual = reduce(ints, 0, average);
		
		int parallel = reduce(Arrays.asList(reduction9, reduction10), 0, average);
		System.out.println("Avg Reduciton: "+ individual);
		System.out.println("Avg parallel Reduciton: "+ parallel);

	}

}
