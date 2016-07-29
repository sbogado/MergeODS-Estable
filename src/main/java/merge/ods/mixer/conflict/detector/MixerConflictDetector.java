package merge.ods.mixer.conflict.detector;

import java.util.List;

import merge.ods.main.ProcessData;

public class MixerConflictDetector {
	
	List<ConflictDetector> conflictDetectors;
	
	public MixerConflictDetector(List<ConflictDetector> conflictDetectors){
		this.conflictDetectors = conflictDetectors;
	}
	
	public void detectConflicts(ProcessData processData){
		processData.setMustContinueDetecting(false);
		processData.setConflictDetected(false);
		detectConflict(processData);
		while(processData.getMustContinueDetecting()){
			processData.setMustContinueDetecting(false);
			processData.setConflictDetected(false);
			detectConflict(processData);
		}
	}

	private void detectConflict(ProcessData processData) {
		for(ConflictDetector conflictDetector : this.conflictDetectors){
			if(processData.getConflictDetected()){
				break;
			}
			conflictDetector.detect(processData);
		}
	}

}
