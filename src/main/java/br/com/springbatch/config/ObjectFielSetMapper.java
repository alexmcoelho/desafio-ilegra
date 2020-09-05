package br.com.springbatch.config;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import br.com.springbatch.manip.ReturnObject;
import br.com.springbatch.model.Customer;
import br.com.springbatch.model.ItemSale;
import br.com.springbatch.model.Sale;
import br.com.springbatch.model.Salesman;

@Component
public class ObjectFielSetMapper implements FieldSetMapper<Object> {
	@Override
	public Object mapFieldSet(FieldSet fieldSet) {
		if (fieldSet.getValues()[0].contains("001")) {
			Customer obj = new Customer();
			obj.setId(Long.parseLong(fieldSet.getValues()[1]));
			obj.setName(fieldSet.getValues()[2]);
			obj.setSalary(new BigDecimal(fieldSet.getValues()[3]));
			return obj;
		}
		if (fieldSet.getValues()[0].contains("002")) {
			Salesman obj = new Salesman();
			obj.setId(Long.parseLong(fieldSet.getValues()[1]));
			obj.setName(fieldSet.getValues()[2]);
			obj.setBusinessArea(fieldSet.getValues()[3]);
			return obj;
		}
		if (fieldSet.getValues()[0].contains("003")) {
			Sale obj = new Sale();
			String[] itens;
			List<ItemSale> list = new ArrayList<ItemSale>();
			try {
				obj.setId(Long.parseLong(fieldSet.getValues()[1]));
				itens = ReturnObject.separateString(fieldSet.getValues()[2]);
				for (String string : itens) {
					ItemSale itemSale = (ItemSale) ReturnObject.bindClass(ItemSale.class, 
							new String[] { "id", "quatityItem", "price" },
							ReturnObject.separateStringOnlyValues(string));
					list.add(itemSale);
				}
				obj.setLisItens(list);
				obj.setSalesman(new Salesman(fieldSet.getValues()[3]));
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return obj;
		}
		return null;
	}
	
}
