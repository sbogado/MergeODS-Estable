package merge.ods.mixer.strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.jopendocument.dom.spreadsheet.Table;

import merge.ods.main.ProcessData;
import merge.ods.mixer.configuration.MixerConfiguration;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.detector.MixerConflictDetector;
import merge.ods.mixer.conflict.resolver.MixerConflictResolver;

public abstract class MixerStrategy {

	private static final Logger logger = Logger.getLogger(MixerStrategy.class.getName());

	private MixerConflictResolver mixerConflictResolver;

	private MixerConflictDetector mixerConflictDetector;
	
	private ProcessData processData;

	/*****************************************************
	 * Mix Main Methods
	 ******************************************/

	public SpreadSheet mixFiles() throws IOException {

		setProcessData(new ProcessData(new MixerConfiguration()));
		collectAllStudentIdentifications();

		for (setActualRowNumber(getProcessData().getMixerConfiguration().getFirstRowToMix()
				- 1); getActualRowNumber() <= getProcessData().getMixerConfiguration().getLastRowToMix() - 1; getProcessData()
						.setActualRowNumber(getActualRowNumber() + 1)) {
			logger.log(Level.FINE, "Mixing row: " + (getActualRowNumber() + 1) + " of "
					+ getProcessData().getMixerConfiguration().getLastRowToMix());
			doMixRow();
		}

		return getProcessData().getOriginal();
	}

	private void collectAllStudentIdentifications() {
		for (setActualRowNumber(getProcessData().getMixerConfiguration().getFirstRowToMix()
				- 1); getActualRowNumber() <= getProcessData().getMixerConfiguration().getLastRowToMix() - 1; getProcessData()
						.setActualRowNumber(getActualRowNumber() + 1)) {
			addStudentIdentification(getActualRowNumber());
		}
	}

	private void addStudentIdentification(int actualRowNumber) {
		getStudentIdentifications().add(getOriginalSheet()
				.getCellAt(getProcessData().getMixerConfiguration().getStudentIdentificationColumn(), actualRowNumber)
				.getValue().toString());
	}

	/*****************************************************
	 * Mix Helper Methods
	 ******************************************/

	protected void doMixRow() throws IOException {

		getProcessData().setConflictsToResolve(new ArrayList<Conflict>());
		getProcessData().setRowChangeWereDetected(false);
		
		getMixerConflictDetector().detectConflicts(getProcessData());

		for (Conflict conflict : getProcessData().getConflictsToResolve()) {
			getMixerConflictResolver().resolveConflict(conflict,getProcessData());
		}
	}

	/*****************************************************
	 * Configuration Data
	 ******************************************/

	private Table<SpreadSheet> getOriginalSheet() {
		return getProcessData().getOriginalSheet();
	}

	private List<String> getStudentIdentifications() {
		return getProcessData().getStudentIdentifications();
	}

	private int getActualRowNumber() {
		return getProcessData().getActualRowNumber();
	}

	private void setActualRowNumber(int row) {
		getProcessData().setActualRowNumber(row);
	}

	protected String getMainSpreadsheetName() {
		return MixerConfiguration.DEFAULT_MAIN_SPREADSHEET_NAME;
	}

	protected String getConflictDataSeparatorSimbol() {
		return MixerConfiguration.DEFAULT_CONFLICT_DATA_SEPARATOR_SYMBOL;
	}

	protected int getStudentIdentificationNumberLenght() {
		return MixerConfiguration.DEFAULT_STUDENT_IDENTIFICATION_COLUMN_NUMBER_LENGHT;
	}

	/*****************************************************
	 * Getters and Setters
	 ******************************************/

	public MixerConflictResolver getMixerConflictResolver() {
		return mixerConflictResolver;
	}

	public void setMixerConflictResolver(MixerConflictResolver mixerConflictResolver) {
		this.mixerConflictResolver = mixerConflictResolver;
	}

	public MixerConflictDetector getMixerConflictDetector() {
		return mixerConflictDetector;
	}

	public void setMixerConflictDetector(MixerConflictDetector mixerConflictDetector) {
		this.mixerConflictDetector = mixerConflictDetector;
	}

	public ProcessData getProcessData() {
		return processData;
	}

	public void setProcessData(ProcessData processData) {
		this.processData = processData;
	}

}
