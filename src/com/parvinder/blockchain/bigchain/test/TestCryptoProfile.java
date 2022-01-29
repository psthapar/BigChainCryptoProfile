package com.parvinder.blockchain.bigchain.test;

import java.io.IOException;
import java.security.KeyPair;
import java.util.Map;
import java.util.TreeMap;

import com.bigchaindb.util.Base58;
import com.parvinder.blockchain.bigchain.cryptoprofile.BigChainCryptoProfileManager;
import com.parvinder.blockchain.bigchain.cryptoprofile.model.BigChainCryptoUserProfile;
import com.parvinder.blockchain.bigchain.cryptoprofile.util.CryptoUtil;

public class TestCryptoProfile {
	
	private static final String NEW_PROFILE_ID = "reallyUnique@userId.com-5afterLongTime";
	private static final String NEW_PROFILE_ID_ENCODED_PAIR = "MC4CAQAwBQYDK2VwBCIEINkukvGY7RhSu/kRhL8brwL95h1zCIlTbJgtOnOx+SKP";
	private static final String NEW_PROFILE_ID_GENESIS_BLOCK_HASH = "08fcb9b96cc82945b00b0eaa570c3aedad333938e9cceb226332afd9a21adc7f";

    /**
     * main method 
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String args[]) throws Exception {
    	BigChainCryptoUserProfile cryptoUser;
    	
    	cryptoUser = testNewCreatedCryptoProfile(NEW_PROFILE_ID);
    	cryptoUser = testReadAppendCryptoProfile(NEW_PROFILE_ID);
    	    	
    	displayAllCryptoProfiles();
    	
    	System.out.println("\n\n====> Crypto User at the end of all Transfers: " + cryptoUser);

    }
    
	private static BigChainCryptoUserProfile testNewCreatedCryptoProfile(String newProfileId) throws Exception{
		BigChainCryptoProfileManager cryptoProfileManager = BigChainCryptoProfileManager.getInstance();
		BigChainCryptoUserProfile cryptoUser = null;
    	try {
    		cryptoUser = cryptoProfileManager.createCryptoUserProfile(newProfileId);
    		System.out.println("\n\n====> Crypto User Created Successfully: " + cryptoUser);
    	}catch (Exception ex) {
    		cryptoUser = cryptoProfileManager.getCryptoUserProfile(newProfileId);
    	}
    	return cryptoUser;
	}
    
    
	private static BigChainCryptoUserProfile testCreateAppendCryptoProfile(String newProfileId) throws Exception{
		BigChainCryptoProfileManager cryptoProfileManager = BigChainCryptoProfileManager.getInstance();
		BigChainCryptoUserProfile cryptoUser = null;
    	try {
    		cryptoUser = cryptoProfileManager.createCryptoUserProfile(newProfileId);
    		System.out.println("\n\n====> Crypto User Created Successfully: " + cryptoUser);
    	}catch (Exception ex) {
    		cryptoUser = cryptoProfileManager.getCryptoUserProfile(newProfileId);
    	}
    	
    	cryptoUser.addPersonalInfo(getPersonalInfo());
    	cryptoUser.addEducation(getEducationInfo());
    	cryptoUser.addJob(getJobInfo());
    	
    	return cryptoUser;
	}
	
	@SuppressWarnings("unused")
	private static BigChainCryptoUserProfile testReadAppendCryptoProfile(String profileId) throws Exception {
		BigChainCryptoProfileManager cryptoProfileManager = BigChainCryptoProfileManager.getInstance();
		
		BigChainCryptoUserProfile cryptoUser = cryptoProfileManager.getCryptoUserProfile(profileId);
		if (null == cryptoUser) {
			cryptoUser = new BigChainCryptoUserProfile (NEW_PROFILE_ID, NEW_PROFILE_ID_ENCODED_PAIR, NEW_PROFILE_ID_GENESIS_BLOCK_HASH);
		}
		
		System.out.println("\n\n====> RESURRECTED Crypto User: " + cryptoUser);
		
		cryptoProfileManager.addCryptoUserProfile(cryptoUser);
		cryptoUser.addEducation(getAdditionalEducationInfo());
		cryptoUser.addJob(getAdditionalJobInfo());
		
		return cryptoUser;
	}
	
	@SuppressWarnings("unused")
	private static BigChainCryptoUserProfile testReadCryptoProfile(BigChainCryptoProfileManager cryptoProfileManager) throws Exception {
		BigChainCryptoUserProfile cryptoUser = new BigChainCryptoUserProfile (	"reallyUnique@userId.com-1", 
																"MC4CAQAwBQYDK2VwBCIEIDJ1WMeDKScrmRiPUpyqYNnbULyI2hMVUD7uDkKvyYWS", // Encoded KeyPair
																"7ae44da02b4606da91310895a1d124952866c47d20daa2f03f6d9f0e6fe95fdb"); // Create Transaction Id
		
		cryptoProfileManager.addCryptoUserProfile(cryptoUser);
		
		cryptoUser = new BigChainCryptoUserProfile (	"reallyUnique@userId.com-2", 
																"MC4CAQAwBQYDK2VwBCIEILjFtf25asqklazkRoMyhi8PZ1EjpJvsrycFTaL71JiP", // Encoded KeyPair
																"b9dd46b617d5d62caaa2550b8478a5a255b9a61570c1f21a29ca26f218618130"); // Create Transaction Id

		
		
		System.out.println("\n\n====> RESURRECTED Crypto User: " + cryptoUser);
		cryptoProfileManager.addCryptoUserProfile(cryptoUser);
		return cryptoUser;
	}	
	
	private static void displayAllCryptoProfiles() throws Exception {
		BigChainCryptoProfileManager cryptoProfileManager = BigChainCryptoProfileManager.getInstance();
		cryptoProfileManager.displayAllCryptoProfiles();
	}
	
	
    private static Map<String, String> getPersonalInfo(){
        // create New asset
        @SuppressWarnings("serial")
		Map<String, String> personalInfo = new TreeMap<String, String>() {{
            put("Name", "Test User");
            put("Phone", "800-222-1111");
        }};
        
        return personalInfo;
    }
    
	private static Map<String, String> getEducationInfo(){
        // create New asset
        @SuppressWarnings("serial")
		Map<String, String> education = new TreeMap<String, String>() {{
            put("Institution", "Cal Poly SLO");
            put("Diploma", "Undergrad");
            put("Majors", "Computer Science");
            put("Year Started", "1998");
            put("Year Completed", "2002");

        }};
        
        return education;
    }
    
	private static Map<String, String> getJobInfo(){
        // create New asset
        @SuppressWarnings("serial")
		Map<String, String> job = new TreeMap<String, String>() {{
            put("Hiring Company", "Bank of America");
            put("Title", "Financial Analyst");
            put("Hiring Manager", "Joe Shmoe");
            put("Salary", "$80,000");
        }};
        
        return job;
    }
	
	private static Map<String, String> getAdditionalEducationInfo(){
        // create New asset
        @SuppressWarnings("serial")
		Map<String, String> education = new TreeMap<String, String>() {{
            put("Institution", "University of Southern California");
            put("Diploma", "Grad");
            put("Majors", "Business Administration");
            put("Year Started", "2011");
            put("Year Completed", "2013");

        }};
        
        return education;
    }
    
	private static Map<String, String> getAdditionalJobInfo(){
        // create New asset
        @SuppressWarnings("serial")
		Map<String, String> job = new TreeMap<String, String>() {{
            put("Hiring Company", "WellsFargo Bank");
            put("Title", "Financial Risk Assessment Manager");
            put("Hiring Manager", "Jane Doe");
            put("Salary", "$125,000");
        }};
        
        return job;
    }
	
 
    @SuppressWarnings("unused")
	private static void testPrintEncodedKeyPair(BigChainCryptoUserProfile cryptoUser) {
    	KeyPair resurrectedKeyPair = CryptoUtil.resurrectKeyPairFromString(cryptoUser.getEncodedKeyPair());
    	
		System.out.println("TETSTED KeyPair: Public Key ===>" + Base58.encode(resurrectedKeyPair.getPublic().getEncoded()));
		System.out.println("TETSTED KeyPair: Private Key ===>" + Base58.encode(resurrectedKeyPair.getPrivate().getEncoded()));	    	
    	
    }
    
}
