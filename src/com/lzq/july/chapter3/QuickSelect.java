/**
 * 
 */
package com.lzq.july.chapter3;

/**
 * @author LuZheqi
 * 
 */
public class QuickSelect {
	int quickSelect(int[] numbers, int k) {
		if (k <= 0 || k >= numbers.length) {
			return Integer.MIN_VALUE;
		}
		return quickSelectIter(numbers, k, 0, numbers.length - 1);
	}

	int quickSelectIter(int[] numbers, int k, int from, int to) {
		if (from == to) {
			return numbers[from];
		}
		int i = from + 1;
		int j = to;
		while (i < j) {
			while (i < j && numbers[i] <= numbers[from]) {
				i++;
			}
			while (i < j && numbers[j] >= numbers[from]) {
				j--;
			}
			if (i < j) {
				int tmp = numbers[j];
				numbers[j] = numbers[i];
				numbers[i] = tmp;
			}
		}
		int mid = (numbers[i] > numbers[from]) ? i - 1 : i;
		int tmp = numbers[mid];
		numbers[mid] = numbers[from];
		numbers[from] = tmp;
		if (mid == k - 1) {
			return numbers[mid];
		} else if (mid < k - 1) {
			return quickSelectIter(numbers, k, mid + 1, to);
		} else {
			return quickSelectIter(numbers, k, from, mid - 1);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickSelect qs = new QuickSelect();
		System.out.println(qs.quickSelect(
				new int[] { 3, 6, 4, 7, 1, 2, 5, 9, 8 }, 1));
		System.out.println(qs.quickSelect(
				new int[] { 3, 6, 4, 7, 1, 2, 5, 9, 8 }, 2));
		System.out.println(qs.quickSelect(
				new int[] { 3, 6, 4, 7, 1, 2, 5, 9, 8 }, 3));
		System.out.println(qs.quickSelect(
				new int[] { 3, 6, 4, 7, 1, 2, 5, 9, 8 }, 4));
		System.out.println(qs.quickSelect(
				new int[] { 3, 6, 4, 7, 1, 2, 5, 9, 8 }, 5));
		System.out.println(qs.quickSelect(
				new int[] { 3, 6, 4, 7, 1, 2, 5, 9, 8 }, 6));
		System.out.println(qs.quickSelect(
				new int[] { 3, 6, 4, 7, 1, 2, 5, 9, 8 }, 7));
		System.out.println(qs.quickSelect(
				new int[] { 3, 6, 4, 7, 1, 2, 5, 9, 8 }, 8));
		System.out.println(qs.quickSelect(
				new int[] { 3, 6, 4, 7, 1, 2, 5, 9, 8 }, 9));
	}

}
