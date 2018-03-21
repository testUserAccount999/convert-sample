//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2018.03.21 時間 01:39:11 PM JST 
//


package org.sample.struts.validator;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;sequence>
 *         &lt;element ref="{}msg" minOccurs="0"/>
 *         &lt;element ref="{}arg0" minOccurs="0"/>
 *         &lt;element ref="{}arg1" minOccurs="0"/>
 *         &lt;element ref="{}arg2" minOccurs="0"/>
 *         &lt;element ref="{}arg3" minOccurs="0"/>
 *         &lt;element ref="{}var" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
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
    "msg",
    "arg0",
    "arg1",
    "arg2",
    "arg3",
    "var"
})
@XmlRootElement(name = "field")
public class Field {

    protected Msg msg;
    protected Arg0 arg0;
    protected Arg1 arg1;
    protected Arg2 arg2;
    protected Arg3 arg3;
    protected List<Var> var;
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
     * msgプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link Msg }
     *     
     */
    public Msg getMsg() {
        return msg;
    }

    /**
     * msgプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link Msg }
     *     
     */
    public void setMsg(Msg value) {
        this.msg = value;
    }

    /**
     * arg0プロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link Arg0 }
     *     
     */
    public Arg0 getArg0() {
        return arg0;
    }

    /**
     * arg0プロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link Arg0 }
     *     
     */
    public void setArg0(Arg0 value) {
        this.arg0 = value;
    }

    /**
     * arg1プロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link Arg1 }
     *     
     */
    public Arg1 getArg1() {
        return arg1;
    }

    /**
     * arg1プロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link Arg1 }
     *     
     */
    public void setArg1(Arg1 value) {
        this.arg1 = value;
    }

    /**
     * arg2プロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link Arg2 }
     *     
     */
    public Arg2 getArg2() {
        return arg2;
    }

    /**
     * arg2プロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link Arg2 }
     *     
     */
    public void setArg2(Arg2 value) {
        this.arg2 = value;
    }

    /**
     * arg3プロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link Arg3 }
     *     
     */
    public Arg3 getArg3() {
        return arg3;
    }

    /**
     * arg3プロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link Arg3 }
     *     
     */
    public void setArg3(Arg3 value) {
        this.arg3 = value;
    }

    /**
     * Gets the value of the var property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the var property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVar().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Var }
     * 
     * 
     */
    public List<Var> getVar() {
        if (var == null) {
            var = new ArrayList<Var>();
        }
        return this.var;
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
