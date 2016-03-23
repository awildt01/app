package importieren;

import java.io.File;

import handler.InsertData;



/** Die Klasse Main beinhaltet die filePath von der XML-vistrack-actions-Datei und ruft die Methode execute und die gesamt Daten zu speichern.
/ In der Klasse wird zu erste ein Path aufgebaut, durch DIRECTORY wird eine File  auf die verzeichniss der Dateien Referenziert.
/ Die Array filearray beinhaltet eine List mit Referzien von alle Datei von den Verzeichnes.
/die IF Anweisung überprüft zu erst ob die Verzeichniss Leer ist. Wenn die Verzeichnes Leer ist die Ausgabe "Keine XML-Datei in Verzeichnis" sonst
/  Die Klasse InsertDate ist zuständig für die Speicherung der Daten in der Datenbank.
 */

public class Main {

	private static final String DIRECTORY = "C:/spiele";

	public static void main(String[] args) {

		System.out.println("Initialisierung gestartet...");

		try {
			File files = new File(DIRECTORY);
			File[] fileArray = files.listFiles();

			if (fileArray.length != 0) {
				System.out.println("Anzahl von XML-Dateien: " + fileArray.length);

				InsertData insertData = new InsertData(fileArray);
				insertData.execute();
				
				System.out.println("Import beendet");

			} else {
				System.out.println("Keine XML-Datei in Verzeichnis " + DIRECTORY + " gefunden.");
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			e.printStackTrace();
		}
	}
}
