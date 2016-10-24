package coincount;

public class Wallet {
	public static final Integer NICKEL=5;
	public static final Integer DIME=10;
	public static final Integer QUARTER=25;
	
	public int numNickes;
	public int numDimes;
	public int numQuarters;
	public int total;
	
	public Wallet()
	{
		this.numNickes=0;
		this.numDimes=0;
		this.numQuarters=0;
		this.total=0;
	}
	
	public Wallet(int nickels, int dimes, int quarters)
	{
		this.numNickes=nickels;
		this.numDimes=dimes;
		this.numQuarters=quarters;
		this.total=this.numNickes * NICKEL + this.numDimes * DIME + this.numQuarters * QUARTER;
	}
	
	public Wallet(Wallet other)
	{
		this.numDimes += other.numDimes;
		this.numNickes += other.numNickes;
		this.numQuarters += other.numQuarters;
		this.total=this.numNickes * NICKEL + this.numDimes * DIME + this.numQuarters * QUARTER;
	}
	
	public int getTotal()
	{
		return (this.numDimes * DIME) + (this.numNickes * NICKEL) + (this.numQuarters * QUARTER);
	}
	
	public void setNumQuarters(int quarters)
	{
		this.numQuarters = quarters;
	}
	
	public void setNumDimes(int dimes)
	{
		this.numDimes = dimes;
	}
	
	@Override
	public String toString() {
		return "Wallet [numNickes=" + numNickes + ", numDimes=" + numDimes
				+ ", numQuarters=" + numQuarters + ", total=" + this.getTotal() + "]";
	}

	public void setNumNickels(int nickels)
	{
		this.numNickes = nickels;
	}
	
	public Wallet incrementNumNickels()
	{
		this.numNickes += 1;
		return this;
	}
	
	public Wallet incrementNumDimes()
	{
		this.numDimes += 1;
		return this;
	}
	
	public Wallet incrementNumQuarters()
	{
		this.numQuarters += 1;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numDimes;
		result = prime * result + numNickes;
		result = prime * result + numQuarters;
		result = prime * result + total;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wallet other = (Wallet) obj;
		if (numDimes != other.numDimes)
			return false;
		if (numNickes != other.numNickes)
			return false;
		if (numQuarters != other.numQuarters)
			return false;
		if (total != other.total)
			return false;
		return true;
	}
}
