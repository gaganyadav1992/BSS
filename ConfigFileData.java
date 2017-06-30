package com.bss.util;





public class ConfigFileData {


	
	private String prepaidPlanPrefix;
	private String postpaidPlanPrefix;
	private String hybridPlanPrefix;
	private String dataPlanPrefix;
	private String deviceInstPercent;
	private String customerIdUploadPath;
	private String bulkUploadArticlePath;
	private String commandNeedToSendOnOC;
	private String oCS;
	private String ocsIp;
	private String ocsPort;
	private String ocsuser;
	private String ocsPassword;
	private String broadbandPlanPrefix;
	private String smppUrl;
	private String senderShortCode;
	
	public String getSenderShortCode() {
		return senderShortCode;
	}
	public void setSenderShortCode(String senderShortCode) {
		this.senderShortCode = senderShortCode;
	}
	public String getBroadbandPlanPrefix() {
		return broadbandPlanPrefix;
	}
	public void setBroadbandPlanPrefix(String broadbandPlanPrefix) {
		this.broadbandPlanPrefix = broadbandPlanPrefix;
	}
	public String getOcsIp() {
		return ocsIp;
	}
	public String getOcsPort() {
		return ocsPort;
	}
	public void setOcsIp(String ocsIp) {
		this.ocsIp = ocsIp;
	}
	public void setOcsPort(String ocsPort) {
		this.ocsPort = ocsPort;
	}
	public String getCommandNeedToSendOnOC() {
		return commandNeedToSendOnOC;
	}
	public String getoCS() {
		return oCS;
	}
	public void setCommandNeedToSendOnOC(String commandNeedToSendOnOC) {
		this.commandNeedToSendOnOC = commandNeedToSendOnOC;
	}
	public void setoCS(String oCS) {
		this.oCS = oCS;
	}
	public String getPrepaidPlanPrefix() {
		return prepaidPlanPrefix;
	}
	public void setPrepaidPlanPrefix(String prepaidPlanPrefix) {
		this.prepaidPlanPrefix = prepaidPlanPrefix;
	}
	public String getPostpaidPlanPrefix() {
		return postpaidPlanPrefix;
	}
	public void setPostpaidPlanPrefix(String postpaidPlanPrefix) {
		this.postpaidPlanPrefix = postpaidPlanPrefix;
	}
	public String getHybridPlanPrefix() {
		return hybridPlanPrefix;
	}
	public void setHybridPlanPrefix(String hybridPlanPrefix) {
		this.hybridPlanPrefix = hybridPlanPrefix;
	}
	public String getDataPlanPrefix() {
		return dataPlanPrefix;
	}
	public void setDataPlanPrefix(String dataPlanPrefix) {
		this.dataPlanPrefix = dataPlanPrefix;
	}
	public String getDeviceInstPercent() {
		return deviceInstPercent;
	}
	public void setDeviceInstPercent(String deviceInstPercent) {
		this.deviceInstPercent = deviceInstPercent;
	}
	public String getCustomerIdUploadPath() {
		return customerIdUploadPath;
	}
	public void setCustomerIdUploadPath(String customerIdUploadPath) {
		this.customerIdUploadPath = customerIdUploadPath;
	}
	public String getBulkUploadArticlePath() {
		return bulkUploadArticlePath;
	}
	public void setBulkUploadArticlePath(String bulkUploadArticlePath) {
		this.bulkUploadArticlePath = bulkUploadArticlePath;
	}
	public ConfigFileData(String prepaidPlanPrefix, String postpaidPlanPrefix,
			String hybridPlanPrefix, String dataPlanPrefix,
			String deviceInstPercent, String customerIdUploadPath,
			String bulkUploadArticlePath) {
		super();
		this.prepaidPlanPrefix = prepaidPlanPrefix;
		this.postpaidPlanPrefix = postpaidPlanPrefix;
		this.hybridPlanPrefix = hybridPlanPrefix;
		this.dataPlanPrefix = dataPlanPrefix;
		this.deviceInstPercent = deviceInstPercent;
		this.customerIdUploadPath = customerIdUploadPath;
		this.bulkUploadArticlePath = bulkUploadArticlePath;
	}
	public ConfigFileData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getOcsuser() {
		return ocsuser;
	}
	public String getOcsPassword() {
		return ocsPassword;
	}
	public void setOcsuser(String ocsuser) {
		this.ocsuser = ocsuser;
	}
	public void setOcsPassword(String ocsPassword) {
		this.ocsPassword = ocsPassword;
	}
	public String getSmppUrl() {
		return smppUrl;
	}
	public void setSmppUrl(String smppUrl) {
		this.smppUrl = smppUrl;
	}
	
	
	
	
	
/*	@Bean
	public String getPrepaidPlanPrefix() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	    try {
	        ctx.register(ConfigFileData.class);
	        ctx.refresh();
	        Environment env = ctx.getEnvironment();
	        return  env.getProperty("prefix.PrePaid");
	       // System.out.println("Topic: >>>>>>>>>>>>>>>>>>>>>>...." + prepaidPlanPrefix);
	    } finally {
	        ctx.close();
	    }
		//return prepaidPlanPrefix;
	}*/
	
	
	
	
}
