/**
 * 
 */
package com.lzq.july.chapter3;

/**
 * @author LuZheqi
 * 
 */
public class TheMinKNumber {
	/**
	 * 整理堆，使其从开始的堆节点开始向下满足堆的性质
	 * 
	 * @param startNumber
	 *            开始整理的堆节点
	 * @param kNumbers
	 *            需要整理的堆
	 */
	void adjustHeap(int startNumber, int[] kNumbers) {
		int length = kNumbers.length;
		int i = startNumber;
		int leftIndex = (2 * i) + 1;
		int rightIndex = (2 * i) + 2;
		while (leftIndex < length || rightIndex < length) {
			int left = (leftIndex < length) ? kNumbers[leftIndex]
					: Integer.MIN_VALUE;
			int right = (rightIndex < length) ? kNumbers[rightIndex]
					: Integer.MIN_VALUE;
			if (Math.max(left, right) > kNumbers[i]) {
				if (left > right) {
					int tmp = left;
					kNumbers[leftIndex] = kNumbers[i];
					kNumbers[i] = tmp;
					i = leftIndex;
				} else {
					int tmp = right;
					kNumbers[rightIndex] = kNumbers[i];
					kNumbers[i] = tmp;
					i = rightIndex;
				}
				leftIndex = (2 * i) + 1;
				rightIndex = (2 * i) + 2;
			} else {
				break;
			}
		}
	}

	/**
	 * 初始化堆
	 * 
	 * @param kNumbers
	 *            未被初始化的堆
	 */
	void initHeap(int[] kNumbers) {
		int startIndex = (kNumbers.length - 1) / 2;
		for (int i = startIndex; i >= 0; i--) {
			adjustHeap(i, kNumbers);
		}
	}

	/**
	 * 往堆中插入数据
	 * 
	 * @param kNumbers
	 *            堆
	 * @param newValue
	 *            插入的数值
	 */
	void insertHeap(int[] kNumbers, int newValue) {
		if (newValue >= kNumbers[0]) {
			return;
		}
		kNumbers[0] = newValue;
		adjustHeap(0, kNumbers);
	}

	/**
	 * 输入一个数组和整数k，从中找到最小的k个数，例如输入数组为A =
	 * {1,9,2,4,7,6,3}，整数k为3，则输出最小的3个数分别为{1,2,3}。
	 * 
	 * @param numbers
	 *            数组
	 * @param k
	 *            k
	 * @return 最小的k个数
	 */
	int[] kMinNumbers(int[] numbers, int k) {
		if (k >= numbers.length) {
			return numbers;
		}
		int[] kNumbers = new int[k];
		for (int i = 0; i < k; i++) {
			kNumbers[i] = numbers[i];
		}
		initHeap(kNumbers);
		for (int i = k; i < numbers.length; i++) {
			insertHeap(kNumbers, numbers[i]);
		}
		return kNumbers;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TheMinKNumber tmk = new TheMinKNumber();
		int[] init = new int[] { 1, 5, 2, 7, 8, 9, 4, 2, 52, 75, 31 };
		// int[] init = new int[] { 1, 2, 3, 4, 5, 6, 7};
		int[] result = tmk.kMinNumbers(init, 3);
		for (int i : result) {
			System.out.print(i + " ");
		}

	}

}
