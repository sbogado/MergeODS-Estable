package merge.ods.mixer.conflict.resolver.row;

import java.awt.Color;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.jopendocument.dom.spreadsheet.Table;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;

public class BothFilesHaveChangedWithConflictSheetLoggerConflictResolver
		extends BothFilesHaveChangedConflictResolverAbstract {

	private String resultSuggested;
	private String actualRowNumberToLog;

	public BothFilesHaveChangedWithConflictSheetLoggerConflictResolver() {
		super();
		setActualRowNumberToLog("");
	}

	/*****************************************************
	 * Mix Abstract Methods
	 ******************************************/

	public void doBeforeMix(int columnNumber, int rowNumber, MutableCell<SpreadSheet> sheetOriginalCell,
			MutableCell<SpreadSheet> sheetFirstChangeCell, MutableCell<SpreadSheet> sheetSecondChangeCell,
			String sheetOriginalValue, String sheetFirstChangeValue, String sheetSecondChangeValue,
			Color sheetOriginalColor, Color sheetFirstChangeColor, Color sheetSecondChangeColor) {

		resetConflictEncountered();
		resetResultSuggested();
	}

	public void applyOnlyFirstFileHaveChangedValue(int columnNumber, int rowNumber,
			MutableCell<SpreadSheet> sheetOriginalCell, MutableCell<SpreadSheet> sheetFirstChangeCell,
			MutableCell<SpreadSheet> sheetSecondChangeCell, String sheetOriginalValue, String sheetFirstChangeValue,
			String sheetSecondChangeValue, Color sheetOriginalColor, Color sheetFirstChangeColor,
			Color sheetSecondChangeColor) {

		aConflictWereEncountered();
		addMoreSuggestions(sheetFirstChangeValue);
	}

	public void applyOnlyFirstFileHaveChangedColor(int columnNumber, int rowNumber,
			MutableCell<SpreadSheet> sheetOriginalCell, MutableCell<SpreadSheet> sheetFirstChangeCell,
			MutableCell<SpreadSheet> sheetSecondChangeCell, String sheetOriginalValue, String sheetFirstChangeValue,
			String sheetSecondChangeValue, Color sheetOriginalColor, Color sheetFirstChangeColor,
			Color sheetSecondChangeColor) {

		aConflictWereEncountered();
		addMoreSuggestions("\\FirstFileColor");
	}

	public void applyOnlySecondFileHaveChangedValue(int columnNumber, int rowNumber,
			MutableCell<SpreadSheet> sheetOriginalCell, MutableCell<SpreadSheet> sheetFirstChangeCell,
			MutableCell<SpreadSheet> sheetSecondChangeCell, String sheetOriginalValue, String sheetFirstChangeValue,
			String sheetSecondChangeValue, Color sheetOriginalColor, Color sheetFirstChangeColor,
			Color sheetSecondChangeColor) {

		aConflictWereEncountered();
		addMoreSuggestions(sheetSecondChangeValue);
	}

	public void applyOnlySecondFileHaveChangedColor(int columnNumber, int rowNumber,
			MutableCell<SpreadSheet> sheetOriginalCell, MutableCell<SpreadSheet> sheetFirstChangeCell,
			MutableCell<SpreadSheet> sheetSecondChangeCell, String sheetOriginalValue, String sheetFirstChangeValue,
			String sheetSecondChangeValue, Color sheetOriginalColor, Color sheetFirstChangeColor,
			Color sheetSecondChangeColor) {

		aConflictWereEncountered();
		addMoreSuggestions("\\SecondFileColor");
	}

	public void applyBothFilesHaveChangedValue(int columnNumber, int rowNumber,
			MutableCell<SpreadSheet> sheetOriginalCell, MutableCell<SpreadSheet> sheetFirstChangeCell,
			MutableCell<SpreadSheet> sheetSecondChangeCell, String sheetOriginalValue, String sheetFirstChangeValue,
			String sheetSecondChangeValue, Color sheetOriginalColor, Color sheetFirstChangeColor,
			Color sheetSecondChangeColor) {

		// if(sheetFirstChangeValue.equals(sheetSecondChangeValue)
		// && sheetOriginalColor.equals(sheetFirstChangeColor) &&
		// sheetOriginalColor.equals(sheetSecondChangeColor)){
		// setCellValue(sheetOriginalCell,sheetFirstChangeValue+MixerConfiguration.DEFAULT_CONFLICT_DATA_SEPARATOR_SYMBOL+sheetSecondChangeValue);
		// }
		// else{
		aConflictWereEncountered();
		addMoreSuggestions(sheetFirstChangeValue + "|" + sheetSecondChangeValue);
		// }

	}

	public void applyBothFilesHaveChangedColor(int columnNumber, int rowNumber,
			MutableCell<SpreadSheet> sheetOriginalCell, MutableCell<SpreadSheet> sheetFirstChangeCell,
			MutableCell<SpreadSheet> sheetSecondChangeCell, String sheetOriginalValue, String sheetFirstChangeValue,
			String sheetSecondChangeValue, Color sheetOriginalColor, Color sheetFirstChangeColor,
			Color sheetSecondChangeColor) {

		aConflictWereEncountered();
		addMoreSuggestions("\\FirstFileColor|SecondFileColor");
	}

	public void doAfterMix(int columnNumber,int rowNumber
			,MutableCell<SpreadSheet> sheetOriginalCell,MutableCell<SpreadSheet> sheetFirstChangeCell,MutableCell<SpreadSheet> sheetSecondChangeCell,
			String sheetOriginalValue,String sheetFirstChangeValue,String sheetSecondChangeValue,
			Color sheetOriginalColor,Color sheetFirstChangeColor,Color sheetSecondChangeColor,ProcessData processData){
		String originalIdentification = processData.getOriginalSheet().getCellAt(processData.getMixerConfiguration().getStudentIdentificationColumn(),rowNumber).getValue().toString();
		if(getConflictEncountered()){
			processData.getConflictSheet().logConflict(new Conflict(getConflictType()
					,originalIdentification,getResultSuggested()
					,Table.toStr(columnNumber)
					,String.valueOf(rowNumber)
					,""
					,""
					,sheetOriginalCell.getValue().toString()
					,sheetFirstChangeCell.getValue().toString()
					,sheetSecondChangeCell.getValue().toString()
					,sheetOriginalColor
					,sheetFirstChangeColor
					,sheetSecondChangeColor
					),processData);
		}
	}

	/*****************************************************
	 * GETTER AND SETTERS
	 ******************************************/

	public String getResultSuggested() {
		return resultSuggested;
	}

	public void setResultSuggested(String resultSuggested) {
		this.resultSuggested = resultSuggested;
	}

	public void addMoreSuggestions(String moreSuggestions) {
		this.resultSuggested = this.resultSuggested + moreSuggestions;
	}

	public void resetResultSuggested() {
		this.resultSuggested = "";
	}

	public String getActualRowNumberToLog() {
		return actualRowNumberToLog;
	}

	public void setActualRowNumberToLog(String actualRowNumberToLog) {
		this.actualRowNumberToLog = actualRowNumberToLog;
	}


}
