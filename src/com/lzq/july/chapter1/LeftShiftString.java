/**
 * 
 */
package com.lzq.july.chapter1;

/**
 * @author LuZheqi
 * 
 */
public class LeftShiftString {
	String reverse(String src, int start, int end) {
		StringBuilder sb = new StringBuilder(src);
		while (start < end) {
			char t = sb.charAt(start);
			sb.setCharAt(start, sb.charAt(end));
			sb.setCharAt(end, t);
			start++;
			end--;
		}
		src = sb.toString();
		return sb.toString();
	}

	/**
	 * 左旋转字符串,定义字符串左旋转操作:把字符串前面的若干个字符移动到字符串尾部,如把字符串 abcdef 左旋转 2 位得到字符串
	 * cdefab。请实现字符串左旋转的函数,要求对长度为 n 的字符串操作的时间复杂度为 O(n),空间复杂度为 O(1)。
	 * 
	 * @param s
	 *            原始字符串
	 * @param n
	 *            旋转的位置，位置的起点为大于等于1，小于等于原始字符串的长度
	 * @return 旋转结束后的结果
	 */
	String leftShiftString(String s, int n) {
		assert (n >= 1 && n <= s.length());
		String result = reverse(s, 0, n - 1); // 旋转前一部分
		result = reverse(result, n, s.length() - 1); // 旋转后一部分
		result = reverse(result, 0, s.length() - 1); // 整体旋转
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new LeftShiftString().leftShiftString("abcdef", 2));
	}

}
