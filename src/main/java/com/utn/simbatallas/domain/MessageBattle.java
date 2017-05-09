package main.java.com.utn.simbatallas.domain;

/**
 * Created by Ignacio on 5/6/2017.
 * <p>
 * Clase Mensaje de batalla
 */
public class MessageBattle extends Message {
    private String s1, s2, s3, s4, s5, s6, s7, s8;

    public MessageBattle(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
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

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public String getS5() {
        return s5;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }

    public String getS6() {
        return s6;
    }

    public void setS6(String s6) {
        this.s6 = s6;
    }

    public String getS7() {
        return s7;
    }

    public void setS7(String s7) {
        this.s7 = s7;
    }

    public String getS8() {
        return s8;
    }

    public void setS8(String s8) {
        this.s8 = s8;
    }
}
