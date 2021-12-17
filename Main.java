import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    Scanner keyboard = new Scanner(System.in);
    String emptyText = "";

    //Auto Generated Password to make
    // key Generator
    byte[] array = new byte[7]; // length is bounded by 7
    new Random().nextBytes(array);
    String generatedString = new String(array, Charset.forName("UTF-8"));
    String password = generatedString;
    String salt = Password.generateRandomSalt();
    String masterEncryptionKey = new Password(password, salt).generateHash();

    final String MENU = "1. Encrypt File\n" + "2. Decrypt File\n" + "3. Exit Menu";
    final int ENCRYPT = 1;
    final int DECRYPT = 2;
    final int EXIT = 3;
    int option = 0;
    do {
      System.out.println(MENU);

      try {
        String userInput = keyboard.nextLine();
        option = Integer.parseInt(userInput);
        switch (option) {
          case ENCRYPT:

            System.out.println("What File would u like to load from");

            String fileSelect = keyboard.nextLine();
            File inputFile = new File(fileSelect);
            Scanner sc = new Scanner(inputFile);
            String originalPlaintext = sc.nextLine().toUpperCase(Locale.ROOT);



            System.out.println("your key = ");
            System.out.println(masterEncryptionKey);

            System.out.println("Enter your key to encrypt");
            String key = keyboard.nextLine();
            if (key.equals(masterEncryptionKey))
            {
              System.out.println("Before Encryption");
              System.out.println(originalPlaintext);
              System.out.println("~~~~~~~~~~~~~~~~~~~");
              System.out.println("After Encryption");
              String ciphertext = Cipher.encryptString(originalPlaintext, masterEncryptionKey);
              System.out.println(ciphertext);
              emptyText = ciphertext;
              System.out.println("~~~~~~~~~~~~~~~~~~~");
            }
            else {
              System.out.println("Encryption Failed = ");
              System.out.println(originalPlaintext);
            }
            break;
          case DECRYPT:

            if (emptyText.equals(""))
            {
              System.out.println("No File Selected / Message to Decrypt");
              option=0;
            }
            else{
              System.out.println("~~~~~~~~~~~~~~~~~~~");
              System.out.println("Decrypt A Message");
              System.out.println(emptyText);
              System.out.println("Enter Key");
              String emptyKey = keyboard.nextLine();
              if(emptyKey.equals(masterEncryptionKey)) {
                System.out.println("~~~~~~~~~~~~~~~~~~~");
                String decryptedPlaintext = Cipher.decryptString(emptyText, masterEncryptionKey);
                System.out.println("PLAINTEXT:");
                System.out.println(decryptedPlaintext);
                System.out.println("~~~~~~~~~~~~~~~~~~~");
            }

            }

            break;


        }













          }
      catch (InputMismatchException | NumberFormatException e)
      {
        System.out.println("Invalid Option");

        }
      }
    while (option != EXIT);
    System.out.println("Exiting Menu");
    }


}
