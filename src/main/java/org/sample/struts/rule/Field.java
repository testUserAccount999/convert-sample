//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2018.03.21 時間 01:39:10 PM JST 
//


package org.sample.struts.rule;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{}msg"/>
 *         &lt;element ref="{}arg0"/>
 *         &lt;element ref="{}arg1"/>
 *         &lt;element ref="{}arg2"/>
 *         &lt;element ref="{}arg3"/>
 *         &lt;element ref="{}var"/>
 *       &lt;/choice>
 *       &lt;attribute name="property" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="depends" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="page" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="indexedListProperty" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "msgOrArg0OrArg1"
})
@XmlRootElement(name = "field")
public class Field {

    @XmlElements({
        @XmlElement(name = "msg", type = Msg.class),
        @XmlElement(name = "arg0", type = Arg0 .class),
        @XmlElement(name = "arg1", type = Arg1 .class),
        @XmlElement(name = "arg2", type = Arg2 .class),
        @XmlElement(name = "arg3", type = Arg3 .class),
        @XmlElement(name = "var", type = Var.class)
    })
    protected List<Object> msgOrArg0OrArg1;
    @XmlAttribute(name = "property", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String property;
    @XmlAttribute(name = "depends")
    @XmlSchemaType(name = "anySimpleType")
    protected String depends;
    @XmlAttribute(name = "page")
    @XmlSchemaType(name = "anySimpleType")
    protected String page;
    @XmlAttribute(name = "indexedListProperty")
    @XmlSchemaType(name = "anySimpleType")
    protected String indexedListProperty;

    /**
     * Gets the value of the msgOrArg0OrArg1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the msgOrArg0OrArg1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMsgOrArg0OrArg1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Msg }
     * {@link Arg0 }
     * {@link Arg1 }
     * {@link Arg2 }
     * {@link Arg3 }
     * {@link Var }
     * 
     * 
     */
    public List<Object> getMsgOrArg0OrArg1() {
        if (msgOrArg0OrArg1 == null) {
            msgOrArg0OrArg1 = new ArrayList<Object>();
        }
        return this.msgOrArg0OrArg1;
    }

    /**
     * propertyプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProperty() {
        return property;
    }

    /**
     * propertyプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProperty(String value) {
        this.property = value;
    }

    /**
     * dependsプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepends() {
        return depends;
    }

    /**
     * dependsプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepends(String value) {
        this.depends = value;
    }

    /**
     * pageプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPage() {
        return page;
    }

    /**
     * pageプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPage(String value) {
        this.page = value;
    }

    /**
     * indexedListPropertyプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndexedListProperty() {
        return indexedListProperty;
    }

    /**
     * indexedListPropertyプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndexedListProperty(String value) {
        this.indexedListProperty = value;
    }

}
