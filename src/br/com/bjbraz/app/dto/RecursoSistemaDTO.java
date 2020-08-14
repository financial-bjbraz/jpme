package br.com.bjbraz.app.dto;

public class RecursoSistemaDTO  {

    /**
	 * 
	 */
    private static final long serialVersionUID = 3851425835855883701L;
    private Integer cdRecu;
    private String nmProg;
    private Integer inAtivoInativo;
    private Boolean ehFilho;
    private String nmProgString;

    public Integer getCdRecu() {
        return cdRecu;
    }

    public void setCdRecu(Integer cdRecu) {
        this.cdRecu = cdRecu;
    }

    public String getNmProg() {
        return nmProg;
    }

    public void setNmProg(String nmProg) {
        this.nmProg = nmProg;
    }

    public Integer getInAtivoInativo() {
        return inAtivoInativo;
    }

    public void setInAtivoInativo(Integer inAtivoInativo) {
        this.inAtivoInativo = inAtivoInativo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdRecu == null) ? 0 : cdRecu.hashCode());
        result = prime * result + ((nmProg == null) ? 0 : nmProg.hashCode());
        return result;
    }


    public Boolean getEhFilho() {
        return ehFilho;
    }
    
    public Boolean isEhFilho(){
        return ehFilho;
    }

    public void setEhFilho(Boolean ehFilho) {
        this.ehFilho = ehFilho;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RecursoSistemaDTO other = (RecursoSistemaDTO) obj;
        if (cdRecu == null) {
            if (other.cdRecu != null)
                return false;
        }
        else if (!cdRecu.equals(other.cdRecu))
            return false;
        if (nmProg == null) {
            if (other.nmProg != null)
                return false;
        }
        else if (!nmProg.equals(other.nmProg))
            return false;
        return true;
    }

    public String getNmProgString() {
        return "\n\n" + nmProg;
    }

    public void setNmProgString(String nmProgString) {
        this.nmProg = nmProgString;
    }
    
    

}
