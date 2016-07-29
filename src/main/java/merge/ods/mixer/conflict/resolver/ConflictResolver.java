package merge.ods.mixer.conflict.resolver;

import merge.ods.main.ProcessData;
import merge.ods.mixer.conflict.Conflict;
import merge.ods.mixer.conflict.ConflictType;

public abstract class ConflictResolver {

	private ConflictType conflictType;

	public ConflictResolver(ConflictType type) {
		this.setConflictType(type);
	}

	public void resolve(Conflict conflict,ProcessData processData) {
		if (accept(conflict)) {
			doResolve(conflict,processData);
		}
	}
	
	public abstract void doResolve(Conflict conflict,ProcessData processData);

	public boolean accept(Conflict conflict) {
		return conflict.getType().equals(getConflictType());
	}
	
	public ConflictType getConflictType() {
		return conflictType;
	}

	public void setConflictType(ConflictType conflictType) {
		this.conflictType = conflictType;
	}

}
