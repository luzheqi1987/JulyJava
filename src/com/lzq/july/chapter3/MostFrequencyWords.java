/**
 * 
 */
package com.lzq.july.chapter3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.lzq.july.util.Trie;

/**
 * @author LuZheqi
 * 
 */
public class MostFrequencyWords {
	/**
	 * 整理堆，使其从开始的堆节点开始向下满足堆的性质
	 * 
	 * @param startNumber
	 *            开始整理的堆节点
	 * @param kNumbers
	 *            需要整理的堆
	 */
	void adjustHeap(int startNumber, WordEntry[] wordEntrys) {
		int length = wordEntrys.length;
		int i = startNumber;
		int leftIndex = (2 * i) + 1;
		int rightIndex = (2 * i) + 2;
		while (leftIndex < length || rightIndex < length) {
			int left = (leftIndex < length) ? wordEntrys[leftIndex].count
					: Integer.MAX_VALUE;
			int right = (rightIndex < length) ? wordEntrys[rightIndex].count
					: Integer.MAX_VALUE;
			if (Math.min(left, right) < wordEntrys[i].count) {
				if (left < right) {
					WordEntry tmp = wordEntrys[leftIndex];
					wordEntrys[leftIndex] = wordEntrys[i];
					wordEntrys[i] = tmp;
					i = leftIndex;
				} else {
					WordEntry tmp = wordEntrys[rightIndex];
					wordEntrys[rightIndex] = wordEntrys[i];
					wordEntrys[i] = tmp;
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
	void initHeap(WordEntry[] wordEntrys) {
		int startIndex = (wordEntrys.length - 1) / 2;
		for (int i = startIndex; i >= 0; i--) {
			adjustHeap(i, wordEntrys);
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
	void insertHeap(WordEntry[] wordEntrys, WordEntry wordEntry) {
		if (wordEntry.count <= wordEntrys[0].count) {
			return;
		}
		wordEntrys[0] = wordEntry;
		adjustHeap(0, wordEntrys);
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
	WordEntry[] kMaxWords(ArrayList<WordEntry> wordEntrys, int k) {
		if (k >= wordEntrys.size()) {
			WordEntry[] entrys = new WordEntry[wordEntrys.size()];
			for (int i = 0; i < wordEntrys.size(); i++) {
				entrys[i] = wordEntrys.get(i);
			}
			return entrys;
		}
		WordEntry[] entrys = new WordEntry[k];
		for (int i = 0; i < k; i++) {
			entrys[i] = wordEntrys.get(i);
		}
		initHeap(entrys);
		for (int i = k; i < wordEntrys.size(); i++) {
			insertHeap(entrys, wordEntrys.get(i));
		}
		return entrys;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Trie<String> trie = new Trie<String>();
		File sourceFile = new File("./dat/harrypotter");
		FileInputStream fin = new FileInputStream(sourceFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(fin));
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<WordEntry> wordEntrys = new ArrayList<WordEntry>();
		MostFrequencyWords mfw = new MostFrequencyWords();

		String s = null;
		while ((s = br.readLine()) != null) {
			String[] ss = s.split(" ");
			for (String str : ss) {
				str.trim();
				if (!trie.contains(str)) {
					words.add(str);
				}
				trie.add(str);
			}
		}
		br.close();

		for (String str : words) {
			wordEntrys.add(new WordEntry(str, trie.getCount(str)));
		}

		WordEntry mostWords[] = mfw.kMaxWords(wordEntrys, 10);
		for (WordEntry word : mostWords) {
			System.out.println(word.word + " " + word.count);
		}
	}

	protected static class WordEntry {
		String word = null;
		int count = 0;

		public WordEntry(String word, int count) {
			this.word = word;
			this.count = count;
		}
	}
}
