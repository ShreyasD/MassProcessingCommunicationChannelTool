//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.05 at 09:55:36 PM IST 
//


package com.husky.mock.pojo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.husky.mock.pojo1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ChannelState_QNAME = new QName("", "ChannelState");
    private final static QName _Service_QNAME = new QName("", "Service");
    private final static QName _ShortLog_QNAME = new QName("", "ShortLog");
    private final static QName _ChannelID_QNAME = new QName("", "ChannelID");
    private final static QName _Party_QNAME = new QName("", "Party");
    private final static QName _ChannelName_QNAME = new QName("", "ChannelName");
    private final static QName _ActivationState_QNAME = new QName("", "ActivationState");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.husky.mock.pojo1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Channels }
     * 
     */
    public Channels createChannels() {
        return new Channels();
    }

    /**
     * Create an instance of {@link Channel }
     * 
     */
    public Channel createChannel() {
        return new Channel();
    }

    /**
     * Create an instance of {@link ChannelStatusResult }
     * 
     */
    public ChannelStatusResult createChannelStatusResult() {
        return new ChannelStatusResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ChannelState")
    public JAXBElement<String> createChannelState(String value) {
        return new JAXBElement<String>(_ChannelState_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Service")
    public JAXBElement<String> createService(String value) {
        return new JAXBElement<String>(_Service_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ShortLog")
    public JAXBElement<String> createShortLog(String value) {
        return new JAXBElement<String>(_ShortLog_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ChannelID")
    public JAXBElement<String> createChannelID(String value) {
        return new JAXBElement<String>(_ChannelID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Party")
    public JAXBElement<String> createParty(String value) {
        return new JAXBElement<String>(_Party_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ChannelName")
    public JAXBElement<String> createChannelName(String value) {
        return new JAXBElement<String>(_ChannelName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ActivationState")
    public JAXBElement<String> createActivationState(String value) {
        return new JAXBElement<String>(_ActivationState_QNAME, String.class, null, value);
    }

}