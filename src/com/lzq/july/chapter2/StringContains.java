/**
 * 
 */
package com.lzq.july.chapter2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LuZheqi
 * 
 */
public class StringContains {
	/**
	 * 假设这有两个分别由字母组成的字符串A另外字符串B，字符串B的字母数较字符串A少一些。
	 * 什么方法能最快地查出字符串B所有字母是不是都在字符串A里？也就是说判断字符串B是不是字符串A的真子集
	 * （为了简化，姑且认为两个集合都不是空集，即字符串都不为空。）。为了简单起见，我们规定输入的字符串只包含大写英文字母。
	 * 
	 * 我的理解是这两个字符串里的字符是可以重复的，不像原题目中那样（字母是不重复的）
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public boolean stringContains(String A, String B) {
		if (null == B || "".equals(B)) {
			return true;
		} else if ((null == A || "".equals(A)) || (A.length() < B.length())) {
			return false;
		}
		char[] as = A.toCharArray();
		char[] bs = B.toCharArray();
		Map<Character, Integer> wordsMap = new HashMap<Character, Integer>();
		for (Character c : as) {
			if (wordsMap.containsKey(c)) {
				wordsMap.put(c, wordsMap.get(c) + 1);
			} else {
				wordsMap.put(c, 1);
			}
		}
		for (Character c : bs) {
			if (wordsMap.containsKey(c)) {
				int number = wordsMap.get(c);
				if (number == 1) {
					wordsMap.remove(c);
				} else {
					wordsMap.put(c, number - 1);
				}
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringContains sc = new StringContains();
		System.out.println(sc.stringContains("ABCDEFGHLMNOPQRS", "DCGSRQPO"));
		System.out.println(sc.stringContains("ABCDEFGHLMNOPQRS", "DCGSRQPZ"));
	}

}
