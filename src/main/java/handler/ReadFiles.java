package handler;

import game.Action;

import game.Match;
import game.Team;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import kalkukoordenation.Position;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.Util;

/**
 *Die Klasse ReadFiles ist  für das Auslesen aller Dateien verantwortlich, 
 *die im Programm benötigen werden.
 *
 * 
 * 
 * @author wildt
 * @version 1.0
 * 
 */
public class ReadFiles {
	
	private static final String TYP_SCORE = "action-soccer-score";
	private static final String TYP_ATTEMPT = "action-soccer-score-attempt";

	private ReadFiles() {
	}

	/**
	 * Die XML-vistrack-actions-Dateien werden einzeln in der Methode initXMLfile initialisiert und gelesen. 
	 * Diese Methode gibt ein Java-Document-Objekt (DOM-Objekt) zurück, die die gesamte XML-Datei enthält. 
	 * Dieses Objekt wird von verschiedenen Methoden in folgendem Ablauf des Import-Programm benötigt.
	 * 
	 * @param filePath
	 * @return doc, Document Objeckt mit allen Daten von den XML-Datei
	 */

	public static Document initXMLFile(File file) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();

		return doc;
	}

	/**
	 *  Die Methode readPosFile liest die Pos-Dateien aus und erzeugt eine Liste mit Objekten aus der Klasse Position,
	 *  die die Koordinaten x, y, die Halbzeit und die Position-Id beinhalten.
	 * 
	 * @param br,BufferedReader zur Pos-datei.
	 * @return list, Liste mit Positionen.
	 */

	public static List<Position> readPosFile(BufferedReader br) throws Exception {
		List<Position> list = new ArrayList<Position>();
		String line = br.readLine();

		while (line != null) {
			String[] stringArray = StringUtils.split(line, '#');

			// Zahlen (Nummerierung)
			String[] zahlen = StringUtils.split(stringArray[0], ',');
			String zahl = zahlen[0];
			String halbzeit = zahlen[2];

			// X, Y Potision
			String[] positionen = StringUtils.split(stringArray[4], ',');
			String x = positionen[0];
			String y = positionen[1];

			Position pos = new Position();
			pos.setId(Integer.parseInt(zahl));
			pos.setHalfTime(Integer.parseInt(StringUtils.remove(halbzeit, ';')));
			pos.setX(Float.parseFloat(x));
			pos.setY(Float.parseFloat(y));

			list.add(pos);

			line = br.readLine();
		}

		return list;

	}

	/**
	 * Die Methode readTeams entnimmt aus dem DOM-Objekt die Daten der Mannschaften und speichert diese in eine Liste mit Objekten 
	 * aus der Klasse Team. 
	 * Der Mannschaftsname und die Mannschafts_Id werden dann später in der Tabelle Team gespeichert.
	 * 
	 * @param doc, Document-Objekt.
	 * @return Mannschaften, Liste von Mannschaften ID und Namen.
	 */

	public static List<Team> readTeams(Document doc) {
		NodeList list12 = doc.getElementsByTagName("team-metadata");
		Node n0 = list12.item(0);
		Node n1 = list12.item(1);

		String key1 = n0.getAttributes().getNamedItem("team-key").getTextContent();
		String name1 = n0.getFirstChild().getNextSibling().getAttributes().getNamedItem("full").getTextContent();

		String key2 = n1.getAttributes().getNamedItem("team-key").getTextContent();
		String name2 = n1.getFirstChild().getNextSibling().getAttributes().getNamedItem("full").getTextContent();

		List<Team> mannschaften = new ArrayList<Team>();
		Team manschaft1 = new Team(Integer.parseInt(key1), name1);
		Team manschaft2 = new Team(Integer.parseInt(key2), name2);
		mannschaften.add(manschaft1);
		mannschaften.add(manschaft2);

		System.out.println("---------------------------- Mannschaften ------------------------");
		System.out.println("Team 1: " + n0.getAttributes().getNamedItem("team-key"));
		System.out.println("Team Name: " + n0.getFirstChild().getNextSibling().getAttributes().getNamedItem("full").getTextContent());
		System.out.println("Team 2: " + n1.getAttributes().getNamedItem("team-key"));
		System.out.println("Team Name: " + n1.getFirstChild().getNextSibling().getAttributes().getNamedItem("full").getTextContent());
		System.out.println("------------------------------------------------------------------");
		System.out.println();

		return mannschaften;

	}

	/**
	 * Die Methode readMatch extrahiert die Daten von einem Spiel aus dem DOM-Objekt und speichert in einem Match-Objekt. 
	 * Später werden diese Daten in der Tabelle Match gespeichert.
	 * 
	 * @param doc
	 *            Document-Objekt.
	 * @param br
	 *          BufferedReader zur Posdatei
	 *            
	 * @return Spiel, Match Objekt gibt zurück mit allen Spielaten.
	 */

	public static Match readMatch(Document doc, BufferedReader br) throws Exception {

		// Spieldatum
		NodeList list11 = doc.getElementsByTagName("event-metadata");
		Node n2 = list11.item(0);
		String spielDatum = n2.getAttributes().getNamedItem("start-date-time").getNodeValue();

		// Liga
		NodeList list13 = doc.getElementsByTagName("tournament-metadata");
		Node n3 = list13.item(0);
		String liga = n3.getAttributes().getNamedItem("tournament-name").getNodeValue();

		// SpielId
		NodeList list14 = doc.getElementsByTagName("event-metadata");
		Node n4 = list14.item(0);
		String spielid = n4.getAttributes().getNamedItem("event-key").getNodeValue();

		// Halbzeitanfang 1
		NodeList list145 = doc.getElementsByTagName("action-soccer-other");
		Node n145 = list145.item(0);
		String halbzeitAnfang_1 = n145.getAttributes().getNamedItem("imp:timestamp").getNodeValue();

		// Halbzeitende 1
		Node n1452 = list145.item(1);
		String halbzeitEnde_1 = n1452.getAttributes().getNamedItem("imp:timestamp").getNodeValue();

		// Halbzeitanfang 2
		Node n1453 = list145.item(2);
		String halbzeitAnfang_2 = n1453.getAttributes().getNamedItem("imp:timestamp").getNodeValue();

		// Halbzeitende 2
		Node n14524 = list145.item(3);
		String halbzeitEnde_2 = n14524.getAttributes().getNamedItem("imp:timestamp").getNodeValue();

		// MannschaftId
		NodeList list121 = doc.getElementsByTagName("team-metadata");
		String mannschfat_id1 = list121.item(0).getAttributes().getNamedItem("team-key").getTextContent();
		String mannschfat_id2 = list121.item(1).getAttributes().getNamedItem("team-key").getTextContent();

		// Fußballfeld
		List<Float> matchFeld = getSoccerField(br);

		System.out.println("---------------------------- Spieldaten ------------------------");
		System.out.println("Spieltag: " + spielDatum);
		System.out.println("Liga: " + liga);
		System.out.println("Spiel_ID: " + spielid);
		System.out.println("Spiel_ID Integer: " + Integer.parseInt(spielid));
		System.out.println("HalbzeitAnfang_1: " + halbzeitAnfang_1);
		System.out.println("HalbzeitEnde_1: " + halbzeitEnde_1);
		System.out.println("HalbzeitAnfang_2: " + halbzeitAnfang_2);
		System.out.println("HalbzeitEnde_2: " + halbzeitEnde_2);
		System.out.println("Mannschfat_id1: " + mannschfat_id1);
		System.out.println("Mannschfat_id2: " + mannschfat_id2);
		System.out.println("Feldlänge: " + matchFeld.get(0));
		System.out.println("Feldbreit: " + matchFeld.get(1));
		System.out.println("----------------------------------------------------------------");
		System.out.println();

		Match spiel = new Match(Integer.parseInt(spielid), Util.getDate(spielDatum), liga, Util.getTime(halbzeitAnfang_1), Util.getTime(halbzeitEnde_1), Util.getTime(halbzeitAnfang_2), Util.getTime(halbzeitEnde_2), Integer.parseInt(mannschfat_id1), Integer.parseInt(mannschfat_id2), matchFeld.get(0), matchFeld.get(1));

		return spiel;

	}

	/**
	 * Liest die Spielfeldlänger aus dem Pos Datei.
	 * 
	 * @return List,Spielfeldlänger.
	 */

	public static List<Float> getSoccerField(BufferedReader br) throws Exception {
		String zeile = br.readLine();
		String[] stringArray = StringUtils.split(zeile, '#');
		String[] feldLaenge = StringUtils.split(stringArray[5], ',');
		
		Float laenge = Float.parseFloat(StringUtils.remove(feldLaenge[1], ';'));
		Float breit = Float.parseFloat(StringUtils.remove(feldLaenge[2], ';'));
		
		List<Float> list = new ArrayList<Float>();
		list.add(laenge);
		list.add(breit);

		return list;
	}

	/**
	 * Die Methode readMatch extrahiert die Daten von einem Spiel aus dem DOM-Objekt und speichert in einem Match-Objekt. 
	 * Später werden diese Daten in der Tabelle Match gespeichert.
	 * 
	 * @return matchID.
	 */

	public static Integer getMatchId(Document doc) {
		NodeList nodeList = doc.getElementsByTagName("event-metadata");
		Node node = nodeList.item(0);
		String matchId = node.getAttributes().getNamedItem("event-key").getNodeValue();

		return Integer.parseInt(matchId);
	}

	/**
	 * Die Methode readAction liest einzelne Aktionen von einem Spiel aus dem DOM-Objekt. Eine Action kann vom Typ action-soccer-score-attemp 
	 * oder action-soccer-score sein. Der Typ action-soccer-score-attemp stellt einen Torversuch im Spiel dar. Während der Typ action-soccer-score
	 *  ein Tor im Spiel repräsentiert. 
	 * Diese Daten werden später in der Tabelle Action gespeichert. 
	 * 
	 * @param doc
	 * @return List, Listen mit alle Action Daten.
	 */

	public static List<Action> readAction(Document doc) throws Exception {

		NodeList list14 = doc.getElementsByTagName("event-metadata");
		Node n4 = list14.item(0);
		String spielid = n4.getAttributes().getNamedItem("event-key").getNodeValue();
		System.out.println("Spiel_ID: " + spielid);

		NodeList action_attempt_id = doc.getElementsByTagName("action-soccer-score-attempt");
		NodeList action_score = doc.getElementsByTagName("action-soccer-score");

		List<Action> list = new ArrayList<Action>();

		int i = 0;
		while (action_attempt_id.item(i) != null) {
			Node n45 = action_attempt_id.item(i);

			String id = n45.getAttributes().getNamedItem("id").getNodeValue();
			String timestamp = n45.getAttributes().getNamedItem("imp:timestamp").getNodeValue();
			String halbzeit = n45.getAttributes().getNamedItem("period-value").getNodeValue();

			System.out.println("id: " + id);
			System.out.println("timestamp: " + timestamp);
			System.out.println("Typ: " + TYP_ATTEMPT);
			System.out.println("halbzeit: " + halbzeit);

			Action aktion = new Action(Integer.parseInt(id), TYP_ATTEMPT, Util.getTime(timestamp), Integer.parseInt(halbzeit), Integer.parseInt(spielid));
			list.add(aktion);
			i++;
		}

		i = 0;
		while (action_score.item(i) != null) {

			Node nodeAktion = action_score.item(i);

			String idScore = nodeAktion.getAttributes().getNamedItem("id").getNodeValue();
			String timestampScore = nodeAktion.getAttributes().getNamedItem("imp:timestamp").getNodeValue();
			String halbzeit1 = nodeAktion.getAttributes().getNamedItem("period-value").getNodeValue();

			System.out.println("id: " + idScore);
			System.out.println("timestamp: " + timestampScore);
			System.out.println("Typ: " + TYP_SCORE);
			System.out.println("halbzeit: " + halbzeit1);

			Action aktion = new Action(Integer.parseInt(idScore), TYP_SCORE, Util.getTime(timestampScore), Integer.parseInt(halbzeit1), Integer.parseInt(spielid));
			list.add(aktion);
			i++;
		}

		return list;
	}

}
