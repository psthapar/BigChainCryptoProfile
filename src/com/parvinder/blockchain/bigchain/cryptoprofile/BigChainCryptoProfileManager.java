package com.parvinder.blockchain.bigchain.cryptoprofile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.bigchaindb.api.AssetsApi;
import com.bigchaindb.api.BlocksApi;
import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.Assets;
import com.bigchaindb.model.Transaction;
import com.bigchaindb.model.Transactions;
import com.parvinder.blockchain.bigchain.cryptoprofile.model.BigChainCryptoUserProfile;
import com.parvinder.blockchain.bigchain.cryptoprofile.util.CryptoUtil;

/**
 *
 *
 */
public class BigChainCryptoProfileManager {
	
	private static BigChainCryptoProfileManager single_instance = null;
	private static Map<String, BigChainCryptoUserProfile> cryptoUserProfiles = new TreeMap<String, BigChainCryptoUserProfile> ();
	
	
	public static BigChainCryptoProfileManager getInstance() {
		if (null == single_instance) {
			single_instance = new BigChainCryptoProfileManager();
		}
		return single_instance;
	}
	
	public BigChainCryptoProfileManager() {
    	CryptoUtil.setConfig();
	}
	
	/**
	 * 
	 */
	public BigChainCryptoUserProfile createCryptoUserProfile (String uniqueId) throws Exception{
		BigChainCryptoUserProfile cryptoUserProfile = null;
		cryptoUserProfile = cryptoUserProfiles.get(uniqueId);
		if (null != cryptoUserProfile) {
			throw new Exception ("(!) User already exists!");
		}
		
		cryptoUserProfile = new BigChainCryptoUserProfile (uniqueId);
		cryptoUserProfiles.put(uniqueId, cryptoUserProfile);
		return cryptoUserProfile;
    }
	
	/**
	 * 
	 */
	public BigChainCryptoUserProfile getCryptoUserProfile (String uniqueId) throws Exception{
		BigChainCryptoUserProfile cryptoUserProfile = cryptoUserProfiles.get(uniqueId);
//		if (null == cryptoUserProfile) {
//			throw new Exception ("(!) User does NOT exist!");
//		}
		return cryptoUserProfile;
    }
	
	/**
	 * 
	 */
	public void addCryptoUserProfile (BigChainCryptoUserProfile cryptoUser) {
		cryptoUserProfiles.put(cryptoUser.getUniqueId(), cryptoUser);
	}
	
	/**
	 * 
	 */
	public void displayAllCryptoProfiles() throws Exception{
        System.out.println("\n(*) Number of Assets Found " + cryptoUserProfiles.size());// + " for User ID: " + profileId);
        
		for (Map.Entry<String,BigChainCryptoUserProfile> entry : cryptoUserProfiles.entrySet()) {
			this.displayCryptoProfile(((BigChainCryptoUserProfile)entry.getValue()).getCryptoAssetId());
		}
		
	}
	
	/**
	 * 
	 */
	public void addBlockToAsset(String profileId, Map<String, String> blockData) throws Exception {
		BigChainCryptoUserProfile cryptoUser = this.getCryptoUserProfile(profileId);
		if (null == cryptoUser) {
			throw new Exception ("Profile " + profileId + " does not exist!");
		}
		cryptoUser.addNewBlock(blockData);
	}
	
	/**
	 * 
	 */
	public BigChainCryptoUserProfile retrieveCryptoUserProfile (String uniqueId, String encodedKey, String assetProfileId) throws Exception{
		BigChainCryptoUserProfile cryptoUserProfile = new BigChainCryptoUserProfile (uniqueId, encodedKey, assetProfileId);
		return cryptoUserProfile;
    }

	
	/**
	 * 

	public SimpleCryptoProfile retrieveCryptoProfile (String profileId) throws Exception {
		SimpleCryptoProfile cryptoProfile = new SimpleCryptoProfile (profileId);
		
		Assets assets = this.searchAssets(profileId); // should return only 1
		for (int assetId=0; assetId<assets.size(); assetId++) {
			System.out.println("\n ===> Asset ID " + assets.getAssets().get(assetId).getId());

			Transactions createTransactions = TransactionsApi.getTransactionsByAssetId(assets.getAssets().get(assetId).getId(), Operations.CREATE);
			for (int createTransId=0; createTransId<createTransactions.getTransactions().size(); createTransId++) {
				Transaction currTransaction = createTransactions.getTransactions().get(createTransId);
				@SuppressWarnings("unchecked")
				Map<String,String> metadataMap = (Map<String,String>)currTransaction.getMetaData();
				if (null != metadataMap) {
					cryptoProfile.addCryptoBlock(metadataMap);	
				}
				
			} // End of for createTransId

			Transactions transferTransactions = TransactionsApi.getTransactionsByAssetId(assets.getAssets().get(assetId).getId(), Operations.TRANSFER);
			for (int transferTransId=0; transferTransId<transferTransactions.getTransactions().size(); transferTransId++) {
				Transaction currTransaction = transferTransactions.getTransactions().get(transferTransId);
				@SuppressWarnings("unchecked")
				Map<String,String> metadataMap = (Map<String,String>)currTransaction.getMetaData();
				if (null != metadataMap) {
					cryptoProfile.addCryptoBlock(metadataMap);	
				}
			} // End of for transferTransId
			
		}// End of for Assets
		
		return cryptoProfile;
	}
*/
	
	/**
	 * 
	 */
	public List<Map<String, String>> retrieveCryptoBlocksForProfile (String profileId) throws Exception {
		List<Map<String, String>> crytoBlocks = new ArrayList<Map<String, String>>();
		
		Assets assets = this.searchAssets(profileId); // should return only 1
		for (int assetId=0; assetId<assets.size(); assetId++) {
			System.out.println("\n ===> Asset ID " + assets.getAssets().get(assetId).getId());

			Transactions createTransactions = TransactionsApi.getTransactionsByAssetId(assets.getAssets().get(assetId).getId(), Operations.CREATE);
			for (int createTransId=0; createTransId<createTransactions.getTransactions().size(); createTransId++) {
				Transaction currTransaction = createTransactions.getTransactions().get(createTransId);
				@SuppressWarnings("unchecked")
				Map<String,String> metadataMap = (Map<String,String>)currTransaction.getMetaData();
				if (null != metadataMap) {
					crytoBlocks.add(metadataMap);	
				}
				
			} // End of for createTransId

			Transactions transferTransactions = TransactionsApi.getTransactionsByAssetId(assets.getAssets().get(assetId).getId(), Operations.TRANSFER);
			for (int transferTransId=0; transferTransId<transferTransactions.getTransactions().size(); transferTransId++) {
				Transaction currTransaction = transferTransactions.getTransactions().get(transferTransId);
				@SuppressWarnings("unchecked")
				Map<String,String> metadataMap = (Map<String,String>)currTransaction.getMetaData();
				if (null != metadataMap) {
					crytoBlocks.add(metadataMap);	
				}
			} // End of for transferTransId
			
		}// End of for Assets
		
		return crytoBlocks;
	}
	
	
	/**
	 * 
	 */
	public void displayCryptoProfile(String profileId) throws Exception{
        Assets assets = this.searchAssets(profileId);
        
        for (int i=0; i<assets.size(); i++) {
        	System.out.println("\n ===> Asset ID " + assets.getAssets().get(i).getId());
        	@SuppressWarnings("unchecked")
			Map<String, String> currAssetData = (Map<String, String>)assets.getAssets().get(i).getData();
        	System.out.println("---------------------------------------------------------------------");
        	System.out.println(" ===> Unique Profile: " + currAssetData.get("UniqueId"));
        	
        	
        	Transactions createTransactions = TransactionsApi.getTransactionsByAssetId(assets.getAssets().get(i).getId(), Operations.CREATE);
        	Transactions transferTransactions = TransactionsApi.getTransactionsByAssetId(assets.getAssets().get(i).getId(), Operations.TRANSFER);
        	//System.out.println(" ========> Number of Transactions for this asset " + (createTransactions.getTransactions().size() + transferTransactions.getTransactions().size()));

        	
        	// Printing Transactions
        	// Create Transactions
        	for (int j=0; j<createTransactions.getTransactions().size(); j++) {
        		Transaction currTransaction = createTransactions.getTransactions().get(j);
        		
        		List<String> blocks = BlocksApi.getBlocksByTransactionId(currTransaction.getId());
        		Iterator<String> blocksIterator = blocks.iterator();

        		while (blocksIterator.hasNext()) {
        			System.out.println(" =============> Current Block" + blocksIterator.next());
        		}
        		
        		@SuppressWarnings("unchecked")
				Map<String,String> metadataMap = (Map<String,String>)currTransaction.getMetaData();
        		
        		if (metadataMap != null) {
            		for (Map.Entry<String,String> entry : metadataMap.entrySet()) {  
                        //System.out.println(" =============> Key = " + entry.getKey() +  ", Value = " + entry.getValue());
            			System.out.println(" =============> " + entry.getKey() + ": " + entry.getValue());
            		} 
            		System.out.println("---------------------------------------------------------------------\n");
        		}
        		
        	}        	
	
        	
        	// Transfer Transactions
        	for (int j=0; j<transferTransactions.getTransactions().size(); j++) {
        		Transaction currTransaction = transferTransactions.getTransactions().get(j);
        		
        		List<String> blocks = BlocksApi.getBlocksByTransactionId(currTransaction.getId());
        		Iterator<String> blocksIterator = blocks.iterator();

        		while (blocksIterator.hasNext()) {
        			System.out.println(" =============> Current Block" + blocksIterator.next());
        		}
        		
        		@SuppressWarnings("unchecked")
				Map<String,String> metadataMap = (Map<String,String>)currTransaction.getMetaData();
        		
        		if (metadataMap != null) {
            		for (Map.Entry<String,String> entry : metadataMap.entrySet()) {  
                        //System.out.println(" =============> Key = " + entry.getKey() +  ", Value = " + entry.getValue());
            			System.out.println(" =============> " + entry.getKey() + ": " + entry.getValue());
            		} 
            		System.out.println("---------------------------------------------------------------------");
        		}
        	}        	
        	
        }

	}
	
    protected Assets searchAssets(String searchKey) {
    	
    	try {
    		Assets assetsFound =  AssetsApi.getAssets(searchKey);
    		//System.out.println("Number of assets found " + assetsFound.getAssets().size());
    		//System.out.println(assetsFound.getAssets().get(0).getId());
    		return assetsFound;
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    	
    } 
    
 
	

}