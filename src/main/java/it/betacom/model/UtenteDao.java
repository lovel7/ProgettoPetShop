package it.betacom.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

public class UtenteDao {

//	private static final Logger logger = LogManager.getLogger(UtenteDao.class);

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GestionalePetShop", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static int save(Utente u) {
		int status = 0;
		try {
			Connection con = getConnection();

			PreparedStatement ps = con.prepareStatement(
					"insert into utente(Nome,Cognome,Email,Cellulare, Data_di_nascita, Password, UserName) values(?,?,?,?,?,?,?)");
			ps.setString(1, u.getNome());
			ps.setString(2, u.getCognome());
			ps.setString(3, u.getEmail());
			ps.setLong(4, u.getCellulare());
			ps.setString(5, u.getDataDiNascita());
			ps.setString(6, u.getPassword());
			ps.setString(7, u.getUserName());
			status = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		}
		return status;
	}

	public static String generaUsername(String nome, String cognome, String annoNascita) {
		// controllo che nome e cognome non siano nulli e minori di 2 lettere
		String nomeIniziali = (nome != null && nome.length() >= 2) ? nome.substring(0, 2) : "";
		String cognomeIniziali = (cognome != null && cognome.length() >= 2) ? cognome.substring(0, 2) : "";

		// Prendo anno di Nascita
		if (annoNascita != null && !annoNascita.isEmpty()) {
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(annoNascita);
				annoNascita = new SimpleDateFormat("yyyy").format(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Creo username con prime due lettere nome, prime due cognome e anno nascita
		String username = nomeIniziali + cognomeIniziali + annoNascita;
		int count = 1;
		try {
			while (controlloEsistenza(username)) {
				// Se l'username esiste già, aggiungi un numero progressivo
				username = username + count;
				count++;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return username.toLowerCase();

	}

	public static boolean controlloEsistenza(String username) {
		try {
			Connection con = getConnection();

			PreparedStatement ps;

			ps = con.prepareStatement("SELECT COUNT(*) FROM utente WHERE UserName = ?");

			ps.setString(1, username);

			try (ResultSet resultSet = ps.executeQuery()) {
				if (resultSet.next()) {
					// Estrae il valore nella prima colonna del risultato della query e lo
					// attribuisce a count
					// se count è maggiore a 0, il metodo ritorna true e quindi c'è già una
					// registrazione
					// con quella mail
					int count = resultSet.getInt(1);
					return count > 0;

				}

			}

			ps.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;

	}

	public static void controlloCredenziali(String username, String password, HttpServletResponse response)
			throws IOException {

		try {
			Connection con = getConnection();

			PreparedStatement ps = con
					.prepareStatement("select Password, Tentativi_Falliti, Stato from utente where UserName = ?");

			ps.setString(1, username);
			ResultSet result = ps.executeQuery();

			if (result.next()) {
				String passwordDatabase = result.getString("Password");
				int tentativiFalliti = result.getInt("Tentativi_Falliti");
				String statoUtente = result.getString("Stato");

				if (passwordDatabase.equals(password)) {
					if (statoUtente.equals("D")) {
						response.sendRedirect("LoginNonCorretto.jsp");

					} else {

						// mando l'utente alla jsp LoginCorretto
						response.sendRedirect("LoginCorretto.jsp");
						// logger.debug("l'utente " + username + " ha effettuato il login
						// correttamente");

						// azzero tentativi falliti
						resetTentativiFalliti(username);
					}
				} else {
					incrementaTentativiFalliti(username, tentativiFalliti);

					if (tentativiFalliti < 2) {
						response.sendRedirect("LoginNonCorretto.jsp?tentativi=" + (2 - tentativiFalliti));
						// logger.debug("l'utente " + username + " ha inserito la password errata");
					} else {
						disabilitaUtente(username);
						// blocco account
						response.sendRedirect("AccountBloccato.jsp");
						// logger.info("l'account di " + username + " è stato disabilitato");
					}

				}
			}

			result.close();
			ps.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private static void incrementaTentativiFalliti(String username, int tentativiFallitiAttuali) {
		try {

			Connection con = getConnection();

			PreparedStatement ps = con.prepareStatement("UPDATE utente SET Tentativi_Falliti = ? WHERE UserName = ?");
			ps.setInt(1, tentativiFallitiAttuali + 1);
			ps.setString(2, username);
			ps.executeUpdate();

			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void resetTentativiFalliti(String username) {
		try {
			Connection con = getConnection();

			PreparedStatement ps = con.prepareStatement("UPDATE utente SET Tentativi_Falliti = 0 WHERE Username = ?");
			ps.setString(1, username);
			ps.executeUpdate();

			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void disabilitaUtente(String username) {
		try {

			Connection con = getConnection();

			PreparedStatement ps = con.prepareStatement("UPDATE utente SET Stato = 'D' WHERE UserName = ?");
			ps.setString(1, username);

			ps.executeUpdate();

			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
