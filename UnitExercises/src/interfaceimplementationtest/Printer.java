package interfaceimplementationtest;

public interface Printer {
	
	public void printSomething();
	
	public static final Integer name = 5;
	public static final Printer NamePrinter = new Printer(){
		public void printSomething()
		{
			System.out.println("My name is xyz");
		}
	};
	
	public static final Printer GenderPrinter = new Printer(){
		public void printSomething()
		{
			System.out.println("My gender is N/A");
		}
	};
}
