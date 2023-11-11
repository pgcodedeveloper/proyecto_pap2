/**
 * ControladorPublish.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publicadores;

public interface ControladorPublish extends java.rmi.Remote {
    public java.lang.String[] obtenerInstituciones() throws java.rmi.RemoteException;
    public void altaActividadDeportiva(java.lang.String arg0, java.lang.String arg1, int arg2, float arg3, java.util.Calendar arg4, java.lang.String arg5, java.lang.String arg6) throws java.rmi.RemoteException, publicadores.ActividadDException;
    public void altaUsuario(publicadores.DtUsuario arg0) throws java.rmi.RemoteException, publicadores.UsuarioRepetidoException;
    public publicadores.InstitucionDeportiva obtenerInstitucion(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String[] obtenerProfesInst(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String[] obtenerActividades(java.lang.String arg0) throws java.rmi.RemoteException;
    public void altaClaseActividad(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, java.util.Calendar arg5, java.util.Calendar arg6, java.lang.String arg7) throws java.rmi.RemoteException, publicadores.ClaseException;
    public void altaSocioClase(java.lang.String arg0, java.lang.String arg1, java.util.Calendar arg2) throws java.rmi.RemoteException, publicadores.SocioYaInscriptoException;
    public java.lang.String[] obtenerListaSocios() throws java.rmi.RemoteException;
    public void altaInstitucion(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, publicadores.InstitucionRepetidaException;
    public publicadores.Clase obtenerInfoClase(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String[] obtenerClases(java.lang.String arg0) throws java.rmi.RemoteException;
    public publicadores.ActividadDeportiva obtenerActividad(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String[] obtenerClasesProfe(int arg0) throws java.rmi.RemoteException;
    public publicadores.DtProfesor[] obtenerProfes() throws java.rmi.RemoteException;
    public publicadores.DtSocio[] obtenerSocios() throws java.rmi.RemoteException;
    public java.lang.String[] obtenerClasesAct(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String[] obtenerUsuarios() throws java.rmi.RemoteException;
    public java.lang.Object[] rankingClases() throws java.rmi.RemoteException;
    public void actualizarProfe(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.util.Calendar arg4, java.lang.String arg5, java.lang.String arg6, java.lang.String arg7, java.lang.String arg8) throws java.rmi.RemoteException;
    public void actualizarUsuario(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.util.Calendar arg4, java.lang.String arg5) throws java.rmi.RemoteException;
    public publicadores.DtUsuario obtenerUsuario(java.lang.String arg0) throws java.rmi.RemoteException;
    public publicadores.DtProfesor obtenerProfesor(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.Object[] rankingActividades() throws java.rmi.RemoteException;
    public void modificarActividadDeportiva(java.lang.String arg0, java.lang.String arg1, int arg2, float arg3, java.lang.String arg4) throws java.rmi.RemoteException;
    public java.lang.String[] obtenerActivDeporProfe(int arg0) throws java.rmi.RemoteException;
    public publicadores.Registro[] obtenerRegistrosSocio(int arg0) throws java.rmi.RemoteException;
    public java.lang.String obtenerProfesorClase(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public publicadores.ActividadDeportiva[] obtenerActividadesInstitucion(java.lang.String arg0) throws java.rmi.RemoteException;
    public publicadores.Clase[] obtenerClasesDeActividad(java.lang.String arg0) throws java.rmi.RemoteException;
    public void modificarInstitucion(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public void eliminarSocioRegistro(java.lang.String arg0, publicadores.Socio arg1) throws java.rmi.RemoteException;
    public java.lang.String[] obtenerActividadClase(java.lang.String arg0) throws java.rmi.RemoteException;
    public boolean existeClaseActividad(java.lang.String arg0) throws java.rmi.RemoteException;
    public boolean existeActividadDepo(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public publicadores.Usuario loginUsuario(java.lang.String arg0) throws java.rmi.RemoteException;
    public boolean existeSocioClase(publicadores.Clase arg0, publicadores.Socio arg1) throws java.rmi.RemoteException;
    public void setPassword(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException;
}
