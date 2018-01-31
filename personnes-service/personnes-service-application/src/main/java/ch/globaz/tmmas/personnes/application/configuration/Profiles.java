package ch.globaz.tmmas.personnes.application.configuration;

public enum Profiles {

	DEV("dev"),
	PRODUCTION("prod"),
	IN_MEMORY_STORAGE("inMemory"),
	HIBERNATE_STOARGE("hibernate");

	private String profileValue;

	Profiles(String profileValue){
		this.profileValue = profileValue;
	}

	public String value() {
		return this.profileValue;
	}
}
