package banking;

// would like lombok here
public class Company extends AccountHolder {
	private final String companyName;

	public Company(String companyName, int taxId) {
		super(taxId);
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

}
