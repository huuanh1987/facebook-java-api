//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.3-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.06.20 at 06:10:07 PM HST 
//


package com.facebook.api.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for group complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="group">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gid" type="{http://api.facebook.com/1.0/}gid"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nid" type="{http://api.facebook.com/1.0/}nid"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="group_type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="group_subtype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recent_news" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pic_big" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pic_small" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creator" type="{http://api.facebook.com/1.0/}uid"/>
 *         &lt;element name="update_time" type="{http://api.facebook.com/1.0/}time"/>
 *         &lt;element name="office" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="website" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="venue" type="{http://api.facebook.com/1.0/}location"/>
 *         &lt;element name="pic_square" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "group", propOrder = {
    "gid",
    "name",
    "nid",
    "description",
    "groupType",
    "groupSubtype",
    "recentNews",
    "pic",
    "picBig",
    "picSmall",
    "creator",
    "updateTime",
    "office",
    "website",
    "venue",
    "picSquare"
})
public class Group {

    protected long gid;
    @XmlElement(required = true)
    protected String name;
    protected long nid;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(name = "group_type", required = true)
    protected String groupType;
    @XmlElement(name = "group_subtype", required = true)
    protected String groupSubtype;
    @XmlElement(name = "recent_news", required = true)
    protected String recentNews;
    @XmlElement(required = true)
    protected String pic;
    @XmlElement(name = "pic_big", required = true)
    protected String picBig;
    @XmlElement(name = "pic_small", required = true)
    protected String picSmall;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long creator;
    @XmlElement(name = "update_time")
    protected int updateTime;
    @XmlElement(required = true)
    protected String office;
    @XmlElement(required = true)
    protected String website;
    @XmlElement(required = true)
    protected Location venue;
    @XmlElement(name = "pic_square", required = true)
    protected String picSquare;

    /**
     * Gets the value of the gid property.
     * 
     */
    public synchronized long getGid() {
        return gid;
    }

    /**
     * Sets the value of the gid property.
     * 
     */
    public synchronized void setGid(long value) {
        this.gid = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public synchronized String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public synchronized void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the nid property.
     * 
     */
    public synchronized long getNid() {
        return nid;
    }

    /**
     * Sets the value of the nid property.
     * 
     */
    public synchronized void setNid(long value) {
        this.nid = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public synchronized String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public synchronized void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the groupType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public synchronized String getGroupType() {
        return groupType;
    }

    /**
     * Sets the value of the groupType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public synchronized void setGroupType(String value) {
        this.groupType = value;
    }

    /**
     * Gets the value of the groupSubtype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public synchronized String getGroupSubtype() {
        return groupSubtype;
    }

    /**
     * Sets the value of the groupSubtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public synchronized void setGroupSubtype(String value) {
        this.groupSubtype = value;
    }

    /**
     * Gets the value of the recentNews property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public synchronized String getRecentNews() {
        return recentNews;
    }

    /**
     * Sets the value of the recentNews property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public synchronized void setRecentNews(String value) {
        this.recentNews = value;
    }

    /**
     * Gets the value of the pic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public synchronized String getPic() {
        return pic;
    }

    /**
     * Sets the value of the pic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public synchronized void setPic(String value) {
        this.pic = value;
    }

    /**
     * Gets the value of the picBig property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public synchronized String getPicBig() {
        return picBig;
    }

    /**
     * Sets the value of the picBig property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public synchronized void setPicBig(String value) {
        this.picBig = value;
    }

    /**
     * Gets the value of the picSmall property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public synchronized String getPicSmall() {
        return picSmall;
    }

    /**
     * Sets the value of the picSmall property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public synchronized void setPicSmall(String value) {
        this.picSmall = value;
    }

    /**
     * Gets the value of the creator property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public synchronized Long getCreator() {
        return creator;
    }

    /**
     * Sets the value of the creator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public synchronized void setCreator(Long value) {
        this.creator = value;
    }

    /**
     * Gets the value of the updateTime property.
     * 
     */
    public synchronized int getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets the value of the updateTime property.
     * 
     */
    public synchronized void setUpdateTime(int value) {
        this.updateTime = value;
    }

    /**
     * Gets the value of the office property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public synchronized String getOffice() {
        return office;
    }

    /**
     * Sets the value of the office property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public synchronized void setOffice(String value) {
        this.office = value;
    }

    /**
     * Gets the value of the website property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public synchronized String getWebsite() {
        return website;
    }

    /**
     * Sets the value of the website property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public synchronized void setWebsite(String value) {
        this.website = value;
    }

    /**
     * Gets the value of the venue property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public synchronized Location getVenue() {
        return venue;
    }

    /**
     * Sets the value of the venue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public synchronized void setVenue(Location value) {
        this.venue = value;
    }

    /**
     * Gets the value of the picSquare property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public synchronized String getPicSquare() {
        return picSquare;
    }

    /**
     * Sets the value of the picSquare property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public synchronized void setPicSquare(String value) {
        this.picSquare = value;
    }

}