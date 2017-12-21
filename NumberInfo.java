import java.math.BigInteger;
public class NumberInfo {
	BigInteger number;
	boolean prime;
	String factors;
	public NumberInfo(BigInteger num, boolean p, String s) {
		number = num;
		prime = p;
		factors = s;
	}

	public String toString(){
		String isPrime = "";
		if(prime)
			isPrime += "PRIME:     ";
		else
			isPrime += "NOT PRIME: ";
		return isPrime + number.toString() + " Prime Factors: " + factors;
	}
}
