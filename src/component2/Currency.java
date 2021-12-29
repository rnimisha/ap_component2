package component2;

//class defination for Currency
public class Currency {
	
//	variables declaration
	private String name, symbol;
	private double factor;
	
	//default constructor
	public Currency()
	{
		name="";
		factor=0.0;
		symbol="";
		
	}
	//parameterized constructor
	public Currency(String name, double factor, String symbol)
	{
			this.name=name;
			this.factor=factor;
			this.symbol=symbol;
	}
	
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
