package merge.ods.mixer.conflict.resolver.row;

import org.jopendocument.dom.spreadsheet.MutableCell;
import static merge.ods.helper.CellHelper.backgroundColorOf;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import merge.ods.helper.CellHelper;
import merge.ods.mixer.conflict.ConflictType;
import merge.ods.mixer.conflict.resolver.ConflictResolver;

public abstract class RowConflictResolver extends ConflictResolver{

	public RowConflictResolver(ConflictType type) {
		super(type);
	}

	public void setCellValue(MutableCell<SpreadSheet> cell,Object value){
		cell.setValue(value);  
	}
	
	public void setColorFromCell(MutableCell<SpreadSheet> cellToColor,MutableCell<SpreadSheet> cellColorOwner) {
		if(!backgroundColorOf(cellToColor).equals(backgroundColorOf(cellColorOwner))){
			CellHelper.setBackgroundColor(cellToColor,backgroundColorOf(cellColorOwner));
		}
	
	}
}
