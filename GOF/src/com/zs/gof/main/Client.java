package com.zs.gof.main;
import com.zs.gof.six.*;

public class Client {

	/**
	 * 区分值传递和引用传递，类，接口，数组，字符串在java中都是引用传递，指向同一块内存的地址
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("大树要报考面包师");
		RestrationInfo restrationInfo1 = new RestrationInfo("大树");
		restrationInfo1.setBirthday("1993-11-15");
		restrationInfo1.setSchool("电子科技大学");
		restrationInfo1.setID("123456");
		restrationInfo1.Show();
	}
}
