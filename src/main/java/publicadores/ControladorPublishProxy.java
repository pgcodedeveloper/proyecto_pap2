package publicadores;

public class ControladorPublishProxy implements publicadores.ControladorPublish {
  private String _endpoint = null;
  private publicadores.ControladorPublish controladorPublish = null;
  
  public ControladorPublishProxy() {
    _initControladorPublishProxy();
  }
  
  public ControladorPublishProxy(String endpoint) {
    _endpoint = endpoint;
    _initControladorPublishProxy();
  }
  
  private void _initControladorPublishProxy() {
    try {
      controladorPublish = (new publicadores.ControladorPublishServiceLocator()).getControladorPublishPort();
      if (controladorPublish != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)controladorPublish)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)controladorPublish)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (controladorPublish != null)
      ((javax.xml.rpc.Stub)controladorPublish)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public publicadores.ControladorPublish getControladorPublish() {
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish;
  }
  
  public java.lang.String[] obtenerActivDeporProfe(int arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerActivDeporProfe(arg0);
  }
  
  public java.lang.String[] obtenerRegistrosSocio(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerRegistrosSocio(arg0);
  }
  
  public void modificarActividadDeportiva(java.lang.String arg0, java.lang.String arg1, int arg2, float arg3, java.lang.String arg4) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    controladorPublish.modificarActividadDeportiva(arg0, arg1, arg2, arg3, arg4);
  }
  
  public java.lang.String[] obtenerInstituciones() throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerInstituciones();
  }
  
  public java.lang.String obtenerProfesorClase(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerProfesorClase(arg0, arg1);
  }
  
  public void altaActividadDeportiva(java.lang.String arg0, java.lang.String arg1, int arg2, float arg3, java.util.Calendar arg4, java.lang.String arg5, java.lang.String arg6) throws java.rmi.RemoteException, publicadores.ActividadDException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    controladorPublish.altaActividadDeportiva(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
  }
  
  public publicadores.InstitucionDeportiva obtenerInstitucion(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerInstitucion(arg0);
  }
  
  public java.lang.String[] obtenerActividades(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerActividades(arg0);
  }
  
  public java.lang.String[] obtenerProfesInst(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerProfesInst(arg0);
  }
  
  public void altaInstitucion(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, publicadores.InstitucionRepetidaException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    controladorPublish.altaInstitucion(arg0, arg1, arg2);
  }
  
  public void altaClaseActividad(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, java.util.Calendar arg5, java.util.Calendar arg6, java.lang.String arg7) throws java.rmi.RemoteException, publicadores.ClaseException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    controladorPublish.altaClaseActividad(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
  }
  
  public void altaSocioClase(java.lang.String arg0, java.lang.String arg1, java.util.Calendar arg2) throws java.rmi.RemoteException, publicadores.SocioYaInscriptoException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    controladorPublish.altaSocioClase(arg0, arg1, arg2);
  }
  
  public java.lang.String[] obtenerListaSocios() throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerListaSocios();
  }
  
  public java.lang.String[] obtenerInfoClase(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerInfoClase(arg0);
  }
  
  public java.lang.String[] obtenerClases(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerClases(arg0);
  }
  
  public java.lang.String[] obtenerActividad(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerActividad(arg0);
  }
  
  public publicadores.DtProfesor[] obtenerProfes() throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerProfes();
  }
  
  public publicadores.DtSocio[] obtenerSocios() throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerSocios();
  }
  
  public java.lang.String[] obtenerClasesProfe(int arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerClasesProfe(arg0);
  }
  
  public void altaUsuario(publicadores.DtUsuario arg0) throws java.rmi.RemoteException, publicadores.UsuarioRepetidoException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    controladorPublish.altaUsuario(arg0);
  }
  
  public java.lang.String[] obtenerClasesAct(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerClasesAct(arg0);
  }
  
  public void actualizarUsuario(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.util.Calendar arg4, java.lang.String arg5) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    controladorPublish.actualizarUsuario(arg0, arg1, arg2, arg3, arg4, arg5);
  }
  
  public java.lang.String[] obtenerUsuarios() throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerUsuarios();
  }
  
  public publicadores.DtUsuario obtenerUsuario(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerUsuario(arg0);
  }
  
  public void actualizarProfe(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.util.Calendar arg4, java.lang.String arg5, java.lang.String arg6, java.lang.String arg7, java.lang.String arg8) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    controladorPublish.actualizarProfe(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
  }
  
  public publicadores.DtProfesor obtenerProfesor(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerProfesor(arg0);
  }
  
  public java.lang.Object[] rankingClases() throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.rankingClases();
  }
  
  public java.lang.Object[] rankingActividades() throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.rankingActividades();
  }
  
  public publicadores.ActividadDeportiva[] obtenerActividadesInstitucion(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerActividadesInstitucion(arg0);
  }
  
  public publicadores.Clase[] obtenerClasesDeActividad(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerClasesDeActividad(arg0);
  }
  
  public void modificarInstitucion(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    controladorPublish.modificarInstitucion(arg0, arg1, arg2);
  }
  
  public void eliminarSocioRegistro(java.lang.String arg0, publicadores.DtUsuario arg1) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    controladorPublish.eliminarSocioRegistro(arg0, arg1);
  }
  
  public java.lang.String[] obtenerActividadClase(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.obtenerActividadClase(arg0);
  }
  
  public boolean existeClaseActividad(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.existeClaseActividad(arg0);
  }
  
  public boolean existeActividadDepo(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.existeActividadDepo(arg0, arg1);
  }
  
  public void setPassword(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    controladorPublish.setPassword(arg0, arg1, arg2, arg3);
  }
  
  public publicadores.DtUsuario loginUsuario(java.lang.String arg0) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.loginUsuario(arg0);
  }
  
  public boolean existeSocioClase(publicadores.Clase arg0, publicadores.Socio arg1) throws java.rmi.RemoteException{
    if (controladorPublish == null)
      _initControladorPublishProxy();
    return controladorPublish.existeSocioClase(arg0, arg1);
  }
  
  
}