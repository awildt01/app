package game;

/**
 * Die Klasse Team bezeichnet alle Daten, die zu einer Mannschaft gehört.Diese
 * Felder werden aus der Datei vistrack-actions entnomen und in der Tabelle Team
 * gespeichert
 * 
 * @author wildt
 * @version 1.0
 * 
 */

public class Team {

	private int id;
	private String name;

	/**
	 * Inisialisiert ein Objekt von der Typ Team.
	 * 
	 * ID: eindeutige Mannschaft-Id Name : Mannschaftsname.
	 * 
	 */

	public Team(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * gibt die Mannschaft-ID zurück.
	 * 
	 * @return id.
	 * 
	 */

	public int getId() {
		return id;
	}

	/**
	 * setze die Mannschaft-ID.
	 * 
	 * @return id.
	 * 
	 */

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * gibt die Mannschaftsname zurück .
	 * 
	 * @param id
	 *            .
	 */

	public String getName() {
		return name;
	}

	/**
	 * setze die Mannschaftsname.
	 * 
	 * @param name
	 *            .
	 * 
	 */

	public void setName(String name) {
		this.name = name;
	}
}
