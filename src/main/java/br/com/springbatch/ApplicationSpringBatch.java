package br.com.springbatch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class ApplicationSpringBatch implements CommandLineRunner{
	
	@Value("/${monitoring-folder}")
	private String monitoringFolter;
	
	@Value("/${progress-folter}")
	private String progressFolter;
	
	@Value("/${progress-file}")
	private String progressFile;
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	public static void main(String[] args) {
		SpringApplication.run(ApplicationSpringBatch.class, args);
	}
	
    @Scheduled(cron = "*/10 * * * * *")
    public void perform() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(job, params);
    }
    
    public void joinContentInProgress(String nameFile) throws FileNotFoundException, IOException {
         
        BufferedReader br = null; 
            
        br = new BufferedReader(new FileReader(System.getProperty("user.dir") + monitoringFolter + nameFile)); 
        
        String line = br.readLine(); 
        
        StringBuilder builder = new StringBuilder();
        while(line != null) 
        { 
        	if(line != null) builder.append("\n").append(line);
            line = br.readLine();
        }
        
        br.close(); 
        
        String fileName = System.getProperty("user.dir") + progressFolter + progressFile;
        byte[] tb = builder.toString().getBytes(); 
        try (FileOutputStream fos = new FileOutputStream(fileName, true)) {					
        	fos.write(tb); 
		}       
        
    }
	
	public void run(String... args) throws Exception {
		
		WatchService watchService = FileSystems.getDefault().newWatchService();

		Path path = Paths.get(System.getProperty("user.dir") + monitoringFolter);

		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		WatchKey key;
		List<String> files = new ArrayList<String>();
		try {
			while ((key = watchService.take()) != null) {
				for (WatchEvent<?> event : key.pollEvents()) {
					System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
					String kind = ""+event.kind();
					String context = ""+event.context();
					if(kind.contains("CREATE") && context.matches("^.*.dat$")) {
						files.add(context);
						Thread.sleep(1000);
					}
				}
				
				key.reset();
				for (String string : files) {
					joinContentInProgress(string);
				}
				files.clear();
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
