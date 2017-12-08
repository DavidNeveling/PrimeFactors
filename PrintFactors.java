import static java.lang.System.*;
import java.util.*;
import java.math.BigInteger;
public class PrintFactors {
	public static void main(String[] args) {
		Scanner scan = new Scanner(in);
		BigInteger longMax = new BigInteger("" + Long.MAX_VALUE);
		boolean negative = false;
		String response = "";
		ArrayList<Pair> pairs = new ArrayList<>();
		do {
			negative = false;
			out.print("Enter a number: ");
			BigInteger input = scan.nextBigInteger();
			if(input.compareTo(BigInteger.ZERO) < 0){
				input = input.negate();
				negative = true;
			}
			if(input.compareTo(BigInteger.ONE) > 0) {
				if(input.compareTo(longMax) < 0) { // do long calculations
					long newInput = Long.parseLong(input.toString());
					ArrayList<Long> factors = getFactors(newInput);
					pairs.add(new Pair(input, (factors.size() == 1) && !negative));
					if(negative)
						newInput = -newInput;
					if(factors.size() == 1) {
						if(negative)
							out.println(newInput + " is not prime");
						else
							out.println(newInput + " is prime");
					}
					else {
						out.print("The factors of " + newInput + " are ");
						if(negative)
							out.print("-1 x ");
						for(int i = 0; i < factors.size() - 1; i++){
							out.print(factors.get(i) + " x ");
						}
						out.println(factors.get(factors.size() - 1));
					}
				}
				else { // do BigInteger calculations
					ArrayList<BigInteger> factors = getBigFactors(input);
					pairs.add(new Pair(input, (factors.size() == 1) && !negative));
					if(negative)
						input.negate();
						if(factors.size() == 1) {
							if(negative)
								out.println(input + " is not prime");
							else
								out.println(input + " is prime");
						}
					else {
						out.print("The factors of " + input + " are ");
						if(negative)
							out.print("-1 x ");
						for(int i = 0; i < factors.size() - 1; i++){
							out.print(factors.get(i).toString() + " x ");
						}
						out.println(factors.get(factors.size() - 1).toString());
					}
				}
			}
			else{
				pairs.add(new Pair(input, false));
				out.println("Invalid number, please try a number with absolute value greater than 1");
			}
			out.print("Would you like to enter another number? (y/n) ");
			response = scan.next();
		} while(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("yep") || response.equalsIgnoreCase("yeah") || response.equalsIgnoreCase("yeet"));
		out.println("Numbers checked:");
		for(int i = 0; i < pairs.size(); i++) {
			out.println(pairs.get(i));
		}
	}

	public static ArrayList<Long> getFactors(long num) {
		long i = 2;
		double root = Math.sqrt(num);
		ArrayList<Long> factors = new ArrayList<>();
		while(i <= num) {
			if(num % i == 0){
				factors.add(i);
				num /= i;
				root = Math.sqrt(num);
			}
			else{
				if(i >= root) {
					factors.add(num);
					return factors;
				}
				i++;
			}
		}
		return factors;
	}

	public static ArrayList<BigInteger> getBigFactors(BigInteger num) {
		BigInteger i = new BigInteger("2");
		BigInteger root = bigIntSqRootCeil(num);
		ArrayList<BigInteger> factors = new ArrayList<>();
		while(i.compareTo(num) <= 0) {
			if(num.mod(i).equals(BigInteger.ZERO)){
				factors.add(i);
				num = num.divide(i);
				root = bigIntSqRootCeil(num);
			}
			else{
				if(i.compareTo(root) >= 0) {
					factors.add(num);
					return factors;
				}
				i = i.add(BigInteger.ONE);
			}
		}
		return factors;
	}

	/* Code from https://stackoverflow.com/questions/4407839/how-can-i-find-the-square-root-of-a-java-biginteger.
	*	 In line 129 changed the two "==" to ".equals()"
	*/
	public static BigInteger bigIntSqRootCeil(BigInteger x)
        throws IllegalArgumentException {
    if (x.compareTo(BigInteger.ZERO) < 0) {
        throw new IllegalArgumentException("Negative argument.");
    }
    // square roots of 0 and 1 are trivial and
    // y == 0 will cause a divide-by-zero exception
    if (x.equals(BigInteger.ZERO) || x.equals(BigInteger.ONE)) {
        return x;
    } // end if
    BigInteger two = BigInteger.valueOf(2L);
    BigInteger y;
    // starting with y = x / 2 avoids magnitude issues with x squared
    for (y = x.divide(two);
            y.compareTo(x.divide(y)) > 0;
            y = ((x.divide(y)).add(y)).divide(two));
    if (x.compareTo(y.multiply(y)) == 0) {
        return y;
    } else {
        return y.add(BigInteger.ONE);
    }
	}
}
