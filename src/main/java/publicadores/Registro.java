/**
 * Registro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class Registro  implements java.io.Serializable {
    private publicadores.Clase claseId;

    private java.util.Calendar fechaReg;

    private publicadores.Socio socioId;

    public Registro() {
    }

    public Registro(
           publicadores.Clase claseId,
           java.util.Calendar fechaReg,
           publicadores.Socio socioId) {
           this.claseId = claseId;
           this.fechaReg = fechaReg;
           this.socioId = socioId;
    }


    /**
     * Gets the claseId value for this Registro.
     * 
     * @return claseId
     */
    public publicadores.Clase getClaseId() {
        return claseId;
    }


    /**
     * Sets the claseId value for this Registro.
     * 
     * @param claseId
     */
    public void setClaseId(publicadores.Clase claseId) {
        this.claseId = claseId;
    }


    /**
     * Gets the fechaReg value for this Registro.
     * 
     * @return fechaReg
     */
    public java.util.Calendar getFechaReg() {
        return fechaReg;
    }


    /**
     * Sets the fechaReg value for this Registro.
     * 
     * @param fechaReg
     */
    public void setFechaReg(java.util.Calendar fechaReg) {
        this.fechaReg = fechaReg;
    }


    /**
     * Gets the socioId value for this Registro.
     * 
     * @return socioId
     */
    public publicadores.Socio getSocioId() {
        return socioId;
    }


    /**
     * Sets the socioId value for this Registro.
     * 
     * @param socioId
     */
    public void setSocioId(publicadores.Socio socioId) {
        this.socioId = socioId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Registro)) return false;
        Registro other = (Registro) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.claseId==null && other.getClaseId()==null) || 
             (this.claseId!=null &&
              this.claseId.equals(other.getClaseId()))) &&
            ((this.fechaReg==null && other.getFechaReg()==null) || 
             (this.fechaReg!=null &&
              this.fechaReg.equals(other.getFechaReg()))) &&
            ((this.socioId==null && other.getSocioId()==null) || 
             (this.socioId!=null &&
              this.socioId.equals(other.getSocioId())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getClaseId() != null) {
            _hashCode += getClaseId().hashCode();
        }
        if (getFechaReg() != null) {
            _hashCode += getFechaReg().hashCode();
        }
        if (getSocioId() != null) {
            _hashCode += getSocioId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Registro.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "registro"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claseId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "claseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "clase"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaReg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaReg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("socioId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "socioId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "socio"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
