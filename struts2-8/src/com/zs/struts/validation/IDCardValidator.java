package com.zs.struts.validation;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class IDCardValidator extends FieldValidatorSupport{

	@Override
	public void validate(Object object) throws ValidationException {
		String fieldName = getFieldName();
		Object value = this.getFieldValue(fieldName, object);
		IDCard iCard = new IDCard();
		boolean result = iCard.Verify((String)value);
		if(!result){
			addFieldError(fieldName, object);
		}	
	}

}
