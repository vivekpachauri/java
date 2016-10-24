package coincount;

import java.util.HashSet;
import java.util.Set;

public class Driver {
	public static long counter = 0;
	public static void main (String[] args )
	{
		Driver driver = new Driver();
		driver.init();
	}
	
	public void local()
	{
		
	}
	
	public void init()
	{
/*		HashSet<Wallet> wallets = new HashSet<>();
		this.increment(new Wallet(), 135, wallets);
		for ( Wallet wallet : wallets ) {
			System.out.println(wallet);
		}
		System.out.println("total way: " + wallets.size());
		System.out.println("counter: " + counter);*/
		this.coinCount(125);
	}
	

	/*
	 * This method is to identify the way target could be reached using the
	 * minimum number of coins
	 */
	public void coinCount(int value)
	{
		Wallet wallet = coinCountHelper(value, new Wallet());
		System.out.println(wallet);
	}
	
	private Wallet coinCountHelper(int valueLeft, Wallet wallet)
	{
		if ( valueLeft == 0 )
			return wallet;
		else if ( valueLeft < 0 )
			return null;
		else
		{
			if ( valueLeft >= Wallet.QUARTER )
			{
				return coinCountHelper(valueLeft - Wallet.QUARTER, wallet.incrementNumQuarters());
			}
			else if ( valueLeft >= Wallet.DIME )
			{
				return coinCountHelper(valueLeft - Wallet.DIME, wallet.incrementNumDimes());
			}
			else if ( valueLeft >= Wallet.NICKEL )
			{
				return coinCountHelper(valueLeft - Wallet.NICKEL, wallet.incrementNumNickels());
			}
			else
			{
				return null;
			}
		}
	}

	/*
	 * This method is to count the number of different ways that target amount
	 * could be reached using the coins of different value
	 */
	public void increment(Wallet wallet, int target, Set<Wallet> wallets )
	{
		counter += 1;
		System.out.println(wallet);
		int total = wallet.getTotal();
		System.out.println("total: " + total);
		if ( wallets.contains(wallet) )
		{
			System.out.println("$$$$ already have this wallet in the solutions so not going to follow this chain");
			return;
		}
		//if wallet is null that means we have reached a condition where we are over 
		//so we return and discard this wallet and not add it to the result wallets
		//termination condition 1  - over the target
		if ( wallet.getTotal() > target)
		{
			System.out.println("terminting chain of wallet: " + wallet);
//			return;
		}
		
		//termination condition 2 - equal to the target
		//in this case we add the final wallet to the result and return back
		else if ( wallet.getTotal()  == target)
		{
			System.out.println("*** reached target with wallet: " + wallet);
			wallets.add(wallet);
//			return;
		}
		else {
			// recursive condition 1 - add another nickel
			Wallet newWallet = new Wallet(wallet.incrementNumNickels());
			if ( wallets.contains(newWallet) )
			{
				System.out.println("$$$$ already have this wallet in the solutions so not going to follow this chain");
				return;
			}
			this.increment(newWallet, target,
					wallets);
			/*
			 * else if (wallet.getTotal() < target) {
			 * 
			 * }
			 */

			// recursive condition 2 - add another dime
			Wallet newWallet2 = new Wallet(wallet.incrementNumDimes());
			if ( wallets.contains(newWallet2) )
			{
				System.out.println("$$$$ already have this wallet in the solutions so not going to follow this chain");
				return;
			}
			this.increment(newWallet2, target,
					wallets);

			// recursive condition 3 - add another quarter
			Wallet newWallet3 = new Wallet(wallet.incrementNumQuarters());
			if ( wallets.contains(newWallet3) )
			{
				System.out.println("$$$$ already have this wallet in the solutions so not going to follow this chain");
				return;
			}
			this.increment(newWallet3, target,
					wallets);
		}
	}
}
