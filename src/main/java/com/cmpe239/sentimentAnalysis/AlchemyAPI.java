package com.cmpe239.sentimentAnalysis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cmpe239.sentimentAnalysis.Utility.ApplicationConstants;

public class AlchemyAPI {

    private String _apiKey;
    private String _requestUri = "http://access.alchemyapi.com/calls/";

    private AlchemyAPI() {
    }


    static public AlchemyAPI GetInstanceFromFile(String keyFilename)
        throws FileNotFoundException, IOException
    {
        AlchemyAPI api = new AlchemyAPI();
        api.LoadAPIKey(keyFilename);

        return api;
    }

    static public AlchemyAPI GetInstanceFromString(String apiKey)
    {
        AlchemyAPI api = new AlchemyAPI();
        api.SetAPIKey(apiKey);

        return api;
    }

    public void LoadAPIKey(String filename) throws IOException, FileNotFoundException
    {
        if (null == filename || 0 == filename.length())
            throw new IllegalArgumentException("Empty API key file specified.");

       /* File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);

        BufferedReader breader = new BufferedReader(new InputStreamReader(fis));*/

        _apiKey = ApplicationConstants.ALCHEMY_API_KEY.replaceAll("\\n", "").replaceAll("\\r", "");

/*        fis.close();
        breader.close();*/

        if (null == _apiKey || _apiKey.length() < 5)
            throw new IllegalArgumentException("Too short API key.");
    }

    public void SetAPIKey(String apiKey) {
        _apiKey = apiKey;

        if (null == _apiKey || _apiKey.length() < 5)
            throw new IllegalArgumentException("Too short API key.");
    }

    public void SetAPIHost(String apiHost) {
        if (null == apiHost || apiHost.length() < 2)
            throw new IllegalArgumentException("Too short API host.");

        _requestUri = "http://" + apiHost + ".alchemyapi.com/calls/";
    }

    public Document URLGetAuthor(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetAuthor(url, new AlchemyAPI_Params());
    }

    public Document URLGetAuthor(String url, AlchemyAPI_Params params)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        CheckURL(url);

        params.setUrl(url);

        return GET("URLGetAuthor", "url", params);
    }

    public Document HTMLGetAuthor(String html, String url)
        throws IOException, SAXException, ParserConfigurationException,
               XPathExpressionException
    {
        return HTMLGetAuthor(html, url, new AlchemyAPI_Params());
    }

    public Document HTMLGetAuthor(String html, String url,
			          AlchemyAPI_Params params)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        CheckHTML(html, url);

        params.setHtml(html);
        params.setUrl(url);

        return POST("HTMLGetAuthor", "html", params);
    }

    public Document URLGetRawText(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetRawText(url, new AlchemyAPI_Params());
    }
    
    public Document URLGetRawText(String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetRawText", "url", params);
	}

    public Document HTMLGetRawText(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetRawText(html, url, new AlchemyAPI_Params());
    }
    
    public Document HTMLGetRawText(String html, String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);

	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetRawText", "html", params);
	}

    public Document URLGetTitle(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetTitle(url, new AlchemyAPI_Params());
    }
    
    public Document URLGetTitle(String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);
	    
	    params.setUrl(url);
	
	    return GET("URLGetTitle", "url", params);
	}

    public Document HTMLGetTitle(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetTitle(html, url, new AlchemyAPI_Params());
    }
    
    public Document HTMLGetTitle(String html, String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetTitle", "html", params);
	}

    public Document URLGetFeedLinks(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetFeedLinks(url, new AlchemyAPI_Params());
    }
    
    public Document URLGetFeedLinks(String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);
	    
	    params.setUrl(url);
	
	    return GET("URLGetFeedLinks", "url", params);
	}

    public Document HTMLGetFeedLinks(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetFeedLinks(html, url, new AlchemyAPI_Params());
    }
    
    public Document HTMLGetFeedLinks(String html, String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetFeedLinks", "html", params);
	}

    public Document URLGetMicroformats(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetMicroformats(url, new AlchemyAPI_Params());
    }
    
    public Document URLGetMicroformats(String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckURL(url);

	    params.setUrl(url);
	
	    return GET("URLGetMicroformatData", "url", params);
	}

    public Document HTMLGetMicroformats(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetMicroformats(html, url, new AlchemyAPI_Params());
    }
    
    public Document HTMLGetMicroformats(String html, String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    CheckHTML(html, url);
	    
	    params.setUrl(url);
	    params.setHtml(html);
	
	    return POST("HTMLGetMicroformatData", "html", params);
	}

	public Document URLGetTextSentiment(String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return URLGetTextSentiment(url, new AlchemyAPI_Params());
    }
    
    public Document URLGetTextSentiment(String url, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
    {
    	CheckURL(url);
    
    	params.setUrl(url);

    	return GET("URLGetTextSentiment", "url", params);
    }    
    

    public Document HTMLGetTextSentiment(String html, String url)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return HTMLGetTextSentiment(html, url, new AlchemyAPI_Params());
    }
    
    public Document HTMLGetTextSentiment(String html, String url, AlchemyAPI_Params params)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        CheckHTML(html, url);

        params.setUrl(url);
        params.setHtml(html);
	
        return POST("HTMLGetTextSentiment", "html", params);
    }

    public Document TextGetTextSentiment(String text)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        return TextGetTextSentiment(text, new AlchemyAPI_Params());
    }
    
    public Document TextGetTextSentiment(String text, AlchemyAPI_Params params)
        throws IOException, SAXException,
        ParserConfigurationException, XPathExpressionException 
    {
        CheckText(text);
		
        params.setText(text);
		
        return POST("TextGetTextSentiment", "text", params);
    }
	
	//------------------


    private void CheckHTML(String html, String url) {
        if (null == html || html.length() < 5)
            throw new IllegalArgumentException("Enter a HTML document to analyze.");

        if (null == url || url.length() < 10)
            throw new IllegalArgumentException("Enter an URL to analyze.");
    }

    private void CheckText(String text) {
        if (null == text )
            throw new IllegalArgumentException("Enter some text to analyze.");
    }

    private void CheckURL(String url) {
        if (null == url || url.length() < 10)
            throw new IllegalArgumentException("Enter an URL to analyze.");
    }
    
    private Document GET(String callName, String callPrefix, AlchemyAPI_Params params)
    throws IOException, SAXException,
           ParserConfigurationException, XPathExpressionException
	{
	    StringBuilder uri = new StringBuilder();
	    uri.append(_requestUri).append(callPrefix).append('/').append(callName)
	       .append('?').append("apikey=").append(this._apiKey);
	    uri.append(params.getParameterString());
	
	    URL url = new URL(uri.toString());
	    HttpURLConnection handle = (HttpURLConnection) url.openConnection();
	    handle.setDoOutput(true);
	
	    return doRequest(handle, params.getOutputMode());
	}

    private Document POST(String callName, String callPrefix, AlchemyAPI_Params params)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        URL url = new URL(_requestUri + callPrefix + "/" + callName);

        HttpURLConnection handle = (HttpURLConnection) url.openConnection();
        handle.setDoOutput(true);

        StringBuilder data = new StringBuilder();

        data.append("apikey=").append(this._apiKey);
        data.append(params.getParameterString());

        handle.addRequestProperty("Content-Length", Integer.toString(data.length()));

        DataOutputStream ostream = new DataOutputStream(handle.getOutputStream());
        ostream.write(data.toString().getBytes());
        ostream.close();

        return doRequest(handle, params.getOutputMode());
    }

    private Document doRequest(HttpURLConnection handle, String outputMode)
        throws IOException, SAXException,
               ParserConfigurationException, XPathExpressionException
    {
        DataInputStream istream = new DataInputStream(handle.getInputStream());
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(istream);

        istream.close();
        handle.disconnect();

        XPathFactory factory = XPathFactory.newInstance();

        if(AlchemyAPI_Params.OUTPUT_XML.equals(outputMode)) {
        	String statusStr = getNodeValue(factory, doc, "/results/status/text()");
        	if (null == statusStr || !statusStr.equals("OK")) {
        		String statusInfoStr = getNodeValue(factory, doc, "/results/statusInfo/text()");
        		if (null != statusInfoStr && statusInfoStr.length() > 0)
        			throw new IOException("Error making API call: " + statusInfoStr + '.');

        		throw new IOException("Error making API call: " + statusStr + '.');
        	}
        }
        else if(AlchemyAPI_Params.OUTPUT_RDF.equals(outputMode)) {
        	String statusStr = getNodeValue(factory, doc, "//RDF/Description/ResultStatus/text()");
        	if (null == statusStr || !statusStr.equals("OK")) {
        		String statusInfoStr = getNodeValue(factory, doc, "//RDF/Description/ResultStatus/text()");
        		if (null != statusInfoStr && statusInfoStr.length() > 0)
        			throw new IOException("Error making API call: " + statusInfoStr + '.');

        		throw new IOException("Error making API call: " + statusStr + '.');
        	}
        }

        return doc;
    }

    private String getNodeValue(XPathFactory factory, Document doc, String xpathStr)
        throws XPathExpressionException
    {
        XPath xpath = factory.newXPath();
        XPathExpression expr = xpath.compile(xpathStr);
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList results = (NodeList) result;

        if (results.getLength() > 0 && null != results.item(0))
            return results.item(0).getNodeValue();

        return null;
    }
}
