package it.betacom.model;


public class Utente {

	private int id;
	private String nome;
	private String cognome;
	private String email;
	private Long cellulare;
	private String dataDiNascita;
	private String password;
	private String userName;
	private String ruolo;
	private String stato;

	

	public Utente() {
		super();
	}

	public Utente(int id, String nome, String cognome, String email, Long cellulare, String dataDiNascita,
			String password, String userName, String ruolo, String stato) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.cellulare = cellulare;
		this.dataDiNascita = dataDiNascita;
		this.password = password;
		this.userName = userName;
		this.ruolo = ruolo;
		this.stato = stato;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCellulare() {
		return cellulare;
	}

	public void setCellulare(Long cellulare) {
		this.cellulare = cellulare;
	}

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", cellulare=" + cellulare
				+ ", dataDiNascita=" + dataDiNascita + ", password=" + password + ", userName=" + userName + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

}
