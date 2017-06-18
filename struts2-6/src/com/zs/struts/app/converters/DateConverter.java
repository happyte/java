package com.zs.struts.app.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter {
	
	private DateFormat dateFormat;
	
	public DateConverter() {
		System.out.println("DateConverter constructor .......");
		dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	}

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		System.out.println("convertFromString...");
		if(toClass == Date.class){
			if(values != null && values.length > 0){
				String value = values[0];
				try {
					return dateFormat.parseObject(value);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		//若没有转换成功, 则返回 values
		return values;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		System.out.println("converting To String");
		if(arg1 instanceof Date){
			Date date = (Date)arg1;
			return dateFormat.format(date);
		}
		return null;
	}
	
}
