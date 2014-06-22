package com.deege.processors;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;
import org.beanio.builder.DelimitedParserBuilder;
import org.beanio.builder.StreamBuilder;

/**
 * 
 * <p>Processes a CSV file.  This expects a class type, so it knows how to process the 
 * CSV file.</p>
 *
 * @param <T> the type for the CSV.  Must be {@link java.io.Serializable} and be annotated
 */
public abstract class CsvProcessor<T> {

	/**
	 * <p>Process one row of the CSV file.</p>
	 * 
	 * @param processItem the CSV row to process
	 */
	abstract public void process(T processItem);
	
	/**
	 * <p>Executes the processing of a CSV file using BeanIO.  This assumes
	 * the {@link java.nio.charset.Charset} is UTF-8.</p>
	 * 
	 * @param inputStream the CSV file to process
	 * @param delimiter the delimiter used in the CSV file
	 * @param clazz the POJO class each row represents
	 */
	public void run(InputStream inputStream, char delimiter, Class<T> clazz) {
		run(inputStream, delimiter, clazz, StandardCharsets.UTF_8);
	}
	
	/**
	 * <p>Executes the processing of a CSV file using BeanIO.</p>
	 * 
	 * @param inputStream the CSV file to process
	 * @param delimiter the delimiter used in the CSV file
	 * @param clazz the POJO class each row represents
	 * @param charset the charset used to process the CSV
	 */
	@SuppressWarnings("unchecked")
	public void run(InputStream inputStream, char delimiter, Class<T> clazz, Charset charset) {
		T csvObject = null;
		StreamFactory factory = StreamFactory.newInstance();
		String STREAM_BUILDER_NAME = "objectFile";
		StreamBuilder builder = new StreamBuilder(STREAM_BUILDER_NAME)
		        .format("delimited")
		        .parser(new DelimitedParserBuilder(delimiter))
		        .addRecord(clazz);
		factory.define(builder);
		BeanReader in = factory.createReader(STREAM_BUILDER_NAME, 
				new InputStreamReader(inputStream, charset));
		
		while ((csvObject = (T) in.read()) != null) {
            // process
			process(csvObject);
        }
        in.close();
	}
}
