/**
 * DtProfesor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public class DtProfesor  extends publicadores.DtUsuario  implements java.io.Serializable {
    private java.lang.String biografia;

    private java.lang.String[] clases;

    private java.lang.String descripcion;

    private java.lang.String inst;

    private publicadores.InstitucionDeportiva institucionDeportiva;

    private java.lang.String sitioWeb;

    public DtProfesor() {
    }

    public DtProfesor(
           java.lang.String apellido,
           java.lang.String email,
           java.util.Calendar fechaNac,
           int id,
           java.lang.String imagen,
           java.lang.String nickname,
           java.lang.String nombre,
           java.lang.String password,
           java.lang.String biografia,
           java.lang.String[] clases,
           java.lang.String descripcion,
           java.lang.String inst,
           publicadores.InstitucionDeportiva institucionDeportiva,
           java.lang.String sitioWeb) {
        super(
            apellido,
            email,
            fechaNac,
            id,
            imagen,
            nickname,
            nombre,
            password);
        this.biografia = biografia;
        this.clases = clases;
        this.descripcion = descripcion;
        this.inst = inst;
        this.institucionDeportiva = institucionDeportiva;
        this.sitioWeb = sitioWeb;
    }


    /**
     * Gets the biografia value for this DtProfesor.
     * 
     * @return biografia
     */
    public java.lang.String getBiografia() {
        return biografia;
    }


    /**
     * Sets the biografia value for this DtProfesor.
     * 
     * @param biografia
     */
    public void setBiografia(java.lang.String biografia) {
        this.biografia = biografia;
    }


    /**
     * Gets the clases value for this DtProfesor.
     * 
     * @return clases
     */
    public java.lang.String[] getClases() {
        return clases;
    }


    /**
     * Sets the clases value for this DtProfesor.
     * 
     * @param clases
     */
    public void setClases(java.lang.String[] clases) {
        this.clases = clases;
    }

    public java.lang.String getClases(int i) {
        return this.clases[i];
    }

    public void setClases(int i, java.lang.String _value) {
        this.clases[i] = _value;
    }


    /**
     * Gets the descripcion value for this DtProfesor.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this DtProfesor.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the inst value for this DtProfesor.
     * 
     * @return inst
     */
    public java.lang.String getInst() {
        return inst;
    }


    /**
     * Sets the inst value for this DtProfesor.
     * 
     * @param inst
     */
    public void setInst(java.lang.String inst) {
        this.inst = inst;
    }


    /**
     * Gets the institucionDeportiva value for this DtProfesor.
     * 
     * @return institucionDeportiva
     */
    public publicadores.InstitucionDeportiva getInstitucionDeportiva() {
        return institucionDeportiva;
    }


    /**
     * Sets the institucionDeportiva value for this DtProfesor.
     * 
     * @param institucionDeportiva
     */
    public void setInstitucionDeportiva(publicadores.InstitucionDeportiva institucionDeportiva) {
        this.institucionDeportiva = institucionDeportiva;
    }


    /**
     * Gets the sitioWeb value for this DtProfesor.
     * 
     * @return sitioWeb
     */
    public java.lang.String getSitioWeb() {
        return sitioWeb;
    }


    /**
     * Sets the sitioWeb value for this DtProfesor.
     * 
     * @param sitioWeb
     */
    public void setSitioWeb(java.lang.String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtProfesor)) return false;
        DtProfesor other = (DtProfesor) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.biografia==null && other.getBiografia()==null) || 
             (this.biografia!=null &&
              this.biografia.equals(other.getBiografia()))) &&
            ((this.clases==null && other.getClases()==null) || 
             (this.clases!=null &&
              java.util.Arrays.equals(this.clases, other.getClases()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.inst==null && other.getInst()==null) || 
             (this.inst!=null &&
              this.inst.equals(other.getInst()))) &&
            ((this.institucionDeportiva==null && other.getInstitucionDeportiva()==null) || 
             (this.institucionDeportiva!=null &&
              this.institucionDeportiva.equals(other.getInstitucionDeportiva()))) &&
            ((this.sitioWeb==null && other.getSitioWeb()==null) || 
             (this.sitioWeb!=null &&
              this.sitioWeb.equals(other.getSitioWeb())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getBiografia() != null) {
            _hashCode += getBiografia().hashCode();
        }
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
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getInst() != null) {
            _hashCode += getInst().hashCode();
        }
        if (getInstitucionDeportiva() != null) {
            _hashCode += getInstitucionDeportiva().hashCode();
        }
        if (getSitioWeb() != null) {
            _hashCode += getSitioWeb().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtProfesor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "dtProfesor"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("biografia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "biografia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clases");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clases"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inst");
        elemField.setXmlName(new javax.xml.namespace.QName("", "inst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("institucionDeportiva");
        elemField.setXmlName(new javax.xml.namespace.QName("", "institucionDeportiva"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publicadores/", "institucionDeportiva"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sitioWeb");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sitioWeb"));
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
