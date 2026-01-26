package flyweight;

public enum TreeType {
	DECIDUOUS("활엽수"),
	CONIFER("침엽수");

	private final String displayName;

	TreeType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
