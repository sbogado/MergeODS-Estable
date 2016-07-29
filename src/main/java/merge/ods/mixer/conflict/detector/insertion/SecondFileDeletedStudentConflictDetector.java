package merge.ods.mixer.conflict.detector.insertion;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.ConflictType;
import merge.ods.mixer.conflict.detector.SecondFileConflictDetector;

public class SecondFileDeletedStudentConflictDetector extends SecondFileConflictDetector {

	public SecondFileDeletedStudentConflictDetector() {
		super(ConflictType.SECOND_FILE_DELETED_STUDENT);
	}

	@Override
	public void detect(ProcessData processData) {
		Boolean identificationsAreEquals = getActualOriginalIdentification(processData)
				.equals(getActualSecondFileIdentification(processData));
		Boolean identificationIsEmpty = getActualSecondFileIdentification(processData).isEmpty();
		Boolean identificationExists = processData.getStudentIdentifications()
				.contains(getActualSecondFileIdentification(processData));
		Boolean identificationIsAfterOriginal = processData.getStudentIdentifications()
				.indexOf(getActualOriginalIdentification(processData)) < processData.getStudentIdentifications()
						.indexOf(getActualSecondFileIdentification(processData));
		
		if ((!identificationsAreEquals && !identificationIsEmpty && identificationExists
				&& identificationIsAfterOriginal)  || (!identificationsAreEquals && identificationIsEmpty)) {
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
