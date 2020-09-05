package br.com.springbatch.manip;

import java.util.Comparator;
import java.util.Set;

import br.com.springbatch.model.Sale;
import br.com.springbatch.model.Salesman;

public class ManipValues {
	
	public static Long maxVenda(Set<Sale> sales) {
		Long max = sales
			      .stream()
			      .max(Comparator.comparing(Sale::getTotal))
			      .map(s -> s.getId())
			      .orElseThrow(null);
		return max;
	}
	
	public static Salesman minVenda(Set<Sale> sales) {
		Salesman min = sales
			      .stream()
			      .min(Comparator.comparing(Sale::getTotal))
			      .map(s -> s.getSalesman())
			      .orElseThrow(null);
		return min;
	}

}
