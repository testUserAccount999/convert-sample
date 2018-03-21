//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2018.03.21 時間 01:39:11 PM JST 
//


package org.sample.struts.validation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element ref="{}global" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}formset" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "global",
    "formset"
})
@XmlRootElement(name = "form-validation")
public class FormValidation {

    protected List<Global> global;
    @XmlElement(required = true)
    protected List<Formset> formset;

    /**
     * Gets the value of the global property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the global property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGlobal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Global }
     * 
     * 
     */
    public List<Global> getGlobal() {
        if (global == null) {
            global = new ArrayList<Global>();
        }
        return this.global;
    }

    /**
     * Gets the value of the formset property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the formset property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFormset().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Formset }
     * 
     * 
     */
    public List<Formset> getFormset() {
        if (formset == null) {
            formset = new ArrayList<Formset>();
        }
        return this.formset;
    }

}
