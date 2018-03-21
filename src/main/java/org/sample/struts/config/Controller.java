//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2018.03.21 時間 01:50:51 PM JST 
//


package org.sample.struts.config;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{}set-property" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="bufferSize" type="{}Integer" />
 *       &lt;attribute name="className" type="{}ClassName" />
 *       &lt;attribute name="contentType" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="debug" type="{}Integer" />
 *       &lt;attribute name="forwardPattern" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="inputForward" type="{}Boolean" />
 *       &lt;attribute name="locale" type="{}Boolean" />
 *       &lt;attribute name="maxFileSize" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="memFileSize" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="multipartClass" type="{}ClassName" />
 *       &lt;attribute name="nocache" type="{}Boolean" />
 *       &lt;attribute name="pagePattern" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="processorClass" type="{}ClassName" />
 *       &lt;attribute name="tempDir" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "setProperty"
})
@XmlRootElement(name = "controller")
public class Controller {

    @XmlElement(name = "set-property")
    protected List<SetProperty> setProperty;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "bufferSize")
    protected String bufferSize;
    @XmlAttribute(name = "className")
    protected String className;
    @XmlAttribute(name = "contentType")
    @XmlSchemaType(name = "anySimpleType")
    protected String contentType;
    @XmlAttribute(name = "debug")
    protected String debug;
    @XmlAttribute(name = "forwardPattern")
    @XmlSchemaType(name = "anySimpleType")
    protected String forwardPattern;
    @XmlAttribute(name = "inputForward")
    protected Boolean inputForward;
    @XmlAttribute(name = "locale")
    protected Boolean locale;
    @XmlAttribute(name = "maxFileSize")
    @XmlSchemaType(name = "anySimpleType")
    protected String maxFileSize;
    @XmlAttribute(name = "memFileSize")
    @XmlSchemaType(name = "anySimpleType")
    protected String memFileSize;
    @XmlAttribute(name = "multipartClass")
    protected String multipartClass;
    @XmlAttribute(name = "nocache")
    protected Boolean nocache;
    @XmlAttribute(name = "pagePattern")
    @XmlSchemaType(name = "anySimpleType")
    protected String pagePattern;
    @XmlAttribute(name = "processorClass")
    protected String processorClass;
    @XmlAttribute(name = "tempDir")
    @XmlSchemaType(name = "anySimpleType")
    protected String tempDir;

    /**
     * Gets the value of the setProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the setProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSetProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SetProperty }
     * 
     * 
     */
    public List<SetProperty> getSetProperty() {
        if (setProperty == null) {
            setProperty = new ArrayList<SetProperty>();
        }
        return this.setProperty;
    }

    /**
     * idプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * idプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * bufferSizeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBufferSize() {
        return bufferSize;
    }

    /**
     * bufferSizeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBufferSize(String value) {
        this.bufferSize = value;
    }

    /**
     * classNameプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassName() {
        return className;
    }

    /**
     * classNameプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassName(String value) {
        this.className = value;
    }

    /**
     * contentTypeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * contentTypeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentType(String value) {
        this.contentType = value;
    }

    /**
     * debugプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebug() {
        return debug;
    }

    /**
     * debugプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebug(String value) {
        this.debug = value;
    }

    /**
     * forwardPatternプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForwardPattern() {
        return forwardPattern;
    }

    /**
     * forwardPatternプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForwardPattern(String value) {
        this.forwardPattern = value;
    }

    /**
     * inputForwardプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getInputForward() {
        return inputForward;
    }

    /**
     * inputForwardプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInputForward(Boolean value) {
        this.inputForward = value;
    }

    /**
     * localeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getLocale() {
        return locale;
    }

    /**
     * localeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLocale(Boolean value) {
        this.locale = value;
    }

    /**
     * maxFileSizeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxFileSize() {
        return maxFileSize;
    }

    /**
     * maxFileSizeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxFileSize(String value) {
        this.maxFileSize = value;
    }

    /**
     * memFileSizeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemFileSize() {
        return memFileSize;
    }

    /**
     * memFileSizeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemFileSize(String value) {
        this.memFileSize = value;
    }

    /**
     * multipartClassプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMultipartClass() {
        return multipartClass;
    }

    /**
     * multipartClassプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMultipartClass(String value) {
        this.multipartClass = value;
    }

    /**
     * nocacheプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getNocache() {
        return nocache;
    }

    /**
     * nocacheプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNocache(Boolean value) {
        this.nocache = value;
    }

    /**
     * pagePatternプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagePattern() {
        return pagePattern;
    }

    /**
     * pagePatternプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagePattern(String value) {
        this.pagePattern = value;
    }

    /**
     * processorClassプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessorClass() {
        return processorClass;
    }

    /**
     * processorClassプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessorClass(String value) {
        this.processorClass = value;
    }

    /**
     * tempDirプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTempDir() {
        return tempDir;
    }

    /**
     * tempDirプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTempDir(String value) {
        this.tempDir = value;
    }

}
