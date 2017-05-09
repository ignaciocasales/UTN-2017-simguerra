package main.java.com.utn.simbatallas.domain;

/**
 * Created by Ignacio on 5/6/2017.
 * <p>
 * Clase Mensaje de batalla
 */
public class MessageBattleLog extends Message {
    private String s1, s2, s3, s4, s5, s6, s7, s8;

    public MessageBattleLog(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
        super(
                s1 +
                        "->id: " + s2 +
                        "->" + s3 +
                        "->Daño: " + s4 +
                        " a " + s5 +
                        "->id: " + s6 +
                        "->" + s7 +
                        "->Vida: " + s8
        );
        this.setType("LOG: ");
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
    }

    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }

    public String getS3() {
        return s3;
    }

    public String getS4() {
        return s4;
    }

    public String getS5() {
        return s5;
    }

    public String getS6() {
        return s6;
    }

    public String getS7() {
        return s7;
    }

    public String getS8() {
        return s8;
    }
}
