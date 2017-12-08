import java.math.BigInteger;
public class Pair {
	BigInteger number;
	boolean prime;
	public Pair(BigInteger num, boolean p) {
		number = num;
		prime = p;
	}

	public String toString(){
		String isPrime = "";
		if(prime)
			isPrime += "PRIME:     ";
		else
			isPrime += "NOT PRIME: ";
		return isPrime + number.toString();
	}
}
