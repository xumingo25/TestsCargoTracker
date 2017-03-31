package test.resources;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Clase p�blica que contiene un log en HTML de forma personalizada para la empresa
 * Testgroup S.A.
 */

public class LogResult {

	
	//static Insertard in=new Insertard();
	
	
	public static String curDir = System.getProperty("user.dir");
	public static String rutaLetraDiscoLocal = "C";
	public Calendar cal=Calendar.getInstance();
    public static String fecha_hora_Ejecucion = "";
    public String Hora_Ini = "";
    public String Fecha_Ini = "";
    public String Hora_Fin = "";
    public String Fecha_Fin = "";
	public String namecase = "";
	public int cods = 0;
	public int idn = 0;
	public int idsub = 0;
	public String create1 = "";
	public String colores1 = "";
	public String colores2 = "";
	public String colores3 = "";	
	public int cont=0;   
	public int porcentajepass=0;
	public int porcentajeerror=0;
	public int porcentajewarning=0;
	public int total=0;
    public WebDriver driverwarning;

	//Variables para puntos de verificacion exitosos
	public StringBuffer passLogVP = new StringBuffer();
	public StringBuffer passLogDescripcion = new StringBuffer();
	public StringBuffer passLogEvidencia = new StringBuffer();
	public StringBuffer passLogVPTotales = new StringBuffer();
	public ArrayList<String> passlist = new ArrayList<String>();	
	
	//Variables para puntos de verificacion Rechazados
	public StringBuffer errorLogVP = new StringBuffer();
	public StringBuffer errorLogDescripcion = new StringBuffer();
	public StringBuffer errorLogEvidencia = new StringBuffer();
	public StringBuffer errorLogVPTotales = new StringBuffer();
	public ArrayList<String> errorlist = new ArrayList<String>();
	
	//Variables para alertas arrojadas por sistema
	public StringBuffer warningLog = new StringBuffer();
	public StringBuffer warningLogDescripcion = new StringBuffer();
	public StringBuffer warningLogEvidencia = new StringBuffer();
	public StringBuffer warningLogVPTotales = new StringBuffer();	
	public ArrayList<String> warninglist = new ArrayList<String>();
	
	
	
		
	/**
	   * M�todo utilizado para almacenar en un ArrayList informaci�n(Nombre de VP, Descripci�n, Evidencia)
	   * de los puntos de verificaci�n que se han ejecutado de forma exitosa.
	   * @param NombreVP (no puede ser nulo y contiene el nombre del caso a utlizar dentro del log).
	   * @param Mensaje (no puede ser nulo y contiene el mensaje que se vera detallado en el log).
	   * @param driver (no puede ser nulo y contiene el conector del explorador a conectar).
	   */
	public void passLog(String NombreVP, String Mensaje, WebDriver driver){
		
		String Evidencia = capturaEvidencia(driver, "VP - "+NombreVP);
		
		//Se guardan la informaci�n del VP en el ArrayList
		passlist.add(NombreVP);
		passlist.add(Mensaje);
		passlist.add(Evidencia);
		
		//Ingresar Nombre del punto de verificaci�n en el textarea ubicado al lado izquierdo del index
		if(passLogVP.length()==0){
			passLogVP.append(NombreVP);
		}else{
			passLogVP.append("\\n"+NombreVP);//Agrega un salto de linea en el textarea de VP exitosos
		}

		passLogVPTotales.append("1");//Se suma 1 al StringBuffer con el total de VP Exitosos
	}
	
	/**
	   * M�todo utilizado para almacenar en un ArrayList informaci�n(Nombre de VP, Descripci�n, Evidencia)
	   * de los puntos de verificaci�n que se han ejecutado y han sido rechazados.
	   * @param NombreVP (no puede ser nulo y contiene el nombre del caso a utlizar dentro del log).
	   * @param Mensaje (no puede ser nulo y contiene el mensaje que se vera detallado en el log).
	   * @param driver (no puede ser nulo y contiene el conector del explorador a conectar).
	   */
	public void errorLog(String NombreVP, String Mensaje, WebDriver driver){

		String Evidencia = capturaEvidencia(driver, "VP - "+NombreVP);

		//Se guardan la informaci�n del VP en el ArrayList
		errorlist.add(NombreVP);
		errorlist.add(Mensaje);
		errorlist.add(Evidencia);

		//Ingresar Nombre del punto de verificaci�n en el textarea ubicado al lado izquierdo del index		
		if(errorLogVP.length()==0){
			errorLogVP.append(NombreVP);
		}else{
			errorLogVP.append("\\n"+NombreVP);//Agrega un salto de linea en el textarea de VP rechazados
		}

		errorLogVPTotales.append("1");//Se suma 1 al StringBuffer con el total de VP Rechazados

	}
		
	/**
	   * M�todo utilizado para almacenar en un ArrayList informaci�n(Nombre Alerta, Descripci�n, Evidencia)
	   * de las alertas arrojadas por el sistema.
	   * @param NombreVP (no puede ser nulo y contiene el nombre del caso a utlizar dentro del log).
	   * @param Mensaje (no puede ser nulo y contiene el mensaje que se vera detallado en el log).
	   * @param driver (no puede ser nulo y contiene el conector del explorador a conectar).
	   */
	public void warningLog(String NombreVP, String Mensaje, WebDriver driver){
		
		String Evidencia = capturaEvidencia(driver, "VP - "+NombreVP);
		
		//Se guardan la informaci�n de la alerta en el ArrayList
		warninglist.add(NombreVP);
		warninglist.add(Mensaje);
		warninglist.add(Evidencia);
		
		//Ingresar Nombre del punto de verificaci�n en el textarea ubicado al lado izquierdo del index				
		if(warningLog.length()==0){
			warningLog.append(NombreVP);
		}else{
			warningLog.append("\\n"+NombreVP);//Agrega un salto de linea en el textarea de Alertas
		}
		
		warningLogVPTotales.append("1");//Se suma 1 al StringBuffer con el total de Alertas	
	}
	
	/**
	   * M�todo utilizado para tomar una captura de la pantalla y porteriormente guardarla en un 
	   * directorio dentro del workspace (Ejecuci�nxxx/Evidencia), junto con todas las otras capturas
	   * obtenidas una vez finalizada la ejecuci�n del script.
	   * @return Retorna el Path final de la imagen.
	   * @param driver (no puede ser nulo y contiene el conector del explorador).
	   * @param imageName (no puede ser nulo y contiene el nombre de la imagen).
	 * @throws IOException 
	 * @throws AWTException 
	 * @throws HeadlessException 
	   */
	public static void EvidenciaEspecial(String nombrearchivo) throws IOException, HeadlessException, AWTException{
    File carpetaArchivos = new File(curDir+"\\CapturaEvidenciasEspeciales");  
  	if (!carpetaArchivos.exists()) {
  		carpetaArchivos.mkdir();
  	}
           
    BufferedImage imagen = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    ImageIO.write(imagen,  "png", new File(curDir + "\\CapturaEvidenciasEspeciales\\"+nombrearchivo));
	}
    
	public static String capturaEvidencia(WebDriver driver, String imageName) {
  
			java.util.Date today2 = Calendar.getInstance().getTime();
		    SimpleDateFormat formatter = new SimpleDateFormat("_dd_MM_yyyy_MM_dd-hh_mm_ss");
		    String fecha_hora = formatter.format(today2);
		    
		  //Directorio donde quedaran las imagenes guardadas
		  File directory = new File(curDir+"\\log\\Ejecución"+ fecha_hora_Ejecucion +"\\Evidencia");
		
		  try {
			  	if (!directory.exists()) {
			  		directory.mkdir();
			  	}
		  
			  	//Toma la captura de imagen 
			  	File imagen = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			  	//Mueve el archivo a la carga especificada con el respectivo nombre
			  	FileUtils.copyFile(imagen, new File(directory.getAbsolutePath() + "\\" + imageName + fecha_hora +".png"));
			  	String Imagen = "Evidencia/"+imageName + fecha_hora +".png";
			  	return Imagen;
		  } catch (IOException e) {
			  //Impresion de Excepciones 
		      System.out.println(e);
		      return null;
		  }
	  }
	  
	  /**
	   * M�todo utilizado para cargar todos los archivos (CSS, Imagenes, Javascript y HTML)
	   * que son necesarios para cargar el log con un formato personalizado en HTML.
	   * Adem�s de invocar otros procesos necesarios para la creaci�n del Log.
	   */
	public void copiarCssImagenes(String ruta) throws IOException {
		  //Se crean los directorios donde se guardaran los archivos necesarios
		  String directorioPadre = ruta;
		  File directory1 = new File(directorioPadre+"\\Formato");		
		  if (!directory1.exists()) {
			directory1.mkdir();  
		  }
		  File directory2 = new File(directorioPadre+"\\Formato\\images");
		  if (!directory2.exists()) {
			directory2.mkdir();
		  }
		  File directory3 = new File(directorioPadre+"\\Formato\\js");		
		  if (!directory3.exists()) {
			directory3.mkdir();
		 }
		 File directory4 = new File(directorioPadre+"\\Formato\\css");		
		  if (!directory4.exists()) {
			directory4.mkdir();
		}
		  	  
				
		//Se copian las imagenes en la Ruta correspondiente
		FileCopy(curDir+"\\MaquetacionLog\\html\\images\\favicon.ico", directory2.toString()+"\\favicon.ico");
		FileCopy(curDir+"\\MaquetacionLog\\html\\images\\logo2.png", directory2.toString()+"\\logo2.png");
		FileCopy(curDir+"\\MaquetacionLog\\html\\images\\btnatras.png", directory2.toString()+"\\btnatras.png");
		FileCopy(curDir+"\\MaquetacionLog\\html\\images\\Camera.png", directory2.toString()+"\\Camera.png");
		FileCopy(curDir+"\\MaquetacionLog\\html\\images\\cerrar.png", directory2.toString()+"\\cerrar.png");
		FileCopy(curDir+"\\MaquetacionLog\\html\\images\\detalle.png", directory2.toString()+"\\detalle.png");	
		FileCopy(curDir+"\\MaquetacionLog\\html\\images\\detalle.png", directory2.toString()+"\\Home.png");		
		
		//Se copian los archivos javascript en la Ruta correspondiente
		FileCopy(curDir+"\\MaquetacionLog\\html\\js\\bgstretcher.js", directory3.toString()+"\\bgstretcher.js");
		FileCopy(curDir+"\\MaquetacionLog\\html\\js\\jquery.elastislide.js", directory3.toString()+"\\jquery.elastislide.js");
		FileCopy(curDir+"\\MaquetacionLog\\html\\js\\jquery.fancybox.pack.js", directory3.toString()+"\\jquery.fancybox.pack.js");
		FileCopy(curDir+"\\MaquetacionLog\\html\\js\\jquery.js", directory3.toString()+"\\jquery.js");
		FileCopy(curDir+"\\MaquetacionLog\\html\\js\\jquery-migrate-1.1.1.js", directory3.toString()+"\\jquery-migrate-1.1.1.js");   
		
		//Se copian los archivos CSS en la Ruta correspondiente
		FileCopy(curDir+"\\MaquetacionLog\\html\\css\\estilo.css", directory4.toString()+"\\estilo.css");
		FileCopy(curDir+"\\MaquetacionLog\\html\\css\\jquery.fancybox.css", directory4.toString()+"\\jquery.fancybox.css");
		
		//Se copian los archivos HTML en la Ruta correspondiente
		FileCopy(curDir+"\\MaquetacionLog\\html\\cuerpo\\index.html", directorioPadre+"\\index.html");
		FileCopy(curDir+"\\MaquetacionLog\\html\\cuerpo\\resumen.html", directorioPadre+"\\resumen.html");
		FileCopy(curDir+"\\MaquetacionLog\\html\\cuerpo\\alertas.html", directorioPadre+"\\alertas.html");
		FileCopy(curDir+"\\MaquetacionLog\\html\\cuerpo\\vp_exitosos.html", directorioPadre+"\\vp_exitosos.html");
		FileCopy(curDir+"\\MaquetacionLog\\html\\cuerpo\\vp_rechazados.html", directorioPadre+"\\vp_rechazados.html");		
		
		/**
		 * Se invocan los metodos para crear las paginas vp_exitosos.html, vp_rechazados.html y alertas.html
		 * las que contendran el detalle de cada VP ejecutado al igual que las alertas obtenidas.
		*/
		CrearTablaVP(passLogVPTotales.length(), passlist);
		sustitucionExitosos();
		CrearTablaVP(errorLogVPTotales.length(), errorlist);
		sustitucionRechazados();
		CrearTablaVP(warningLogVPTotales.length(), warninglist);
		sustitucionAlertas();
		
		//Se invoca metodo para calcular los porcentanjes de VP exitosos, rechazados y alertas
		calcularPorcentajes(passLogVPTotales.length(), errorLogVPTotales.length(), warningLogVPTotales.length());

		//Se invocan metodos para crear index.html y resumen.html con parametros dinamicos
		sustitucionIndex();
		sustitucionResumen();
		
		//Se cambiar valor a parametros para completar tabla de progreso al lado izquierdo del index
		colores1= rellenarProgreso("009c63", porcentajepass);
		colores2= rellenarProgreso("CC2D30", porcentajeerror);
		colores3= rellenarProgreso("284177", porcentajewarning);	
		sustitucionColores();//Metodo que crea 
}

	  /**
	   * M�todo utilizado para crear copia identica de dos archivos en los rutas especificas. 
	   * @param sourceFile (no puede ser nulo y contiene el path completo del archivo original).
	   * @param destinationFile (no puede ser nulo y contiene el path completo del archivo de destino).
	   */
      public void FileCopy(String sourceFile, String destinationFile) {
			try {
				File inFile = new File(sourceFile);
				File outFile = new File(destinationFile);

				FileInputStream in = new FileInputStream(inFile);
				FileOutputStream out = new FileOutputStream(outFile);

				int c;
				while( (c = in.read() ) != -1)
				out.write(c);
				in.close();
				out.close();
				
			} catch(IOException e) {
				System.err.println("Hubo un error de entrada/salida!!!");
			}
		}
	 
      /**
	   * M�todo utilizado para reemplazar variables en archivo resumen.html. 
	   */
	  public void sustitucionResumen() throws IOException { 
		  	String file = curDir+"\\log\\Ejecución"+fecha_hora_Ejecucion+"\\resumen.html";
		  	//Se crea un objeto File usando el constructor que recibe la ruta(String)
		    File archivo = new File (file);
		    //Usamos estos 2 objetos porque BufferedReader contiene un m�todo sencilo
		    //Para leer el archivo por linea
		    //FileReader fileReader;
		    
		    
		    
		    /**** HORA INICIO *****/    
		    //HORA
		    String v_horaInicio=Hora_Ini.substring(0, 2);			
			//MINUTOS
			String v_minutoInicio=Hora_Ini.substring(3, 5);			
			//segundos
			String v_segundosInicio=Hora_Ini.substring(6, 8);
		
			
	/***** HORA Termino *****/   	
			 //HORA
		    String v_horaFin=Hora_Fin.substring(0, 2);			
			//MINUTOS
			String v_minutoFin=Hora_Fin.substring(3, 5);			
			//segundos
			String v_segundosFin=Hora_Fin.substring(6, 8);
			
			
			//Parseo hora INICIO
			int horaInicioEjecucion = Integer.parseInt(v_horaInicio.toString());
			int minutoInicioEjecucion = Integer.parseInt(v_minutoInicio.toString());
			int segundosInicioEjecucion =Integer.parseInt(v_segundosInicio.toString());
			
			//Parseo hora FIN
			int horaFinEjecucion = Integer.parseInt(v_horaFin.toString());
			int minutoFinEjecucion = Integer.parseInt(v_minutoFin.toString());
			int segundosFinEjecucion = Integer.parseInt(v_segundosFin.toString());
			
			
			int diferenciaHoras= horaFinEjecucion - horaInicioEjecucion;
			int diferenciaMinutos = minutoFinEjecucion - minutoInicioEjecucion;
			int diferenciaSegundos = segundosFinEjecucion - segundosInicioEjecucion;
			
					
			
			int contenedorDiferenciaSegundoFinal=0;
			String labelTiempoEjecucion="";
			
			if(diferenciaSegundos>0){
				System.out.println("El tiempo de ejecucion fue de: "+ diferenciaHoras +":"+ diferenciaMinutos + ":"+ diferenciaSegundos );
				contenedorDiferenciaSegundoFinal = diferenciaSegundos;
				labelTiempoEjecucion= diferenciaHoras +":"+ diferenciaMinutos + ":"+ diferenciaSegundos + "  (hh:mm:ss)";
			}
			else if(diferenciaSegundos<0){
				int diferenciaSegundoFormateado=diferenciaSegundos*-1;
				System.out.println("Segundos formateado a positivo: "+ diferenciaSegundoFormateado);
				System.out.println("El tiempo de ejecucion fue de (segundos negativo): "+ diferenciaHoras +":"+ diferenciaMinutos + ":"+ diferenciaSegundoFormateado );
				
				contenedorDiferenciaSegundoFinal=diferenciaSegundoFormateado;
				labelTiempoEjecucion = diferenciaHoras +":"+ diferenciaMinutos + ":"+ contenedorDiferenciaSegundoFinal + "  (hh:mm:ss)";
			}

		    
		    
			try {

					FileInputStream fis = new FileInputStream(archivo);
					InputStreamReader is = new InputStreamReader(fis, "utf8");
					BufferedReader buffReader = new BufferedReader(is);
				
			    //Aqu� guardaremos cada l�nea del archivo por vez
			    String linea=null;
			    //Aqu� acumularemos todas las l�neas
			    String contenido="";
			    int cont=0;
			    //Cada que se invoca el m�todo readLine() se busca una linea y el cursor
			    //pasa a la siguiente linea cuando no hay mas lineas regresa null
					while((linea=buffReader.readLine())!=null){
					   
						switch (linea.trim()) {
				            case "var hora_inicial_resumen = \"12:00\";":  
			            		 	linea = "var hora_inicial_resumen = \""+Hora_Ini+"\";";
			            		 	break;
				            case "var fecha_inicial_resumen = \"30-06-2014\";":  
			            		 	linea = "var fecha_inicial_resumen = \""+Fecha_Ini+"\";";
			            		 	break;
				            case "var hora_termino_resumen = \"13:00\";":  
			            		 	linea = "var hora_termino_resumen = \""+Hora_Fin+"\";";
			            		 	break;
				            case "var fecha_termino_resumen = \"30-06-2014\";":  
			            		 	linea = "var fecha_termino_resumen = \""+Fecha_Fin+"\";";
			            		 	break;
				            case "var tiempo_ejecucion = \"00:00\";":  
		            		 		linea = "var tiempo_ejecucion = \""+ labelTiempoEjecucion +"\";";
		            		 		break;	 	 	
			            		 	
			            		 	
				            case "<a href=\"alertas.html\"><img src=\"Formato/images/detalle.png\" width=\"32px\" title=\"Mas detalles\" align=\"right\"/></a>":  
		            		 	if(warningLogVPTotales.length()==0)
		            		 	{linea = "<img src=\"Formato/images/detalle.png\" width=\"32px\" title=\"Mas detalles\" align=\"right\"/>";}
		            		 	break;
				            case "<a href=\"vp_exitosos.html\"><img src=\"Formato/images/detalle.png\" width=\"32px\" title=\"Mas detalles\" align=\"right\"/></a>":  
		            		 	if(passLogVPTotales.length()==0)
		            		 	{linea = "<img src=\"Formato/images/detalle.png\" width=\"32px\" title=\"Mas detalles\" align=\"right\"/>";}
		            		 	break;
				            case "<a href=\"vp_rechazados.html\"><img src=\"Formato/images/detalle.png\" width=\"32px\" title=\"Mas detalles\" align=\"right\"/></a>":  
		            		 	if(errorLogVPTotales.length()==0)
		            		 	{linea = "<img src=\"Formato/images/detalle.png\" width=\"32px\" title=\"Mas detalles\" align=\"right\"/>";}
		            		 	break;	
						}
					   
					   if(cont==0){
						   contenido+="\n<!--";
						   cont=1;
						   
					   }else{
						   contenido+="\n"+linea;
					   }
					}
			    
			    //Se valida que no sea nulo y se cierra
			    if( null != fis){
						fis.close();
						archivo.delete();
						BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
						 bw.write(contenido);
						 bw.close();
			    }
			    
			} catch (FileNotFoundException e) {				
				System.out.println(e);
			}		  
		  }
	  
      /**
	   * M�todo utilizado para reemplazar variables en archivo index.html. 
	   */
	  public void sustitucionIndex() throws IOException { 
		  	String file = curDir+"\\log\\Ejecución"+fecha_hora_Ejecucion+"\\index.html";
		  	//Se crea un objeto File usando el constructor que recibe la ruta(String)
		    File archivo = new File (file);
		    
		    //Usamos estos 2 objetos porque BufferedReader contiene un m�todo sencilo
		    //Para leer el archivo por linea
		    //FileReader fileReader;
			try {

					FileInputStream fis = new FileInputStream(archivo);
					InputStreamReader is = new InputStreamReader(fis, "utf8");
					BufferedReader buffReader = new BufferedReader(is);
				
			    //Aqu� guardaremos cada l�nea del archivo por vez
			    String linea=null;
			    //Aqu� acumularemos todas las l�neas
			    String contenido="";
			    int cont=0;
			    //Cada que se invoca el m�todo readLine() se busca una linea y el cursor
			    //pasa a la siguiente linea cuando no hay mas lineas regresa null
					while((linea=buffReader.readLine())!=null){
					   
						switch (linea.trim()) {
				            case "var nombreCaso = \"PRUEBA CASO 22\";":  
				            		linea = "var nombreCaso = \""+namecase+"\";";
				                    break;
				            case "var VP_Exitosos = \"VP_Prueba1\";":  
			            		 	linea = "var VP_Exitosos = \""+passLogVP+"\";";
			            		 	break;
				            case "var VP_Rechazados = \"VP_Prueba2\";":  
			            		 	linea = "var VP_Rechazados = \""+errorLogVP+"\";";
			            		 	break;
				            case "var VP_Alertas = \"VP_Prueba3\";":  
			            		 	linea = "var VP_Alertas = \""+warningLog+"\";";
			            		 	break;
				            case "var Total_exitosos = \"0\";":  
		            		 	linea = "var Total_exitosos = \""+passLogVPTotales.length()+"\";";
			            		 	break;
				            case "var Total_rechazados = \"0\";":  
		            		 	linea = "var Total_rechazados = \""+errorLogVPTotales.length()+"\";";
		            		 	break;
				            case "var Total_alertas = \"0\";":  
		            		 	linea = "var Total_alertas = \""+warningLogVPTotales.length()+"\";";
		            		 	break;
				            case "var porcentaje_exitosos = \"40%\";":  
		            		 	linea = "var porcentaje_exitosos = \""+porcentajepass+"\";";
		            		 	break;
				            case "var porcentaje_rechazados = \"40%\";":  
				            	linea = "var porcentaje_rechazados = \""+porcentajeerror+"\";";
		            		 	break;
				            case "var porcentaje_alertas = \"40%\";":   
				            	linea = "var porcentaje_alertas = \""+porcentajewarning+"\";";
				            	break;	 	
						}
						
					   if(cont==0){
						   contenido+="\n<!--";
						   cont=1;
						   
					   }else{
						   contenido+="\n"+linea;
					   }
					}
			    
			    //Se valida que no sea nulo y se cierra
			    if( null != fis){
						fis.close();
						archivo.delete();
						BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
						 bw.write(contenido);
						 bw.close();
			    }
			    
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
		  }
	  
      /**
	   * M�todo utilizado para reemplazar variables en archivo vp_exitosos.html. 
	   */	  
	  public void sustitucionExitosos() throws IOException { 
		  	String file = curDir+"\\log\\Ejecución"+fecha_hora_Ejecucion+"\\vp_exitosos.html";
		  	//Se crea un objeto File usando el constructor que recibe la ruta(String)
		    File archivo = new File (file);
		    //Usamos estos 2 objetos porque BufferedReader contiene un m�todo sencilo
		    //Para leer el archivo por linea
		    //FileReader fileReader;
			try {

					FileInputStream fis = new FileInputStream(archivo);
					InputStreamReader is = new InputStreamReader(fis, "utf8");
					BufferedReader buffReader = new BufferedReader(is);
				
			    //Aqu� guardaremos cada l�nea del archivo por vez
			    String linea=null;
			    //Aqu� acumularemos todas las l�neas
			    String contenido="";
			    int cont=0;
			    //Cada que se invoca el m�todo readLine() se busca una linea y el cursor
			    //pasa a la siguiente linea cuando no hay mas lineas regresa null
					while((linea=buffReader.readLine())!=null){
					   
						switch (linea.trim()) {
				            case "variablecreaciontabla1":  
				            		linea = ""+create1+"";
				                    break;
						}
					   
					   if(cont==0){
						   contenido+="\n<!--";
						   cont=1;
						   
					   }else{
						   contenido+="\n"+linea;
					   }
					}
			    
			    //Se valida que no sea nulo y se cierra
			    if( null != fis){
						fis.close();
						archivo.delete();
						BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
						 bw.write(contenido);
						 bw.close();
			    }
			    
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
		  }
	  
      /**
	   * M�todo utilizado para reemplazar variables en archivo vp_rechazados.html. 
	   */	  	  
	  public void sustitucionRechazados() throws IOException { 
		  	String file = curDir+"\\log\\Ejecución"+fecha_hora_Ejecucion+"\\vp_rechazados.html";
		  	//Se crea un objeto File usando el constructor que recibe la ruta(String)
		    File archivo = new File (file);
		    //Usamos estos 2 objetos porque BufferedReader contiene un m�todo sencilo
		    //Para leer el archivo por linea
			try {
					FileInputStream fis = new FileInputStream(archivo);
					InputStreamReader is = new InputStreamReader(fis, "utf8");
					BufferedReader buffReader = new BufferedReader(is);
				
			    //Aqu� guardaremos cada l�nea del archivo por vez
			    String linea=null;
			    //Aqu� acumularemos todas las l�neas
			    String contenido="";
			    int cont=0;
			    //Cada que se invoca el m�todo readLine() se busca una linea y el cursor
			    //pasa a la siguiente linea cuando no hay mas lineas regresa null
					while((linea=buffReader.readLine())!=null){
					   
						switch (linea.trim()) {
				            case "variablecreaciontabla2":  
				            		linea = ""+create1+"";
				                    break;
						}
					   
					   if(cont==0){
						   contenido+="\n<!--";
						   cont=1;
						   
					   }else{
						   contenido+="\n"+linea;
					   }
					}
			    
			    //Se valida que no sea nulo y se cierra
			    if( null != fis){
						fis.close();
						archivo.delete();
						BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
						 bw.write(contenido);
						 bw.close();
			    }
			    
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
}
	  
      /**
	   * M�todo utilizado para reemplazar variables en archivo alertas.html. 
	   */	  	  	  
	  public void sustitucionAlertas() throws IOException { 
		  	String file = curDir+"\\log\\Ejecución"+fecha_hora_Ejecucion+"\\alertas.html";
		  	//Se crea un objeto File usando el constructor que recibe la ruta(String)
		    File archivo = new File (file);
		    //Usamos estos 2 objetos porque BufferedReader contiene un m�todo sencilo
		    //Para leer el archivo por linea
		    //FileReader fileReader;
			try {
					FileInputStream fis = new FileInputStream(archivo);
					InputStreamReader is = new InputStreamReader(fis, "utf8");
					BufferedReader buffReader = new BufferedReader(is);
				
			    //Aqu� guardaremos cada l�nea del archivo por vez
			    String linea=null;
			    //Aqu� acumularemos todas las l�neas
			    String contenido="";
			    int cont=0;
			    //Cada que se invoca el m�todo readLine() se busca una linea y el cursor
			    //pasa a la siguiente linea cuando no hay mas lineas regresa null
					while((linea=buffReader.readLine())!=null){
					   
						switch (linea.trim()) {
				            case "variablecreaciontabla3":  
				            		linea = ""+create1+"";
				                    break;
						}
					   
					   if(cont==0){
						   contenido+="\n<!--";
						   cont=1;
						   
					   }else{
						   contenido+="\n"+linea;
					   }
					}
			    
			    //Se valida que no sea nulo y se cierra
			    if( null != fis){
						fis.close();
						archivo.delete();
						BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
						 bw.write(contenido);
						 bw.close();
			    }
			    
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
	}	 
	  
      /**
	   * M�todo para obtener Hora y Fecha del inicio de la ejecuci�n. 
	   */	  	  	  	  
	  public void InicioScript(WebDriver drive){
		  java.util.Date today = Calendar.getInstance().getTime();
		    SimpleDateFormat formatter = new SimpleDateFormat("_MM_dd_yyyy-HH_mm_ss");
		    fecha_hora_Ejecucion = formatter.format(today);
		    SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
		    Hora_Ini = formatter2.format(today);
		    SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MM-yyyy");
		    Fecha_Ini = formatter3.format(today);
		    drive.manage().window().maximize();
}
	  
      /**
	   * M�todo para obtener Hora y Fecha del fin de ejecuci�n. 
	   */	  	  	  	  	  
	  public void FinScript(){
		  java.util.Date today = Calendar.getInstance().getTime();
		    SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
		    Hora_Fin = formatter2.format(today);
		    SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MM-yyyy");
		    Fecha_Fin = formatter3.format(today);
 }
	  
      /**
	   * M�todo para crear tabla con el detalle de cada VP ya sea exitoso o rechazado,
	   * y tambi�n de cada alerta generada por el sistema. 
	   */	  	  	  	  	  
	  public void CrearTablaVP(int vptotales, ArrayList <String> lista)
	  {
		  String var1 = "<table border=\"2\" rules=\"all\" width=\"100%\">"+"\n"+
		   "<tr>"+"\n"+
		   "<td colspan=\"10\">"+"\n"+
		   "<div class=\"div_interna\">"+"\n"+
		   "<center>"+"\n"+
		   "<table class=\"tabla_interna\" align=\"center\" cellpadding=\"40\">"+"\n";
		  String var3 = 
					"</table>"+"\n"+
					"</div>"+"</td>"+"</tr>"+"\n"+
					"</table>"+	"\n"+
					"</center>"+"\n";
		  //Variable donde se almacenara el contenido a reemplazar en el archivo HTML deseado
		  create1=var1;
		  
			  if(vptotales%2==0)
			  {
				  CreacionPares(vptotales, lista);
				  
			  }
			  else if(vptotales>1)
			  {
				  CreacionPares(vptotales, lista);
				  CreacionImPares(lista);
			  }
			  else
			  {
				 CreacionImPares(lista);
			  }
			  
		  create1+=var3;
		  cont=0;
	  }

      /**
	   * M�todo para crear la tabla del metodo anterior, en caso que el numero total de VP
	   * o alertas sea par. 
	   */	  	  	  	  	  	
	  public void CreacionPares(int vptot, ArrayList <String> lista){
		  int tot=0;
		  tot= vptot/2;

		  while(tot!=0)
		  {
		  create1+="<tr>"+"\n";	  
			  
		  for(int x=1; x<=2; x++)
		  {  
			  String var2 = 
						"<td>"+"\n"+
						"<table rules=\"all\" border=\"2\">"+"\n"+
						"<tr>"+"\n"+
								"<td id=\"fondo\">Nombre VP</td>"+"<td id=\"info2\">"+lista.get(cont)+"</td>"+"\n"+
								"</tr>"+"\n"+
								"<tr>"+"\n"+
								"<td id=\"fondo\">Mensaje</td>"+"<td id=\"info2\">"+lista.get(cont+1)+"</td>"+"\n"+
								"</tr>"+"\n"+
								"<tr>"+"\n"+
								"<td id=\"fondo\">Evidencia</td>"+"<td id=\"info2\">"+"\n"+
								"<a class=\"view magnifier\" data-fancybox-group=\"gallery\" href=\""+lista.get(cont+2)+"\">"+"<img width=\"32px\" src=\"Formato/images/Camera.png\" />"+"</a>"+"\n"+
								"</td>"+"\n"+
								"</tr>"+"\n"+
							"</table>"+"\n"+
							"</td>";	  
			cont=cont+3;
		   create1 += var2;
		  }
		  tot=tot-1;
		  create1+="</tr>"+"\n";
		  }
	  }
	 
      /**
	   * M�todo para crear la tabla del metodo anterior, en caso que el numero total de VP
	   * o alertas sea impar. 
	   */	  	  	  	  	   
	  public void CreacionImPares(ArrayList <String> lista){

		  create1+="<tr>"+"\n";	  		
			  String var2 = 
						"<td colspan=\"2\">"+"\n"+
						"<center>"+"\n"+
						"<table rules=\"all\" border=\"2\">"+"\n"+
						"<tr>"+"\n"+
								"<td id=\"fondo\">Nombre VP</td>"+"<td id=\"info2\">"+lista.get(cont)+"</td>"+"\n"+
								"</tr>"+"\n"+
								"<tr>"+"\n"+
								"<td id=\"fondo\">Mensaje</td>"+"<td id=\"info2\">"+lista.get(cont+1)+"</td>"+"\n"+
								"</tr>"+"\n"+
								"<tr>"+"\n"+
								"<td id=\"fondo\">Evidencia</td>"+"<td id=\"info2\">"+"\n"+
								"<a class=\"view magnifier\" data-fancybox-group=\"gallery\" href=\""+lista.get(cont+2)+"\">"+"<img width=\"32px\" src=\"Formato/images/Camera.png\" />"+"</a>"+"\n"+
								"</td>"+"\n"+
								"</tr>"+"\n"+
							"</table>"+"\n"+
							"</center>"+"\n"+	
							"</td>";	  
			cont=cont+3;
		   create1 += var2;

		  create1+="</tr>"+"\n";
}
	  
      /**
	   * M�todo para crear tabla de progreso ubicada en el costado izquierdo del index
	   */	  	  	  	  	  	  
	  public void sustitucionColores() throws IOException { 
		  	String file = curDir+"\\log\\Ejecución"+fecha_hora_Ejecucion+"\\index.html";
		  	//Se crea un objeto File usando el constructor que recibe la ruta(String)
		    File archivo = new File (file);		    
		    //Usamos estos 2 objetos porque BufferedReader contiene un m�todo sencilo
		    //Para leer el archivo por linea
		    //FileReader fileReader;
			try {
					FileInputStream fis = new FileInputStream(archivo);
					InputStreamReader is = new InputStreamReader(fis, "utf8");
					BufferedReader buffReader = new BufferedReader(is);
				
			    //Aqu� guardaremos cada l�nea del archivo por vez
			    String linea=null;
			    //Aqu� acumularemos todas las l�neas
			    String contenido="";
			    int cont=0;
			    //Cada que se invoca el m�todo readLine() se busca una linea y el cursor
			    //pasa a la siguiente linea cuando no hay mas lineas regresa null
					while((linea=buffReader.readLine())!=null){
					   
						switch (linea.trim()) {
							case "creartablaprogreso1":  
		            		linea = ""+colores1+"";
		                    break;
				            case "creartablaprogreso2":  
			            		linea = ""+colores2+"";
			                    break;
				            case "creartablaprogreso3":  
			            		linea = ""+colores3+"";
			                    break;
						}
					   
					   if(cont==0){
						   contenido+="\n<!--";
						   cont=1;
						   
					   }else{
						   contenido+="\n"+linea;
					   }
					}
			    //Se valida que no sea nulo y se cierra
			    if( null != fis){
						fis.close();
						archivo.delete();
						BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
						 bw.write(contenido);
						 bw.close();
			    }
			    
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
}
	  
      /**
	   * M�todo para crear tabla de progreso ubicada en el costado izquierdo del index
	   */	  	  	  	  	  	  	  
	  public void calcularPorcentajes(int passTotales, int errorTotales, int warningTotales)
	  {
		  total=passLogVPTotales.length()+errorLogVPTotales.length()+warningLogVPTotales.length();
		  porcentajepass= (int)redondear(((passTotales*100)/total));
		  porcentajeerror=(int)redondear(((errorTotales*100)/total));
		  porcentajewarning=(int)redondear(((warningTotales*100)/total));
		  	
		  int porcentajetotal=porcentajepass+porcentajeerror+porcentajewarning;
		  
				if(porcentajetotal<100)
				{
					if(porcentajepass>porcentajeerror && porcentajepass>porcentajewarning)
					{
					 porcentajepass=porcentajepass+1;
					}
					else if(porcentajeerror>porcentajepass && porcentajeerror>porcentajewarning)
					{
						porcentajeerror=porcentajeerror+1;
					}
					else if(porcentajewarning>porcentajepass && porcentajewarning>porcentajeerror)
					{
						porcentajewarning=porcentajewarning+1;
					}
				}	
				System.out.println(porcentajetotal);
	}

      /**
	   * M�todo para crear tabla de progreso ubicada en el costado izquierdo del index
	   */	  	  	  	  	  	  	  	  
	  public String rellenarProgreso(String color, int porcentaje){
		String variable = "";  

		  if(porcentaje<=10 && porcentaje>0)
		  {
			  variable+= "w2('<TABLE rules=\"all\" style=\"border-color:#A9A9A9; border-collapse: separate\" border=\"1\" align=\"center\">')"+"\n"+
						"w2('<TR>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('</TR>')"+"\n"+	
						"w2('</TABLE>')"; 
		  }
		  else if(porcentaje>10 && porcentaje<=20)
		  {
			  variable+= "w2('<TABLE rules=\"all\" style=\"border-color:#A9A9A9; border-collapse: separate\" border=\"1\" align=\"center\">')"+"\n"+
						"w2('<TR>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('</TR>')"+"\n"+	
						"w2('</TABLE>')"; 
		  }
		  else if(porcentaje>20 && porcentaje<=30)
		  {
			  variable+= "w2('<TABLE rules=\"all\" style=\"border-color:#A9A9A9; border-collapse: separate\" border=\"1\" align=\"center\">')"+"\n"+
						"w2('<TR>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('</TR>')"+"\n"+	
						"w2('</TABLE>')"; 
		  }
		  else if(porcentaje>30 && porcentaje<=40)
		  {
			  variable+= "w2('<TABLE rules=\"all\" style=\"border-color:#A9A9A9; border-collapse: separate\" border=\"1\" align=\"center\">')"+"\n"+
						"w2('<TR>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('</TR>')"+"\n"+	
						"w2('</TABLE>')"; 
		  }
		  else if(porcentaje>40 && porcentaje<=50)
		  {
			  variable+= "w2('<TABLE rules=\"all\" style=\"border-color:#A9A9A9; border-collapse: separate\" border=\"1\" align=\"center\">')"+"\n"+
						"w2('<TR>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('</TR>')"+"\n"+	
						"w2('</TABLE>')"; 
		  }
		  else if(porcentaje>50 && porcentaje<=60)
		  {
			  variable+= "w2('<TABLE rules=\"all\" style=\"border-color:#A9A9A9; border-collapse: separate\" border=\"1\" align=\"center\">')"+"\n"+
						"w2('<TR>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('</TR>')"+"\n"+	
						"w2('</TABLE>')"; 
		  }
		  else if(porcentaje>60 && porcentaje<=70)
		  {
			  variable+= "w2('<TABLE rules=\"all\" style=\"border-color:#A9A9A9; border-collapse: separate\" border=\"1\" align=\"center\">')"+"\n"+
						"w2('<TR>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('</TR>')"+"\n"+	
						"w2('</TABLE>')"; 
		  }
		  else if(porcentaje>70 && porcentaje<=80)
		  {
			  variable+= "w2('<TABLE rules=\"all\" style=\"border-color:#A9A9A9; border-collapse: separate\" border=\"1\" align=\"center\">')"+"\n"+
						"w2('<TR>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('</TR>')"+"\n"+	
						"w2('</TABLE>')"; 
		  }
		  else if(porcentaje>80 && porcentaje<=90)
		  {
			  variable+= "w2('<TABLE rules=\"all\" style=\"border-color:#A9A9A9; border-collapse: separate\" border=\"1\" align=\"center\">')"+"\n"+
						"w2('<TR>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('</TR>')"+"\n"+	
						"w2('</TABLE>')"; 
		  }
		  else if(porcentaje>90 && porcentaje<=100)
		  {
			  variable+= "w2('<TABLE rules=\"all\" style=\"border-color:#A9A9A9; border-collapse: separate\" border=\"1\" align=\"center\">')"+"\n"+
						"w2('<TR>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#"+ color +"\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('</TR>')"+"\n"+	
						"w2('</TABLE>')";   
		  }
		  else
		  {
			  variable+= "w2('<TABLE rules=\"all\" style=\"border-color:#A9A9A9; border-collapse: separate\" border=\"1\" align=\"center\">')"+"\n"+
						"w2('<TR>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('<TD style=\"background-color:#DDDBD1\">&nbsp&nbsp</TD>')"+"\n"+
						"w2('</TR>')"+"\n"+	
						"w2('</TABLE>')";   
		  }  
		  return variable;	  
	}
	  
      /**
	   * M�todo para crear redondear porcentajes
	   */		  
	  public static double redondear(double numero) {
			int numeroDecimales=0;
			long mult=(long)Math.pow(10,numeroDecimales);
			double resultado=(Math.round(numero*mult))/(double)mult;
			return resultado;
			}
	  /**
	   * M�todo utilizado para la creaci�n del log invocando otros metodos y las variables globales
	*/
		/**
		 * @param nombreSistema
		 * @param nombreCaso
		 */
		public void crearLog(String nombreCaso){
			
			namecase = nombreCaso;
			
			//Directorio donde quedaran las imagenes guardadas
			  File directory = new File(curDir+"\\log"); 
			  File directoryEjecucion = new File(curDir+"\\log\\Ejecución"+fecha_hora_Ejecucion);	
			  File f = new File(directoryEjecucion+"\\index.html");
			 // System.out.println(directoryEjecucion);
			//Escritura
			try{
				if (!directory.exists()) {
			  		directory.mkdir();
			  	}
				
				if (!directoryEjecucion.exists()) {
					directoryEjecucion.mkdir();
			  	}
				
				FinScript();
				
				copiarCssImagenes(directoryEjecucion.toString());
				
				Desktop desktop;
				if (Desktop.isDesktopSupported()){// En caso que el host  esta API 
		        desktop = Desktop.getDesktop();//Se obtiene una instancia del Desktop(Escritorio)de mi host 
		        try {
		            desktop.open(f);//Se abre el archivo con el programa predeterminado
		            }
		        catch (IOException ex){
		        	System.out.println(ex);
		        }	        	
		        }
		        else{ 
		        	warningLog("Apertura Navegador", "Lo sentimos,no se puede abrir el archivo; �sta Maquina no soporta la API Desktop", driverwarning);
		        }
				
				}catch(IOException e){
		        	System.out.println(e);
				};			
				//try {
				//	in.insertacaso(cods,namecase,total,passLogVPTotales,errorLogVPTotales,warningLogVPTotales,Hora_Ini,Fecha_Fin,Hora_Fin,Fecha_Fin,fecha_hora_Ejecucion,idn,idsub);
				//} catch (SQLException e) {
				//	System.out.println(e);					
				//}			
		}

}

	  	