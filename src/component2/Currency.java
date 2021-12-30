package component2;

/*class defination for Currency
 * for storing data about currency*/
public class Currency {
	
	//attributes of Currency
	//name stores particular Currency name 
	//symbol stores symbol of currency
	private String name, symbol;
	private double factor;// comversion factor
	
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
		//this referes to current instance itself
		this.name=name;
		this.factor=factor;
		this.symbol=symbol;
	}
	
	//setters
	//set name of currency
	public void setName(String name) {
		this.name = name;
	}
	//set symbol of currency
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	//set factor of currency
	public void setFactor(double factor) {
		this.factor = factor;
	}
	
	//getters
	//returns name of currency when called
	public String getName() {
		return name;
	}
	//returns symbol of currency when called
	public String getSymbol() {
		return symbol;
	}
	//returns factor of currency when called
	public double getFactor() {
		return factor;
	}

}
