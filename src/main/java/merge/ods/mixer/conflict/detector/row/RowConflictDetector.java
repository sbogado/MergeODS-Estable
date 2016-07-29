package merge.ods.mixer.conflict.detector.row;

import java.awt.Color;
import static merge.ods.helper.CellHelper.backgroundColorOf;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.ConflictType;
import merge.ods.mixer.conflict.detector.ConflictDetector;

public class RowConflictDetector extends ConflictDetector{

	private Boolean isFirstChanged = false;
	private Boolean isSecondChanged = false;
	
	public RowConflictDetector() {
		super();
	}
	
	@Override
	public void detect(ProcessData processData){
				
			Boolean originalIdentificationIsEmpty = getActualOriginalIdentification(processData).isEmpty();
			Boolean originalAndFirstIdentificationAreEquals = getActualOriginalIdentification(processData).equals(getActualFirstFileIdentification(processData));
			Boolean originalAndSecondIdentificationAreEquals = getActualOriginalIdentification(processData).equals(getActualSecondFileIdentification(processData));
			Boolean originalIdentificationsAndOthersIdentificationAreEquals = originalAndFirstIdentificationAreEquals && originalAndSecondIdentificationAreEquals;
			
			setIsFirstChanged(false);
			setIsSecondChanged(false);
			
			String sheetOriginalValue;
			String sheetFirstChangeValue;
			String sheetSecondChangeValue;
		
			Color sheetOriginalColor;
			Color sheetFirstChangeColor;
			Color sheetSecondChangeColor;
		
			MutableCell<SpreadSheet> sheetOriginalCell;
			MutableCell<SpreadSheet> sheetFirstCell;
			MutableCell<SpreadSheet> sheetSecondCell;
			
			if (!originalIdentificationIsEmpty && originalIdentificationsAndOthersIdentificationAreEquals ){
				
				for (int actualColumnNumber = 0; actualColumnNumber <= processData.getMixerConfiguration().getLastColumnToMix()
						&& !(getIsFirstChanged() && getIsSecondChanged()); actualColumnNumber++) {
					sheetOriginalCell = getOriginalFileCell(actualColumnNumber,processData);
					sheetOriginalValue = sheetOriginalCell.getValue().toString();
					sheetOriginalColor = backgroundColorOf(sheetOriginalCell);
		
					if (!getIsFirstChanged()) {
						sheetFirstCell = getFirstFileCell(actualColumnNumber,processData);
						sheetFirstChangeValue = sheetFirstCell.getValue().toString();
						sheetFirstChangeColor = backgroundColorOf(sheetFirstCell);
						setIsFirstChanged(!sheetOriginalValue.equals(sheetFirstChangeValue) || !sheetOriginalColor.equals(sheetFirstChangeColor)) ;
					}
					if (!getIsSecondChanged()) {
						sheetSecondCell = getSecondFileCell(actualColumnNumber,processData);
						sheetSecondChangeValue = sheetSecondCell.getValue().toString();
						sheetSecondChangeColor = backgroundColorOf(sheetSecondCell);
						setIsSecondChanged(!sheetOriginalValue.equals(sheetSecondChangeValue) || !sheetOriginalColor.equals(sheetSecondChangeColor)) ;
					}
				}
			}
			
			addConflict(processData);
	}

	@Override
	public Conflict getConflict(ProcessData processData) {	
		if (getIsFirstChanged() && getIsSecondChanged()) {
			return new Conflict(ConflictType.BOTH_FILES_HAVE_CHANGED);
		} else {
			if (getIsFirstChanged()) {
				processData.setRowChangeWereDetected(true);
				return new Conflict(ConflictType.FIRST_FILE_HAVE_CHANGED);
			}
			if (getIsSecondChanged()) {
				processData.setRowChangeWereDetected(true);
				return (new Conflict(ConflictType.SECONDS_FILE_HAVE_CHANGED));
			}
		}
		return new Conflict(ConflictType.NO_CHANGES);
	}
	

	public MutableCell<SpreadSheet> getOriginalFileCell(int columnNumber,ProcessData processData){
		return processData.getOriginalSheet().getCellAt(columnNumber, processData.getActualRowNumber());
	}

	public MutableCell<SpreadSheet> getFirstFileCell(int columnNumber,ProcessData processData){
		return processData.getFirstChangeSheet().getCellAt(columnNumber, processData.getFirstFileRowNumber());
	}

	public MutableCell<SpreadSheet> getSecondFileCell(int columnNumber,ProcessData processData){
		return processData.getSecondChangeSheet().getCellAt(columnNumber, processData.getSecondFileRowNumber());
	}

	public Boolean getIsFirstChanged() {
		return isFirstChanged;
	}

	public void setIsFirstChanged(Boolean isFirstChanged) {
		this.isFirstChanged = isFirstChanged;
	}

	public Boolean getIsSecondChanged() {
		return isSecondChanged;
	}

	public void setIsSecondChanged(Boolean isSecondChanged) {
		this.isSecondChanged = isSecondChanged;
	}

}
