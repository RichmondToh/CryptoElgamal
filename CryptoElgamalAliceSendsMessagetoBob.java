import java.math.BigInteger;

public class CryptoElgamalAliceSendsMessagetoBob {

	public static void main(String[] args) {
		BigInteger Contains0 =  new BigInteger("0"); //This is to check if 0
		//Alice sends her parameter's, Bob encrypts and sends message to Alice, Alice decrypts Message
		//Alice parameters
		BigInteger AprimeNumber = new BigInteger("3677456903");   //Prime Number  3677456903 //79
		BigInteger g1 = new BigInteger("5");            //Generator Number 5 //30
		BigInteger AprivateKey = new BigInteger("2994522459");   // The Private Key 2994522459 //61
		BigInteger ApublicKey = new BigInteger("0");            //Public Key
		
		//Bob parameters
		BigInteger g2 = new BigInteger("1570039966"); //Random Number  1570039966 //4
		BigInteger BpublicKey = new BigInteger("0"); //The Public Key //Ephemeral Key
		BigInteger BprivateKey = new BigInteger ("0"); //
	
           
		BigInteger Message = new BigInteger("987654321"); // 987654321 //44
		BigInteger InverseOfBprivateKey = new BigInteger("0");
		BigInteger CipherText = new BigInteger("0");
		if(ApublicKey.equals(Contains0)) {
			ApublicKey = CalculatePublicKey(g1,AprivateKey,AprimeNumber);
		}
		
		
		System.out.println(ApublicKey+" This is Alice's Public Key");  // This generates the Public Key
		System.out.println();
		System.out.println();
		
		
		System.out.println("We send over Alice's PrimeNumber '"+AprimeNumber+"' and the random number g '"+g1+"' and the Public Key '"+ApublicKey+"'");
		if(BpublicKey.equals(Contains0)){
			BpublicKey = CalculatePublicKey(g1,g2,AprimeNumber); // (Alice's Generator Number, Bob's Random Number, Alice's Prime Number)
		}
		System.out.println(BpublicKey+" This is Bob's Public Key");

		if(BprivateKey.equals(Contains0)) {
			BprivateKey = CalculatePrivateKey(ApublicKey,g2,AprimeNumber); // ApublicKey ^ g2 mod Alice's primeNumber 
		}
		System.out.println(BprivateKey+" Bob's Private Key");
		
		
		
		
		
		System.out.println(CreateEncryptedMessage(BprivateKey,Message,AprimeNumber)+" Bob Encrypts the message and this is the ciphertext");
		CipherText = CreateEncryptedMessage(BprivateKey,Message,AprimeNumber);
		
		System.out.println();
		System.out.println();
		System.out.println("We send over Bob's Public key '"+BpublicKey+"' and we also send the ciphertext '"+CipherText+"'");
		System.out.println(BpublicKey.modPow(AprivateKey, AprimeNumber)+" We can compute Bob's Private Key");
		System.out.println(BprivateKey.modInverse(AprimeNumber)+" The Inverse of Bob's Private Key to undo decryption");
		InverseOfBprivateKey = BprivateKey.modInverse(AprimeNumber);
		
		System.out.println(CipherText.multiply(InverseOfBprivateKey).mod(AprimeNumber)+" The Decrypted message");
		
		

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
 