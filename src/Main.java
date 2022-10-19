import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta del fichero: ");
        File path = new File(sc.nextLine());

        if (path.exists() && path.isFile()) {
            String fileName = path.getName();
            ProcessBuilder process = new ProcessBuilder();
            if (System.getProperty("os.name").startsWith("Windows")) {
                process.command("cmd.exe", "/c" , "type", fileName, "|", "find", "/v", "/c", "", fileName);
                process.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            } else process.command("cat", fileName, "|", "wc", fileName);

            try {
                process.start();
            } catch (IOException e) {
                System.out.println("ERROR! Parámetros de entrada incorrectos.");
            }
        } else System.out.println("ERROR! El fichero introducido no existe.");
    }
}