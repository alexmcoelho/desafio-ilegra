package br.com.springbatch.config;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.classify.Classifier;

public class ClassifierCompositeLineMapper {
	
	private Classifier<String, LineMapper<?>> classifier;

    public ClassifierCompositeLineMapper(Classifier<String, LineMapper<?>> classifier) {
        this.classifier = classifier;
    }

    public Object mapLine(String line, int lineNumber) throws Exception {
        return classifier.classify(line).mapLine(line, lineNumber);
    }

}
