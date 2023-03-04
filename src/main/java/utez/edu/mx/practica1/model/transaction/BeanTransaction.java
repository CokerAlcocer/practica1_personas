package utez.edu.mx.practica1.model.transaction;

public class BeanTransaction {
    private int id;
    private String nombrePersona;
    private String fechaMovimiento;
    private String tipo;
    private String datoViejo;
    private String datoNuevo;

    public BeanTransaction() {
    }

    public BeanTransaction(int id, String nombrePersona, String fechaMovimiento, String tipo, String datoViejo, String datoNuevo) {
        this.id = id;
        this.nombrePersona = nombrePersona;
        this.fechaMovimiento = fechaMovimiento;
        this.tipo = tipo;
        this.datoViejo = datoViejo;
        this.datoNuevo = datoNuevo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDatoViejo() {
        return datoViejo;
    }

    public void setDatoViejo(String datoViejo) {
        this.datoViejo = datoViejo;
    }

    public String getDatoNuevo() {
        return datoNuevo;
    }

    public void setDatoNuevo(String datoNuevo) {
        this.datoNuevo = datoNuevo;
    }


}
