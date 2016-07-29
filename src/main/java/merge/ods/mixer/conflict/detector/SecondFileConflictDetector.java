package merge.ods.mixer.conflict.detector;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.ConflictType;

public abstract class SecondFileConflictDetector extends ConflictDetector{

	public SecondFileConflictDetector(ConflictType conflictType) {
		super(conflictType);
	}

	protected void addFileDephasing(int dephasing,ProcessData processData) {
		processData.setSecondFileDephasing(processData.getSecondFileDephasing() +dephasing);
	}
}
