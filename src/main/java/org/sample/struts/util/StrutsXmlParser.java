package org.sample.struts.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import org.sample.struts.config.StrutsConfig;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class StrutsXmlParser {
    private static final Set<String> IGNORE_PUBLIC_IDS = new HashSet<String>() {
		private static final long serialVersionUID = 1L;

		{
            add("-//Apache Software Foundation//DTD Commons Validator Rules Configuration 1.0//EN");
            add("-//Apache Software Foundation//DTD Struts Configuration 1.0//EN");
            add("-//Apache Software Foundation//DTD Struts Configuration 1.1//EN");
            add("-//Apache Software Foundation//DTD Tiles Configuration 1.1//EN");
        }
    };

    private StrutsXmlParser() {
    }

    public static org.sample.struts.rule.FormValidation parseValidationRule(InputStream inputStream)
            throws ParserConfigurationException, SAXException, JAXBException {
        return (org.sample.struts.rule.FormValidation) parseXml(inputStream,
                org.sample.struts.rule.FormValidation.class);
    }

    public static org.sample.struts.validation.FormValidation parseValidation(InputStream inputStream)
            throws ParserConfigurationException, SAXException, JAXBException {
        return (org.sample.struts.validation.FormValidation) parseXml(inputStream,
                org.sample.struts.validation.FormValidation.class);
    }

    public static StrutsConfig parseStrutsConfig(InputStream inputStream)
            throws ParserConfigurationException, SAXException, JAXBException {
        return (StrutsConfig) parseXml(inputStream, StrutsConfig.class);
    }

    private static Object parseXml(InputStream inputStream, Class<?> type)
            throws ParserConfigurationException, SAXException, JAXBException {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream is null.");
        }
        SAXParserFactory spf = SAXParserFactory.newInstance();
        EntityResolver entityResolver = new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                //To ignore DocType
                if (IGNORE_PUBLIC_IDS.contains(publicId)) {
                    return new InputSource(new StringReader(""));
                }

                return null;
            }
        };
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        xmlReader.setEntityResolver(entityResolver);
        SAXSource source = new SAXSource(xmlReader, new InputSource(inputStream));
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        Unmarshaller um = jaxbContext.createUnmarshaller();
        return um.unmarshal(source);
    }

}
