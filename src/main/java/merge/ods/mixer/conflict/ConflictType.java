package merge.ods.mixer.conflict;


public enum ConflictType {

	NO_CHANGES(""),
	FIRST_FILE_HAVE_CHANGED("First File Have Changed"),
	SECONDS_FILE_HAVE_CHANGED("Second File Have Changed"),
	BOTH_FILES_HAVE_CHANGED("Both Files Have Changed"),
	FIRST_FILE_INSERTED_STUDENT("First File Insert"),
	SECOND_FILE_INSERTED_STUDENT("Second File Insert"),
	FIRST_FILE_DELETED_STUDENT("First File Delete"),
	SECOND_FILE_DELETED_STUDENT("Second File Delete"),
	FIRST_FILE_DUPLICATED_INSERT_STUDENT("First File Duplicate"),
	SECOND_FILE_DUPLICATED_INSERT_STUDENT("Second File Duplicate");

	private String conflictName;

	ConflictType(String conflictName){
		this.conflictName = conflictName;
	}

	public String getConflictName() {
		return conflictName;
	}

	public void setConflictName(String conflictName) {
		this.conflictName = conflictName;
	}
	
}
