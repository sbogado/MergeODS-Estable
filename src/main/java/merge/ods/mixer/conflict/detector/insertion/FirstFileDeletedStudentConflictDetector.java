package merge.ods.mixer.conflict.detector.insertion;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.ConflictType;
import merge.ods.mixer.conflict.detector.FirstFileConflictDetector;

public class FirstFileDeletedStudentConflictDetector extends FirstFileConflictDetector {

	public FirstFileDeletedStudentConflictDetector() {
		super(ConflictType.FIRST_FILE_DELETED_STUDENT);
	}

	@Override
	public void detect(ProcessData processData) {
		Boolean identificationsAreEquals = getActualOriginalIdentification(processData).equals(getActualFirstFileIdentification(processData));
		Boolean identificationIsEmpty = getActualFirstFileIdentification(processData).isEmpty();
		Boolean identificationExists = processData.getStudentIdentifications().contains(getActualFirstFileIdentification(processData));
		Boolean identificationIsAfterOriginal = processData.getStudentIdentifications().indexOf(getActualOriginalIdentification(processData)) < processData.getStudentIdentifications().indexOf(getActualFirstFileIdentification(processData));

		if ((!identificationsAreEquals && !identificationIsEmpty && identificationExists
				&& identificationIsAfterOriginal) || (!identificationsAreEquals && identificationIsEmpty)) {
			addConflict(processData);
		}

	}

	public void addConflict(ProcessData processData) {
		super.addConflict(processData);
		addFileDephasing(-1,processData);
	}

	public Conflict getConflict(ProcessData processData) {
		return new Conflict(getConflictType(), getActualOriginalIdentification(processData), "","", getOriginalFileRowToLog(processData), getFirstFileFileRowToLog(processData),
				getSecondFileRowToLog(processData), getActualOriginalIdentification(processData), "", "");
	}

}
