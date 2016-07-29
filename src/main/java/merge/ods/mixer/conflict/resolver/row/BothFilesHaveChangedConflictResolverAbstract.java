package merge.ods.mixer.conflict.resolver.row;

import java.awt.Color;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.ConflictType;

public abstract class BothFilesHaveChangedConflictResolverAbstract extends RowConflictResolver{

	private Boolean conflictEncountered;
	
	public BothFilesHaveChangedConflictResolverAbstract() {
		super(ConflictType.BOTH_FILES_HAVE_CHANGED);
		
	}

	@Override
	public void doResolve(Conflict conflict,ProcessData processData) {
		for(int actualColumnNumber = 0; actualColumnNumber <= processData.getMixerConfiguration().getLastColumnToMix(); actualColumnNumber++ ){
			mixCell(actualColumnNumber,processData.getActualRowNumber(),processData);
		}
	}

	/***************************************************** Mix Methods ******************************************/
	
	public void mixCell(int columnNumber,int rowNumber,ProcessData processData) {
		final MutableCell<SpreadSheet> sheetOriginalCell = processData.getOriginalSheet().getCellAt(columnNumber,rowNumber);
		final MutableCell<SpreadSheet> sheetFirstChangeCell = processData.getFirstChangeSheet().getCellAt(columnNumber,rowNumber);
		final MutableCell<SpreadSheet> sheetSecondChangeCell = processData.getSecondChangeSheet().getCellAt(columnNumber,rowNumber);
		
		final String sheetOriginalValue = sheetOriginalCell.getValue().toString();
		final String sheetFirstChangeValue = sheetFirstChangeCell.getValue().toString();
		final String sheetSecondChangeValue = sheetSecondChangeCell.getValue().toString();
		
		final Color sheetOriginalColor = sheetOriginalCell.getStyle().getBackgroundColor(sheetOriginalCell);
		final Color sheetFirstChangeColor = sheetFirstChangeCell.getStyle().getBackgroundColor(sheetFirstChangeCell);
		final Color sheetSecondChangeColor = sheetSecondChangeCell.getStyle().getBackgroundColor(sheetSecondChangeCell);
		
		doBeforeMix(columnNumber,rowNumber,sheetOriginalCell,sheetFirstChangeCell,sheetSecondChangeCell,
				sheetOriginalValue,sheetFirstChangeValue,sheetSecondChangeValue,
				sheetOriginalColor,sheetFirstChangeColor,sheetSecondChangeColor);
		
		applyOnlyFirstFileHaveChanged( columnNumber,rowNumber,sheetOriginalCell,sheetFirstChangeCell,sheetSecondChangeCell,
				sheetOriginalValue,sheetFirstChangeValue,sheetSecondChangeValue,
				sheetOriginalColor,sheetFirstChangeColor,sheetSecondChangeColor);

		applyOnlySecondFileHaveChanged(columnNumber,rowNumber,sheetOriginalCell,sheetFirstChangeCell,sheetSecondChangeCell,
				sheetOriginalValue,sheetFirstChangeValue,sheetSecondChangeValue,
				sheetOriginalColor,sheetFirstChangeColor,sheetSecondChangeColor);

		applyBothFilesHaveChanged(columnNumber,rowNumber,sheetOriginalCell,sheetFirstChangeCell,sheetSecondChangeCell,
				sheetOriginalValue,sheetFirstChangeValue,sheetSecondChangeValue,
				sheetOriginalColor,sheetFirstChangeColor,sheetSecondChangeColor);
		
		doAfterMix(columnNumber,rowNumber,sheetOriginalCell,sheetFirstChangeCell,sheetSecondChangeCell,
				sheetOriginalValue,sheetFirstChangeValue,sheetSecondChangeValue,
				sheetOriginalColor,sheetFirstChangeColor,sheetSecondChangeColor,processData);
		
	}

	/***************************************************** Mix Helper Methods ******************************************/


	public abstract void doBeforeMix(int columnNumber,int rowNumber
			,MutableCell<SpreadSheet> sheetOriginalCell,MutableCell<SpreadSheet> sheetFirstChangeCell,MutableCell<SpreadSheet> sheetSecondChangeCell,
			String sheetOriginalValue,String sheetFirstChangeValue,String sheetSecondChangeValue,
			Color sheetOriginalColor,Color sheetFirstChangeColor,Color sheetSecondChangeColor);
	
	public abstract void doAfterMix(int columnNumber,int rowNumber
			,MutableCell<SpreadSheet> sheetOriginalCell,MutableCell<SpreadSheet> sheetFirstChangeCell,MutableCell<SpreadSheet> sheetSecondChangeCell,
			String sheetOriginalValue,String sheetFirstChangeValue,String sheetSecondChangeValue,
			Color sheetOriginalColor,Color sheetFirstChangeColor,Color sheetSecondChangeColor,ProcessData processData);
	
	public void applyOnlyFirstFileHaveChanged(int columnNumber,int rowNumber
			,MutableCell<SpreadSheet> sheetOriginalCell,MutableCell<SpreadSheet> sheetFirstChangeCell,MutableCell<SpreadSheet> sheetSecondChangeCell,
			String sheetOriginalValue,String sheetFirstChangeValue,String sheetSecondChangeValue,
			Color sheetOriginalColor,Color sheetFirstChangeColor,Color sheetSecondChangeColor){
		if(sheetOriginalValue.equals(sheetSecondChangeValue) && !sheetOriginalValue.equals(sheetFirstChangeValue)){
			applyOnlyFirstFileHaveChangedValue(columnNumber,rowNumber,sheetOriginalCell,sheetFirstChangeCell,sheetSecondChangeCell,
					sheetOriginalValue,sheetFirstChangeValue,sheetSecondChangeValue,
					sheetOriginalColor,sheetFirstChangeColor,sheetSecondChangeColor);
		}
		
		if(sheetOriginalColor.equals(sheetSecondChangeColor) && !sheetOriginalColor.equals(sheetFirstChangeColor)){
			applyOnlyFirstFileHaveChangedColor(columnNumber,rowNumber,sheetOriginalCell,sheetFirstChangeCell,sheetSecondChangeCell,
					sheetOriginalValue,sheetFirstChangeValue,sheetSecondChangeValue,
					sheetOriginalColor,sheetFirstChangeColor,sheetSecondChangeColor);
		}
	}
	

	public abstract void applyOnlyFirstFileHaveChangedValue(int columnNumber, int rowNumber,
			MutableCell<SpreadSheet> sheetOriginalCell, MutableCell<SpreadSheet> sheetFirstChangeCell,
			MutableCell<SpreadSheet> sheetSecondChangeCell, String sheetOriginalValue, String sheetFirstChangeValue,
			String sheetSecondChangeValue, Color sheetOriginalColor, Color sheetFirstChangeColor,
			Color sheetSecondChangeColor);

	public abstract void applyOnlyFirstFileHaveChangedColor(int columnNumber, int rowNumber,
			MutableCell<SpreadSheet> sheetOriginalCell, MutableCell<SpreadSheet> sheetFirstChangeCell,
			MutableCell<SpreadSheet> sheetSecondChangeCell, String sheetOriginalValue, String sheetFirstChangeValue,
			String sheetSecondChangeValue, Color sheetOriginalColor, Color sheetFirstChangeColor,
			Color sheetSecondChangeColor);


	public void applyOnlySecondFileHaveChanged(int columnNumber,int rowNumber
			,MutableCell<SpreadSheet> sheetOriginalCell,MutableCell<SpreadSheet> sheetFirstChangeCell,MutableCell<SpreadSheet> sheetSecondChangeCell,
			String sheetOriginalValue,String sheetFirstChangeValue,String sheetSecondChangeValue,
			Color sheetOriginalColor,Color sheetFirstChangeColor,Color sheetSecondChangeColor){
		if(sheetOriginalValue.equals(sheetFirstChangeValue) && !sheetOriginalValue.equals(sheetSecondChangeValue)){
			applyOnlySecondFileHaveChangedValue(columnNumber,rowNumber,sheetOriginalCell,sheetFirstChangeCell,sheetSecondChangeCell,
					sheetOriginalValue,sheetFirstChangeValue,sheetSecondChangeValue,
					sheetOriginalColor,sheetFirstChangeColor,sheetSecondChangeColor);
		}
		
		if(sheetOriginalColor.equals(sheetFirstChangeColor) && !sheetOriginalColor.equals(sheetSecondChangeColor)){
			applyOnlySecondFileHaveChangedColor(columnNumber,rowNumber,sheetOriginalCell,sheetFirstChangeCell,sheetSecondChangeCell,
					sheetOriginalValue,sheetFirstChangeValue,sheetSecondChangeValue,
					sheetOriginalColor,sheetFirstChangeColor,sheetSecondChangeColor);
		}
	}
	

	public abstract void applyOnlySecondFileHaveChangedValue(int columnNumber, int rowNumber,
			MutableCell<SpreadSheet> sheetOriginalCell, MutableCell<SpreadSheet> sheetFirstChangeCell,
			MutableCell<SpreadSheet> sheetSecondChangeCell, String sheetOriginalValue, String sheetFirstChangeValue,
			String sheetSecondChangeValue, Color sheetOriginalColor, Color sheetFirstChangeColor,
			Color sheetSecondChangeColor);
	
	public abstract void applyOnlySecondFileHaveChangedColor(int columnNumber, int rowNumber,
			MutableCell<SpreadSheet> sheetOriginalCell, MutableCell<SpreadSheet> sheetFirstChangeCell,
			MutableCell<SpreadSheet> sheetSecondChangeCell, String sheetOriginalValue, String sheetFirstChangeValue,
			String sheetSecondChangeValue, Color sheetOriginalColor, Color sheetFirstChangeColor,
			Color sheetSecondChangeColor);

	public void applyBothFilesHaveChanged(int columnNumber,int rowNumber
			,MutableCell<SpreadSheet> sheetOriginalCell,MutableCell<SpreadSheet> sheetFirstChangeCell,MutableCell<SpreadSheet> sheetSecondChangeCell,
			String sheetOriginalValue,String sheetFirstChangeValue,String sheetSecondChangeValue,
			Color sheetOriginalColor,Color sheetFirstChangeColor,Color sheetSecondChangeColor){
		if(!sheetOriginalValue.equals(sheetFirstChangeValue) && !sheetOriginalValue.equals(sheetSecondChangeValue)){		
			applyBothFilesHaveChangedValue(columnNumber,rowNumber,sheetOriginalCell,sheetFirstChangeCell,sheetSecondChangeCell,
					sheetOriginalValue,sheetFirstChangeValue,sheetSecondChangeValue,
					sheetOriginalColor,sheetFirstChangeColor,sheetSecondChangeColor);
		}
		
		if(!sheetOriginalColor.equals(sheetFirstChangeColor) && !sheetOriginalColor.equals(sheetSecondChangeColor)){
			applyBothFilesHaveChangedColor(columnNumber,rowNumber,sheetOriginalCell,sheetFirstChangeCell,sheetSecondChangeCell,
					sheetOriginalValue,sheetFirstChangeValue,sheetSecondChangeValue,
					sheetOriginalColor,sheetFirstChangeColor,sheetSecondChangeColor);
		}
	}
	
	public abstract void applyBothFilesHaveChangedValue(int columnNumber, int rowNumber,
			MutableCell<SpreadSheet> sheetOriginalCell, MutableCell<SpreadSheet> sheetFirstChangeCell,
			MutableCell<SpreadSheet> sheetSecondChangeCell, String sheetOriginalValue, String sheetFirstChangeValue,
			String sheetSecondChangeValue, Color sheetOriginalColor, Color sheetFirstChangeColor,
			Color sheetSecondChangeColor);

	public abstract void applyBothFilesHaveChangedColor(int columnNumber, int rowNumber,
			MutableCell<SpreadSheet> sheetOriginalCell, MutableCell<SpreadSheet> sheetFirstChangeCell,
			MutableCell<SpreadSheet> sheetSecondChangeCell, String sheetOriginalValue, String sheetFirstChangeValue,
			String sheetSecondChangeValue, Color sheetOriginalColor, Color sheetFirstChangeColor,
			Color sheetSecondChangeColor);
   
	/***************************************************** Getters And Setters ******************************************/
	
	public Boolean getConflictEncountered() {
		return conflictEncountered;
	}

	public void resetConflictEncountered() {
		this.conflictEncountered = false;
	}
	
	public void aConflictWereEncountered() {
		this.conflictEncountered = true;
	}
	
}
