import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/*
Java 8 brought a new type inference rule that states that a throws T is inferred as RuntimeException
whenever allowed. This gives the ability to implement sneaky throws without the helper method.

A problem with sneaky throws is that you probably want to catch the exceptions eventually,
but the Java compiler doesn't allow you to catch sneakily thrown checked exceptions using
exception handler for their particular exception type.

The compiler sees the signature with the throws T inferred to a RuntimeException type,
so it allows the unchecked exception to propagate. The Java Runtime doesn't see any type in
the throws as all throws are the same a simple throw e.

@SneakyThrows can be used to sneakily throw checked exceptions without actually
declaring this in your method's throws clause
 */
public class SneakyThrow {
    public static void main(String[] args) {
        try{
            getURIs();
        }catch (Exception e){
            System.out.println("Exception encounterd: "+e);
            e.printStackTrace();
        }
    }
    @SneakyThrows
    private static void getURIs(){
        List<String> list = (List<String>) Files.readAllLines(Path.of("/resources/test.txt"));
        System.out.println(list);
    }
}
