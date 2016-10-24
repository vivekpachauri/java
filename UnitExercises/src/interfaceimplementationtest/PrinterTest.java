package interfaceimplementationtest;

public class PrinterTest {

	public static void main (String[] args ){
		Printer myPrinter = Printer.NamePrinter;
		Printer gPrinter = Printer.GenderPrinter;
		myPrinter.printSomething();
		
		gPrinter.printSomething();
	}
}
