package com.zs.gof.main;
import com.zs.gof.six.*;

public class Client {

	public static void main(String[] args) {
		System.out.println("大树要报考面包师");
		RestrationInfo restrationInfo1 = new RestrationInfo("大树");
		restrationInfo1.setBirthday("1993-11-15");
		restrationInfo1.setSchool("电子科技大学");
		restrationInfo1.setID("123456");
		restrationInfo1.Show();
		System.out.println("大树要报考厨师");
		RestrationInfo restrationInfo2 = restrationInfo1;
		restrationInfo2.setSchool("中国计量大学");
		restrationInfo2.Show();
		
	}

}
