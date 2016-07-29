package merge.ods.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import merge.ods.mixer.configuration.MixerConfiguration;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.strategy.ConflictSheet;

public class ProcessData {

	
	private SpreadSheet original;
	
	private Sheet originalSheet;
	private Sheet firstChangeSheet;
	private Sheet secondChangeSheet;
	
	private int actualRowNumber;
	
	private List<String> studentIdentifications;
	private int firstFileDephasing;
	private int secondFileDephasing;
	
	private Boolean mustContinueDetecting;
	private Boolean rowChangeWereDetected;
	private Boolean conflictDetected;
	
	private ConflictSheet conflictSheet;
	private MixerConfiguration mixerConfiguration;
	
	private List<Conflict> conflictsToResolve;
	
	public ProcessData(MixerConfiguration mixerConfiguration){
		
		setMixerConfiguration(mixerConfiguration);
		setOriginal(openFile(getMixerConfiguration().getOriginalFileName()));
		SpreadSheet firstChange  = openFile(getMixerConfiguration().getFirstChangeFileName());
		SpreadSheet secondChange = openFile(getMixerConfiguration().getSecondChangeFileName());
		
		this.setOriginalSheet(getOriginal().getSheet(getMainSpreadsheetName()));
		this.setFirstChangeSheet(firstChange.getSheet(getMainSpreadsheetName()));
		this.setSecondChangeSheet(secondChange.getSheet(getMainSpreadsheetName()));
		
		setStudentIdentifications(new ArrayList<String>());
		
		setFirstFileDephasing(0);
		setSecondFileDephasing(0);
		
		setConflictSheet(new ConflictSheet());
		setConflictsToResolve(new ArrayList<Conflict>());
		setRowChangeWereDetected(false);
		
	}

	/***************************************************** Sheet Methods ******************************************/
	
	
	private static SpreadSheet openFile(String filename) {
		try {
			return SpreadSheet.createFromFile(new File(filename));
		} catch (IOException e) {
			throw new RuntimeException("No se encontro el archivo " + filename, e);
		}
	}
	/***************************************************** Configuration Data ******************************************/
	
	protected String getMainSpreadsheetName() {
		return MixerConfiguration.DEFAULT_MAIN_SPREADSHEET_NAME;
	}
	
	/***************************************************** Getters and Setters ******************************************/

	
	public SpreadSheet getOriginal() {
		return original;
	}
	public Sheet getFirstChangeSheet() {
		return firstChangeSheet;
	}

	public void setFirstChangeSheet(Sheet firstChangeSheet) {
		this.firstChangeSheet = firstChangeSheet;
	}

	public Sheet getSecondChangeSheet() {
		return secondChangeSheet;
	}

	public void setSecondChangeSheet(Sheet secondChangeSheet) {
		this.secondChangeSheet = secondChangeSheet;
	}
	
	public Sheet getOriginalSheet() {
		return this.originalSheet;
	}
	
	public void setOriginalSheet(Sheet originalSheet) {
		this.originalSheet = originalSheet;
	}

	public void setOriginal(SpreadSheet original) {
		this.original = original;
	}

	public int getFirstFileRowNumber(){
		return actualRowNumber + getFirstFileDephasing();
	}
	public int getSecondFileRowNumber(){
		return actualRowNumber + getSecondFileDephasing();
	}
	public int getActualRowNumber() {
		return actualRowNumber;
	}
	public void setActualRowNumber(int actualRowNumber) {
		this.actualRowNumber = actualRowNumber;
	}
	public List<String> getStudentIdentifications() {
		return studentIdentifications;
	}
	public void setStudentIdentifications(List<String> studentIdentifications) {
		this.studentIdentifications = studentIdentifications;
	}
	public int getFirstFileDephasing() {
		return firstFileDephasing;
	}
	public void setFirstFileDephasing(int firstFileDephasing) {
		this.firstFileDephasing = firstFileDephasing;
	}
	public int getSecondFileDephasing() {
		return secondFileDephasing;
	}
	public void setSecondFileDephasing(int secondFileDephasing) {
		this.secondFileDephasing = secondFileDephasing;
	}

	public Boolean getMustContinueDetecting() {
		return mustContinueDetecting;
	}

	public void setMustContinueDetecting(Boolean mustContinueDetecting) {
		this.mustContinueDetecting = mustContinueDetecting;
	}

	public Boolean getConflictDetected() {
		return conflictDetected;
	}

	public void setConflictDetected(Boolean conflictDetected) {
		this.conflictDetected = conflictDetected;
	}

	public ConflictSheet getConflictSheet() {
		return conflictSheet;
	}

	public void setConflictSheet(ConflictSheet conflictSheet) {
		this.conflictSheet = conflictSheet;
	}

	public MixerConfiguration getMixerConfiguration() {
		return mixerConfiguration;
	}

	public void setMixerConfiguration(MixerConfiguration mixerConfiguration) {
		this.mixerConfiguration = mixerConfiguration;
	}

	public List<Conflict> getConflictsToResolve() {
		return conflictsToResolve;
	}

	public void setConflictsToResolve(List<Conflict> conflictsToResolve) {
		this.conflictsToResolve = conflictsToResolve;
	}
	
	public Boolean getRowChangeWereDetected() {
		return rowChangeWereDetected;
	}

	public void setRowChangeWereDetected(Boolean rowChangeWereDetected) {
		this.rowChangeWereDetected = rowChangeWereDetected;
	}
	
}
