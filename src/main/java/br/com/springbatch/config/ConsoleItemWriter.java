package br.com.springbatch.config;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.springbatch.model.Customer;
import br.com.springbatch.model.Sale;
import br.com.springbatch.model.Salesman;
 
public class ConsoleItemWriter<T> implements ItemWriter<T> { 
	
	@Autowired
	private WriterArchive writerArchive;
	
	Set<Customer> listSalesman = new LinkedHashSet<Customer>();
	Set<Salesman> listClient = new LinkedHashSet<Salesman>();
	Set<Sale> listSales = new LinkedHashSet<Sale>();
	
    @Override
    public void write(List<? extends T> items) throws Exception { 
        for (T item : items) {
        	if(item instanceof Customer) {
        		listSalesman.add((Customer) item);
        	}
        	if(item instanceof Salesman) {
        		listClient.add((Salesman) item);
        	}
        	if(item instanceof Sale) {
        		listSales.add((Sale) item);
        	}
        } 
        writerArchive.clean();
        writerArchive.writeArchiveSalesman(listSalesman);
        writerArchive.writeArchiveClient(listClient);
        writerArchive.writeArchiveSale(listSales);
        writerArchive.writer();
    } 
    
    
    
}
