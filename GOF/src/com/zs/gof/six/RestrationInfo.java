package com.zs.gof.six;


//报名信息类
public class RestrationInfo implements Cloneable {

	private String name;
	private String birthday;
	private String school;
	private String ID;
	private Nationality nationality;
	//设置生日
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	//设置毕业院校
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	//设置身份证号
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public RestrationInfo(String name) {
		this.name = name;
		nationality = new Nationality();
	}
	//设置国籍
	public void setNation(String nation){
		nationality.setNation(nation);
	}
	public void Show(){
		System.out.println("姓名:"+name);
		System.out.println("毕业院校:"+school);
		System.out.println("出生年月:"+birthday);
		System.out.println("身份证号:"+ID);
		System.out.println("国籍:"+nationality.getNation());
	}
	//实现Cloneable接口的clone方法
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		System.out.println("大树要报考面包师");
		RestrationInfo restrationInfo1 = new RestrationInfo("大树");
		restrationInfo1.setBirthday("1993-11-15");
		restrationInfo1.setSchool("电子科技大学");
		restrationInfo1.setID("123456");
		restrationInfo1.setNation("中国");
		restrationInfo1.Show();
		System.out.println("=======================");
		System.out.println("大树要报考厨师");
		//clone出来的不是指向同一块内存, == 和 equals判断都为false,在不重新初始化对象的情况下动态获得对象在运行时的状态
		RestrationInfo restrationInfo2 = (RestrationInfo) restrationInfo1.clone();
		restrationInfo2.setNation("意大利");
		restrationInfo2.Show();
	}
}
