package merge.ods.mixer.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MixerConfiguration {

	private static final Logger logger = Logger
			.getLogger(MixerConfiguration.class.getName());
	
	public static String CONFIGURATION_PROPERTIES_NAME = "config";

	private static String DEFAULT_ORIGINAL_FILE_NAME = "original.ods";
	private static String DEFAULT_FIRST_CHANGED_FILE_NAME = "firstChange.ods";
	private static String DEFAULT_SECOND_CHANGED_FILE_NAME = "secondChange.ods";
	private static String DEFAULT_RESULT_FILE_NAME = "result.ods";

	public static String DEFAULT_MAIN_SPREADSHEET_NAME = "Activos";
	public static String DEFAULT_MAIN_CONFLICT_SPREADSHEET_NAME = "Conflictos";
	public static String DEFAULT_CONFLICT_DATA_SEPARATOR_SYMBOL = ",";
	public static int DEFAULT_STUDENT_IDENTIFICATION_COLUMN_NUMBER_LENGHT = 8;
	
	private static int DEFAULT_LAST_COLUMN_TO_MIX = 160;
	private static int DEFAULT_FIRST_ROW_TO_MIX = 13;
	private static int DEFAULT_LAST_ROW_TO_MIX = 13;
	private static int DEFAULT_STUDENT_IDENTIFICATION_COLUMN_NUMBER = 6;

	private String originalFileName;
	private String resultFileName;
	private String firstChangeFileName;
	private String secondChangeFileName;
	private int lastColumnToMix;
	private int firstRowToMix;
	private int lastRowToMix;
	private int studentIdentificationColumn;
	
	public MixerConfiguration(){
		extractConfigurationValues();
	}
	
	/**************************************************** Configuration Main Methods ******************************************/
	
	private void extractConfigurationValues(){

		try {
			Properties prop = new Properties();
			InputStream inputStream = new FileInputStream(new File(
					CONFIGURATION_PROPERTIES_NAME));
			prop.load(inputStream);

			setPropertiesToFields(prop);

			inputStream.close();

		} catch (Exception e) {
			logger.log(Level.INFO, "Could not load "
					+ CONFIGURATION_PROPERTIES_NAME + " because: ", e);
			logger.log(Level.INFO, "Using default configuration");
			setDefaultValuesToFields();
		}
	}

	private void setPropertiesToFields(Properties prop) {
		originalFileName = (prop.getProperty("originalFileName") != null) ? prop.getProperty("originalFileName") : DEFAULT_ORIGINAL_FILE_NAME ;
		resultFileName = (prop.getProperty("resultFileName") != null ) ?  prop.getProperty("resultFileName") : DEFAULT_RESULT_FILE_NAME;
		firstChangeFileName = (prop.getProperty("firstChangeFileName") != null) ? prop.getProperty("firstChangeFileName") : DEFAULT_FIRST_CHANGED_FILE_NAME;
		secondChangeFileName = (prop.getProperty("secondChangeFileName") != null) ? prop.getProperty("secondChangeFileName") : DEFAULT_SECOND_CHANGED_FILE_NAME;
		lastColumnToMix =  (prop.getProperty("lastColumnToMix")!= null) ?  Integer.parseInt(prop.getProperty("lastColumnToMix")) : DEFAULT_LAST_COLUMN_TO_MIX;
		firstRowToMix = (prop.getProperty("firstRowToMix") != null) ?  Integer.parseInt(prop.getProperty("firstRowToMix")) : DEFAULT_FIRST_ROW_TO_MIX;
		lastRowToMix = (prop.getProperty("lastRowToMix") != null) ?  Integer.parseInt(prop.getProperty("lastRowToMix")) : DEFAULT_LAST_ROW_TO_MIX;
		studentIdentificationColumn = (prop.getProperty("studentIdentificationColumn") != null) ? Integer.parseInt(prop.getProperty("studentIdentificationColumn")) : DEFAULT_STUDENT_IDENTIFICATION_COLUMN_NUMBER;
	}
	
	private void setDefaultValuesToFields() {
		originalFileName = DEFAULT_ORIGINAL_FILE_NAME;
		resultFileName = DEFAULT_RESULT_FILE_NAME;
		firstChangeFileName = DEFAULT_FIRST_CHANGED_FILE_NAME;
		secondChangeFileName = DEFAULT_SECOND_CHANGED_FILE_NAME;
		lastColumnToMix = DEFAULT_LAST_COLUMN_TO_MIX;
		firstRowToMix = DEFAULT_FIRST_ROW_TO_MIX;
		lastRowToMix = DEFAULT_LAST_ROW_TO_MIX;
		studentIdentificationColumn = DEFAULT_STUDENT_IDENTIFICATION_COLUMN_NUMBER;
	}

	/***************************************************** Getters and Setters ******************************************/
	
	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getFirstChangeFileName() {
		return firstChangeFileName;
	}

	public void setFirstChangeFileName(String firstChangeFileName) {
		this.firstChangeFileName = firstChangeFileName;
	}

	public String getSecondChangeFileName() {
		return secondChangeFileName;
	}

	public void setSecondChangeFileName(String secondChangeFileName) {
		this.secondChangeFileName = secondChangeFileName;
	}

	public int getLastColumnToMix() {
		return lastColumnToMix;
	}

	public void setLastColumnToMix(int lastColumnToMix) {
		this.lastColumnToMix = lastColumnToMix;
	}

	public int getFirstRowToMix() {
		return firstRowToMix;
	}

	public void setFirstRowToMix(int firstRowToMix) {
		this.firstRowToMix = firstRowToMix;
	}

	public int getLastRowToMix() {
		return lastRowToMix;
	}

	public void setLastRowToMix(int lastRowToMix) {
		this.lastRowToMix = lastRowToMix;
	}

	public int getStudentIdentificationColumn() {
		return studentIdentificationColumn;
	}

	public void setStudentIdentificationColumn(int studentIdentificationColumn) {
		this.studentIdentificationColumn = studentIdentificationColumn;
	}

	public String getResultFileName() {
		return resultFileName;
	}

	public void setResultFileName(String resultFileName) {
		this.resultFileName = resultFileName;
	}

}
