
package com.help.server.common;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * 登录加密工具类
 */
public class RsaUtils {
	private static final Provider DEFAULTPROVIDER = new BouncyCastleProvider();
	private static final String DEFAULT_MODULUS = "108515725448342660217771118363923730296424098423847034969078109022938901475697118831097989759318452732393052579929705744189600493630065211152847021878710513721576086203801222625127634699732009258161718577442033681170556414504415796120818621764323256045115056302406986490370882680859535840925699683478370999877";
	private static final String DEFAULT_PUBLIC_EXPONENT = "65537";
	private static final String DEFAULT_PRIVATE_EXPONENT = "85293224427346798192438253950630013504270841784170902930057090681441455861820192947915218097899082001785722942724552577852124763536199691119603518485987074405511624271890927772587211731181688077279565695132700474576821789695077043650741408500093205755122466045931537364441758993731368248512249955139821788833";
	private static KeyPair defaultKeyPair = null;
	private static KeyFactory keyFactory = null;

	public static KeyPair getDefaultKeyPair() {
		return defaultKeyPair;
	}

	public static RSAPublicKey getDefaultPublicKey() {
		return getRSAPublidKey(DEFAULT_MODULUS, DEFAULT_PUBLIC_EXPONENT);
	}

	public static RSAPublicKey getRSAPublidKey(String modulus, String exponent) {
		BigInteger b1 = new BigInteger(modulus);
		BigInteger b2 = new BigInteger(exponent);
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
		try {
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static RSAPrivateKey getDefaultPrivateKey() {
		return getRSAPrivateKey(DEFAULT_MODULUS, DEFAULT_PRIVATE_EXPONENT);
	}

	public static RSAPrivateKey getRSAPrivateKey(String modulus, String exponent) {
		BigInteger b1 = new BigInteger(modulus);
		BigInteger b2 = new BigInteger(exponent);
		if ((modulus != null) && (exponent != null)) {
			RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(b1, b2);
			try {
				return (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String encrypt(PublicKey publicKey, String str) throws Exception {
		Cipher ci = Cipher.getInstance("RSA", DEFAULTPROVIDER);
		ci.init(1, publicKey);
		byte[] enData = ci.doFinal(str.getBytes());
		return new String(Hex.encodeHex(enData));
	}

	public static String decrypt(PrivateKey privateKey, String str) throws Exception {
		byte[] data = Hex.decodeHex(str.toCharArray());
		Cipher ci = Cipher.getInstance("RSA", DEFAULTPROVIDER);
		ci.init(2, privateKey);
		return new String(ci.doFinal(data));
	}

	public static void main(String[] args) throws Exception {
		RSAPublicKey publicKey = getDefaultPublicKey();
		RSAPrivateKey privateKey = getDefaultPrivateKey();
		String aaa = "123456";
		String bbb = encrypt(publicKey, aaa);
		System.out.println(bbb);
		String ccc = decrypt(privateKey, bbb);
		System.out.println(new String(ccc));
	}

	static {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024);
			defaultKeyPair = keyPairGen.generateKeyPair();
			keyFactory = KeyFactory.getInstance("RSA", DEFAULTPROVIDER);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}