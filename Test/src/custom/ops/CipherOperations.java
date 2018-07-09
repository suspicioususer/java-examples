package custom.ops;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class CipherOperations {

	private final String UTF8 = "UTF-8";
	private final String ALGORITHM = "DESede";
	private final String encryptionKey = "Sample_3DES_Encryption_Key";
	private KeySpec keySpec;
	private SecretKeyFactory secretKeyFactory;
	private Cipher cipher;
	private byte[] keyAsBytes;
	private SecretKey secretKey;

	public String encryptorShiftCipher(String text, int shiftCount) {
		char plainTextArray[] = text.toLowerCase().toCharArray();
		for (int i = 0; i < plainTextArray.length; i++) {
			char c = plainTextArray[i];
			c = (char) (c + shiftCount);
			plainTextArray[i] = c;
		}
		return new String(plainTextArray);
	}

	public String decryptorShiftCipher(String text, int shiftCount) {
		char encryptedArray[] = text.toLowerCase().toCharArray();
		for (int i = 0; i < encryptedArray.length; i++) {
			char c = encryptedArray[i];
			c = (char) (c - shiftCount);
			encryptedArray[i] = c;
		}
		return new String(encryptedArray);
	}

	public String encryptor3DES(String text) {
		String encryptedString = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] plainText = text.getBytes(UTF8);
			byte[] encryptedText = cipher.doFinal(plainText);
			encryptedString = Base64.getEncoder().encodeToString(encryptedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}

	public String decryptor3DES(String text) {
		String decryptedText = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] encryptedText = Base64.getDecoder().decode(text);
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText = bytesToString(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedText;
	}

	public void initialize3DES() throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException {
		keyAsBytes = encryptionKey.getBytes(UTF8);
		keySpec = new DESedeKeySpec(keyAsBytes);
		secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		cipher = Cipher.getInstance(ALGORITHM);
		secretKey = secretKeyFactory.generateSecret(keySpec);
	}

	public String bytesToString(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			stringBuffer.append((char) bytes[i]);
		}
		return stringBuffer.toString();
	}

}
