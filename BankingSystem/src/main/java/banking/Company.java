package banking;

public class Company  {
	private final String companyName;
	private final int taxId;

	public Company(String companyName, int taxId) {
		this.companyName = companyName;
		this.taxId = taxId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public int getTaxId() {
		return taxId;
	}
}
