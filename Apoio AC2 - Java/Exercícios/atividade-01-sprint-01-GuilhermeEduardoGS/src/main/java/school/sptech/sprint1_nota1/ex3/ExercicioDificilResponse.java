package school.sptech.sprint1_nota1.ex3;

public class ExercicioDificilResponse {

    private Integer enesimoTermo;
    private Integer soma;

    public ExercicioDificilResponse(Integer enesimoTermo, Integer soma) {
        this.enesimoTermo = enesimoTermo;
        this.soma = soma;
    }

    public Integer getEnesimoTermo() {
        return enesimoTermo;
    }

    public void setEnesimoTermo(Integer enesimoTermo) {
        this.enesimoTermo = enesimoTermo;
    }

    public Integer getSoma() {
        return soma;
    }

    public void setSoma(Integer soma) {
        this.soma = soma;
    }
}
