package ru.cft;

import java.io.File;

public class Flags {
    private boolean o;
    private String oPath;
    private boolean p;
    private String pPrefix;
    private boolean a;
    private boolean s;
    private boolean f;

    public void setO(boolean o) {
        this.o = o;
    }

    public boolean getO() {
        return o;
    }
    public void setoPath(String oPath) {
        this.oPath = oPath;
    }

    public String getoPath() {
        return  oPath;
    }

    public void setP(boolean p) {
        this.p = p;
    }

    public boolean getP() {
        return p;
    }

    public void setpPrefix(String pPrefix) {
        this.pPrefix = pPrefix;
    }

    public String getpPrefix() {
        return pPrefix;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public boolean getA() {
        return a;
    }

    public void setS(boolean s) {
        this.s = s;
    }

    public boolean getS() {
        return s;
    }

    public void setF(boolean f) {
        this.f = f;
    }

    public boolean getF() {
        return f;
    }

    public String getOFileDirectory(){
        String result = "";
        if(getoPath() != null){
            result = oPath;
            checkOrCreateDirectory();
            if(getoPath().startsWith("\\")){
                result = getoPath().replaceFirst("\\\\", "");
            }
            if(getoPath().startsWith("/")){
                result = getoPath().replaceFirst("/", "");
            }
            if(!getoPath().endsWith(File.separator)){
                result = result + File.separator;
            }
        }
        return result;
    }

    private void checkOrCreateDirectory(){
        String dirName = getoPath();
        if(dirName.startsWith("/")){
            dirName = getoPath().replaceFirst("/", "");
        }
        if(dirName.startsWith("\\")){
            dirName = getoPath().replaceFirst("\\\\", "");
        }
        File theDir = new File(dirName);
        if (!theDir.exists()){
            if(!theDir.mkdirs()){
                System.out.println("Не удалось создать директорию: " + getoPath());
            }
        }
    }
}
