package br.com.springbatch.manip;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ReturnObject {
	
	public static String[] separateString(String txt) {
		txt = txt.substring(1, txt.length());
		txt = txt.substring(0, txt.length() - 1);
        String[] split = txt.split(",");
        return split;
    }
	
	public static String[] separateStringOnlyValues(String text) {
        String[] split = text.split("-");
        return split;
    }
	
	@SuppressWarnings("rawtypes")
	public static Object bindClass(Class c, String[] fields, String[] values) 
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object newInstance = null;
        Map<String, Object> param;
        if(fields == null && values == null || 
        		(fields != null && values != null && fields.length != values.length)) {
        	return null;
        }
        param = new HashMap<String, Object>();
        for (int i = 0; i < values.length; i++) {
			param.put(fields[i], values[i]);
		}
        newInstance = c.newInstance();

        for (String key : param.keySet()) {
            Object value = param.get(key);
            String nomeMetodo = "set" + key.substring(0, 1).toUpperCase().concat(key.substring(1));
            Method method = null;

            for (Method m : newInstance.getClass().getDeclaredMethods()) {
                if (m.getName().equals(nomeMetodo)) {
                    method = m;
                    break;
                }
            }

            Class type = method.getParameterTypes()[0];
            value = conversor(value, type);

            method.invoke(newInstance, value);

        }
        return newInstance;
    }
	
	@SuppressWarnings("rawtypes")
    public static Object conversor(Object valor, Class type) {
        if (type.getName().contains("Long")) {
            Long v = Long.parseLong(valor.toString());
            valor = v;
        }
        if (type.getName().contains("BigDecimal")) {
            BigDecimal v = new BigDecimal(valor.toString());
            valor = v;
        }
        if (type.getName().contains("int")) {
            int v = Integer.parseInt(valor.toString());
            valor = v;
        }
        return valor;
    }

}
