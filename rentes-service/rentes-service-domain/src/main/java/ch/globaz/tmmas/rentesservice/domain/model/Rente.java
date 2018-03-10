package ch.globaz.tmmas.rentesservice.domain.model;

import lombok.ToString;

import java.time.LocalDate;

@ToString
public class Rente {

    private String numero;
    private LocalDate dateEnregistrement;
    private Long requerantId;


    public String numero() {
        return numero;
    }

    public Long id() {
        return id;
    }

    public Long requerantId () {
        return requerantId;
    }

    public LocalDate dateEnregistrement() {
        return dateEnregistrement;
    }



    public Rente(String numero, LocalDate dateEnregistrement, Long requerantId){
        this.requerantId = requerantId;
        this.dateEnregistrement = dateEnregistrement;
        this.numero = numero;
    }

    public static Rente builder(String numero, Long reuqerantId, LocalDate dateEnregistrement) {
        return new Rente(numero,dateEnregistrement,reuqerantId);
    }
    //hibernate
    private Long id;

    Rente () {}


}
