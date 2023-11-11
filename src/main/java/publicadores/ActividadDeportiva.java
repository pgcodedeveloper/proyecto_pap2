/**
 * ActividadDeportiva.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class ActividadDeportiva  implements java.io.Serializable {
    private publicadores.Clase[] clases;

    private float costo;

    private java.lang.String descripcion;

    private java.util.Calendar fechaReg;

    private java.lang.String imagen;

    private publicadores.InstitucionDeportiva inst;

    private java.lang.String nombre;

    public ActividadDeportiva() {
    }

    public ActividadDeportiva(
           publicadores.Clase[] clases,
           float costo,
           java.lang.String descripcion,
           java.util.Calendar fechaReg,
           java.lang.String imagen,
           publicadores.InstitucionDeportiva inst,
           java.lang.String nombre) {
           this.clases = clases;
           this.costo = costo;
           this.descripcion = descripcion;
           this.fechaReg = fechaReg;
           this.imagen = imagen;
           this.inst = inst;
           this.nombre = nombre;
    }


    /**
     * Gets the clases value for this ActividadDeportiva.
     * 
     * @return clases
     */
    public publicadores.Clase[] getClases() {
        return clases;
    }


    /**
     * Sets the clases value for this ActividadDeportiva.
     * 
     * @param clases
     */
    public void setClases(publicadores.Clase[] clases) {
        this.clases = clases;
    }

    public publicadores.Clase getClases(int i) {
        return this.clases[i];
    }

    public void setClases(int i, publicadores.Clase _value) {
        this.clases[i] = _value;
    }


    /**
     * Gets the costo value for this ActividadDeportiva.
     * 
     * @return costo
     */
    public float getCosto() {
        return costo;
    }


    /**
     * Sets the costo value for this ActividadDeportiva.
     * 
     * @param costo
     */
    public void setCosto(float costo) {
        this.costo = costo;
    }


    /**
     * Gets the descripcion value for this ActividadDeportiva.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this ActividadDeportiva.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the fechaReg value for this ActividadDeportiva.
     * 
     * @return fechaReg
     */
    public java.util.Calendar getFechaReg() {
        return fechaReg;
    }


    /**
     * Sets the fechaReg value for this ActividadDeportiva.
     * 
     * @param fechaReg
     */
    public void setFechaReg(java.util.Calendar fechaReg) {
        this.fechaReg = fechaReg;
    }


    /**
     * Gets the imagen value for this ActividadDeportiva.
     * 
     * @return imagen
     */
    public java.lang.String getImagen() {
        return imagen;
    }


    /**
     * Sets the imagen value for this ActividadDeportiva.
     * 
     * @param imagen
     */
    public void setImagen(java.lang.String imagen) {
        this.imagen = imagen;
    }


    /**
     * Gets the inst value for this ActividadDeportiva.
     * 
     * @return inst
     */
    public publicadores.InstitucionDeportiva getInst() {
        return inst;
    }


    /**
     * Sets the inst value for this ActividadDeportiva.
     * 
     * @param inst
     */
    public void setInst(publicadores.InstitucionDeportiva inst) {
        this.inst = inst;
    }


    /**
     * Gets the nombre value for this ActividadDeportiva.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this ActividadDeportiva.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ActividadDeportiva)) return false;
        ActividadDeportiva other = (ActividadDeportiva) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.clases==null && other.getClases()==null) || 
             (this.clases!=null &&
              java.util.Arrays.equals(this.clases, other.getClases()))) &&
            this.costo == other.getCosto() &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.fechaReg==null && other.getFechaReg()==null) || 
             (this.fechaReg!=null &&
              this.fechaReg.equals(other.getFechaReg()))) &&
            ((this.imagen==null && other.getImagen()==null) || 
             (this.imagen!=null &&
              this.imagen.equals(other.getImagen()))) &&
            ((this.inst==null && other.getInst()==null) || 
             (this.inst!=null &&
              this.inst.equals(other.getInst()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre())));
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
        if (getClases() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getClases());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getClases(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Float(getCosto()).hashCode();
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getFechaReg() != null) {
            _hashCode += getFechaReg().hashCode();
        }
        if (getImagen() != null) {
            _hashCode += getImagen().hashCode();
        }
        if (getInst() != null) {
            _hashCode += getInst().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ActividadDeportiva.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "actividadDeportiva"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clases");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clases"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "clase"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("costo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "costo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("imagen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "imagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inst");
        elemField.setXmlName(new javax.xml.namespace.QName("", "inst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "institucionDeportiva"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
