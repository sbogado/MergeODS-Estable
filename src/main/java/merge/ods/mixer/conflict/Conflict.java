package merge.ods.mixer.conflict;

import java.awt.Color;

public class Conflict {

	private ConflictType type;
	
	private String identification;
	private String extraData;
	private String column;
	private String originalFileRowNumber;
	private String firstFileRowNumber;
	private String secondFileRowNumber;
	
	private String originalData;
	private String firstChangedData;
	private String secondChangedData;
	
	private Color originalColor;
	private Color firstFileColor;
	private Color secondFileColor;
	
	public Conflict(ConflictType type){
		this.type = type;
	}

	public Conflict(ConflictType type,String identification, String extraData,String column, String originalFileRowNumber, String firstFileRowNumber,
			String secondFileRowNumber, String originalData, String firstChangedData,
			String secondChangedData) {
		this.type = type;
		this.identification = identification;
		this.extraData = extraData;
		this.column = column;
		this.originalFileRowNumber = originalFileRowNumber;
		this.firstFileRowNumber = firstFileRowNumber;
		this.secondFileRowNumber = secondFileRowNumber;
		this.originalData = originalData;
		this.firstChangedData = firstChangedData;
		this.secondChangedData = secondChangedData;
	}
	
	public Conflict(ConflictType type, String identification, String extraData,String column, String originalFileRowNumber, String firstFileRowNumber,
			String secondFileRowNumber, String originalData, String firstChangedData,
			String secondChangedData,Color originalColor,Color firstFileColor,Color secondFileColor) {
		this.type = type;
		this.identification = identification;
		this.extraData = extraData;
		this.column = column;
		this.originalFileRowNumber = originalFileRowNumber;
		this.firstFileRowNumber = firstFileRowNumber;
		this.secondFileRowNumber = secondFileRowNumber;
		this.originalData = originalData;
		this.firstChangedData = firstChangedData;
		this.secondChangedData = secondChangedData;
		this.originalColor = originalColor ;
		this.firstFileColor = firstFileColor;
		this.secondFileColor = secondFileColor;
	}



	public ConflictType getType() {
		return type;
	}

	public void setType(ConflictType type) {
		this.type = type;
	}

	public String getExtraData() {
		return extraData;
	}

	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}

	public String getOriginalFileRowNumber() {
		return originalFileRowNumber;
	}

	public void setOriginalFileRowNumber(String originalFileRowNumber) {
		this.originalFileRowNumber = originalFileRowNumber;
	}

	public String getFirstFileRowNumber() {
		return firstFileRowNumber;
	}

	public void setFirstFileRowNumber(String firstFileRowNumber) {
		this.firstFileRowNumber = firstFileRowNumber;
	}

	public String getSecondFileRowNumber() {
		return secondFileRowNumber;
	}

	public void setSecondFileRowNumber(String secondFileRowNumber) {
		this.secondFileRowNumber = secondFileRowNumber;
	}

	public String getOriginalData() {
		return originalData;
	}

	public void setOriginalData(String originalData) {
		this.originalData = originalData;
	}

	public String getFirstChangedData() {
		return firstChangedData;
	}

	public void setFirstChangedData(String firstChangedData) {
		this.firstChangedData = firstChangedData;
	}

	public String getSecondChangedData() {
		return secondChangedData;
	}

	public void setSecondChangedData(String secondChangedData) {
		this.secondChangedData = secondChangedData;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Color getOriginalColor() {
		return originalColor;
	}

	public void setOriginalColor(Color originalColor) {
		this.originalColor = originalColor;
	}

	public Color getFirstFileColor() {
		return firstFileColor;
	}

	public void setFirstFileColor(Color firstFileColor) {
		this.firstFileColor = firstFileColor;
	}

	public Color getSecondFileColor() {
		return secondFileColor;
	}

	public void setSecondFileColor(Color secondFileColor) {
		this.secondFileColor = secondFileColor;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}
}
