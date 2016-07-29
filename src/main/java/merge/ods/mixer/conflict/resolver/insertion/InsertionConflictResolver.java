package merge.ods.mixer.conflict.resolver.insertion;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.ConflictType;
import merge.ods.mixer.conflict.resolver.ConflictResolver;

public abstract class InsertionConflictResolver extends ConflictResolver {

	public InsertionConflictResolver(ConflictType type) {
		super(type);
	}

	@Override
	public void doResolve(Conflict conflict, ProcessData processData) {
		processData.getConflictSheet().logConflict(conflict, processData);
	}
}
