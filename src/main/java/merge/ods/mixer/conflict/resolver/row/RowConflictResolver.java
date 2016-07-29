package merge.ods.mixer.conflict.resolver.row;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

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
		if(!cellToColor.getStyle().getBackgroundColor(cellToColor).equals(cellColorOwner.getStyle().getBackgroundColor(cellColorOwner))){
			cellToColor.setBackgroundColor(cellColorOwner.getStyle().getBackgroundColor(cellColorOwner));
		}
	
	}
}
