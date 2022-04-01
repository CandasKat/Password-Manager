import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    Random random = ThreadLocalRandom.current();

    StringBuilder stringBuilder = new StringBuilder();
    private StringBuilder charlist(){
        for (int i = 33; i < 123; i++){
            char character = (char)i;
            stringBuilder.append(character);
        }
        return stringBuilder;
    }

    public String generateFor16(){
        StringBuilder password = new StringBuilder();

        while (password.length() < 16){
            int randomChar = random.nextInt(charlist().length());
            password.append(charlist().charAt(randomChar));
        }

        return String.valueOf(password);
    }

    public String generateFor28(){
        StringBuilder password = new StringBuilder();

        while (password.length() < 28){
            int randomChar = random.nextInt(charlist().length());
            password.append(charlist().charAt(randomChar));
        }
        return String.valueOf(password);
    }

}
