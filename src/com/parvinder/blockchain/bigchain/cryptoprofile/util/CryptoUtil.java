package com.parvinder.blockchain.bigchain.cryptoprofile.util;

import java.security.KeyPair;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.bigchaindb.util.Base58;
import com.bigchaindb.util.KeyPairUtils;

public class CryptoUtil implements CryptoConstants{
	/**
	 * configures connection url and credentials
	 */
	public static void setConfig() {
		System.out.println("\n\n===> CryptoUtil: setconfig BASE_URL: " + BASE_URL + "\n\n");
		BigchainDbConfigBuilder
		//.baseUrl("http://localhost:9984/") //or use http://testnet.bigchaindb.com
		//.baseUrl("https://testnet.bigchaindb.com")
		//.baseUrl("https://test.bigchaindb.com/")
		.baseUrl(BASE_URL)
		.addToken("app_id", "")
		.addToken("app_key", "").setup();

	}

	/**
	 * generates EdDSA keypair to sign and verify transactions
	 * @return KeyPair
	 */
	public static KeyPair createNewCryptoKeys() {
		//  prepare your keys
		net.i2p.crypto.eddsa.KeyPairGenerator edDsaKpg = new net.i2p.crypto.eddsa.KeyPairGenerator();
		KeyPair keyPair = edDsaKpg.generateKeyPair();
		System.out.println("(*) Keys Generated..");
		System.out.println("NEW KeyPair: Public Key ===>" + Base58.encode(keyPair.getPublic().getEncoded()));
		System.out.println("NEW KeyPair: Private Key ===>" + Base58.encode(keyPair.getPrivate().getEncoded()));
		return keyPair;

	}

	/**
	 * Retrieves Public Key as a String from the given keypair 
	 * @return String
	 */
	public static String getPublicKeyAsString(KeyPair keys) {
		//String publicK = Base64.getEncoder().encodeToString((keys.getPublic().getEncoded()));
		String publicK = Base58.encode(keys.getPublic().getEncoded());
		return publicK;
	} 


	/**
	 * Retrieves Private Key as a String from the given keypair 
	 * @return String
	 */
	public static String getPrivateKeyAsString(KeyPair keys) {
		//String privateK = Base64.getEncoder().encodeToString((keys.getPrivate().getEncoded()));
		String privateK = Base58.encode(keys.getPrivate().getEncoded());
		return privateK;
	}
	
	/**
	 * Encode KeyPair 
	 * @return String
	 */
	public static String enCodeKeyPair(KeyPair keyPair) {
        return KeyPairUtils.encodePrivateKeyBase64(keyPair);
    }
	

	/**
	 * Resurrect KeyPair given a Public Key and a Private Key 
	 * @return String
	 */
	public static KeyPair resurrectKeyPairFromString(String encodedKeyPair) {
		System.out.println("\n====>RESURRECTED KeyPair: Encoded KeyPair ===>" + encodedKeyPair);
		
		KeyPair resurrectedKeyPair = KeyPairUtils.decodeKeyPair(encodedKeyPair);
		
		System.out.println("RESURRECTED KeyPair: Public Key ===>" + Base58.encode(resurrectedKeyPair.getPublic().getEncoded()));
		System.out.println("RESURRECTED KeyPair: Private Key ===>" + Base58.encode(resurrectedKeyPair.getPrivate().getEncoded()));	
		
		return resurrectedKeyPair;
	}

}
