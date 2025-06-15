/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_cupiCava
 * Autor: Equipo Cupi2 2020
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiCava.mundo;

import java.util.ArrayList;

/**
 * Clase que representa la Cupi-Cava. <br>
 * <b>inv: </b> <br>
 * TODO Parte1 PuntoC: Declare la invariante de la clase.
 */
public class CupiCava
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Lista de vinos en la cava.
     */
    private ArrayList<Vino> vinos;

    // -------------------------------------------------------------
    // Método Constructor
    // -------------------------------------------------------------

    /**
     * Construye una nueva cava sin vinos. <br>
     * <b>post:</b> La lista de vinos ha sido inicializada.
     */
    public CupiCava( )
    {
        vinos = new ArrayList<Vino>( );
    }

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Retorna la lista de vinos.
     * @return Lista de vinos.
     */
    public ArrayList<Vino> darVinos( )
    {
        return vinos;
    }

    /**
     * Busca un vino con el nombre dado por parámetro. <br>
     * <b>pre:</b> La lista de vinos está inicializada.
     * @param pNombre Nombre del vino. pNombre != null && pNombre != ""
     * @return Vino con el nombre dado, null en caso de no encontrarlo.
     */
    public Vino buscarVino( String pNombre )
    {
        Vino buscado = null;
        boolean encontre = false;

        for( int i = 0; i < vinos.size( ) && !encontre; i++ )
        {
            Vino vinoActual = ( Vino )vinos.get( i );
            if( vinoActual.darNombre( ).equalsIgnoreCase( pNombre ) )
            {
                buscado = vinoActual;
                encontre = true;
            }
        }

        return buscado;
    }

    /**
     * Busca un vino utilizando una búsqueda binaria. <br>
     * <b>pre: </b> La lista de vinos está inicializada y se encuentra ordenada por nombre.
     * @param pNombre Nombre del vino que se va a buscar. pNombre != null && pNombre != "".
     * @return Vino con el nombre dado, null en caso de no encontrarlo.
     */
    public Vino buscarBinarioPorNombre( String pNombre )
    {
    	 int inicio = 0;
         int fin = vinos.size() - 1;
         
         while (inicio <= fin) {
             int medio = (inicio + fin) / 2;
             Vino vinoMedio = vinos.get(medio);
             
             int comparacion = vinoMedio.darNombre().compareToIgnoreCase(pNombre);
             
             if (comparacion == 0) {
                 return vinoMedio; // Encontrado
             } else if (comparacion < 0) {
                 inicio = medio + 1; // Buscar en la mitad derecha
             } else {
                 fin = medio - 1; // Buscar en la mitad izquierda
             }
         }
         
         return null; // No encontrado
    }

    /**
     * Busca el vino más dulce (con mayor contenido en azúcar) de la cava. <br>
     * <b>pre:</b> La lista de vinos está inicializada.
     * @return Vino más dulce de la cava. Si la cava no tiene vinos se retorna null. Si existen varios vinos con el contenido en azúcar más alto, se retorna el primer vino
     *         encontrado.
     */
    public Vino buscarVinoMasDulce( )
    {
    	 if (vinos.isEmpty()) {
             return null;
         }
         
         Vino masDulce = vinos.get(0);
         
         for (int i = 1; i < vinos.size(); i++) {
             Vino actual = vinos.get(i);
             if (actual.darContenidoAzucar() > masDulce.darContenidoAzucar()) {
                 masDulce = actual;
             }
         }
         
         return masDulce;
    }

    /**
     * Busca el vino más seco (con menor contenido en azúcar) de la cava. <br>
     * <b>pre:</b> La lista de vinos está inicializada.
     * @return Vino más seco de la cava. Si la cava no tiene vinos se retorna null. Si existen varios vinos con el contenido en azúcar más bajo, se retorna el primer vino
     *         encontrado.
     */
    public Vino buscarVinoMasSeco( )
    {
    	 if (vinos.isEmpty()) {
             return null;
         }
         
         Vino masSeco = vinos.get(0);
         
         for (int i = 1; i < vinos.size(); i++) {
             Vino actual = vinos.get(i);
             if (actual.darContenidoAzucar() < masSeco.darContenidoAzucar()) {
                 masSeco = actual;
             }
         }
         
         return masSeco;
    }

    /**
     * Busca los vinos del tipo dado por parámetro. <br>
     * <b>pre:</b> La lista de vinos está inicializada.
     * @param pTipo Tipo de vino de acuerdo a su contenido en azúcar.pTipo != null && pTipo != "" && (pTipo == SECO || pTipo == ABOCADO || pTipo == SEMI_SECO || pTipo ==
     *        SEMI_DULCE || pTipo == DULCE).
     * @return Lista de vinos del tipo dado.
     */
    public ArrayList<Vino> buscarVinosDeTipo( String pTipo )
    {
    	 ArrayList<Vino> vinosTipo = new ArrayList<Vino>();
         
         for (Vino vino : vinos) {
             if (vino.darTipo().equals(pTipo)) {
                 vinosTipo.add(vino);
             }
         }
         
         return vinosTipo;
    }

    /**
     * Agrega un nuevo vino a la cava si no existe actualmente un vino en la cava con el mismo nombre.<br>
     * <b>pre:</b> La lista de vinos está inicializada.<br>
     * <b>post:</b> Se agregó un nuevo vino a la lista de vinos.<br>
     * @param pNombre Nombre del vino. pNombre != null && pNombre != "".
     * @param pPresentacion Presentación del vino. pPresentacion != null && pPresentacion != "" && (pPresentacion == BOTELLA || pPresentacion == BARRIL).
     * @param pAnhoElaboracion Año de elaboración del vino. pAnhoElaboracion > 0.
     * @param pContenidoAzucar Contenido en azúcar del vino. pContenidoAzucar >= 0
     * @param pTipo Tipo de vino de acuerdo a su contenido en azúcar. pTipo != null && pTipo != "" && (pTipo == SECO || pTipo == ABOCADO || pTipo == SEMI_SECO || pTipo ==
     *        SEMI_DULCE || pTipo == DULCE).
     * @param pColor Color del vino. pColor != null && pColor != "" && (pColor == TINTO || pColor == ROSADO || pColor == BLANCO).
     * @param pLugarOrigen Lugar de origen del vino. lugarElaboracion != null y lugarElaboracion != "".
     * @param pImagen Imagen del vino. pImagen != null && pImagen != "".
     * @return True si el vino es agregado, false de lo contrario.
     */
    public boolean agregarVino( String pNombre, String pPresentacion, int pAnhoElaboracion, double pContenidoAzucar, String pTipo, String pColor, String pLugarOrigen, String pImagen )
    {
        Vino buscado = buscarVino( pNombre );
        boolean agregada = false;

        if( buscado == null )
        {
            Vino vino = new Vino( pNombre, pPresentacion, pAnhoElaboracion, pContenidoAzucar, pTipo, pColor, pLugarOrigen, pImagen );
            vinos.add( vino );
            agregada = true;
        }
        
        verificarInvariante();
        return agregada;
    }

    /**
     * Ordena ascendentemente la lista de vinos por nombre usando el algoritmo de burbuja. <br>
     * <b>pre:</b> La lista de vinos está inicializada. <br>
     * <b>post:</b> La lista de vinos está ordenada por nombre (orden ascendente).
     */
    public void ordenarVinosPorNombre( )
    {
	   int n = vinos.size();
       
       for (int i = 0; i < n - 1; i++) {
           for (int j = 0; j < n - i - 1; j++) {
               Vino vino1 = vinos.get(j);
               Vino vino2 = vinos.get(j + 1);
               
               if (vino1.compararPorNombre(vino2) > 0) {
                   // Intercambiar vinos
                   vinos.set(j, vino2);
                   vinos.set(j + 1, vino1);
               }
           }
       } 
     }

    /**
     * Ordena descendentemente la lista de vinos por año de elaboración usando el algoritmo de selección. <br>
     * <b>pre:</b> La lista de vinos está inicializada. <br>
     * <b>post:</b> La lista de vinos está ordenada por año de elaboración (orden descendente).
     */
    public void ordenarVinosPorAnhoElaboracion( )
    {
    	
		int n = vinos.size();
	     
	     for (int i = 0; i < n - 1; i++) {
	         int maxIdx = i;
	         
	         for (int j = i + 1; j < n; j++) {
	             if (vinos.get(j).compararPorAnhoElaboracion(vinos.get(maxIdx)) > 0) {
	                 maxIdx = j;
	             }
	         }
	         
	         // Intercambiar el elemento encontrado con el primero
	         Vino temp = vinos.get(maxIdx);
	         vinos.set(maxIdx, vinos.get(i));
	         vinos.set(i, temp);
         }
    }

    /**
     * Ordena ascendentemente la lista de vinos por lugar de origen usando el algoritmo de inserción. <br>
     * <b>pre:</b> La lista de vinos está inicializada.<br>
     * <b> post: </b>La lista de vinos está ordenada por lugar de origen (orden ascendente).
     */
    public void ordenarVinosPorLugarOrigen( )
    {
    	int n = vinos.size();
        
        for (int i = 1; i < n; i++) {
            Vino key = vinos.get(i);
            int j = i - 1;
            
            while (j >= 0 && vinos.get(j).compararPorLugarOrigen(key) > 0) {
                vinos.set(j + 1, vinos.get(j));
                j = j - 1;
            }
            
            vinos.set(j + 1, key);
        }
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------
    /**
     * Verifica el invariante de la clase
     * <b>inv: </b> <br>
     * vinos != null.
     * No hay dos vinos con el mismo nombre en la cava.
     */
    
    private void verificarInvariante() {
        assert vinos != null : "La lista de vinos no puede ser nula";
        assert !hayVinosConNombreRepetido() : "No puede haber dos vinos con el mismo nombre";
    }
    
    /**
     * Verifica que no haya vinos con nombres repetidos en la cava.
     * @return true si hay vinos con nombres repetidos, false en caso contrario.
     */
    private boolean hayVinosConNombreRepetido() {
        for (int i = 0; i < vinos.size(); i++) {
            String nombreVino = vinos.get(i).darNombre();
            
            for (int j = i + 1; j < vinos.size(); j++) {
                if (nombreVino.equalsIgnoreCase(vinos.get(j).darNombre())) {
                    return true;
                }
            }
        }
        
        return false;
    }
    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     * @return Respuesta 1.
     */
    public String metodo1( )
    {
        return "Respuesta 1.";
    }

    /**
     * Método para la extensión 2.
     * @return Respuesta 2.
     */
    public String metodo2( )
    {
        return "Respuesta 2.";
    }
}