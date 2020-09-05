package br.com.springbatch.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.springbatch.manip.ManipText;
import br.com.springbatch.manip.ManipValues;
import br.com.springbatch.model.Customer;
import br.com.springbatch.model.Sale;
import br.com.springbatch.model.Salesman;

@Component
public class WriterArchive {
	
	@Value("/${done-path}")
	private String donePath;
	
	private StringBuilder builder = new StringBuilder();
	private String uploadingDir;
	
	public void writeArchiveSalesman(Set<Customer> list) {
		builder.append("Quantidade de clientes no arquivo de entrada: ")
			.append(list.size())
			.append("\n");
	}
	
	public void writeArchiveClient(Set<Salesman> list) {
		builder.append("Quantidade de vendedor no arquivo de entrada: ")
			.append(list.size())
			.append("\n");
	}
	
	public void writeArchiveSale(Set<Sale> list) {
		if(list != null && list.size() > 0) {
			builder.append("ID da venda mais cara: ")
			.append(ManipValues.maxVenda(list))
			.append("\n");
		}
		if(list != null && list.size() > 0) {
			Salesman salesman = ManipValues.minVenda(list);
			builder.append("O pior vendedor: ")
			.append(salesman.getName())
			.append("\n");
		}
	}
	
	public void writer() {
		uploadingDir = System.getProperty("user.dir") + donePath;
		if(builder.toString().length() > 0) {
			ManipText.writer(uploadingDir, builder.toString());
		}
	}
	
	public void clean() {
		if(builder != null) {
			builder.setLength(0);
		}
	}
	

}
