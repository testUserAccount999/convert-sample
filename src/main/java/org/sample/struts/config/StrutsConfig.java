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
 *         &lt;element ref="{}data-sources" minOccurs="0"/>
 *         &lt;element ref="{}form-beans" minOccurs="0"/>
 *         &lt;element ref="{}global-exceptions" minOccurs="0"/>
 *         &lt;element ref="{}global-forwards" minOccurs="0"/>
 *         &lt;element ref="{}action-mappings" minOccurs="0"/>
 *         &lt;element ref="{}controller" minOccurs="0"/>
 *         &lt;element ref="{}message-resources" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}plug-in" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dataSources",
    "formBeans",
    "globalExceptions",
    "globalForwards",
    "actionMappings",
    "controller",
    "messageResources",
    "plugIn"
})
@XmlRootElement(name = "struts-config")
public class StrutsConfig {

    @XmlElement(name = "data-sources")
    protected DataSources dataSources;
    @XmlElement(name = "form-beans")
    protected FormBeans formBeans;
    @XmlElement(name = "global-exceptions")
    protected GlobalExceptions globalExceptions;
    @XmlElement(name = "global-forwards")
    protected GlobalForwards globalForwards;
    @XmlElement(name = "action-mappings")
    protected ActionMappings actionMappings;
    protected Controller controller;
    @XmlElement(name = "message-resources")
    protected List<MessageResources> messageResources;
    @XmlElement(name = "plug-in")
    protected List<PlugIn> plugIn;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * dataSourcesプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link DataSources }
     *     
     */
    public DataSources getDataSources() {
        return dataSources;
    }

    /**
     * dataSourcesプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link DataSources }
     *     
     */
    public void setDataSources(DataSources value) {
        this.dataSources = value;
    }

    /**
     * formBeansプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link FormBeans }
     *     
     */
    public FormBeans getFormBeans() {
        return formBeans;
    }

    /**
     * formBeansプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link FormBeans }
     *     
     */
    public void setFormBeans(FormBeans value) {
        this.formBeans = value;
    }

    /**
     * globalExceptionsプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link GlobalExceptions }
     *     
     */
    public GlobalExceptions getGlobalExceptions() {
        return globalExceptions;
    }

    /**
     * globalExceptionsプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalExceptions }
     *     
     */
    public void setGlobalExceptions(GlobalExceptions value) {
        this.globalExceptions = value;
    }

    /**
     * globalForwardsプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link GlobalForwards }
     *     
     */
    public GlobalForwards getGlobalForwards() {
        return globalForwards;
    }

    /**
     * globalForwardsプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalForwards }
     *     
     */
    public void setGlobalForwards(GlobalForwards value) {
        this.globalForwards = value;
    }

    /**
     * actionMappingsプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionMappings }
     *     
     */
    public ActionMappings getActionMappings() {
        return actionMappings;
    }

    /**
     * actionMappingsプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionMappings }
     *     
     */
    public void setActionMappings(ActionMappings value) {
        this.actionMappings = value;
    }

    /**
     * controllerプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link Controller }
     *     
     */
    public Controller getController() {
        return controller;
    }

    /**
     * controllerプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link Controller }
     *     
     */
    public void setController(Controller value) {
        this.controller = value;
    }

    /**
     * Gets the value of the messageResources property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageResources property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageResources().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageResources }
     * 
     * 
     */
    public List<MessageResources> getMessageResources() {
        if (messageResources == null) {
            messageResources = new ArrayList<MessageResources>();
        }
        return this.messageResources;
    }

    /**
     * Gets the value of the plugIn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plugIn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlugIn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlugIn }
     * 
     * 
     */
    public List<PlugIn> getPlugIn() {
        if (plugIn == null) {
            plugIn = new ArrayList<PlugIn>();
        }
        return this.plugIn;
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

}
