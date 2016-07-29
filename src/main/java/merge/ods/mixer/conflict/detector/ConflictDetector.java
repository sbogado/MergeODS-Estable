package merge.ods.mixer.conflict.detector;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.ConflictType;

public abstract class ConflictDetector {

	private ConflictType conflictType;
	
	public ConflictDetector(ConflictType conflictType) {
		this.setConflictType(conflictType);
	}

	public abstract void detect(ProcessData processData);
	

	public void addConflict(ProcessData processData) {
		processData.setConflictDetected(true);
		processData.getConflictsToResolve().add(getConflict(processData));
	}
	
	public abstract Conflict getConflict(ProcessData processData);

	public String getActualOriginalIdentification(ProcessData processData){
		return processData.getOriginalSheet()
				.getCellAt(processData.getMixerConfiguration().getStudentIdentificationColumn(),
						processData.getActualRowNumber())
				.getValue().toString();
	}
	public String getActualFirstFileIdentification(ProcessData processData){
		return processData.getFirstChangeSheet()
				.getCellAt(processData.getMixerConfiguration().getStudentIdentificationColumn(),
						processData.getFirstFileRowNumber())
				.getValue().toString();
	}
	
	public String getActualSecondFileIdentification(ProcessData processData){
		return processData.getSecondChangeSheet()
				.getCellAt(processData.getMixerConfiguration().getStudentIdentificationColumn(),
						processData.getSecondFileRowNumber())
				.getValue().toString();
	}
	
	public String getOriginalFileRowToLog(ProcessData processData){
		return String.valueOf(processData.getActualRowNumber() + 1);
	}
	
	public String getFirstFileFileRowToLog(ProcessData processData){
		return String.valueOf(processData.getFirstFileRowNumber() + 1);
	}
	
	public String getSecondFileRowToLog(ProcessData processData){
		return String.valueOf(processData.getSecondFileRowNumber() + 1);
	}
	
	public ConflictType getConflictType() {
		return conflictType;
	}

	public void setConflictType(ConflictType conflictType) {
		this.conflictType = conflictType;
	}

}
