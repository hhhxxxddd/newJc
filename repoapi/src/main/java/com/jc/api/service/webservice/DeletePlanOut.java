
package com.jc.api.service.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type的 Java 类。
 *
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="JsonPara" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "jsonPara"
})
@XmlRootElement(name = "DeletePlanOut")
public class DeletePlanOut {

    @XmlElementRef(name = "JsonPara", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jsonPara;

    /**
     * 获取jsonPara属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getJsonPara() {
        return jsonPara;
    }

    /**
     * 设置jsonPara属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setJsonPara(JAXBElement<String> value) {
        this.jsonPara = value;
    }

}
