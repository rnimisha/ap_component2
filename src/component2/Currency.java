package component2;

//class defination for Currency
public class Currency {
	
//	variables declaration
	private String name, symbol;
	private double factor;
	
	//setters
	public void setName(String name) {
		this.name = name;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setFactor(double factor) {
		this.factor = factor;
	}
	
	//getters
	public String getName() {
		return name;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public double getFactor() {
		return factor;
	}

}
