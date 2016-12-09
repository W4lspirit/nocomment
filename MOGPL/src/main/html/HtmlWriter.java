package main.html;

import java.util.HashMap;
import java.util.Map;

import dijkstra.Grid;

public class HtmlWriter {
	private static final String WHITE = "white";
	private static int next = 0;
	static String start = "<!DOCTYPE html>" + "<html lang='en'>" + "<head>" + "  <meta charset='utf-8'>"
			+ "  <meta http-equiv='X-UA-Compatible' content='IE=edge'>"
			+ "  <meta name='viewport' content='width=device-width, initial-scale=1'>" + "    <title>MOGPL</title>"
			+ "  <link href='css/bootstrap.min.css' rel='stylesheet'>" + "</head>" + "<body>"
			+ "  <h1>Rush Hour Printer </h1>";
	static String stop = "</script>"
			+ "    <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>"
			+ "<script src='js/bootstrap.min.js'></script>" + "  </body></html>";
	static String table = "		<div class='table-responsive'>" + "		  <table class='table'>";

	static String entable = "</table>" + "		</div><br>";

	static String[] colours = { "#309b95", "#5e6183", "#9fbf9f", "#6c6108", "#1ced43", "#8be690", "#e26399", "#8e5565",
			"#2e87f9", "#554e3c", "#7c399e", "#b605c5", "#938f3c", "#06256e", "#803025", "#3f3104", "#6fb66a",
			"#2a85f9", "#d9f6e0", "#73057a" };
	static final String RED = "#ff3637";
	static Map<String, String> m = new HashMap<>();

	public static String stop() {

		return stop;
	}

	public static String start() {
		return start;
	}

	public static String newTable() {

		return table;

	}

	public static String endTable() {

		return entable;

	}

	public static String table(Grid p) {
		StringBuilder g = new StringBuilder();
		p.getGrid().forEach(r -> {
			g.append("<tr>");
			r.forEach(c -> {
				g.append("<td style=background-color:" + getColour(c) + ";>");
				g.append(c);
				g.append("</td>");
			});
			g.append("</tr>");
		});
		;
		return g.toString();
	}

	private static String getColour(String key) {
		if (key.equals("g")) {
			return RED;
		}if (key.equals("0")) {
			return WHITE;
		}
		String col = m.get(key);
		if (col == null) {
			col = colours[next];
			m.put(key, col);
			next++;
		}
		return col;
	}
}
