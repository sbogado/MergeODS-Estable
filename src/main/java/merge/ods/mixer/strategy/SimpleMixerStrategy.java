package merge.ods.mixer.strategy;

import java.util.ArrayList;
import java.util.List;

import merge.ods.mixer.conflict.detector.ConflictDetector;
import merge.ods.mixer.conflict.detector.MixerConflictDetector;
import merge.ods.mixer.conflict.detector.insertion.FirstFileDeletedStudentConflictDetector;
import merge.ods.mixer.conflict.detector.insertion.FirstFileDuplicatedStudentConflictDetector;
import merge.ods.mixer.conflict.detector.insertion.FirstFileInsertedStudentConflictDetector;
import merge.ods.mixer.conflict.detector.insertion.SecondFileDeletedStudentConflictDetector;
import merge.ods.mixer.conflict.detector.insertion.SecondFileDuplicatedStudentConflictDetector;
import merge.ods.mixer.conflict.detector.insertion.SecondFileInsertedStudentConflictDetector;
import merge.ods.mixer.conflict.detector.row.RowConflictDetector;
import merge.ods.mixer.conflict.resolver.ConflictResolver;
import merge.ods.mixer.conflict.resolver.MixerConflictResolver;
import merge.ods.mixer.conflict.resolver.insertion.FirstFileDeletedStudentConflictResolver;
import merge.ods.mixer.conflict.resolver.insertion.FirstFileDuplicateInsertStudenConflictResolver;
import merge.ods.mixer.conflict.resolver.insertion.FirstFileInsertedStudenConflictResolver;
import merge.ods.mixer.conflict.resolver.insertion.SecondFileDeletedStudentConflictResolver;
import merge.ods.mixer.conflict.resolver.insertion.SecondFileDuplicateInsertStudenConflictResolver;
import merge.ods.mixer.conflict.resolver.insertion.SecondFileInsertedStudenConflictResolver;
import merge.ods.mixer.conflict.resolver.row.BothFilesHaveChangedWithConflictSheetLoggerConflictResolver;
import merge.ods.mixer.conflict.resolver.row.FirstFileHaveChangedConflictResolver;
import merge.ods.mixer.conflict.resolver.row.NoChangesConflictResolver;
import merge.ods.mixer.conflict.resolver.row.SecondFileHaveChangedConflictResolver;


public class SimpleMixerStrategy extends MixerStrategy {

	public SimpleMixerStrategy() {
		super();
		List<ConflictResolver> conflictResolvers = new ArrayList<ConflictResolver>();
		conflictResolvers.add(new NoChangesConflictResolver());
		conflictResolvers.add(new FirstFileHaveChangedConflictResolver());
		conflictResolvers.add(new SecondFileHaveChangedConflictResolver());
		conflictResolvers.add(new BothFilesHaveChangedWithConflictSheetLoggerConflictResolver());
		conflictResolvers.add(new FirstFileDeletedStudentConflictResolver());
		conflictResolvers.add(new FirstFileDuplicateInsertStudenConflictResolver());
		conflictResolvers.add(new FirstFileInsertedStudenConflictResolver());
		conflictResolvers.add(new SecondFileDeletedStudentConflictResolver());
		conflictResolvers.add(new SecondFileDuplicateInsertStudenConflictResolver());
		conflictResolvers.add(new SecondFileInsertedStudenConflictResolver());
		
		MixerConflictResolver mixerConflictResolver = new MixerConflictResolver(conflictResolvers);
		setMixerConflictResolver(mixerConflictResolver);
		
		List<ConflictDetector> conflictDetectors = new ArrayList<ConflictDetector>();
		conflictDetectors.add(new FirstFileInsertedStudentConflictDetector());
		conflictDetectors.add(new FirstFileDuplicatedStudentConflictDetector());
		conflictDetectors.add(new FirstFileDeletedStudentConflictDetector());
		conflictDetectors.add(new SecondFileInsertedStudentConflictDetector());
		conflictDetectors.add(new SecondFileDuplicatedStudentConflictDetector());
		conflictDetectors.add(new SecondFileDeletedStudentConflictDetector());
		conflictDetectors.add(new RowConflictDetector());
		
		MixerConflictDetector mixerConflictDetector = new MixerConflictDetector(conflictDetectors);
		setMixerConflictDetector(mixerConflictDetector);
		
	}

}
