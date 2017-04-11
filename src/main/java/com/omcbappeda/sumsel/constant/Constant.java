package com.omcbappeda.sumsel.constant;

public enum Constant {
	EMPTY_STRING(""),
	Active("1"),
	InActive("0"),
	YES("Y"),
	NO("N"),
	DATEFORMAT("MM-dd-yyyy HH:mm:ss"),
	MAN("L"),
	WOMAN("P"),
	
	/* Activity Log */
	LOGIN("LOGIN"),
	LOGOUT("LOGOUT"),
	
	APPROVE_USER("APPROVE USER"),
	REJECT_USER("REJECT USER"),
	UPDATE_USER("UPDATE USER"),
	DELETE_USER("DELETE USER"),
	SETTING_USER("DELETE USER"),

	SAVE_DEPT("SAVE DEPARTEMENT"),
	UPDATE_DEPT("UPDATE DEPARTEMENT"),
	DELETE_DEPT("DELETE DEPARTEMENT"),

	SAVE_DIR("SAVE DIRECTORY"),
	UPDATE_DIR("UPDATE DIRECTORY"),
	MOVE_DIRECTORY("MOVE DIRECTORY"),
	DELETE_DIR("DELETE DIRECTORY"),

	SAVE_FILE("SAVE FILE"),
	UPDATE_FILE("UPDATE FILE"),
	MOVE_FILE("MOVE FILE"),
	DELETE_FILE("DELETE FILE"),
	
	// User
	REQUEST("request"),
	APPROVED("approve"),
	REJECT("reject"),
	ROOT_FOLDER("root"),
	;
	private String code;
	private Constant(String code){
		this.code = code;
	}
	
	@Override
	public String toString() {
		return code;
	}
}
