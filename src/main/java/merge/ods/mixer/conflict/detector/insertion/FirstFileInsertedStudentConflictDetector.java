package merge.ods.mixer.conflict.detector.insertion;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.ConflictType;
import merge.ods.mixer.conflict.detector.FirstFileConflictDetector;

public class FirstFileInsertedStudentConflictDetector extends FirstFileConflictDetector {

	public FirstFileInsertedStudentConflictDetector() {
		super(ConflictType.FIRST_FILE_INSERTED_STUDENT);
	}

	@Override
	public void detect(ProcessData processData) {
		Boolean identificationsAreEquals = getActualOriginalIdentification(processData).equals(getActualFirstFileIdentification(processData));
		Boolean identificationIsEmpty = getActualFirstFileIdentification(processData).isEmpty();
		Boolean identificationExists = processData.getStudentIdentifications()
				.contains(getActualFirstFileIdentification(processData));

		if (!identificationsAreEquals && !identificationIsEmpty && !identificationExists) {
			addConflict(processData);
			addNewIdentification(processData);
		}

	}

	private void addNewIdentification(ProcessData processData) {
		processData.getStudentIdentifications().add(getActualFirstFileIdentification(processData));
	}

	public void addConflict(ProcessData processData) {
		super.addConflict(processData);
		addFileDephasing(1,processData);
		processData.setMustContinueDetecting(true);
	}

	public Conflict getConflict(ProcessData processData) {
		return new Conflict(getConflictType(), getActualOriginalIdentification(processData), "","", getOriginalFileRowToLog(processData), getFirstFileFileRowToLog(processData),
				getSecondFileRowToLog(processData), "", getActualFirstFileIdentification(processData), "");
	}

}
