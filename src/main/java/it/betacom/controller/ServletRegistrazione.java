package it.betacom.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import it.betacom.model.Utente;
import it.betacom.model.UtenteDao;

/**
 * Servlet implementation class ServletRegistrazione
 */
@WebServlet(name = "Registrazione", urlPatterns = { "/Registrazione" })
public class ServletRegistrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
//    private static final Logger logger = LogManager.getLogger(ServletRegistrazione.class);


	public ServletRegistrazione() {
		super();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String cellulareStr = request.getParameter("cellulare");
		String password = request.getParameter("password");
		String dataNascita = request.getParameter("dataNascita");
		long cellulare = Long.parseLong(cellulareStr);
		String userName = UtenteDao.generaUsername(nome, cognome, dataNascita);

		try {
			if (UtenteDao.controlloEsistenza(userName) == true) {
				response.sendRedirect("RegistrationFailed.jsp");
				return;
			}
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

		Utente utente = new Utente();
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setEmail(email);
		utente.setCellulare(cellulare);
		utente.setPassword(password);
		utente.setDataDiNascita(dataNascita);
		utente.setUserName(userName);

		int status = UtenteDao.save(utente);

		if (status > 0) {
			
    //        logger.info("Registrazione effettuata per l’utente " + utente.getNome() + " " + utente.getCognome());
			
			// imposto attributo alla richiesta HTTP denominandolo generatedUsername il cui valore è userName
			request.setAttribute("generatedUsername", userName);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("registrazioneConferma.jsp");
			// inoltro risposta e richiesta all'indirizzo indicato sopra
			dispatcher.forward(request, response);
		} else {
			response.sendError(status, password);
		}

	}

}
