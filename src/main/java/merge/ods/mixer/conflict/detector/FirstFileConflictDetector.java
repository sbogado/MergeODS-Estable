package merge.ods.mixer.conflict.detector;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.ConflictType;

public abstract class FirstFileConflictDetector extends ConflictDetector{

	public FirstFileConflictDetector(ConflictType conflictType) {
		super(conflictType);
	}

	protected void addFileDephasing(int dephasing,ProcessData processData) {
		processData.setFirstFileDephasing(processData.getFirstFileDephasing() +dephasing);
	}
}
