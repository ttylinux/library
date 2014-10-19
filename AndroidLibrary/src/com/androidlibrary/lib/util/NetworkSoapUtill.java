/** 
 * @author LuShuWei E-mail:albertxiaoyu@163.com 
 * @version ����ʱ�䣺2014-10-19 ����1:19:30 
 * ��˵�� 
 */

package com.androidlibrary.lib.util;



import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;


public class NetworkSoapUtill {

	private String SERVICE_NAMESPACE;
	private String SERVICE_URL;
	private HttpTransportSE _httpTransportse;
	private Element _header;



	public NetworkSoapUtill(String nameSpace, String serviceUrl) {
		SERVICE_NAMESPACE = nameSpace;
		SERVICE_URL = serviceUrl;
        _httpTransportse = new HttpTransportSE(
    			SERVICE_URL);
	}

	/**
	 *Before you make a request from a url, you need to gain the request auth.
	 *
	 *@param headerName  the soap header
	 *@param authNameKey element name
	 *@param authName   element value
	 *@param authPasswordKey 
	 *@param authPassword
	 * 
	 */
	public void setAuthHeader(String headerName, String authNameKey,
			String authName, String authPasswordKey, String authPassword) {
		
		_header = new Element().createElement(SERVICE_NAMESPACE, headerName);
		
		Element userName = new Element().createElement(SERVICE_NAMESPACE, authNameKey);
		userName.addChild(Node.TEXT, authName);
		Element password = new Element().createElement(SERVICE_NAMESPACE, authPasswordKey);
		password.addChild(Node.TEXT, authPassword);
		
		_header.addChild(Node.ELEMENT, userName);
		_header.addChild(Node.ELEMENT, password);
		
	}
	
	/**
	 * demonstrate how to perform a request which use a soap protocol
	 */
	public String performLogin(String methodName,String resultKey, String userNameKey, String userName, String passwordKey, String password)
	{
		SoapSerializationEnvelope envelope = initalEnvelope();
		SoapObject requestSoapObject = new SoapObject(SERVICE_NAMESPACE,methodName);
		requestSoapObject.addProperty(userNameKey, userName);
		requestSoapObject.addProperty(passwordKey, password);
		envelope.bodyOut = requestSoapObject;
		
		try {
			_httpTransportse.call(SERVICE_NAMESPACE + methodName, envelope);
			
			if(envelope.getResponse() != null)
			{
				SoapObject resultObject = (SoapObject) envelope.bodyIn;
				return resultObject.getProperty(resultKey).toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	private SoapSerializationEnvelope initalEnvelope()
	{
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.headerOut = new Element[1];
		envelope.headerOut[0] = _header;
		return envelope;
	}

}
