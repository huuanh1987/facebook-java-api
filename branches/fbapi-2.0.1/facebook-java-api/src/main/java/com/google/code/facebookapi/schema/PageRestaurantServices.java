//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.0 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.02.10 at 10:42:34 PM PST 
//


package com.google.code.facebookapi.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for page_restaurant_services complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="page_restaurant_services">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reserve" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="walkins" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="groups" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="kids" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="takeout" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="delivery" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="catering" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="waiter" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="outdoor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "page_restaurant_services", propOrder = {
    "reserve",
    "walkins",
    "groups",
    "kids",
    "takeout",
    "delivery",
    "catering",
    "waiter",
    "outdoor"
})
public class PageRestaurantServices {

    protected boolean reserve;
    protected boolean walkins;
    protected boolean groups;
    protected boolean kids;
    protected boolean takeout;
    protected boolean delivery;
    protected boolean catering;
    protected boolean waiter;
    protected boolean outdoor;

    /**
     * Gets the value of the reserve property.
     * 
     */
    public boolean isReserve() {
        return reserve;
    }

    /**
     * Sets the value of the reserve property.
     * 
     */
    public void setReserve(boolean value) {
        this.reserve = value;
    }

    /**
     * Gets the value of the walkins property.
     * 
     */
    public boolean isWalkins() {
        return walkins;
    }

    /**
     * Sets the value of the walkins property.
     * 
     */
    public void setWalkins(boolean value) {
        this.walkins = value;
    }

    /**
     * Gets the value of the groups property.
     * 
     */
    public boolean isGroups() {
        return groups;
    }

    /**
     * Sets the value of the groups property.
     * 
     */
    public void setGroups(boolean value) {
        this.groups = value;
    }

    /**
     * Gets the value of the kids property.
     * 
     */
    public boolean isKids() {
        return kids;
    }

    /**
     * Sets the value of the kids property.
     * 
     */
    public void setKids(boolean value) {
        this.kids = value;
    }

    /**
     * Gets the value of the takeout property.
     * 
     */
    public boolean isTakeout() {
        return takeout;
    }

    /**
     * Sets the value of the takeout property.
     * 
     */
    public void setTakeout(boolean value) {
        this.takeout = value;
    }

    /**
     * Gets the value of the delivery property.
     * 
     */
    public boolean isDelivery() {
        return delivery;
    }

    /**
     * Sets the value of the delivery property.
     * 
     */
    public void setDelivery(boolean value) {
        this.delivery = value;
    }

    /**
     * Gets the value of the catering property.
     * 
     */
    public boolean isCatering() {
        return catering;
    }

    /**
     * Sets the value of the catering property.
     * 
     */
    public void setCatering(boolean value) {
        this.catering = value;
    }

    /**
     * Gets the value of the waiter property.
     * 
     */
    public boolean isWaiter() {
        return waiter;
    }

    /**
     * Sets the value of the waiter property.
     * 
     */
    public void setWaiter(boolean value) {
        this.waiter = value;
    }

    /**
     * Gets the value of the outdoor property.
     * 
     */
    public boolean isOutdoor() {
        return outdoor;
    }

    /**
     * Sets the value of the outdoor property.
     * 
     */
    public void setOutdoor(boolean value) {
        this.outdoor = value;
    }

}