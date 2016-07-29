package merge.ods.mixer.strategy;

import java.util.ArrayList;
import java.util.List;

import merge.ods.mixer.conflict.resolver.ConflictResolver;
import merge.ods.mixer.conflict.resolver.MixerConflictResolver;
import merge.ods.mixer.conflict.resolver.row.BothFilesHaveChangedWithConflictSheetLoggerConflictResolver;
import merge.ods.mixer.conflict.resolver.row.FirstFileHaveChangedConflictResolver;
import merge.ods.mixer.conflict.resolver.row.NoChangesConflictResolver;
import merge.ods.mixer.conflict.resolver.row.SecondFileHaveChangedConflictResolver;

public class SimpleWithLoggerMixerStrategy extends SimpleMixerStrategy{

	public SimpleWithLoggerMixerStrategy() {
		super();
		List<ConflictResolver> conflictResolvers = new ArrayList<ConflictResolver>();
		conflictResolvers.add(new NoChangesConflictResolver());
		conflictResolvers.add(new FirstFileHaveChangedConflictResolver());
		conflictResolvers.add(new SecondFileHaveChangedConflictResolver());
		conflictResolvers.add(new BothFilesHaveChangedWithConflictSheetLoggerConflictResolver());
		MixerConflictResolver mixerConflictResolver = new MixerConflictResolver(conflictResolvers);
		setMixerConflictResolver(mixerConflictResolver);
	}

}
