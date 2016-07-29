package merge.ods.mixer.strategy;

import java.awt.Color;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import merge.ods.main.ProcessData;
import merge.ods.mixer.configuration.MixerConfiguration;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.ConflictType;

public class ConflictSheet {

	private MixerStrategy strategy;
	private Sheet conflictSheet;
	private String lastRowNumberLogged;

	public ConflictSheet(){
		lastRowNumberLogged = "";
	}
	
	public Sheet getConflictSheet(ProcessData processData) {
		if (conflictSheet == null) {
			if(processData.getOriginal().getSheet(MixerConfiguration.DEFAULT_MAIN_CONFLICT_SPREADSHEET_NAME) == null){
				processData.getOriginal().addSheet(MixerConfiguration.DEFAULT_MAIN_CONFLICT_SPREADSHEET_NAME);
			}	
			conflictSheet = processData.getOriginal().getSheet(MixerConfiguration.DEFAULT_MAIN_CONFLICT_SPREADSHEET_NAME);
			createConflictSheetHeader();
		}
		return conflictSheet;
	}

	private void createConflictSheetHeader() {
		conflictSheet.ensureColumnCount(8);
		conflictSheet.ensureRowCount(1);
		// Linea
		conflictSheet.setValueAt("LINEA", 0, 0);
		// PK
		conflictSheet.setValueAt("PK", 1, 0);
		// Tipo de conflicto
		conflictSheet.setValueAt("TIPO DE CONFLICTO", 2, 0);
		// Columna
		conflictSheet.setValueAt("COLUMNA", 3, 0);
		// Dato Original
		conflictSheet.setValueAt("DATO ORIGINAL", 4, 0);
		// Dato de cambio 1
		conflictSheet.setValueAt("DATO DE CAMBIO 1", 5, 0);
		// Dato de cambio 2
		conflictSheet.setValueAt("DATO DE CAMBIO 2", 6, 0);
		// Sugerencia
		conflictSheet.setValueAt("SUGERENCIA", 7, 0);
	}

	public void logConflict(Conflict conflict,ProcessData processData){

		getConflictSheet(processData).ensureRowCount(getConflictSheet(processData).getRowCount() + 1);
		int actualConflictRow = getConflictSheet(processData).getRowCount() - 1;
		String firstFileRowNumberToLog = conflict.getFirstFileRowNumber() != "" ? " , ".concat(conflict.getFirstFileRowNumber()) : "";
		String secondFileRowNumberToLog = conflict.getSecondFileRowNumber() != "" ? " , ".concat(conflict.getSecondFileRowNumber()) : "";
		Boolean isRowLog = conflict.getType().equals(ConflictType.BOTH_FILES_HAVE_CHANGED);
		Boolean haveToLogRowFile = !conflict.getOriginalFileRowNumber().equals(getLastRowNumberLogged());
		
		if(isRowLog){
			if(haveToLogRowFile){
				// Linea
				getConflictSheet(processData).setValueAt(conflict.getOriginalFileRowNumber(), 0, actualConflictRow);
				// PK
				getConflictSheet(processData).setValueAt(conflict.getIdentification(), 1, actualConflictRow);
				// Tipo de conflicto
				getConflictSheet(processData).setValueAt(conflict.getType().getConflictName(), 2, actualConflictRow);
				
				setLastRowNumberLogged(conflict.getOriginalFileRowNumber());
				getConflictSheet(processData).ensureRowCount(getConflictSheet(processData).getRowCount() + 1);
				actualConflictRow = getConflictSheet(processData).getRowCount() - 1;
			}
		}
		else{
				// Linea
				getConflictSheet(processData).setValueAt(conflict.getOriginalFileRowNumber()+firstFileRowNumberToLog+secondFileRowNumberToLog, 0, actualConflictRow);
				// PK
				getConflictSheet(processData).setValueAt(conflict.getOriginalData(), 1, actualConflictRow);
				
				// Tipo de conflicto
				getConflictSheet(processData).setValueAt(conflict.getType().getConflictName(), 2, actualConflictRow);
			
		}
		// Columna
		getConflictSheet(processData).setValueAt(conflict.getColumn(), 3, actualConflictRow);
		// Dato Original
		getConflictSheet(processData).setValueAt(conflict.getOriginalData(), 4, actualConflictRow);
		setColorFromCell(getConflictSheet(processData).getCellAt(4, actualConflictRow), conflict.getOriginalColor());
		// Dato de cambio 1
		getConflictSheet(processData).setValueAt(conflict.getFirstChangedData(), 5, actualConflictRow);
		setColorFromCell(getConflictSheet(processData).getCellAt(5, actualConflictRow), conflict.getFirstFileColor());
		// Dato de cambio 2
		getConflictSheet(processData).setValueAt(conflict.getSecondChangedData(), 6, actualConflictRow);
		setColorFromCell(getConflictSheet(processData).getCellAt(6, actualConflictRow), conflict.getSecondFileColor());
		// Sugerencia
		getConflictSheet(processData).setValueAt(conflict.getExtraData(), 7, actualConflictRow);
	}

	public void setColorFromCell(MutableCell<SpreadSheet> cellToColor,Color color) {
		if(color != null){
			cellToColor.setBackgroundColor(color);
		}
	}
	
	public String getLastRowNumberLogged() {
		return lastRowNumberLogged;
	}

	public void setLastRowNumberLogged(String lastRowNumberLogged) {
		this.lastRowNumberLogged = lastRowNumberLogged;
	}

	public MixerStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(MixerStrategy strategy) {
		this.strategy = strategy;
	}
}
