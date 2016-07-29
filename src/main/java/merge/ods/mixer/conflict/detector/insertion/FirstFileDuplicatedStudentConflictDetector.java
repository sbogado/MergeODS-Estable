package merge.ods.mixer.conflict.detector.insertion;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.ConflictType;
import merge.ods.mixer.conflict.detector.FirstFileConflictDetector;

public class FirstFileDuplicatedStudentConflictDetector extends FirstFileConflictDetector {

	public FirstFileDuplicatedStudentConflictDetector() {
		super(ConflictType.FIRST_FILE_DUPLICATED_INSERT_STUDENT);
	}

	@Override
	public void detect(ProcessData processData) {
		Boolean identificationsAreEquals = getActualOriginalIdentification(processData).equals(getActualFirstFileIdentification(processData));
		Boolean identificationIsEmpty = getActualFirstFileIdentification(processData).isEmpty();
		Boolean identificationExists = processData.getStudentIdentifications()
				.contains(getActualFirstFileIdentification(processData));
		Boolean identificationIsAfterOriginal = processData.getStudentIdentifications()
				.indexOf(getActualOriginalIdentification(processData)) < processData.getStudentIdentifications()
						.indexOf(getActualFirstFileIdentification(processData));

		if (!identificationsAreEquals && !identificationIsEmpty && identificationExists
				&& !identificationIsAfterOriginal) {
			addConflict(processData);
		}
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
