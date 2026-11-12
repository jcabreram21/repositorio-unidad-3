1. El Controlador y la entrada de datos (ControladorTorneo.java)
Registrar los equipos: Esta parte se encarga de pedir los datos por la consola.

Seguridad con el teclado: Si el usuario escribe una letra o un símbolo por error en lugar de un número, el programa usa un ciclo while para atrapar el fallo, mostrar un mensaje de aviso y volver a pedir un número válido sin que se cierre la aplicación.

Validación rápida: Justo después de recibir el número y antes de pedir los nombres de los equipos, el sistema revisa si el número es correcto. Si pones un número inválido (como el 1), el programa frena de golpe y avisa del error, evitando que pierdas tiempo escribiendo nombres.

Pedir los nombres: Si el número es correcto, se activa un ciclo for para pedir el nombre de cada equipo uno por uno de forma ordenada.

2. El Modelo y las reglas del torneo (Torneo.java)
Listas guardadas: Usa listas (ArrayList) para guardar todos los nombres de los equipos y los partidos que se van generando.

Validar el número de equipos: Aquí vive la regla principal que revisa si la cantidad de equipos ingresada es exactamente 2, 4, 8 o 16. Si no es ninguno de estos, se activa el error NumeroEquiposInvalidosException para rechazarlo al instante.

3. El sorteo de los partidos (Recursividad)
El sorteo automático: Usa una función recursiva (que se llama a sí misma) para armar las llaves de los partidos paso a paso (desde octavos, cuartos, semifinales hasta la final).

Evitar errores de emparejamientos: El sistema valida que ningún equipo repita rival en la misma fase y que un equipo no juegue contra sí mismo. Si algo falla en las llaves, lanza el error SorteoInvalidoException.

4. Guardar y cargar el juego (torneo.ser)
Hacerlo guardable: La clase principal implementa la función Serializable para que el juego se pueda convertir en un archivo guardado.

Guardar y abrir: Usa herramientas de Java (ObjectOutputStream y ObjectInputStream) para escribir el archivo torneo.ser en la computadora cuando quieres guardar el progreso, y para volver a abrirlo y cargar tus datos cuando vuelvas a iniciar el programa.

Instrucciones de Descarga y Ejecución
Descargar del repositorio: Entra al link del repositorio público en GitHub, haz clic en el botón verde <> Code y selecciona Download ZIP. Descomprime la carpeta en tu computadora.

Importar en el IDE: Abre Eclipse, ve al menú File > Open Projects from File System..., busca la carpeta que descomprimiste y dale a Finish.

Ejecutar el programa: Busca la clase principal que tiene el código de inicio (main), haz clic derecho sobre ella y selecciona Run As > Java Application.
