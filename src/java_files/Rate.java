package java_files;

public class Rate implements java.io.Serializable {

	private Integer positiveRates;
	private Integer negativeRates;
	
	
	public Rate() {
		super();
	}
	public Rate(Integer positiveRates, Integer negativeRates) {
		super();
		this.positiveRates = positiveRates;
		this.negativeRates = negativeRates;
	}
	public Integer getPositiveRates() {
		return positiveRates;
	}
	public void setPositiveRates(Integer positiveRates) {
		this.positiveRates = positiveRates;
	}
	public Integer getNegativeRates() {
		return negativeRates;
	}
	public void setNegativeRates(Integer negativeRates) {
		this.negativeRates = negativeRates;
	}
	
	
}
