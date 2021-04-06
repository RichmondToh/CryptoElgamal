import java.math.BigInteger;

public class CryptoElgamalBobSendsMessagetoAlice {

	public static void main(String[] args) {
		BigInteger Contains0 =  new BigInteger("0"); //This is to check if 0
		//Alice sends her parameter's, Bob encrypts and sends message to Alice, Alice decrypts Message
		//Alice parameters
		BigInteger AprimeNumber = new BigInteger("3677456903");   //Prime Number  79
		BigInteger g1 = new BigInteger("5");            //Generator Number 30
		BigInteger AprivateKey = new BigInteger("0");   // The Private Key 61 
		BigInteger ApublicKey = new BigInteger("2531790967");            //Public Key
		
		//Bob parameters
		BigInteger g2 = new BigInteger("3092551593"); //Random Number  4
		BigInteger BpublicKey = new BigInteger("0"); //The Public Key //Ephemeral Key
		BigInteger BprivateKey = new BigInteger ("0"); //
	
           
		BigInteger Message = new BigInteger("1000"); //44
		BigInteger InverseOfBprivateKey = new BigInteger("0");
		BigInteger CipherText = new BigInteger("0");
		if(ApublicKey.equals(Contains0)) {
			ApublicKey = CalculatePublicKey(g1,AprivateKey,AprimeNumber);
		}
		
		
		System.out.println("'"+ApublicKey+"' This is Alice's Public Key and is calclated by "+g1+"^"+AprivateKey+" Mod "+AprimeNumber);  // This generates the Public Key
		System.out.println("Alice makes Public her PrimeNumber '"+AprimeNumber+"' and the random number g '"+g1+"' and the Public Key '"+ApublicKey+"'");
		System.out.println();
		System.out.println();
		
		
		
		if(BpublicKey.equals(Contains0)){
			BpublicKey = CalculatePublicKey(g1,g2,AprimeNumber); // (Alice's Generator Number, Bob's Random Number, Alice's Prime Number)
		}
		System.out.println("'"+g2+"' Bob Selects a Random Number in the set between 2 and Alice's (PrimeNumber - 2)");
		System.out.println("'"+BpublicKey+"' This is Bob's Public Key/Ephemeral Key, this is Computed by Alice's Random Number g to the power of Bob's Random number, Mod Alice's PrimeNumber = "+g1+"^"+g2+" mod "+AprimeNumber);

		if(BprivateKey.equals(Contains0)) {
			BprivateKey = CalculatePrivateKey(ApublicKey,g2,AprimeNumber);
		}
		System.out.println("'"+BprivateKey+" Bob's Private Key/Shared Secret Key we can Compute it by using Alice's Public Key to the power of Bob's Random number, Mod Alice's PrimeNumber = "+ApublicKey+"^"+g2+" Mod "+AprimeNumber);
		
		
		
		
		
		System.out.println("'"+CreateEncryptedMessage(BprivateKey,Message,AprimeNumber)+"'is The CipherText since, Bob Encrypts the Plaintext message m = '"+Message+"' by multiply the plaintext 'M' by Bob's Private Key/Shared Secret Key, Mod Alice's PrimeNumber = "+Message+"*"+BprivateKey+" Mod "+AprimeNumber);
		CipherText = CreateEncryptedMessage(BprivateKey,Message,AprimeNumber);
		
		System.out.println();
		System.out.println();
		System.out.println("We send over Bob's Public key '"+BpublicKey+"' and we also send the ciphertext '"+CipherText+"'");
		System.out.println("'"+BpublicKey.modPow(AprivateKey, AprimeNumber)+"' We can compute Bob's Private Key by Computing Bob's Public key to the power of Alice's Private Key, Mod Alice's PrimeNumber = "+BpublicKey+"^"+AprivateKey+" Mod "+AprimeNumber);
		System.out.println("'"+BprivateKey.modInverse(AprimeNumber)+"' We Compute the Inverse of Bob's Private Key to undo decryption by Computing Bob's Private Key to the power of -1, Mod Alice's PrimeNumber = "+BpublicKey.modPow(AprivateKey, AprimeNumber)+"^-1 Mod "+AprimeNumber);
		InverseOfBprivateKey = BprivateKey.modInverse(AprimeNumber);
		
		System.out.println("'"+CipherText.multiply(InverseOfBprivateKey).mod(AprimeNumber)+"' The Decrypted message is Computed by the CipherText Multiply Inverse of Bob's Private Key, Mod Alice's Prime Number "+CipherText+"*"+BprivateKey.modInverse(AprimeNumber)+" Mod "+AprimeNumber);
		
		

	}
	public static BigInteger CalculatePublicKey(BigInteger GeneratorNumber,BigInteger PrivateKey, BigInteger PrimeNumber) {
		return GeneratorNumber.modPow(PrivateKey, PrimeNumber);    //   g.modPow(b,p)
	
	}
	public static BigInteger CalculatePrivateKey(BigInteger PublicKey,BigInteger RandomNumber, BigInteger ModP) {
		return PublicKey.modPow(RandomNumber, ModP);    //   otherPerson publickey, your random number, otherPerson ModP
	}
	public static BigInteger CreateEncryptedMessage(BigInteger PrivateKey, BigInteger Message, BigInteger PrimeNumber) {
		return PrivateKey.multiply(Message).mod(PrimeNumber); // Your PrivateKey, PlainTextMessage, Other Persons PrimeNumber
	}

}
 