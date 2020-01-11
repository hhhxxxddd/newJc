
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
 *         &lt;element name="Send_PlanOutResultResult" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="sResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sendPlanOutResultResult",
    "sResult"
})
@XmlRootElement(name = "Send_PlanOutResultResponse")
public class SendPlanOutResultResponse {

    @XmlElement(name = "Send_PlanOutResultResult")
    protected Boolean sendPlanOutResultResult;
    @XmlElementRef(name = "sResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sResult;

    /**
     * 获取sendPlanOutResultResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSendPlanOutResultResult() {
        return sendPlanOutResultResult;
    }

    /**
     * 设置sendPlanOutResultResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSendPlanOutResultResult(Boolean value) {
        this.sendPlanOutResultResult = value;
    }

    /**
     * 获取sResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSResult() {
        return sResult;
    }

    /**
     * 设置sResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSResult(JAXBElement<String> value) {
        this.sResult = value;
    }

}
