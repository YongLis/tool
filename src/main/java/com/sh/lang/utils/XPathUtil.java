package com.sh.lang.utils;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XPathUtil {

    private static final Logger logger = LoggerFactory.getLogger(XPathUtil.class);

    public static String evalText(String xml, String xpathExpression) {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true); // never forget this!
            DocumentBuilder builder = domFactory.newDocumentBuilder();

            InputSource inputSource = new InputSource(new StringReader(xml));

            Document doc = builder.parse(inputSource);

            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = xpath.compile(xpathExpression);

            Object result = expr.evaluate(doc, XPathConstants.STRING);
            return (String) result;
        } catch (ParserConfigurationException e) {
            logger.info(e.getMessage(), e);
        } catch (SAXException e) {
            logger.info(e.getMessage(), e);
        } catch (IOException e) {
            logger.info(e.getMessage(), e);
        } catch (XPathExpressionException e) {
            logger.info(e.getMessage(), e);
        }

        return null;
    }
}
