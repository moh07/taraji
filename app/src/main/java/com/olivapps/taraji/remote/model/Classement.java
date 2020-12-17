package com.olivapps.taraji.remote.model;

public class Classement {
    private Equipe equipe;
    private int rang;
    private int nbrJ;
    private int nbrG;
    private int nbrN;
    private int nbrP;
    private int nbrBp;

    public Classement(Equipe equipe, int rang,int nbrJ, int nbrG, int nbrN, int nbrP, int nbrBp) {
        this.equipe = equipe;
        this.rang = rang;
        this.nbrJ = nbrJ;
        this.nbrG = nbrG;
        this.nbrN = nbrN;
        this.nbrP = nbrP;
        this.nbrBp = nbrBp;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getNbrJ() {
        return nbrJ;
    }

    public void setNbrJ(int nbrJ) {
        this.nbrJ = nbrJ;
    }

    public int getNbrG() {
        return nbrG;
    }

    public void setNbrG(int nbrG) {
        this.nbrG = nbrG;
    }

    public int getNbrN() {
        return nbrN;
    }

    public void setNbrN(int nbrN) {
        this.nbrN = nbrN;
    }

    public int getNbrP() {
        return nbrP;
    }

    public void setNbrP(int nbrP) {
        this.nbrP = nbrP;
    }

    public int getNbrBp() {
        return nbrBp;
    }

    public void setNbrBp(int nbrBp) {
        this.nbrBp = nbrBp;
    }



    @Override
    public String toString() {
        return "Classement{" +
                "equipe=" + equipe.toString() +
                ", rang=" + rang +
                ", nbrJ=" + nbrJ +
                ", nbrG=" + nbrG +
                ", nbrN=" + nbrN +
                ", nbrP=" + nbrP +
                ", nbrBp=" + nbrBp +
                '}';
    }
}
