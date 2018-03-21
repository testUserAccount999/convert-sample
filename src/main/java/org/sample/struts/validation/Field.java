//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2018.03.21 時間 08:28:06 PM JST 
//


package org.sample.struts.validation;

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
 *         &lt;element ref="{}arg0" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}arg1" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}arg2" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}arg3" maxOccurs="unbounded" minOccurs="0"/>
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
    protected List<Arg0> arg0;
    protected List<Arg1> arg1;
    protected List<Arg2> arg2;
    protected List<Arg3> arg3;
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
     * Gets the value of the arg0 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arg0 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArg0().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Arg0 }
     * 
     * 
     */
    public List<Arg0> getArg0() {
        if (arg0 == null) {
            arg0 = new ArrayList<Arg0>();
        }
        return this.arg0;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arg1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArg1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Arg1 }
     * 
     * 
     */
    public List<Arg1> getArg1() {
        if (arg1 == null) {
            arg1 = new ArrayList<Arg1>();
        }
        return this.arg1;
    }

    /**
     * Gets the value of the arg2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arg2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArg2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Arg2 }
     * 
     * 
     */
    public List<Arg2> getArg2() {
        if (arg2 == null) {
            arg2 = new ArrayList<Arg2>();
        }
        return this.arg2;
    }

    /**
     * Gets the value of the arg3 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arg3 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArg3().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Arg3 }
     * 
     * 
     */
    public List<Arg3> getArg3() {
        if (arg3 == null) {
            arg3 = new ArrayList<Arg3>();
        }
        return this.arg3;
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
