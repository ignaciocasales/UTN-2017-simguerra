package main.java.com.utn.simbatallas.domain;

/**
 * Created by ignacio on 23/04/17.
 * <p>
 * Clase Soldado Base
 */
public class ArmyUnit implements ISoldierAttack, ISoldierDefense {

    private static int cantidadGlobal = 0;
    private int id;
    private int Salud;
    private boolean estado;
    private ISoldierAttack comportamientoAtaque;
    private ISoldierDefense comportamientoDefensa;

    public ArmyUnit(ISoldierAttack comportamientoAtaque, ISoldierDefense comportamientoDefensa) {
        this.setId(cantidadGlobal + 1);
        this.Salud = 250;
        this.estado = true;
        this.setComportamientoAtaque(comportamientoAtaque);
        this.setComportamientoDefensa(comportamientoDefensa);
        cantidadGlobal = cantidadGlobal + 1;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getSalud() {
        return Salud;
    }

    public void setSalud(int salud) {
        this.Salud = salud;
    }

    public static int getCantidadGlobal() {
        return cantidadGlobal;
    }

    public static void setCantidadGlobal(int cantidadGlobal) {
        ArmyUnit.cantidadGlobal = cantidadGlobal;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ISoldierAttack getComportamientoAtaque() {
        return comportamientoAtaque;
    }

    public void setComportamientoAtaque(ISoldierAttack comportamientoAtaque) {
        this.comportamientoAtaque = comportamientoAtaque;
    }

    public ISoldierDefense getComportamientoDefensa() {
        return comportamientoDefensa;
    }

    public void setComportamientoDefensa(ISoldierDefense comportamientoDefensa) {
        this.comportamientoDefensa = comportamientoDefensa;
    }

    @Override
    public void atacar(ArmyUnit oponente) {
        this.comportamientoAtaque.atacar(oponente);
    }

    @Override
    public int getDanioHechoUltimoAtaque() {
        return this.comportamientoAtaque.getDanioHechoUltimoAtaque();
    }

    @Override
    public void setDanioHechoUltimoAtaque(int danio) {
        this.comportamientoAtaque.setDanioHechoUltimoAtaque(danio);
    }

    @Override
    public int defend() {
        return this.comportamientoDefensa.defend();
    }

    @Override
    public String toString() {
        return "ArmyUnit";
    }
}