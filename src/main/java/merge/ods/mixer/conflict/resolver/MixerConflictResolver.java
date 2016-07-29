package merge.ods.mixer.conflict.resolver;

import java.util.List;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;

public class MixerConflictResolver {

	List<ConflictResolver> conflictResolvers;
	
	public MixerConflictResolver(List<ConflictResolver> conflictResolvers){
		this.conflictResolvers = conflictResolvers;
	}
	
	public void resolveConflict(Conflict conflict,ProcessData processData){
		for(ConflictResolver conflictResolver : this.conflictResolvers){
			conflictResolver.resolve(conflict,processData);
		}
	}
	
}
