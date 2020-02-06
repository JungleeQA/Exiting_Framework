package com.Junglee.RestUtils;

public class URLs {

	String WebDeployment ="https://www.example.com/";
	String WebLive ="https://www.example.com/";
	String WebStaging ="https://www.example.com/";
	String WapDeployment ="https://www.example.com/";
	String WapLive ="https://www.example.com/";
	String WapStaging ="https://www.example.com/";
	String ApiDeployment ="https://www.example.com/";
	String ApiLive ="https://www.example.com/";

	public String Url(String type)
	{
		switch(type){

		case "WebDeployment":
			return WebDeployment;
		case "WebLive":
			return WebLive;
		case "WebStaging":
			return WebStaging;
		case "WapDeployment":
			return WapDeployment;
		case "WapLive":
			return WapLive;
		case "WapStaging":
			return WapStaging;
		case "ApiDeployment":
			return ApiDeployment;
		case "ApiLive":
			return ApiLive;
		default:
			return WebDeployment;
		}
	}
}
