package com.parvinder.blockchain.bigchain.cryptoprofile.model;

import java.io.Serializable;
import java.security.KeyPair;
import java.util.Map;
import java.util.TreeMap;

import com.bigchaindb.model.MetaData;
import com.parvinder.blockchain.bigchain.cryptoprofile.BigChainCryptoConnectionManager;
import com.parvinder.blockchain.bigchain.cryptoprofile.util.CryptoUtil;

public class BigChainCryptoUserProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1592321459779690628L;
	
	private String uniqueId;
	private String cryptoAssetId;
	private KeyPair keys;
	private String encodedKeyPair;
	
	/**
	 * 
	 */
	public BigChainCryptoUserProfile (String uniqueId) throws Exception {
		this.uniqueId = uniqueId;
		this.keys = CryptoUtil.createNewCryptoKeys();
		this.cryptoAssetId = this.createCryptoAsset(this.uniqueId, this.keys);
		this.encodedKeyPair = CryptoUtil.enCodeKeyPair(this.keys);
	}
	
	/**
	 * 
	 */
	public BigChainCryptoUserProfile (String uniqueId, String encodedKeyPair, String assetId) throws Exception {
		this.uniqueId = uniqueId;
		this.encodedKeyPair = encodedKeyPair;
		this.cryptoAssetId = assetId;
		this.keys = CryptoUtil.resurrectKeyPairFromString(encodedKeyPair);
	}
	
	/**
	 * 
	 */
	public BigChainCryptoUserProfile (String uniqueId, String encodedKeyPair, String assetId, KeyPair keys) throws Exception {
		this.uniqueId = uniqueId;
		this.encodedKeyPair = encodedKeyPair;
		this.cryptoAssetId = assetId;
		this.keys = keys;
	}
	
	
	/**
	 * 
	 */
	public String addNewBlock (Map <String, String> blockData) throws Exception{
		// 1. Peform any auditing work
		// 2. Commit to Blockchain
    	return this.appendCryptoProfile(blockData);
    } 
	
	
	/**
	 * 
	 */
	public String addPersonalInfo (Map <String, String> personalInfo) throws Exception{
		// 1. Peform any auditing work
		// 2. Commit to Blockchain
    	return this.appendCryptoProfile(personalInfo);
    } 
    
	/**
	 * 
	 */
	public String addEducation (Map <String, String> education) throws Exception{
		// 1. Peform any auditing work
		// 2. Commit to Blockchain
		return this.appendCryptoProfile(education);
    }
    
	/**
	 * 
	 */
	public String addJob (Map <String, String> job) throws Exception{
		// 1. Peform any auditing work
		// 2. Commit to Blockchain
    	return this.appendCryptoProfile(job);
    } 
	
	/**
	 * 
	 */
	protected String createCryptoAsset(String uniqueId, KeyPair keys) throws Exception{
		BigChainCryptoConnectionManager cryptoConnManager = new BigChainCryptoConnectionManager();
		
    	@SuppressWarnings("serial")
		Map<String, String> assetData = new TreeMap<String, String>() {{
            put("UniqueId", uniqueId);
        }};
        
        return cryptoConnManager.doCreate(assetData, keys);
	}
	
	/**
	 * 
	 */
	protected String appendCryptoProfile(Map <String, String> userData) throws Exception{
		BigChainCryptoConnectionManager cryptoConnManager = new BigChainCryptoConnectionManager();
		
		MetaData metadata = new MetaData();
		//metadata.setMetaData(userData);
		if (userData != null) {
    		for (Map.Entry<String,String> entry : userData.entrySet()) {  
    			metadata.setMetaData(entry.getKey(), entry.getValue());
    		} 
		}		
		
		return cryptoConnManager.doAppend(this.cryptoAssetId, metadata, this.keys);
	}
	
	/**
	 * 
	 */
	public String getCryptoAssetId() {
		return cryptoAssetId;
	}

	/**
	 * 
	 */
	public String getUniqueId() {
		return uniqueId;
	}
	
	/**
	 * 
	 */
	public KeyPair getKeys() {
		return keys;
	}

	/**
	 * 
	 */
	public void setKeys(KeyPair keys) {
		this.keys = keys;
	}

	/**
	 * 
	 */
	public String getEncodedKeyPair() {
		return encodedKeyPair;
	}

	/**
	 * 
	 */
	public void setEncodedKeyPair(String encodedKeyPair) {
		this.encodedKeyPair = encodedKeyPair;
	}

	@Override
	public String toString() {
		return "CryptoUserProfile [UniqueId=" + uniqueId 
									+ ", EncodedKeyPair=" + this.encodedKeyPair 
									+ ", CryptoAssetId=" + cryptoAssetId 
									+ "]";
	}
	
	
	
}
