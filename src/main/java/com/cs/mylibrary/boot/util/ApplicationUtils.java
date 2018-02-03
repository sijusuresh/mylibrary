package com.cs.mylibrary.boot.util;

import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class ApplicationUtils {
	public static String getStringFromDocument(Document doc) {
	    try {
	      DOMSource domSource = new DOMSource(doc);
	      StringWriter writer = new StringWriter();
	      StreamResult result = new StreamResult(writer);
	      TransformerFactory tf = TransformerFactory.newInstance();
	      Transformer transformer = tf.newTransformer();
	      transformer.transform(domSource, result);
	      return writer.toString();
	    } catch (TransformerException e) {
	      System.out.println("Exception in XML Parsing");;
	    }
	    return "";
	  }
}
