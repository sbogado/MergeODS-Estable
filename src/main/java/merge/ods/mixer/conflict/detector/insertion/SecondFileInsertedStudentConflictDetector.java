package merge.ods.mixer.conflict.detector.insertion;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.ConflictType;
import merge.ods.mixer.conflict.detector.SecondFileConflictDetector;

public class SecondFileInsertedStudentConflictDetector extends SecondFileConflictDetector {

	public SecondFileInsertedStudentConflictDetector() {
		super(ConflictType.SECOND_FILE_INSERTED_STUDENT);
	}

	@Override
	public void detect(ProcessData processData) {
		Boolean identificationAreEquals = getActualOriginalIdentification(processData).equals(getActualSecondFileIdentification(processData));
		Boolean identificationIsEmpty = getActualSecondFileIdentification(processData).isEmpty();
		Boolean identificationExists = processData.getStudentIdentifications()
				.contains(getActualSecondFileIdentification(processData));
		
		if (!identificationAreEquals && !identificationIsEmpty && !identificationExists) {
			addConflict(processData);
			addNewIdentification(processData);
		}
	}

	private void addNewIdentification(ProcessData processData) {
		processData.getStudentIdentifications().add(getActualSecondFileIdentification(processData));
	}
	
	public void addConflict(ProcessData processData) {
		super.addConflict(processData);
		addFileDephasing(1,processData);
		processData.setMustContinueDetecting(true);
	}

	public Conflict getConflict(ProcessData processData) {
		return new Conflict(getConflictType(), getActualOriginalIdentification(processData), "","", getOriginalFileRowToLog(processData), getFirstFileFileRowToLog(processData),
				getSecondFileRowToLog(processData), "", "", getActualSecondFileIdentification(processData));
	}

}
