import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    private String chars = "qwertzuiopasdfghjklyxcvbnm";
    private String cap = chars.toUpperCase(Locale.ROOT);
    private String digits = "0123456789";
    private String special = "+@*#%&/()=?![]£$<>,;.:-_";
    private String password = "";
    List<String> charsListe = new ArrayList<String>();
    List<String> capListe = new ArrayList<String>();
    List<String> digitListe = new ArrayList<String>();
    List<String> specialListe = new ArrayList<String>();
    List<String> all = new ArrayList<String>();

    Random random = ThreadLocalRandom.current();


    private void listeyekoy(){

        for (int i = 0; i < chars.length(); i++){
            charsListe.add(String.valueOf(chars.charAt(i)));
        }

        for (int i = 0; i < cap.length(); i++){
            capListe.add(String.valueOf(cap.charAt(i)));
        }
        for (int i = 0; i < digits.length(); i++){
            digitListe.add(String.valueOf(digits.charAt(i)));
        }
        for (int i = 0; i < special.length(); i++){
            specialListe.add(String.valueOf(special.charAt(i)));
        }
    }

    public void generate(){
        listeyekoy();
        List<String> passList = new ArrayList<String>();

        while (passList.size() < 16 ){
            int rastgeleChar = random.nextInt(charsListe.size());
            int rastgeleCap = random.nextInt(capListe.size());
            int rastgeleDigit = random.nextInt(digitListe.size());
            int rastgeleSpecial = random.nextInt(specialListe.size());
            passList.add(charsListe.get(rastgeleChar));
            passList.add(capListe.get(rastgeleCap));
            passList.add(digitListe.get(rastgeleDigit));
            passList.add(specialListe.get(rastgeleSpecial));
        }
        String x = "";

        for(int i = 0; i < 16; i++){
            x += passList.get(i);
        }
        setPassword(x);


    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {

        Generator generator = new Generator();
        generator.generate();
        System.out.println(generator.getPassword());

    }


}
