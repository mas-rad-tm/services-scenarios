package ch.globaz.tmmas.rentesservice.application.api.dto;


public class RenteEtendueDto {

	private RenteDto rente;
	private PersonnesPhysiqueDto personne;

	public RenteEtendueDto () {}

	public RenteEtendueDto (RenteDto rente, PersonnesPhysiqueDto personne){
		this.rente = rente;
		this.personne = personne;
	}

	public RenteDto getRente () {
		return rente;
	}

	public PersonnesPhysiqueDto getPersonne () {
		return personne;
	}


}
