package merge.ods.mixer.conflict.resolver.row;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.ConflictType;

public class FirstFileHaveChangedConflictResolver extends RowConflictResolver{

	public FirstFileHaveChangedConflictResolver() {
		super(ConflictType.FIRST_FILE_HAVE_CHANGED);
	}

	@Override
	public void doResolve(Conflict conflict,ProcessData processData) {
		for(int actualColumnNumber = 0; actualColumnNumber <= processData.getMixerConfiguration().getLastColumnToMix(); actualColumnNumber++ ){
			mixCell(actualColumnNumber,processData.getActualRowNumber(),processData);
		}
	}
	
	/***************************************************** Mix Methods ******************************************/
	
	public void mixCell(int columnNumber,int rowNumber,ProcessData processData)  {
		MutableCell<SpreadSheet> originalCell = processData.getOriginalSheet().getCellAt(columnNumber,rowNumber);
		MutableCell<SpreadSheet> firstChangeCell = processData.getFirstChangeSheet().getCellAt(columnNumber,rowNumber);
		setCellValue(originalCell,firstChangeCell.getValue());
		setColorFromCell(originalCell, firstChangeCell);
		
	}

}
