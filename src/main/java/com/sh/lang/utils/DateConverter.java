package com.sh.lang.utils;

import java.util.Date;

import org.apache.commons.beanutils.converters.DateTimeConverter;


public class DateConverter extends DateTimeConverter {
    /**
     * Construct a <b>java.util.Date</b> <i>Converter</i> that throws
     * a <code>ConversionException</code> if an error occurs.
     */
    public DateConverter() {
        super();
    }

    /**
     * Construct a <b>java.util.Date</b> <i>Converter</i> that returns
     * a default value if an error occurs.
     *
     * @param defaultValue The default value to be returned
     * if the value to be converted is missing or an error
     * occurs converting the value.
     */
    public DateConverter(Object defaultValue) {
        super(defaultValue);
    }

    /**
     * Return the default type this <code>Converter</code> handles.
     *
     * @return The default type this <code>Converter</code> handles.
     */
    protected Class getDefaultType() {
        return Date.class;
    }
	@Override
	protected String convertToString(Object value) throws Throwable {
		System.out.println("test date convertToString start");
//		if(value==null){
//			return null;
//		}
		return super.convertToString(value);
	}

	@Override
	protected Object convertToType(Class targetType, Object value)
			throws Exception {
		System.out.println("test date convertToType start"+value);
//		if(value==null){
//			return null;
//		}
//		String v=value.toString().trim();
//		if(v.length()==0){
//			return null;
//		}
		return super.convertToType(targetType, value);
	}
/**
 * 覆盖这个方法即可，如果value为空，跳出来（李强）
 */
	@Override
	public Object convert(Class type, Object value) {
		if(value==null){
			return null;
		}
		return super.convert(type, value);
	}

}
