package test.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Scanner;

public class Sum {

	public static class Lottery {

		public int[] getResult() {

			int a[] = new int[7];
			// 获奖号码
			for (int i = 0; i < 6; i++) {
				while (true) {
					int k = 0;
					a[i] = (int) (Math.random() * 33) + 1;
					for (int j = 0; j < i; j++) {
						if (a[i] == a[j]) {
							k = 1;
						}
					}
					if (k == 0) {
						break;
					}
				}
			}

			a[6] = (int) (Math.random() * 16) + 1;
			// 给开奖号码排序
			for (int i = 0; i < 5; i++) {
				for (int j = i + 1; j < 6; j++) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}

			return a;
		}

		// 获得卖号
		public int[] getko() {
			int b[] = new int[7];
			Scanner sc = new Scanner(System.in);

			for (int i = 0; i < 6; i++) {
				System.out.println("请输入一个(1-33)之间的数：");
				while (true) {
					int k = 0;
					b[i] = sc.nextInt();
					if (b[i] < 1 || b[i] > 33) {
						System.out.println("超出了双色球的范围，请你输入一个在范围内的号码1！");
						break;
					}
					for (int j = 0; j < i; j++) {
						if (b[i] == b[j]) {
							System.out.println("超出了双色球的范围，请你输入一个在范围内的号码2！");
							k = 1;
						}
					}
					if (k == 0) {
						break;
					}
				}
			}
			System.out.println("请输入一个(1—16)之间的数：");
			while (true) {
				b[6] = sc.nextInt();

				if (b[6] < 1 || b[6] > 16) {
					System.out.println("超出了双色球的范围，请你输入一个在范围内的号码3！");
				} else {

					break;
				}
			}
			for (int i = 0; i < 5; i++) {
				for (int j = i + 1; j < 6; j++) {
					int temp = b[i];
					b[i] = b[j];
					b[j] = temp;
				}
			}
			// 输出购买号码
			System.out.println("你购买的号码为：");
			for (int i = 0; i < 7; i++) {
				System.out.print(b[i] + "  ");
			}
			System.out.println("");

			return b;
		}

		public void getRs() {
			int[] a = getResult();
//			int[] b = getko();

			System.out.println("今日的开奖号码为：");
			for (int i = 0; i < 7; i++) {
				System.out.print(a[i] + " ");
				System.out.print("");
			}

//			int k = 0;
//			for (int j = 0; j < 6; j++) {
//				for (int i = 0; i < 6; i++) {
//					if (a[j] == b[i]) {
//						k++;
//					}
//				}
//			}
//			if (k == 6 && a[6] == b[6]) {
//				System.out.println("恭喜恭喜！五百万你可以拿回家了！");
//			} else if (k == 6) {
//				System.out.println("恭喜，你获得二等奖！");
//			} else if (k == 5 && a[6] == b[6]) {
//				System.out.println("恭喜，你获得三等奖！");
//			} else if (k == 5 || k == 4 && a[6] == b[6]) {
//				System.out.println("恭喜，你获得四等奖！");
//			} else if (k == 4 || k == 3 && a[6] == b[6]) {
//				System.out.println("恭喜，你获得五等奖！");
//			} else if (k == 3 || k == 2 && a[6] == b[6]) {
//				System.out.println("恭喜，你获得六等奖！");
//			} else {
//				System.out.println("不好意啦！手气不佳！");
//			}

		}

		public static void main(String[] args) {

			new Lottery().getRs();
		}
	}
}
