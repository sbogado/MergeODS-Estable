package merge.ods.mixer.conflict.resolver.row;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.ConflictType;

public class NoChangesConflictResolver extends RowConflictResolver{

	public NoChangesConflictResolver() {
		super(ConflictType.NO_CHANGES);
	}

	@Override
	public void doResolve(Conflict conflict,ProcessData processData) {
	}

}
