//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2018.03.21 時間 01:39:10 PM JST 
//


package org.sample.struts.rule;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.sample.struts.rule package. 
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

    private final static QName _VarValue_QNAME = new QName("", "var-value");
    private final static QName _ConstantName_QNAME = new QName("", "constant-name");
    private final static QName _VarName_QNAME = new QName("", "var-name");
    private final static QName _ConstantValue_QNAME = new QName("", "constant-value");
    private final static QName _Javascript_QNAME = new QName("", "javascript");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.sample.struts.rule
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Msg }
     * 
     */
    public Msg createMsg() {
        return new Msg();
    }

    /**
     * Create an instance of {@link Formset }
     * 
     */
    public Formset createFormset() {
        return new Formset();
    }

    /**
     * Create an instance of {@link Constant }
     * 
     */
    public Constant createConstant() {
        return new Constant();
    }

    /**
     * Create an instance of {@link Form }
     * 
     */
    public Form createForm() {
        return new Form();
    }

    /**
     * Create an instance of {@link Field }
     * 
     */
    public Field createField() {
        return new Field();
    }

    /**
     * Create an instance of {@link Arg0 }
     * 
     */
    public Arg0 createArg0() {
        return new Arg0();
    }

    /**
     * Create an instance of {@link Arg1 }
     * 
     */
    public Arg1 createArg1() {
        return new Arg1();
    }

    /**
     * Create an instance of {@link Arg2 }
     * 
     */
    public Arg2 createArg2() {
        return new Arg2();
    }

    /**
     * Create an instance of {@link Arg3 }
     * 
     */
    public Arg3 createArg3() {
        return new Arg3();
    }

    /**
     * Create an instance of {@link Var }
     * 
     */
    public Var createVar() {
        return new Var();
    }

    /**
     * Create an instance of {@link Validator }
     * 
     */
    public Validator createValidator() {
        return new Validator();
    }

    /**
     * Create an instance of {@link Global }
     * 
     */
    public Global createGlobal() {
        return new Global();
    }

    /**
     * Create an instance of {@link FormValidation }
     * 
     */
    public FormValidation createFormValidation() {
        return new FormValidation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "var-value")
    public JAXBElement<String> createVarValue(String value) {
        return new JAXBElement<String>(_VarValue_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "constant-name")
    public JAXBElement<String> createConstantName(String value) {
        return new JAXBElement<String>(_ConstantName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "var-name")
    public JAXBElement<String> createVarName(String value) {
        return new JAXBElement<String>(_VarName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "constant-value")
    public JAXBElement<String> createConstantValue(String value) {
        return new JAXBElement<String>(_ConstantValue_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "javascript")
    public JAXBElement<String> createJavascript(String value) {
        return new JAXBElement<String>(_Javascript_QNAME, String.class, null, value);
    }

}
